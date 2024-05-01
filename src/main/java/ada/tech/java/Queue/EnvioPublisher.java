package ada.tech.java.Queue;

import ada.tech.java.payloads.Response.EnvioErrorResponse;
//import ada.tech.java.payloads.Response.EnvioResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnvioPublisher {

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public void publish(List<EnvioErrorResponse> envioErrorResponses) {
        log.info("Mensagem enviada para o broker {}", envioErrorResponses);
        String mensagem = null;
        try {
            mensagem = objectMapper.writeValueAsString(envioErrorResponses);
            rabbitTemplate.convertAndSend(queue.getName(), mensagem);

        } catch (JsonProcessingException e) {
            log.error("Não foi possível enviar a mensagem ", e);
            throw new RuntimeException(e);
        }
    }
}
