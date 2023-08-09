package br.com.eduardomelle;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Path("/hello")
public class GreetingResource {

    private static int i = 0;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws UnknownHostException {
        // return "Hello from RESTEasy Reactive";
        return "Hello, Confraria Dev! V2: " + i++ + " - " + InetAddress.getLocalHost().getHostName() + "\n";
    }

}