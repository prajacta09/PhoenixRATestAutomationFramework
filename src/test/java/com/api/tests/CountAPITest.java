package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;

public class CountAPITest {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//	}
	
	@Test
	public void verifyCountAPIResponse() {
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.header("Authorization",getToken(FD))	//raw header
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(200)
		.body("message", Matchers.equalTo("Success"))
		.time(Matchers.lessThan(1000L))
		.body("data", Matchers.notNullValue())
		.body("data.size()", Matchers.equalTo(3))
		.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
		.body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
		.body("data.key", Matchers.containsInAnyOrder("created_today","pending_for_delivery","pending_fst_assignment"));
//		.body(matchesJsonSchemaInClasspath("response-schema/countAPIResponseSchema-FD.json"));
	}
	
	@Test
	public void countAPITest_MissingAuthToken() {
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(401);

	}

}
