import java.util.Comparator;

public class WebPageComparator implements Comparator<WebPage> {

	@Override
	public int compare(WebPage w1, WebPage w2) {
		// TODO Auto-generated method stub
		if (w1.score > w2.score) {
			return 1;
		} else if (w1.score == w2.score) {
			return 0;
		} else {
			return -1;
		}
	}

}