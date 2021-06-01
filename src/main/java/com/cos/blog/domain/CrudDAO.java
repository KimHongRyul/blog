package com.cos.blog.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.cos.blog.config.DBConn;
import com.cos.blog.domain.user.User;

public interface CrudDAO<T> {
	// get
	public T findById(int id);

	// get
	public List<T> findAll();

	// post
	public int save(T data);

	// post
	public int update(T data);

	// post
	public int deleteById(int id);
}
