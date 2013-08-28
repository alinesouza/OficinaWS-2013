package br.iff.campos.pooa20131d.oficina.webservices;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.iff.campos.pooa20131d.oficina.model.controller.ProprietarioPersistence;
import br.iff.campos.pooa20131d.oficina.model.controller.VeiculoPersistence;
import br.iff.campos.pooa20131d.oficina.model.entity.Veiculo;

@Path("/veiculos")
public class VeiculoResource {

	private VeiculoPersistence pveiculo;
	private ProprietarioPersistence pproprietario;

	public VeiculoResource() {
		try {
			InitialContext ctx = new InitialContext();
			pproprietario = (ProprietarioPersistence) ctx
					.lookup("java:global/OficinaEAR/oficinaEJB/ProprietarioPersistence!br.iff.campos.pooa20131d.oficina.model.controller.ProprietarioPersistence");
		} catch (NamingException ne) {
			System.out.println("\n[MyRestService]NamingException: " + ne);
			ne.printStackTrace();
		}
		
		try {
			InitialContext ctx = new InitialContext();
			pveiculo = (VeiculoPersistence) ctx
					.lookup("java:global/OficinaEAR/oficinaEJB/VeiculoPersistence!br.iff.campos.pooa20131d.oficina.model.controller.VeiculoPersistence");
		} catch (NamingException ne) {
			System.out.println("\n[MyRestService]NamingException: " + ne);
			ne.printStackTrace();
		}
	}

	@GET()
	@Produces("text/plain")
	public String sayHello() {
		Date date = new Date();
		return "Hello World! data agora ++++++++++++++++++++=> "
				+ date.toString();
	}

	@GET
	@Path("/{uid}")
	@Produces(MediaType.APPLICATION_XML)
	public Veiculo getVeiculo(@PathParam("uid") String uid) {
		
		return  pveiculo.find(uid);
	}
	
	@DELETE
	@Path("/{uid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteVeiculo(@PathParam("uid") String uid) {
		
		pveiculo.delete(uid);
		return  Response.ok("").build();
	}
	
	@POST
	@Path("/{uid},{nome},{uidp}")
	@Produces(MediaType.APPLICATION_XML)
	public Response addVeiculo(@PathParam("uid") String uid,@PathParam("nome") String nome,@PathParam("uidp") String uidp) {
		
		pveiculo.inserir(uid, 2013, "preta", nome, "12344",
				pproprietario.find(uidp));
		return  Response.ok("").build();
	}
	@PUT
	@Path("/{uid},{nome},{uidp}")
	@Produces(MediaType.APPLICATION_XML)
	public Response updateVeiculo(@PathParam("uid") String uid,@PathParam("nome") String nome,@PathParam("uidp") String uidp) {
		
		pveiculo.update(uid, 2013, "preta", nome, "12344",
				pproprietario.find(uidp));
		return  Response.ok("").build();
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_XML)
	public List<Veiculo> getAll() {

		return pveiculo.findAll();
	}
}
