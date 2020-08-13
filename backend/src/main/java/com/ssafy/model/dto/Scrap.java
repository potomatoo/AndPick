package com.ssafy.model.dto;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "scrap")
public class Scrap {
	@Id
	private String id;
	private String scrap;

	public String getId() {
		return id;

	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScrap() {
		return scrap;
	}

	public void setScrap(String scrap) {
		this.scrap = scrap;
	}

	@Override
	public String toString() {
		return "Scrap [id=" + id + ", scrap=" + scrap + "]";
	}

}
