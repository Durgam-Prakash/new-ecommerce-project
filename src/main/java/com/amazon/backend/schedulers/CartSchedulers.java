package com.amazon.backend.schedulers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazon.backend.repository.CartRepository;

@Service
public class CartSchedulers {

	@Autowired
	private CartRepository cartRepository;
	

	
	@Transactional
	@Scheduled(cron = " 0 * * * * ?")
	public void sendCartReminders() {
		System.out.println("Running cart reminders methods...1");

		List<Object[]> cartData = cartRepository.getCartReminders();
		
		for(Object[] row : cartData) {
			System.out.println(row[0]);
			}
		}
}
