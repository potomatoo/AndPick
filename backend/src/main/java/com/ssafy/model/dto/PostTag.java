package com.ssafy.model.dto;

import javax.annotation.Generated;
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
	@Column(name = "tag_id")
	private long tagId;

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

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	@Override
	public String toString() {
		return "PostTag [postTagId=" + postTagId + ", postId=" + postId + ", tagId=" + tagId + "]";
	}

}
