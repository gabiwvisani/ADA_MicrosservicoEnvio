package ada.tech.java.rest;

import ada.tech.java.Service.CadastrarEnvioService;
import ada.tech.java.payloads.Request.EnvioRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EnvioController {
    private final CadastrarEnvioService cadastrarEnvioService;
    private final ListarEnviosService listarEnviosService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarEnvio(@RequestBody EnvioRequest envioRequest) {
        log.info("Requisição recebida para cadastrar envio: {}", envioRequest);
        cadastrarEnvioService.execute(convertEnvioRequestToEnvio(envioRequest));
    }

}
