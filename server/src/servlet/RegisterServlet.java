package servlet;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.*;
import util.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
//		
		String account = request.getParameter("account"); // �� request �л�ȡ��Ϊ account �Ĳ�����ֵ
		String password = request.getParameter("password"); // �� request �л�ȡ��Ϊ password �Ĳ�����ֵ
		System.out.println("account:" + account + "\npassword:" + password); // ��ӡ������һ��

		String resCode = "";
		String resMsg = "";
		String userId = "";
		
		/* ����������һ����򵥵�ע���߼�����Ȼ�����ʵ��ҵ������൱���� */
		try {
			Connection connect = DBUtil.getConnect();
			Statement statement = (Statement) connect.createStatement(); // Statement�������Ϊ���ݿ����ʵ���������ݿ�����в�����ͨ������ʵ��
			ResultSet result;
			
			String sqlQuery = "select * from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + account + "'";
			
			// ��ѯ���������һ��ResultSet���ϣ�û�в鵽���ʱResultSet�ĳ���Ϊ0
			result = statement.executeQuery(sqlQuery); // �Ȳ�ѯͬ�����˺ţ������ֻ��ţ��Ƿ����
			if(result.next()){ // �Ѵ���
				resCode = Constant.REGISTER_FAIL_EXIST_ACCOUNT;
				resMsg = Constant.REGISTER_FAIL_EXIST_ACCOUNT_INFO;
				userId = Constant.REGISTER_NONE_USER_ID;
			} else { // ������
				String sqlInsertPass = "insert into " + DBUtil.TABLE_PASSWORD + "(userAccount,userPassword) values('"+account+"','"+password+"')";
				int row1 = statement.executeUpdate(sqlInsertPass); // �����ʺ�����
				if(row1 == 1){
					String sqlQueryId = "select userId from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + account + "'";
					ResultSet result2 = statement.executeQuery(sqlQueryId); // ��ѯ������¼��userId
					if(result2.next()){
						userId = result2.getInt(Constant.USER_ID) + "";
						resCode = Constant.REGISTER_SUCCESS;
						resMsg = Constant.REGISTER_SUCCESS_INFO;
					}
				} else {
					resCode = Constant.REGISTER_FAIL_INSERT_DATA;
					resMsg = Constant.REGISTER_FAIL_INSERT_DATA_INFO;
					userId = Constant.REGISTER_NONE_USER_ID;
				}
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
		doGet(request, response);
	}

}