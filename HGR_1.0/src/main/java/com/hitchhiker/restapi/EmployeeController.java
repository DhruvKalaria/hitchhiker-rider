package com.hitchhiker.restapi;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the Customer service.
 */
@Controller
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	//Map to store employees, ideally we should use database
	public static Map<String, Customer> customerData = new HashMap<String, Customer>();

	@RequestMapping(value = EmpRestURIConstants.SIGN_UP, method = RequestMethod.POST)
	public @ResponseBody Boolean createCustomer(@RequestBody Customer customer, @RequestHeader("messageId") String header)	{
		MessageReplay.messageId.put("dummy", 001);
		Set<String> keys = MessageReplay.messageId.keySet();
		for(String i : keys){
			if(MessageReplay.messageId.get(i).equals(Integer.valueOf(header)))	{
				logger.info("Message Replay detected");
				MessageReplay.messageReplayFlag = true;
			}
		}
		if(MessageReplay.messageReplayFlag==false)	{
			MessageReplay.messageId.put(header, Integer.valueOf(header));
			logger.info("Customer Creation in Progress");
			customerData.put(customer.getCustomerId(), customer);
			return true;
		}
		MessageReplay.messageReplayFlag = false;
		return false;
	}

	@RequestMapping(value = EmpRestURIConstants.LOGIN, method = RequestMethod.POST)
	public @ResponseBody Boolean login(@PathVariable("id") String customerId, @RequestBody Customer customer) {
		logger.info("Start Login for ID="+customerId);
		if(customerData.get(customerId)!=null)	{
			if( customerData.get(customerId).getCustomerId().equals(customer.getCustomerId()) && 
					customerData.get(customerId).getCustomerPassword().equals(customer.getCustomerPassword()) )
				return true ;
			else
				return false;
		}	else	{
			return false;
		}
	}

	@RequestMapping(value = EmpRestURIConstants.GET_CUSTOMER, method = RequestMethod.GET)
	public @ResponseBody Customer getCustomer(@PathVariable("id") int customerId) {
		logger.info("Get Details for Employee with ID="+customerId);
		return customerData.get(customerId);
	}
}