package ada.tech.java.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class EnvioRequest {
    private long idCliente;
    private long idCompra;
    private String remetente;
    private String enderecoRemetente;
    private String destinatario;
    private String enderecoDestinatatio;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;


}
