import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
			keyword.add(new Keyword(infos, 2));
		}

		for (Keyword word : keyword) {
			System.out.println(word);
		}
	}

	public void searchWebPage() throws IOException {
		for (int i = 0; i < keyword.size(); i++) {
			getGoogleQuery(i);
			for (WebPage web : webPage) {
				System.out.println(web.title + "," + web.score + web.url.substring(0, 5));
			}
		}
		webPage.sort(new WebPageComparator());
//		for (WebPage web : webPage) {
//			if (web.title.length() > 5) {
//				System.out.println(web.title.substring(0, 5) + "," + web.score + web.url.substring(0, 5));
//			} else {
//				System.out.println(web.title + "," + web.score + web.url.substring(0, 5));
//			}
//		}

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
		String keywordGoSearch = keyword.get(0).keyword;
		if (i!=0) {
			keywordGoSearch = keywordGoSearch+"+"+keyword.get(i).keyword;
		}
		GoogleQuery googleQuery = new GoogleQuery(keywordGoSearch);
		query.putAll(googleQuery.query());
		
//		GoogleQuery googleQuery;
//		int add;
//		if (i == 0) {
//			googleQuery = new GoogleQuery(keyword.get(0).keyword);
//			add = keyword.get(0).weight;
//		} else {
//			googleQuery = new GoogleQuery(keyword.get(0).keyword + "+" + keyword.get(i).keyword);
//			add = keyword.get(0).weight + keyword.get(i).weight;
//		}
//		query = googleQuery.query();
////		System.out.println(googleQuery.url);
//		WebPage repeat;
//		for (WebPage web : googleQuery.webPage) {
//			repeat = repeated(web);
//			if (repeat == null) {
//				web.score += add;
//				webPage.add(web);
//			} else {
//				webPage.get(webPage.indexOf(repeat)).score += add;
//			}
////			if (web.title.length() > 5) {
////				System.out.println(web.title.substring(0, 5) + "," + web.score + web.url.substring(0, 5));
////			} else {
////				System.out.println(web.title+ "," + web.score + web.url.substring(0, 5));
////			}
//		}
//		GoogleQuery googleQuery = new GoogleQuery(keyword.get(i).keyword);
//		int add = keyword.get(i).weight;
//		if (i > 0) {
//			googleQuery = new GoogleQuery(keyword.get(0).keyword + "+" + keyword.get(i).keyword);
//			add = keyword.get(i).weight + keyword.get(0).weight;
//		}
//		googleQuery.query();
//		WebPage repeat;
//		ArrayList<WebPage> gooWeb = googleQuery.webPage;
//		for (WebPage web : gooWeb) {
//			repeated(web,add);
//		}
	}

	private void repeated(WebPage web, int score) {
		for (WebPage page : webPage) {
			if (web.url.startsWith(page.url) || page.url.startsWith(web.url)) {
				page.score += score;
				System.out.println(page.title + " is updated with the score of " + score);
				return;
			}
		}
		web.score += score;
		webPage.add(web);
		System.out.println(
				web.title + " is added with the score of " + score + " at the position of" + webPage.indexOf(web));
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
