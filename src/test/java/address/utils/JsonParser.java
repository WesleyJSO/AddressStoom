package address.utils;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

import java.io.IOException;
import java.util.List;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
	public static<T> List<T> jsonToList(String s, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {		  
		return new ObjectMapper()
        		.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
        		.readValue(s, new ObjectMapper().getTypeFactory().constructCollectionType(List.class, clazz));
	}
	
	public static String objectToJson(Object value) throws JsonProcessingException {
		return new ObjectMapper()
				.writeValueAsString(value);
	}

	public static <T> T jsonToObject(String s, Class<T> classOf) throws JsonParseException, JsonMappingException, IOException {		
		return new ObjectMapper().readValue(s, classOf);
	}
	
	@SuppressWarnings("deprecation")
	public static MockHttpServletRequestBuilder build(MockHttpServletRequestBuilder mockRequestBuilder) throws Exception {
		mockRequestBuilder
			.contentType(APPLICATION_JSON_UTF8)
			.header("Origin", "*");
		return mockRequestBuilder;
	}
}
