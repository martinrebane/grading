package ee.ttu.kert.maria.central;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.kert.maria.git.GitService;
import ee.ttu.kert.maria.grading.Grade;
import ee.ttu.kert.maria.ide.EclipseService;
import ee.ttu.kert.maria.plagiarism.MossService;
import ee.ttu.kert.maria.plagiarism.Plagiarism;
import ee.ttu.kert.maria.review.Review;
import ee.ttu.kert.maria.sandbox.EmbeddablService;
import ee.ttu.kert.maria.sandbox.SandBox;
import ee.ttu.kert.maria.studenttask.StudentTask;
import ee.ttu.kert.maria.studenttask.StudentTaskService;
import ee.ttu.kert.maria.submission.Submission;
import ee.ttu.kert.maria.submission.SubmissionService;
import ee.ttu.kert.maria.task.Task;
import ee.ttu.kert.maria.task.TaskService;

@Service
@Transactional
/**
 * Central service activated by the central controller.
 * @author Maria Kert
 *
 */
public class CentralGitHookService {

	@Value("${paths.files.repos}")
	private String repoPath;
	private TaskService taskService;
	private StudentTaskService studentTaskService;
	private SubmissionService submissionService;
	private MossService mossService;
	private EmbeddablService embeddablService;
	private GitService gitService;
	private EclipseService eclipseService;

	public CentralGitHookService(TaskService taskService, StudentTaskService studentTaskService,
			SubmissionService submissionService, MossService mossService, EmbeddablService embeddablService,
			GitService gitService, EclipseService eclipseService) {
		this.taskService = taskService;
		this.studentTaskService = studentTaskService;
		this.submissionService = submissionService;
		this.mossService = mossService;
		this.embeddablService = embeddablService;
		this.gitService = gitService;
		this.eclipseService = eclipseService;
	}
	
	/**
	 * Method to initialize Tasks, StudentTasks and Submissions.
	 * Method is activated by a Git hook. It goes through each
	 * folder in a student's repository and creates instances.
	 * @param uniid Student identification 
	 * @param subjectCode Subject identification
	 */
	public void init(String uniid, String subjectCode) {
		gitService.pull(uniid, "");

		String path = repoPath.replace("/mnt/d", "D:");
		path += uniid;
		File repo = new File(path);
		File[] allTasks = repo.listFiles();

		for (File taskFolder : allTasks) {
			String taskName = taskFolder.getName();
			if (!taskName.startsWith(".")) {
				Task task = makeTask(taskName);
				StudentTask studentTask = makeStudentTask(uniid, task);
				Submission submission = makeSubmission(uniid, taskName, studentTask);
				embeddablService.addSubmissionToQueue(submission);
			}
		}
	}
	
	/**
	 * Task creation. Method checks if there already exists
	 * a task by the given taskName. If not, creates a new instance
	 * and saves it to the database.
	 * @param taskName Given task name
	 * @return Task from the database if one exists, new instance otherwise
	 */
	private Task makeTask(String taskName) {
		Task task = taskService.getByName(taskName);
		if (task == null) {
			System.out.println("creating task " + taskName);
			task = new Task();
			Plagiarism plagiarism = new Plagiarism();
			task.setName(taskName);
			plagiarism.setTask(task);
			task.setPlagiarism(plagiarism);
			task.setStudentTasks(new ArrayList<>());
			return taskService.save(task);
		}
		return task;
	}
	
	/**
	 * StudentTask creation. Method checks if there already exists
	 * a StudentTask that is associated with the given uniid and task. 
	 * If not, creates a new instance and saves it to the database.
	 * @param uniid Student identification
	 * @param task Given task
	 * @return StudentTask from the database if one exists, new instance otherwise
	 */
	private StudentTask makeStudentTask(String uniid, Task task) {
		StudentTask studentTask = studentTaskService.getByTaskAndUniid(task, uniid);
		if (studentTask == null) {
			System.out.println("creating studenttask");
			studentTask = new StudentTask();
			Review review = new Review();
			Grade grade = new Grade();
			grade.setStudentTask(studentTask);
			review.setStudentTask(studentTask);
			studentTask.setReview(review);
			studentTask.setGrade(grade);
			studentTask.setUniid(uniid);
			studentTask.setSubmissions(new ArrayList<>());
			studentTask.setTask(task);
			task.getStudentTasks().add(studentTask);
			return studentTaskService.save(studentTask);
		}
		return studentTask;
	}
	
	/**
	 * Submission creation. Method checks if there already exists
	 * a Submission that is associated with the given uniid, task 
	 * name and StudentTask. If not, creates a new instance and 
	 * saves it to the database.
	 * @param uniid Student identification
	 * @param taskName Given task name
	 * @param studentTask Given StudentTask
	 * @return Submission from the database if one exists, new instance otherwise
	 */
	private Submission makeSubmission(String uniid, String taskName, StudentTask studentTask) {

		if (gitService.hasChanged(uniid, taskName)) {
			System.out.println("creating submission");
			Submission submission = new Submission();
			SandBox sandBox = embeddablService.create(uniid, taskName);
			mossService.transferFiles(uniid, taskName);
			String embeddablLocation = embeddablService.zipProject(uniid, taskName);
			sandBox.setLocation(embeddablLocation);
			sandBox.setSubmission(submission);
			submission.setSandBox(sandBox);
			submission.setStudentTask(studentTask);
			String submissionLocation = eclipseService.createProject(uniid, taskName);
			submission.setLocation(submissionLocation);
			studentTask.getSubmissions().add(submission);
			return submissionService.save(submission);
		}
		return null;
	}
}
