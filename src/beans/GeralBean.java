package beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.event.ValueChangeEvent;

@ManagedBean
@SessionScoped
public class GeralBean {
	
	private Locale locale;
	private List<Locale> localesDisponiveis;
	
	public GeralBean() {
		localesDisponiveis = new ArrayList<>();
		
		Iterator<Locale> iterator = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
		
		while (iterator.hasNext()) {
			localesDisponiveis.add(iterator.next());
		}
		
		// Atribuindo o locale sugerido pelo browser
		locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
		
		// Atribuindo o locale default do faces-config
		locale = FacesContext.getCurrentInstance().getApplication().getDefaultLocale();
		
		// Atribuindo o locale calculo pelo UiViewRoot
		
		if(FacesContext.getCurrentInstance().getViewRoot() != null) {
			System.out.println("viewRoot diferente de nulo");
			locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();	
		}
	}
	
	public void atualizarLocale(ActionEvent e) {
		
		Locale l = (Locale) e.getComponent().getAttributes().get("locale");
		
		if (l != null) {
			locale = l;
		}
		
		if(FacesContext.getCurrentInstance().getViewRoot() != null) {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);	
		}
	}
	
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public List<Locale> getLocalesDisponiveis() {
		return localesDisponiveis;
	}
	public void setLocalesDisponiveis(List<Locale> localesDisponiveis) {
		this.localesDisponiveis = localesDisponiveis;
	}

	@Override
	public String toString() {
		return "GeralBean [locale=" + locale + ", localesDisponiveis=" + localesDisponiveis + "]";
	}
	
	
}
