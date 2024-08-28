package domain.Return;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import global.Controller;

public class ReturnController implements Controller {

	private ReturnDataAccessor returnDao;
	
	public ReturnController() {
		this.returnDao = new ReturnDataAccessor();
	}

	@Override
	public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		ArrayList<ReturnBookDto> returnBookList = returnDao.showLoanBook(1,1);
		 System.out.println("ReturnBookList Size: " + returnBookList.size()); // 확인용 로그

		req.setAttribute("returnBookList", returnBookList);
		
		// JSP 페이지로 포워드
		String url = "/WEB-INF/views/returnBook.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, res);
	
		res.setStatus(200);
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
	}
	
	
	
	
}
