package it.eagleprojects.progettoverificamybatis4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.eagleprojects.progettoverificamybatis4.dao.CorsoMapper;
import it.eagleprojects.progettoverificamybatis4.dao.StudenteMapper;
import it.eagleprojects.progettoverificamybatis4.model.Corso;

@Controller
public class CorsoController {

	@Autowired
	CorsoMapper corsoMapper;

	@Autowired
	StudenteMapper studenteMapper;

	// Metodi GET
	/* _____________________________________________________________________ */

	@RequestMapping(value = "/corsi", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Corso> getAllCorsi() {
		return corsoMapper.getAllCorsi();
	}

	@RequestMapping(value = "/corsi/{corsoId}", method = RequestMethod.GET)
	public @ResponseBody Corso getCorsoId(@PathVariable("corsoId") long corsoId) throws Exception {
		Corso corso = corsoMapper.getCorsoById(corsoId);

		if (corso == null) {
			throw new Exception("Non esiste un Corso con id = " + corsoId);
		}
		return corso;
	}

	@RequestMapping(value = "/studenti/{studenteId}/corsi", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Corso> getAllCorsiByStudenteId(@PathVariable("studenteId") long studenteId)
			throws Exception {
		if (studenteMapper.getStudenteById(studenteId) == null) {
			throw new Exception("Non esiste uno Studente con id = " + studenteId);
		} else {
			return corsoMapper.getAllCorsiByStudenteId(studenteId);
		}
	}

	// Metodi POST
	/* _____________________________________________________________________ */

	@RequestMapping(value = "/corsi", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Corso saveCorso(@RequestBody Corso corso) {
		return corsoMapper.saveCorso(corso);
	}

	@RequestMapping(value = "/studenti/{studenteId}/corsi", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody void addCorsoToStudente(@PathVariable(value = "studenteId") long studenteId,
			@RequestBody Corso corso) throws Exception {
		if (studenteMapper.getStudenteById(studenteId) == null) {
			throw new Exception("Non esiste uno Studente con id = " + studenteId);
		}

		if (corso.getId() == null) {
			throw new Exception("Non è stato specifica l'id del corso da aggiungere " + corso.getId());
		}

		if (corsoMapper.getCorsoById(corso.getId()) == null) {
			throw new Exception("Non esiste un Corso con id = " + corso.getId());
		}

		corsoMapper.addCorsoToStudente(studenteId, corso);
	}

	// Metodi PUT
	/* _____________________________________________________________________ */

	@RequestMapping(value = "/corsi/{corsoId}", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody Corso updateCorsoById(@PathVariable("corsoId") long corsoId, @RequestBody Corso corsoRequest)
			throws Exception {

		Corso corsoDaAggiornare = corsoMapper.getCorsoById(corsoId);

		if (corsoDaAggiornare == null) {
			throw new Exception("Non esiste un Corso con id = " + corsoId);
		}

		corsoRequest.setId(corsoDaAggiornare.getId());

		if (corsoRequest.getNome() == null) {
			corsoRequest.setNome(corsoDaAggiornare.getNome());
		}

		if (corsoRequest.getCfu() == null) {
			corsoRequest.setCfu(corsoDaAggiornare.getCfu());
		}

		if (corsoRequest.getOre() == null) {
			corsoRequest.setOre(corsoDaAggiornare.getOre());
		}
		return corsoMapper.updateCorsoById(corsoRequest);
	}

	// Metodi DELETE
	/* _____________________________________________________________________ */

	@RequestMapping(value = "/corsi", method = RequestMethod.DELETE)
	public @ResponseBody void deleteAllCorsi() throws Exception {
		corsoMapper.deleteAllCorsi();
	}

	@RequestMapping(value = "/corsi/{corsoId}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteCorso(@PathVariable("corsoId") Long corsoId) throws Exception {
		if (corsoMapper.getCorsoById(corsoId) == null) {
			throw new Exception("Non esiste un Corso con id = " + corsoId);
		}

		corsoMapper.deleteCorsoById(corsoId);
	}

	@RequestMapping(value = "/studenti/{studenteId}/corsi", method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody void deleteCorsoFromStudente(@PathVariable(value = "studenteId") Long studenteId,
			@RequestBody Corso corso) throws Exception {

		if (studenteMapper.getStudenteById(studenteId) == null) {
			throw new Exception("Non esiste uno Studente con id = " + studenteId);
		}

		if (corso.getId() == null) {
			throw new Exception("Non è stato specifica l'id del Corso da eliminare " + corso.getId());
		}

		if (corsoMapper.getCorsoById(corso.getId()) == null) {
			throw new Exception("Non esiste un Corso con id = " + corso.getId());
		}

		corsoMapper.deleteCorsoFromStudente(studenteId, corso);
	}

}
