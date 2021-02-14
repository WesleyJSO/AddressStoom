package br.com.stoomtest.address.utils.google;

import java.util.List;

public class Component {
	private List<String> types;
	private String long_name;

	public String getLong_name() {
		return long_name;
	}

	public void setLong_name(String long_name) {
		this.long_name = long_name;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}
}