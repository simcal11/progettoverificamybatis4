package it.eagleprojects.progettoverificamybatis4.model;

import java.util.List;
import java.util.Objects;

public class Corso {
	private Long id;
	private String nome;
	private Integer cfu;
	private Integer ore;

	private List<Studente> studenti;

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

	public Integer getCfu() {
		return cfu;
	}

	public void setCfu(Integer cfu) {
		this.cfu = cfu;
	}

	public Integer getOre() {
		return ore;
	}

	public void setOre(Integer ore) {
		this.ore = ore;
	}

	public List<Studente> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Studente> studenti) {
		this.studenti = studenti;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cfu, id, nome, ore, studenti);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		return Objects.equals(cfu, other.cfu) && Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(ore, other.ore) && Objects.equals(studenti, other.studenti);
	}

	

}
