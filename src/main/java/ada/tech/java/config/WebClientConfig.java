package ada.tech.java.config;

import ada.tech.java.rest.EnvioController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {
    @Value("http://localhost:9080")
    private String envioUrl;
    @Bean
    WebClient webClient(){
        return WebClient.builder().baseUrl(envioUrl)
                .build();
    }
    @Bean
    EnvioController envioController (WebClient webClient){
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builder(WebClientAdapter
                        .forClient(webClient)).build();
        return httpServiceProxyFactory.createClient(EnvioController.class);
    }
}
