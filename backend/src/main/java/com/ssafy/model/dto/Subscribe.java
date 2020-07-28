package com.ssafy.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SUBSCRIBE")
public class Subscribe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="subscribe_id")
	private String subscribeId;
	
	private String subscribeName;
	private long feedId;
	private long userNo;
	private long rssId;

}
