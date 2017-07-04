package dummy;

import java.net.URL;

public class Test {
	public static void main(String[] args) {

		URL url = Test.class.getClassLoader().getResource("UnplannedEvents.xml");
		System.out.println(url != null);

	}
}