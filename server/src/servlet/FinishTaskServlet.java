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
 * Servlet implementation class FinishTaskServlet
 */
@WebServlet("/FinishTaskServlet")
public class FinishTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinishTaskServlet() {
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
		String deadline = request.getParameter("deadline");
		String info = request.getParameter("info");
		System.out.println("projectName = " + projectName + "deadline=" + deadline + "info=" + info); 
		
		String resCode = "";
		String resMsg = "";
		
		try {
			Connection connect = DBUtil.getConnect();
			Statement statement = (Statement) connect.createStatement(); 
			
			boolean result;
			
			String sql = "UPDATE " + DBUtil.TABLE_TASK_INFO + " SET isOK = '1' WHERE deadline = " + deadline + " and projectName = " + projectName + " and taskContent = " + info;
			
			result = statement.execute(sql);
			
			if (result) {
				resCode = Constant.REFRESH_TASK_OK_SUCCESS;
				resMsg = Constant.REFRESH_TASK_OK_SUCCESS_INFO;
			} else {
				resCode = Constant.REFRESH_TASK_OK_FAIL;
				resMsg = Constant.REFRESH_TASK_OK_FAIL_INFO;
			}
			
		} catch (SQLException e) {
			resCode = Constant.REFRESH_TASK_OK_FAIL;
			resMsg = Constant.REFRESH_TASK_OK_FAIL_INFO;			
			e.printStackTrace();
		}
		
		
		HashMap<String, Object> map = new HashMap<>();
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
