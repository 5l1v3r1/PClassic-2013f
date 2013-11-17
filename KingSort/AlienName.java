
public class AlienName implements Comparable {

	public String first;
	public long last;
	public String orig;
	
	public AlienName(String name) {
		first = name.split(" ")[0].toUpperCase();
		last = calcValue(name.split(" ")[1].toUpperCase());
		orig = name;
	}

	public String toString() {
		return orig;
	}
	
	@Override
	public int compareTo(Object arg0) {
		int res = first.compareTo(((AlienName)arg0).first);
		if (res == 0) {
			return last < ((AlienName)arg0).last ? -1 : 1;
		} else return res;
	}
	
	public static long getLetterValue(String s) {
		if (s.equals("X")) return 10;
		if (s.equals("Y")) return 5;
		if (s.equals("Z")) return 1;
		return 0;
	}
	
	public static long calcValue(String s) {
		long answer = 0;
		for (int i = 0; i < s.length(); i++) {
			long firstVal = getLetterValue("" + s.charAt(i));
			if (i + 1 < s.length()) {
				long secondVal = getLetterValue("" + s.charAt(i + 1));
				if (firstVal < secondVal) answer -= firstVal;
				else answer += firstVal;
			} else answer += firstVal;
		}
		return answer;
	}
	
}
