package homefinance.form.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import homefinance.form.service.TransactionService;
import homefinance.form.validator.TransactionFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import homefinance.form.model.Transaction;
//import javax.validation.Valid;

//http://www.tikalk.com/redirectattributes-new-feature-spring-mvc-31/
//https://en.wikipedia.org/wiki/Post/Redirect/Get
//http://www.oschina.net/translate/spring-mvc-flash-attribute-example
@Controller
public class TransactionController {

	private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	TransactionFormValidator transactionFormValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(transactionFormValidator);
	}

	private TransactionService transactionService;

	@Autowired
	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("index()");
		return "redirect:/transactions";
	}

	// list page
	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
	public String showAllTransactions(Model model) {

		logger.debug("showAllTransactions()");
		model.addAttribute("transactions", transactionService.findAll());
		return "transactions/list";
	}

	// list page (pattern)
	@RequestMapping(value = "/transactions/{pattern}/list", method = RequestMethod.GET)
	public String showPatternTransaction(@PathVariable("pattern") String pattern, Model model) {
		logger.debug("showPatternTransaction() pattern: {}", pattern);
		if (transactionService.findByPattern(pattern) == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Transaction is not found");
		}
		model.addAttribute("transactions", transactionService.findByPattern(pattern));
		return "redirect:/transactions";
	}

	// save or update transaction
	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	public String saveOrUpdateTransaction(@ModelAttribute("transactionForm") @Validated Transaction transaction,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

		logger.debug("saveOrUpdateTransaction() : {}", transaction);

		if (result.hasErrors()) {
			populateDefaultModel(model);
			return "transactions/transactionform";
		} else {

			redirectAttributes.addFlashAttribute("css", "success");
			if(transaction.isNew()){
				redirectAttributes.addFlashAttribute("msg", "Transaction is added successfully!");
			}else{
				redirectAttributes.addFlashAttribute("msg", "Transaction is updated successfully!");
			}
			transactionService.saveOrUpdate(transaction);
			
			// POST/REDIRECT/GET
			return "redirect:/transactions/" + transaction.getId();
			// POST/FORWARD/GET
			// return "transaction/list";
		}
	}

	// show add transaction form
	@RequestMapping(value = "/transactions/add", method = RequestMethod.GET)
	public String showAddTransactionForm(Model model) {
		logger.debug("showAddTransactionForm()");
		Transaction transaction = new Transaction();
		// set default value
		transaction.setUser("Nata");
		transaction.setTransactionDate("10/10/2015");
		transaction.setSourceSum(1000);
		transaction.setTransactionDesc("First part");
		transaction.setTransactionType(new ArrayList<String>(Arrays.asList("Income")));
		transaction.setSourceType(new ArrayList<String>(Arrays.asList("Salary")));
		model.addAttribute("transactionForm", transaction);
		populateDefaultModel(model);
		return "transactions/transactionform";
	}

	// show update form
	@RequestMapping(value = "/transactions/{id}/update", method = RequestMethod.GET)
	public String showUpdateTransactionForm(@PathVariable("id") int id, Model model) {
		logger.debug("showUpdateTransactionForm() : {}", id);
		Transaction transaction = transactionService.findById(id);
		model.addAttribute("transactionForm", transaction);
		populateDefaultModel(model);
		return "transactions/transactionform";
	}

	// delete transaction
	@RequestMapping(value = "/transactions/{id}/delete", method = RequestMethod.POST)
	public String deleteTransaction(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
		logger.debug("deleteTransaction() : {}", id);
		transactionService.delete(id);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Transaction is deleted!");
		return "redirect:/transactions";
	}

	// show transaction
	@RequestMapping(value = "/transactions/{id}", method = RequestMethod.GET)
	public String showTransaction(@PathVariable("id") int id, Model model) {
		logger.debug("showTransaction() id: {}", id);
		Transaction transaction = transactionService.findById(id);
		if (transaction == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Transaction is not found");
		}
		model.addAttribute("transaction", transaction);
		return "transactions/show";
	}

	private void populateDefaultModel(Model model) {

		List<String> transactionTypeList = new ArrayList<String>();
		transactionTypeList.add("Income");
		transactionTypeList.add("Expense");
		model.addAttribute("transactionTypeList", transactionTypeList);

		List<String> sourceTypeList = new ArrayList<String>();
		sourceTypeList.add("Salary");
		sourceTypeList.add("Pension");
		sourceTypeList.add("Flat");
		sourceTypeList.add("Eat");
		model.addAttribute("sourceTypeList", sourceTypeList);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {
		logger.debug("handleEmptyData()");
		logger.error("Request: {}, error ", req.getRequestURL(), ex);
		ModelAndView model = new ModelAndView();
		model.setViewName("transaction/show");
		model.addObject("msg", "transaction is not found");
		return model;
	}
}