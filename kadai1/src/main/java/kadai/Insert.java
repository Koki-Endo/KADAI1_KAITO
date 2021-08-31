package kadai;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Product;
import dao.DAO;


@WebServlet("/kadai/insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Insert() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
        try {
            String name = request.getParameter("name");
            int price = Integer.parseInt(request.getParameter("price"));
            
            Product p = new Product();
            p.setName(name);
            p.setPrice(price);
            
            DAO dao = new DAO();
            int line = dao.insert(p);
 
            if (line > 0) {
            	response.sendRedirect("toppage");
            } else {
            	request.setAttribute("error", name + "　は既に登録済みです。");
    	        request.getRequestDispatcher("error.jsp").forward(request,response);
            }
            
        } catch (Exception e) {
        	request.setAttribute("error", e.getMessage());
	        request.getRequestDispatcher("error.jsp").forward(request,response);
        }

		
	}

}
