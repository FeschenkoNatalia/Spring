package homefinance.form.validator;

import homefinance.form.model.Transaction;
import homefinance.form.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class TransactionFormValidator implements Validator {

	@Autowired
	@Qualifier("transactionDateValidator")
	TransactionDateValidator transactionDateValidator;
	
	@Autowired
	TransactionService transactionService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Transaction.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Transaction transaction = (Transaction) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "NotEmpty.transactionForm.user");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "transactionDate", "NotEmpty.transactionForm.transactionDate");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sourceSum", "NotEmpty.transactionForm.sourceSum");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "transactionDesc", "NotEmpty.transactionForm.transactionDesc");

		if(!transactionDateValidator.valid(transaction.getTransactionDate())){
			errors.rejectValue("transactionDate", "Pattern.transactionForm.transactionDate");
		}
		
		if(transaction.getSourceSum()<=0){
			errors.rejectValue("sourceSum", "NotEmpty.transactionForm.sourceSum");
		}
		

		if (transaction.getTransactionType() == null || transaction.getTransactionType().size() > 1) {
			errors.rejectValue("transactionType", "Valid.transactionForm.transactionType");
		}

		if (transaction.getSourceType() == null || transaction.getSourceType().size() > 1) {
			errors.rejectValue("sourceType", "Valid.transactionForm.sourceType");
		}
	}
}