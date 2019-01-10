package com.yourstat.service;

import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yourstat.model.Twit;

public class RequestService {
	
	public String getTwits(String nick, String numTwits) throws Exception{
		String twits = "";
		String answer = "";
		String url = "";
		if(Integer.valueOf(numTwits) > 25) {
			Integer i = 0;
			Long minId = Long.valueOf(1);
			while(true) {
				if((i+1)*25 > Integer.valueOf(numTwits)) {
					url = "http://api.twitter.com/1/statuses/user_timeline.json?screen_name=" + nick + "&count=" + (Long.valueOf(numTwits) - 25*i) + "&max_id=" + (minId - 1);
					URLConnection conn = new URL(url).openConnection();
					conn.setDoOutput(false);
					conn.setDoInput(true);
					conn.connect();
					Scanner scan = new Scanner(conn.getInputStream());
					while(scan.hasNext()) {
						answer = answer + scan.next() + ' ';
					}
					Twit[] masTw = parsingJSON(answer);
					for(int l = 0; l < masTw.length; l++) {
						twits = twits + ' ' + masTw[l].text;
					}
					break;
				}
				else {
					if(i == 0) {
						url = "http://api.twitter.com/1/statuses/user_timeline.json?screen_name=" + nick + "&count=25";
					}
					else {
						url = "http://api.twitter.com/1/statuses/user_timeline.json?screen_name=" + nick + "&count=25&max_id=" + (minId - 1);
					}
					URLConnection conn = new URL(url).openConnection();
					conn.setDoOutput(false);
					conn.setDoInput(true);
					conn.connect();
					Scanner scan = new Scanner(conn.getInputStream());
					while(scan.hasNext()) {
						answer = answer + scan.next() + ' ';
					}
					Twit[] masTw = parsingJSON(answer);
					for(int l = 0; l < masTw.length; l++) {
						twits = twits + ' ' + masTw[l].text;
					}
					minId = masTw[0].id;
					for(int l = 1; l < masTw.length; l++) {
						if(masTw[l].id < minId) {minId = masTw[l].id;}
					}
					answer = "";
					i+=1;
				}
			}
		}
		else {
			url = "http://api.twitter.com/1/statuses/user_timeline.json?screen_name=" + nick + "&count=" + numTwits;
			URLConnection conn = new URL(url).openConnection();
			conn.setDoOutput(false);
			conn.setDoInput(true);
			conn.connect();
			Scanner scan = new Scanner(conn.getInputStream());
			while(scan.hasNext()) {
				answer = answer + scan.next() + ' ';
			}
			Twit[] masTw = parsingJSON(answer);
			for(int l = 0; l < masTw.length; l++) {
				twits = twits + ' ' + masTw[l].text;
			}
		}
		return twits;
	}
	
	private Twit[] parsingJSON(String jsonAnswer) throws Exception{
		Gson gson = new Gson();
		Collection<Twit> twits = gson.fromJson(jsonAnswer, new TypeToken<Collection<Twit>>(){}.getType());
		Twit[] masTw = new Twit[twits.size()];
		twits.toArray(masTw);
		return masTw;
	}
	
}