package br.com.stoomtest.address.adapter;

import br.com.stoomtest.address.utils.google.ResultObject;

public interface IAdapter<T> {

	T adapt(T entity, ResultObject resultObject);
}
