package br.com.caelum.eats.distancia;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.caelum.eats.distancia.mongo.RestauranteMongo;
import br.com.caelum.eats.distancia.mongo.RestauranteMongoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class RestaurantesController {
	
	private RestauranteMongoRepository repo;

	@PostMapping("/restaurantes")
	public ResponseEntity<RestauranteMongo> adiciona(@RequestBody RestauranteMongo restaurante, 
				UriComponentsBuilder uriBuilder) {
		log.info("Inserindo restaurante: " + restaurante);
		RestauranteMongo salvo = repo.insert(restaurante);
		URI location = uriBuilder.path("/restaurantes/{id}").buildAndExpand(salvo.getId()).toUri();
		return ResponseEntity.created(location).contentType(MediaType.APPLICATION_JSON).body(salvo);
	}
	
	@PutMapping("/restaurantes/{id}")
	public RestauranteMongo atualiza(@PathVariable Long id, @RequestBody RestauranteMongo restaurante) {
		throw new RuntimeException();
//		if (!repo.existsById(id)) {
//			throw new ResourceNotFoundException();
//		}
//		log.info("Atualizando restaurante: " + restaurante);
//		return repo.save(restaurante);
	}
	
}
