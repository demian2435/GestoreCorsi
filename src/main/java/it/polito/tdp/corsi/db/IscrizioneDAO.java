package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Iscrizione;

public class IscrizioneDAO {

	public List<Iscrizione> getMatricolaByCorso(String corso) {
		List<Iscrizione> result = new ArrayList<>();
		String sql = "SELECT * FROM iscrizione WHERE codins=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Iscrizione i = new Iscrizione(rs.getInt("matricola"), corso);
				result.add(i);
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println("Errore in getMatricolaByCorso");
			throw new RuntimeException(e);
		}
		return result;
	}

}
