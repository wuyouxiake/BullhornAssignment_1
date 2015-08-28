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
import model.User;

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
		System.out.println(user_name);
		if(user_name==null){
			response.setContentType("text/html");
			String alert = "Please log in";
			request.setAttribute("alert", alert);
			getServletContext().getRequestDispatcher("/error.jsp")
			.include(request, response);
		}else{
			
			EntityManager em = DBUtil.getEmFactory().createEntityManager();

			String qString1 = "select u from User u where u.userName = ?1";
			TypedQuery<User> q1 = em.createQuery(qString1, User.class);
			q1.setParameter(1, user_name);
			List<User> userDetail;
			try{
				userDetail=q1.getResultList();
				if(userDetail ==null ||userDetail.isEmpty()){
					userDetail=null;
				}
				
			String pl = "";
			for(int i=0;i<userDetail.size();i++)
	        {
				pl=userDetail.get(i).getPhotolink();
	        }
			
		String content = request.getParameter("content");
		Content post = new Content();
		
		post.setUserName(user_name);
		post.setContent(content);
		post.setPhotolink(pl);
		PostDB.insert(post);
		
		getServletContext().getRequestDispatcher("/GetPost")
		.forward(request, response);
		}catch(Exception e){
			}
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
