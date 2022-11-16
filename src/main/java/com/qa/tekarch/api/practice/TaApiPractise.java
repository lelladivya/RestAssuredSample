package com.qa.tekarch.api.practice;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.qa.tekarch.api.utils.RandomString;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TaApiPractise {
	String tokenvalue=null;
	RandomString randomString;

	@Test
	public void getLogin() throws InterruptedException, Exception {
		RestAssured. baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		
		
//		JSONObject requestBody=new JSONObject();
//		requestBody.put("username", "divya.feb22@ta.com");
//		requestBody.put("password","divya@tekarch123");
		
		JSONParser parser=new JSONParser();
		FileReader reader=new FileReader("C:\\Users\\Rajesh\\eclipse-workspace\\RestAssuredDemo\\src\\test\\resources\\testdata.json");
		Object obj=parser.parse(reader);
		JSONObject requestBody =(JSONObject)obj;
		
		System.out.println(requestBody);
	Response response=	RestAssured.given().
		header("Content-Type","application/json").
		body(requestBody.toString()).
		when().
		post("/login").then().statusCode(201).extract().response();
	System.out.println(response.asString());
	System.out.println(response.getBody());
	tokenvalue = response.jsonPath().get("token[0]").toString().replaceAll("\\[", "");;
	System.out.println("token value is : "+tokenvalue);
	Thread.sleep(3000);
	}
	@Test
	public void getUserData() {
		RestAssured. baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		System.out.println("token value in this method : "+tokenvalue);
	RestAssured.given().
		header("token",tokenvalue).header("Content-Type","application/json").
		when().get("/getdata").then().statusCode(200);
		//System.out.println(response.asString());
		
	}
	@Test(dependsOnMethods="getLogin")
	public void addUserData() {
		randomString=new RandomString();
		RestAssured.baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		JSONObject request=new JSONObject();
		request.put("accountno", randomString.getRandomInt(7));
		request.put("departmentno",randomString.getRandomInt(1) );
		request.put("pincode", randomString.getRandomInt(7));
		request.put("salary", randomString.getRandomInt(7));
		
		System.out.println("token value in add user data is : "+tokenvalue);
		Response response=RestAssured.given().
		header("token",tokenvalue).header("Content-Type","application/json").
		body(request).
		when().
		post("https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/addData").
		then().
		statusCode(201).log().all()
		.extract().response();
		
//System.out.println(response.getBody().asPrettyString());

	}
}
