package it.eagleprojects.progettoverificamybatis4.model;

public class CorsiStudentiIscrizioni {

	long corsoId;

	long studenteId;

	public CorsiStudentiIscrizioni() {

	}

	public CorsiStudentiIscrizioni(long corsoId, long studenteId) {
		super();
		this.corsoId = corsoId;
		this.studenteId = studenteId;
	}

	public long getCorsoId() {
		return corsoId;
	}

	public void setCorsoId(long corsoId) {
		this.corsoId = corsoId;
	}

	public long getStudenteId() {
		return studenteId;
	}

	public void setStudenteId(long studenteId) {
		this.studenteId = studenteId;
	}

}
