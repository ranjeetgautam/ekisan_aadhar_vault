	package com.aadhar.vault;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Provider;
import java.security.Security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AadharVaultApplication {

	public static void main(String[] args) {
		
		 Security.addProvider(new BouncyCastleProvider());

	        // List all security providers to verify
	        System.out.println("Security Providers:");
	        for (Provider provider : Security.getProviders()) {
	            System.out.println(provider.getName());
	        }
		SpringApplication.run(AadharVaultApplication.class, args);
	}

}
