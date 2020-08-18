package com.ssafy.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.ssafy.model.dto.RssChannel;
import com.ssafy.model.dto.RssItem;

public class RssParser implements Runnable {
	private String link;
	private RssChannel rssChannel;
	private RedisTemplate<String, Object> redisTemplate;

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

			Elements items = document.select("item");
			this.rssChannel.itemInit();
			for (Element item : items) {
				RssItem rssItem = new RssItem();
				rssItem.setTitle(item.select("title").text());
				rssItem.setDescription(Jsoup.parse(item.select("description").text()).text());
				rssItem.setLink(item.select("link").text());
				rssItem.setPubDate(item.select("pubDate").text());
				rssItem.setRssTitle(this.rssChannel.getTitle());
				this.rssChannel.addItem(rssItem);
			}

			redisTemplate.opsForValue().set(this.link, this.rssChannel);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("[ERROR] 파싱에러");
			e.printStackTrace();
		}
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				this.parse();
				this.redisTemplate.opsForValue().set(this.link, this.rssChannel);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
