package com.example.demo;

import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JenkinJobCalls {
	
	@PostMapping("/jenkins/{jobname}")
	public ResponseEntity<String> getBuild(@PathVariable("jobname") String jobname) throws Exception {
		 String url=AppConstants.url+"/"+jobname+"/build?token="+AppConstants.token;	
	      URL obj=new URL(url);
			HttpURLConnection con=(HttpURLConnection) obj.openConnection();
			Authenticator.setDefault (new Authenticator() {
	    	    protected PasswordAuthentication getPasswordAuthentication() {
	    	        return new PasswordAuthentication (AppConstants.username, AppConstants.password.toCharArray());
	    	    }
	    	});
			Thread.sleep(10000);
			
			System.out.println("Job status is " +con.getResponseCode());
			System.out.println("execution ended");
			con.disconnect();
			
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}

}
