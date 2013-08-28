package br.iff.campos.pooa20131d.oficina.webservices;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

@Path("/ordemservicos")
public class OrdemservicoResource {

	private VeiculoPersistence pveiculo;
	private OrdemservicoPersistence pordemservico;
	DateFormat formatter = new SimpleDateFormat("dd-MM-yy");  
	
	

	public OrdemservicoResource() {
		try {
			InitialContext ctx = new InitialContext();
			pordemservico = (OrdemservicoPersistence) ctx
					.lookup("java:global/OficinaEAR/oficinaEJB/OrdemservicoPersistence!br.iff.campos.pooa20131d.oficina.model.controller.OrdemservicoPersistence");
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
	public Ordemservico getOrdemservico(@PathParam("uid") String uid) {
		
		return  pordemservico.find(uid);
	}
	
	@DELETE
	@Path("/{uid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteOrdemservico(@PathParam("uid") String uid) {
		
		pordemservico.delete(uid);
		return  Response.ok("").build();
	}
	
	@POST
	@Path("/{uid},{data},{uidp}")
	@Produces(MediaType.APPLICATION_XML)
	public Response addOrdemservico(@PathParam("uid") String uid,@PathParam("data") String data,@PathParam("uidp") String uidp) {
		
		try {
			pordemservico.inserir(uid, (Date)formatter.parse(data),
					pveiculo.find(uidp));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  Response.ok("").build();
	}
	@PUT
	@Path("/{uid},{data},{uidp}")
	@Produces(MediaType.APPLICATION_XML)
	public Response updateOrdemservico(@PathParam("uid") String uid,@PathParam("data") String data,@PathParam("uidp") String uidp) {
		
		try {
			pordemservico.update(uid, (Date)formatter.parse(data) ,	pveiculo.find(uidp));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  Response.ok("").build();
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_XML)
	public List<Ordemservico> getAll() {

		return pordemservico.findAll();
	}
}
