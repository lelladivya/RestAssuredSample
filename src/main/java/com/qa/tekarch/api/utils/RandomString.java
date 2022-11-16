package com.qa.tekarch.api.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomString {
	
	public String getRandomString(int noOfCharacters) {
		String generatedString=RandomStringUtils.randomAlphabetic(noOfCharacters);
		return generatedString;
	}
	public String getRandomInt(int noOfCharacters) {
		String generatedString=RandomStringUtils.randomNumeric(noOfCharacters);
		return generatedString;
	}


}
