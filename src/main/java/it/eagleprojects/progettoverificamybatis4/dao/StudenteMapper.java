package it.eagleprojects.progettoverificamybatis4.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import it.eagleprojects.progettoverificamybatis4.dao.utils.MyBatisUtil;
import it.eagleprojects.progettoverificamybatis4.model.Studente;


@Repository
public class StudenteMapper {
	
	/**
	 * Metodo che ritorna tutte le righe della tabella studenti
	 * @return una <code> List </code> di Studente
	 */
	public List<Studente> getAllStudenti(){
		
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Studente> studentiList = session.selectList("getAllStudenti"); //Qui il nome è quello definito nel file mapper xml
		session.commit();
		session.close();
		return studentiList;
	}
	
	/**
	 *  Metodo che salva una istanza di <code> Studente </code> nel database sottostante 
	 * @param e l'istanza da persistere
	 */
	public void saveStudente(Studente studente) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("insertStudente", studente);
		session.commit();
		session.close();
	}
}
