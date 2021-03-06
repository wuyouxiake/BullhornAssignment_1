import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import postTools.DBUtil;
import model.Content;
import model.Message;
import model.User;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/SendMsg")
public class SendMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendMsg() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String user_name = (String) session.getAttribute("user_name");
		String message = request.getParameter("message");
		String receiver = request.getParameter("receiver");
		
		
		if (user_name == null) {
			response.setContentType("text/html");
			String alert = "Please log in";
			request.setAttribute("alert", alert);
			getServletContext().getRequestDispatcher("/error.jsp").include(
					request, response);
		}else{
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		// System.out.println(user_name);
		String qString = "select u from User u where u.userName = ?1";
		TypedQuery<User> q = em.createQuery(qString, User.class);
		q.setParameter(1, receiver);
		List<User> postList;
		postList = q.getResultList();
		
		if (postList == null || postList.isEmpty()) {

				response.setContentType("text/html");
				String alert = "Receiver name not valid!";
				request.setAttribute("alert", alert);
				getServletContext().getRequestDispatcher("/error.jsp").include(
						request, response);
				postList.clear();
			}else{
				Message msg = new Message();
				msg.setSender(user_name);
				msg.setReceiver(receiver);
				msg.setMessage(message);
				MessageDB.insert(msg);

				String alert = "Message Sent!";
				request.setAttribute("alert", alert);
				getServletContext().getRequestDispatcher("/successful.jsp")
						.forward(request, response);
				
			}

		}	
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
