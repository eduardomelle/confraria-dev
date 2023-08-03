package br.com.eduardomelle;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("credito")
public class CreditoResource {

  @Inject
  CreditoService creditoService;

  @Path("newPedidoValor")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response newPedidoValor(Long pedidoId, int valor) {
    this.creditoService.newPedidoValor(pedidoId, valor);
    return null;
  }

  @Path("cancelPedidoValor")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response cancelPedidoValor(Long id) {
    this.creditoService.cancelPedidoValor(id);
    return null;
  }

  @Path("getCreditoTotal")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response getCreditoTotal() {
    int creditoTotal = this.creditoService.getCreditoTotal();
    return null;
  }

}
