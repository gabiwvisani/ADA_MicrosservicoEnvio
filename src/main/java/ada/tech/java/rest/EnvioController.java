package ada.tech.java.rest;

import ada.tech.java.Model.Envio;
import ada.tech.java.Queue.EnvioPublisher;
import ada.tech.java.Repository.EnvioRepository;
import ada.tech.java.Service.AlterarStatusEnvioService;
import ada.tech.java.Service.CadastrarEnvioService;
import ada.tech.java.Service.ConsultarEnvioService;
import ada.tech.java.Service.ListarEnviosService;
import ada.tech.java.payloads.Request.AlteraStatusEnvioRequest;
import ada.tech.java.payloads.Request.EnvioRequest;
import ada.tech.java.payloads.Response.EnvioErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@Slf4j
public class EnvioController {
    private final CadastrarEnvioService cadastrarEnvioService;
    private final ModelMapper modelMapper;
    private final ConsultarEnvioService consultarEnvioService;
    private final EnvioPublisher envioPublisher;
    private final ListarEnviosService listarEnviosService;
    private final AlterarStatusEnvioService alterarStatusEnvioService;
    private final EnvioRepository envioRepository;

   @Autowired
   public EnvioController(EnvioRepository envioRepository,ModelMapper modelMapper, CadastrarEnvioService cadastrarEnvioService, ConsultarEnvioService consultarEnvioService, EnvioPublisher envioPublisher, ListarEnviosService listarEnviosService, AlterarStatusEnvioService alterarStatusEnvioService){
       this.envioRepository=envioRepository;
       this.modelMapper = modelMapper;
       this.cadastrarEnvioService = cadastrarEnvioService;
       this.consultarEnvioService =consultarEnvioService;
       this.envioPublisher = envioPublisher;
       this.listarEnviosService = listarEnviosService;
       this.alterarStatusEnvioService = alterarStatusEnvioService;
   }


    @Operation(summary = "Cadastrar Envio")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Compra enviada com sucesso"),
//            @ApiResponse(responseCode = "400", description = "Erro ao tentar enviar uma compra"),
//           @ApiResponse(responseCode = "422", description = "Erro ao tentar enviar uma compra")
//    })
    @PostMapping("/add/envio")
    public ResponseEntity<String> cadastrarEnvio(@RequestBody EnvioRequest envioRequest) {
        log.info("Requisição recebida para cadastrar envio: {}", envioRequest);
        try {
            cadastrarEnvioService.execute(envioRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Compra enviada com sucesso");
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao tentar enviar uma compra: Informações insuficientes");
        }
    }


    @Operation(summary = "Consultar envio de compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envio cadastrado"),
            @ApiResponse(responseCode = "404", description = "Envio não encontrado"),
    })
    @GetMapping("/consulta/envio/{id}")
    public ResponseEntity<Envio> buscarEnvioPorID(@PathVariable String id) throws InterruptedException, ExecutionException, ExecutionException {
        Future<Optional<Envio>> futureEnvio = consultarEnvioService.execute(id);
        Optional<Envio> envio = futureEnvio.get();
        return envio.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Consultar todos envios")
    @GetMapping("/consulta/envios")
    public List<Envio> buscarEnvioPorID() throws InterruptedException, ExecutionException, ExecutionException {
        List<Envio> listaComTodos = envioRepository.findAll();
        return listaComTodos;
    }


    @Operation(summary = "Alterar status envio")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Alterado o status do envio"),
//            @ApiResponse(responseCode = "404", description = "Envio não encontrado"),
//    })
    @PatchMapping("/altera/status/envio/{id}")
    public ResponseEntity<?> alterarStatusEnvio(
            @PathVariable String id,
            @RequestBody AlteraStatusEnvioRequest request) {
        try {
            String alterado = String.valueOf(alterarStatusEnvioService.alterarStatusEnvio(id, request.isStatusEnviadoProCliente()));
            if (alterado.isEmpty()||alterado.isBlank()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok().build();
            }
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @Operation(summary = "Listar todos os envios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de envios retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao listar envios"),
    })
    @GetMapping("/envios")
    public ResponseEntity<List<Envio>> listarEnvios() {
        try {
            List<Envio> envios = listarEnviosService.listarTodos();
            return ResponseEntity.ok(envios);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
