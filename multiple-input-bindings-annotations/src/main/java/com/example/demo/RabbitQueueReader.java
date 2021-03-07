package com.example.demo;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class RabbitQueueReader
{
	@StreamListener(Sink.INPUT)
	public void readRabbit(String message)
	{
		System.out.println("From Rabbit -->"+ message);
	}
}

interface RabbitQueueProcessor{
	
	@Input("readRabbit")
    String input();
}