package sa.test.exception.handler;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, String error, String message, String path) {
		super(status, error, message, path);
	}

	public void addError(String fielName, String messagem) {
		errors.add(new FieldMessage(fielName, messagem));
	}


	public List<FieldMessage> getErrors() {
		return this.errors;
	}

	public void setErrors(List<FieldMessage> errors) {
		this.errors = errors;
	}


}
