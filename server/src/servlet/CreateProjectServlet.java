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
 * Servlet implementation class CreateProjectServlet
 */
@WebServlet("/CreateProjectServlet")
public class CreateProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProjectServlet() {
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
		String name1 = request.getParameter("name1");
		String name2 = request.getParameter("name2");
		String name3 = request.getParameter("name3");
		String name4 = request.getParameter("name4");
		
		System.out.println(projectName+name1+name2+name3+name4); // ��ӡ������һ��

		String resCode = "";
		String resMsg = "";
		
		ArrayList<Project> datas = new ArrayList<>(); 
		
		try {
			Connection connect = DBUtil.getConnect();
			Statement statement = (Statement) connect.createStatement(); // Statement�������Ϊ���ݿ����ʵ���������ݿ�����в�����ͨ������ʵ��
			
			String sqlQuery1 = "select * from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + name1 + "'";
			String sqlQuery2 = "select * from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + name2 + "'";
			String sqlQuery3 = "select * from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + name3 + "'";
			String sqlQuery4 = "select * from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + name4 + "'";
			
			// ��ѯ���������һ��ResultSet���ϣ�û�в鵽���ʱResultSet�ĳ���Ϊ0
			ResultSet result1, result2, result3, result4;
			result1 = statement.executeQuery(sqlQuery1); // �Ȳ�ѯͬ�����˺��Ƿ����
			result2 = statement.executeQuery(sqlQuery2); // �Ȳ�ѯͬ�����˺��Ƿ����
			result3 = statement.executeQuery(sqlQuery3); // �Ȳ�ѯͬ�����˺��Ƿ����
			result4 = statement.executeQuery(sqlQuery4); // �Ȳ�ѯͬ�����˺��Ƿ����
			if(result1.next() && result2.next() && result3.next() && result4.next()){
				String sqlInsertProject = "insert into " + DBUtil.TABLE_PROJECT_INFO + "(projectName,name1,name2,name3,name4) values('"+projectName+"','"+name1+"','"+name2+"','"+name3+"','"+name4+"')";
				// �������������һ��int���͵�ֵ������ò���Ӱ�쵽������
				int row1 = statement.executeUpdate(sqlInsertProject); // �����ʺ�����
				if(row1 == 1){
					String sqlQueryId = "select teamId from " + DBUtil.TABLE_PROJECT_INFO + " where projectName='" + projectName + "'";
					ResultSet result = statement.executeQuery(sqlQueryId); // ��ѯ������¼��userId
					if(result.next()){
						resCode = Constant.CREATE_PROJECT_SUCCESS;
						resMsg = Constant.CREATE_PROJECT_SUCCESS_INFO;
					}
				} else {
					resCode = Constant.CREATE_PROJCET_FAIL;
					resMsg = Constant.CREATE_PROJCET_FAIL_INFO;	
				}
			}
		} catch (SQLException e) {
			System.out.println("�׳�SQL�쳣");
			e.printStackTrace();
			resCode = Constant.CREATE_PROJCET_FAIL;
			resMsg = Constant.CREATE_PROJCET_FAIL_INFO;
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
