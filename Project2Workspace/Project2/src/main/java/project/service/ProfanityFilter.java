package project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class ProfanityFilter {

	// Fields necessary
	private static int largestWordLength = 12;
	private HashMap<String, String> allBadWords;

	// Constructor
  @Autowired
  public ProfanityFilter(HashMap<String, String> allBadWords) {
	super();
	this.allBadWords = allBadWords;
	
}

	/**
	 * Iterates over a String input and checks whether any cuss word was found - and
	 * for any/all cuss word found, as long as the cuss word should not be ignored
	 * (i.e. check for false positives - e.g. even though "bass" contains the word
	 * *ss, bass should not be censored) then (in the String returned) replace the
	 * cuss word with asterisks.
	 */
	public String getCensoredText(final String input) {
		

		generateBadWords();
		if (input == null) {
			return "";
		}

		String modifiedInput = input;

		// remove leetspeak
		modifiedInput = modifiedInput.replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e")
				.replaceAll("4", "a").replaceAll("@", "a").replaceAll("5", "s").replaceAll("7", "t")
				.replaceAll("0", "o").replaceAll("9", "g");

//    System.out.println(modifiedInput);

		// ignore any character that is not a letter
		modifiedInput = modifiedInput.toLowerCase().replaceAll("[^a-zA-Z]", "");

//    System.out.println(modifiedInput);

		ArrayList<String> badWordsFound = new ArrayList<>();

		// iterate over each letter in the word
		for (int start = 0; start < modifiedInput.length(); start++) {

			for (int offset = 1; offset < (modifiedInput.length() + 1 - start)
					&& offset < largestWordLength; offset++) {

				String wordToCheck = modifiedInput.substring(start, start + offset);

//				System.out.println(wordToCheck);

				if (allBadWords.containsKey(wordToCheck)) {

					String ignoreCheck = allBadWords.get(wordToCheck);

//					System.out.println(ignoreCheck.toString());

					boolean ignore = false;


						if (modifiedInput.contains(ignoreCheck/* [stringIndex] */)) {

							ignore = true;

							badWordsFound.add(wordToCheck);

							break;
						}

				}
			}
		}

		String inputToReturn = input;

		for (String swearWord : badWordsFound) {

			char[] charsStars = new char[swearWord.length()];

			Arrays.fill(charsStars, '*');

			final String stars = new String(charsStars);

			inputToReturn = inputToReturn.replaceAll("(?i)" + swearWord, stars);


		}

		return inputToReturn;
	} 

	public void generateBadWords() {

		// Adding keys and string arrays to the hashMap
		allBadWords.put("sozen", "sozen");
		allBadWords.put("dai", "dai");
		allBadWords.put("li", "li");
		allBadWords.put("comet", "comet");
		allBadWords.put("murder", "murder");
		allBadWords.put("death", "death");
		allBadWords.put("blood", "blood");
		allBadWords.put("war", "war");
	}

}