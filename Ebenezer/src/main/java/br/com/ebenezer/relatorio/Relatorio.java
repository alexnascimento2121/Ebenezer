package br.com.ebenezer.relatorio;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.com.ebenezer.seguranca.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

public class Relatorio {

	public static void gerarPDF(Long valor, String nomeParametro, String nomePdf,
			String caminho) throws IOException, SQLException {
		Connection conexao = HibernateUtil.getConexao();
		Map<String, Object> parametros = new HashMap<String, Object>();

		if (parametros != null) {
			parametros.put(nomeParametro, valor);
			parametros.put("SUBREPORT_DIR", "/reports/");
		}

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			System.out.println("response " + response);
			ServletOutputStream responseStream = response.getOutputStream();

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", nomePdf);
			response.setHeader("Pragma", "");
			response.setHeader("Cache-Control", "");
			response.setHeader("Expires", "");

			InputStream reportStream = context.getExternalContext().getResourceAsStream(caminho);
			JasperRunManager.runReportToPdfStream(reportStream, responseStream, parametros, conexao);
			responseStream.flush();
			responseStream.close();
			context.responseComplete();
			conexao.close();

		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
