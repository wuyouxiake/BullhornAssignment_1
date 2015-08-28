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
@WebServlet("/SearchUserPost")
public class SearchUserPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUserPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String target =request.getParameter("target");
		String userName =request.getParameter("userName");
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		
		String qString = "select c from Content c where c.userName = ?1 and c.content LIKE ?2 order by c.id desc";
		TypedQuery<Content> q = em.createQuery(qString, Content.class);
		q.setParameter(1, userName);
		q.setParameter(2, "%" + target + "%");
		
		List<Content> contentList;
		try{
			contentList=q.getResultList();
			if(contentList ==null ||contentList.isEmpty()){
				contentList=null;
			}
		String fullList = "";
		for(int i=0;i<contentList.size();i++)
        {
            fullList+="<li class=\"list-group-item\">"+contentList.get(i).getContent()+"</li>";
            
        }
		request.setAttribute("fullList", fullList);
		
		
		getServletContext().getRequestDispatcher("/listSearch.jsp")
		.forward(request, response);
		}catch(Exception e){
			}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
