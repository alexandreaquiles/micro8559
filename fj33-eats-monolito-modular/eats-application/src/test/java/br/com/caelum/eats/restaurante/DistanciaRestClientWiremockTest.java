package br.com.caelum.eats.restaurante;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.eats.admin.TipoDeCozinha;

@AutoConfigureStubRunner(ids="br.com.caelum:eats-distancia-service:+:stubs:9992", stubsMode=StubsMode.LOCAL)
@RunWith(SpringRunner.class)
@SpringBootTest
public class DistanciaRestClientWiremockTest {
	
	private DistanciaRestClient distanciaRestClient;
	
	@Before
	public void antes() {
		RestTemplate restTemplate = new RestTemplate();
		String distanciaServiceUrl = "http://localhost:9992";
		distanciaRestClient = new DistanciaRestClient(restTemplate, distanciaServiceUrl);
	}
	
	@Test
	public void deveAvisarQueNovoRestauranteFoiAprovado() {
		
		TipoDeCozinha tipoDeCozinha = new TipoDeCozinha();
		tipoDeCozinha.setId(1L);

		
		Restaurante restauranteAprovado = new Restaurante();
		restauranteAprovado.setId(2L);
		restauranteAprovado.setCep("715000-000");
		restauranteAprovado.setTipoDeCozinha(tipoDeCozinha);
		
		
		distanciaRestClient.novoRestauranteAprovado(restauranteAprovado);
		
	}

}

















