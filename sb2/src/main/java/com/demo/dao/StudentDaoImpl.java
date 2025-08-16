package com.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.model.Student;
import com.demo.model.StudentRowMapper;

@Repository
public class StudentDaoImpl implements StudentDao{
	
	@Autowired
	private JdbcTemplate j;

	@Override
	public int insertStudent(Student s) {
		String sql="insert into student(id,name,city,percentage)values(?,?,?,?)";
		return j.update(sql,new Object[] {s.getId(),s.getName(),s.getCity(),s.getPercentage()});
	}

	@Override
	public int deleteStudentById(int id) {
		String sql ="delete from student where id=?";
		return j.update(sql,id);
	}

	@Override
	public Student findStudentById(int id) {
	String sql="select id,name,city,percentage from student where id=?";
	List<Student> list = j.query(sql,new StudentRowMapper(),id);
	return (!list.isEmpty())?list.get(0):null; 
	}

	@Override
	public List<Student> findAllStudent() {
		String sql="select id,name,city,percentage from student";
		return j.query(sql,new StudentRowMapper());
	}

	@Override
	public int updateStudent(Student s) {
	String sql="update Student set name=?,city=?,percentage=? where id=?";
	return j.update(sql,new Object[] {s.getName(),s.getCity(),s.getPercentage(),s.getId()});
	
	}

}
