package br.com.portifolify.application.dataprovider.impl.event.transactionaloutbox;

import br.com.portifolify.application.config.threadpool.MessageRelayThreadPoolConfig;
import br.com.portifolify.application.dataprovider.impl.persistence.dao.OutboxDAOImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class MessageRelay {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final OutboxDAOImpl outboxDAO;

    @SneakyThrows
    @Async(MessageRelayThreadPoolConfig.THREAD_NAME)
    public void publishEventAsynchronously(Outbox outbox) {
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>(outbox.getTopicName(), outbox.getEventData());

        var result = kafkaTemplate.send(producerRecord).get().getRecordMetadata();

        Long offset = result.offset();
        Integer partition = result.partition();

        outbox.markAsProcessed(offset, partition);
        outboxDAO.update(outbox);
    }

}
