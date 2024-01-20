package sa.test.service.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import jakarta.validation.ConstraintValidatorContext;
import sa.test.exception.handler.FieldMessage;
import sa.test.model.enums.CustomerType;

public class ExistCustomerTypeValidator implements jakarta.validation.ConstraintValidator<ExistCustomerType, String> {

	String message;

	@Override
	public void initialize(ExistCustomerType ann) {
		message = ann.message();
	}

	@Override
	public boolean isValid(String type, ConstraintValidatorContext context) {
		if (StringUtils.isNotBlank(type)) {
			CustomerType costumerType = CustomerType.get(type);

			List<FieldMessage> list = new ArrayList<>();

			if (costumerType == null) {
				list.add(new FieldMessage("type", message));
			}

			for (FieldMessage e : list) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(e.getMessage()).addConstraintViolation();
			}
			return list.isEmpty();
		}

		return true;
	}
}
