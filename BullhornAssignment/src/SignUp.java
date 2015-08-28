import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Content;
import model.User;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String motto = request.getParameter("motto");
		User tempUser=new User();
		
		tempUser.setUserName(user_name);
		tempUser.setPassword(password);
		tempUser.setMotto(motto);
		
		DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		Date joindate = new Date();
		tempUser.setJoindate(joindate);
		
		UserDB.insert(tempUser);
		
		String alert="User created!";
		request.setAttribute("alert", alert);
		getServletContext().getRequestDispatcher("/successful.jsp")
		.forward(request, response);
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
