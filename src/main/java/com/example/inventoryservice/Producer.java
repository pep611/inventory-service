package com.example.inventoryservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer
{
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "booking-events";

    @Autowired //DEPENDENCY INJECTION PROMISE FULFILLED AT RUNTIME
    private KafkaTemplate<String, String> kafkaTemplate ;

    public void pubBookingEvent(BookingEvent bookingEvent) throws JsonProcessingException // LOGIN | REGISTER
    {
        // convert to JSON
        logger.info("Producing BookingEvent with bookingId: {}, status: {}", bookingEvent.getBookingId(), bookingEvent.getStatus());
        ObjectMapper objectMapper = new ObjectMapper();
        String datum = objectMapper.writeValueAsString(bookingEvent);

        logger.info(String.format("#### -> Producing message -> %s", datum));
        this.kafkaTemplate.send(TOPIC,"1", datum);
    }
}
