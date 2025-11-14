package banco;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de serviço que gerencia todas as operações de CRUD
 * (Criar, Ler, Atualizar, Deletar) do banco.
 */
public class Banco {

    private List<Usuario> usuarios;
    private List<ContaBancaria> contas;

    public Banco() {
        this.usuarios = new ArrayList<>();
        this.contas = new ArrayList<>();
    }

    // --- CRUD de Usuário ---

    /**
     * (CREATE) Cria e adiciona um novo usuário à lista do banco.
     */
    public Usuario criarUsuario(String nome, String cpf) {
        if (buscarUsuario(cpf) != null) {
            System.out.println("Erro: CPF já cadastrado.");
            return null;
        }
        Usuario novoUsuario = new Usuario(nome, cpf);
        this.usuarios.add(novoUsuario); // Uso do método add()
        System.out.println("Usuário " + nome + " criado com sucesso.");
        return novoUsuario;
    }

    /**
     * (READ) Busca um usuário pelo CPF.
     */
    public Usuario buscarUsuario(String cpf) {
        for (Usuario u : this.usuarios) {
            if (u.getCpf().equals(cpf)) {
                return u; // Uso implícito de get()
            }
        }
        return null; // Não encontrado
    }

    /**
     * (UPDATE) Atualiza o nome de um usuário (Uso do set()).
     */
    public boolean atualizarUsuario(String cpf, String novoNome) {
        Usuario u = buscarUsuario(cpf);
        if (u != null) {
            u.setNome(novoNome); // Método 'set' da classe Usuario
            // A lista 'usuarios' usa a referência, então não precisamos
            // de 'usuarios.set(index, u)'
            System.out.println("Nome do usuário " + cpf + " atualizado para " + novoNome);
            return true;
        }
        System.out.println("Erro: Usuário não encontrado para atualização.");
        return false;
    }

    /**
     * (DELETE) Remove um usuário e todas as suas contas.
     */
    public boolean removerUsuario(String cpf) {
        Usuario u = buscarUsuario(cpf);
        if (u != null) {
            // Remove todas as contas associadas a este usuário da lista principal
            this.contas.removeAll(u.getContas());
            // Remove o usuário da lista de usuários
            this.usuarios.remove(u); // Uso do método remove()
            System.out.println("Usuário " + u.getNome() + " e todas as suas contas foram removidos.");
            return true;
        }
        System.out.println("Erro: Usuário não encontrado para remoção.");
        return false;
    }

    // --- CRUD de Conta ---

    /**
     * (CREATE) Cria uma nova conta e a associa a um usuário.
     */
    public ContaBancaria criarConta(String cpfUsuario, String numeroConta, String tipoConta) {
        Usuario dono = buscarUsuario(cpfUsuario);
        if (dono == null) {
            System.out.println("Erro: Usuário com CPF " + cpfUsuario + " não encontrado.");
            return null;
        }

        ContaBancaria novaConta = new ContaBancaria(numeroConta, tipoConta, dono);
        this.contas.add(novaConta); // Adiciona na lista geral
        dono.adicionarConta(novaConta); // Adiciona na lista do usuário
        System.out.println("Conta " + numeroConta + " (" + tipoConta + ") criada para " + dono.getNome());
        return novaConta;
    }

    /**
     * (READ) Busca uma conta pelo número.
     */
    public ContaBancaria buscarConta(String numeroConta) {
        for (ContaBancaria c : this.contas) {
            if (c.getNumeroConta().equals(numeroConta)) {
                return c;
            }
        }
        return null; // Não encontrada
    }

    /**
     * (DELETE) Remove uma conta específica.
     */
    public boolean removerConta(String numeroConta) {
        ContaBancaria c = buscarConta(numeroConta);
        if (c != null) {
            c.getDono().removerConta(c); // Remove da lista do usuário
            this.contas.remove(c); // Remove da lista geral
            System.out.println("Conta " + numeroConta + " removida com sucesso.");
            return true;
        }
        System.out.println("Erro: Conta não encontrada para remoção.");
        return false;
    }

    // --- Listagens (Requisitos) ---

    /**
     * Lista TODAS as contas cadastradas no banco.
     */
    public void listarTodasContas() {
        System.out.println("\n--- LISTA GERAL DE CONTAS DO BANCO ---");
        if (this.contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada no banco.");
        } else {
            for (ContaBancaria c : this.contas) {
                System.out.println(c);
            }
        }
    }

    /**
     * Lista as contas de um cliente específico via CPF.
     */
    public void listarContasPorCpf(String cpf) {
        Usuario u = buscarUsuario(cpf);
        if (u != null) {
            u.listarContas();
        } else {
            System.out.println("Erro: Nenhuma conta encontrada para o CPF " + cpf);
        }
    }
}