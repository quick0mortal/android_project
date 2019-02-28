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
		String account = request.getParameter("account"); // 从 request 中获取名为 account 的参数的值
		String password = request.getParameter("password"); // 从 request 中获取名为 password 的参数的值
		System.out.println("account:" + account + "\npassword:" + password); // 打印出来看一看

		String resCode = "";
		String resMsg = "";
		String userId = "";
		
		/* 这里我们做一个最简单的注册逻辑，当然，你的实际业务可以相当复杂 */
		try {
			Connection connect = DBUtil.getConnect();
			Statement statement = (Statement) connect.createStatement(); // Statement可以理解为数据库操作实例，对数据库的所有操作都通过它来实现
			ResultSet result;
			
			String sqlQuery = "select * from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + account + "'";
			
			// 查询类操作返回一个ResultSet集合，没有查到结果时ResultSet的长度为0
			result = statement.executeQuery(sqlQuery); // 先查询同样的账号（比如手机号）是否存在
			if(result.next()){ // 已存在
				resCode = Constant.REGISTER_FAIL_EXIST_ACCOUNT;
				resMsg = Constant.REGISTER_FAIL_EXIST_ACCOUNT_INFO;
				userId = Constant.REGISTER_NONE_USER_ID;
			} else { // 不存在
				String sqlInsertPass = "insert into " + DBUtil.TABLE_PASSWORD + "(userAccount,userPassword) values('"+account+"','"+password+"')";
				int row1 = statement.executeUpdate(sqlInsertPass); // 插入帐号密码
				if(row1 == 1){
					String sqlQueryId = "select userId from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + account + "'";
					ResultSet result2 = statement.executeQuery(sqlQueryId); // 查询新增记录的userId
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