package com.ssafy.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post_tag")
public class PostTag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_tag_id")
	private long postTagId;
	@Column(name = "post_id")
	private long postId;
	@Column(name = "tag_name")
	private String tagName;

	public long getPostTagId() {
		return postTagId;
	}

	public void setPostTagId(long postTagId) {
		this.postTagId = postTagId;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Override
	public String toString() {
		return "PostTag [postTagId=" + postTagId + ", postId=" + postId + ", tagName=" + tagName + "]";
	}

}
