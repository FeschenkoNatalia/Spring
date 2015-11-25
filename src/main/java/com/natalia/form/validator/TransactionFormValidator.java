package com.natalia.form.validator;

import com.natalia.form.model.Transaction;
import com.natalia.form.service.TransactionService;
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
	@Qualifier("transaction_dateValidator")
	Transaction_dateValidator transaction_dateValidator;
	
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "transaction_date", "NotEmpty.transactionForm.transaction_date");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "source_sum", "NotEmpty.transactionForm.source_sum");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "transaction_desc", "NotEmpty.transactionForm.transaction_desc");

		if(!transaction_dateValidator.valid(transaction.getTransaction_date())){
			errors.rejectValue("transaction_date", "Pattern.transactionForm.transaction_date");
		}
		
		if(transaction.getSource_sum()<=0){
			errors.rejectValue("source_sum", "NotEmpty.transactionForm.source_sum");
		}
		

		if (transaction.getTransaction_type() == null || transaction.getTransaction_type().size() > 1) {
			errors.rejectValue("transaction_type", "Valid.transactionForm.transaction_type");
		}

		if (transaction.getSource_type() == null || transaction.getSource_type().size() > 1) {
			errors.rejectValue("source_type", "Valid.transactionForm.source_type");
		}
	}
}