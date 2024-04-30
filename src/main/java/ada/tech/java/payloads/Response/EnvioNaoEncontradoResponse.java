package ada.tech.java.payloads.Response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class EnvioNaoEncontradoResponse {

    public static  String retornarMensagemDeErro(String id_envio) {
        return "Não foi localizado um envio com o Id " + id_envio;
    }
}
