package br.com.ebenezer.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsultaCEP {
	private String cep = "cep";
	private String logradouro = "logradouro";
	private String bairro = "bairro";
	private String localidade = "localidade";
	private String uf = "uf";
	
	public static String buscarCep(String cep) {
        String json;
        try {
            URL url = new URL("http://viacep.com.br/ws/"+ cep +"/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuilder jsonSb = new StringBuilder();

            br.lines().forEach(l -> jsonSb.append(l.trim()));

            json = jsonSb.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    public void pesquisaCEP(String cepCorrente) throws IOException {
        String json = buscarCep(cepCorrente);
        Map<String,String> mapa = new HashMap<>();

        Matcher matcher = Pattern.compile("\"\\D.*?\": \".*?\"").matcher(json);
        while (matcher.find()) {
            String[] group = matcher.group().split(":");
            mapa.put(group[0].replaceAll("\"", "").trim(), group[1].replaceAll("\"", "").trim());
        }

        if ( mapa.containsKey( "cep" ) ) {
            cep = (mapa.get(cep));  
        }
        if ( mapa.containsKey( "logradouro" ) ) {
            logradouro = (mapa.get(logradouro));  
        }
        if ( mapa.containsKey( "bairro" ) ) {
            bairro = (mapa.get(bairro));  
        }
        if ( mapa.containsKey( "localidade" ) ) {
           localidade = (mapa.get(localidade));  
        }
        if ( mapa.containsKey( "uf" ) ) {
            uf = (mapa.get(uf));  
        }
    }

	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
}