package ada.tech.java.Service;

import ada.tech.java.Model.Envio;
import ada.tech.java.Repository.EnvioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j

public class ConsultarEnvioService {
    private final EnvioRepository envioRepository;

    //private final long idEnvio;

    public ConsultarEnvioService(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
        //this.idEnvio = idEnvio;
    }

    @Async
    public Optional<Envio> execute(long idEnvio){
        return envioRepository.findById(idEnvio);
    }
}
