package com.ssafy.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.redis.core.RedisTemplate;

import com.ssafy.model.dto.Rss;
import com.ssafy.model.dto.RssChannel;
import com.ssafy.model.dto.RssItem;

public class RssParser implements Runnable {
	private String link;
	private RssChannel rssChannel;
	private RedisTemplate<String, Object> redisTemplate;

	public RssParser(Rss rss) {
		this.link = rss.getRssUrl();
		this.rssChannel = new RssChannel();
		this.rssChannel.setRss(rss);
	}

	public void parse() {
		try {
			Connection con = Jsoup.connect(this.link);
			Document document = con.get();

			this.rssChannel.setTitle(this.rssChannel.getRss().getRssName());
			this.rssChannel.setLink(document.selectFirst("link").text());
			try {
				Element img = document.selectFirst("image");
				try {
					this.rssChannel.setImg(img.selectFirst("url").text());
				} catch (Exception e) {
					this.rssChannel.setImg("");
				}
			} catch (Exception e) {
				// TODO: handle exception
				this.rssChannel.setImg("");
			}

			Elements items = document.select("item");
			HashMap<String, String> newsDetail = new HashMap<String, String>();
			this.rssChannel.itemInit();
			for (Element item : items) {
				RssItem rssItem = new RssItem();
				rssItem.setTitle(item.select("title").text());
				rssItem.setLink(item.select("link").text());
				Elements description = item.select("description");
				rssItem.setDescription(Jsoup.parse(description.text()).text());
				newsDetail.put(rssItem.getLink(), description.text());
				rssItem.setRssTitle(this.rssChannel.getTitle());

				try {
					SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.ENGLISH);
					String sdate = item.selectFirst("pubDate").text();
					Date date = format.parse(sdate);
					rssItem.setPubDate(date);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("[ERROR] DATE ERROR" + this.rssChannel.getRss().getRssName());
				}

				Document descDoc = Jsoup.parse(description.text());
				Element imgelement = descDoc.selectFirst("img");
				if (imgelement != null) {
					rssItem.setImgsrc(imgelement.attr("src"));
				}

				this.rssChannel.addItem(rssItem);
			}

			redisTemplate.opsForValue().set(this.link, this.rssChannel);
			redisTemplate.opsForValue().set(this.rssChannel.getRss().getRssName(), newsDetail);

			List<RssItem> sub = new ArrayList<RssItem>();
			try {
				sub = new ArrayList<RssItem>(this.rssChannel.getItems().subList(0, 3));
			} catch (Exception e) {
				sub = new ArrayList<RssItem>(this.rssChannel.getItems().subList(0, this.rssChannel.getItems().size()));
			}
			this.rssChannel.setItems(sub);
			redisTemplate.opsForValue().set("limit " + this.link, this.rssChannel);

			System.out.println("[PARSE] " + this.rssChannel.getRss().getRssName());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("[ERROR] " + this.rssChannel.getRss().getRssName());
			e.printStackTrace();
		}
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				this.parse();
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
