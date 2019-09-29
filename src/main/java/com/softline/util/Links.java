package com.softline.util;

public class Links {
	public Links() {
	}

	public Links(Integer id, String source, String target, String name,
			Double weight) {
		super();
		this.id = id;
		this.source = source;
		this.target = target;
		this.name = name;
		this.weight = weight;
	}

	private Integer id;
	private String source;
	private String target;
	private String name;
	private Double weight;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
}
