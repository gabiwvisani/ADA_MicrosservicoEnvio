package ada.tech.java.rest;

import ada.tech.java.Model.Envio;
import ada.tech.java.Queue.EnvioPublisher;
import ada.tech.java.Service.CadastrarEnvioService;
import ada.tech.java.Service.ConsultarEnvioService;
import ada.tech.java.payloads.Request.AlteraStatusEnvioRequest;
import ada.tech.java.payloads.Request.EnvioRequest;
import ada.tech.java.payloads.Response.EnvioErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@RestController
//@RequiredArgsConstructor
@Slf4j
public class EnvioController {
    private final CadastrarEnvioService cadastrarEnvioService;
    private final ModelMapper modelMapper;
    private final ConsultarEnvioService consultarEnvioService;
    private final EnvioPublisher envioPublisher;
   // private final ListarEnviosService listarEnviosService;
   @Autowired
   public EnvioController(ModelMapper modelMapper, CadastrarEnvioService cadastrarEnvioService, ConsultarEnvioService consultarEnvioService, EnvioPublisher envioPublisher){
       this.modelMapper = modelMapper;
       this.cadastrarEnvioService = cadastrarEnvioService;
       this.consultarEnvioService =consultarEnvioService;
       this.envioPublisher = envioPublisher;
   }
    @Operation(summary = "Cadastrar Envio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Compra enviada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao tentar enviar uma compra"),
    })
    @PostMapping("/add/envio")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarEnvio(@RequestBody EnvioRequest envioRequest) {
        log.info("Requisição recebida para cadastrar envio: {}", envioRequest);
        try {
            Envio envioConvertido = modelMapper.map(envioRequest, Envio.class);
            cadastrarEnvioService.execute(envioConvertido);
        }catch(Exception e){
            EnvioErrorResponse envioErrorResponse = new EnvioErrorResponse (envioRequest.getId_compra(),"Contrato não enviado, erro na requisição.");
            envioPublisher.publish(envioErrorResponse);
        }
    }
    @Operation(summary = "Consultar envio de compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envio cadastrado"),
            @ApiResponse(responseCode = "404", description = "Envio não encontrado"),
    })
    @GetMapping("/consulta/envio/{id}")
    public Optional<Envio> buscarEnvioPorID(@PathVariable String id) {
        Optional<Envio> optionalEnvio = consultarEnvioService.execute(id);
        return Optional.of(optionalEnvio.get());
    }
    @Operation(summary = "Alterar status envio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alterado o status do envio"),
            @ApiResponse(responseCode = "404", description = "Envio não encontrado"),
    })
    @PatchMapping("/altera/status/envio/{id}")
    public void alterarValorQuarto(
            @PathVariable String id,
            @RequestBody AlteraStatusEnvioRequest request) throws Exception {
            Optional<Envio> optionalEnvio =  consultarEnvioService.execute(id);
            Envio envioModificado = optionalEnvio.get();
            envioModificado.setStatusEnviadoProCliente(request.isStatusEnviadoProCliente());
            cadastrarEnvioService.execute(envioModificado);
    }


}
