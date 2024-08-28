package domain.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import global.Controller;

public class SaveUserController implements Controller {

	private UserDAO userDao;

	public SaveUserController() {
		this.userDao = new UserDAO();
	}

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		long studentId = -1;
		String name = request.getParameter("name");
		String dateString = request.getParameter("date");
		String address = request.getParameter("address");
		Date date = null;
		
		try {
			
			date = new Date(dateFormat.parse(dateString).getTime());
			
			studentId = userDao.selectUser(name, date, address);
			
			if (studentId != -1) {
				out.print("<h4>이미 존재하는 회원입니다.</h4>");
			} else {
				userDao.saveUser(name, date, address);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
                studentId = userDao.selectUser(name, date, address);
                if (studentId != -1) {
                	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
            		dispatcher.forward(request, response);
                	
                } else {
                	out.print("<h4>오류가 발생했습니다.</h4>");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
				
	}
	
}
