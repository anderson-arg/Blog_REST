package br.edu.ifpb.si.pd.recurso;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.edu.ifpb.si.pd.dao.NoticiaDAO;
import br.edu.ifpb.si.pd.model.Noticia;

@Path("/noticia")
public class NoticiaRecurso {

	private NoticiaDAO NoticiaDAO;
	
	public NoticiaRecurso() {
		this.NoticiaDAO = new NoticiaDAO();
	}
	
	@GET
	@Path("/{id}")
	public Response getNoticia(@PathParam("id") int id){
		Noticia n = this.NoticiaDAO.findById(id);

		if(n == null || n.equals("")){
			return Response.status(Response.Status.NOT_FOUND).build();
		}else{			
			String html = "<table>"
							+ "<th>ID</th>"
							+ "<th>Autor</th>"
							+ "<th>Titulor</th>"
							+ "<th>Conteudo</th>"
							+ "<th>Data de Publicação</th>"
							+ "<tr>"
								+ "<td>"+n.getId()+"</td>"
								+ "<td>"+n.getAutor()+"</td>"
								+ "<td>"+n.getTitulo()+"</td>"
								+ "<td>"+n.getConteudo()+"</td>"
								+ "<td>"+n.getData().toString()+"</td>"
							+ "</tr>"
						+ "</table>";
			return Response.ok(html, MediaType.TEXT_HTML).build();	
		}
	}

	@PUT
	@Produces("application/json")
	@Path("/atualizar/{id}/{titulo}")
	public Response atualizar(@PathParam("id") int id, @PathParam("titulo") String titulo){
		Noticia n = this.NoticiaDAO.findById(id);
		if(n != null){
			this.NoticiaDAO.begin();
			n.setTitulo(titulo.replaceAll("_", " "));
			this.NoticiaDAO.update(n);
			this.NoticiaDAO.commit();
			return Response.ok(n).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();	
	}

	@POST
    @Consumes(MediaType.APPLICATION_XML)
	public Response add(Noticia n) {
		if(n != null){
			if(this.NoticiaDAO.findById(n.getId()) == null){
				this.NoticiaDAO.begin();
				n.setData(new Date());
				this.NoticiaDAO.persist(n);
				this.NoticiaDAO.commit();
				return Response.status(Response.Status.ACCEPTED).build();
			}else{
				return Response.status(Response.Status.CONFLICT).build();				
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/excluir/{id}")
	public Response delete(@PathParam("id") int id) {
		Noticia n = this.NoticiaDAO.findById(id);
		if(n != null){
			this.NoticiaDAO.begin();
			this.NoticiaDAO.remove(n);
			this.NoticiaDAO.commit();
			return Response.ok(n, MediaType.APPLICATION_XML).build();
		}else{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
}
