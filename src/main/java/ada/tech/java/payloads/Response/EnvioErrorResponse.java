package ada.tech.java.payloads.Response;

import lombok.Data;

@Data
public class EnvioErrorResponse {

    private String id_Compra;
    private String error;

}
