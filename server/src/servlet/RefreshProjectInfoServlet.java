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
import data.Project;
import data.RequirementInfo;
import util.CommonUtil;
import util.DBUtil;

/**
 * Servlet implementation class RefreshProjectInfo
 */
@WebServlet("/RefreshProjectInfo")
public class RefreshProjectInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefreshProjectInfoServlet() {
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
		
		String name = request.getParameter("name"); // 从 request 中获取名为 account 的参数的值
		System.out.println("name:" + name); // 打印出来看一看

		String resCode = "";
		String resMsg = "";
		
		ArrayList<Project> datas = new ArrayList<>(); 
		
		try {
			Connection connect = DBUtil.getConnect();
			Statement statement = (Statement) connect.createStatement(); // Statement可以理解为数据库操作实例，对数据库的所有操作都通过它来实现
			
			String sqlQueryTmp;
			double finish = 0;
			double teamFinish = 0;
			double total = 0;
			double teamTotal = 0;
			int process = 0;
			int teamProcess = 0;
			
			
			ResultSet tmp;
			
			ResultSet result;
			
			//String sqlQuery = "select * from " + DBUtil.TABLE_PROJECT_INFO + " where name1 = '" + name + "' or name2 = '" + name + "' or name3 = '" + name + "' or name4 = '" + name + "'";
			String sqlQuery = "select * from " + DBUtil.TABLE_PROJECT_INFO + " where 1";
			result = statement.executeQuery(sqlQuery); // 先查数据
			
			if (result == null) {
				resCode = Constant.REFRESH_PROJECT_FAIL;
				resMsg = Constant.REFRESH_PROJECT_FAIL_INFO;
			} else {
				resCode = Constant.REFRESH_PROJECT_SUCCESS;
				resMsg = Constant.REFRESH_PROJECT_SUCCESS_INFO;
			}
			
			while(result.next()) {
				Project project = new Project(result.getString(Constant.PROJECT_NAME), result.getString(Constant.NAME1), result.getString(Constant.NAME2), result.getString(Constant.NAME3),result.getString(Constant.NAME4), result.getString(Constant.COMMENT));	
				
				sqlQueryTmp = "select * form" + DBUtil.TABLE_TASK_INFO + "where isOK = '1' and name1 = '" + result.getString(Constant.NAME1);
				tmp = statement.executeQuery(sqlQueryTmp);
				while(tmp.next()) {
					finish++;
				}
				sqlQueryTmp = "select * form" + DBUtil.TABLE_TASK_INFO + "where and name1 = '" + result.getString(Constant.NAME1);
				tmp = statement.executeQuery(sqlQueryTmp);
				while(tmp.next()) {
					total++;
				}
				teamFinish += finish;
				teamTotal += total;
				process = (int)(finish / total * 100);
				project.setName1Proccess(String.valueOf(process));


				finish = 0;
				total = 0;
				process = 0;
				sqlQueryTmp = "select * form" + DBUtil.TABLE_TASK_INFO + "where isOK = '1' and name1 = '" + result.getString(Constant.NAME2);
				tmp = statement.executeQuery(sqlQueryTmp);
				while(tmp.next()) {
					finish++;
				}
				sqlQueryTmp = "select * form" + DBUtil.TABLE_TASK_INFO + "where and name1 = '" + result.getString(Constant.NAME2);
				tmp = statement.executeQuery(sqlQueryTmp);
				while(tmp.next()) {
					total++;
				}
				teamFinish += finish;
				teamTotal += total;
				process = (int)(finish / total * 100);
				project.setName2Proccess(String.valueOf(process));
				
				finish = 0;
				total = 0;
				process = 0;
				sqlQueryTmp = "select * form" + DBUtil.TABLE_TASK_INFO + "where isOK = '1' and name1 = '" + result.getString(Constant.NAME3);
				tmp = statement.executeQuery(sqlQueryTmp);
				while(tmp.next()) {
					finish++;
				}
				sqlQueryTmp = "select * form" + DBUtil.TABLE_TASK_INFO + "where and name1 = '" + result.getString(Constant.NAME3);
				tmp = statement.executeQuery(sqlQueryTmp);
				while(tmp.next()) {
					total++;
				}
				teamFinish += finish;
				teamTotal += total;
				process = (int)(finish / total * 100);
				project.setName3Proccess(String.valueOf(process));
				
				teamProcess = (int)(teamFinish / teamTotal * 100);
				project.setName4Proccess(String.valueOf(teamProcess));
				
				datas.add(project);
			}
		} catch (SQLException e) {
			resCode = Constant.REFRESH_PROJECT_FAIL;
			resMsg = Constant.REFRESH_PROJECT_FAIL_INFO;
			e.printStackTrace();
		}
		
		
		HashMap<String, Object> map = new HashMap<>();
		map.put(Constant.RES_CODE, resCode);
		map.put(Constant.RES_MESSAGE, resMsg);
		map.put(Constant.PROJECT_COUNT, datas.size());
		
		for(int i = 0; i < datas.size(); i++) {
			map.put(Constant.PROJECT_OBJECT + i, datas.get(i).toMap());
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
