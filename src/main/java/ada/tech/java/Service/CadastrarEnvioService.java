package ada.tech.java.Service;

import ada.tech.java.Model.Envio;
import ada.tech.java.Queue.EnvioPublisher;
import ada.tech.java.Repository.EnvioRepository;
import ada.tech.java.payloads.Response.EnvioErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ada.tech.java.payloads.Request.EnvioRequest;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class CadastrarEnvioService {

private final EnvioRepository envioRepository;
    private final ModelMapper modelMapper;
    private final  EnvioPublisher envioPublisher;

    public CadastrarEnvioService(EnvioRepository envioRepository,ModelMapper modelMapper, EnvioPublisher envioPublisher) {
        this.envioRepository = envioRepository;
        this.modelMapper= modelMapper;
        this.envioPublisher = envioPublisher;
    }

    @Async
    public CompletableFuture<EnvioErrorResponse> execute(EnvioRequest envioRequest) {
        EnvioErrorResponse envioErrorResponse = new EnvioErrorResponse();

        if (envioRequest.getId_cliente().isBlank() || envioRequest.getId_compra().isBlank() || envioRequest.getCep().isBlank()) {
            envioErrorResponse.setId_compra(envioRequest.getId_compra());
            if (envioRequest.getId_cliente().isBlank()) {
                envioErrorResponse.setError("Id_cliente não enviado, erro na requisição.");
            } else if (envioRequest.getId_compra().isBlank()) {
                envioErrorResponse.setError("Id_compra não enviado, erro na requisição.");
            } else {
                envioErrorResponse.setError("Cep não enviado, erro na requisição.");
            }
            envioPublisher.publish(envioErrorResponse);
            return CompletableFuture.completedFuture(envioErrorResponse);
        }

        try {
            Envio envioCadastrado = modelMapper.map(envioRequest, Envio.class);
            envioCadastrado.setStatusEnviadoProCliente(true);
            envioCadastrado = envioRepository.save(envioCadastrado);
            return CompletableFuture.completedFuture(null); // Retorna null indicando sucesso
        } catch (Exception e) {
            envioErrorResponse.setError("Erro ao cadastrar envio: " + e.getMessage());
            envioPublisher.publish(envioErrorResponse);
            return CompletableFuture.completedFuture(envioErrorResponse);
        }
    }
}
