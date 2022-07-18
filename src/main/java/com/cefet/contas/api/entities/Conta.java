package com.cefet.contas.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.validation.constraints.NotEmpty;

import java.time.Instant;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "conta")
public class Conta implements Serializable{

	private static final long serialVersionUID = -4347892548614073481L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao", nullable = false)
	@NotEmpty(message = "Descrição é obrigatória.")
	@Length(min = 3, max = 200, message = "Descrição deve conter entre 3 e 200 caracteres.")
	private String descricao;
	
	@Column(name = "valor", nullable = false)
	private double  valor;
	
	@Column(name = "dataVencimento", nullable = false)
	private Instant data;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idTipo", nullable= false) 
	private Tipo tipo;
	
	@Column(name = "situacao", nullable = false)
	private boolean situacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}	

	public Instant getData() {
		return data;
	}

	public void setData(Instant data) {
		this.data = data;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}	
	
	
	
	
	
}
