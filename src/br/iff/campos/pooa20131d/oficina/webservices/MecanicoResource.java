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

import br.iff.campos.pooa20131d.oficina.model.controller.MecanicoPersistence;
import br.iff.campos.pooa20131d.oficina.model.entity.Mecanico;

@Path("/mecanicos")
public class MecanicoResource {

	private MecanicoPersistence pmecanico;

	public MecanicoResource() {
		try {
			InitialContext ctx = new InitialContext();
			pmecanico = (MecanicoPersistence) ctx
					.lookup("java:global/OficinaEAR/oficinaEJB/MecanicoPersistence!br.iff.campos.pooa20131d.oficina.model.controller.MecanicoPersistence");
		} catch (NamingException ne) {
			System.out.println("\n[MyRestService]NamingException: " + ne);
			ne.printStackTrace();
		}
	}

	@GET()
	@Produces("text/plain")
	public String sayHello() {
		Date date = new Date();
		return "Hello World! Mecanicos ++++++++++++++++++++=> "
				+ date.toString();
	}

	@GET
	@Path("/{uid}")
	@Produces(MediaType.APPLICATION_XML)
	public Mecanico getMecanico(@PathParam("uid") String uid) {
		
		return  pmecanico.find(uid);
	}
	
	@DELETE
	@Path("/{uid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteMecanico(@PathParam("uid") String uid) {
		
		pmecanico.delete(uid);
		return  Response.ok("").build();
	}
	
	@POST
	@Path("/{uid},{nome}")
	@Produces(MediaType.APPLICATION_XML)
	public Response addMecanico(@PathParam("uid") String uid,@PathParam("nome") String nome) {
		
		pmecanico.inserir(uid, "especialidade", nome, "telefone");
		return  Response.ok("").build();
	}
	@PUT
	@Path("/{uid},{nome}")
	@Produces(MediaType.APPLICATION_XML)
	public Response updateMecanico(@PathParam("uid") String uid,@PathParam("nome") String nome) {
		
		pmecanico.update(uid, "especialidade", nome, "telefone");
		return  Response.ok("").build();
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_XML)
	public List<Mecanico> getAll() {

		return pmecanico.findAll();
	}
}
