package com.test.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "counter")
public class Counter {
	private String collectionName;
	private long sequence;

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
}
