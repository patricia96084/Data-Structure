import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class UserInfo extends HttpServlet {
	private ArrayList<String> userInfo = new ArrayList<String>();
	private String name;
	private String filePath = "finalProject_DataStructure\\";
	private File info;

	public UserInfo() {
		super();
//		info = new File(filePath + name + ".txt");
//		try {
//			InputStreamReader reader = new InputStreamReader(new FileInputStream(info)); // 建立一個輸入流物件reader
//			BufferedReader br = new BufferedReader(reader); // 建立一個物件，它把檔案內容轉成計算機能讀懂的語言
//			String information = br.readLine();
//			int i = 0;
//			for (String infos : information.split("\n")) {
//				userInfo.add(i, infos);
//				i++;
//			}
//			br.close();
//		} catch (IOException e) {
//
//		}
	}

	public String keywordToString() {
		String infoKeyword = "";
		for (String keywords : userInfo) {
			infoKeyword = infoKeyword + "+" + keywords;
		}
		return infoKeyword;
	}

	public ArrayList<String> getuserInfo() {
		return userInfo;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		name = request.getParameter("name");
		info = new File(filePath + name + ".txt");
		try {
			if (!info.exists() || !findFile(name)) {
				userInfo.add(request.getParameter("schoolName"));
				for (String interest : request.getParameter("interest").split(" ")) {
					userInfo.add(interest);
				}
				userInfo.add(request.getParameter("area"));
				try {
					createFile(name, userInfo);
				} catch (IOException e1) {
					System.out.println("create failed "+e1.toString());
				}
			}
			return;
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		SearchGoogle searchGoogle = new SearchGoogle(userInfo);
	}

	private boolean findFile(String name) throws IOException {
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(info)); // 建立一個輸入流物件reader
			BufferedReader br = new BufferedReader(reader); // 建立一個物件，它把檔案內容轉成計算機能讀懂的語言
			String information = br.readLine();
			int i = 0;
			for (String infos : information.split(" ")) {
				userInfo.add(i, infos);
				i++;
			}
			br.close();
			reader.close();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	private void createFile(String name, ArrayList<String> userInfo) throws IOException {
		info.createNewFile();
		FileOutputStream writer = new FileOutputStream(info);
		PrintWriter pw = new PrintWriter(writer);
		for (String infos : userInfo) {
			pw.write(infos);
		}
		pw.close();
		writer.close();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
