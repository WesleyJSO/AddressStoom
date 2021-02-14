package br.com.stoomtest.address.strategy.impl;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.stoomtest.address.DTO.IDTO;
import br.com.stoomtest.address.DTO.impl.AddressDTO;
import br.com.stoomtest.address.adapter.impl.AddressAdapter;
import br.com.stoomtest.address.entity.impl.Address;
import br.com.stoomtest.address.strategy.IStrategy;
import br.com.stoomtest.address.utils.Result;
import br.com.stoomtest.address.utils.google.GoogleAPIResponse;

/**
 * Make a request to google API to find latitude and longitude in case of those values are not informed.
 * @author Wesley
 *
 */
@Component
public class FindLocationWithGoogleAPI implements IStrategy {

	private final String GOOGLE_API = "https://maps.googleapis.com/maps/api/geocode/json?address=";
	private final String GOOGLE_API_KEY = "&key=AIzaSyCj0cY2yEvVfYhAaTz3-P2MW-YRKmhz5Uw";
	
	@Override
	public Result execute(IDTO DTO) throws RestClientException {
		
		Result result = new Result();
		AddressDTO addressDTO = (AddressDTO) DTO;
		
		if(addressDTO.getLatitude() == null && addressDTO.getLongitude() == null) {
			String url = GOOGLE_API 
					+ (addressDTO.getNumber() != null ? addressDTO.getNumber() + "+" : "") 
					+ add(addressDTO.getStreetName())  
					+ add(addressDTO.getState())
					+ add(addressDTO.getCountry())  
					+ GOOGLE_API_KEY;
			
			ResponseEntity<GoogleAPIResponse> response = new RestTemplate().getForEntity(url, GoogleAPIResponse.class);
			if(validAddressFound(response)) {
				addressDTO = new AddressAdapter().adapt(addressDTO, response.getBody().getResults().get(0));
			}
		}
		result.setEntity(new Address(addressDTO));
		return result;
	}

	private boolean validAddressFound(ResponseEntity<GoogleAPIResponse> response) {
		return response.getStatusCode().is2xxSuccessful()
				&& response.getBody().getResults().size() > 0
				&& response.getBody().getResults().get(0).getGeometry() != null
				&& response.getBody().getResults().get(0).getGeometry().getLocation() != null
				&& response.getBody().getResults().get(0).getGeometry().getLocation().getLat() != null
				&& response.getBody().getResults().get(0).getGeometry().getLocation().getLng() != null;
	}

	private String add(String value) {
		return Optional.ofNullable(value).map(e -> e + ",").orElse("");
	}
}
