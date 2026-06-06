package com.api.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import com.api.pojo.UserCredentials;
import static com.api.utils.ConfigManager.*;


import io.restassured.http.ContentType;

public class LoginAPITest {

	@Test
	public void loginAPITest() throws IOException {
		//Rest Assured Code
		
		//Read the property value that is going to be passed from terminal
		System.out.println("env key: " + System.getProperty("env"));
		
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
		given()
			.baseUri(getProperty("BASE_URI"))
			.and()
			.contentType(ContentType.JSON)
			.and()
			.log().headers()
			.and()
			.body(userCredentials)
			
		.when()
			.post("login")
			
		.then()
			.log().body()
			.statusCode(200)
			.and()
			.body("message",equalTo("Success"))
			.and()					
			.body(matchesJsonSchemaInClasspath("responseSchema/loginAPIResponseSchema.json"))
			.time(lessThan(1500L));
			
			
	}

}
