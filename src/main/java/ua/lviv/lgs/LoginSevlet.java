package ua.lviv.lgs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@MultipartConfig
public class LoginSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = UserService.getUserService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = userService.getUser(email);
		
		if (email.equals("admin") && password.equals("admin")) {
			System.out.println(123);
			request.getRequestDispatcher("adminCabinet.jsp").forward(request, response);
		} else if (user.getPassword().equals(password)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("email", email);
			request.getRequestDispatcher("cabinet.jsp").forward(request, response);
		} else if (!user.getPassword().equals(password)) {
			request.getRequestDispatcher("/loginPage.jsp").forward(request,response);
		}


	}

}
