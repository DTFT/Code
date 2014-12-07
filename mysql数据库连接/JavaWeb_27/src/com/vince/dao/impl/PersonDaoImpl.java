package com.vince.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.db.DBUtils;
import com.util.db.JdbcTemplete;
import com.vince.base.ResultSetHandler;
import com.vince.dao.PersonDao;
import com.vince.domain.Person;
/**
 * PersonDao�ľ���ʵ����
 * @author lamp
 *
 */
public class PersonDaoImpl implements PersonDao{
	private JdbcTemplete jdbcTemplete;
	public PersonDaoImpl(){
		jdbcTemplete = new JdbcTemplete();
	}
	

	/**
	 * ʵ����ӷ���
	 */
	@Override
	public void add(Person p) throws SQLException {
		String sql = "insert into person(name,age,description)values(?,?,?)";
		jdbcTemplete.update(sql, p.getName(),p.getAge(),p.getDescription());
	}

	/**
	 * ���·���
	 */
	@Override
	public void update(Person p) throws SQLException {
		String sql = "update person set name=?,age=?,description=? where id=?";
		jdbcTemplete.update(sql, p.getName(),p.getAge(),p.getDescription(),p.getId());
	}

	/**
	 * ɾ������
	 */
	@Override
	public void delete(int id) throws SQLException {
		String sql = "delete from person where id=?";
		jdbcTemplete.update(sql, id);
	}

	/**
	 * ����ID��ѯһ������
	 */
	@Override
	public Person findById(final int id) throws SQLException {
		String sql = "select name,age,description from person where id=?";
		return (Person)jdbcTemplete.query(sql, new ResultSetHandler() {
			@Override
			public Object doHandler(ResultSet rs)throws SQLException {
				Person p = null;
					if(rs.next()){
						p = new Person();
						p.setId(id);
						p.setName(rs.getString(1));
						p.setAge(rs.getInt(2));
						p.setDescription(rs.getString(3));
					}
				return p;
			}
		}, id);
	}

	/**
	 * ��ѯ��������
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> findAll() throws SQLException {
		String sql = "select id,name,age,description from person";
		return (List<Person>)jdbcTemplete.query(sql, new ResultSetHandler() {
			@Override
			public Object doHandler(ResultSet rs) throws SQLException {
				List<Person> persons = new ArrayList<Person>();
				Person p = null;
				while(rs.next()){
					p = new Person();
					p.setId(rs.getInt(1));
					p.setName(rs.getString(2));
					p.setAge(rs.getInt(3));
					p.setDescription(rs.getString(4));
					persons.add(p);
				}
				return persons;
			}
		});
	}

}















