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
import data.Project;
import util.CommonUtil;
import util.DBUtil;

/**
 * Servlet implementation class CreateMessageServlet
 */
@WebServlet("/CreateMessageServlet")
public class CreateMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMessageServlet() {
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
		
		String from = request.getParameter("from");
		String content = request.getParameter("content");
		String to = request.getParameter("to");
		System.out.println(from+content+to); // 打印出来看一看

		String resCode = "";
		String resMsg = "";
		
		ArrayList<MessageInfo> datas = new ArrayList<>(); 
		
		try {
			Connection connect = DBUtil.getConnect();
			Statement statement = (Statement) connect.createStatement(); 
			String sqlInsertProject = "insert into " + DBUtil.TABLE_FROM_MESSAGE_TO + "(from,content,to) values('"+from+"','"+content+"','"+to+"')";
			// 更新类操作返回一个int类型的值，代表该操作影响到的行数
			int row1 = statement.executeUpdate(sqlInsertProject); 
			if(row1 == 1){
				String sqlQueryId = "select msgId from " + DBUtil.TABLE_FROM_MESSAGE_TO + " where from='" + from + "'";
				ResultSet result = statement.executeQuery(sqlQueryId); // 查询新增记录的userId
				if(result.next()){
					resCode = Constant.CREATE_MESSAGE_SUCCESS;
					resMsg = Constant.CREATE_MESSAGE_SUCCESS_INFO;
				}
			} else {
				resCode = Constant.CREATE_MESSAGE_FAIL;
				resMsg = Constant.CREATE_MESSAGE_FAIL_INFO;	
			}
		} catch (SQLException e) {
			System.out.println("抛出SQL异常");
			e.printStackTrace();
			resCode = Constant.CREATE_MESSAGE_FAIL;
			resMsg = Constant.CREATE_MESSAGE_FAIL_INFO;
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
