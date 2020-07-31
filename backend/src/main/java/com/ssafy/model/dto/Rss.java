package com.ssafy.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rss")
public class Rss implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rss_id")
	private long rssId;
	@Column(name = "rss_url")
	private String rssUrl;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@Column(name="rss_name")
	private String rssName;

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getRssName() {
		return rssName;
	}

	public void setRssName(String rssName) {
		this.rssName = rssName;
	}

	@Override
	public String toString() {
		return "Rss [rssId=" + rssId + ", rssUrl=" + rssUrl + ", category=" + category + ", rssName=" + rssName + "]";
	}


}
