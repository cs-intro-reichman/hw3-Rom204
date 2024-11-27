
/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true
		System.out.println(isAnagram("eea","aae")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		String fixedStr1 = preProcess(str1);
		String fixedStr2 = preProcess(str2);
		
		if (fixedStr1.length() != fixedStr2.length()) return false;

		for (int i = 0; i < fixedStr1.length(); i++){
			boolean found = false;
			for (int j = 0; j < fixedStr2.length(); j++){
				if (fixedStr1.charAt(i) == fixedStr2.charAt(j)){
					found = true;
					fixedStr2 = fixedStr2.substring(0, j) + fixedStr2.substring(j + 1);
					break;
				}
			}
			if (found == false) return false;
		}
		return true;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	static String preProcess(String str) {
		String temp = "";
		boolean found = false;
		for (int i = 0; i < str.length(); i++){
			char c = str.charAt(i);
			if ((c >= 'a') && (c <= 'z')){
				temp += c;
			} else if ((c >= 'A') && (c <= 'Z')){
				c += 32;
				temp += (char) c; 
			} else if (c == ' ' && !found){
				temp += c;
				found = !found;
			}
		}
		str = temp;

		return str;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		String temp = "";
		// iter through all the str
		while (str.length() > 0){
			// get the index of random char
			int ch = (int) (Math.random() * str.length());
			temp += str.charAt(ch);

			// remove char from str 
			str = str.substring(0, ch) + str.substring(ch + 1);
		}
		str = temp;
		return str;
	}
}