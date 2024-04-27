package ada.tech.java.Queue;

import ada.tech.java.Service.ConsultarEnvioService;
import ada.tech.java.payloads.Request.EnvioRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnvioConsumer {

    private final ObjectMapper objectMapper;
    private final ConsultarEnvioService consultarEnvioService;
    @RabbitListener(queues = {"${negocio.remover.fila}"})
    public void consumer(Message message , Channel channel) throws IOException {
        String stringMessage = new String(message.getBody());
        List<EnvioRequest> items =
                Arrays.asList(objectMapper.readValue(stringMessage, EnvioRequest[].class));

        consultarEnvioService.execute(items, true);
        log.info("Mensagem recebida {}", stringMessage);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

}


