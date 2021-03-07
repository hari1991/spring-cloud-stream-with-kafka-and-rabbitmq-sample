package com.example.demo;

import java.util.function.Consumer;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopicReader 
{
	@Bean
	public Consumer<KStream<String, Sensor>> readKafka()
	{
		return input -> {
			input.peek((key, value) -> {
				System.out.println(" value: "+value.toString());
			});
		};
	}
}
