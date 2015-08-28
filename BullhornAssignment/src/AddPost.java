import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Content;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/AddPost")
public class AddPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String user_name =(String) session.getAttribute("user_name");
		if(user_name==null){
			response.setContentType("text/html");
			String alert = "Please log in";
			request.setAttribute("alert", alert);
			getServletContext().getRequestDispatcher("/error.jsp")
			.include(request, response);
		}else{
		
		//String user_id = request.getParameter("user_id");
		String content = request.getParameter("content");
		Content post = new Content();
		
		post.setUserName(user_name);
		post.setContent(content);
		PostDB.insert(post);
		
		getServletContext().getRequestDispatcher("/GetPost")
		.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
