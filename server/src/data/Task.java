package data;

import java.util.HashMap;
import java.util.Map;


public class Task {
    private String projectName;
    private String personName;
    private String taskContent;
    private String deadline;
    private String isFinish;

    public Task() {

    }

    public Task(String projectName, String personName, String taskContent, String deadline, String isFinish) {
        this.projectName = projectName;
        this.personName = personName;
        this.taskContent = taskContent;
        this.deadline = deadline;
        this.isFinish = "0";
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Map<String, String> toMap(){

        HashMap<String, String> outMap = new HashMap<>();
        outMap.put(Constant.PROJECT_NAME, this.getProjectName());
        outMap.put(Constant.PERSON_NAME, this.getPersonName());
        outMap.put(Constant.TASK_CONTENT, this.getTaskContent());
        outMap.put(Constant.DEADLINE, this.getDeadline());
        outMap.put(Constant.IS_FINISH, this.getIsFinish());

        return outMap;
    }

	public String getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}
}
