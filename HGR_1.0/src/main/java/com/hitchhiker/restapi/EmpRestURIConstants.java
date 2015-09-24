package com.hitchhiker.restapi;

public class EmpRestURIConstants {
    
    // Hitchhiker Service URI
    public static final String SIGN_UP = "/rest/signup";
    public static final String LOGIN = "/rest/login/{id}";
    public static final String GET_CUSTOMER = "/rest/getcustomer/{id}";
    public static final String CREATE_BOOKING = "/rest/booking/createbooking";
    public static final String ADD_EXISTING = "/rest/booking/addexisting/{id}";
    public static final String UPDATE_BOOKING = "/rest/booking/updatebooking/{id}";
    public static final String DELETE_BOOKING = "/rest/booking/deletebooking/{id}";
    public static final String GET_ALL_BOOKING = "/rest/booking/allbookings";
    public static final String GET_BOOKING = "/rest/booking/getbooking/{id}";
      
}