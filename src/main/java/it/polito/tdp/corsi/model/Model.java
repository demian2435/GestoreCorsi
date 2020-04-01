package it.polito.tdp.corsi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {
	private CorsoDAO corsoDAO;

	public Model() {
		corsoDAO = new CorsoDAO();
	}

	public List<Corso> getCorsiByPeriodo(Integer pd) {
		return corsoDAO.getCorsiByPeriodo(pd);
	}

	public Map<Corso, Integer> getIscrittiByPeriodo(Integer pd) {
		return corsoDAO.getIscrittiByPeriodo(pd);
	}

	public List<Studente> getStudenteByCorso(String corso) {
		return corsoDAO.getStudenteByCorso(corso);
	}

	public boolean esisteCorso(String corso) {
		return corsoDAO.esisteCorso(corso);
	}

	public Map<String, Integer> getDivisione(String corso) {
		/*
		 * List<Studente> studenti = corsoDAO.getStudenteByCorso(corso); Map<String,
		 * Integer> statistiche = new HashMap<String, Integer>();
		 * 
		 * for (Studente s : studenti) { if (statistiche.containsKey(s.getCds())) {
		 * statistiche.put(s.getCds(), statistiche.get(s.getCds()) + 1); } else {
		 * statistiche.put(s.getCds(), 1); } } return statistiche;
		 */
		return corsoDAO.divisioneStudenti(new Corso(corso, null, null, null));
	}

}
