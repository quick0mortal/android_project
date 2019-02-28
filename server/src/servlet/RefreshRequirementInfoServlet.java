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
 * Servlet implementation class RefreshRequirementInfoServlet
 */
@WebServlet("/RefreshRequirementInfoServlet")
public class RefreshRequirementInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefreshRequirementInfoServlet() {
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
		
		String number = request.getParameter("number"); // 从 request 中获取名为 account 的参数的值
		System.out.println("number:" + number); // 打印出来看一看

		String resCode = "";
		String resMsg = "";
		
		ArrayList<RequirementInfo> datas = new ArrayList<>(); 
		
		try {
			Connection connect = DBUtil.getConnect();
			Statement statement = (Statement) connect.createStatement(); // Statement可以理解为数据库操作实例，对数据库的所有操作都通过它来实现
			
			ResultSet result;
			
			String sqlQuery = "select * from " + DBUtil.TABLE_REQUIREMENT_INFOS + " where 1";
			
			result = statement.executeQuery(sqlQuery); // 先查数据
			
			if (result != null) {
				resCode = Constant.REFRESH_INFORMATION_SUCCESS;
				resMsg = Constant.REFRESH_INFORMATION_SUCCESS_INFO;
			} 
			
			while(result.next()) {
				datas.add(new RequirementInfo(result.getString(Constant.USERNAME), result.getString(Constant.REQUIREMENT_MESSAGE)));	
			}
		} catch (SQLException e) {
			resCode = Constant.REFRESH_INFORMATION_FAIL;
			resMsg = Constant.REFRESH_INFORMATION_FAIL_INFO;
			e.printStackTrace();
		}
		
		
		HashMap<String, Object> map = new HashMap<>();
		map.put(Constant.RES_CODE, resCode);
		map.put(Constant.RES_MESSAGE, resMsg);
		map.put(Constant.REQUIREMENT_COUNT, datas.size());
		
		for(int i = 0; i < datas.size(); i++) {
			map.put(Constant.REQUIREMENT_OBJECT + i, datas.get(i).toMap());
		}
		
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
