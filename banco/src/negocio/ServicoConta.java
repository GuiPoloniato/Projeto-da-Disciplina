package negocio;

import java.util.List;
import acesso_a_dado.ContaDAO;

public class ServicoConta {

    private ContaDAO contaDAO = new ContaDAO();

    public void criarConta(Conta conta) {
        contaDAO.inserirConta(conta);
    }

    public List<Conta> listarContas() {
        return contaDAO.listarContas();
    }

    public void creditar(String numero, double valor) {
        Conta conta = contaDAO.buscarConta(numero);
        conta.creditar(valor);
        contaDAO.atualizarConta(conta);
    }

    public void debitar(String numero, double valor) {
        Conta conta = contaDAO.buscarConta(numero);
        conta.debitar(valor);
        contaDAO.atualizarConta(conta);
    }

    public void transferir(String origem, String destino, double valor) {
        Conta contaOrigem = contaDAO.buscarConta(origem);
        Conta contaDestino = contaDAO.buscarConta(destino);

        contaOrigem.debitar(valor);
        contaDestino.creditar(valor);

        contaDAO.atualizarConta(contaOrigem);
        contaDAO.atualizarConta(contaDestino);
    }
}
