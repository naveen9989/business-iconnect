package com.iconnect.profiling.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.iconnect.profiling.command.Registration;
import com.iconnect.profiling.exceptions.ActivationFailureException;
import com.iconnect.profiling.exceptions.InvalidUrlException;
import com.iconnect.profiling.exceptions.RegistrationFailureException;
import com.iconnect.profiling.service.IRegistrationSevice;
import com.iconnect.profiling.validator.FormValidator;

/**
 * @author NaveenKumar
 * 
 */
@Controller
@RequestMapping("/registration")
@SessionAttributes("registration")
public class RegistrationController {
	private Logger logger = LoggerFactory
			.getLogger(RegistrationController.class);
	private IRegistrationSevice<Registration> service;
	private FormValidator validator;

	@Autowired
	public RegistrationController(IRegistrationSevice<Registration> service,
			FormValidator validator) {
		this.service = service;
		this.validator = validator;
	}

	/*
	 * Controller will always look for a default GET method for first
	 * request(Call), Below method allows us to render GET request
	 */
	@RequestMapping(method = RequestMethod.GET)
	// Method contain a Model as input to setup registration object
	public String intializeForm(Model model) {
		logger.debug("Entered Into Controller for'GET' Request");
		Registration registration = new Registration();
		if (!model.containsAttribute("registration"))
			model.addAttribute("registration", registration);
		// Based on view resolver configuration the registration form view will
		// be rendered
		logger.debug("Returning 'First VIEW(registrationstep1.jsp)' to User");
		return "registrationstep1";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String formSubmit(
			@ModelAttribute("registration") Registration registration,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response, SessionStatus status,
			@RequestParam("_page") int currentPage, Model model)
			throws RegistrationFailureException {
		String view;
		logger.debug("Entered into formSubmit(Method) with'POST' Request current page value is::"
				+ currentPage);
		// The below Map is used to navigate the views based on pageNo submitted
		// by user
		Map<Integer, String> formPages = new HashMap<Integer, String>();
		formPages.put(0, "registrationstep1");
		formPages.put(1, "registrationstep2");
		if (request.getParameter("_finish") != null) {
			// validate the data and pass the object to service class
			logger.debug("UserClicked _Finish Button,validating User Submitted Values");
			validator.validateBusinessForm(registration, result);
			if (!result.hasErrors()) {
				// get the remote ipaddress
				registration.setIpAddress(request.getRemoteAddr());
				logger.debug("Passed Validations Interacting with Service Layer");
				boolean checkEmail = service.checkEmailExists(registration
						.getEmail());
				if (checkEmail != true) {
					view = service.createProfile(registration);
				} else {
					model.addAttribute("url",
							"Email id already exists choose another");
					view = "registrationstep2";
				}
				// status.setComplete();
				logger.debug("User Session Completed returning the view(SUCCESS or FAILURE)");
				return view;
			} else {
				logger.debug("Validation Errors Found Returning the SAME PAGE view");
				return formPages.get(currentPage);
			}
		}
		/*
		 * Get the value of _target page or _previous page based on that after
		 * passing form validation redirect to target page view or previous page
		 * view
		 */else {
			// Get the targetPage value using Spring provided static method
			int _targetPage = WebUtils.getTargetPage(request, "_target",
					currentPage);
			System.out.println("Target page value" + _targetPage);
			System.out.println("Entered Else");
			if (_targetPage < currentPage) {
				// user clicked on previous page
				logger.debug("User Clicked on Previous Button");
				logger.debug("Returning Previous page View");
				return formPages.get(_targetPage);

			} else
				// user clicked next page
				logger.debug("User Clicked on (PROCEED)Next Page validating User Submitted Current Page Values");
			validator.validateAccountForm(registration, result);

			if (!result.hasErrors()) {
				logger.debug("Validations Passed returning next(TARGET) view");
				return formPages.get(_targetPage);
			} else {
				logger.debug("Validation Errors Found returning SAME PAGE view");
				return formPages.get(currentPage);
			}

		}

	}

	/* below method used for activating the profile */
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	// Method allows us to activate profile when user clicks on activation url.
	public String activateProfile(@RequestParam String activationCode)
			throws InvalidUrlException, ActivationFailureException {

		if ((!activationCode.equals("") && activationCode != null)) {
			return service.activateProfile(activationCode);
		} else {
			throw new InvalidUrlException("Invalid activation url");
		}

	}

	// Wrap the application exception and convert into userFriendly exception
	@ExceptionHandler(value = InvalidUrlException.class)
	public ModelAndView handleException(InvalidUrlException exception) {

		System.out.println("Entered into exception handler");
		ModelAndView view = new ModelAndView();
		view.setViewName("error");
		view.addObject("url", exception.getMessage());
		return view;
	}

	// Wrap the application exception and convert into userFriendly exception
	@ExceptionHandler(ActivationFailureException.class)
	public ModelAndView handleException(ActivationFailureException exception) {
		System.out.println("Entered into exception handler");
		ModelAndView view = new ModelAndView();
		view.setViewName("error");
		view.addObject("url", exception.getMessage());
		return view;
	}

	// Wrap the application exception and convert into userFriendly exception
	@ExceptionHandler(RegistrationFailureException.class)
	public ModelAndView handleException(RegistrationFailureException exception) {
		System.out.println("Entered into exception handler");
		ModelAndView view = new ModelAndView();
		view.setViewName("error");
		view.addObject("url", exception.getMessage());
		return view;
	}

	

	// Allows to bind userSubmitted date in defined format
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

}
