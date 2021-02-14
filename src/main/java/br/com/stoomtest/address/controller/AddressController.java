package br.com.stoomtest.address.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.of;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.stoomtest.address.DTO.impl.AddressDTO;
import br.com.stoomtest.address.entity.impl.Address;
import br.com.stoomtest.address.repository.AddressRepository;
import br.com.stoomtest.address.strategy.IStrategy;
import br.com.stoomtest.address.utils.Result;

@RestController
@RequestMapping("/endereco")
public class AddressController {

	@Autowired 
	AddressRepository repository;

	/**
	 * 
	 * @return All {@link Address} entities
	 * in case of exceptions return {@link org.springframework.http.HttpStatus#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} and the exception message
	 */
	@GetMapping
	public @ResponseBody ResponseEntity<?> getAddress() {
		try {
			return ok(repository.findAll());
		} catch(Exception e) {
			return status(INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	/**
	 * 
	 * @param id - The id of the {@link Address} to fetch
	 * @return If it is a valid {@link Address} id, then returns an {@link Address}.<br />
	 * returns {@link org.springframework.http.HttpStatus#NotFound NotFound} if the {@link Address} is doesn't exists
	 * in case of exceptions return {@link org.springframework.http.HttpStatus#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} and the exception message
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getAddress(@NotNull @PathVariable Integer id) {
		try {
			Optional<Address> found = repository.findById(id);
			return found
					.map(ResponseEntity::ok)
					.orElseGet(() -> notFound(id));
		} catch(Exception e) {
			return status(INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param id - The id of the {@link Address} to delete
	 * @return {@link org.springframework.http.HttpStatus#OK OK} if present after deletion, 
	 * or {@link org.springframework.http.HttpStatus#NotFound NotFound} if the address is doesn't exists
	 * in case of exceptions return {@link org.springframework.http.HttpStatus#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} and the exception message 
	 */
	@SuppressWarnings("unchecked")
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> deleteAddress(@NotNull @PathVariable Integer id) {
		try {
			Optional<Address> found = repository.findById(id);
			found.ifPresent(repository::delete);
			return found
					.map(entity -> ResponseEntity.ok().body("Endereço excluído com sucesso"))
					.orElseGet(() -> notFound(id));
		} catch(Exception e) {
			return status(INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}	

	@Autowired
	IStrategy strategy;
	/**
	 * 
	 * @param address - The new {@link Address} to persist
	 * @return If it is a valid {@link Address}, then returns {@link org.springframework.http.HttpStatus#CREATED CREATED} status, and a user friendly message.<br />
	 * In case of exceptions return {@link org.springframework.http.HttpStatus#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} and the exception message
	 */
	@PostMapping
	public @ResponseBody ResponseEntity<?> postAddress(@Valid @RequestBody AddressDTO addressDTO) {
		try {
			Result result = strategy.execute(addressDTO);
			if(result.hasError()) {
				return status(INTERNAL_SERVER_ERROR).body(result.getMessage());				
			}
			Address address = repository.save((Address) result.getEntity().get());
			return created(getURI(address.getId())).body("Endereço criado com sucesso");
		} catch(Exception e) {
			return status(INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param address - An existing {@link Address} to update
	 * @param id - The {@link Address} id to update
	 * @return If it is a valid {@link Address} id, then returns {@link org.springframework.http.HttpStatus#CREATED CREATED} status, 
	 * the API URL to fetch the resource and a user-friendly message.<br />
	 * If it is an invalid {@link Address} id, then returns {@link org.springframework.http.HttpStatus#NOT_FOUND NOT_FOUND} status.<br />
	 * In case of exceptions return {@link org.springframework.http.HttpStatus#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} and the exception message
	 */
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<?> putAddress(@Valid @RequestBody AddressDTO addressDTO, @NotNull @PathVariable Integer id) {
		try {
			Optional<Address> found = repository.findById(id);
			if(!found.isPresent()) {
				return of(found);
			}
			Address address = new Address(addressDTO);
			address.setId(id);
			address = repository.save(address);
			return created(getURI(id)).body("Endereço atualizado com sucesso");
		} catch(Exception e) {
			return status(INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}	
	
	private URI getURI(Integer id) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(id)
				.toUri();
	}

	@SuppressWarnings("rawtypes")
	private  ResponseEntity notFound(Integer id) {
		return status(HttpStatus.NOT_FOUND).body("Endereço não encontrado a partir do id: " + id);
	}
}
