package br.com.eduardomelle;

import org.apache.camel.Header;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@RegisterRestClient(baseUri = "http://ac3bf6511ae094dbd9eb5a3b38b7eaba-262391892.us-east-2.elb.amazonaws.com/pedido")
public interface PedidoService {

  @GET
  @Path("newPedido")
  @Produces(MediaType.TEXT_PLAIN)
  public void newPedido(@QueryParam("id") @Header("id") Long id);

  @GET
  @Path("cancelPedido")
  @Produces(MediaType.TEXT_PLAIN)
  public void cancelPedido(@QueryParam("id") @Header("id") Long id);

}
