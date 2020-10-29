package br.com.ebenezer.util;

import java.util.Date;

import br.com.ebenezer.model.Pessoa;
import br.com.ebenezer.model.PessoaFisica;





public class ConverteCaixaAlta {
	
	public static Pessoa convertePessoa(Pessoa pessoa) {
		pessoa.setNomeReceita(pessoa.getNomeReceita().toUpperCase());
		
		if (pessoa.getApelido() != null) {
			pessoa.setApelido(pessoa.getApelido().toUpperCase());
		}
		
		if (pessoa.getTelefone() != null) {
			pessoa.setTelefone(pessoa.getTelefone().toUpperCase());
		}
		
		if (pessoa.getDataCadastro() == null) {
			pessoa.setDataCadastro(new Date());
		}
		
		pessoa.setLogradouro(pessoa.getLogradouro().toUpperCase());
		pessoa.setNumero(pessoa.getNumero().toUpperCase());
		pessoa.setBairro(pessoa.getBairro().toUpperCase());
		pessoa.setCidade(pessoa.getCidade().toUpperCase());
		pessoa.setEstadoSigla(pessoa.getEstadoSigla().toUpperCase());
		return pessoa;
	}
	
	public static PessoaFisica convertePessoaFisica(PessoaFisica pessoaFisica) {
		pessoaFisica.setNumeroDocumento(pessoaFisica.getNumeroDocumento().toUpperCase());
		pessoaFisica.setOrgaoEmissor(pessoaFisica.getOrgaoEmissor().toUpperCase());
		pessoaFisica.setNacionalidade(pessoaFisica.getNacionalidade().toUpperCase());
		return pessoaFisica;
	}	
	
}
