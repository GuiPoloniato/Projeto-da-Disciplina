package apresentacao;

import negocio.Conta;
import negocio.ContaNormal;
import negocio.ContaDebEspecial;
import apresentacao.ContasXMLBuilder;
import acesso_a_dado.AcessoADado;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Banco {

    // Estrutura de dados padrão inicial
    private List<Conta> contas;

    // Construtor do Banco
    public Banco() {
        contas = inicializarContas();
    }

    // Método para criar lista de contas (usando ArrayList por padrão)
    // Implementa Singleton
    private List<Conta> inicializarContas() {
        if (contas == null) {
            contas = new ArrayList<>(); // Substitua por LinkedList, HashSet ou TreeSet se necessário
        }
        return contas;
    }

    // Implementação de Singleton com LinkedList
    /*
    private List<Conta> inicializarContas() {
        if (contas == null) {
            contas = new LinkedList<>();
        }
        return contas;
    }
    */

    // Implementação de Singleton com HashSet
    /*
    private HashSet<Conta> inicializarContas() {
        if (contas == null) {
            contas = new HashSet<>();
        }
        return contas;
    }
    */

    // Implementação de Singleton com TreeSet
    /*
    private TreeSet<Conta> inicializarContas() {
        if (contas == null) {
            contas = new TreeSet<>();
        }
        return contas;
    }
    */

    // Método para criar uma conta
    public void criaConta(Conta c) {
        contas.add(c);
    }

    // Método para remover uma conta pelo número
    public void removeConta(String numero) {
        Iterator<Conta> iterator = contas.iterator();
        while (iterator.hasNext()) {
            Conta c = iterator.next();
            if (c.getNumero().equals(numero)) {
                iterator.remove();
                break;
            }
        }
    }

    // Método para creditar valor em uma conta
    public void creditaConta(String numero, double valor) {
        for (Conta c : contas) {
            if (c.getNumero().equals(numero)) {
                c.creditar(valor);
                break;
            }
        }
    }

    // Método para debitar valor em uma conta
    public void debitaConta(String numero, double valor) {
        for (Conta c : contas) {
            if (c.getNumero().equals(numero)) {
                c.debitar(valor);
                break;
            }
        }
    }

    // Método para transferir valores entre contas
    public void transfereConta(String numeroOrigem, String numeroDestino, double valor) {
        debitaConta(numeroOrigem, valor);
        creditaConta(numeroDestino, valor);
    }

    // Método para listar contas no console
    public void listaContas() {
        for (Conta c : contas) {
            System.out.printf("Conta: %s | Saldo: %.2f\n", c.getNumero(), c.getSaldo());
        }
    }

    // Método para listar contas em formato XML
    public void listaContasXML() {
        Iterator<Conta> iterator = contas.iterator();
        ContasXMLBuilder contasXml = new ContasXMLBuilder();
        String resultado = contasXml.listagemContas(iterator);
        System.out.println(resultado);
    }

    // Método para listar contas em formato PDF (não implementado totalmente)
    public void listaContasPDF() {
        System.out.println("Funcionalidade de listar contas em PDF ainda não implementada.");
    }

    public static void main(String[] args) {
        Banco banco = new Banco();
        AcessoADado acesso = new AcessoADado();

        // Criando contas
        Conta c1 = new ContaNormal();
        c1.setNumero("1654-3");
        c1.setSaldo(500);

        ContaDebEspecial c2 = new ContaDebEspecial();
        c2.setNumero("4067-6");
        c2.setSaldo(2500);
        c2.setLimite(1000.67);

        ContaDebEspecial c3 = new ContaDebEspecial("6578-9", 2500, 5050);

        // Adicionando contas ao banco
        banco.criaConta(c1);
        banco.criaConta(c2);
        banco.criaConta(c3);

        // Operações
        banco.listaContas();
        banco.creditaConta("6578-9", 1000);
        banco.listaContas();
        banco.debitaConta("6578-9", 500);
        banco.listaContas();
        banco.transfereConta("6578-9", "1654-3", 500.00);
        banco.listaContas();
        banco.listaContasXML();
    }
}
