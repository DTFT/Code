package com.test;

import java.sql.Connection;
import java.sql.DriverManager;

//从数据库中得到连接
public class ConnDB {

	
		private Connection ct=null;
		
		public Connection getConn(){
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			ct = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL","scott","tiger");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ct;
}

}
