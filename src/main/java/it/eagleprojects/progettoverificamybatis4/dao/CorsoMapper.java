package it.eagleprojects.progettoverificamybatis4.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import it.eagleprojects.progettoverificamybatis4.dao.utils.MyBatisUtil;
import it.eagleprojects.progettoverificamybatis4.model.CorsiStudentiIscrizioni;
import it.eagleprojects.progettoverificamybatis4.model.Corso;



@Repository
public class CorsoMapper {

	/**
	 * Metodo che ritorna tutte le righe della tabella corsi
	 * @return una <code> List </code> di Corso
	 */
	public List<Corso> getAllCorsi(){

		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Corso> listaCorsi = session.selectList("getAllCorsi"); //Qui il nome è quello definito nel file mapper xml
		session.commit();
		session.close();
		return listaCorsi;
	}


	/**
	 * Metodo che ritorna una riga corrispondente al corsoId specificato come parametro
	 * @param corsoId è l'id del corso che si vuole ottenere
	 * @return una istanza di Corso
	 */
	public Corso getCorsoById(@Param("corsoId") long corsoId) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Corso corso = session.selectOne("getCorsoById", corsoId); //Qui il nome è quello definito nel file mapper xml
		session.commit();
		session.close();
		return corso;
	}



	/**
	 * Metodo che ritorna tutti i corsi associati allo studenteId specificato come parametro
	 * @param studenteId è l'id dello studente
	 * @return una <code> List </code> di Corso
	 */
	public List<Corso> getAllCorsiByStudenteId(@Param("studenteId") long studenteId){

		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Corso> corsiList = session.selectList("getAllCorsiByStudenteId", studenteId); //Qui il nome è quello definito nel file mapper xml
		session.commit();
		session.close();
		return corsiList;
	}



	/**
	 *  Metodo che salva una istanza di <code> Corso </code> nel database sottostante
	 * @param corso è l'istanza da persistere
	 */
	public Corso saveCorso(Corso corso) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("insertCorso", corso);
		session.commit();
		session.close();
		return corso;
	}

	/**
	 * Metodo che aggiunge una istanza di <code> Corso </code> allo Studente specificato
	 * @param studenteId l'id dello studente
	 * @param corso è l'istanza da aggiungere
	 * 
	 */
	public void addCorsoToStudente(Long studenteId, Corso corso) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		CorsiStudentiIscrizioni csi = new CorsiStudentiIscrizioni(corso.getId(), studenteId);
		session.insert("insertCorsoToStudente", csi);
		session.commit();
		session.close();
	}



	/**
	 * Metodo che aggiorna una istanza di <code> Corso </code> nel database sottostante
	 * @return una istanza di Corso (quella appena aggiornata)
	 * @param corso è l'istanza da aggiornare
	 */
	public Corso updateCorsoById(@Param("corso") Corso corso) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Integer righeModificate = session.update("updateCorsoById", corso); //Qui il nome è quello definito nel file mapper xml
		session.commit();
		session.close();
		return corso;
	}

	/**
	 * Metodo che elimina una istanza di <code> Corso </code> nel database sottostante
	 * @param corsoId è l'id del corso da eliminare
	 * @return void
	 */
	public void deleteCorsoById(@Param("corsoId") Long corsoId) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		int righeModificate = session.delete("deleteCorsoById", corsoId); //Qui il nome è quello definito nel file mapper xml
		session.commit();
		session.close();
	}

	/**
	 * Metodo che elimina tutte le istanze <code> Corso </code> nel database sottostante
	 * @return void
	 */
	public void deleteAllCorsi() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Integer righeModificate = session.delete("deleteAllCorsi"); //Qui il nome è quello definito nel file mapper xml
		session.commit();
		session.close();
	}

	/**
	 *  Metodo che elimina una istanza di <code> Corso </code> dallo Studente specificato
	 * @param studenteId è l'id dello studente
	 * @param corso è l'istanza da disassociare
	 * @return void
	 */
	public void deleteCorsoFromStudente(Long studenteId, Corso corso) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		CorsiStudentiIscrizioni csi = new CorsiStudentiIscrizioni(corso.getId(), studenteId);
		session.delete("deleteCorsoFromStudente", csi);
		session.commit();
		session.close();
	}

}
