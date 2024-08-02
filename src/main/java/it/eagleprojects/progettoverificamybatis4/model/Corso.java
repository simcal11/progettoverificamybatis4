package it.eagleprojects.progettoverificamybatis4.model;

import java.util.Objects;

public class Corso {
	private long id;
	private String nome;
	private int cfu;
	private int ore;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCfu() {
		return cfu;
	}
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	public int getOre() {
		return ore;
	}
	public void setOre(int ore) {
		this.ore = ore;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cfu, id, nome, ore);
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
		return cfu == other.cfu && id == other.id && Objects.equals(nome, other.nome) && ore == other.ore;
	}
	
	
	
}
