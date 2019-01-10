package tokinizer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokinizer {
	private static void upCounter(String key, Map<String, IntHolder> map) {
		// Если такой ключ существует, увеличиваем на 1 хранимое значение
		if (map.containsKey(key))
			map.get(key).value++;
		else
			// Если нет, то зоздаем новый со знаением 1
			map.put(key, new IntHolder(1));
	}

	public static void tokenize(List<String> twits,
			Map<String, IntHolder> nWords, Map<String, IntHolder> nLetters,
			Set<String> stopwords) {
		Pattern wordPattern = Pattern.compile("[a-zа-я]+");
		Pattern letterPattern = Pattern.compile("[a-zа-я]");
		Matcher m;
		for (String twit : twits) {
			twit = twit.toLowerCase();
			m = wordPattern.matcher(twit);
			// Пока есть еще совпадения
			while (m.find()) {
				// Получить новое совпадение
				String word = m.group();
				if (!stopwords.contains(word)) {
					upCounter(word, nWords);
				}
			}

			m = letterPattern.matcher(twit);
			while (m.find())
				upCounter(m.group(), nLetters);
		}
	}

	public static ArrayList<String> fileToListString(String file) {
		ArrayList<String> result = null;
		try {
			return fileToListString(new BufferedReader(new FileReader(file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static ArrayList<String> fileToListString(InputStream is) {
		ArrayList<String> result = null;
		try {
			return fileToListString(new BufferedReader(new InputStreamReader(is, "UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static ArrayList<String> fileToListString(BufferedReader in) {
		ArrayList<String> result = new ArrayList<String>();

		String line;
		try {
			while ((line = in.readLine()) != null) {
				result.add(line);
			}
		} catch (IOException e) {
			System.err.println("Error in reading file");
			e.printStackTrace();
		}

		return result;
	}

	public static TokinizerResult getFreqs(List<String> twits, List<String> stopwordsList) {
		Map<String, IntHolder> nWords = new HashMap<String, IntHolder>();
		Map<String, IntHolder> nLetters = new HashMap<String, IntHolder>();

		Set<String> stopwords = new HashSet<String>();

		for (String word : stopwordsList) {
			stopwords.add(word);
		}

		tokenize(twits, nWords, nLetters, stopwords);

		WordFreq[] wordsFreq = WordFreq.mapToWordFreqArray(nWords);
		WordFreq[] lettersFreq = WordFreq.mapToWordFreqArray(nLetters);
		Arrays.sort(wordsFreq);
		Arrays.sort(lettersFreq);

		return new TokinizerResult(wordsFreq, lettersFreq, nWords, nLetters);
	}
}
