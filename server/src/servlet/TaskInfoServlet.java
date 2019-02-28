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
import data.RequirementInfo;
import data.Task;
import util.CommonUtil;
import util.DBUtil;

/**
 * Servlet implementation class TaskInfoServlet
 */
@WebServlet("/TaskInfoServlet")
public class TaskInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskInfoServlet() {
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
		
		String name = request.getParameter("name"); // �� request �л�ȡ��Ϊ account �Ĳ�����ֵ
		System.out.println("name:" + name); // ��ӡ������һ��

		String resCode = "";
		String resMsg = "";
		
		ArrayList<Task> datas = new ArrayList<>(); 
		
		try {
			Connection connect = DBUtil.getConnect();
			Statement statement = (Statement) connect.createStatement(); // Statement�������Ϊ���ݿ����ʵ���������ݿ�����в�����ͨ������ʵ��
			
			ResultSet result;
			
			String sqlQuery = "select * from " + DBUtil.TABLE_TASK_INFO + " where personName = '" + name + "'";
			
			result = statement.executeQuery(sqlQuery); // �Ȳ�����
			
			if (result != null) {
				resCode = Constant.REFRESH_TASK_SUCCESS;
				resMsg = Constant.REFRESH_TASK_SUCCESS_INFO;
			} else {
				resCode = Constant.REFRESH_TASK_FAIL;
				resMsg = Constant.REFRESH_TASK_FAIL_INFO;
			}
			
			while(result.next()) {
				datas.add(new Task(result.getString(Constant.PROJECT_NAME), result.getString(name), result.getString(Constant.CONTENT), result.getString(Constant.DEADLINE), result.getString(Constant.IS_FINISH)));	
			}
			
		} catch (SQLException e) {
			resCode = Constant.REFRESH_INFORMATION_FAIL;
			resMsg = Constant.REFRESH_INFORMATION_FAIL_INFO;
			e.printStackTrace();
		}
		
		
		HashMap<String, Object> map = new HashMap<>();
		map.put(Constant.RES_CODE, resCode);
		map.put(Constant.RES_MESSAGE, resMsg);
		map.put(Constant.TASK_COUNT, datas.size());
		
		for(int i = 0; i < datas.size(); i++) {
			map.put(Constant.TASK_OBJECT + i, datas.get(i).toMap());
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
