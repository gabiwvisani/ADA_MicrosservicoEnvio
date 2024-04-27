package ada.tech.java.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvioRMQFila {
    @Value("$envio_produto")
    private String fila;
    @Bean
    public Queue queue(){
        return new Queue(fila, true);
    }
}