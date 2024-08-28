package domain.card;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import global.Controller;

public class CardRegisterController implements Controller {

	private final CardDAO cardDAO;
	private final LibraryDAO libraryDAO;

	public CardRegisterController() {
		this.cardDAO = AppConfig.cardDAO();
		this.libraryDAO = AppConfig.libraryDAO();
	}

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("library");
		Long libraryId = libraryDAO.findByName(name);
		
	    Long userIdObject = (Long) request.getSession().getAttribute("userId");
	    long userId = (userIdObject != null) ? userIdObject : -1;
	    System.out.println(userId);
	    if(userId == -1) {
			request.setAttribute("errorMessage", "유저가 존재하지 않습니다.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
			dispatcher.forward(request, response);
			return;
	    }
	    

		if (libraryId != -1L) {
			cardDAO.save(userId, libraryId);
		} else {
			// 도서관이 존재하지 않을 경우 처리
			request.setAttribute("errorMessage", "도서관이 존재하지 않습니다.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
			dispatcher.forward(request, response);
			return;
		}

	    
		List<String> libraryList =  cardDAO.findLibraryNameByStudentId(userId);
		request.setAttribute("libraryList", libraryList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/openLibrary.jsp");
		dispatcher.forward(request, response);
	}
}