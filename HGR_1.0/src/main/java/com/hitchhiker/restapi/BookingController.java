package com.hitchhiker.restapi;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;

/**
 * Handles requests for the Booking service.
 */

@Controller
public class BookingController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	
	Map<String, Booking> bookingData = new HashMap<String, Booking>();
	Map<String, Customer> customerPercent = new HashMap<String, Customer>();
	Customer customer;

	@RequestMapping(value = EmpRestURIConstants.CREATE_BOOKING, method = RequestMethod.POST)
	public @ResponseBody Boolean createBooking(@RequestBody Booking booking)	{
		logger.info("Booking Creation in Progress");
		if(Integer.valueOf(booking.getNumberOfPeople()) <= 4)	{
			booking.setCountJoinee(0);
			booking.setCustomerPeople(Integer.valueOf(booking.getNumberOfPeople()));
			booking.setPrimary(booking.getCustomerId());
			EmployeeController.customerData.get(booking.getCustomerId()).setTotalBookings(EmployeeController.customerData.get(booking.getCustomerId()).getTotalBookings()+1);
			EmployeeController.customerData.get(booking.getCustomerId()).setSuccessBooking(EmployeeController.customerData.get(booking.getCustomerId()).getSuccessBooking()+1);
			bookingData.put(booking.getBookingId(), booking);
			return true;
		}	else	{
			return false;
		}
	}

	@RequestMapping(value = EmpRestURIConstants.DELETE_BOOKING, method = RequestMethod.PUT)
	public @ResponseBody Boolean deleteBooking(@PathVariable("id") String id, @RequestBody Booking booking)	{
		logger.info("Booking Deletion in Progress");

		if(bookingData.get(id).getCustomerId() == bookingData.get(id).getPrimary()
				&& bookingData.get(id).getCountJoinee() == 0)	{
			EmployeeController.customerData.get(booking.getCustomerId()).setSuccessBooking(EmployeeController.customerData.get(booking.getCustomerId()).getSuccessBooking()-1);
			bookingData.remove(id);
			return true;
		} else if(bookingData.get(id).getCustomerId().equals(bookingData.get(id).getPrimary())
				&& bookingData.get(id).getCountJoinee() == 1)	{

			bookingData.get(id).setNumberOfPeople(Integer.toString((Integer.valueOf(bookingData.get(id).getNumberOfPeople()) - bookingData.get(id).getCustomerPeople())));
			bookingData.get(id).setPrimary(bookingData.get(id).getJoinee1());
			bookingData.get(id).setCustomerId(bookingData.get(id).getJoinee1());
			bookingData.get(id).setCustomerPeople(bookingData.get(id).getJoinee1People());
			bookingData.get(id).setJoinee1(null);
			bookingData.get(id).setJoinee1People(0);
			bookingData.get(id).setCountJoinee(bookingData.get(id).getCountJoinee()-1);
			EmployeeController.customerData.get(booking.getCustomerId()).setSuccessBooking(EmployeeController.customerData.get(booking.getCustomerId()).getSuccessBooking()-1);
			return true;

		} else if(bookingData.get(id).getCustomerId() == bookingData.get(id).getPrimary()
				&& bookingData.get(id).getCountJoinee() == 2)	{

			bookingData.get(id).setNumberOfPeople(Integer.toString((Integer.valueOf(bookingData.get(id).getNumberOfPeople()) - bookingData.get(id).getCustomerPeople())));
			bookingData.get(id).setPrimary(bookingData.get(id).getJoinee1());
			bookingData.get(id).setCustomerId(bookingData.get(id).getJoinee1());
			bookingData.get(id).setJoinee1(bookingData.get(id).getJoinee2());
			bookingData.get(id).setJoinee1People(bookingData.get(id).getJoinee2People());
			bookingData.get(id).setJoinee2(null);
			bookingData.get(id).setJoinee2People(0);
			bookingData.get(id).setCountJoinee(bookingData.get(id).getCountJoinee()-1);
			EmployeeController.customerData.get(booking.getCustomerId()).setSuccessBooking(EmployeeController.customerData.get(booking.getCustomerId()).getSuccessBooking()-1);
			return true;

		} else if(bookingData.get(id).getCustomerId() == bookingData.get(id).getPrimary()
				&& bookingData.get(id).getCountJoinee() == 3)	{
			bookingData.get(id).setNumberOfPeople(Integer.toString((Integer.valueOf(bookingData.get(id).getNumberOfPeople()) - bookingData.get(id).getCustomerPeople())));
			bookingData.get(id).setPrimary(bookingData.get(id).getJoinee1());
			bookingData.get(id).setCustomerId(bookingData.get(id).getJoinee1());
			bookingData.get(id).setJoinee1(bookingData.get(id).getJoinee2());
			bookingData.get(id).setJoinee1People(bookingData.get(id).getJoinee2People());
			bookingData.get(id).setJoinee2(bookingData.get(id).getJoinee3());
			bookingData.get(id).setJoinee2People(bookingData.get(id).getJoinee3People());
			bookingData.get(id).setJoinee3(null);
			bookingData.get(id).setJoinee3People(0);
			bookingData.get(id).setCountJoinee(bookingData.get(id).getCountJoinee()-1);
			EmployeeController.customerData.get(booking.getCustomerId()).setSuccessBooking(EmployeeController.customerData.get(booking.getCustomerId()).getSuccessBooking()-1);
			return true;

		} else	{
			if(booking.getCustomerId() == booking.getJoinee1())	{
				bookingData.get(id).setNumberOfPeople(Integer.toString((Integer.valueOf(bookingData.get(id).getNumberOfPeople()) - bookingData.get(id).getJoinee1People())));
				bookingData.get(id).setCountJoinee(bookingData.get(id).getCountJoinee()-1);
				bookingData.get(id).setJoinee1(bookingData.get(id).getJoinee2());
				bookingData.get(id).setJoinee1People(bookingData.get(id).getJoinee2People());
				bookingData.get(id).setJoinee2(bookingData.get(id).getJoinee3());
				bookingData.get(id).setJoinee2People(bookingData.get(id).getJoinee3People());
				bookingData.get(id).setJoinee3(null);
				bookingData.get(id).setJoinee3People(0);
				EmployeeController.customerData.get(booking.getCustomerId()).setSuccessBooking(EmployeeController.customerData.get(booking.getCustomerId()).getSuccessBooking()-1);
				return true;

			}	else if (booking.getCustomerId() == booking.getJoinee2())	{
				bookingData.get(id).setNumberOfPeople(Integer.toString((Integer.valueOf(bookingData.get(id).getNumberOfPeople()) - bookingData.get(id).getJoinee2People())));
				bookingData.get(id).setCountJoinee(bookingData.get(id).getCountJoinee()-1);
				bookingData.get(id).setJoinee2(bookingData.get(id).getJoinee3());
				bookingData.get(id).setJoinee2People(bookingData.get(id).getJoinee3People());
				bookingData.get(id).setJoinee3(null);
				bookingData.get(id).setJoinee3People(0);
				EmployeeController.customerData.get(booking.getCustomerId()).setSuccessBooking(EmployeeController.customerData.get(booking.getCustomerId()).getSuccessBooking()-1);
				return true;

			}	else if (booking.getCustomerId() == booking.getJoinee2())	{
				bookingData.get(id).setNumberOfPeople(Integer.toString((Integer.valueOf(bookingData.get(id).getNumberOfPeople()) - bookingData.get(id).getJoinee3People())));
				bookingData.get(id).setCountJoinee(bookingData.get(id).getCountJoinee()-1);
				bookingData.get(id).setJoinee3(null);
				bookingData.get(id).setJoinee3People(0);
				EmployeeController.customerData.get(booking.getCustomerId()).setSuccessBooking(EmployeeController.customerData.get(booking.getCustomerId()).getSuccessBooking()-1);
				return true;

			}	else	{

				return false;
			}

		}

	}

	@RequestMapping(value = EmpRestURIConstants.ADD_EXISTING, method = RequestMethod.PUT)
	public @ResponseBody int addToExistingBooking(@PathVariable("id") String bookingid, @RequestBody Booking booking)	{
		logger.info("Update Booking in Progress");

		int joinee3Success;
		int joinee2Success;
		int joinee1Success;
		int primarySuccess;
		int primaryTotal;
		int joinee1Total;
		int joinee2Total;
		int joinee3Total;
		int successPercent=100;
		
		if(bookingData.get(bookingid) != null && Integer.valueOf(bookingData.get(bookingid).getNumberOfPeople()) < 4)	{

			if(booking.getCustomerId().equals(bookingData.get(bookingid).getCustomerId()))	{
				
				return 111;
			}

			bookingData.get(bookingid).setCountJoinee(bookingData.get(bookingid).getCountJoinee() + 1);
			switch (bookingData.get(bookingid).getCountJoinee()) {
			case 1:
				bookingData.get(bookingid).setNumberOfPeople(Integer.toString(Integer.valueOf(booking.getNumberOfPeople()) + Integer.valueOf(bookingData.get(bookingid).getNumberOfPeople())));
				bookingData.get(bookingid).setJoinee1People(Integer.valueOf(booking.getNumberOfPeople()));
				bookingData.get(bookingid).setJoinee1(booking.getCustomerId());
				primarySuccess = EmployeeController.customerData.get(bookingData.get(bookingid).getCustomerId()).getSuccessBooking();
				primaryTotal = EmployeeController.customerData.get(bookingData.get(bookingid).getCustomerId()).getTotalBookings();
				joinee1Success = EmployeeController.customerData.get(booking.getCustomerId()).getSuccessBooking();
				joinee1Total = EmployeeController.customerData.get(booking.getCustomerId()).getTotalBookings();
				if(primaryTotal!=0 && joinee1Total==0)	{
					successPercent = (int)primarySuccess/primaryTotal;

				}	else {
					successPercent = 100;
				}

				break;
			case 2:
				bookingData.get(bookingid).setNumberOfPeople(Integer.toString(Integer.valueOf(booking.getNumberOfPeople()) + Integer.valueOf(bookingData.get(bookingid).getNumberOfPeople())));
				bookingData.get(bookingid).setJoinee2People(Integer.valueOf(booking.getNumberOfPeople()));
				bookingData.get(bookingid).setJoinee2(booking.getCustomerId());
				primarySuccess = EmployeeController.customerData.get(bookingData.get(bookingid).getCustomerId()).getSuccessBooking();
				primaryTotal = EmployeeController.customerData.get(bookingData.get(bookingid).getCustomerId()).getTotalBookings();
				joinee1Success = EmployeeController.customerData.get(bookingData.get(bookingid).getJoinee1()).getSuccessBooking();
				joinee1Total = EmployeeController.customerData.get(bookingData.get(bookingid).getJoinee1()).getTotalBookings();
				joinee2Success = EmployeeController.customerData.get(booking.getCustomerId()).getSuccessBooking();
				joinee2Total = EmployeeController.customerData.get(booking.getCustomerId()).getTotalBookings();
				if(joinee2Total == 0)	{
					successPercent = (int)(primarySuccess+joinee1Success)/(primaryTotal+joinee1Total);
				} else	{
					successPercent = (int)(primarySuccess+joinee1Success+joinee2Success)/(primaryTotal+joinee1Total+joinee2Total);
				}
				break;
			case 3:
				bookingData.get(bookingid).setNumberOfPeople(Integer.toString(Integer.valueOf(booking.getNumberOfPeople()) + Integer.valueOf(bookingData.get(bookingid).getNumberOfPeople())));
				bookingData.get(bookingid).setJoinee3People(Integer.valueOf(booking.getNumberOfPeople()));
				bookingData.get(bookingid).setJoinee3(booking.getCustomerId());
				primarySuccess = EmployeeController.customerData.get(bookingData.get(bookingid).getCustomerId()).getSuccessBooking();
				primaryTotal = EmployeeController.customerData.get(bookingData.get(bookingid).getCustomerId()).getTotalBookings();
				joinee1Success = EmployeeController.customerData.get(bookingData.get(bookingid).getJoinee1()).getSuccessBooking();
				joinee1Total = EmployeeController.customerData.get(bookingData.get(bookingid).getJoinee1()).getTotalBookings();
				joinee2Success = EmployeeController.customerData.get(bookingData.get(bookingid).getJoinee2()).getSuccessBooking();
				joinee2Total = EmployeeController.customerData.get(bookingData.get(bookingid).getJoinee2()).getTotalBookings();
				joinee3Success = EmployeeController.customerData.get(booking.getCustomerId()).getSuccessBooking();
				joinee3Total = EmployeeController.customerData.get(booking.getCustomerId()).getTotalBookings();
				if(joinee3Total == 0)	{
					successPercent = (int)(primarySuccess+joinee1Success+joinee2Success)/(primaryTotal+joinee1Total+joinee2Total);
				} else	{
					successPercent = (int)(primarySuccess+joinee1Success+joinee2Success+joinee3Success)/(primaryTotal+joinee1Total+joinee2Total+joinee3Total);
				}
				break;
			}
			
			return successPercent;
		} else	{
			return 404;
		}
	}

	@RequestMapping(value = EmpRestURIConstants.GET_BOOKING, method = RequestMethod.GET)
	public @ResponseBody
	List<Booking> getBookings(@PathVariable("id") String customerid)	{
		logger.info("Start getting Bookings");
		List<Booking> bookings = new ArrayList<Booking>();
		Set<String> bookingKeys = bookingData.keySet();
		for(String i : bookingKeys){
			if(bookingData.get(i).getCustomerId().equals(customerid) || 
					(bookingData.get(i).getJoinee1() != null && bookingData.get(i).getJoinee1().equals(customerid))||
					(bookingData.get(i).getJoinee2() != null && bookingData.get(i).getJoinee2().equals(customerid)) ||
					(bookingData.get(i).getJoinee3() != null && bookingData.get(i).getJoinee3().equals(customerid)))	{
				bookings.add(bookingData.get(i)); 
			}
		}		
		return bookings;
	}

	@RequestMapping(value = EmpRestURIConstants.GET_ALL_BOOKING, method = RequestMethod.GET)
	public @ResponseBody 
	List<Booking> getAllBookings() {
		logger.info("Start getAllBookings");
		List<Booking> bookings = new ArrayList<Booking>();
		Set<String> bookingKeys = bookingData.keySet();
		for(String i : bookingKeys){
			bookings.add(bookingData.get(i));
		}
		return bookings;
	}
	
	@RequestMapping(value = "/rest/jose", method = RequestMethod.GET)
	public @ResponseBody void createJose() throws JoseException	{

		Key key = new AesKey(ByteUtil.randomBytes(16));
		 JsonWebEncryption jwe = new JsonWebEncryption();
		 jwe.setPayload("Hello World!");
		 jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
		 jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		 jwe.setKey(key);
		 String serializedJwe = jwe.getCompactSerialization();
		 System.out.println("Serialized Encrypted JWE: " + serializedJwe);
		 jwe = new JsonWebEncryption();
		 jwe.setKey(key);
		 jwe.setCompactSerialization(serializedJwe);
		 System.out.println("Payload: " + jwe.getPayload());
		 
	}
	
}