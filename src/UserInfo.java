import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class UserInfo extends HttpServlet {
	private ArrayList<String> userInfo;
	private String name;
	private String filePath = "finalProject_DataStructure\\";
	private File info;

	public UserInfo(String name) {
		super();
		this.name = name;
		this.userInfo = new ArrayList<String>();
		info = new File(filePath + name + ".txt");
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(info)); // 建立一個輸入流物件reader
			BufferedReader br = new BufferedReader(reader); // 建立一個物件，它把檔案內容轉成計算機能讀懂的語言
			String information = br.readLine();
			int i = 0;
			for (String infos : information.split("\n")) {
				userInfo.add(i, infos);
				i++;
			}
			br.close();
		} catch (IOException e) {
			// get the info by jsp
		}
	}

	public String keywordToString() {
		String infoKeyword = "";
		for (String keywords : userInfo) {
			infoKeyword = infoKeyword + "+"+ keywords;
		}
		return infoKeyword;
	}
	
	public ArrayList<String> getuserInfo(){
		return userInfo;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		//接收使用者名稱
		name = request.getParameter("name");
		if (request.getParameter("name") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
	}
}
