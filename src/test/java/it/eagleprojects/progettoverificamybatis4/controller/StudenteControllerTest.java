package it.eagleprojects.progettoverificamybatis4.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;


import it.eagleprojects.progettoverificamybatis4.dao.CorsoMapper;
import it.eagleprojects.progettoverificamybatis4.dao.StudenteMapper;
import it.eagleprojects.progettoverificamybatis4.model.Corso;
import it.eagleprojects.progettoverificamybatis4.model.Studente;

public class StudenteControllerTest {

	@Mock
	private StudenteMapper studenteMapper;

	@Mock
	private CorsoMapper corsoMapper;

	@InjectMocks
	private StudenteController studenteController;

	private MockMvc mockMvc;

	static Corso corsoProgrammazione = new Corso(Long.valueOf(1), "Programmazione 1", 6, 70, null);
	static Corso corsoMatematica = new Corso(Long.valueOf(2), "Matematica Discreta", 6, 40, null);
	static Corso corsoAnalisi = new Corso(Long.valueOf(3), "Analisi Matematica", 12, 120, null);

	static Studente studenteMarioRossi = new Studente(Long.valueOf(1), "Mario", "Rossi", "ARO76",
			"mariorossi@email.com", null);
	static Studente studenteAliceVerdi = new Studente(Long.valueOf(2), "Alice", "Verdi", "ALVE45",
			"alicerossi@email.com", null);
	static Studente studenteGiacomoBianchi = new Studente(Long.valueOf(3), "Giacomo", "Bianchi", "GIBI22",
			"giacomobianchi@email.com", null);

	static List<Studente> studenti = new ArrayList<Studente>();
	static List<Studente> studentiProgrammazione1 = new ArrayList<Studente>();


	// Eseguito una sola volta prima di tutti i test
	@BeforeClass
	public static void init() {
		studenti.add(studenteMarioRossi);
		studenti.add(studenteAliceVerdi);
		studenti.add(studenteGiacomoBianchi);

		studentiProgrammazione1.add(studenteMarioRossi);
		studentiProgrammazione1.add(studenteAliceVerdi);
		studentiProgrammazione1.add(studenteGiacomoBianchi);

	}

	// Eseguito all'inizio di ogni test
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(studenteController).build();
	}

	@Test
	public void whenGetAllStudenti_thenControlFlowCorrect() throws Exception {
		when(studenteMapper.getAllStudenti()).thenReturn(studenti);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/studenti")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].id", is(1)));

		Mockito.verify(studenteMapper, Mockito.times(1)).getAllStudenti();
	}

	@Test
	public void whenGetStudenteById_thenControlFlowCorrect() throws Exception {

		when(studenteMapper.getStudenteById(studenteMarioRossi.getId())).thenReturn(studenteMarioRossi);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/studenti/" + studenteMarioRossi.getId().toString()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id", is(1)));

		Mockito.verify(studenteMapper, Mockito.times(1)).getStudenteById(studenteMarioRossi.getId());
	}

	@Test
	public void whenGetAllStudentiByCorsoId_thenControlFlowCorrect() throws Exception {
		when(corsoMapper.getCorsoById(corsoProgrammazione.getId())).thenReturn(corsoProgrammazione);
		when(studenteMapper.getAllStudentiByCorsoId(corsoProgrammazione.getId())).thenReturn(studentiProgrammazione1);

		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/corsi/" + corsoProgrammazione.getId().toString() + "/studenti"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[2].id", is(3)));

		Mockito.verify(corsoMapper, Mockito.times(1)).getCorsoById(corsoProgrammazione.getId());
		Mockito.verify(studenteMapper, Mockito.times(1)).getAllStudentiByCorsoId(corsoProgrammazione.getId());
	}
	
	@Test
    public void whenSaveStudente_thenControlFlowCorrect() throws Exception {
        
        String requestBody = "{\"id\": 47, \"nome\": \"Studente Nuovo\", \"cognome\": \"Bianchi\", \"email\": \"nuova@gmail.com\", \"matricola\": \"ABDC43\", \"corsi\": []}";

        this.mockMvc
        .perform(MockMvcRequestBuilders.post("/studenti").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(studenteMapper, Mockito.times(1)).saveStudente(Mockito.any(Studente.class));
    }
	
	@Test
    public void whenUpdateStudenteById_thenControlFlowCorrect() throws Exception {
		Studente studenteAggiornato = new Studente(Long.valueOf(1), "Mario Update", "Rossi", "mariorossi@email.com", "MAR231", null);
		
        when(studenteMapper.updateStudenteById(studenteAggiornato)).thenReturn(studenteAggiornato);
        
        when(studenteMapper.getStudenteById(studenteAggiornato.getId())).thenReturn(studenteAggiornato);
        
        String requestBody = "{\"id\": 1, \"nome\": \"Mario Update\", \"cognome\": \"Rossi\", \"email\": \"mariorossi@email.com\", \"matricola\": \"MAR231\", \"corsi\": []}";


        this.mockMvc
        .perform(
                MockMvcRequestBuilders.put("/studenti/{studenteId}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        
        
        Mockito.verify(studenteMapper, Mockito.times(1)).getStudenteById(Long.valueOf(1));
        
        Mockito.verify(studenteMapper, Mockito.times(1)).updateStudenteById(Mockito.any(Studente.class));

    }
	
	 @Test
	    public void whenDeleteStudenteById_thenControlFlowCorrect() throws Exception {
		 
		 when(studenteMapper.getStudenteById(studenteMarioRossi.getId())).thenReturn(studenteMarioRossi);

	        mockMvc.perform(
	                MockMvcRequestBuilders.delete("/studenti/{id}", "1"))
	        .andExpect(MockMvcResultMatchers.status().isOk());

	        Mockito.verify(studenteMapper, Mockito.times(1))
	                .deleteStudenteById(Long.valueOf(1));
	    }
	
}
