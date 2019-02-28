package data;

import java.util.HashMap;
import java.util.Map;

public class Project {
    private String projectName;
    private String name1;
    private String name2;
    private String name3;
    private String name4;
    private String name1Proccess;
    private String name2Proccess;
    private String name3Proccess;
    private String name4Proccess;

    private String comment;

    public Project() {

    }

    public Project(String projectName, String name1, String name2, String name3, String name4, String comment) {
        this.projectName = projectName;
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
        this.name4 = name4;
        this.name1Proccess = "0";
        this.name2Proccess = "0";
        this.name3Proccess = "0";
        this.name4Proccess = "0"; 
        this.comment = comment;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getName4() {
        return name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }

    public String getName1Proccess() {
        return name1Proccess;
    }

    public void setName1Proccess(String name1Proccess) {
        this.name1Proccess = name1Proccess;
    }

    public String getName2Proccess() {
        return name2Proccess;
    }

    public void setName2Proccess(String name2Proccess) {
        this.name2Proccess = name2Proccess;
    }

    public String getName3Proccess() {
        return name3Proccess;
    }

    public void setName3Proccess(String name3Proccess) {
        this.name3Proccess = name3Proccess;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName4Proccess() {
        return name4Proccess;
    }

    public void setName4Proccess(String name4Proccess) {
        this.name4Proccess = name4Proccess;
    }

    public Map<String, String> toMap(){

        HashMap<String, String> outMap = new HashMap<>();
        outMap.put(Constant.PROJECT_NAME, this.getProjectName());
        outMap.put(Constant.NAME1, this.getName1());
        outMap.put(Constant.NAME2, this.getName2());
        outMap.put(Constant.NAME3, this.getName3());
        outMap.put(Constant.NAME4, this.getName4());
        outMap.put(Constant.NAME1_PROCCESS, this.getName1Proccess());
        outMap.put(Constant.NAME2_PROCCESS, this.getName2Proccess());
        outMap.put(Constant.NAME3_PROCCESS, this.getName3Proccess());
        outMap.put(Constant.NAME4_PROCCESS, this.getName4Proccess());
        outMap.put(Constant.COMMENT, this.getComment());

        return outMap;
    }
}
