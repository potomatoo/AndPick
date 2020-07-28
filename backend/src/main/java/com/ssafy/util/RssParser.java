package com.ssafy.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.ssafy.model.dto.RssChannel;
import com.ssafy.model.dto.RssItem;

public class RssParser {
	private String link;
	private RssChannel rssChannel;

	public RssParser(String link) {
		this.link = link;
		this.rssChannel = new RssChannel();
	}

	public void parse() {
		try {
			Connection con = Jsoup.connect(this.link);
			Document document = con.get();

			this.rssChannel.setTitle(document.selectFirst("title").text());
			this.rssChannel.setLink(document.selectFirst("link").text());
			this.rssChannel.setCategory(document.selectFirst("category").text());

			Elements items = document.select("item");
			for (Element item : items) {
				RssItem rssItem = new RssItem();
				rssItem.setTitle(item.select("title").text());
				rssItem.setDescription(item.select("description").text());
				rssItem.setLink(item.select("link").text());
				rssItem.setPubDate(item.select("pubDate").text());

				this.rssChannel.addItem(rssItem);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("[ERROR] 파싱에러");
			e.printStackTrace();
		}
	}
}
