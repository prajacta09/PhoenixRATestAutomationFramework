package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.Role;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.Header;

public class UserDetailsAPITest {

	@Test
	public void userDetailsAPITest() throws IOException {
		
//		ConfigManager configManager = new ConfigManager();
		
		Header headerOne = new Header("Authorization",
				getToken(Role.FD));

		String fName = given()
					.baseUri(getProperty("BASE_URI"))
					.and()
					.header(headerOne)
				.when()
					.get("userdetails")
				.then()
					.log()
					.body()
					.statusCode(200)
					.and()
					.body("message", equalTo("Success"))
					.time(lessThan(1500L)).extract()
//		.			path("data.first_name");
					.jsonPath().getString("data.first_name");

				System.out.println(fName);
	}
}
