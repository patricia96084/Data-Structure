import java.util.ArrayList;

public class RepeatSearch {
	private UserInfo userInfo;
	private String searchWord;
	private ArrayList<Keyword> keyword;
	public ArrayList<WebPage> webPage;
	
	public RepeatSearch(UserInfo userInfo, String searchWord, ArrayList<Keyword> keyword) {
		this.userInfo = userInfo;
		this.searchWord = searchWord;
		keyword = new ArrayList<Keyword>();
		concludeKeyword();
	}
	
	private void concludeKeyword() {
		String[] words = searchWord.split(" ");
		for (String word : words) {
			keyword.add(new Keyword(word,5));
		}
		for (String infos:userInfo.getuserInfo()) {
			keyword.add(new Keyword(infos,1));
		}
	}
	
	public void searchWebPage() {
		for (Keyword find : keyword) {
			
		}
	}
}
