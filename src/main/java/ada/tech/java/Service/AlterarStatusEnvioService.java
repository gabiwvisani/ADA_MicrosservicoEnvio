package ada.tech.java.Service;

import ada.tech.java.Model.Envio;
import ada.tech.java.Repository.EnvioRepository;
import ada.tech.java.payloads.Response.EnvioNaoEncontradoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@AllArgsConstructor
@Service
public class AlterarStatusEnvioService {
    private final EnvioRepository envioRepository;
    private final ConsultarEnvioService consultarEnvioService;
    private final CadastrarEnvioService cadastrarEnvioService;

    public String alterarStatusEnvio(String id_envio, boolean novoStatus) {
        Optional<Envio> optionalEnvio = consultarEnvioService.execute(id_envio);

        if (optionalEnvio.isPresent()) {
            Envio envio = optionalEnvio.get();
            envio.setStatusEnviadoProCliente(novoStatus);
            cadastrarEnvioService.execute(envio);
            return null;
        } else {
            return EnvioNaoEncontradoResponse.retornarMensagemDeErro(id_envio);
        }
    }


}
