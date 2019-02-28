package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Constant;
import data.Task;
import util.CommonUtil;
import util.DBUtil;

/**
 * Servlet implementation class EditTaskServlet
 */
@WebServlet("/EditTaskServlet")
public class EditTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String projectName = request.getParameter("projectName");
		String personName = request.getParameter("personName");
		String taskContent = request.getParameter("taskContent");
		String deadline = request.getParameter("deadline");
		
		System.out.println("projectName=" + projectName + "personName=" + personName + "taskContent=" + taskContent + "deadline=" + deadline);

		String resCode = "";
		String resMsg = "";
		
		try {
			Connection connect = DBUtil.getConnect();
			Statement statement = (Statement) connect.createStatement(); // Statement可以理解为数据库操作实例，对数据库的所有操作都通过它来实现
			int result;
			
			String sqlInsertPass = "insert into " + DBUtil.TABLE_TASK_INFO + "(projectName,personName,taskContent,deadline) values('"+projectName+"','"+personName+"','"+taskContent+"','"+deadline+"')";
			
			result = statement.executeUpdate(sqlInsertPass); // 先查询同样的账号（比如手机号）是否存在
			if(result!=1){
				resCode = Constant.CREATE_TASK_FAIL;
				resMsg = Constant.CREATE_TASK_FAIL_INFO;
			} else if (result == 1){
				resCode = Constant.CREATE_TASK_SUCCESS;
				resMsg = Constant.CREATE_TASK_SUCCESS_INFO;
			}
		} catch (SQLException e) {
			resCode = Constant.CREATE_TASK_FAIL;
			resMsg = Constant.CREATE_TASK_FAIL_INFO;
			e.printStackTrace();
		}
		
		
		HashMap<String, String> map = new HashMap<>();
		map.put(Constant.RES_CODE, resCode);
		map.put(Constant.RES_MESSAGE, resMsg);
		
		CommonUtil.renderJson(response, map);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
