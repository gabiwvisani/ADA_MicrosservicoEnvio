package ada.tech.java.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_envio;
    private String id_cliente;
    private String id_compra;
    private String destinatario;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private boolean statusEnviadoProCliente;

    public Envio(String id_cliente, String id_compra, String destinatario, String rua, String bairro, String cidade,
                 String estado, String cep, boolean statusEnviadoProCliente) {
        this.id_cliente = id_cliente;
        this.id_compra = id_compra;
        this.destinatario = destinatario;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.statusEnviadoProCliente = statusEnviadoProCliente;
    }

    public Envio() {

    }
}
