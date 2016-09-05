package br.edu.ifpb.si.pd.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.thoughtworks.xstream.XStream;

import br.edu.ifpb.si.pd.model.Noticia;

@ManagedBean(name="NoticiaBean")
@ViewScoped
public class NoticiaBean extends GenericBean{
	private Noticia noticia;
	private HttpClient http;

	@PostConstruct
	public void init(){
		this.http = new HttpClient();
		this.reset();
	}
	
	public void reset(){
		this.noticia = new Noticia();		
	}
	
	public void criar(){
		XStream xml = new XStream();
		xml.alias("noticia", Noticia.class);
		String x = "<?xml version=\"1.0\"?>"; 
		x += xml.toXML(this.noticia);
		
		try {
			StringRequestEntity xmlParser = new StringRequestEntity(x, "application/xml", "UTF-8");
			PostMethod post = new PostMethod("http://localhost:9090/blog-server/noticia");
			post.setRequestEntity(xmlParser);
			this.http.executeMethod(post);
			this.addInfoMessage(post.getStatusCode()+" - "+post.getStatusText());
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(){
        try {
        	DeleteMethod delete = new DeleteMethod("http://localhost:9090/blog-server/noticia/excluir/"+this.noticia.getId());
			http.executeMethod(delete);
			this.addInfoMessage(delete.getStatusCode()+" - "+delete.getStatusText());
			if(!delete.getResponseBodyAsString().isEmpty())
				this.addInfoMessage(delete.getResponseBodyAsString());
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void pesquisar(){
        try {
        	GetMethod get = new GetMethod("http://localhost:9090/blog-server/noticia/"+this.noticia.getId());
			http.executeMethod(get);
			this.addInfoMessage(get.getStatusCode()+" - "+get.getStatusText());
			if(!get.getResponseBodyAsString().isEmpty())
				this.addInfoMessage(get.getResponseBodyAsString());
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(){
        try {
        	PutMethod put = new PutMethod("http://localhost:9090/blog-server/noticia/atualizar/"+this.noticia.getId()+"/"+this.noticia.getTitulo().replaceAll(" ", "_"));
			http.executeMethod(put);
			this.addInfoMessage(put.getStatusCode()+" - "+put.getStatusText());
			if(!put.getResponseBodyAsString().isEmpty()){
				//Gson g = new Gson();
				//String json = g.toJson(put.getResponseBodyAsString()); 
				this.addInfoMessage(put.getResponseBodyAsString());
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}
}
