package com.example.admisistrator.demo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlFactory {
    private static String baseUrl = "http://172.20.10.3:8080/Team1/";

    private static String regiserUrl = "RegisterServlet?";

    private static String loginUrl = "LoginServlet?";

    private static String sendRequirementUrl = "SendRequirementServlet?";

    private static String informationUrl = "RefreshInfoServlet?";

    private static String requirementInfoUrl = "RefreshRequirementInfoServlet?";

    private static String projectInfoUrl = "RefreshProjectInfoServlet?";

    private static String taskInfoUrl = "TaskInfoServlet?";

    private static String editTaskUrl = "EditTaskServlet?";

    private static String createProjectUrl = "CreateProjectServlet?";

    private static String finishTaskUrl = "FinishTaskServlet?";

    private static String createMessageUrl = "CreateMessageServlet?";

    private static String refreshMessageUrl = "RefreshMessageServlet?";

    public static String getRegiserUrl(String account, String password) {
        return baseUrl + regiserUrl + "account=" + account + "&password=" + password;
    }

    public static String getLoginUrl(String account, String password) {
        return baseUrl + loginUrl + "account=" + account + "&password=" + password;
    }

    public static String getSendRequirementUrl(String account, String requirementMessage) {

        return baseUrl + sendRequirementUrl + "account=" + account + "&requirementMessage=" + transfer(requirementMessage);
    }

    public static String getInformationUrl(String number) {

        return baseUrl + informationUrl + "number=" + number;
    }

    public static String getRequirementInfoUrl(String number) {
        return baseUrl + requirementInfoUrl + "number=" + number;
    }

    public static String getProjectInfoUrl(String name) {
        return baseUrl + projectInfoUrl + "name=" + name;
    }

    public static String getTaskInfoUrl(String name) {
        return baseUrl + taskInfoUrl + "name=" + name;
    }

    public static String getEditTaskUrl(String projectName, String personName, String taskContent, String deadline) {
        return baseUrl + editTaskUrl + "projectName=" + projectName + "&personName=" + personName + "&taskContent=" + taskContent + "&deadline=" + deadline;
    }

    public static String getCreateProjectUrl(String projectName, String name1, String name2, String name3, String name4) {
        return baseUrl + createProjectUrl + "projectName=" + projectName + "&name1=" + name1 + "&name2=" + name2 + "&name3=" + name3
                + "&name4=" + name4;
    }

    public static String getFinishTaskUrl(String projectName, String deadline, String info) {
        return baseUrl + finishTaskUrl + "projectName=" + projectName + "&deadline=" + deadline + "&info=" + info;
    }

    public static String getCreateMessageUrl(String form, String content, String to) {
        return baseUrl + createMessageUrl + "from=" + form + "&content=" + content + "&to=" + to;
    }

    public static String getRefreshMessageUrl(String to) {
        return baseUrl + refreshMessageUrl + "to=" + to;
    }


    public static String transfer(String s) {
        String ss = "";
        try {
            ss = URLEncoder.encode(s, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ss;
    }
}
