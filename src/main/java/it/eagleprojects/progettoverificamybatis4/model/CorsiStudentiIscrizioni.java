package it.eagleprojects.progettoverificamybatis4.model;

import java.util.Objects;

public class CorsiStudentiIscrizioni {

	Long corsoId;

	Long studenteId;

	public CorsiStudentiIscrizioni() {

	}

	public CorsiStudentiIscrizioni(Long corsoId, Long studenteId) {
		super();
		this.corsoId = corsoId;
		this.studenteId = studenteId;
	}

	public Long getCorsoId() {
		return corsoId;
	}

	public void setCorsoId(Long corsoId) {
		this.corsoId = corsoId;
	}

	public Long getStudenteId() {
		return studenteId;
	}

	public void setStudenteId(Long studenteId) {
		this.studenteId = studenteId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(corsoId, studenteId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CorsiStudentiIscrizioni other = (CorsiStudentiIscrizioni) obj;
		return Objects.equals(corsoId, other.corsoId) && Objects.equals(studenteId, other.studenteId);
	}
	
	
	

}
