package ada.tech.java.Service;

import ada.tech.java.Model.Envio;
import ada.tech.java.Repository.EnvioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class CadastrarEnvioService {

private final EnvioRepository envioRepository;

    public CadastrarEnvioService(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
    }

    @Async
    public CompletableFuture<Envio> execute(Envio envio){
        Envio envioCadastrado = envioRepository.save(envio);
        return CompletableFuture.completedFuture(envioCadastrado);
    }

}
