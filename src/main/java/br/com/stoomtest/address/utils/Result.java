package br.com.stoomtest.address.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.com.stoomtest.address.entity.IEntity;
import br.com.stoomtest.address.entity.impl.Address;

public class Result {

	private String KEY = "DEFAULT_ENTITY";
	private String message;
	private boolean error = false;
	private Map<String, IEntity> entities = new HashMap<>();
	
	public void error(String string) {
		message = string;
		error = true;
	}
	
	public boolean hasError() {
		return error;
	}
	
	public String getMessage() {
		return message;
	}

	public void setEntity(Address address) {
		entities.put(KEY, address);
	}
	public Optional<IEntity> getEntity() {
		return Optional.ofNullable(entities.get(KEY));
	}
}
