package domain.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginServlet")
public class LoginUserControllerServlet extends HttpServlet  {

	private UserDAO userDao;

	public LoginUserControllerServlet() {
		this.userDao = new UserDAO();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
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
				if (session.isNew() || session.getAttribute("logindetail") == null) {
					out.println("로그인 성공");
					session.setAttribute("logindetail", studentId);
				} else {
					out.print("<h4>이미 로그인 중입니다.</h4>");
				}
			} else {
				out.print("<h4>회원 정보가 존재하지 않습니다.</h4>");
//				리다이렉션...? 하심?
//				request.getRequestDispatcher("login.html").include(request, response); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		out.close();
				
	}
	
}
