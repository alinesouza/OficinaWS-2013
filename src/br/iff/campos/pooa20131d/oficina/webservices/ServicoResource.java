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

import br.iff.campos.pooa20131d.oficina.model.controller.ServicoPersistence;
import br.iff.campos.pooa20131d.oficina.model.entity.Servico;

@Path("/servicos")
public class ServicoResource {

	private ServicoPersistence pservico;

	public ServicoResource() {
		try {
			InitialContext ctx = new InitialContext();
			pservico = (ServicoPersistence) ctx
					.lookup("java:global/OficinaEAR/oficinaEJB/ServicoPersistence!br.iff.campos.pooa20131d.oficina.model.controller.ServicoPersistence");
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
	public Servico getServico(@PathParam("uid") String uid) {
		
		return  pservico.find(uid);
	}
	
	@DELETE
	@Path("/{uid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteServico(@PathParam("uid") String uid) {
		
		pservico.delete(uid);
		return  Response.ok("").build();
	}
	
	@POST
	@Path("/{uid},{descricao}")
	@Produces(MediaType.APPLICATION_XML)
	public Response addServico(@PathParam("uid") String uid,@PathParam("descricao") String descricao) {
		
		pservico.inserir(uid,  descricao, 10, 10);
		return  Response.ok("").build();
	}
	@PUT
	@Path("/{uid},{descricao}")
	@Produces(MediaType.APPLICATION_XML)
	public Response updateServico(@PathParam("uid") String uid,@PathParam("descricao") String descricao) {
		
		pservico.update(uid,  descricao, 10, 10);
		return  Response.ok("").build();
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_XML)
	public List<Servico> getAll() {

		return pservico.findAll();
	}
}
