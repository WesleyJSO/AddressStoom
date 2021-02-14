package address;

import static address.utils.JsonParser.build;
import static address.utils.JsonParser.jsonToList;
import static address.utils.JsonParser.jsonToObject;
import static address.utils.JsonParser.objectToJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.stoomtest.address.AddressApplication;
import br.com.stoomtest.address.DTO.impl.AddressDTO;
import br.com.stoomtest.address.entity.impl.Address;

@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest(classes = AddressApplication.class, webEnvironment = WebEnvironment.MOCK)
public class AddressApplicationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("Find all address")
	void getAllAddressTest() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/endereco"))
			.andReturn();
		
		Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		List<Address> list = jsonToList(result.getResponse().getContentAsString(), Address.class);
		Assertions.assertEquals(1, list.get(0).getId());
		
	}
	
	@Test
	@DisplayName("Insert one address")
	void postOneAddressTest() throws Exception {
		MvcResult result = mockMvc
					.perform(build(post("/endereco/")
						.content(objectToJson(createAddress()))))
					.andReturn();
		
		Assertions.assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
		Assertions.assertEquals("Endereço criado com sucesso", result.getResponse().getContentAsString());
	}
	
	@Test
	@DisplayName("Update one address")
	void putOneAddressTest() throws Exception {
		MvcResult result = mockMvc
				.perform(build(put("/endereco/1")
					.content(objectToJson(createAddress()))))
				.andReturn();
		
		Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		Assertions.assertEquals("Endereço atualizado com sucesso", result.getResponse().getContentAsString());
	}

	@Test
	@DisplayName("Find one address")
	void getOneAddressTest() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/endereco/1"))
				.andReturn();
		
		Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		Assertions.assertEquals(1, jsonToObject(result.getResponse().getContentAsString(), Address.class).getId());
	}
	
	@Test
	@DisplayName("Delete one address")
	void deleteOneAddressTest() throws Exception {
		postOneAddressTest();
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/endereco/2"))
				.andReturn();
		
		Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		Assertions.assertEquals("Endereço excluído com sucesso", result.getResponse().getContentAsString());
	}
	

	private AddressDTO createAddress() {
		AddressDTO dto = new AddressDTO();
		dto.setNumber(100);
		dto.setStreetName("Praça da Sé");
		dto.setNeighbourhood("anithing");
		dto.setCountry("Brazil");
		dto.setZipcode("01001-000");
		dto.setCity("São Paulo");
		dto.setState("SP");
		return dto;
	}
}
