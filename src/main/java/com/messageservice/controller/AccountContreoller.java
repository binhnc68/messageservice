package com.messageservice.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messageservice.model.AccountDTO;
import com.messageservice.model.MessageDTO;
import com.messageservice.model.StatisticDTO;


@RestController
@RequestMapping("/account")
public class AccountContreoller {

	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	
	@PostMapping("/new")
	public AccountDTO create(@RequestBody  AccountDTO account) {
		
		StatisticDTO statisticDTO = new StatisticDTO("account:" + account.getEmail() + " create date: ", new Date());
		
		// send notification
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setTo(account.getEmail());
		messageDTO.setToName(account.getName());
		messageDTO.setSubject("Subject");
		messageDTO.setContent("Content");
		System.out.println("test 1" + messageDTO.getToName());
		kafkaTemplate.send("notification", messageDTO);
		kafkaTemplate.send("statistic", statisticDTO);
		
		
		return account;
		
	}

}
