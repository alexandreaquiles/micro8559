package br.com.caelum.apigateway;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import br.com.caelum.apigateway.AmqpApiGatewayConfig.AtualizacaoPedidoSink;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatusDoPedidoService {

	private SimpMessagingTemplate websocket;
	
	@StreamListener(AtualizacaoPedidoSink.PEDIDO_COM_STATUS_ATUALIZADO)
	public void pedidoAtualizado(PedidoDto pedidoDto) {
		websocket.convertAndSend("/pedidos/"+pedidoDto.getId()+"/status", pedidoDto);
	
		if ("PAGO".equals(pedidoDto.getStatus())) {
			websocket.convertAndSend("/parceiros/restaurantes/"+pedidoDto.getRestaurante().getId()+"/pedidos/pendentes", pedidoDto);
		}
	}
	
}
