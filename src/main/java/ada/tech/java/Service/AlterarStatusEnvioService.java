package ada.tech.java.Service;

import ada.tech.java.Model.Envio;
import ada.tech.java.Repository.EnvioRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@Service
public class AlterarStatusEnvioService {
    private final EnvioRepository envioRepository;
    private final ConsultarEnvioService consultarEnvioService;
    private final CadastrarEnvioService cadastrarEnvioService;

    @Async
    public CompletableFuture<Optional<Envio>> alterarStatusEnvio(String id_envio, boolean novoStatus) {
        CompletableFuture<Optional<Envio>> futureEnvio = consultarEnvioService.execute(id_envio);

        try {
            Optional<Envio> envio = futureEnvio.get();
            if (envio.isPresent()) {
                envio.get().setStatusEnviadoProCliente(novoStatus);
                envioRepository.save(envio.get());
                return futureEnvio;
            } else {
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {

            return null;
        }

    }
}
