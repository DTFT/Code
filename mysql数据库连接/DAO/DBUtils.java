package com.test;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/*
 * 数据库操作工具类
 * 
 */
public class DBUtils {
	public static String URL ;
	public static String USERNAME ;
	public static String PASSWORD ;
	public static String DRIVER ;
	
	private static ResourceBundle rb=ResourceBundle.getBundle("com.test.db-config");
	
	private DBUtils(){}
	//使用静态块加载驱动程序
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
	//定义一个获取数据可连接的方法
	public static java.sql.Connection getConnection(){
		java.sql.Connection conn=null;
		try {
			conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取连接失败");
		}
		return conn;
	}
	/*
	 * 关闭数据库连接
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
