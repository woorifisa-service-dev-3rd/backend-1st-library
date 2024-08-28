package global;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Return.ReturnController;
import domain.card.CardController;
import domain.login.LoginUserController;
import domain.login.SaveUserController;
import domain.login.LoginFormController;
import domain.login.RegisterFormController;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/library-api/*")
public class FrontController extends HttpServlet {
	private Map<String, Controller> controllerMap = new HashMap<>();
	
	

	public FrontController() {
		controllerMap.put("/card", new CardController());
		controllerMap.put("/loginForm", new LoginFormController());
		controllerMap.put("/registerForm", new RegisterFormController());
		controllerMap.put("/login", new LoginUserController());
		controllerMap.put("/register", new SaveUserController());
		controllerMap.put("/returnbook", new ReturnController());
	}



	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String path = request.getPathInfo();
		Controller controller =  controllerMap.get(path);
		controller.process(request, response);
	}

}
