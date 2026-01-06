package com.amazon.backend.utils;

import java.util.Random;

public class AuthUtility {
	
	public static int generateOtp() {
		
		Random random = new Random();
		return 100000 + random.nextInt(999999);
	}

}
