package it.eagleprojects.progettoverificamybatis4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.eagleprojects.progettoverificamybatis4.dao.StudenteMapper;
import it.eagleprojects.progettoverificamybatis4.model.Studente;




@Controller
@RequestMapping("/studente")
public class StudenteController {
	
	@Autowired
	StudenteMapper studenteMapper;

	
	@RequestMapping(method = RequestMethod.GET, produces  = "application/json")
	public @ResponseBody List<Studente> getAllStudenti() {
		return studenteMapper.getAllStudenti();
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody void save(@RequestBody Studente studente) {
		studenteMapper.saveStudente(studente);
	}
	
}
