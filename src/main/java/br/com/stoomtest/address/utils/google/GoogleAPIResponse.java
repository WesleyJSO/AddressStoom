package br.com.stoomtest.address.utils.google;

import java.util.List;

public class GoogleAPIResponse {
	private String status;
	private List<ResultObject> results;
	public List<ResultObject> getResults() {
		return results;
	}

	public void setResults(List<ResultObject> results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}