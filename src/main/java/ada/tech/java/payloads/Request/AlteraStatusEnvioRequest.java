package ada.tech.java.payloads.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
//@Data
@Getter
@Setter
public class AlteraStatusEnvioRequest {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String id_compra;
    private boolean statusEnviadoProCliente;
}
