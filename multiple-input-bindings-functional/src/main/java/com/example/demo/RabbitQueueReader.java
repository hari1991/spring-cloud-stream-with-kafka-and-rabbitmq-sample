package com.example.demo;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
public class RabbitQueueReader 
{
	@Bean
	public Consumer<Message<String>> readRabbit()
	{
		return message -> {
			System.out.println("From Rabbit -->"+ message.getPayload());
			System.out.println("From Rabbit -->"+ message.getHeaders());
			Long deliveryTag = null;
			Channel channel = null;
			for(Entry<String, Object> header: message.getHeaders().entrySet())
			{
				System.out.println(header.getKey()+" - "+header.getValue());
				if(header.getKey().equalsIgnoreCase(AmqpHeaders.CHANNEL))
				{
					channel = (Channel) header.getValue();
				}
				if(header.getKey().equalsIgnoreCase(AmqpHeaders.DELIVERY_TAG))
				{
					deliveryTag = (Long) header.getValue();
				}
			}
			System.out.println("Delivery Tag is : "+deliveryTag);
			if(message.getPayload().contains("good"))
			{
				try {
					channel.basicAck(deliveryTag, true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				try {
					channel.basicNack(deliveryTag, true, false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
}
