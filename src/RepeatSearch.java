import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class RepeatSearch {
	private ArrayList<String> userInfo;
	private String searchWord;
	private ArrayList<Keyword> keyword;
	public HashMap<String, String> query;
	public ArrayList<WebPage> webPage;

	public RepeatSearch(ArrayList<String> userInfo, String searchWord) {
		this.userInfo = userInfo;
		this.searchWord = searchWord.replace(" ", "+");
		keyword = new ArrayList<Keyword>();
		query = new HashMap<String, String>();
		webPage = new ArrayList<WebPage>();
		concludeKeyword();
	}

	private void concludeKeyword() {
		keyword.add(new Keyword(searchWord, 5));
		for (String infos : userInfo) {
			keyword.add(new Keyword(infos, 1));
		}
	}

	public void searchWebPage() throws IOException {
		for (int i = 0; i < keyword.size(); i++) {
			getGoogleQuery(i);
		}
	}
//		GoogleQuery googleQuery = new GoogleQuery(keyword.get(0).keyword);
//		for (int i = 0; i < keyword.size(); i++) {
//			if (i != 0) {
//				googleQuery = new GoogleQuery(keyword.get(i).keyword + "+" + keyword.get(0).keyword);
//			}
//			calWebPageTitle(googleQuery, i);
////			calWebPageContent(googleQuery, i);
//			webPage.addAll(googleQuery.webPage);
//			query.putAll(googleQuery.query());
//		}

	private void getGoogleQuery(int i) throws IOException {
		GoogleQuery googleQuery;
		if (i == 0) {
			googleQuery = new GoogleQuery(keyword.get(0).keyword);
			query = googleQuery.query();
			WebPage repeat;
			for (WebPage web : googleQuery.webPage) {
				repeat = repeated(web);
				if (repeat == null) {
					web.score += 5;
					webPage.add(web);
				} else {
					webPage.get(webPage.indexOf(repeat)).score += 5;
				}
			}
		} else {
			googleQuery = new GoogleQuery(keyword.get(0).keyword + "+" + keyword.get(i).keyword);
			query = googleQuery.query();
			WebPage repeat;
			for (WebPage web : googleQuery.webPage) {
				repeat = repeated(web);
				if (repeat == null) {
					web.score += 7;
					webPage.add(web);
				} else {
					webPage.get(webPage.indexOf(repeat)).score += 7;
				}
			}
		}
	}

	private WebPage repeated(WebPage web) {
		for (WebPage page : webPage) {
			if (web.url.startsWith(page.url) || page.url.startsWith(web.url)) {
				return page;
			}
		}
		return null;
	}

//	private void calWebPageTitle(GoogleQuery googleQuery, int time) throws IOException {
//		int i = googleQuery.webPage.size();
//		for (WebPage web : googleQuery.webPage) {
//			for (String find : searchWord.split("+")) {
//				if (web.title.contains(find)) {
//					web.score += keyword.get(0).weight * i;
//				}
//			}
//			if (time != 0) {
//				if (web.title.contains(keyword.get(time).keyword)) {
//					web.score += keyword.get(time).weight * i;
//				}
//			}
//			i--;
//		}
//	}
//
//	private void calWebPageContent(GoogleQuery googleQuery, int time) throws IOException {
//		
//	}
}
