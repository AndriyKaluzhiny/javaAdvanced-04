package ua.lviv.lgs.servlet;

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
  
@MultipartConfig  
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private UserService userService = UserService.getUserService();
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		request.getRequestDispatcher("cabinet.jsp").forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		userService.saveUser(new User(username, lastName, email, password));
		
		javax.servlet.http.HttpSession session = request.getSession(true);
		session.setAttribute("username", username);
		request.getRequestDispatcher("cabinet.jsp").forward(request, response);
	}

}
