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
import br.iff.campos.pooa20131d.oficina.model.entity.Proprietario;

@Path("/proprietarios")
public class ProprietarioResource {

	private ProprietarioPersistence pproprietario;

	public ProprietarioResource() {
		try {
			InitialContext ctx = new InitialContext();
			pproprietario = (ProprietarioPersistence) ctx
					.lookup("java:global/OficinaEAR/oficinaEJB/ProprietarioPersistence!br.iff.campos.pooa20131d.oficina.model.controller.ProprietarioPersistence");
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
	public Proprietario getProprietario(@PathParam("uid") String uid) {
		
		return  pproprietario.find(uid);
	}
	
	@DELETE
	@Path("/{uid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteProprietario(@PathParam("uid") String uid) {
		
		pproprietario.delete(uid);
		return  Response.ok("").build();
	}
	
	@POST
	@Path("/{uid},{nome}")
	@Produces(MediaType.APPLICATION_XML)
	public Response addProprietario(@PathParam("uid") String uid,@PathParam("nome") String nome) {
		
		pproprietario.inserir(uid, "bairro", "cidade",
				"complemento", "cpf", 20, nome, "rua", "uf");
		return  Response.ok("").build();
	}
	@PUT
	@Path("/{uid},{nome}")
	@Produces(MediaType.APPLICATION_XML)
	public Response updateProprietario(@PathParam("uid") String uid,@PathParam("nome") String nome) {
		
		pproprietario.update(uid, "bairro", "cidade",
				"complemento", "cpf", 20, nome, "rua", "uf");
		return  Response.ok("").build();
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_XML)
	public List<Proprietario> getAll() {

		return pproprietario.findAll();
	}
}
