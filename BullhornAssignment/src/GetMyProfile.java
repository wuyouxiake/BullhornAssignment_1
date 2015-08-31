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
@WebServlet("/GetMyProfile")
public class GetMyProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMyProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String userName =(String) session.getAttribute("user_name");
		if(userName==null){
			response.setContentType("text/html");
			String alert = "Please log in";
			request.setAttribute("alert", alert);
			getServletContext().getRequestDispatcher("/error.jsp")
			.include(request, response);
		}else{
			
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
//		
		String qString1 = "select u from User u where u.userName = ?1";
		TypedQuery<User> q1 = em.createQuery(qString1, User.class);
		q1.setParameter(1, userName);
		List<User> userDetail;
		String pl = "";
		String fullList = "";
		String fullUser = "";
		try{
			userDetail=q1.getResultList();
			if(userDetail ==null ||userDetail.isEmpty()){
				userDetail=null;
			}else{
		
		
		for(int i=0;i<userDetail.size();i++)
        {
			fullUser="<li class=\"list-group-item\">User Name: "+userDetail.get(i).getUserName()+"</li>"
					+"<li class=\"list-group-item\">Photo: <img src=\""+userDetail.get(i).getPhotolink()+"\" style=\"width:80px;height:80px\"></li>"
					+"<li class=\"list-group-item\">Motto: "+userDetail.get(i).getMotto()+"</li>"
					+"<li class=\"list-group-item\">Join Date: "+userDetail.get(i).getJoindate()+"</li><br><br>";
			pl=userDetail.get(i).getPhotolink();
        }}
		
//	
		String qString2 = "select c from Content c where c.userName = ?1 order by c.id desc";
		TypedQuery<Content> q2 = em.createQuery(qString2, Content.class);
		
		q2.setParameter(1, userName);
		List<Content> postList;
			postList=q2.getResultList();
			if(postList ==null ||postList.isEmpty()){
				postList=null;
			}else{
			
			
		
		
		for(int i=0;i<postList.size();i++)
        {
            fullList+="<li class=\"list-group-item\"><img src=\""+pl+"\" style=\"width:40px;height:40px\">"
            		+": "+postList.get(i).getContent()+"</li>";
        }}
//get sent list		
		String sentText = "";
		String qString3 = "select m from Message m where m.sender = ?1 order by m.id desc";
		TypedQuery<Message> q3 = em.createQuery(qString3, Message.class);
		q3.setParameter(1, userName);
		List<Message> sentList;
		sentList=q3.getResultList();
			if(sentList ==null ||sentList.isEmpty()){
				sentList=null;
			}else{
			
		
		for(int i=0;i<sentList.size();i++)
        {
			sentText+="<tr><td>"
            		+sentList.get(i).getReceiver()
            		+"</td><td>"+sentList.get(i).getMessage()
            		+"</td></tr>";
        }
			}
//get received list		
			String receivedText = "";
				String qString4 = "select m from Message m where m.receiver = ?1 order by m.id desc";
				TypedQuery<Message> q4 = em.createQuery(qString4, Message.class);
				q4.setParameter(1, userName);
				List<Message> receivedList;
				receivedList=q4.getResultList();
				
					if(receivedList ==null ||receivedList.isEmpty()){
						receivedList=null;
						}else{
					
				System.out.println("131313");
				
				for(int i=0;i<receivedList.size();i++)
		        {
					receivedText+="<tr><td>"
		          		+receivedList.get(i).getSender()
		            		+"</td><td>"+receivedList.get(i).getMessage()
		         		+"</td></tr>";
		            
		        }	
				}	
		
				System.out.println("151515");
		
		//Set response content type
				response.setContentType("text/html");

				request.setAttribute("fullUser", fullUser);
				request.setAttribute("userName", userName);
				request.setAttribute("fullList", fullList);
				request.setAttribute("sentText", sentText);
				request.setAttribute("receivedText", receivedText);
				getServletContext().getRequestDispatcher("/myprofile.jsp")
						.forward(request, response);
				fullList = "";
				sentText = "";
				fullUser = "";
				receivedText = "";
				postList.clear();
				userDetail.clear();
				sentList.clear();
				receivedList.clear();
				System.out.println("151515");
		}catch(Exception e){
		}finally{
			em.close();
		}
	}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
