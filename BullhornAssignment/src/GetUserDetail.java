import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import postTools.DBUtil;
import model.Content;
import model.User;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/GetUserDetail")
public class GetUserDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("user_name");
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
//		
		String qString1 = "select u from User u where u.userName = ?1";
		TypedQuery<User> q1 = em.createQuery(qString1, User.class);
		q1.setParameter(1, userName);
		List<User> userDetail;
		try{
			userDetail=q1.getResultList();
			if(userDetail ==null ||userDetail.isEmpty()){
				userDetail=null;
			}
			
		
		String fullUser = "";
		for(int i=0;i<userDetail.size();i++)
        {
			fullUser="<li class=\"list-group-item\">User Name: "+userDetail.get(i).getUserName()+"</li>"
					+"<li class=\"list-group-item\">Motto: "+userDetail.get(i).getMotto()+"</li>"
					+"<li class=\"list-group-item\">Join Date: "+userDetail.get(i).getJoindate()+"</li><br><br>";
            
        }
	
//	
		String qString2 = "select c from Content c where c.userName = ?1 order by c.id desc";
		TypedQuery<Content> q2 = em.createQuery(qString2, Content.class);
		q2.setParameter(1, userName);
		List<Content> postList;
		
			postList=q2.getResultList();
			if(postList ==null ||postList.isEmpty()){
				postList=null;
			}
			
		
		String fullList = "";
		for(int i=0;i<postList.size();i++)
        {
            fullList+="<li class=\"list-group-item\">Post: "+postList.get(i).getContent()+"</li>";
            
        }
		
		// Set response content type
				response.setContentType("text/html");

				request.setAttribute("fullUser", fullUser);
				request.setAttribute("userName", userName);
				request.setAttribute("fullList", fullList);
				getServletContext().getRequestDispatcher("/profile.jsp")
						.forward(request, response);
				fullList = "";
				fullUser = "";
		}catch(Exception e){
		}finally{
			em.close();
		}
	}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
