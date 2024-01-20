package sa.test.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import sa.test.exception.BusinessException;
import sa.test.exception.ObjectNotFoundException;

@RestControllerAdvice
public class ResourceExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(ResourceExceptionHandler.class);

	private static final String CAUSE = "Causa: {} : {}";

	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public StandardError objectNotFoundExecption(ObjectNotFoundException e, HttpServletRequest req) {
		log.error(CAUSE, e, e.getMessage());

		return new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(),
				e.getMessage(), req.getRequestURI());
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public StandardError rulesException(BusinessException e, HttpServletRequest req) {
		log.error(CAUSE, e, e.getMessage());

		return new StandardError(HttpStatus.BAD_REQUEST.value(), "Error",
				e.getMessage(), req.getRequestURI());
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<StandardError> validation(BindException e, HttpServletRequest req) {
		log.error(CAUSE, e, e.getMessage());

		ValidationError validationError = new ValidationError(
				HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error", "Validation error", req.getRequestURI());
		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			validationError.addError(f.getField(), f.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationError);
	}

}
