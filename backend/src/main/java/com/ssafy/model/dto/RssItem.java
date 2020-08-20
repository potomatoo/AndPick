package com.ssafy.model.dto;

import java.io.Serializable;
import java.util.Date;

public class RssItem implements Serializable {
	private String title;
	private String description;
	private String link;

	private Date pubDate;
	private String rssTitle;
	private String subscribeName;
	private String imgsrc;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getRssTitle() {
		return rssTitle;
	}

	public void setRssTitle(String rssTitle) {
		this.rssTitle = rssTitle;
	}

	public String getSubscribeName() {
		return subscribeName;
	}

	public void setSubscribeName(String subscribeName) {
		this.subscribeName = subscribeName;
	}

	public String getImgsrc() {
		return imgsrc;
	}

	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}

	@Override
	public String toString() {
		return "RssItem [title=" + title + ", description=" + description + ", link=" + link + ", pubDate=" + pubDate
				+ ", rssTitle=" + rssTitle + ", subscribeName=" + subscribeName + ", imgsrc=" + imgsrc + "]";
	}

}
