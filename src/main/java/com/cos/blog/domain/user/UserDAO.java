package com.cos.blog.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.cos.blog.config.DBConn;
import com.cos.blog.domain.CrudDAO;
import com.cos.blog.domain.board.Board;

public class UserDAO implements CrudDAO<User> {
	
	private static UserDAO instance = new UserDAO();
	private UserDAO() {}
	public static UserDAO getInstance() {
		return instance;
	}
	
	//로그인
	public User findByUsernameAndPassword(String username, String password) {
		User user = new User();
		String sql ="SELECT id,username,email,address,created FROM users where username=? and password=?";
		
		try {
			Connection conn = DBConn.디비연결();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery(); // row 리턴/ executeQuery 타입은 ResultSet
			while(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setCreated(rs.getTimestamp("created"));
				return user;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(User data) {
		//쿼리를 망가트려서 Script.back() 함수 테스트
		String sql = "INSERT INTO users(id,username,password,email,address,created) VALUES(users_seq.nextval,?,?,?,?,sysdate)";

//		LocalDateTime.now(); // 현재 시스템의 시간
//		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		// 현재 시간 구하기

		try {
			Connection conn = DBConn.디비연결();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			User user = (User) data;
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());

			// 변경된 행의 개수 리턴
			return pstmt.executeUpdate(); // commit o
			// pstmt.executeQuery(); commit x select 결과만 보여줌

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	} // save

	@Override
	public int update(User data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
