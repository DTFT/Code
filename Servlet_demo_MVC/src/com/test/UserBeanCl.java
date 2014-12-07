package com.test;
import java.sql.*;
import java.util.ArrayList;
//这是一个处理类（处理users表）操作userbean
//业务逻辑在这里

public class UserBeanCl {
	
	//业务处理
	private Connection ct = null;
	private Statement sm = null;
	private ResultSet rs = null;
	int PageCount=0;
	
	public int getPageCount(){
		try {
			ConnDB cd=new ConnDB();
			ct=cd.getConn();
			CallableStatement cs = ct.prepareCall("{call fenye(?,?,?,?,?,?)}");

			cs.setString(1, "users");
			cs.setInt(2, 3);
			cs.setInt(3, 1);

			cs.registerOutParameter(4, oracle.jdbc.OracleTypes.INTEGER);
			cs.registerOutParameter(5, oracle.jdbc.OracleTypes.INTEGER);
			cs.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			
			PageCount = cs.getInt(5);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return PageCount;
	}
	
	//分页显示结果,这里要返回ArrayList
	@SuppressWarnings("unchecked")
	public ArrayList getResultByPage(int pageNow,int pageSize){
		
		ArrayList al=new ArrayList();
		ConnDB cd=new ConnDB();
		ct=cd.getConn();
		

		// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		// //2、得到连接
		// ct=DriverManager.getConnection("jdbc:odbc:test","scott","tiger");

		try {
			CallableStatement cs = ct.prepareCall("{call fenye(?,?,?,?,?,?)}");

			cs.setString(1, "users");
			cs.setInt(2, 3);
			cs.setInt(3, pageNow);

			cs.registerOutParameter(4, oracle.jdbc.OracleTypes.INTEGER);
			cs.registerOutParameter(5, oracle.jdbc.OracleTypes.INTEGER);
			cs.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			
			PageCount = cs.getInt(5);
			
			ResultSet rs = (ResultSet) cs.getObject(6);
			
			while(rs.next()){
				//将rs中的每条记录封装到UserBean中
				UserBean ub=new UserBean();
				ub.setUserId(rs.getInt(1));
				ub.setUserName(rs.getString(2));
				ub.setPassWd(rs.getString(3));
				ub.setEmail(rs.getString(4));
				ub.setGrade(rs.getInt(5));
				//讲ub放入到集合中！！！
				al.add(ub);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
		
		return al;
	}
	
	
	//封装验证用户方法
	public boolean checkUser(String u,String p){
		boolean b=false;
		
		try {
			//得到连接
			ConnDB cd=new ConnDB();
			ct=cd.getConn();
			sm = ct.createStatement();
			rs = sm.executeQuery("select passwd from users where username='"
					+ u + "'");
			
			if(rs.next()){	
				String dbPasswd=rs.getString(1);
				if(dbPasswd.endsWith(p)){
					return	b=true;
				}		
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			close();
		}

		return b;		
	}
	//关闭资源,写一个方法
	public void close(){
		try {
			if(rs!=null){
				rs.close();
			}
			if(sm!=null){
				sm.close();
			}
			if(ct!=null){
				ct.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
