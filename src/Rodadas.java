import java.nio.channels.ScatteringByteChannel;
import java.util.Scanner;

public class Rodadas {
    //scanner
    Scanner sc = new Scanner(System.in);
    //atributos
    private static String[] jogadoresQueApostaram;

    //metodos
    public int rodadaPoker(int jogadores, Carta[][] cartasJogadores) {

        //defino bet para todos os jogadores
        jogadoresQueApostaram = new String[jogadores];
        for (int i = 0; i < jogadoresQueApostaram.length; i++) {
            jogadoresQueApostaram[i] = "bet";
        }

        //criar variaveis necessarias
        Object[][] decisaoJogador = new Object[jogadores][2];
        int somaApostas = 0;
        int opcaoJogador;
        int apostaDaRodada = 0;
        boolean apostasIguais = false;

        // primeira volta na mesa
        for (int i = 0; i < decisaoJogador.length; i++) {
            try {
                System.out.println("Jogador " + (i + 1) + "," + "bet (1) ou out (2)?");
                opcaoJogador = sc.nextInt();
                if (!(opcaoJogador == 1 || opcaoJogador == 2)) {
                    throw new ArithmeticException("opção inválida");
                } else if (opcaoJogador == 1) {
                    decisaoJogador[i][0] = "bet";
                    System.out.println("Jogador " + (i + 1) + " escolheu bet.");
                    System.out.println("Qual o valor da aposta? (valor da aposta atual: R$ " + apostaDaRodada + ")");
                    decisaoJogador[i][1] = sc.nextInt();
                    if(checkAposta(apostaDaRodada,(int)decisaoJogador[i][1])){
                        apostaDaRodada = (int) decisaoJogador[i][1];
                    }else {
                        throw new ArithmeticException("opção inválida");
                    }

                } else {
                    decisaoJogador[i][0] = "out";
                    decisaoJogador[i][1] = 0;
                    jogadoresQueApostaram[i] = "out";
                    Baralho.imprimeCartaJogadoresRestantes(cartasJogadores, jogadoresQueApostaram);
                }
            } catch (Exception e) {
                System.out.println("Jogador " + (i + 1) + " digitou uma opção inválida. Está fora da rodada");
                decisaoJogador[i][0] = "out";
                jogadoresQueApostaram[i] = "out";
                Baralho.imprimeCartaJogadoresRestantes(cartasJogadores, jogadoresQueApostaram);
                decisaoJogador[i][1] = 0;
            }
        }

        //laço de repetição  verificando apostas
        do {
            //verifico se todas as apostas são iguais
            for (int i = 0; i < decisaoJogador.length; i++) {
                if (decisaoJogador[i][0] == "bet") {
                    if (checkAposta(apostaDaRodada,(int) decisaoJogador[i][1])){
                        apostaDaRodada = (int) decisaoJogador[i][1];
                        apostasIguais = true ;
                    } else {
                        apostasIguais = false;
                        System.out.println("Jogador " + (i + 1) + ", a aposta atual é: R$" + apostaDaRodada);
                        System.out.println("Você escolhe bet(1) ou out(2)?");
                        try {
                            opcaoJogador = sc.nextInt();
                            if (!(opcaoJogador == 1 || opcaoJogador == 2)) {
                                throw new ArithmeticException("opção inválida");
                            } else if (opcaoJogador == 1) {
                                System.out.println("Jogador "+ (i+1) +" escolheu bet.");
                                System.out.println("Qual o valor da aposta? (valor da aposta atual: " + apostaDaRodada + ")");
                                decisaoJogador[i][1] = sc.nextInt();
                                if (checkAposta(apostaDaRodada,(int) decisaoJogador[i][1])) {
                                    apostaDaRodada = (int) decisaoJogador[i][1];
                                } else {
                                    throw new ArithmeticException("opção inválida");
                                }
                            } else {
                                decisaoJogador[i][0] = "out";
                                jogadoresQueApostaram[i] = "out";
                                Baralho.imprimeCartaJogadoresRestantes(cartasJogadores, jogadoresQueApostaram);
                            }

                        } catch (Exception e) {
                            System.out.println("Jogador " + (i + 1) + " digitou uma opção inválida . Está fora da rodada");
                            decisaoJogador[i][0] = "out";
                            jogadoresQueApostaram[i] = "out";
                            Baralho.imprimeCartaJogadoresRestantes(cartasJogadores, jogadoresQueApostaram);
                        }
                    }
                }
            }
        }
        while (!apostasIguais);

        // soma a rodada
        for (int i = 0; i < decisaoJogador.length; i++) {
            somaApostas += (int) decisaoJogador[i][1];
        }

        return somaApostas;
    }

    public int jogadoresDaRodada() {
        boolean continua = false;
        int jogadores = 0;
        //validador de jogadores para a rodada
        while (!continua) {
            System.out.println("quantos jogadores irão participar: ");
            try {
                jogadores = sc.nextInt();
                if (jogadores <= 0) {
                    throw new ArithmeticException("numero invalido");
                } else {
                    continua = true;
                }
            } catch (Exception e) {
                System.out.println("você digitou um número inválido de participantes");
            }
        }
        return jogadores;
    }

    //getter p/ os jogadores que apostaram
    public static String[] getJogadoresQueApostaram() {
        return jogadoresQueApostaram;
    }

    public static boolean checkAposta(int apostaAtual, int apostaJogador) {

        if (apostaJogador < apostaAtual) {
            System.out.println("A aposta informada é menor do que a aposta atual, digite um valor maior ou igual ou peça pra sair.");
            return false;
        } else {
            return true;
        }
    }
}


