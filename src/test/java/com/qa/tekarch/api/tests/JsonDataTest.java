package com.qa.tekarch.api.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;






public class JsonDataTest {

	@Test
	public void jsontest() throws IOException, ParseException {
		JSONParser parser=new JSONParser();
		FileReader reader=new FileReader("C:\\Users\\Rajesh\\eclipse-workspace\\RestAssuredDemo\\src\\test\\resources\\testdata.json");
		Object obj=parser.parse(reader);
		JSONObject json=(JSONObject)obj;
		
//		JSONObject json=new JSONObject();
//		json.put("name", "morpheus");
//		json.put("job", "leader");

		RestAssured.given()
		.header("Content-Type","application/json")
		.body(json.toJSONString())
		.when()
		.post("https://reqres.in/api/users")
		.then()
		.statusCode(201)
		.extract().response();
	}
	@Test
	public void jsontest2() {
		Response res=RestAssured.given()
		.get("https://reqres.in/api/users?page=2")
		.then()
		.statusCode(200)
		.extract().response();
		System.out.println(res);
		System.out.println(res.getBody());
	}
}
