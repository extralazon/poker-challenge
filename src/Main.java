import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Scanner para receber inputs
        Scanner sc = new Scanner(System.in);
        //Criar baralho para jogar
        Carta[] baralho = new Baralho().Baralho();
        //Cria o array com as cartas dos jogadores
        Carta[][] cartasJogadores;
        //embaralhar as cartas
        Baralho.embaralhaCartas(baralho);
        //variavel jogadores
        int jogadores;

        //soma da rodada
        int soma;

        //jogador vencedor
        int jogadorVencedor;

        //validador de jogadores vs cartas no baralho
        boolean jogoSegue  = false;

        //Frase de start do game
        System.out.println("---=== BLIND DEALER TEXAS HOLD'EM ===---");
        //verifica se as cartas sao suficientes
        do {
            //
            jogadores = new Rodadas().jogadoresDaRodada();
            //calcula o total de cartas distribuidas
            int cartasDistribuidas = jogadores * 2;
            //verifica se há cartas no baralho
            jogoSegue = Baralho.temCarta(cartasDistribuidas, baralho);
        } while(!jogoSegue);

        //dar as cartas aos jogadores
        cartasJogadores = Baralho.darCartas(jogadores,baralho);

        //começam as rodadas
        Baralho.imprimeCartasJogadores(cartasJogadores);

        soma = new Rodadas().rodadaPoker(jogadores,cartasJogadores);
        Baralho.imprimeCartasDealer(baralho,Baralho.posicaoBaralho(jogadores));
        String[] jogadoresRestantes = Rodadas.getJogadoresQueApostaram();
        Baralho.imprimeCartaJogadoresRestantes(cartasJogadores,jogadoresRestantes);
        System.out.println();
        System.out.println("eaí, quem ganhou?");
        jogadorVencedor = sc.nextInt();
        System.out.println("-------------------");
        System.out.println("O jogador "+jogadorVencedor+ " recebeu: R$ "+ soma+" na rodada!");







    }
}
