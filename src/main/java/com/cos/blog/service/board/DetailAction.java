package com.cos.blog.service.board;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.BoardDAO;
import com.cos.blog.domain.user.User;
import com.cos.blog.service.Action;
import com.cos.blog.util.Script;
import com.cos.blog.util.ValidationHandler;
import com.cos.blog.web.dto.BoardDetailDTO;

public class DetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		BoardDetailDTO boardDetailDTO = boardDAO.mDetail(id);
		
		System.out.println(boardDetailDTO);
		
		if (boardDetailDTO != null) {
		request.setAttribute("dto", boardDetailDTO);
		RequestDispatcher dis = request.getRequestDispatcher("views/board/detail.jsp");
		dis.forward(request, response);
		} else {
			Script.back("잘못된 접근", response);
		}
		//response.sendRedirect("views/board/list.jsp");
				
	}

}
