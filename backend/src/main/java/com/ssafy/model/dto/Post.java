package com.ssafy.model.dto;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private long postId;
	@Column(name = "user_no")
	private long userNo;
	@Column(name = "post_dir_id")
	private long postDirId;
	@Column(name = "post_title")
	private String postTitle;
	@Column(name = "post_content")
	private String postContent;
	@Column(name = "post_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date postDate;
	@Column(name = "post_favorite")
	private boolean postFavorite;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "post_id")
	private List<PostTag> tagList;

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public long getPostDirId() {
		return postDirId;
	}

	public void setPostDirId(long postDirId) {
		this.postDirId = postDirId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public boolean isPostFavorite() {
		return postFavorite;
	}

	public void setPostFavorite(boolean postFavorite) {
		this.postFavorite = postFavorite;
	}

	public List<PostTag> getTagList() {
		return tagList;
	}

	public void setTagList(List<PostTag> tagList) {
		this.tagList = tagList;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", userNo=" + userNo + ", postDirId=" + postDirId + ", postTitle=" + postTitle
				+ ", postContent=" + postContent + ", postDate=" + postDate + ", postFavorite=" + postFavorite
				+ ", tagList=" + tagList + "]";
	}

}
