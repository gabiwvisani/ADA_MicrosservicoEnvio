package ada.tech.java.payloads.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnvioErrorResponse {

    private String id_compra;
    private String error;

}
