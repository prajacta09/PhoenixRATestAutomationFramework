package com.api.utils;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import com.api.constant.Role;
import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	//private constructor -- so no one can create obj of utility class
	private AuthTokenProvider() {
		
	}

	public static String getToken(Role role) {
		// Make the request for the login api and we want to extract the token and print it in console
		
	UserCredentials userCredentials = null;
	
	if(role == Role.FD) {
		userCredentials = new UserCredentials("iamfd", "password");
	}
	if(role == Role.SUP) {
		userCredentials = new UserCredentials("iamsup", "password");
	}
	if(role == Role.ENG) {
		userCredentials = new UserCredentials("iameng", "password");
	}
	if(role == Role.QC) {
		userCredentials = new UserCredentials("iamqc", "password");
	}
		
	String token =	given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.contentType(ContentType.JSON)
		.and()
		.body(userCredentials)
		
	.when()
		.post("login")
		
	.then()
		.log()
		.ifValidationFails()
		.statusCode(200)
		.body("message", equalTo("Success"))
		.extract()
		.body()
		.jsonPath()
		.getString("data.token");
		
	System.out.println("-------------------------");
	System.out.println(token);

	return token;
	}

}
