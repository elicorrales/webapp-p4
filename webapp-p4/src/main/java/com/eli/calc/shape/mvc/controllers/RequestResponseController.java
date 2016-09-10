package com.eli.calc.shape.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eli.calc.shape.domain.CalculationRequest;
import com.eli.calc.shape.domain.CalculationResult;
import com.eli.calc.shape.service.ShapeCalculatorService;

@Controller
public class RequestResponseController {

	@Autowired
	private ShapeCalculatorService calculator;
	

	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView welcomeTheSlash() {

    	System.err.println("\n\n\nWelcome: The / (slash) ; Index page\n\n\n");

		return new ModelAndView("/index","message","Slash Index Page: dynamic message from Controller");
	}


	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView welcomeTheIndex() {

    	System.err.println("\n\n\nWelcome2: The /index Index page\n\n\n");

		return new ModelAndView("/index","message","Index Index Page: dynamic message from Controller");
	}


	@RequestMapping(value="/newreq",method=RequestMethod.GET)
	public ModelAndView newreq() {

    	System.err.println("\n\n\nNew Request\n\n\n");

		return new ModelAndView("/newreq","message","New Request Page: message from Controller");

	}

	@RequestMapping(value="/pending",method=RequestMethod.GET)
	public ModelAndView pending() {

    	System.err.println("\n\n\nPending Requests\n\n\n");
    	List<CalculationRequest> pendingRequests = calculator.getAllPendingRequests();

		if (null==pendingRequests || pendingRequests.isEmpty()) {
			return new ModelAndView("/pending","message", "There are NO Pending Requests");
		}

		return new ModelAndView("/pending","pendingRequests",pendingRequests);

	}

	@RequestMapping(value="/results",method=RequestMethod.GET)
	public ModelAndView results() {

    	System.err.println("\n\n\nCalculated Results\n\n\n");

    	List<CalculationResult> calculatedResults = this.calculator.getAllCalculatedResults();

		if (null==calculatedResults || calculatedResults.isEmpty()) {
			return new ModelAndView("/results","message", "There are NO Calculated Results Yet");
		}

		return new ModelAndView("/results","calculatedResults",calculatedResults);

	}


	@RequestMapping(value="/delpending",method=RequestMethod.POST)
	public ModelAndView delpending() {

    	System.err.println("\n\n\nINSIDE Controller delpending()\n\n\n");

    	this.calculator.deleteAllPendingRequests();

    	List<CalculationRequest> pending = this.calculator.getAllPendingRequests();

		if (null==pending || pending.isEmpty()) {
			return new ModelAndView("/pending","message", "There are NO Pending Requests");
		}

		return new ModelAndView("/pending","message","Oops! there are "+pending.size());

	}

	@RequestMapping(value="/delresults",method=RequestMethod.POST)
	public ModelAndView delresults() {

    	System.err.println("\n\n\nINSIDE Controller delresults()\n\n\n");

    	this.calculator.deleteAllResults();

    	List<CalculationResult> results = this.calculator.getAllCalculatedResults();

		if (null==results || results.isEmpty()) {
			return new ModelAndView("/results","message", "There are NO Results");
		}

		return new ModelAndView("/results","message","Oops! there are "+results.size());

	}

}
