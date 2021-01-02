public class Keyword {
	public String keyword;
	public int count;
	public int weight;

	public Keyword(String keyword, int count, int weight) {
		this.keyword = keyword;
		this.count = count;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "[" + keyword + "," + count + "," + weight + "]";
	}
}
