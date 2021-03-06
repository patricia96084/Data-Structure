import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleQuery

{

	public String searchKeyword;

	public String url;

	public String content;

	public ArrayList<WebPage> webPage;

	public GoogleQuery(String searchKeyword)

	{

		this.searchKeyword = searchKeyword;

		try {
			url = "http://www.google.com/search?q=" + URLEncoder.encode(searchKeyword, "UTF-8") + "&oe=utf8&num=10";
		} catch (UnsupportedEncodingException e) {
		}

	}

	private String fetchContent() throws IOException {
		String retVal = "";

		URL u = new URL(url);

		URLConnection conn = u.openConnection();

		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in, "utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while ((line = bufReader.readLine()) != null) {
			retVal += line;

		}
		return retVal;
	}

	public HashMap<String, String> query() throws IOException

	{

		if (content == null)

		{

			content = fetchContent();

		}

		HashMap<String, String> retVal = new HashMap<String, String>();
		Document doc = Jsoup.parse(content);
		System.out.println(doc.text());
		Elements lis = doc.select("div");
		// System.out.println(lis);
		lis = lis.select(".kCrYT");
		// System.out.println(lis.size());
		webPage = new ArrayList<WebPage>();
		for (Element li : lis) {
			try {
				String citeUrl = li.select("a").get(0).attr("href");
				String title = li.select("a").get(0).select(".vvjwJb").text();

//				if (url.startsWith("/?url")) {
//					webPage.add(new WebPage(title, "google.com" + url));
//				} else {
//					webPage.add(new WebPage(title, url));
//				}
				if (citeUrl.startsWith("/url")) {
					citeUrl = "google.com" + citeUrl;
				}
				System.out.println(title + "," + citeUrl);
				retVal.put(title, citeUrl);
			} catch (IndexOutOfBoundsException e) {
//				e.printStackTrace();
			}
		}

		return retVal;

	}

}