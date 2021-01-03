import java.io.*;
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
	private File userFile;
	private String userName;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
//		if (request.getParameter("inputKeyword") == null) {
//			String requestUri = request.getRequestURI();
//			request.setAttribute("requestUri", requestUri);
//			request.getRequestDispatcher("Search.jsp").forward(request, response);
//			return;
//		} //��code
		userName = request.getParameter("name");
		if (userName == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("UserInfo.jsp").forward(request, response);
			return;
		}
		userFile = new File("beatGoogle_DataStructure\\" + userName + ".txt");
		if (!userInfo()) {
			if (!word(request.getParameter("school"))) {
				userInfo.add(request.getParameter("school"));
			}
			if (!word(request.getParameter("area"))) {
				userInfo.add(request.getParameter("area"));
			}
			for (String interest : request.getParameter("interest").split(" ")) {
				if (!word(interest)) {
					userInfo.add(interest);
				}
			}
			if (userInfo.size()==0) {
				request.getRequestDispatcher("UserInfo.jsp").forward(request, response);
				return;
			} else {
				createUserInfo(userInfo);
				request.getRequestDispatcher("SearchPage.jsp").forward(request, response);
				return;
			}
		}

////		GoogleQuery google = new GoogleQuery(request.getParameter("inputKeyword"));//��code
//		RepeatSearch repeatSearch = new RepeatSearch(userInfo, request.getParameter("inputKeyword"));
////		HashMap<String, String> query = google.query(); //��code
//		HashMap<String, String> query = repeatSearch.query;
//		String[][] s = new String[query.size()][2];
//		request.setAttribute("query", s);
//		int num = 0;
//		for (Entry<String, String> entry : query.entrySet()) {
//			String key = entry.getKey();
//			String value = entry.getValue();
//			s[num][0] = key;
//			s[num][1] = value;
//			num++;
//		}
//
//		request.getRequestDispatcher("googleitem.jsp").forward(request, response);
	}

	
	//whether the word include "not words"
	private boolean word(String word) {
		if(word == null) {
			return false;
		}
		return word.matches("[A-Za-z0-9\\u4e00-\\u9fa5]+");
	}

	
	//load userInfo from file
	private boolean userInfo() throws IOException {
		if (userFile.exists()) {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(userFile));
			BufferedReader br = new BufferedReader(isr);
			for (String infos : br.readLine().split(" ")) {
				userInfo.add(infos);
			}
			br.close();
			isr.close();
			return true;
		} else {
			return false;
		}
	}

	private void createUserInfo(ArrayList<String> info) throws IOException {
		userFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(userFile);
		PrintWriter pw = new PrintWriter(fos);
		for (String infos : userInfo) {
			pw.write(infos + " ");
		}
		pw.close();
		fos.close();
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}