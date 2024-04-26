package ada.tech.java.Service;

import ada.tech.java.Model.Envio;
import ada.tech.java.Repository.EnvioRepository;
import ada.tech.java.payloads.EnvioRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CadastrarEnvioService {

private final EnvioRepository envioRepository;

private final EnvioRequest envioRequest;


private final Envio envio;
    public CadastrarEnvioService(EnvioRepository envioRepository, EnvioRequest envioRequest, Envio envio) {
        this.envioRepository = envioRepository;
        this.envioRequest = envioRequest;
        this.envio = envio;
    }

    @Async
    public void execute(Envio envio){
        envioRepository.save(envio);
    }

}
