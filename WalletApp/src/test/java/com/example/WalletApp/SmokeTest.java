package com.example.WalletApp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mvc.WalletController; 

@SpringBootTest 
public class SmokeTest {
	@Autowired
	private WalletController controller;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
