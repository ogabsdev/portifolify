package br.com.portifolify.application.entrypoint.consumer;

import br.com.portifolify.application.config.broker.KafkaConsumerConfig;
import br.com.portifolify.application.dataprovider.impl.event.record.common.DomainEventEnvelope;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerDomainEventConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topics.customer-domain-event.name}",
            containerFactory = KafkaConsumerConfig.CUSTOMER_DOMAIN_EVENT_LISTENER_FACTORY)
    public void consume(ConsumerRecord<String, String> record) {
        try {
            objectMapper.readValue(record.value(), DomainEventEnvelope.class);
            log.info("Evento recebido: " + record);
        } catch (Exception e) {
            log.error("Erro ao consumir evento", e);
        }
    }

}
