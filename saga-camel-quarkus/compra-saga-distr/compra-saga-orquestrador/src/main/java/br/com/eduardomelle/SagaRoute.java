package br.com.eduardomelle;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.SagaPropagation;
import org.apache.camel.saga.CamelSagaService;
import org.apache.camel.saga.InMemorySagaService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class SagaRoute extends RouteBuilder {

    @Inject
    @RestClient
    PedidoService pedidoService;

    @Inject
    @RestClient
    CreditoService creditoService;

    @Override
    public void configure() throws Exception {
        CamelSagaService sagaService = new InMemorySagaService();
        getContext().addService(sagaService);

        // Saga:
        from("direct:saga").messageHistory().saga().propagation(SagaPropagation.REQUIRES_NEW).log("Iniciando a transação")
                .to("direct:newPedido").log("Criando novo pedido com id ${header.id}")
                .to("direct:newPedidoValor").log("Reservando o crédito")
                .to("direct:finaliza").log("Feito!");

        // Pedido Service:
        from("direct:newPedido").messageHistory().saga().propagation(SagaPropagation.MANDATORY)
                .compensation("direct:cancelPedido")
                .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
                .bean(pedidoService, "newPedido").log("Pedido ${body} criado");

        from("direct:cancelPedido")
                .messageHistory().transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
                .bean(pedidoService, "cancelPedido").log("Pedido ${body} cancelado");

        // Crédito Service:
        from("direct:newPedidoValor").messageHistory().saga().propagation(SagaPropagation.MANDATORY)
                .compensation("direct:cancelPedidoValor")
                .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
                .bean(creditoService, "newPedidoValor")
                .log("Crédito do pedido ${header.pedidoId} no valor de ${header.valor} reservado para a saga ${body}");

        from("direct:cancelPedidoValor")
                .messageHistory().transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
                .bean(creditoService, "cancelPedidoValor")
                .log("Crédito do pedido ${header.pedidoId} no valor de ${header.valor} cancelado para a saga ${body}");

        // Finaliza:
        from("direct:finaliza").messageHistory().saga().propagation(SagaPropagation.MANDATORY)
                .choice().end();
    }

}
