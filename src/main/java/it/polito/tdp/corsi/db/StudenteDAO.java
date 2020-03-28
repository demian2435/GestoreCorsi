package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {

	public List<Studente> getStudenteByCorso(String corso) {
		List<Studente> result = new ArrayList<>();
		String sql = "SELECT * FROM iscrizione AS i, studente s WHERE s.matricola = i.matricola AND i.codins=? GROUP BY s.cognome";

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
}
