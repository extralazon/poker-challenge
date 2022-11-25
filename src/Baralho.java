import java.util.Random;

public class Baralho {
    //atributos
    Carta[] baralho = new Carta[52];
    String[] naipes = {"♦", "♠", "♣", "♥"};

    //construtor
    public Carta[] Baralho() {
        for (int i = 0; i < baralho.length; i++) {
            baralho[i] = new Carta();
        }
        int a = 0;
        for (int i = 0; i < naipes.length; i++) {
            for (int j = 1; j < 14; j++) {
                baralho[a].setNaipe(naipes[i]);
                baralho[a].setNome(j);
                a += 1;
            }
        }
        return baralho;
    }

    //metodos
    static void embaralhaCartas(Carta[] baralho) {
        Carta cartaTemporaria;
        Random r = new Random();
        int a, b;
        for (int i = 0; i < 2000; i++) {
            a = baralho.length - 1 - r.nextInt(baralho.length - 1);
            b = r.nextInt(baralho.length - 1);
            cartaTemporaria = baralho[a];
            baralho[a] = baralho[b];
            baralho[b] = cartaTemporaria;
        }
    }

    static void imprimeBaralho(Carta[] baralho) {
        String nomeCarta="";
        for (int i = 0; i < baralho.length; i++) {
            nomeCarta = String.valueOf(baralho[i].getNome());
            switch (nomeCarta){
                case("1"): {
                    nomeCarta ="A";
                    break;
                }
                case("11"): {
                    nomeCarta = "J";
                    break;
                }
                case("12"):{
                    nomeCarta="Q";
                    break;
                }
                case("13"):{
                    nomeCarta="K";
                    break;
                }
            }
            System.out.println("["+nomeCarta + baralho[i].getNaipe()+"]");
        }
    }

    static Carta[][] darCartas(int jogadores, Carta[] baralho) {
        Carta[][] cartasJogadores = new Carta[jogadores][2];
        int a = 0;
        for (int i = 0; i < jogadores; i++) {
            for (int j = 0; j < 2; j++) {
                cartasJogadores[i][j] = baralho[a];
                a++;
            }
        }

        return cartasJogadores;
    }

    static void imprimeCartasJogadores(Carta[][] cartasJogadores) {
        System.out.println("::::: CARTAS DISTRIBUIDAS :::::");
        String nomeCarta = "";

        for (int i = 0; i < cartasJogadores.length; i++) {
            System.out.println("Jogador " + (i + 1) + ":");
            for (int j = 0; j < 2; j++) {
                nomeCarta = String.valueOf(cartasJogadores[i][j].getNome());
                switch (nomeCarta){
                    case("1"): {
                        nomeCarta ="A";
                        break;
                    }
                    case("11"): {
                        nomeCarta = "J";
                        break;
                    }
                    case("12"):{
                        nomeCarta="Q";
                        break;
                    }
                    case("13"):{
                        nomeCarta="K";
                        break;
                    }
                }
                System.out.print("["+nomeCarta + cartasJogadores[i][j].getNaipe()+"]");
            }
            System.out.println();
        }
    }

    static boolean temCarta(int cartasDistribuidas, Carta[] baralho) {
        var temCarta = false;
        if (cartasDistribuidas + 5 < baralho.length) {
            temCarta = true;
        } else {
            System.out.println("não há cartas suficientes no baralho para esses jogadores");
        }
        return temCarta;
    }

    static int posicaoBaralho(int jogadores) {
        int posicaoBaralho = jogadores * 2;
        return posicaoBaralho -1;
    }

    static void imprimeCartaJogadoresRestantes(Carta[][] cartasJogadores,String[] jogadoresQueApostaram) {
        String[] jogadoresRestantes = jogadoresQueApostaram;
        String nomeCarta = "";
        System.out.println(":::: JOGADORES RESTANTES ::::");
        for (int i = 0; i < cartasJogadores.length; i++) {
            if (jogadoresRestantes[i] == "bet") {
                System.out.println("Jogador " + (i + 1) + ":");
                for (int j = 0; j < 2; j++) {
                    nomeCarta = String.valueOf(cartasJogadores[i][j].getNome());
                    switch (nomeCarta){
                        case("1"): {
                            nomeCarta ="A";
                            break;
                        }
                        case("11"): {
                            nomeCarta = "J";
                            break;
                        }
                        case("12"):{
                            nomeCarta="Q";
                            break;
                        }
                        case("13"):{
                            nomeCarta="K";
                            break;
                        }
                    }
                    System.out.print("["+nomeCarta + cartasJogadores[i][j].getNaipe()+"]");
                }
                System.out.println();
            }
        }
    }

    static void imprimeCartasDealer(Carta[] baralho, int posicaoBaralho){
        String nomeCarta = "";
        System.out.println("::::::: CARTAS DA MESA :::::::");
        for (int i= posicaoBaralho; i < posicaoBaralho+5;i++){
            nomeCarta = String.valueOf(baralho[i].getNome());
            switch (nomeCarta){
                case("1"): {
                    nomeCarta ="A";
                    break;
                }
                case("11"): {
                    nomeCarta = "J";
                    break;
                }
                case("12"):{
                    nomeCarta="Q";
                    break;
                }
                case("13"):{
                    nomeCarta="K";
                    break;
                }
            }
            System.out.print("["+nomeCarta + baralho[i].getNaipe()+"]");
        }
        System.out.println();
    }

}


