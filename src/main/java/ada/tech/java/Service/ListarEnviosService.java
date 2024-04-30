package ada.tech.java.Service;

import ada.tech.java.Model.Envio;
import ada.tech.java.Repository.EnvioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
@Slf4j
@Service
public class ListarEnviosService {

    private final EnvioRepository envioRepository;

    public ListarEnviosService(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
    }

    public List<Envio> listarTodos() {
        return envioRepository.findAll();
    }
}
