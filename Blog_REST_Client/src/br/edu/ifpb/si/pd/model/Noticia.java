package br.edu.ifpb.si.pd.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Noticia implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String autor;
	private String titulo;
	private String conteudo;
	private Date data;
	
	public Noticia(){}

	public Noticia(String autor, String titulo, String conteudo) {
		super();
		this.autor = autor;
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.data = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Noticia [id=" + id + ", autor=" + autor + ", titulo=" + titulo + ", conteudo=" + conteudo + ", data="
				+ data + "]";
	}
	
}
