package com.yourstat.view;

import java.util.ArrayList;
import java.util.List;

import tokinizer.FontsEvaluator;
import tokinizer.Tokinizer;
import tokinizer.TokinizerResult;

import com.opensymphony.xwork2.ActionSupport;
import com.yourstat.model.WordFont;
import com.yourstat.service.RequestService;

public class ResultAction extends ActionSupport {
	protected String nick;
	protected String numTwits;
	protected String allTwits;
	protected List<WordFont> listWF = new ArrayList<WordFont>();

	@Override
	public String execute() throws Exception {
		String stopwords = "a b c d e f g h i j  k l m n o p q r s t u v w x y z co http https www ru" +
						   "com net the is was are were be a an and or to in of you your he she it his " +
						   "its we no there when our they their who what in to of between around " +
						   "on every for from off that this theese что кто чего кого ком чем кем " +
						   "для тут тобой нами чем вас нас чей чья чью чьи как зачем почему откуда куда " +
						   "когда меня мне нам вами мой никак незачем ниоткуда некуда некогда или на то за " +
						   "все по но это так вам если есть нет да не ни по перед после гр " +
						   "вы ты они он она оно мой твой мои твои наш наши нашим ними ими а б в г д " +
						   "е ё ж з и й к л м н о п р с т у ф х ц ч ш щ ъ ы ь э ю я";
		/*allTwits = "And God said, Let there be said said let When no zbush of the " +
				   "field1 was yet in the land2 and no small plant of the field had " +
				   "yet sprung up—for the Lord God had not caused it to rain on the " +
				   "land, and there was no man ato work the ground, 6 and a mist3 was " +
				   "going up from the land and was watering the whole face of the " +
				   "ground— 7 then the Lord God formed the man of bdust from the ground " +
				   "and cbreathed into his dnostrils the breath of life, and ethe man " +
				   "became a living creature. 8 And the Lord God planted a fgarden in " +
				   "Eden, in the east, and there he put the man whom he had formed. 9 " +
				   "And out of the ground the Lord God made to spring up every tree that is pleasant";*/
		String[] masStopWords = stopwords.split(" ");
		String[] masAllTwits = allTwits.split(" ");
		List<String> listStopWords = new ArrayList<String>();
		List<String> listAllTwits = new ArrayList<String>();
		for(int i=0; i < masStopWords.length; i++) {listStopWords.add(masStopWords[i]);}
		for(int i=0; i < masAllTwits.length; i++) {listAllTwits.add(masAllTwits[i]);}
		TokinizerResult tr = Tokinizer.getFreqs(listAllTwits, listStopWords);
		int maxNumWords = 27;
		if(maxNumWords > tr.wordsFreq.length) {maxNumWords = tr.wordsFreq.length;}
		int[] fonts = FontsEvaluator.getFont(tr, maxNumWords, 10, 30);
		for(int i = 0; i < maxNumWords; i++) {
			WordFont wf = new WordFont();
			wf.setWord(tr.wordsFreq[i].word);
			wf.setFont(fonts[i]);
			listWF.add(wf);
		}
		return SUCCESS;
	}
	@Override
	public void validate() {
		try {Integer.valueOf(numTwits);}
		catch(Exception e) {addFieldError("numTwits", "Number of twits must be an integer number");}
		finally {}
		
		if(nick.isEmpty()) {addFieldError("emptyNick","Nick is empty");}
		
		try {
			Integer.valueOf(numTwits);
			if(!nick.isEmpty()) {
				try {
					RequestService reqServ = new RequestService();
					allTwits = reqServ.getTwits(nick, numTwits);
				}
				catch(Exception e) {addFieldError("error","Service unavailable or NICK is wrong"); e.printStackTrace();}
				finally {}
			}
		}
		catch(Exception e) {}
		finally {}
	}
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getNumTwits() {
		return numTwits;
	}
	public void setNumTwits(String numTwits) {
		this.numTwits = numTwits;
	}
	public String getAllTwits() {
		return allTwits;
	}
	public void setAllTwits(String allTwits) {
		this.allTwits = allTwits;
	}
	public List<WordFont> getListWF() {
		return listWF;
	}
	public void setListWF(List<WordFont> listWF) {
		this.listWF = listWF;
	}
}