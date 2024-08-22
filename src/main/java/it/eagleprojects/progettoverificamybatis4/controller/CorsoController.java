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
import it.eagleprojects.progettoverificamybatis4.model.Corso;
import it.eagleprojects.progettoverificamybatis4.model.Studente;

@Controller
public class CorsoController {

	@Autowired
	CorsoMapper corsoMapper;

	@Autowired
	StudenteMapper studenteMapper;

	// Metodi GET
	/* _____________________________________________________________________ */

	@RequestMapping(value = "/corsi", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<List<Corso>> getAllCorsi() {
		return new ResponseEntity<>(corsoMapper.getAllCorsi(), HttpStatus.OK);
	}

	@RequestMapping(value = "/corsi/{corsoId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<Corso> getCorsoId(@PathVariable("corsoId") long corsoId) throws Exception {
		Corso corso = corsoMapper.getCorsoById(corsoId);

		if (corso == null) {
			throw new Exception("Non esiste un Corso con id = " + corsoId);
		}
		return new ResponseEntity<>(corso, HttpStatus.OK);
	}

	@RequestMapping(value = "/studenti/{studenteId}/corsi", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<List<Corso>> getAllCorsiByStudenteId(@PathVariable("studenteId") long studenteId)
			throws Exception {
		if (studenteMapper.getStudenteById(studenteId) == null) {
			throw new Exception("Non esiste uno Studente con id = " + studenteId);
		} else {
			return new ResponseEntity<>(corsoMapper.getAllCorsiByStudenteId(studenteId), HttpStatus.OK);
		}
	}

	// Metodi POST
	/* _____________________________________________________________________ */

	@RequestMapping(value = "/corsi", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Corso> saveCorso(@RequestBody Corso corso) {
		corsoMapper.saveCorso(corso);
		return new ResponseEntity<>(corso, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/studenti/{studenteId}/corsi", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Corso>  addCorsoToStudente(@PathVariable(value = "studenteId") long studenteId,
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
		
		return new ResponseEntity<>(corso, HttpStatus.OK);
	}

	// Metodi PUT
	/* _____________________________________________________________________ */

	@RequestMapping(value = "/corsi/{corsoId}", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseEntity<Corso> updateCorsoById(@PathVariable("corsoId") Long corsoId, @RequestBody Corso corsoRequest)
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
		
		if (corsoRequest.getStudenti() == null) {
			corsoRequest.setStudenti(corsoDaAggiornare.getStudenti());
		}
		
		return new ResponseEntity<>(corsoMapper.updateCorsoById(corsoRequest), HttpStatus.NO_CONTENT);
	}

	// Metodi DELETE
	/* _____________________________________________________________________ */

	@RequestMapping(value = "/corsi", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Corso>  deleteAllCorsi() throws Exception {
		corsoMapper.deleteAllCorsi();
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/corsi/{corsoId}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Corso> deleteCorsoById(@PathVariable("corsoId") Long corsoId) throws Exception {
		if (corsoMapper.getCorsoById(corsoId) == null) {
			throw new Exception("Non esiste un Corso con id = " + corsoId);
		}

		corsoMapper.deleteCorsoById(corsoId);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/studenti/{studenteId}/corsi", method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Corso>  deleteCorsoFromStudente(@PathVariable(value = "studenteId") Long studenteId,
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
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
//	@RequestMapping(value = "/studenti/{studenteId}/corsi", method = RequestMethod.DELETE, produces = "application/json")
//	public @ResponseBody void deleteAllCorsiByStudenteId(@PathVariable("studenteId") long studenteId)
//			throws Exception {
//		if (studenteMapper.getStudenteById(studenteId) == null) {
//			throw new Exception("Non esiste uno Studente con id = " + studenteId);
//		} else {
//			corsoMapper.deleteAllCorsiByStudenteId(studenteId);
//		}
//	}

}
