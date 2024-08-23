package Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneMatcherDemo {
	public static void main(String[] args) {

		String phoneList = """
				(800) 123-4567
				(800)123-4567
				(800) 123 4567
				800-123-4567
				800 123-4567
				800 123 4567
				8001234567
				""";

		Pattern pattern = Pattern.compile("\\(*[0-9]{3}\\) [0-9]{3}-[0-9]{4}");
		Matcher matcher = pattern.matcher(phoneList);

		matcher.results()
				.forEach(mr -> System.out.println(mr.group()));

		Pattern pattern2 = Pattern.compile("\\(*[0-9]{3}\\)[0-9]{3}-[0-9]{4}");
		Matcher matcher2 = pattern2.matcher(phoneList);

		matcher2.results()
				.forEach(mr -> System.out.println(mr.group()));

		Pattern pattern3 = Pattern.compile("\\(*[0-9]{3}[)][0-9]{3}-[0-9]{4}");
		Matcher matcher3 = pattern3.matcher(phoneList);

		matcher3.results()
				.forEach(mr -> System.out.println(mr.group()));


		Pattern pattern4 = Pattern.compile("\\(*[0-9]{3}[)\\s]*[0-9]{3}[\\s]*[0-9]{4}");
		Matcher matcher4 = pattern4.matcher(phoneList);

		matcher4.results()
				.forEach(mr -> System.out.println(mr.group()));

		Pattern pattern5 = Pattern.compile("\\(*[0-9]{3}[)\\s-]*[0-9]{3}[\\s-]*[0-9]{4}");
		Matcher matcher5 = pattern5.matcher(phoneList);

		matcher5.results()
				.forEach(mr -> System.out.println(mr.group()));

		// [0-9] can be replaced with either \d or \p{Digit}
		Pattern pattern6 = Pattern.compile("\\(*[0-9]{3}[)\\s-]*\\d{3}[\\s-]*\\p{Digit}{4}");
		Matcher matcher6 = pattern6.matcher(phoneList);

		matcher6.results()
				.forEach(mr -> System.out.println(mr.group()));


		String htmlSnippets = """
                <H1>My Heading</h1>
                <h2>Sub-heading</h2>
                <p>This is a paragraph about something.</p>
                <p style="abc">This is another paragraph about something else.</p>
                <h3 id="third">Summary</h3>
                <br/>
                <p>Testing</p>
                """;

		Pattern pattern1 = Pattern.compile("<(\\w+)[^>]*>([^\\v</>]*)(</\\1>)*");
		Matcher matcher1 = pattern1.matcher(htmlSnippets);

		matcher1.results()
				.forEach(mr -> System.out.println("Full Tag : " + mr.group(0) +
						"\n" + "Type : " + mr.group(1) +
						"\n" + "Text : " + mr.group(2)));

		System.out.println("-". repeat(30));

		// [a-zA-Z_0-9] is same as [\\w]
		Pattern pattern_2 = Pattern.compile("<([a-zA-Z_0-9]+)[^>]*>([^\\v</>]*)(</\\1>)*");
		Matcher matcher_2 = pattern_2.matcher(htmlSnippets);

		matcher_2.results()
				.forEach(mr -> System.out.println("Full Tag : " + mr.group(0) +
						"\n" + "Type : " + mr.group(1) +
						"\n" + "Text : " + mr.group(2)));

		System.out.println("-". repeat(30));

		Pattern pattern_3 = Pattern.compile("<([a-zA-Z_0-9]+)[^>]*>([^\\v</>]*)((?i)</\\1>)*");
		Matcher matcher_3 = pattern_3.matcher(htmlSnippets);

		matcher_3.results()
				.forEach(mr -> System.out.println("Full Tag : " + mr.group(0) +
						"\n" + "Type : " + mr.group(1) +
						"\n" + "Text : " + mr.group(2)));

		System.out.println("-". repeat(30));

		Pattern pattern_4 = Pattern.compile("<([a-zA-Z_0-9]+)[^>]*>([^\\v</>]*)(</\\1>)*", Pattern.CASE_INSENSITIVE);
		Matcher matcher_4 = pattern_4.matcher(htmlSnippets);

		matcher_4.results()
				.filter(mr -> mr.group(1).toLowerCase().startsWith("h"))
				.forEach(mr -> System.out.println("Full Tag : " + mr.group(0) +
						"\n" + "Type : " + mr.group(1) +
						"\n" + "Text : " + mr.group(2)));

		System.out.println("-". repeat(30));

	}
}
