package ee.ttu.joop.grading.central;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.joop.grading.git.GitService;
import ee.ttu.joop.grading.grading.Grade;
import ee.ttu.joop.grading.ide.EclipseService;
import ee.ttu.joop.grading.plagiarism.MossService;
import ee.ttu.joop.grading.plagiarism.Plagiarism;
import ee.ttu.joop.grading.review.Review;
import ee.ttu.joop.grading.sandbox.EmbeddablService;
import ee.ttu.joop.grading.sandbox.SandBox;
import ee.ttu.joop.grading.studenttask.StudentTask;
import ee.ttu.joop.grading.studenttask.StudentTaskService;
import ee.ttu.joop.grading.submission.Submission;
import ee.ttu.joop.grading.submission.SubmissionService;
import ee.ttu.joop.grading.task.Task;
import ee.ttu.joop.grading.task.TaskService;

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

		String path = repoPath + uniid;
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
			String embeddablLocation = "/file" + embeddablService.zipProject(uniid, taskName);
			sandBox.setLocation(embeddablLocation);
			sandBox.setSubmission(submission);
			submission.setSandBox(sandBox);
			submission.setStudentTask(studentTask);
			String submissionLocation = "/file" + eclipseService.createProject(uniid, taskName);
			submission.setLocation(submissionLocation);
			studentTask.getSubmissions().add(submission);
			return submissionService.save(submission);
		}
		return null;
	}
}
