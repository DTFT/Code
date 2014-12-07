package com.test;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/*
 * ���ݿ����������
 * 
 */
public class DBUtils {
	public static String URL ;
	public static String USERNAME ;
	public static String PASSWORD ;
	public static String DRIVER ;
	
	private static ResourceBundle rb=ResourceBundle.getBundle("com.test.db-config");
	
	private DBUtils(){}
	//ʹ�þ�̬�������������
	static{
			URL=rb.getString("jdbc.url");
			USERNAME=rb.getString("jdbc.username");
			PASSWORD=rb.getString("jdbc.password");
			DRIVER=rb.getString("jdbc.driver");
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//����һ����ȡ���ݿ����ӵķ���
	public static java.sql.Connection getConnection(){
		java.sql.Connection conn=null;
		try {
			conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��ȡ����ʧ��");
		}
		return conn;
	}
	/*
	 * �ر����ݿ�����
	 */
	public static void close(ResultSet rs,Statement stat,Connection conn){
			try {
				if(rs!=null)rs.close();
				if(stat!=null)stat.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
}
