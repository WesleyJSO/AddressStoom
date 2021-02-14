package br.com.stoomtest.address.strategy;

import br.com.stoomtest.address.DTO.IDTO;
import br.com.stoomtest.address.utils.Result;

public interface IStrategy {
	
	Result execute(IDTO DTO) throws Exception;
}
