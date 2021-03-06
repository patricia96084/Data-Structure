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
//	private File userFile;
	private String userName, keyword;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchGoogle() {
		super();
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
		response.setContentType("text/html;charset=UTF-8");
		/*
		 * ��code response.setContentType("text/html"); if
		 * (request.getParameter("inputKeyword") == null) { String requestUri =
		 * request.getRequestURI(); request.setAttribute("requestUri", requestUri);
		 * request.getRequestDispatcher("Search.jsp").forward(request, response);
		 * return; }
		 */
		if (request.getParameter("name") == null || request.getParameter("keyword") == null) {
			goSearchPage(request, response);
			return;
		}
		userName = request.getParameter("name");
		if (!userName.matches("[a-zA-Z0-9_]+")) {
			goSearchPage(request, response);
			return;
		}
		// System.out.println(userName);
		keyword = request.getParameter("keyword");
//		System.out.println(keyword);
		if (!word(keyword)) {
			goSearchPage(request, response);
			return;
		}
		postUserInfo(request);
		if (userInfo.size() > 0 && word(keyword)) {
			search(request);
			request.getRequestDispatcher("googleitem.jsp").forward(request, response);
			return;
		}
		goSearchPage(request, response);
		return;
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

	private void goSearchPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		request.setAttribute("requestUri", requestUri);
		request.getRequestDispatcher("SearchPage.jsp").forward(request, response);
	}

	private void postUserInfo(HttpServletRequest request) {
		userInfo = new ArrayList<String>();
//		userFile = (new UserInfoFile(,userName)).userFile;
//		if (!userInfo()) {
//		System.out.println(userName);
//		System.out.println(request.getParameter("school"));
//		System.out.println(request.getParameter("userArea"));
//		System.out.println(request.getParameter("userInterest"));
		if (word(request.getParameter("school"))) {
			userInfo.add(request.getParameter("school"));
		}
		if (word(request.getParameter("userArea"))) {
			userInfo.add(request.getParameter("userArea"));
		}
		String interests = request.getParameter("userInterest");
		if (interests != null) {
			for (String interest : interests.split(" ")) {
				if (word(interest)) {
					userInfo.add(interest);
				}
			}
		}

//		for (String i : userInfo) {
//			System.out.println(i);
//		}
	}

	private void search(HttpServletRequest request) throws IOException {
		RepeatSearch repeatSearch = new RepeatSearch(userInfo, keyword);
		repeatSearch.searchWebPage();
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
	}

	// whether the word include "not words"
	private boolean word(String word) {
		if (word == null) {
			return false;
		}
		return word.matches("[A-Za-z0-9\\u4e00-\\u9fa5]+");
	}

	// load userInfo from file
//	private boolean userInfo() throws IOException {
//		if (userFile.exists()) {
//			InputStreamReader isr = new InputStreamReader(new FileInputStream(userFile));
//			BufferedReader br = new BufferedReader(isr);
//			for (String infos : br.readLine().split(" ")) {
//				userInfo.add(infos);
//			}
//			br.close();
//			isr.close();
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	private void createUserInfo(ArrayList<String> info) throws IOException {
//		userFile.createNewFile();
//		FileOutputStream fos = new FileOutputStream(userFile);
//		PrintWriter pw = new PrintWriter(fos);
//		for (String infos : userInfo) {
//			pw.write(infos + " ");
//		}
//		pw.close();
//		fos.close();
//		return;
//	}

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