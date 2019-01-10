package tokinizer;

import java.util.Map;

public class TokinizerResult {
	public final WordFreq[] wordsFreq;
	public final WordFreq[] lettersFreq;
	public final Map<String, IntHolder> nWords;
	public final Map<String, IntHolder> nLetters;

	public TokinizerResult(WordFreq[] wordsFreq, WordFreq[] lettersFreq,
			Map<String, IntHolder> nWords, Map<String, IntHolder> nLetters) {
		this.wordsFreq = wordsFreq;
		this.lettersFreq = lettersFreq;
		this.nWords = nWords;
		this.nLetters = nLetters;
	}
}
