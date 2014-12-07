package com.test;
import java.sql.*;
import java.util.ArrayList;
//����һ�������ࣨ����users������userbean
//ҵ���߼�������

public class UserBeanCl {
	
	//ҵ����
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
	
	//��ҳ��ʾ���,����Ҫ����ArrayList
	@SuppressWarnings("unchecked")
	public ArrayList getResultByPage(int pageNow,int pageSize){
		
		ArrayList al=new ArrayList();
		ConnDB cd=new ConnDB();
		ct=cd.getConn();
		

		// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		// //2���õ�����
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
				//��rs�е�ÿ����¼��װ��UserBean��
				UserBean ub=new UserBean();
				ub.setUserId(rs.getInt(1));
				ub.setUserName(rs.getString(2));
				ub.setPassWd(rs.getString(3));
				ub.setEmail(rs.getString(4));
				ub.setGrade(rs.getInt(5));
				//��ub���뵽�����У�����
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
	
	
	//��װ��֤�û�����
	public boolean checkUser(String u,String p){
		boolean b=false;
		
		try {
			//�õ�����
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
	//�ر���Դ,дһ������
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
