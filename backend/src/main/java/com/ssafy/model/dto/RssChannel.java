package com.ssafy.model.dto;

import java.util.ArrayList;
import java.util.List;

public class RssChannel {
	// 필수요소
	private String title;
	private String link;
	// 선택 요소
	private String category;

	private List<RssItem> items;

	public RssChannel() {
		// TODO Auto-generated constructor stub
		this.items = new ArrayList<RssItem>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void addItem(RssItem item) {
		this.items.add(item);
	}

	@Override
	public String toString() {
		String result = "RssChannel [title=" + title + ", link=" + link + ", category=" + category + "]";

		for (RssItem item : this.items) {
			result += "\n\t" + item.toString();
		}

		return result;
	}

}
