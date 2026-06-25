import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


public class Main {
    static Map<String, Convidado> listaDeConvidados = new HashMap<>(); //Lista de MAP para buscar pela Chave (CPF) e o valor (objeto completo do convidado).

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        menu();
    }

    public static void menu() {
        Scanner scan = new Scanner(System.in);

        int escolha = -1;
        while (escolha != 0) {
            System.out.println("\n##### GESTÃO DE INGRESSOS #####\n");
            System.out.println();
            System.out.println("1. Cadastrar convidado           3. Relatório VIP");
            System.out.println("2. Buscar convidado por CPF      4. Fechamento do caixa");
            System.out.println("5. Listar convidados ");
            System.out.println("0. Sair do programa ");

            escolha = scan.nextInt();

            switch (escolha) {
                case 1:
                    cadastroConvidado();
                    break;
                case 2:
                    buscaConvidado();
                    break;
                case 3:
                    relatorioVip();
                    break;
                case 4:
                    fechamentoCaixa();
                    break;
                case 5:
                    listarConvidados();
                    break;
                case 0:
                    break;
                default:
                    menu();
                    break;
            }
        }

    }

    public static void cadastroConvidado(){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n##### CADASTRO DE CONVIDADO #####");
        System.out.println("Digite as informações do convidado para cadastro.");
        System.out.println("Nome:");
        String nome = scan.nextLine();
        // vefica se o NOME não é vazio.
        if (nome == null || nome.isEmpty()){
            System.out.println("Digite um nome!");
            return;
        }

        System.out.println("CPF:");
        String cpf = scan.nextLine();
        // vefica se o CPF é valido.
        if (cpf == null || cpf.isEmpty() || cpf.length() != 11){
            System.out.println("Digite um CPF válido!");
            return;
        }

        System.out.println("Idade:");
        int idade = scan.nextInt();
        // vefica se a IDADE é valida.
        if (idade < 1){
            System.out.println("Digte uma idade válida!");
            return;
        }
        System.out.println("Tipo de Ingresso (PISTA: R$50,00, VIP R$75,00, OU CAMAROTE R$100,00)");
        LocalDate dataCadastro = LocalDate.now(); // pega a data do registro do ingresso

        String entradaUsuario = scan.next().toUpperCase();

        if (!entradaUsuario.equals("PISTA") && !entradaUsuario.equals("VIP") && !entradaUsuario.equals("CAMAROTE")) {
            System.out.println("Digite um tipo de ingresso válido.");
            return;
        }
        TipoIngresso tipoIngresso = TipoIngresso.valueOf(entradaUsuario);
        Convidado convidadoNovo = new Convidado(nome, cpf, idade, dataCadastro, tipoIngresso);
        listaDeConvidados.put(cpf, convidadoNovo);
}
    public static void buscaConvidado(){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n##### BUSCA DE CONVIDADO NA LISTA #####");
        if (listaDeConvidados.isEmpty()){
            System.out.println("A lista está vazia. Adicione algum convidado para poder buscar.");
            return;
        } else {
            System.out.println("Digite o CPF do convidado para busca-lo na lista.");
            String cpfBuscado = scan.nextLine();

            if (cpfBuscado == null || cpfBuscado.isEmpty() || cpfBuscado.length() != 11){
                System.out.println("Digite um CPF válido!");
                return;
            } else {
                // Faz a busca do cpf digitado e devolve se existe na lista ou nao
                if(listaDeConvidados.containsKey(cpfBuscado)){
                    System.out.println("O convidado (Portador do CPF: " + listaDeConvidados.get(cpfBuscado).getCpf() + ")" + "está na lista");
                    System.out.println(listaDeConvidados.get(cpfBuscado));
                } else {
                    System.out.println("O convidado portador do CPF: " + cpfBuscado.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4") + ", não está na lista!");
                }
            }
        }
    }

    public static void relatorioVip() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n##### LISTA DE CONVIDADOS VIP #####");
        System.out.println("");

        // armazena se existe convidado vip na lista
        boolean existeConvidadoVipNaLista = listaDeConvidados.values()
                .stream()
                .anyMatch(convidado -> convidado.getTipoIngresso() == TipoIngresso.VIP);


        if(listaDeConvidados.isEmpty()){ //Verificia se a lista está vazia
            System.out.println("A lista está vazia. Adicione algum convidado para poder listar os convidados vip");
        } else if (!existeConvidadoVipNaLista){ //verifica se possui convidado vip
            System.out.println("A lista não contém convidados vip, adicione ao menos um para listar.");
            return;
        } else {
            // Filtrar todos os convidados VIP
            listaDeConvidados.entrySet() // transforma o Map em Set para poder interpretar com o stream.
                    .stream() // ativa o stream para poder usar a filtragem
                    .filter(entry -> entry.getValue().getTipoIngresso() == TipoIngresso.VIP) // Faz a verificação => convidado (entry) -> (pegue isso e faça) pega o valor (o objeto convidado) pega o tipo ingresso  dentro do valor (verifica se o tipo ingresso é igual a vip)
                    .forEach(entry -> System.out.println(entry.getValue()));
        }


    }
    public static void fechamentoCaixa(){
        Scanner scan = new Scanner(System.in);
        if (listaDeConvidados.isEmpty()){
            System.out.println("Não temos nenhum convidado.");
            return;
        }
            BigDecimal valorTotalCaixa = listaDeConvidados.values()
                    .stream()
                    .map(Convidado::getValorPago)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            System.out.println("O total ganho foi de R$" + valorTotalCaixa);
            System.out.println("Detalhes:");
            listaDeConvidados.forEach((cpf,convidado)-> {
                System.out.println("Convidado: " + convidado.getNome() +
                        " | Comprou: " + convidado.getTipoIngresso() +
                        " | Valor: R$ " + convidado.getValorPago());
            });

    }
    public static void listarConvidados(){
        Scanner scan = new Scanner(System.in);
        if (listaDeConvidados.isEmpty()){
            System.out.println("A lista está vazia. Adicione algum convidado para poder lista-la.");
            return;
        }else {
            System.out.println(listaDeConvidados);
        }

    }

}

