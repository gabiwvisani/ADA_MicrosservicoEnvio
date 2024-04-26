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
    private long idenvio;
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
    private boolean enviadoProCliente;



    public Envio(long idCliente, long idCompra, String remetente, String enderecoRemetente, String destinatario,
                 String enderecoDestinatatio, String cidade,
                 String estado, String pais, String cep, boolean enviadoProCliente) {
        this.idCliente = idCliente;
        this.idCompra = idCompra;
        this.remetente = remetente;
        this.enderecoRemetente = enderecoRemetente;
        this.destinatario = destinatario;
        this.enderecoDestinatatio = enderecoDestinatatio;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
        this.enviadoProCliente = enviadoProCliente;
    }


    public Envio() {

    }
}
