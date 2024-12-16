package apresentacao;

import negocio.ServicoConta;
import negocio.Conta;
import negocio.ContaNormal;
import negocio.ContaDebEspecial;
import com.google.gson.Gson;

public class BancoRest {

    private ServicoConta servico = new ServicoConta();
    private Gson gson = new Gson();

    public String listarContas() {
        return gson.toJson(servico.listarContas());
    }

    public String criarConta(String numero, double saldo, double limite, boolean especial) {
        Conta conta = especial
                ? new ContaDebEspecial(numero, saldo, limite)
                : new ContaNormal(numero, saldo);
        servico.criarConta(conta);
        return "Conta criada com sucesso!";
    }
}
