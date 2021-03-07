package com.example.demo;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(KafkaTopicProcessor.class)
public class KafkaTopicReader 
{
	@StreamListener
	public void readKafka(@Input("readKafka") KStream<String, String> message)
	{
		message.peek(((key, value) -> System.out.println(" value: "+value.toString())));
	}
}

interface KafkaTopicProcessor{
	
	String INPUT = "readKafka";
	
	@Input(KafkaTopicProcessor.INPUT)
    KStream<String, String> input();
}
