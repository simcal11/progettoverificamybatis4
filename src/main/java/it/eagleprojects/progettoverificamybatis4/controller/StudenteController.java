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
	public @ResponseBody ResponseEntity<Studente> getStudenteById(@PathVariable("studenteId") Long studenteId) throws Exception {
		Studente studente = studenteMapper.getStudenteById(studenteId);

		if (studente == null) {
			throw new Exception("Non esiste uno Studente con id = " + studenteId);
		}
		return new ResponseEntity<>(studente, HttpStatus.OK);
	}

	@RequestMapping(value = "/corsi/{corsoId}/studenti", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<List<Studente>> getAllStudentiByCorsoId(@PathVariable("corsoId") Long corsoId)
			throws Exception {
		if (corsoMapper.getCorsoById(corsoId) == null) {
			throw new Exception("Non esiste un Corso con id = " + corsoId);
		} else {
			return new ResponseEntity<>(studenteMapper.getAllStudentiByCorsoId(corsoId), HttpStatus.OK);
		}
	}

	// Metodi POST
	/* _____________________________________________________________________ */

	@RequestMapping(value = "/studenti", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Studente> saveStudente(@RequestBody Studente studente) {
		return new ResponseEntity<>( studenteMapper.saveStudente(studente),  HttpStatus.CREATED);
	}

	@RequestMapping(value = "/corsi/{corsoId}/studenti", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Studente> addStudenteToCorso(@PathVariable(value = "corsoId") Long corsoId,
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
		
		return new ResponseEntity<>(studente, HttpStatus.OK);
		
	}

	// Metodi PUT
	/* _____________________________________________________________________ */

	@RequestMapping(value = "/studenti/{studenteId}", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseEntity<Studente> updateStudenteById(@PathVariable("studenteId") Long studenteId,
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

		return new ResponseEntity<>(studenteMapper.updateStudenteById(studenteRequest), HttpStatus.NO_CONTENT);
	}

	// Metodi DELETE
	/* _____________________________________________________________________ */

	@RequestMapping(value = "/studenti/{studenteId}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Studente> deleteStudenteById(@PathVariable("studenteId") Long studenteId) throws Exception {

		if (studenteMapper.getStudenteById(studenteId) == null) {
			throw new Exception("Non esiste uno Studente con id = " + studenteId);
		}
		
		studenteMapper.deleteStudenteById(studenteId);

		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		
	}

	@RequestMapping(value = "/studenti", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Studente>  deleteAllStudenti() throws Exception {
		studenteMapper.deleteAllStudenti();
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/corsi/{corsoId}/studenti", method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Studente>  deleteStudenteFromCorso(@PathVariable(value = "corsoId") Long corsoId,
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
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

}
