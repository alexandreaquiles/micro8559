package br.com.caelum.eats.pagamento;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import br.com.caelum.eats.pagamento.AmqpPagamentoConfig.PagamentoSource;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificadorPagamentoConfirmado {

	private PagamentoSource pagamentoSource;
	
	public void notificaPagamentoConfirmado(Pagamento pagamento) {
		PagamentoConfirmado pagamentoConfirmado = new PagamentoConfirmado(pagamento.getId(), pagamento.getPedidoId());
		Message<?> message = MessageBuilder.withPayload(pagamentoConfirmado).build();
		pagamentoSource.pagamentosConfirmados().send(message);
	}
	
}
