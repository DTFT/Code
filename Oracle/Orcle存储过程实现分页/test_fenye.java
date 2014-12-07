package com.ora;

import java.sql.*;

public class test_fenye {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection cn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL","scott","tiger");
			
			CallableStatement cs=cn.prepareCall("{call fenye(?,?,?,?,?,?)}");
			
			cs.setString(1, "emp");
			cs.setInt(2, 5);
			cs.setInt(3, 1);
			
			cs.registerOutParameter(4, oracle.jdbc.OracleTypes.INTEGER);
			cs.registerOutParameter(5, oracle.jdbc.OracleTypes.INTEGER);
			cs.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
			
			cs.execute();
			
			int rows=cs.getInt(4);
			int pageCount=cs.getInt(5);
			ResultSet rs=(ResultSet) cs.getObject(6);
			
			System.out.println("共有"+rows+"条记录");
			System.out.println("共有"+pageCount+"页");
			while(rs.next()){
				System.out.println("员工编号是:"+rs.getInt(1)+",员工姓名是:"+rs.getString(2));
				
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
