import java.math.BigDecimal;
import java.time.LocalDate;

public class Convidado {
    private String nome;
    private String cpf;
    private int idade;
    private LocalDate dataCadastro;
    private TipoIngresso tipoIngresso;
    private BigDecimal valorPago;

    //Este constructor serve para garantir que o objeto não será construido vazio e que as informações já sejam criadas no instante da criação do objeto.
    public Convidado(String nome, String cpf, int idade, LocalDate dataCadastro, TipoIngresso tipoIngresso) {
        this.nome = nome;
        setCpf(cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4"));
        setIdade(idade);
        this.dataCadastro = LocalDate.now();
        this.tipoIngresso = tipoIngresso;
        this.valorPago = calcularPreco(tipoIngresso);

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
         this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade < 1) {
            System.out.println("Idade não pode ser menor que 1.");
            return;
        } else {
            this.idade = idade;
        }
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public TipoIngresso getTipoIngresso() {
        return tipoIngresso;
    }

    public void setTipoIngresso(TipoIngresso tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    // metodo privado que calcula o valor do ingresso, ajudando o constructor a fazer a conta.
    private BigDecimal calcularPreco(TipoIngresso tipo) {
        BigDecimal valorBase = new BigDecimal("50.00"); // cria o valor preciso do ingresso base

        //verifica se o tipo de ingresso é VIP ou CAMAROTE e faz sua respectiva conta de 50% e 100% a mais.
        if (tipo == TipoIngresso.VIP) {
            return valorBase.multiply(new BigDecimal("1.5"));
        } else if (tipo == TipoIngresso.CAMAROTE) {
            return valorBase.multiply(new BigDecimal("2.0"));
        }
        return valorBase; // retorna valor da pista PISTA
    }

    // Traduz o objeto e seus diferentes tipos de dados para o formato de texto
    @Override
    public String toString() {
        return "\n" +
                "Nome= " + this.nome + ", \n" +
                "CPF= " + this.cpf + ", \n" +
                "Idade= " + this.idade + ", \n" +
                "Valor Pago= " + this.valorPago + ", \n" +
                "Tipo do Ingresso= " + this.tipoIngresso + ", \n" +
                "Dia da compra= " + this.dataCadastro + "\n" + "\n";
    }
}
