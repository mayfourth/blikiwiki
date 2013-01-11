package info.bliki.api;

public class UserParserTest {
	private static String TEST = "<?xml version=\"1.0\" encoding=\"utf-8\"?><api><login result=\"Success\" lguserid=\"7550\""
			+ " lgusername=\"Tester\" lgtoken=\"9c46c045ff00d7a8e8c61c60e0ed54bf\" /></api>";

	public static void main(String args[]) throws Exception {
		XMLUserParser parser = new XMLUserParser(new User("", "", ""), TEST);
		parser.parse();
	}
}
