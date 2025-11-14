package banco;

/**
 * Classe principal que instancia os cenários solicitados na atividade.
 * Demonstra o uso da classe Banco e das operações de CRUD.
 */

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Iniciando Simulação Bancária (CRUD) ===");
        Banco meuBanco = new Banco();
        System.out.println("---------------------------------------------");

        // --- Cenário 1: 2 clientes com 1 conta bancária cada ---
        System.out.println("Cenário 1: 2 clientes, 1 conta cada");
        meuBanco.criarUsuario("Cliente 1 (José)", "111.111.111-11");
        ContaBancaria c1 = meuBanco.criarConta("111.111.111-11", "001-1", "Corrente");

        meuBanco.criarUsuario("Cliente 2 (Maria)", "222.222.222-22");
        ContaBancaria c2 = meuBanco.criarConta("222.222.222-22", "002-2", "Poupança");
        System.out.println("---------------------------------------------");

        // --- Cenário 2: 2 clientes com 2 contas bancárias cada ---
        System.out.println("Cenário 2: 2 clientes, 2 contas cada");
        meuBanco.criarUsuario("Cliente 3 (Ana)", "333.333.333-33");
        ContaBancaria c3_1 = meuBanco.criarConta("333.333.333-33", "003-1", "Corrente");
        ContaBancaria c3_2 = meuBanco.criarConta("333.333.333-33", "003-2", "Poupança");

        meuBanco.criarUsuario("Cliente 4 (Bruno)", "444.444.444-44");
        ContaBancaria c4_1 = meuBanco.criarConta("444.444.444-44", "004-1", "Corrente");
        ContaBancaria c4_2 = meuBanco.criarConta("444.444.444-44", "004-2", "Poupança");
        System.out.println("---------------------------------------------");

        // --- Cenário 3: 1 cliente com 3 contas ---
        System.out.println("Cenário 3: 1 cliente, 3 contas");
        meuBanco.criarUsuario("Cliente 5 (Empresa Diony)", "555.555.555-55");
        ContaBancaria c5_1 = meuBanco.criarConta("555.555.555-55", "005-1", "Corrente");
        ContaBancaria c5_2 = meuBanco.criarConta("555.555.555-55", "005-2", "Poupança");
        ContaBancaria c5_3 = meuBanco.criarConta("555.555.555-55", "005-3", "Empresarial");
        System.out.println("---------------------------------------------");

        // --- Cenário 4: Todas as contas com movimentações ---
        System.out.println("Cenário 4: Registrando movimentações...");
        c1.depositar(1000); c1.sacar(200);
        c2.depositar(500); c2.sacar(50);
        c3_1.depositar(2000); c3_1.sacar(1000);
        c3_2.depositar(300); c3_2.sacar(100);
        c4_1.depositar(10000); c4_1.sacar(500);
        c4_2.depositar(100);
        c5_1.depositar(50000); c5_1.sacar(10000); c5_1.sacar(5000);
        c5_2.depositar(100000);
        c5_3.depositar(1000000); c5_3.sacar(250000);
        System.out.println("Movimentações concluídas.");
        System.out.println("---------------------------------------------");

        // --- Demonstração das Funcionalidades (Listagens) ---

        // Listar TODAS as contas
        meuBanco.listarTodasContas();

        // Listar contas por CPF (Cenário 2 e 3)
        meuBanco.listarContasPorCpf("333.333.333-33"); // Cliente 3 (Ana)
        meuBanco.listarContasPorCpf("555.555.555-55"); // Cliente 5 (Diony)

        // Consultar extrato (Cenário 3)
        c5_1.consultarExtrato(); // Extrato da Conta Corrente do Cliente 5
        c2.consultarExtrato(); // Extrato da Conta Poupança da Cliente 2

        // --- Demonstração do CRUD (Update, Delete) ---
        System.out.println("\n--- Demonstração CRUD (Update e Delete) ---");

        // UPDATE (Uso do set())
        System.out.println("\n(UPDATE) Atualizando nome do Cliente 1...");
        meuBanco.atualizarUsuario("111.111.111-11", "Cliente 1 (José da Silva)");
        meuBanco.listarContasPorCpf("111.111.111-11");

        // DELETE (Uso do remove())
        System.out.println("\n(DELETE) Removendo Cliente 2 e sua conta...");
        meuBanco.removerUsuario("222.222.222-22");

        // Verificação do Delete
        System.out.println("\n--- LISTA GERAL APÓS REMOÇÃO ---");
        meuBanco.listarTodasContas();

        System.out.println("\n=== Simulação Bancária Finalizada ===");
    }
}