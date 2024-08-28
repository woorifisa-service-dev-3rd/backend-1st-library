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
	
	private final CardDAO cardDAO;
	
	public CardController() {
		this.cardDAO = AppConfig.cardDAO();
	}
	

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Long userIdObject = (Long) request.getSession().getAttribute("userId");

	    System.out.println(userIdObject);
	    long userId = (userIdObject != null) ? userIdObject : -1;
	    
	    if(userId == -1) {
			request.setAttribute("errorMessage", "유저가 존재하지 않습니다.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
			dispatcher.forward(request, response);
			return;
	    }

	    List<String> libraryList = cardDAO.findLibrariesWithoutStudent(userId);
	    
	    request.setAttribute("libraryList", libraryList);

	    // JSP 페이지로 포워딩
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/card.jsp");
	    dispatcher.forward(request, response);
	}


}