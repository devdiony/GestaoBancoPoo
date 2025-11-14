package banco;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa uma única operação (Depósito ou Saque) para o extrato.
 */
public class Operacao {

    private String tipo;
    private double valor;
    private LocalDateTime data;

    public Operacao(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = LocalDateTime.now(); // Grava a data e hora exatas
    }

    // Sobrescreve o método toString() para formatar a saída no extrato.
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = this.data.format(formatter);

        // Formata o valor com R$ e duas casas decimais
        String valorFormatado = String.format("R$ %.2f", this.valor);

        // Formata o tipo para alinhamento
        String tipoFormatado = String.format("%-10s", this.tipo); // Alinha à esquerda com 10 espaços

        return String.format("Data: %s | Tipo: %s | Valor: %s",
                dataFormatada, tipoFormatado, valorFormatado);
    }
}