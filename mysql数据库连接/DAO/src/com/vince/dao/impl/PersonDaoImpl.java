package com.vince.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.db.DBUtils;
import com.vince.dao.PersonDAO;
import com.vince.domain.Person;

public class PersonDaoImpl implements PersonDAO {
	/*
	 * 这是PersonDAO的具体实现类
	 * 
	 * @see com.vince.dao.PersonDAO#add(com.vince.domain.Person)
	 */
	@Override
	public void add(Person p) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into person1(name,age,description) values(?,?,?)";
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setInt(2, p.getAge());
			ps.setString(3, p.getDes());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("添加信息失败");
		}finally{
			DBUtils.close(null,ps,conn);
		}

	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from person1 where id=?";
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
		
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("删除信息失败");
		}finally{
			DBUtils.close(null,ps,conn);
		}
	}

	@Override
	public List<Person> findAll() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Person p=null;
		List<Person> persons=new ArrayList<Person>();
		String sql="select id,name,age,description from person1";
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()){
				p=new Person();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setAge(rs.getInt(3));
				p.setDes(rs.getNString(4));
				persons.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SQLException("查询所有数据失败");
		}finally{
			DBUtils.close(rs, ps, conn);
		}
		return persons;
	
	}

	@Override
	public Person findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Person p=null;
		String sql="select name,age,description from  person1 where id=? ";
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				p=new Person();
				p.setId(id);
				p.setName(rs.getString(1));
				p.setAge(rs.getInt(2));
				p.setDes(rs.getNString(3));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SQLException("根据ID查询失败");
		}finally{
			DBUtils.close(rs, ps, conn);
		}
		return p;
	}

	@Override
	public void update(Person p) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update person1 set name=?,age=?,description=? where id=?";
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setInt(2, p.getAge());
			ps.setString(3, p.getDes());
			ps.setInt(4, p.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("更新信息失败");
		}finally{
			DBUtils.close(null,ps,conn);
		}
	}

}
