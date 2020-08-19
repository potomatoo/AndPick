package com.ssafy.model.dto;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "news_detail")
public class NewsDetail {
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
		return "NewsDetail [id=" + id + ", content=" + content + "]";
	}

}
