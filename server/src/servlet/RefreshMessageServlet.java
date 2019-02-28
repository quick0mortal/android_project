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
import data.MessageInfo;
import data.RequirementInfo;
import util.CommonUtil;
import util.DBUtil;

/**
 * Servlet implementation class RefreshMessageServlet
 */
@WebServlet("/RefreshMessageServlet")
public class RefreshMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefreshMessageServlet() {
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
		
		String to = request.getParameter("to"); // 从 request 中获取名为 account 的参数的值
		System.out.println("to:" + to); // 打印出来看一看

		String resCode = "";
		String resMsg = "";
		
		ArrayList<MessageInfo> datas = new ArrayList<>(); 
		
		try {
			Connection connect = DBUtil.getConnect();
			Statement statement = (Statement) connect.createStatement(); // Statement可以理解为数据库操作实例，对数据库的所有操作都通过它来实现
			
			ResultSet result;
			
			String sqlQuery = "select * from " + DBUtil.TABLE_FROM_MESSAGE_TO + " where to = '" + to + "')";
			
			result = statement.executeQuery(sqlQuery); // 先查数据
			
			if (result != null) {
				resCode = Constant.REFRESH_MESSAGE_SUCCESS;
				resMsg = Constant.REFRESH_MESSAGE_SUCCESS_INFO;
			} 
			
			while(result.next()) {
				datas.add(new MessageInfo(result.getString(Constant.FROM), result.getString(Constant.CONTENT), result.getString(Constant.TO)));	
			}
		} catch (SQLException e) {
			System.out.println("hhhh");
			resCode = Constant.REFRESH_MESSAGE_FAIL;
			resMsg = Constant.REFRESH_MESSAGE_FAIL_INFO;
			e.printStackTrace();
		}
		
		
		HashMap<String, Object> map = new HashMap<>();
		map.put(Constant.RES_CODE, resCode);
		map.put(Constant.RES_MESSAGE, resMsg);
		map.put(Constant.MESSAGE_COUNT, datas.size());
		
		for(int i = 0; i < datas.size(); i++) {
			map.put(Constant.MESSAGE_OBJECT + i, datas.get(i).toMap());
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
