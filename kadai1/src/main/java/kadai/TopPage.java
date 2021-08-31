package kadai;

import dao.DAO;
import bean.Product;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/kadai/toppage")
public class TopPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TopPage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--- 入出力用文字エンコード
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		try {
			//--- DAOオブジェクトのインスタンス化
			DAO dao = new DAO();
			//--- パラメータ（キーワード）の取得
			String keyword = request.getParameter("keyword");
			//--- データ格納用配列
			List<Product> ary = new ArrayList<Product>();
			//--- DAOのsearchメソッドを使ってデータを取得
			ary = dao.search(keyword);
			//--- 表示用のJSPへ転送
			//--- 転送するデータに data という名前を付けて設定
			request.setAttribute("data", ary);
			//--- top.jsp へ転送
	        request.getRequestDispatcher("top.jsp").forward(request,response);
		} catch (Exception e) {
			//--- 改行コードや空白文字を有効にするタグを発行
			response.getWriter().println("<pre>");
			response.getWriter().println(e.getMessage());
			e.printStackTrace();
		}
	}
}
