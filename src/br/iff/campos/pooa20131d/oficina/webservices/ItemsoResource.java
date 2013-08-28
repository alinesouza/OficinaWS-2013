package br.iff.campos.pooa20131d.oficina.webservices;

import java.sql.Timestamp;
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

import br.iff.campos.pooa20131d.oficina.model.controller.ItensoPersistence;
import br.iff.campos.pooa20131d.oficina.model.controller.MecanicoPersistence;
import br.iff.campos.pooa20131d.oficina.model.controller.OrdemservicoPersistence;
import br.iff.campos.pooa20131d.oficina.model.controller.ServicoPersistence;
import br.iff.campos.pooa20131d.oficina.model.entity.Itemso;

@Path("/itemsos")
public class ItemsoResource {

	private MecanicoPersistence pmecanico;
	private OrdemservicoPersistence pordemservico;
	private ItensoPersistence pitemso;
	private ServicoPersistence pservico;

	DateFormat dia = new SimpleDateFormat("dd-MM-yy");
	DateFormat hora = new SimpleDateFormat("HH:mm");

	// SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");

	public ItemsoResource() {
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
			pservico = (ServicoPersistence) ctx
					.lookup("java:global/OficinaEAR/oficinaEJB/ServicoPersistence!br.iff.campos.pooa20131d.oficina.model.controller.ServicoPersistence");
		} catch (NamingException ne) {
			System.out.println("\n[MyRestService]NamingException: " + ne);
			ne.printStackTrace();
		}

		try {
			InitialContext ctx = new InitialContext();
			pmecanico = (MecanicoPersistence) ctx
					.lookup("java:global/OficinaEAR/oficinaEJB/MecanicoPersistence!br.iff.campos.pooa20131d.oficina.model.controller.MecanicoPersistence");
		} catch (NamingException ne) {
			System.out.println("\n[MyRestService]NamingException: " + ne);
			ne.printStackTrace();
		}

		try {
			InitialContext ctx = new InitialContext();
			pitemso = (ItensoPersistence) ctx
					.lookup("java:global/OficinaEAR/oficinaEJB/ItensoPersistence!br.iff.campos.pooa20131d.oficina.model.controller.ItensoPersistence");
		} catch (NamingException ne) {
			System.out.println("\n[MyRestService]NamingException: " + ne);
			ne.printStackTrace();
		}
	}

	@GET()
	@Produces("text/plain")
	public String sayHello() {
		Date date = new Date();
		return "Hello ITEM OrdemServico!++++++++++++++++++++=> "
				+ date.toString();
	}

	@GET
	@Path("/{uid}")
	@Produces(MediaType.APPLICATION_XML)
	public Itemso getItemso(@PathParam("uid") String uid) {

		return pitemso.find(uid);
	}

	@DELETE
	@Path("/{uid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteItemso(@PathParam("uid") String uid) {

		pitemso.delete(uid);
		return Response.ok("").build();
	}

	@POST
	@Path("/{uid},{data},{horai},{uids},{uidos},{uidm}")
	@Produces(MediaType.APPLICATION_XML)
	public Response addItemso(@PathParam("uid") String uid,
			@PathParam("data") String data, @PathParam("horai") String horai,
			@PathParam("uids") String uids, @PathParam("uidos") String uidos,
			@PathParam("uidm") String uidm) {

		try {
			pitemso.inserir(uid, (Date) dia.parse(data),
					(Date) dia.parse(data),
					new Timestamp(System.currentTimeMillis()), new Timestamp(
							System.currentTimeMillis()), pservico.find(uids),
					pordemservico.find(uidos), pmecanico.find(uidm));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.ok("").build();
	}

	@PUT
	@Path("/{uid},{data},{horai},{uids},{uidm},{uidm}")
	@Produces(MediaType.APPLICATION_XML)
	public Response updateItemso(@PathParam("uid") String uid,
			@PathParam("data") String data, @PathParam("horai") String horai,
			@PathParam("uids") String uids, @PathParam("uidos") String uidos,
			@PathParam("uidm") String uidm) {

		try {
			pitemso.update(uid, (Date) dia.parse(data), (Date) dia.parse(data),
					new Timestamp(System.currentTimeMillis()), new Timestamp(
							System.currentTimeMillis()), pservico.find(uids),
					pordemservico.find(uidos), pmecanico.find(uidm));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.ok("").build();
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_XML)
	public List<Itemso> getAll() {

		return pitemso.findAll();
	}
}
