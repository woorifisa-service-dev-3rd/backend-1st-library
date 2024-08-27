package domain.card;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import global.Controller;



public class CardController implements Controller {
	

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		
		// MouseDAO.java를 통해 DB에서 Mouse 데이터의 값을 조회해서 받아오기
		//List<Mouse> mice = mouseDAO.findAll();
		
		// 조회된 값을 request에 저장
		//request.setAttribute("mouseList", mice);
		
		// JSP 페이지로 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/card.jsp");
		dispatcher.forward(request, response);
		
	}
}
