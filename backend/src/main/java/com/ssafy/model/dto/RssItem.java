package com.ssafy.model.dto;

import java.io.Serializable;
import java.util.Date;

public class RssItem implements Serializable {
	// 필수 요소
	private String title;
	private String description;
	private String link;
	// 선택 요소
	private Date pubDate;
	// 식별용
	private String rssTitle;
	private String subscribeName;

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

	@Override
	public String toString() {
		return "RssItem [title=" + title + ", description=" + description + ", link=" + link + ", pubDate=" + pubDate
				+ ", rssTitle=" + rssTitle + ", subscribeName=" + subscribeName + "]";
	}

}
