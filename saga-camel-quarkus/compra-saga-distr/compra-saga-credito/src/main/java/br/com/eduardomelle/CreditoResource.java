package br.com.eduardomelle;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("credito")
public class CreditoResource {

  @Inject
  CreditoService creditoService;

  @Path("newPedidoValor")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response newPedidoValor(@QueryParam("pedidoId") Long pedidoId, @QueryParam("valor") int valor) {
    try {
      this.creditoService.newPedidoValor(pedidoId, valor);
      return Response.ok().build();
    } catch (IllegalStateException e) {
      return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
    }
  }

  @Path("cancelPedidoValor")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public void cancelPedidoValor(@QueryParam("id") Long id) {
    this.creditoService.cancelPedidoValor(id);
  }

  @Path("getCreditoTotal")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public int getCreditoTotal() {
    return this.creditoService.getCreditoTotal();
  }

}
