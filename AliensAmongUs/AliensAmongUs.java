import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

public class AliensAmongUs {

	/*	Write the body of this method
	 *	Data contains a 2-D array
	 *	data[n] will represent the nth person
	 *	data[n][0] is the name of the nth person
	 *	everything after data[n][0] are the responses of the nth person
	 */
	public static String findAlien (String[][] data) {
		String[] commonAnswers = new String[data[0].length - 1];

		for (int question = 1; question < data[0].length; question++) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for (int i = 0; i < data.length; i++) {
				String answer = data[i][question];
				if (map.get(answer) != null) {
					Integer val = map.get(answer);
					map.put(answer, new Integer(val.intValue() + 1));
				} else {
					map.put(answer, new Integer(1));
				}
			}
			String maxAnswer = null;
			int maxCount = 0;
			for (String key : map.keySet()) {
				if (map.get(key).intValue() > maxCount) {
					maxAnswer = key;
					maxCount = map.get(key).intValue();
				}
			}
			commonAnswers[question - 1] = maxAnswer;
		}
		
		int correctCount = -1;
		String alien = null;
		for (int person = 0; person < data.length; person++) {
			// check their correct
			int correct = 0;
			for (int question = 1; question < data[0].length; question++) {
				if (data[person][question].equals(commonAnswers[question - 1])) {
					correct++;
				}
			}
			if (correct < correctCount || alien == null) {
				alien = data[person][0];
				correctCount = correct;
			}
		}

		return alien;
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("AliensAmongUs.txt"));

		while (br.ready()) {
			int people = Integer.parseInt(br.readLine());
			String[][] data = new String[people][];
			for (int i = 0; i < people; i++) {
				data[i] = br.readLine().split(",\\s*");
			}
			System.out.println(findAlien(data));
		}
		br.close();
	}
}
