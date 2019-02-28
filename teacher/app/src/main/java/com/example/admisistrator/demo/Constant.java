package com.example.admisistrator.demo;

import android.support.v7.widget.LinearLayoutManager;

public interface Constant {

    public final static int DEFAULT_ORENTATION = LinearLayoutManager.VERTICAL;

    public static int MSG_REGISTER = 0xA001;
    public static int MSG_LOGIN = 0x002;
    public static int MSG_SEND_REQUREMENT = 0x003;
    public static int MSG_REFRESH_INFO = 0x004;
    public static int MSG_CREATE_PROJECT = 0x005;
    public static int MSG_REFRESH_PROJECT = 0x006;
    public static int MSG_CREATE_TASK = 0x007;
    public static int MSG_REFRESH_TASK = 0x008;
    public static int MSG_FINISH_TASK = 0x009;
    public static int MSG_CREATE_MESSAGE = 0x010;
    public static int MSG_REFRESH_MESSAGE = 0x011;

    public static String RES_CODE = "resCode";
    public static String RES_MESSAGE = "resMsg";
    public static String USER_ID = "userId";
    public static String USER_PASSWORD = "password";

    public static final String REGISTER_SUCCESS = "100";
    public static final String REGISTER_SUCCESS_INFO = "注册成功";
    public static final String REGISTER_FAIL_EXIST_ACCOUNT = "101";
    public static final String REGISTER_FAIL_EXIST_ACCOUNT_INFO = "该账号已经注册，请使用此账号直接登录或使用其他账号";
    public static final String REGISTER_FAIL_INSERT_DATA = "102";
    public static final String REGISTER_FAIL_INSERT_DATA_INFO = "用户信息表插入错误";
    public static final String REGISTER_NONE_USER_ID = "";

    public static final String LOG_IN_SUCCESS = "200";
    public static final String LOG_IN_SUCCESS_INFO = "登录成功";
    public static final String LOG_IN_FAIL_WITH_WRONG_PASSWORD = "201";
    public static final String LOG_IN_FAIL_WITH_WRONG_PASSWORD_INFO = "密码错误";
    public static final String LOG_IN_FAIL_WITH_NONE_ACCOUNT = "202";
    public static final String LOG_IN_FAIL_WITH_NONE_ACCOUNT_INFO = "账号不存在";
    public static final String LOG_IN_NONE_USER_ID = "";

    public static final String SEND_REQUIREMENT_SUCCESS = "300";
    public static final String SEND_REQUIREMENT_SUCCESS_INFO = "请求发送成功";
    public static final String SEND_REQUIREMENT_FAIL = "301";
    public static final String SEND_REQUIREMENT_FAIL_INFO = "请求发送失败";


    public final static String REFRESH_INFORMATION_SUCCESS = "400";
    public final static String REFRESH_INFORMATION_SUCCESS_INFO = "刷新成功";
    public final static String REFRESH_INFORMATION_FAIL = "401";
    public final static String REFRESH_INFORMATION_FAIL_INFO = "刷新失败";

    public final static String CREATE_PROJECT_SUCCESS = "500";
    public final static String CREATE_PROJECT_SUCCESS_INFO = "创建成功";
    public final static String CREATE_PROJCET_FAIL = "501";
    public final static String CREATE_PROJCET_FAIL_INFO = "创建失败，您输入的用户不存在";

    public final static String REFRESH_PROJECT_SUCCESS = "600";
    public final static String REFRESH_PROJECT_SUCCESS_INFO = "刷新成功";
    public final static String REFRESH_PROJECT_FAIL = "601";
    public final static String REFRESH_PROJECT_FAIL_INFO = "刷新失败";

    public final static String CREATE_TASK_SUCCESS = "700";
    public final static String CREATE_TASK_SUCCESS_INFO = "插入任务成功";
    public final static String CREATE_TASK_FAIL = "701";
    public final static String CREATE_TASK_FAIL_INFO = "插入任务失败";

    public final static String REFRESH_TASK_SUCCESS = "800";
    public final static String REFRESH_TASK_SUCCESS_INFO = "刷新任务成功";
    public final static String REFRESH_TASK_FAIL = "801";
    public final static String REFRESH_TASK_FAIL_INFO = "刷新任务失败";

    public final static String REFRESH_TASK_OK_SUCCESS = "900";
    public final static String REFRESH_TASK_OK_SUCCESS_INFO = "完成ok";
    public final static String REFRESH_TASK_OK_FAIL = "901";
    public final static String REFRESH_TASK_OK_FAIL_INFO = "失败ok";

    public final static String CREATE_MESSAGE_SUCCESS = "1000";
    public final static String CREATE_MESSAGE_SUCCESS_INFO = "发送成功";
    public final static String CREATE_MESSAGE_FAIL = "1001";
    public final static String CREATE_MESSAGE_FAIL_INFO = "发送失败";

    public final static String REFRESH_MESSAGE_SUCCESS = "1100";
    public final static String REFRESH_MESSAGE_SUCCESS_INFO = "刷新成功";
    public final static String REFRESH_MESSAGE_FAIL = "1101";
    public final static String REFRESH_MESSAGE_FAIL_INFO = "刷新失败";

    public final static String USERNAME = "username";
    public final static String REQUIREMENT_MESSAGE = "requirementMessage";
    public final static String REQUIREMENT_OBJECT = "requirementObject_";
    public final static String REQUIREMENT_COUNT = "requirementCount";

    public final static String PROJECT_NAME = "projectName";
    public final static String NAME1 = "name1";
    public final static String NAME2 = "name2";
    public final static String NAME3 = "name3";
    public final static String NAME4 = "name4";
    public final static String NAME1_PROCCESS = "name1Proccess";
    public final static String NAME2_PROCCESS = "name2Proccess";
    public final static String NAME3_PROCCESS = "name3Proccess";
    public final static String NAME4_PROCCESS = "name4Proccess";
    public final static String COMMENT = "comment";
    public final static String PROJECT_OBJECT = "projectObject_";
    public final static String PROJECT_COUNT = "projectCount";

    public final static String FROM = "from";
    public final static String CONTENT = "content";
    public final static String TO = "to";
    public final static String MESSAGE_OBJECT = "messageObject_";
    public final static String MESSAGE_COUNT = "messageCount";


    public final static String PERSON_NAME = "personName";
    public final static String TASK_CONTENT = "taskContent";
    public final static String DEADLINE = "deadline";
    public final static String IS_FINISH = "isOK";
    public final static String TASK_COUNT = "taskCount";
    public final static String TASK_OBJECT = "taskObject_";

    public final static String INFORMATION_TITLE = "title";
    public final static String INFORMATION_MESSAGE = "informationMessage";
    public final static String INFORMATION_OBJECT = "informationObject_";
    public final static String INFORMATION_COUNT = "informationCount";




}
