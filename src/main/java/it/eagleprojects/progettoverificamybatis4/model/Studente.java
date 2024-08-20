package it.eagleprojects.progettoverificamybatis4.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Studente {
	private Long id;
	private String nome;
	private String cognome;
	private String email;
	private String matricola;
	private List<Corso> corsi = new ArrayList<>();

	public Studente() {

	}

	public Studente(Long id, String nome, String cognome, String email, String matricola, List<Corso> corsi) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.matricola = matricola;
		this.corsi = corsi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public List<Corso> getCorsi() {
		return corsi;
	}

	public void setCorsi(List<Corso> corsi) {
		this.corsi = corsi;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cognome, corsi, email, id, matricola, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		Studente other = (Studente) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(corsi, other.corsi)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id) && matricola == other.matricola
				&& Objects.equals(nome, other.nome);
	}

}
