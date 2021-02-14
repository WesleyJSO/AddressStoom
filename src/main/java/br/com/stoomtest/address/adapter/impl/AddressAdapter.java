package br.com.stoomtest.address.adapter.impl;

import br.com.stoomtest.address.DTO.impl.AddressDTO;
import br.com.stoomtest.address.adapter.IAdapter;
import br.com.stoomtest.address.utils.google.Component;
import br.com.stoomtest.address.utils.google.ResultObject;

public class AddressAdapter implements IAdapter<AddressDTO> {

	@Override
	public AddressDTO adapt(AddressDTO address, ResultObject obj) {
		for (Component component : obj.getAddress_components()) {
			for (String s : component.getTypes()) {
				switch (s) {
				case "route":
					address.setStreetName(component.getLong_name());
					break;
				case "administrative_area_level_2":
					address.setCity(component.getLong_name());
					break;
				case "administrative_area_level_1":
					address.setState(component.getLong_name());
					break;
				case "country":
					address.setCountry(component.getLong_name());
					break;
				case "postal_code":
					address.setZipcode(component.getLong_name());
					break;
				}
			}
		}
		address.setLatitude(obj.getGeometry().getLocation().getLat());
		address.setLongitude(obj.getGeometry().getLocation().getLng());
		return address;
	}
}
