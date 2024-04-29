package ada.tech.java.Service;

import ada.tech.java.Model.Envio;
import ada.tech.java.Repository.EnvioRepository;
import ada.tech.java.payloads.Request.EnvioRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CadastrarEnvioService {

private final EnvioRepository envioRepository;

    public CadastrarEnvioService(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
    }

    @Async
    public void execute(Envio envio){
        envioRepository.save(envio);
    }

}
