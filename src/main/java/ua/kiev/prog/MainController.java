package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private TransDAO transDAO;

	@RequestMapping("/")
	public ModelAndView listTrans() {
		return new ModelAndView("index", "trans", transDAO.list());
	}

	@RequestMapping(value = "/add_page", method = RequestMethod.POST)
	public String addPage(Model model) {
		return "add_page";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam(value="pattern") String pattern) {
		return new ModelAndView("index", "trans", transDAO.list(pattern));
	}

	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value="id") long id) {
		transDAO.delete(id);
		return new ModelAndView("index", "trans", transDAO.list());
	}

	/*@RequestMapping("/image/{file_id}")
	public void getFile(HttpServletRequest request, HttpServletResponse response, @PathVariable("file_id") long fileId) {
		try {
			byte[] content = advDAO.getPhoto(fileId);
			response.setContentType("image/png");
			response.getOutputStream().write(content);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}*/

	@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView addTrans(@RequestParam(value="transaction_date") String transaction_date,
						 @RequestParam(value="transaction_sum") double transaction_sum,
						 @RequestParam(value="transaction_desc", required=false) String transaction_desc,
						 @RequestParam(value="sourcetypes") SourceTypes sourcetypes,
			             @RequestParam(value="transactionstypes") TransactionsTypes transactionstypes,
						 HttpServletRequest request,
						 HttpServletResponse response)
	{
		//try {
			Transactions trans = new Transactions(
					transaction_date, transaction_sum, transaction_desc,
					new SourceTypes(sourcetypes.getType(), sourcetypes.getSource_sum(),sourcetypes.getUsers()),
					new TransactionsTypes(transactionstypes.getType()));
			transDAO.add(trans);
			return new ModelAndView("index", "trans", transDAO.list());
		//} catch (IOException ex) {
		//	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		//	return null;
		}
	}