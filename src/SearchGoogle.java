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
	private String userName;

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
		 * ­ìcode response.setContentType("text/html"); if
		 * (request.getParameter("inputKeyword") == null) { String requestUri =
		 * request.getRequestURI(); request.setAttribute("requestUri", requestUri);
		 * request.getRequestDispatcher("Search.jsp").forward(request, response);
		 * return; }
		 */
		userName = request.getParameter("name");
		if (userName == null || !userName.matches("[a-zA-Z0-9_]+")) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("UserInfo.jsp").forward(request, response);
			return;
		}
		System.out.println(userName);
//		userFile = (new UserInfoFile(,userName)).userFile;
		userInfo = new ArrayList<String>();
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
		} else {
			System.out.println("interest is null");
		}

		for (String i : userInfo) {
			System.out.println(i);
		}

		if (userInfo.size() == 0) {
			request.getRequestDispatcher("UserInfo.jsp").forward(request, response);
			return;
		} else if (!word(request.getParameter("keyword"))) {
//				createUserInfo(userInfo);
//			System.out.println("k");
			request.getRequestDispatcher("SearchPage.jsp").forward(request, response);
			return;
		} else {
			RepeatSearch repeatSearch = new RepeatSearch(userInfo, request.getParameter("keyword"));
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
//	}

////		GoogleQuery google = new GoogleQuery(request.getParameter("inputKeyword"));//­ìcode
//		RepeatSearch repeatSearch = new RepeatSearch(userInfo, request.getParameter("inputKeyword"));
////		HashMap<String, String> query = google.query(); //­ìcode
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