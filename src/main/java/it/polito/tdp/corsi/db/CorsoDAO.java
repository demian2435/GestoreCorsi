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
		String sql = "SELECT c.codins, c.nome, c.crediti, c.pd, COUNT(*) AS tot FROM corso AS c, iscrizione i"
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

}
