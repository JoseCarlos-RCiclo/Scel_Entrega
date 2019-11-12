package com.fatec.scel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;


@Entity(name = "Aluno")
public class Aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NaturalId
	@Column(nullable = false, length = 13)
	@NotEmpty(message = "O RA do aluno deve ser preenchido")
	private String ra;
	@Column(nullable = false, length = 100)
	@NotEmpty(message = "O nome do aluno deve ser preenchido")
	private String nome;
	@Column(nullable = false)
	@NotNull(message = "E-mail invalido")
	@Email(message = "Digite um e-mail válido")
	private String email;
	
	public Aluno() {
	}

	public Aluno(String i, String t, String a) {
		this.ra = i;
		this.nome = t;
		this.email = a;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
