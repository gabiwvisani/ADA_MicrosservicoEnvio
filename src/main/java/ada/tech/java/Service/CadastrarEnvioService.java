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
    private EnvioErrorResponse envioErrorResponse = new EnvioErrorResponse();


    @Async
    public CompletableFuture<Envio> execute(EnvioRequest envioRequest) {
        envioErrorResponse.setError("");
        if(envioRequest.getId_cliente().isBlank()||envioRequest.getId_cliente().isEmpty()||envioRequest.getId_cliente().equals("")) {
             envioErrorResponse.setId_compra(envioRequest.getId_compra());
            envioErrorResponse.setError("Id_cliente não enviado, erro na requisição.");
            throw new RuntimeException(envioErrorResponse.getError());

        }
        if(envioRequest.getId_compra().isBlank()||envioRequest.getId_compra().isEmpty()) {
            envioErrorResponse.setId_compra(envioRequest.getId_compra());
            envioErrorResponse.setError("Id_compra não enviado, erro na requisição.");
            throw new RuntimeException(envioErrorResponse.getError());
        }
        if(envioRequest.getCep().isBlank()||envioRequest.getCep().isEmpty()) {
            envioErrorResponse.setId_compra(envioRequest.getId_compra());
            envioErrorResponse.setError("Cep não enviado, erro na requisição.");
            throw new RuntimeException(envioErrorResponse.getError());
        }
        if(!envioErrorResponse.getError().isBlank()&&!envioErrorResponse.getError().isEmpty()) {
            envioPublisher.publish(envioErrorResponse);
            throw new RuntimeException(envioErrorResponse.getError());

            //return CompletableFuture.failedFuture(new RuntimeException());
        }
        try {
            //envioErrorResponse.setId_compra(envioRequest.getId_compra());
           // envioErrorResponse.setError("Cep não enviado, erro na requisição.");
           // envioPublisher.publish(envioErrorResponse);
        Envio envioCadastrado = modelMapper.map(envioRequest, Envio.class);
        envioCadastrado.setStatusEnviadoProCliente(true);
         envioCadastrado = envioRepository.save(envioCadastrado);
        return CompletableFuture.completedFuture(envioCadastrado);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar envio: " + e.getMessage());
        }
    }

}
