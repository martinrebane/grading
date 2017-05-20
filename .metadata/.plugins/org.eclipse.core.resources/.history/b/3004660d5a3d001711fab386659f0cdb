package ee.ttu.kert.maria.central;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.kert.maria.git.GitService;
import ee.ttu.kert.maria.grading.Grade;
import ee.ttu.kert.maria.grading.GradeService;
import ee.ttu.kert.maria.ide.EclipseService;
import ee.ttu.kert.maria.plagiarism.MossService;
import ee.ttu.kert.maria.plagiarism.Plagiarism;
import ee.ttu.kert.maria.review.GitHubService;
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
public class CentralGitHookService {

	@Value("${paths.files.repos}")
	private String repoPath;
	private TaskService taskService;
	private StudentTaskService studentTaskService;
	private SubmissionService submissionService;
	private MossService mossService;
	private GitHubService gitHubService;
	private GradeService gradeService;
	private EmbeddablService embeddablService;
	private GitService gitService;
	private EclipseService eclipseService;

	public CentralGitHookService(TaskService taskService, StudentTaskService studentTaskService,
			SubmissionService submissionService, MossService mossService, GitHubService gitHubService,
			GradeService gradeService, EmbeddablService embeddablService, GitService gitService,
			EclipseService eclipseService) {
		this.taskService = taskService;
		this.studentTaskService = studentTaskService;
		this.submissionService = submissionService;
		this.mossService = mossService;
		this.gitHubService = gitHubService;
		this.gradeService = gradeService;
		this.embeddablService = embeddablService;
		this.gitService = gitService;
		this.eclipseService = eclipseService;
	}

	public void init(String uniid, String subjectCode) {
		gitService.pull(uniid, "");

		String path = repoPath.replace("/mnt/d", "D:");
		path += uniid;
		System.out.println(path);
		File repo = new File(path);
		File[] allTasks = repo.listFiles();

		for (File taskFolder : allTasks) {
			String taskName = taskFolder.getName();
			if (!taskName.startsWith(".")) {
				Task task = makeTask(uniid, taskName);
				StudentTask studentTask = makeStudentTask(uniid, taskName, task);
				makeSubmission(uniid, taskName, studentTask);
			}
		}
	}

	private Task makeTask(String uniid, String taskName) {
		Task task = taskService.getByName(taskName);
		if (task == null) {
			System.out.println("creating task");
			task = new Task();
			Plagiarism plagiarism = new Plagiarism();

			task.setName(taskName);
			plagiarism.setTask(task);
			mossService.transferFiles(uniid, taskName);
			task.setPlagiarism(mossService.save(plagiarism));
			task.setStudentTasks(new ArrayList<>());
			return taskService.save(task);
		}
		return task;
	}

	private StudentTask makeStudentTask(String uniid, String taskName, Task task) {
		StudentTask studentTask = studentTaskService.getByTaskAndUniid(task, uniid);
		if (studentTask == null) {
			System.out.println("creating studenttask");
			studentTask = new StudentTask();
			Review review = new Review();
			Grade grade = new Grade();
			grade.setStudentTask(studentTask);
			review.setUniId(uniid);
			review.setTaskName(taskName);
			review.setStudentTask(studentTask);
			studentTask.setReview(gitHubService.saveReview(review));
			studentTask.setGrade(gradeService.saveGrade(grade));
			studentTask.setUniid(uniid);
			studentTask.setSubmissions(new ArrayList<>());
			studentTask.setTask(task);
			task.getStudentTasks().add(studentTask);
			return studentTaskService.save(studentTask);
		}
		return studentTask;
	}

	private Submission makeSubmission(String uniid, String taskName, StudentTask studentTask) {
		String currentHash = gitService.getHash(uniid, taskName);
		String newHash = gitService.createHash(uniid, taskName);

		if (!newHash.equals(currentHash)) {
			System.out.println("creating submission");
			Submission submission = new Submission();
			SandBox sandBox = new SandBox();
			embeddablService.zipProject(uniid, taskName);
			submission.setSandBox(embeddablService.save(sandBox));
			submission.setStudentTask(studentTask);
			studentTask.getSubmissions().add(submission);
			eclipseService.createProject(uniid, taskName);
			return submissionService.save(submission);
		}
		return null;
	}
}
