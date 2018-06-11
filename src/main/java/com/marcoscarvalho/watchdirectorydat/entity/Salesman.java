package com.marcoscarvalho.watchdirectorydat.entity;

public class Salesman {

	private String name;
	private String document;
	private String area;

	public Salesman() {

	}

	public Salesman(String line) {
		try {
			String[] quebra = line.split("ç");
			this.name = quebra[2];
			this.document = quebra[1];
			this.area = quebra[3];
		} catch (Exception e) {
			return;
		}
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("name: ");
		str.append(name);
		str.append(", document: ");
		str.append(document);
		str.append(", area: ");
		str.append(area);
		return str.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
