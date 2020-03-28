package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;

	public Model() {
		corsoDAO = new CorsoDAO();
		studenteDAO = new StudenteDAO();
	}

	public List<Corso> getCorsiByPeriodo(Integer pd) {
		return corsoDAO.getCorsiByPeriodo(pd);
	}

	public Map<Corso, Integer> getIscrittiByPeriodo(Integer pd) {
		return corsoDAO.getIscrittiByPeriodo(pd);
	}
	
	public List<Studente> getStudenteByCorso(String corso) {
		return studenteDAO.getStudenteByCorso(corso);
	}

}
