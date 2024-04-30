package ada.tech.java.payloads.Response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EnvioErrorResponse {

    private String id_compra;
    private String error;

}
