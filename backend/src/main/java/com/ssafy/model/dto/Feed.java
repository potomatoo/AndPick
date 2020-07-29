package com.ssafy.model.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "feed")
public class Feed implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feed_id")
	private long feedId;

	@Column(name = "user_no")
	private long userNo;

	@Column(name = "feed_name")
	private String feedName;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "feed_id")
	private List<Subscribe> subscribeList;

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

	public String getFeedName() {
		return feedName;
	}

	public void setFeedName(String feedName) {
		this.feedName = feedName;
	}

	public List<Subscribe> getSubscribeList() {
		return subscribeList;
	}

	public void setSubscribeList(List<Subscribe> subscribeList) {
		this.subscribeList = subscribeList;
	}

	@Override
	public String toString() {
		return "Feed [feedId=" + feedId + ", userNo=" + userNo + ", feedName=" + feedName + ", subscribeList="
				+ subscribeList + "]";
	}
}
