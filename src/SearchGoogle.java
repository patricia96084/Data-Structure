import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class SearchGoogle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> userInfo;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchGoogle(ArrayList<String> userInfo) {
		super();
		this.userInfo = userInfo;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if (request.getParameter("inputKeyword") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		//搜尋的文字透過google回傳搜尋結果
//		GoogleQuery google = new GoogleQuery(request.getParameter("inputKeyword"));//原code
		RepeatSearch repeatSearch = new RepeatSearch(userInfo,request.getParameter("inputKeyword"));
//		HashMap<String, String> query = google.query(); //原code
		HashMap<String, String> query = repeatSearch.query;
		String[][] s = new String[query.size()][2];
		request.setAttribute("query", s);
		int num = 0;
		for (Entry<String, String> entry : query.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			s[num][0] = key;
			s[num][1] = value;
			num++;
		}
		
		request.getRequestDispatcher("googleitem.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}