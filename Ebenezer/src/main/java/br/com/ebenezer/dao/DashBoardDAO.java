package br.com.ebenezer.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import br.com.ebenezer.model.DashBoard;
import br.com.ebenezer.seguranca.HibernateUtil;

public class DashBoardDAO implements Serializable {
	private static final long serialVersionUID = -7373720006922758912L;

	public DashBoard carregar(String colunaValor, String coluna, String mesOuSemana, String situacao, Date dataVencimento) {
		DashBoard dashBoard = null;

		try {
			Connection conexao = HibernateUtil.getConexao();
			Statement stmt;
			stmt = conexao.createStatement();

			ResultSet rs = stmt.executeQuery("select count(situacao), sum(" + colunaValor + ") from contratoparcela c where " + 
					"extract('" + mesOuSemana + "' from c." + coluna + ") = date_part('" + mesOuSemana + "', "
							+ "timestamp '" + dataVencimento + "') and situacao = '" + situacao + "'");

			while (rs.next()) {
				dashBoard = new DashBoard();
				dashBoard.setQuantidade(Integer.parseInt(rs.getString(1)));
				dashBoard.setValor(new BigDecimal(rs.getString(2)));
			} 
			
			stmt.close();
		} catch (RuntimeException | SQLException erro) {
			if (dashBoard.getValor() == null) {
				dashBoard = new DashBoard();
				dashBoard.setQuantidade(0);
				dashBoard.setValor(new BigDecimal("0.00"));
			}
		} finally {
		}
		return dashBoard;
	}

}
