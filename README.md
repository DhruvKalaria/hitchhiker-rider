# hitchhiker-rider

Introduction:
HitchHiker! Lets you go for shopping with other fellow students and share the ride fare. Looking from social perspective it gives you chance to know more students and interact with them during your ride. It is an android-based application. It asks for the minimal personal information. Since we have deep linked the application with Uber, we need official Uber App already installed in the phone. You have to select the timeslot to travel, from and to places and number of people travelling, boom there you go. That’s it you are good to go with booking and have a ride! Also, you can update the ride details or delete the requested booking. We cannot be sure about the exact time when we can reach the decided pickup spot and hence we have given a timeslot of 0.5 hours within which students can group.

II. APPLICATION ARCHITECTURE AND TECHNICAL INFORMATION 
It’s a typical Client-Server model with Android application acting as a client and a Java Spring based Server as backend, which processes the information fed by the users. We have used the standard JSON broker to communicate between client and server.

III. SUPPORTING APPLICATION – UBER
Uber is a mobile application that helps a person to book a cab and the cab will be at the destination at whatever time has been mentioned in the ‘pickup time’. It takes the credit card details of the person using the application and stores the information. Whenever the ride gets over, the amount is charged directly to the card.
It is kind of a taxi service that is easy and convenient to use, the cab rate and fare split is very transparent and reliable. The services of this cab are fast and easy.
Below is a picture of how this application looks like-:

IV. CLIENT SIDE DETAILS
Most active smartphone users use android OS. Application development is done in android only through JAVA as a programming language as the android runtime uses Dalvik Virtual Machine, which is based on JVM until Android 4.4 KitKat. Android 5.0 Lollipop uses ART as the runtime. Our application is built with compatibility to Android 5.0 and lower android versions till Android 4.0.
We have made sure to introduce all the application level validations on the input forms. We have tried to minimize user input in form text and have used combo-box also known as spinners to reduce user input errors. Security is given due diligence while creating the application, hence we have carefully handled client side validations not to accept any special characters in any of the input forms except password fields. 


Client side Use Cases:
1.	Create Booking: Helps the user to create a booking with following parameters-
1)	Time slot – Half an hour slot to group
2)	Pickup Spot – Predefined spot to accumulate
3)	Drop Spot – Destination to reach
4)	Number of Persons travelling – Max 4 people
2.	Join who are already going: Helps user to add himself or a group with the person who is already going and have made a booking with following parameters-
1)	Update the number of persons
2.	Delete Booking: Helps user to remove from the group if he wishes to cancel his booking.

We have leveraged the deep linking with Uber App, so that we don’t have to implement the Uber API. This has couple of advantages as mentioned below:
1.	We do not have to look for securing the Uber endpoints in our application.
2.	The original authenticity and experience with Uber is available to the user.

Also, we have implemented the push notification services using the Android GCM services, so that user gets a push notification as a reminder for his booking.


V. SERVER SIDE DETAILS
Java based server backed by Java Spring framework is used as a backend for the processing needs of the client application. We have used the spring framework to leverage the inherent security provided by the framework. We have followed the classic approach of REST URI + JSON implementation for server side implementation. The server servers the client by providing required resources when hit appropriately by the API endpoints. Currently all the data is stored in the Hash map. It can be further extended to use any relational or non-relational databases to persist the data with the help of suitable adapter to the database. Also, we will be deploying the server on Amazon cloud environment.
VI. DEEP LINKING

Deep linking means invoking an application from some other application. Deeplinking connects a unique url to a defined action in a mobile app, seamlessly linking users to relevant content. A deeplink functions much like a traditional hyperlink on a webpage. It is composed of separate elements that make up what is referred to as a Uniform Resource Identifier (URI). The URI contains all the information that, when invoked, launches a mobile application with a specific screen. When thinking about deeplink structure, the best practice is to implement a URL with a unique scheme name and routing parameters (path and query strings) that represent custom actions to take in the app.
In our application we are going to deep link Uber App in our application. We will provide the pickup and drop-off locations as the query parameters to UBER, so that user directly has to book the ride.

X. APPLICATION SECURITY DETAILS 
Utmost care has been taken to implement the security for both client and server. Today every application if it works on Internet is vulnerable to the many kinds of attacks. Keeping this in mind we have implemented following security aspects to the application to make is safe from some of the attacks. Also, as a preventive measure we will be communicating over the HTTPS channel with self-signed certificate.
1.	Message Reply Attack Detection: We have introduced a Cryptographic Nonce Token. The nonce is used to give 'originality' to the message, where a nonce is used once and only once. If the server receives any other request with the same nonce, it will discard that request.
2.	CSRF Prevention: We have used the OWASP CSRFGuard to implement the prevention of cross-site Request Forgery attack.
3.	Certificate Pinning: Since HTTPS uses a asymmetric key, key needs to be shared over the network while protocol negotiation handshaking between client and server. This exposed the risk of exposure of key to some other person who is sniffing the network. So in order to minimize the man in the middle attack we can install the certificate on client for a given keystore on the server. Hence we have eliminated the key-sharing problem.

XII. COVERAGE AND LIMITATIONS
In the given timeframe we have tried to cover as many Use cases as we could to implement in the application. Coverage is limited to the SJSU students and a predefined list of pickup and drop off points. Cab Fare distribution among the students is not handled by the application. It has to be managed by the students by themselves by own. This implies that there is no way to initiate a Split Fare request through the Uber API, but users can continue to utilize this feature within the Uber app during the lifecycle of a request

XIII. CONCLUSION AND FUTURE WORK
With this application we are trying to solve the group ride share booking by the students to go and shop at most frequently visited grocery outlets. It’s always an open ground for various enhancements and many other features and updates can be added to the application. Some of the following enhancements can be taken up as a future work to the existing built application-
a.	Update the fixed grocery outlets to choose any grocery store of choice by user
b.	Implement the fare distribution among the students using PayPal or any other payment gateway.
c.	Increase the security on both client and server side to tackle against vulnerabilities.

Acknowledgment
We take this opportunity to extend our sincere gratitude to Prof. Rakesh Ranjan for his constant guidance and support throughout the course of the project. His step-by-step guidance led us to the successful completion of the project. We are thankful to the professor for introducing us to new and interesting ideas and being a source of inspiration through this journey.
We are also thankful to the San Jose State University for providing us with resources that helped us in accomplishing the project on time.


References
[1]	https://www.owasp.org/index.php/CSRFGuard_3_Installation
[2]	https://www.owasp.org/index.php/Capture-replay
[3]	https://www.owasp.org/index.php/Certificate_and_Public_Key_Pinning
[4]	https://developer.uber.com/v1/deep-linking/
[5]	http://programmerguru.com/android-tutorial/android-restful-webservice-tutorial-part-1/
[6]	http://mobiledeeplinking.org
[7]	https://developer.uber.com/v1/tutorials/
[8]	Class slides of Prof. Rakesh Ranjan
