package beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import modelo.Pessoa;
import modelo.PessoaFisica;
import modelo.PessoaJuridica;
import modelo.Sexo;

@ManagedBean
@SessionScoped
public class CadastroPessoasBean {

	private Pessoa pessoaSelecionada;
	private Collection<Pessoa> lista;
	private String tipoNovaPessoa;
	private Locale requestLocale;
	
	public CadastroPessoasBean() {
		requestLocale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
		lista = new ArrayList<Pessoa>();
		pessoaSelecionada = new PessoaFisica();
		
		for (int i = 0; i < 10; i++) {
			Pessoa p = (i%2==0) ? new PessoaFisica() : new PessoaJuridica();
			p.setNome(String.format("Fulano %02d", i));
			p.setEmail(String.format("fulano%02d@teste.com", i));
			p.setTelefone(String.format("999.88-%02d", i));
			lista.add(p);
		}
	}
	
	public void salvar() {
		if(!lista.contains(pessoaSelecionada)) {
			lista.add(pessoaSelecionada);
		}
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Edição efetuada com sucesso!", ""));
	}
	
	public void criar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		if(tipoNovaPessoa == null) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Vc deve especificar o tipo", ""));
			return;
		}
		
		if(tipoNovaPessoa.equals("PF")) {
			pessoaSelecionada = new PessoaFisica();
		} else if(tipoNovaPessoa.equals("PJ")) {
			pessoaSelecionada = new PessoaJuridica();
		}
		
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa criada com sucesso!", ""));
		
	}
	
	public void ouvinteAjax(AjaxBehaviorEvent event) {
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("AJAX " + event.getPhaseId());
	}
	
	public void ouvinteAjax(ValueChangeEvent event) {
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("AJAX VALUE CHANGE " + event.getPhaseId());
	}
	
	public String cancelar() {
		pessoaSelecionada = null;
		tipoNovaPessoa = null;
		return "inicio";
	}
	
	public void excluir() {
		lista.remove(pessoaSelecionada);
		
		String mensagem = ResourceBundle.getBundle("bundles.mensagens",
				requestLocale
				).getString("excluida");
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
		
		pessoaSelecionada = null;
		
	}

	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}

	public Collection<Pessoa> getLista() {
		return lista;
	}

	public void setLista(Collection<Pessoa> lista) {
		this.lista = lista;
	}
	
	public Sexo[] getSexos() {
		return Sexo.values();
	}

	public String getTipoNovaPessoa() {
		return tipoNovaPessoa;
	}

	public void setTipoNovaPessoa(String tipoNovaPessoa) {
		this.tipoNovaPessoa = tipoNovaPessoa;
	}
	
	public boolean isPessoaFisicaSelecionada() {
		return pessoaSelecionada instanceof PessoaFisica;
	}
	
	public boolean isPessoaJuridicaSelecionada() {
		return pessoaSelecionada instanceof PessoaJuridica;
	}
	
	

}
