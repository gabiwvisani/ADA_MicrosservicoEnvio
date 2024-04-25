package ada.tech.java.Service;

import ada.tech.java.Model.Envio;
import ada.tech.java.Repository.EnvioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CadastrarEnvioService {

private final EnvioRepository envioRepository;

private final Envio envio;
    public CadastrarEnvioService(EnvioRepository envioRepository, Envio envio) {
        this.envioRepository = envioRepository;
        this.envio = envio;
    }



}
