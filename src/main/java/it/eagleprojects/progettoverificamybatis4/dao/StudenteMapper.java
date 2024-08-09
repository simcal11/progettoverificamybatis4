package it.eagleprojects.progettoverificamybatis4.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import it.eagleprojects.progettoverificamybatis4.dao.utils.MyBatisUtil;
import it.eagleprojects.progettoverificamybatis4.model.CorsiStudentiIscrizioni;
import it.eagleprojects.progettoverificamybatis4.model.Studente;


@Repository
public class StudenteMapper {

	/**
	 * Metodo che ritorna tutte le righe della tabella studenti
	 * @return una <code> List </code> di Studente
	 */
	public List<Studente> getAllStudenti(){

		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Studente> listaStudenti = session.selectList("getAllStudenti"); //Qui il nome è quello definito nel file mapper xml
		session.commit();
		session.close();
		return listaStudenti;
	}


	/**
	 * Metodo che ritorna una riga corrispondente allo studenteId specificato come parametro
	 * @param corsoId è l'id dello studente che si vuole ottenere
	 * @return una istanza di Studente
	 */
	public Studente getStudenteById(@Param("studenteId") Long studenteId) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Studente studente = session.selectOne("getStudenteById", studenteId); //Qui il nome è quello definito nel file mapper xml
		session.commit();
		session.close();
		return studente;
	}

	/**
	 * Metodo che ritorna tutti gli Studenti iscritti al corsoId specificato come parametro
	 * @param corsoId è l'id del corso
	 * @return una <code> List </code> di Studente
	 * 
	 */
	public List<Studente> getAllStudentiByCorsoId(@Param("corsoId") Long corsoId){

		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Studente> studentiList = session.selectList("getAllStudentiByCorsoId", corsoId); //Qui il nome è quello definito nel file mapper xml
		session.commit();
		session.close();
		return studentiList;
	}


	/**
	 *  Metodo che salva una istanza di <code> Studente </code> nel database sottostante
	 * @param studente è l'istanza da persistere
	 */
	public Studente saveStudente(Studente studente) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("insertStudente", studente);
		session.commit();
		session.close();
		return studente;
	}

	/**
	 *  Metodo che aggiunge una istanza di <code> Studente </code> al Corso specificato
	 * @param corsoId l'id del corso
	 * @param studente è l'istanza da aggiungere
	 */
	public void addStudenteToCorso(Long corsoId, Studente studente) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		CorsiStudentiIscrizioni csi = new CorsiStudentiIscrizioni(corsoId, studente.getId());
		session.insert("insertStudenteToCorso", csi);
		session.commit();
		session.close();
	}



	/**
	 * Metodo che aggiorna una istanza di <code> Studente </code> nel database sottostante
	 * @return una istanza di Studente (quella appena aggiornata)
	 * @param studente è l'istanza da aggiornare
	 */
	public Studente updateStudenteById(@Param("studente") Studente studente) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		int righeModificate = session.update("updateStudenteById", studente); //Qui il nome è quello definito nel file mapper xml
		session.commit();
		session.close();
		return studente;
	}

	/**
	 * Metodo che elimina una istanza di <code> Studente </code> nel database sottostante
	 * @param studenteId è l'id dello Studente da eliminare
	 * @return void
	 */
	public void deleteStudenteById(@Param("studenteId") Long studenteId) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Integer righeModificate = session.delete("deleteStudenteById", studenteId); //Qui il nome è quello definito nel file mapper xml
		session.commit();
		session.close();
	}

	/**
	 * Metodo che elimina tutte le istanze <code> Studente </code> nel database sottostante
	 * @return void
	 */
	public void deleteAllStudenti() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Integer righeModificate = session.delete("deleteAllStudenti"); //Qui il nome è quello definito nel file mapper xml
		session.commit();
		session.close();
	}

	/**
	 * Metodo che elimina una istanza di <code> Studente </code> dal Corso specificato
	 * @param corsoId è l'id del corso
	 * @param studente è l'istanza da disassociare
	 * @return void
	 */
	public void deleteStudenteFromCorso(Long corsoId, Studente studente) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		CorsiStudentiIscrizioni csi = new CorsiStudentiIscrizioni(corsoId, studente.getId());
		session.delete("deleteStudenteFromCorso", csi);
		session.commit();
		session.close();
	}
}
