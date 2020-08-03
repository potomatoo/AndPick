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
@Table(name = "subscribe")
public class Subscribe implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subscribe_id")
	private long subscribeId;
	@Column(name = "subscribe_name")
	private String subscribeName;

	@Column(name = "feed_id")
	private long feedId;
	@Column(name = "user_no")
	private long userNo;
	@ManyToOne
	@JoinColumn(name = "rss_id")
	private Rss rss;

	public long getSubscribeId() {
		return subscribeId;
	}

	public void setSubscribeId(long subscribeId) {
		this.subscribeId = subscribeId;
	}

	public String getSubscribeName() {
		return subscribeName;
	}

	public void setSubscribeName(String subscribeName) {
		this.subscribeName = subscribeName;
	}

	public long getFeedId() {
		return feedId;
	}

	public void setFeedId(long feedId) {
		this.feedId = feedId;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public Rss getRss() {
		return rss;
	}

	public void setRss(Rss rss) {
		this.rss = rss;
	}

	@Override
	public String toString() {
		return "Subscribe [subscribeId=" + subscribeId + ", subscribeName=" + subscribeName + ", feedId=" + feedId
				+ ", userNo=" + userNo + ", rss=" + rss + "]";
	}

}
