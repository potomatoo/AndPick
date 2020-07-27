package com.ssafy.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RSS")
public class Rss implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rss_no")
	private long rssId;
	@Column(name = "rss_url")
	private String rssUrl;
	@Column(name = "category_id")
	private long categoryId;

	public long getRssId() {
		return rssId;
	}

	public void setRssId(long rssId) {
		this.rssId = rssId;
	}

	public String getRssUrl() {
		return rssUrl;
	}

	public void setRssUrl(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Rss [rssId=" + rssId + ", rssUrl=" + rssUrl + ", categoryId=" + categoryId + "]";
	}
}
