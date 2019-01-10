package tokinizer;
import java.util.Map;

public class WordFreq implements Comparable<WordFreq> {
	public final String word;
	public final int freq;

	public WordFreq(String word, int freq) {
		this.word = word;
		this.freq = freq;
	}

	@Override
	public int compareTo(WordFreq o) {
		return -(this.freq - o.freq);
	}

	public static WordFreq[] mapToWordFreqArray(Map<String, IntHolder> map) {
		WordFreq[] result = new WordFreq[map.size()];

		int i = 0;
		for (String key : map.keySet()) {
			result[i++] = new WordFreq(key, map.get(key).value);
		}

		return result;
	}
}
