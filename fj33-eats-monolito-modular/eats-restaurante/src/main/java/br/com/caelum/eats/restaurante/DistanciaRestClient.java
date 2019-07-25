package br.com.caelum.eats.restaurante;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DistanciaRestClient {
	
	private RestTemplate restTemplate;
	private String distanciaServiceUrl;
	
	public DistanciaRestClient(RestTemplate restTemplate, 
		@Value("${configuracao.distancia.service.url}") String distanciaServiceUrl) {
		this.restTemplate = restTemplate;
		this.distanciaServiceUrl = distanciaServiceUrl;
	}

	void novoRestauranteAprovado(Restaurante restauranteAprovado) {
		String url = distanciaServiceUrl + "/restaurantes";
		
		RestauranteParaServicoDeDistancia restauranteParaServicoDeDistancia =
					new RestauranteParaServicoDeDistancia(restauranteAprovado);
		
		ResponseEntity<RestauranteParaServicoDeDistancia> responseEntity = 
			restTemplate.postForEntity(url, restauranteParaServicoDeDistancia, RestauranteParaServicoDeDistancia.class);
	
		HttpStatus statusCode = responseEntity.getStatusCode();
		
		if (!HttpStatus.CREATED.equals(statusCode)) {
			throw new RuntimeException("Status diferente do esperado: " + statusCode);
		}
	}
	
	void restauranteAtualizado(Restaurante restauranteAtualizado) {
		String url = distanciaServiceUrl +  "/restaurantes/" + restauranteAtualizado.getId();

		RestauranteParaServicoDeDistancia restauranteParaServicoDeDistancia =
				new RestauranteParaServicoDeDistancia(restauranteAtualizado);

		restTemplate.put(url, restauranteParaServicoDeDistancia);

		
		
	}

}








