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

public class CorsoControllerTest {

	@Mock
	private CorsoMapper corsoMapper;

	@Mock
	private StudenteMapper studenteMapper;

	@InjectMocks
	private CorsoController corsoController;

	private MockMvc mockMvc;

	static Corso corsoProgrammazione = new Corso(Long.valueOf(1), "Programmazione 1", 6, 70, null);
	static Corso corsoMatematica = new Corso(Long.valueOf(2), "Matematica Discreta", 6, 40, null);
	static Corso corsoAnalisi = new Corso(Long.valueOf(3), "Analisi Matematica", 12, 120, null);

	static Studente studenteMarioRossi = new Studente(Long.valueOf(1), "Mario", "Rossi", "ARO76",
			"mariorossi@email.com", null);

	static List<Corso> corsi = new ArrayList<Corso>();
	static List<Corso> corsiMarioRossi = new ArrayList<Corso>();


	// Eseguito una sola volta prima di tutti i test
	@BeforeClass
	public static void init() {
		corsi.add(corsoProgrammazione);
		corsi.add(corsoMatematica);

		corsiMarioRossi.add(corsoProgrammazione);
		corsiMarioRossi.add(corsoMatematica);
		corsiMarioRossi.add(corsoAnalisi);

	}

	// Eseguito all'inizio di ogni test
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(corsoController).build();
	}

	@Test
	public void whenGetAllCorsi_thenControlFlowCorrect() throws Exception {
		when(corsoMapper.getAllCorsi()).thenReturn(corsi);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/corsi")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].id", is(1)));

		Mockito.verify(corsoMapper, Mockito.times(1)).getAllCorsi();
	}

	@Test
	public void whenGetCorsoById_thenControlFlowCorrect() throws Exception {

		when(corsoMapper.getCorsoById(corsoProgrammazione.getId())).thenReturn(corsoProgrammazione);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/corsi/" + corsoProgrammazione.getId().toString()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id", is(1)));

		Mockito.verify(corsoMapper, Mockito.times(1)).getCorsoById(corsoProgrammazione.getId());
	}

	@Test
	public void whenGetAllCorsiByStudenteId_thenControlFlowCorrect() throws Exception {
		when(studenteMapper.getStudenteById(studenteMarioRossi.getId())).thenReturn(studenteMarioRossi);
		when(corsoMapper.getAllCorsiByStudenteId(studenteMarioRossi.getId())).thenReturn(corsiMarioRossi);

		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/studenti/" + studenteMarioRossi.getId().toString() + "/corsi"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[2].id", is(3)));

		Mockito.verify(studenteMapper, Mockito.times(1)).getStudenteById(studenteMarioRossi.getId());
		Mockito.verify(corsoMapper, Mockito.times(1)).getAllCorsiByStudenteId(studenteMarioRossi.getId());
	}
	
	@Test
    public void whenSaveCorso_thenControlFlowCorrect() throws Exception {
        
        String requestBody = "{\"id\": 47, \"nome\": \"Corso Nuovo\", \"cfu\": 9, \"ore\": 120, \"studenti\": []}";

        this.mockMvc
        .perform(MockMvcRequestBuilders.post("/corsi").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(corsoMapper, Mockito.times(1)).saveCorso(Mockito.any(Corso.class));
    }
	
	@Test
    public void whenUpdateCorsoById_thenControlFlowCorrect() throws Exception {
		Corso corsoAggiornato = new Corso(Long.valueOf(1), "Programmazione 1 Update", 12, 150, null);
		
        when(corsoMapper.updateCorsoById(corsoAggiornato)).thenReturn(corsoAggiornato);
        
        when(corsoMapper.getCorsoById(corsoAggiornato.getId())).thenReturn(corsoAggiornato);
        
        String requestBody = "{\"id\": 1, \"nome\": \"Programmazione 1 Update\", \"cfu\": 12, \"ore\": 150, \"studenti\": []}";

        this.mockMvc
        .perform(
                MockMvcRequestBuilders.put("/corsi/{corsoId}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        
        
        Mockito.verify(corsoMapper, Mockito.times(1)).getCorsoById(Long.valueOf(1));
        
        Mockito.verify(corsoMapper, Mockito.times(1)).updateCorsoById(Mockito.any(Corso.class));

    }
	
	 @Test
	    public void whenDeleteCorsoById_thenControlFlowCorrect() throws Exception {
		 
		 when(corsoMapper.getCorsoById(corsoProgrammazione.getId())).thenReturn(corsoProgrammazione);

	        mockMvc.perform(
	                MockMvcRequestBuilders.delete("/corsi/{id}", "1"))
	        .andExpect(MockMvcResultMatchers.status().isOk());

	        Mockito.verify(corsoMapper, Mockito.times(1))
	                .deleteCorsoById(Long.valueOf(1));
	    }
	
	
//	@Test
//	public void whenAddCorsoToStudente_thenControlFlowCorrect() throws Exception {
//
//		when(studenteMapper.getStudenteById(studenteMarioRossi.getId())).thenReturn(studenteMarioRossi);
//		
//		this.mockMvc
//				.perform(MockMvcRequestBuilders.post("/corsi").contentType(MediaType.APPLICATION_JSON)
//						.content(corsoNuovo))
//				.andExpect(MockMvcResultMatchers.status().isOk());
//	}

}
