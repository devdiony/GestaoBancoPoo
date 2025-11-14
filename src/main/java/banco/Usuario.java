package banco;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um Usuário/Cliente do banco.
 * Gerencia seus dados e sua lista de contas.
 */
public class Usuario {

    private String nome;
    private String cpf;
    private List<ContaBancaria> contas; // Um usuário pode ter várias contas

    public Usuario(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.contas = new ArrayList<>();
    }

    /**
     * Adiciona uma conta à lista de contas do usuário.
     */
    public void adicionarConta(ContaBancaria conta) {
        this.contas.add(conta);
    }

    /**
     * Remove uma conta da lista do usuário (Exemplo de CRUD - Delete).
     */
    public void removerConta(ContaBancaria conta) {
        this.contas.remove(conta);
    }

    /**
     * Lista todas as contas pertencentes a este usuário.
     */
    public void listarContas() {
        System.out.println("\n--- Contas do Cliente: " + this.nome + " (CPF: " + this.cpf + ") ---");
        if (contas.isEmpty()) {
            System.out.println("Este cliente não possui contas.");
        } else {
            for (ContaBancaria conta : this.contas) {
                System.out.println(conta); // Chama o toString() da ContaBancaria
            }
        }
    }

    // Getters e Setters (Encapsulamento)
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public List<ContaBancaria> getContas() {
        return contas;
    }

    /**
     * Permite a atualização do nome (Exemplo de CRUD - Update).
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}