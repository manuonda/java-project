package com.pattern.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.pattern.entity.Outbox;
import com.pattern.publisher.MessagePublisher;
import com.pattern.repository.OutboxRepository;

import lombok.extern.slf4j.Slf4j;


/*
 * Service to encargado de publicar los mensajes en el topic de kafka
 * cada cierto tiempo en este caso lo que hace es buscar los mensajes
 * que no han sido procesados y los publica en el topic de kafka
 * durane 60 segundos
 */
@Service
@Slf4j
public class OrderPollerService {

    private final MessagePublisher messagePublisher;
    private final OutboxRepository outboxRepository;

    public OrderPollerService(MessagePublisher messagePublisher,
    OutboxRepository outboxRepository){
        this.messagePublisher = messagePublisher;
        this.outboxRepository = outboxRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void pollOutBoxMessagesAndPublish(){
        //1- fetch unprocessed record
        List<Outbox> unprocessedRecords = this.outboxRepository.findByProcessed(Boolean.FALSE);
        log.info("unprocessed record count : {}", unprocessedRecords.size()); 
        // 2 publish record to kafka/queue
        unprocessedRecords.forEach( outbox -> {
            try {
                this.messagePublisher.publish(outbox.getPayload());
                outbox.setProcessed(Boolean.TRUE);
                this.outboxRepository.save(outbox);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
