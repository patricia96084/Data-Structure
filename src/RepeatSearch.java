import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class RepeatSearch {
	private UserInfo userInfo;
	private String searchWord;
	private ArrayList<Keyword> keyword;
	public HashMap<String, String> query;
	public ArrayList<WebPage> webPage;

	public RepeatSearch(UserInfo userInfo, String searchWord) {
		this.userInfo = userInfo;
		this.searchWord = searchWord.replace(" ", "+");
		keyword = new ArrayList<Keyword>();
//		query = new HashMap<String, String>();
		concludeKeyword();
	}

	private void concludeKeyword() {
		keyword.add(new Keyword(searchWord, 5));
		for (String infos : userInfo.getuserInfo()) {
			keyword.add(new Keyword(infos, 1));
		}
	}

	public void searchWebPage() throws IOException {
		GoogleQuery original = new GoogleQuery(keyword.get(0).keyword);
		query.putAll(original.query());
		for (WebPage web : original.webPage) {
			for (String find : searchWord.split("+")) {
				if (web.title.contains(find)) {
					web.score += keyword.get(0).weight;
				}
			}
		}
		for (int i = 1; i < keyword.size(); i++) {

		}
	}
}
