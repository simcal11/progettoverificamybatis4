package it.eagleprojects.progettoverificamybatis4.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import it.eagleprojects.progettoverificamybatis4.dao.utils.MyBatisUtil;
import it.eagleprojects.progettoverificamybatis4.model.Corso;



@Repository
public class CorsoMapper {
	
	/**
	 * Metodo che ritorna tutte le righe della tabella corsi
	 * @return una <code> List </code> di Corso
	 */
	public List<Corso> getAllCorsi(){
		
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Corso> corsiList = session.selectList("getAllCorsi"); //Qui il nome è quello definito nel file mapper xml
		session.commit();
		session.close();
		return corsiList;
	}
	
	/**
	 *  Metodo che salva una istanza di <code> Corso </code> nel database sottostante 
	 * @param e l'istanza da persistere
	 */
	public void saveStudente(Corso corso) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("insertCorso", corso);
		session.commit();
		session.close();
	}
}
