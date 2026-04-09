package com.api.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;

public class LoginAPITest {

	@Test
	public void loginAPITest() {
		//Rest Assured Code
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
		given()
			.baseUri("http://64.227.160.186:9000/v1")
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
