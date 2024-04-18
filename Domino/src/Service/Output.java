package Service;

import Models.Lista;
import Models.Peca;

public class Output {
    private static Lista jogadorPrincipal;
    private static Lista jogadorRobo;
    private static Lista mesa;
    private static Lista pecas;

    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printInitialGameState(Lista jogadorPrincipal, Lista jogadorRobo, Lista mesa, Lista pecas) {
        System.out.println("Estado inicial do jogo:");
        System.out.println("Pecas antes da distribuição:");
        pecas.imprimirPecas();
        System.out.println("\nPeças do jogador principal:");
        jogadorPrincipal.imprimirPecas();
        System.out.println("\nPeças do jogador robô:");
        jogadorRobo.imprimirPecas();
        System.out.println("\nPeças na mesa:");
        mesa.imprimirPecas();
    }

    public static void PecasDoJogadorPrincipal() {
        println("\nPeças do jogador principal:");
    }


    public static void pecasJogadorRobo() {
        println("Peças do robô após a jogada:");
    }
    public static void pecaNaMesaJogadorRobo() {
        println("\nPeças na mesa após a jogada do robô:");
    }

    public static void indiceInvalido(){
        println("Índice inválido! Por favor, escolha um índice dentro do intervalo.");
    }

    public static void  pecaEscolhidaNaoJogavel(){
        println("A peça escolhida não é jogável. Escolha outra peça.");
    }

    public static void suasPecas() {
        System.out.println("Suas peças:");
    }

    public static void suaVezDeJogar() {
        println("\nSua vez de jogar:");
    }


    public static void passouAVez() {
        println("\nVocê não tem uma peça jogável. Você passou a vez.");
    }
    public static void roboPassouAVez() {
        println("\n Jogador robô não tem uma peça jogável. Ele passou a vez.");
    }


    public static void mensagemEmpate() {
        println("\nNenhum dos jogadores tem uma peça jogável e ambos têm a mesma quantidade de peças. O jogo termina em empate!");
    }

    public static void mensagemSemPecaJogavel(Lista jogadorAtual, Lista jogadorPrincipal) {
        println("\n" + (jogadorAtual == jogadorPrincipal ? "Você" : "Jogador robô") + " não tem uma peça jogável. " + (jogadorAtual == jogadorPrincipal ? "Você" : "Ele") + " passou a vez.");
    }

    public static void mensagemVencedorPorMenosPecas(String vencedor) {
       println("\nNenhum dos jogadores tem uma peça jogável. O jogador " + vencedor + " venceu por ter menos peças!");
    }

    public static void pedirIndiceParaJogar(int tamanhoLista) {
        println("Escolha o índice da peça que deseja jogar (1 - " + tamanhoLista + "): ");
    }

    public static void mostrarJogadaRealizada(Peca pecaJogada) {
        println("Você jogou a peça " + pecaJogada);
    }

    public static void pecaNaMesaAposJogada(){
        System.out.println("\nPeças na mesa após a sua jogada:");
    }

    public static void vezDoRoboJogar(){
        println("\nVez do robô jogar:");
    }
    public static void jogarPecaAleatoria(Peca pecaJogavel) {
        System.out.println("Jogador robô jogou uma peça aleatória: " + pecaJogavel);
    }

    public static void linhasDeSeparacao(){
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }

}