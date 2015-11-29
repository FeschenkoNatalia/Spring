package homefinance.form.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component("transactionDateValidator")
public class TransactionDateValidator {

	private Pattern pattern;
	private Matcher matcher;
	// format DD/MM/YYYY
	private static final String TRANSACTION_DATE_PATTERN = "^((0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](20)\\d\\d)$";

	public TransactionDateValidator() {
		pattern = Pattern.compile(TRANSACTION_DATE_PATTERN);
	}

	public boolean valid(final String transactionDate) {

		matcher = pattern.matcher(transactionDate);
		return matcher.matches();

	}
}