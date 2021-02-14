package br.com.stoomtest.address.utils.google;

import java.util.List;

public class ResultObject {
	private Geometry geometry;
	private List<Component> address_components;

	public List<Component> getAddress_components() {
		return address_components;
	}

	public void setAddress_components(List<Component> address_components) {
		this.address_components = address_components;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
}
