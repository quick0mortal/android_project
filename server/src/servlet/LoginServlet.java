package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.*;
import util.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		String account = request.getParameter("account"); // �� request �л�ȡ��Ϊ account �Ĳ�����ֵ
		String password = request.getParameter("password"); // �� request �л�ȡ��Ϊ password �Ĳ�����ֵ
		System.out.println("account:" + account + "\npassword:" + password); // ��ӡ������һ��

		String resCode = "";
		String resMsg = "";
		String userId = "";
		
		
		
		try {
			Connection connect = DBUtil.getConnect();
			Statement statement = (Statement) connect.createStatement(); // Statement�������Ϊ���ݿ����ʵ���������ݿ�����в�����ͨ������ʵ��
			ResultSet result;
			
			String sqlQuery = "select * from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + account + "'";
			
			// ��ѯ���������һ��ResultSet���ϣ�û�в鵽���ʱResultSet�ĳ���Ϊ0
			result = statement.executeQuery(sqlQuery); // �Ȳ�ѯͬ�����˺ţ������ֻ��ţ��Ƿ����
			if(result.next()){
				if ((result.getString("userPassword").equals(password)) && result.getString("userAccount").equals(account)) {
					resCode = Constant.LOG_IN_SUCCESS;
					resMsg = Constant.LOG_IN_SUCCESS_INFO;
					
					String sqlQueryId = "select userId from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + account + "'"; 
					ResultSet result2 = statement.executeQuery(sqlQueryId);
					
					if(result2.next()){
						userId = result2.getInt(Constant.USER_ID) + "";
					} else {
						userId = "-1";
					}
					
				} else { 
					resCode = Constant.LOG_IN_FAIL_WITH_WRONG_PASSWORD;
					resMsg = Constant.LOG_IN_FAIL_WITH_WRONG_PASSWORD_INFO;
					userId = Constant.LOG_IN_NONE_USER_ID;
				}
			} else {
				resCode = Constant.LOG_IN_FAIL_WITH_NONE_ACCOUNT;
				resMsg = Constant.LOG_IN_FAIL_WITH_NONE_ACCOUNT_INFO;
				userId = Constant.LOG_IN_NONE_USER_ID;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		
		HashMap<String, String> map = new HashMap<>();
		map.put(Constant.RES_CODE, resCode);
		map.put(Constant.RES_MESSAGE, resMsg);
		map.put(Constant.USER_ID, userId);
		
		
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
