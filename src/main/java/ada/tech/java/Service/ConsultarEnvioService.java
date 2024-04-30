package ada.tech.java.Service;

import ada.tech.java.Model.Envio;
import ada.tech.java.Repository.EnvioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j

public class ConsultarEnvioService {
    private final EnvioRepository envioRepository;

    public ConsultarEnvioService(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
    }

    @Async
    public CompletableFuture<Optional<Envio>> execute(String idEnvio) {
        Optional<Envio> envio = envioRepository.findById(idEnvio);
        return CompletableFuture.completedFuture(envio);
    }
}
