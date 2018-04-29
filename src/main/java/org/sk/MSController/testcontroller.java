package org.sk.MSController;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sk.MSModel.Employee;
import org.sk.MSModel.Test;
import org.sk.Service.CurrencyService;
import org.sk.Service.EmpService;
import org.sk.Utiilty.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController

public class testcontroller {
	@Autowired
	EmpService empService;
	@Autowired
	CurrencyService currencyService;

	private String baseCurrency = "EUR";

	public static final String CLICHED_MESSAGE = "Hello Sujatha. This is from Rest from JAX-WS framework";

	@RequestMapping("/test")
	String test() {
		return "Hello Sujatha! Your test for simple microservices is successful. Keep going";
	}

	@RequestMapping(value = "/test2", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ModelAndView homePage() {
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}

	@RequestMapping("/")
	String home() {
		return "Hello Sujatha!";
	}

	@GET
	@RequestMapping("/test1")
	@Produces("text/plain")
	public String getHello() {

		return CLICHED_MESSAGE;
	}

	// not working
	@RequestMapping(value = "/getTestXML", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML)
	public ResponseEntity<Object> getTestDataXML() {
		Test test = new Test();
		test.setId(1);
		test.setName("Sujatha");
		test.setTelephoneNumber(12346789);

		return new ResponseEntity<Object>(test, HttpStatus.OK);
	}

	@RequestMapping(value = "/getTestJSON", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Object> getTestDataJSON() {
		Test test = new Test();
		test.setId(1);
		test.setName("Sujatha");
		test.setTelephoneNumber(12346789);
		return new ResponseEntity<Object>(test, HttpStatus.OK);
	}

	// @RequestMapping(value = "/retrieve/{id}", method = RequestMethod.GET)
	public Employee getEmployee(String id) {
		// retrieveAllEmployees();
		return empService.getUser(id);
	}

	@RequestMapping("/emps")
	public List<Employee> getAllUser() {
		return empService.getAllUsers();
	}

	private Map<String, Double> getCurrencyData(String baseCurrency) {
		Utilities util = new Utilities();
		String currencyRate = currencyService.getCurrencyResult(baseCurrency);
		Map<String, Double> currencyMap = new TreeMap<String, Double>();
		currencyMap = util.getCurrentCurrenyRates();
		return currencyMap;
	}

	private Map<String, Double> getCurrencyRatesMap(String currency) {
		Map<String, Double> currencyMap = new TreeMap<String, Double>();
		currencyMap = getCurrencyData(currency);
		return currencyMap;
	}

	private double getCurrencyRate(String baseCurrency, String currency) {
		Map<String, Double> currencyMap = new TreeMap<String, Double>();
		currencyMap = getCurrencyData(baseCurrency);
		double currencyRate = 0;
		currencyRate = currencyMap.get(currency);
		return currencyRate;
	}

	private double getCurrencyRateDetail(String currency) {
		double currencyRate = 0;
		currencyRate = getCurrencyRatesMap(currency).get(currency);
		return currencyRate;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public @ResponseBody ModelAndView deleteEmployee(@RequestParam("id") String id,
			@RequestParam("currency") String currency) {		
	//	empService.deleteEmployeeIfSupervisor(id);
	//	empService.
		empService.delete(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("PageAction", "delete");
		modelAndView = getCurrencyRateValue(currency, modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "/currencyRate", method = RequestMethod.GET)
	public ModelAndView getCurrencyRate(@RequestParam("currency") String currency) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = getCurrencyRateValue(currency, modelAndView);
		return modelAndView;
	}

	private ModelAndView getCurrencyRateValue(String currency, ModelAndView modelAndView) {
		double currencyRate = 0;
		System.out.println("currency " + currency);
		if (currency != null) {
			currencyRate = getCurrencyRateDetail(currency);
		}
		// System.out.println("currencyRate "+currencyRate);
		modelAndView.addObject("firstload", "false");
		modelAndView.addObject("currencyRate", currencyRate);
		modelAndView.addObject("currencyName", currency);
		modelAndView = retrieveAllEmployees(modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "/retrieveAll", method = RequestMethod.GET)
	public ModelAndView retrieveAllemployees() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = retrieveAllEmployees(modelAndView);
		modelAndView.addObject("firstload", "true");
		modelAndView.addObject("currencyName", baseCurrency);
		double currencyRate = getCurrencyRateDetail("EUR");
		modelAndView.addObject("currencyRate", currencyRate);
		return modelAndView;
	}

	private ModelAndView retrieveAllEmployees(ModelAndView modelAndView) {
		Employee dummyEmp = new Employee();
		modelAndView.addObject("PageAction", "retrieveAll");
		List<Employee> getAllEmployees = getAllUser();

		if (getAllEmployees != null) {

			modelAndView.addObject("EmployeeData", dummyEmp);
			modelAndView.addObject("EmployeeList", getAllEmployees);

			modelAndView.addObject("Result", "Employee Data Retrieved");
		} else {
			modelAndView.addObject("Result", "Employee Data Not Found or Not Available");
		}
		modelAndView.setViewName("result");
		return modelAndView;
	}

	@RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	public ModelAndView retrieve(@ModelAttribute Employee emp) {
		ModelAndView modelAndView = new ModelAndView();
		Employee employee = getEmployee(emp.getId());
		modelAndView.addObject("PageAction", "retrieve");
		if (employee != null) {
			modelAndView.addObject("EmployeeData", employee);
			modelAndView.addObject("Result", "Employee Data Retrieved");
		} else {
			modelAndView.addObject("Result", "Employee Data Not Found or Not Available");
		}
		modelAndView.setViewName("result");
		return modelAndView;
	}
	
	@RequestMapping(value = "/retrieveId", method = RequestMethod.GET)
	public ModelAndView retrieve(@RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView();
		Employee employee = getEmployee(id);
		modelAndView.addObject("PageAction", "retrieve");
		modelAndView.addObject("Action", "edit");
		if (employee != null) {
			modelAndView.addObject("EmployeeData", employee);
			modelAndView.addObject("Result", "Employee Data Retrieved");
		} else {
			modelAndView.addObject("Result", "Employee Data Not Found or Not Available");
		}
		modelAndView.setViewName("result");
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute Employee emp) {
		String pageAction = "save";
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("PageAction", pageAction);
		modelAndView = addOrUpdate(modelAndView, emp, pageAction);
		return modelAndView;
	}

	@RequestMapping(value = "/editdata", method = RequestMethod.POST)
	public @ResponseBody ModelAndView editdata(@Validated @ModelAttribute Employee emp, BindingResult bindingResult) {
		String pageAction = "update";
		System.out.println(" pageAction "+pageAction);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("PageAction", pageAction);
		modelAndView = addOrUpdate(modelAndView, emp, pageAction);
		return modelAndView;
	}

	public ModelAndView addOrUpdate(ModelAndView modelAndView, Employee emp, String pageAction) {
		empService.addOrUpdateEmployee(emp);
		Employee emp1 = new Employee();
		emp1 = empService.getUser(emp.getId());
		if (emp1 != null) {
			if ("save".equals(pageAction)) {
				modelAndView.addObject("Result", "Employee Data added and saved");
			} else if ("update".equals(pageAction)) {
				modelAndView.addObject("Result", "Employee Data Updated");
			}
		} else {
			if ("save".equals(pageAction)) {
				modelAndView.addObject("Result", "Employee Data not added properly");
			} else if ("update".equals(pageAction)) {
				modelAndView.addObject("Result", "Employee Data not Updated properly");
			}
		}
		modelAndView.addObject("EmployeeData", emp1);
		modelAndView.setViewName("result");
		return modelAndView;
	}

}
