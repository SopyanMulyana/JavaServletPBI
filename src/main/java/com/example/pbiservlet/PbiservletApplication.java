package com.example.pbiservlet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.pbiservlet.repository.Schedule;
import com.example.pbiservlet.repository.ScheduleRepository;
import com.example.pbiservlet.repository.User;
import com.example.pbiservlet.repository.UserRepository;



@SpringBootApplication
public class PbiservletApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PbiservletApplication.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		for (int i=0; i<5; i++) {
			scheduleRepository.save(new Schedule("Schedule #"+(i+1), "2017-09-08"));
		}
		for (int i=0; i<5; i++) {
			userRepository.save(new User("User #"+(i+1),"user"+(i+1)+"@mitrais.com","JP"));
		}
		
		
	}
}
