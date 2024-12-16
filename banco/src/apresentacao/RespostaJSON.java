package apresentacao;

import com.google.gson.Gson;

public class RespostaJSON {

    private static final Gson gson = new Gson();

    public static String sucesso(String mensagem) {
        return gson.toJson(new Resposta("sucesso", mensagem));
    }

    public static String erro(String mensagem) {
        return gson.toJson(new Resposta("erro", mensagem));
    }

    private static class Resposta {
        String status;
        String mensagem;

        Resposta(String status, String mensagem) {
            this.status = status;
            this.mensagem = mensagem;
        }
    }
}
