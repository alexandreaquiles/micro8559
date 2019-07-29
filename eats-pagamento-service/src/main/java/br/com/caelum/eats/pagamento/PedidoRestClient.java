package br.com.caelum.eats.pagamento;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="monolito")
public interface PedidoRestClient {
	
	@PutMapping("/pedidos/{pedidoId}/pago")
	public void avisaQueFoiPago(@PathVariable Long pedidoId);

}
