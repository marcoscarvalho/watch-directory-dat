package com.marcoscarvalho.watchdirectorydat.entity;

public class Client {

	private String name;
	private String document;
	private Long salary;

	public Client() {

	}

	public Client(String line) {
		try {
			String[] quebra = line.split("ç");
			this.name = quebra[2];
			this.document = quebra[1];
			this.salary = Long.valueOf(quebra[3]);
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
		str.append(", salary: ");
		str.append(salary);
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

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

}
