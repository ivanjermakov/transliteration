package com.gmail.ivanjermakov1.transliteration;

public class Transliterator {
	
	public enum Language {
		ENGLISH,
		RUSSIAN
	}
	
	public static final String[] russianLetters = {
			"а", "б", "в", "г", "д",
			"е", "ё", "ж", "з", "и",
			"й", "к", "л", "м", "н",
			"о", "п", "р", "с", "т",
			"у", "ф", "х", "ц", "ч",
			"ш", "щ", "ъ", "ы", "ь",
			"э", "ю", "я"
	};
	
	public static final String[] russianSyllables = {
			"а", "б", "с", "д", "е",
			"ф", "г", "х", "и", "ж",
			"к", "л", "м", "н", "о",
			"п", "к", "р", "с", "т",
			"у", "в", "в", "кс", "y",
			"з"
	};
	
	public static final String[] englishLetters = {
			"a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j",
			"k", "l", "m", "n", "o",
			"p", "q", "r", "s", "t",
			"u", "v", "w", "x", "y",
			"z"
	};
	
	
	public static final String[] englishSyllables = {
			"a", "b", "v", "g", "d",
			"e", "yo", "zh", "z", "i",
			"i", "k", "l", "m", "n",
			"o", "p", "r", "s", "t",
			"u", "f", "h", "c", "ch",
			"sh", "sch", "\'", "i", "\'",
			"e", "y", "ya"
	};
	
	private Language domainLanguage;
	private Language targetLanguage;
	
	
	public static void main(String[] args) {
		System.out.println(new Transliterator(Language.RUSSIAN, Language.ENGLISH).transliterate("простой пример строки"));
		System.out.println(transliterate("example of simple line!", Language.ENGLISH, Language.RUSSIAN));
	}
	
	public Transliterator() {}
	
	public Transliterator(Language domainLanguage, Language targetLanguage) {
		this.domainLanguage = domainLanguage;
		this.targetLanguage = targetLanguage;
	}
	
	public String transliterate(String string) {
		if (domainLanguage == null || targetLanguage == null)
			throw new IllegalStateException("Domain and target languages must be specified");
		if (domainLanguage == Language.RUSSIAN) {
			return swap(string, russianLetters, englishSyllables);
		}
		if (domainLanguage == Language.ENGLISH) {
			return swap(string, englishLetters, russianSyllables);
		}
		
		return "";
	}
	
	public static String transliterate(String string, Language domainLanguage, Language targetLanguage) {
		if (domainLanguage == Language.RUSSIAN) {
			return swap(string, russianLetters, englishSyllables);
		}
		if (domainLanguage == Language.ENGLISH) {
			return swap(string, englishLetters, russianSyllables);
		}
		
		return "";
	}
	
	private static String swap(String string, String[] letters, String[] syllables) {
		StringBuilder result = new StringBuilder();
		char[] init = string.toCharArray();
		for (char letter : init) {
			if (contains(letters, String.valueOf(letter))) {
				result.append(syllables[find(letters, String.valueOf(letter))]);
			} else {
				result.append(letter);
			}
		}
		return result.toString();
	}
	
	private static int find(String[] letters, String letter) {
		for (int i = 0; i < letters.length; i++) {
			if (letters[i].equals(letter)) return i;
		}
		
		return -1;
	}
	
	private static boolean contains(String[] letters, String letter) {
		for (String l : letters) {
			if (l.equals(letter)) return true;
		}
		
		return false;
	}
	
}