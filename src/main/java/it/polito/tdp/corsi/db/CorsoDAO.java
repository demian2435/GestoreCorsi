package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Studente;

public class CorsoDAO {

	public List<Corso> getCorsiByPeriodo(Integer pd) {
		List<Corso> result = new ArrayList<>();
		String sql = "SELECT * FROM corso WHERE pd=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"),
						rs.getInt("pd"));
				result.add(c);
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println("Errore in getCorsiByPeriodo");
			throw new RuntimeException(e);
		}
		return result;
	}

	public Map<Corso, Integer> getIscrittiByPeriodo(Integer pd) {
		Map<Corso, Integer> result = new HashMap<>();
		String sql = "SELECT c.codins, c.nome, c.crediti, c.pd, COUNT(*) AS tot FROM corso c, iscrizione i"
				+ " WHERE c.codins = i.codins AND c.pd = ? GROUP BY c.codins, c.nome, c.crediti, c.pd";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"),
						rs.getInt("pd"));
				Integer n = rs.getInt("tot");
				result.put(c, n);
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println("Errore in getIscrittiByPeriodo");
			throw new RuntimeException(e);
		}
		return result;
	}

	public List<Studente> getStudenteByCorso(String corso) {
		List<Studente> result = new ArrayList<>();
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS FROM iscrizione AS i, studente s WHERE s.matricola = i.matricola AND i.codins=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"),
						rs.getString("CDS"));
				result.add(s);
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println("Errore in getStudenteByCorso");
			throw new RuntimeException(e);
		}
		return result;
	}

	public boolean esisteCorso(String corso) {
		String sql = "SELECT * FROM corso WHERE codins=?";
		boolean risultato = false;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				risultato = true;
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println("Errore in getCorsiByPeriodo");
			throw new RuntimeException(e);
		}

		return risultato;
	}

	public Map<String, Integer> divisioneStudenti(Corso corso) {
		String sql = "SELECT s.CDS, COUNT(*) AS tot FROM studente s, iscrizione i "
				+ "WHERE i.codins = ? AND s.matricola = i.matricola AND s.CDS<> \"\" GROUP BY s.CDS";
		Map<String, Integer> statistiche = new HashMap<String, Integer>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				statistiche.put(rs.getString("CDS"), rs.getInt("tot"));
			}
			
			conn.close();
			
			return statistiche;
		} catch (SQLException e) {
			System.out.println("Errore in getStudenteByCorso");
			throw new RuntimeException(e);
		}
	}

}
