package ada.tech.java.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="envio")
public class Envio {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id_envio", nullable=false)
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
                 String estado, String cep ) {
        this.id_cliente = id_cliente;
        this.id_compra = id_compra;
        this.destinatario = destinatario;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.statusEnviadoProCliente = true;
    }

//    public Envio() {
//
//    }
}
