package com.ssafy.model.dto;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "post_detail")
public class PostDetail {
	@Id
	private String id;
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "PostDetail [id=" + id + ", content=" + content + "]";
	}

}
