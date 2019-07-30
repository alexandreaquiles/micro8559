package br.com.caelum.apigateway;

import org.springframework.stereotype.Component;

@Component
public class RestauranteRestClientFallback implements RestauranteRestClient {

	@Override 
	public RestauranteDto porId(Long id) {
		RestauranteDto restaurante = new RestauranteDto();
		// IDEAL: pegar de um cache
		restaurante.setId(id);
		return restaurante;
	}

}
