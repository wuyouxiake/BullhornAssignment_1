import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/GetPost")
public class GetPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select c from Content c order by c.id desc";
		TypedQuery<Content> q = em.createQuery(qString, Content.class);
		List<Content> content;
		try{
			content=q.getResultList();
			if(content ==null ||content.isEmpty()){
				content=null;
			}
			
		
		String fullList = "";
		for(int i=0;i<content.size();i++)
        {
            fullList+="<li class=\"list-group-item\">"+content.get(i).getUserName()+": "+content.get(i).getContent()+"</li>";
            
        }
		
		// Set response content type
				response.setContentType("text/html");

				request.setAttribute("fullList", fullList);
				
				getServletContext().getRequestDispatcher("/list.jsp")
						.forward(request, response);
				fullList = "";
				
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
