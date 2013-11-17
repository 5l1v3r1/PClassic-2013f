import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class KingSort {

	@SuppressWarnings("unchecked")
	public static ArrayList<AlienName> names(String[] list) {
		ArrayList<AlienName> names = new ArrayList<AlienName>();
		for (int i = 0; i < list.length; i++) {
			String s = list[i].trim();
			names.add(new AlienName(s));
		}
		Collections.sort(names);
		return names;
	}
	
	public static String kingSort(String kingString) {
		ArrayList<AlienName> sorted = names(kingString.split(","));
		String list = "";
		for (int i = 0; i < sorted.size(); i++) {
			if (i != 0) list += ",";
			list += sorted.get(i).toString();
		}
		return list;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new FileReader ("KingSort.txt"));
		
		while (br.ready()) {
			System.out.println(kingSort(br.readLine()));
		}
		
		br.close ();
	}
	
}
