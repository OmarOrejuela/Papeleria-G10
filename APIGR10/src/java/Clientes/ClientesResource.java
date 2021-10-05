package Clientes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ClientesResource {

    public ClientesResource() {
    }

    ServicioClientes servicio = new ServicioClientes();
    
    @GET    
    public ArrayList<ModeloClientes> getClientes(){
    return servicio.getClientes();}

    @Path("/{ClienteId}")
    @GET
    public ModeloClientes getCliente(@PathParam("ClienteId") int id) {
        return servicio.getCliente(id);}
    
    @POST
    public ModeloClientes addCliente(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ModeloClientes cliente = gson.fromJson(JSON, ModeloClientes.class);
        return servicio.addCliente(cliente);
    }
}