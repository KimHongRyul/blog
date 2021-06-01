package com.cos.blog.service.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.domain.board.BoardDAO;
import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserDAO;
import com.cos.blog.service.Action;
import com.cos.blog.util.Script;
import com.cos.blog.util.ValidationHandler;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. http Body 데이터 유효성 검사
		ValidationHandler vh = new ValidationHandler();
		List<String> keys = Arrays.asList("username","password","email","address");
		if (vh.validation(keys,request, response) != 1) {
			return;
		}
		// 2. http Body 데이터 변수로 받아야 됨.
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		User user = new User(null,username,password,email,address,null);
		// 3. DAO 연결해서 save()하기
		// 4. result 받아야 함.
		UserDAO userDAO = UserDAO.getInstance();
		int result = userDAO.save(user);
		

		// 5. 1일때, 1이아닐때를 분기하여 1일때는 로그인 페이지 보내기,
		// 1이 아니면 joinForm 페이지로 보내기
		if (result ==1) {
			//System.out.println("가입 성공");
			// content-Type : text/html; charset=utf-8
			response.sendRedirect("views/user/loginForm.jsp");
		} else {
			//System.out.println("가입 실패");
			//alert창
			Script.back("회원가입실패", response);
		}
	}

}
