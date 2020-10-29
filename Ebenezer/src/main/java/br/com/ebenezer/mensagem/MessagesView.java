package br.com.ebenezer.mensagem;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesView {
	 
    public static void info(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem));
    }
 
    public static void warn(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, mensagem));
    }
 
    public static void error(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem));
    }
 
    public static void fatal(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, mensagem, mensagem));
    }
}