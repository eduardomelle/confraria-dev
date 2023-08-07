package br.com.eduardomelle;

import org.apache.camel.Header;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "http://compra-saga-credito-eduardomelle-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/credito")
@ApplicationScoped
public interface CreditoService {

  @GET
  @Path("newPedidoValor")
  @Produces(MediaType.TEXT_PLAIN)
  public void newPedidoValor(@QueryParam("pedidoId") @Header("pedidoId") Long pedidoId, @QueryParam("valor") @Header("valor") int valor);

  @GET
  @Path("cancelPedidoValor")
  @Produces(MediaType.TEXT_PLAIN)
  public void cancelPedidoValor(@QueryParam("id") @Header("id") Long id);

  @GET
  @Path("getCreditoTotal")
  @Produces(MediaType.TEXT_PLAIN)
  public int getCreditoTotal();

}
