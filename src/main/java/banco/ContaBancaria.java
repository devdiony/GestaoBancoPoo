package banco;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma Conta Bancária.
 * Gerencia seu próprio saldo e extrato (lista de operações).
 */
public class ContaBancaria {

    private String numeroConta;
    private String tipoConta; // Ex: Corrente, Poupança, Empresarial
    private double saldo;
    private Usuario dono; // Associação com o usuário
    private List<Operacao> extrato; // Lista de operações (depósitos/saques)

    public ContaBancaria(String numeroConta, String tipoConta, Usuario dono) {
        this.numeroConta = numeroConta;
        this.tipoConta = tipoConta;
        this.dono = dono;
        this.saldo = 0.0; // Saldo inicial é zero
        this.extrato = new ArrayList<>();
    }

    /**
     * Tenta realizar um depósito.
     * @param valor O valor a ser depositado (deve ser > 0)
     * @return true se o depósito foi bem-sucedido, false caso contrário.
     */
    public boolean depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            // Adiciona a operação ao extrato
            this.extrato.add(new Operacao("Depósito", valor));
            return true;
        }
        System.out.println("Erro: Valor de depósito deve ser positivo.");
        return false;
    }

    /**
     * Tenta realizar um saque.
     * @param valor O valor a ser sacado (deve ser > 0 e <= saldo)
     * @return true se o saque foi bem-sucedido, false caso contrário.
     */
    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Erro: Valor de saque deve ser positivo.");
            return false;
        }
        if (valor > this.saldo) {
            System.out.println("Erro: Saldo insuficiente.");
            return false;
        }

        this.saldo -= valor;
        // Adiciona a operação ao extrato
        this.extrato.add(new Operacao("Saque", valor));
        return true;
    }

    /**
     * Imprime o extrato completo da conta.
     */
    public void consultarExtrato() {
        System.out.println("\n--- Extrato da Conta " + this.numeroConta + " (" + this.tipoConta + ") ---");
        System.out.println("Dono: " + this.dono.getNome());
        if (this.extrato.isEmpty()) {
            System.out.println("Nenhuma operação realizada.");
        } else {
            for (Operacao op : this.extrato) {
                System.out.println(op); // Chama o toString() da Operacao
            }
        }
        System.out.printf("Saldo Atual: R$ %.2f\n", this.saldo);
        System.out.println("-----------------------------------------------------");
    }

    // Getters para acesso aos dados (Encapsulamento)
    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Usuario getDono() {
        return dono;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    @Override
    public String toString() {
        return String.format("Conta [Nº: %s, Tipo: %s, Dono: %s, Saldo: R$ %.2f]",
                this.numeroConta, this.tipoConta, this.dono.getNome(), this.saldo);
    }
}