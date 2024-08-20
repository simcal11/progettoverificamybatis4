package it.eagleprojects.progettoverificamybatis4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.eagleprojects.progettoverificamybatis4.dao.CorsoMapper;
import it.eagleprojects.progettoverificamybatis4.dao.StudenteMapper;
import it.eagleprojects.progettoverificamybatis4.model.Studente;

@Controller
public class StudenteController {

	@Autowired
	StudenteMapper studenteMapper;

	@Autowired
	CorsoMapper corsoMapper;

	// Metodi GET
	/* _____________________________________________________________________ */

	@RequestMapping(value = "/studenti", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Studente>> getAllStudenti() {
		return new ResponseEntity<>(studenteMapper.getAllStudenti(), HttpStatus.OK);
	}

	@RequestMapping(value = "/studenti/{studenteId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Studente getStudenteById(@PathVariable("studenteId") Long studenteId) throws Exception {
		Studente studente = studenteMapper.getStudenteById(studenteId);

		if (studente == null) {
			throw new Exception("Non esiste uno Studente con id = " + studenteId);
		}
		return studente;
	}

	@RequestMapping(value = "/corsi/{corsoId}/studenti", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Studente> getAllStudentiByCorsoId(@PathVariable("corsoId") Long corsoId)
			throws Exception {
		if (corsoMapper.getCorsoById(corsoId) == null) {
			throw new Exception("Non esiste un Corso con id = " + corsoId);
		} else {
			return studenteMapper.getAllStudentiByCorsoId(corsoId);
		}
	}

	// Metodi POST
	/* _____________________________________________________________________ */

	@RequestMapping(value = "/studenti", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Studente saveStudente(@RequestBody Studente studente) {
		return studenteMapper.saveStudente(studente);
	}

	@RequestMapping(value = "/corsi/{corsoId}/studenti", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody void addStudenteToCorso(@PathVariable(value = "corsoId") Long corsoId,
			@RequestBody Studente studente) throws Exception {

		if (corsoMapper.getCorsoById(corsoId) == null) {
			throw new Exception("Non esiste un Corso con id = " + corsoId);
		}

		if (studente.getId() == null) {
			throw new Exception("Non è stato specifica l'id dello studente da aggiungere " + studente.getId());
		}

		if (studenteMapper.getStudenteById(studente.getId()) == null) {
			throw new Exception("Non esiste uno Studente con id = " + studente.getId());
		}

		studenteMapper.addStudenteToCorso(corsoId, studente);
	}

	// Metodi PUT
	/* _____________________________________________________________________ */

	@RequestMapping(value = "/studenti/{studenteId}", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody Studente updateStudenteById(@PathVariable("studenteId") Long studenteId,
			@RequestBody Studente studenteRequest) throws Exception {

		Studente studenteDaAggiornare = studenteMapper.getStudenteById(studenteId);

		if (studenteDaAggiornare == null) {
			throw new Exception("Non esiste uno Studente con id = " + studenteId);
		}

		studenteRequest.setId(studenteDaAggiornare.getId());

		if (studenteRequest.getNome() == null) {
			studenteRequest.setNome(studenteDaAggiornare.getNome());
		}

		if (studenteRequest.getCognome() == null) {
			studenteRequest.setCognome(studenteDaAggiornare.getCognome());
		}

		if (studenteRequest.getEmail() == null) {
			studenteRequest.setEmail(studenteDaAggiornare.getEmail());
		}

		if (studenteRequest.getMatricola() == null) {
			studenteRequest.setMatricola(studenteDaAggiornare.getMatricola());
		}

		return studenteMapper.updateStudenteById(studenteRequest);
	}

	// Metodi DELETE
	/* _____________________________________________________________________ */

	@RequestMapping(value = "/studenti/{studenteId}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteStudenteById(@PathVariable("studenteId") Long studenteId) throws Exception {

		if (studenteMapper.getStudenteById(studenteId) == null) {
			throw new Exception("Non esiste uno Studente con id = " + studenteId);
		}

		studenteMapper.deleteStudenteById(studenteId);
	}

	@RequestMapping(value = "/studenti", method = RequestMethod.DELETE)
	public @ResponseBody void deleteAllStudenti() throws Exception {
		studenteMapper.deleteAllStudenti();
	}

	@RequestMapping(value = "/corsi/{corsoId}/studenti", method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody void deleteStudenteFromCorso(@PathVariable(value = "corsoId") Long corsoId,
			@RequestBody Studente studente) throws Exception {

		if (corsoMapper.getCorsoById(corsoId) == null) {
			throw new Exception("Non esiste un Corso con id = " + corsoId);
		}

		if (studente.getId() == null) {
			throw new Exception("Non è stato specifica l'id dello studente da eliminare " + studente.getId());
		}

		if (studenteMapper.getStudenteById(studente.getId()) == null) {
			throw new Exception("Non esiste uno Studente con id = " + studente.getId());
		}

		studenteMapper.deleteStudenteFromCorso(corsoId, studente);
	}

}
