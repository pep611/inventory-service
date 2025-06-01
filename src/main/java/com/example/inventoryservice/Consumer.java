package com.example.inventoryservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.Instant;

@Service
public class Consumer {
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private Producer producer;

    @KafkaListener(topics = "inventory-events", groupId = "inventory-group")
    public void consumeBookingEvents(String message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InventoryEvent datum = mapper.readValue(message, InventoryEvent.class);
        logger.info(String.format("#### -> Received inventory update message: -> %s", datum));

        InventoryModel bus = inventoryRepository.findByBusNumber(datum.getBusNumber());
        if (bus != null) {
            int updatedSeats = bus.getAvailableSeats() - datum.getNoOfSeats();
            bus.setAvailableSeats(updatedSeats);
            bus.setLastUpdatedDate(Instant.now());
            // Optionally, store bookingNumber if your entity supports it
            inventoryRepository.save(bus);
            logger.info("Updated inventory With Booking-Id {} Bus {} with {} seats booked",datum.getBookingId(), datum.getBusNumber(), datum.getNoOfSeats());

            String bookingNumber = String.valueOf(datum.getBookingId());
            logger.info("Booking Number: {}", bookingNumber);
            BookingEvent event = new BookingEvent();

            event.setBookingId(bookingNumber);
            logger.info("Set bookingId in BookingEvent: {}", event.getBookingId());
            event.setStatus("Confirmed");
            try {
                producer.pubBookingEvent(event);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
