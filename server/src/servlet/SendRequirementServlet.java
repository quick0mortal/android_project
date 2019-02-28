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

import data.*;
import util.*;

/**
 * Servlet implementation class SendRequirementServlet
 */
@WebServlet("/SendRequirementServlet")
public class SendRequirementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendRequirementServlet() {
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
		
		String account = request.getParameter("account"); // 从 request 中获取名为 account 的参数的值
		String requirementMessage = request.getParameter("requirementMessage"); // 从 request 中获取名为 password 的参数的值
		System.out.println("account:" + account + "\requirementMessage:" + requirementMessage); // 打印出来看一看

		String resCode = "";
		String resMsg = "";
		
		
		try {
			Connection connect = DBUtil.getConnect();
			Statement statement = (Statement) connect.createStatement(); // Statement可以理解为数据库操作实例，对数据库的所有操作都通过它来实现
		
			
			String sqlInsertMessage = "insert into " + DBUtil.TABLE_REQUIREMENT_INFOS + "(username,requirementMessage) values('"+account+"','"+requirementMessage+"')";
			// 更新类操作返回一个int类型的值，代表该操作影响到的行数
			System.out.println(sqlInsertMessage);
			int row1 = statement.executeUpdate(sqlInsertMessage); // 插入消息记录
			if(row1 == 1){
				resCode = Constant.SEND_REQUIREMENT_SUCCESS;
				resMsg = Constant.SEND_REQUIREMENT_SUCCESS_INFO;
			} else {
				resCode = Constant.SEND_REQUIREMENT_FAIL;
				resMsg = Constant.SEND_REQUIREMENT_FAIL_INFO;
			}
			
		} catch (SQLException e) {
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
