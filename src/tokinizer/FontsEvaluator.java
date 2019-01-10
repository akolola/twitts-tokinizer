package tokinizer;
import java.util.Arrays;

public class FontsEvaluator {
	private static int[] getFont(int[] freqs, int minFontSize, int maxFontSize) {
		int[] result = new int[freqs.length];

		int uniqueFreqsN = 1;
		for (int i = 1; i < freqs.length; ++i) {
			if (freqs[i] != freqs[i - 1]) {
				uniqueFreqsN++;
			}
		}

		result[0] = maxFontSize;
		int j = 0;
		for (int i = 1; i < freqs.length; ++i) {
			if (freqs[i] == freqs[i - 1]) {
				result[i] = result[i - 1];
			} else {
				j++;
				result[i] = (int) Math.round(maxFontSize
						- (double) (maxFontSize - minFontSize) * j
						/ (uniqueFreqsN - 1));
			}
		}

		return result;
	}

	public static int[] getFont(TokinizerResult tr, int maxWordN,
			int minFontSize, int maxFontSize) {
		WordFreq[] highWordFreqs = Arrays.copyOf(tr.wordsFreq,
				Math.min(maxWordN, tr.wordsFreq.length));

		maxWordN = highWordFreqs.length;
		int[] freqs = new int[maxWordN];

		for (int i = 0; i < highWordFreqs.length; ++i) {
			freqs[i] = highWordFreqs[i].freq;
		}

		return getFont(freqs, minFontSize, maxFontSize);
	}

	public static void main(String[] args) {

		TokinizerResult tr = Tokinizer.getFreqs(
				Tokinizer.fileToListString("twits.txt"),
				Tokinizer.fileToListString("stopwords.txt"));

		int[] fonts = getFont(tr, 40, 10, 14);

		for (int i = 0; i < fonts.length; ++i) {
			System.out.printf("%s->%d(%d)\n", tr.wordsFreq[i].word,
					tr.wordsFreq[i].freq, fonts[i]);
		}
	}
}
