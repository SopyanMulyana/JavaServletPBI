package com.example.pbiservlet.repository;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String topic;
	private String date;
	
	public Schedule() {
		// TODO Auto-generated constructor stub
	}
	
	public Schedule(String topic, String date) {
		this.topic=topic;
		this.date=date;
	}

	public Long getId() {
		return id;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
}
