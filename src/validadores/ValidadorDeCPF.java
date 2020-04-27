package validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="validadorDeCPF")
public class ValidadorDeCPF implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		
		String valorObjeto = value.toString();
		
		if(valorObjeto.equals("111.111.111-11")) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"", ""));
		}
		
	}

}
