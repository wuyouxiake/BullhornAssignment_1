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
import javax.servlet.http.HttpSession;

import postTools.DBUtil;
import model.Content;
import model.User;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
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
		String target = request.getParameter("target");
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select c from Content c where c.content LIKE ?1";
		TypedQuery<Content> q = em.createQuery(qString, Content.class);
		q.setParameter(1, "%" + target + "%");
		List<Content> contentList;
		try{
			contentList=q.getResultList();
			if(contentList ==null ||contentList.isEmpty()){
				contentList=null;
			}
		String fullList = "";
		for(int i=0;i<contentList.size();i++)
        {
            fullList+="<li class=\"list-group-item\">"+contentList.get(i).getUserName()+": "+contentList.get(i).getContent()+"</li>";
            
        }
		request.setAttribute("fullList", fullList);
		
		
		getServletContext().getRequestDispatcher("/listSearch.jsp")
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
	}

}
