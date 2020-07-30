package com.ssafy.model.dto;

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
@Table(name = "post_dir")
public class PostDir {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_dir_id")
	private long postDirId;
	@Column(name = "user_no")
	private long userNo;
	@Column(name = "post_dir_name")
	private String postDirName;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_dir_id")
	private List<Post> postList;

	public long getPostDirId() {
		return postDirId;
	}

	public void setPostDirId(long postDirId) {
		this.postDirId = postDirId;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public String getPostDirName() {
		return postDirName;
	}

	public void setPostDirName(String postDirName) {
		this.postDirName = postDirName;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	@Override
	public String toString() {
		return "PostDir [postDirId=" + postDirId + ", userNo=" + userNo + ", postDirName=" + postDirName + "]";
	}

}
