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

import br.iff.campos.pooa20131d.oficina.model.controller.OrdemservicoPersistence;
import br.iff.campos.pooa20131d.oficina.model.controller.VeiculoPersistence;
import br.iff.campos.pooa20131d.oficina.model.entity.Ordemservico;

@Path("/ordemServicos")
public class OrdemServicoResource {
    private VeiculoPersistence pveiculo;
	private OrdemservicoPersistence pordemServico;

	public OrdemServicoResource() {
		try {
			InitialContext ctx = new InitialContext();
			pordemServico = (OrdemservicoPersistence) ctx
					.lookup("java:global/OficinaEAR/oficinaEJB/OrdemServicoPersistence!br.iff.campos.pooa20131d.oficina.model.controller.OrdemServicoPersistence");
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
	public Ordemservico getOrdemServico(@PathParam("uid") String uid) {
		
		return  pordemServico.find(uid);
	}
	
	@DELETE
	@Path("/{uid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteOrdemServico(@PathParam("uid") String uid) {
		
		pordemServico.delete(uid);
		return  Response.ok("").build();
	}
	
	@POST
	@Path("/{uid},{nome}")
	@Produces(MediaType.APPLICATION_XML)
	public Response addOrdemServico(@PathParam("uid") String uid,@PathParam("nome") String nome) {
		
		/*pordemServico.inserir(uid, "bairro", "cidade",
				"complemento", "cpf", 20, nome, "rua", "uf");*/
		return  Response.ok("").build();
	}
	@PUT
	@Path("/{uid},{nome}")
	@Produces(MediaType.APPLICATION_XML)
	public Response updateOrdemServico(@PathParam("uid") String uid,@PathParam("nome") String nome) {
		
		/*pordemServico.update(uid, "bairro", "cidade",
				"complemento", "cpf", 20, nome, "rua", "uf");*/
		return  Response.ok("").build();
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_XML)
	public List<Ordemservico> getAll() {

		return pordemServico.findAll();
	}
}
