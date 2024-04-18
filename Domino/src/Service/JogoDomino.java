package Service;

import Models.Lista;
import Models.Peca;

import java.util.Random;

public class JogoDomino {
    private Lista jogadorPrincipal;
    private Lista jogadorRobo;
    private Lista mesa;
    private Lista pecas;
    private Random random = new Random();
    private boolean jogoEmAndamento;

    public JogoDomino() {
        this.pecas = new Lista();
        this.jogadorPrincipal = new Lista();
        this.jogadorRobo = new Lista();
        this.mesa = new Lista();
        this.jogoEmAndamento = true;
    }

    public void iniciarJogo() {
        adicionarTodasPecas();
        distribuirPecas();
        Output.printInitialGameState(jogadorPrincipal, jogadorRobo, mesa, pecas);
        jogar();
    }

    private void adicionarTodasPecas() {
        for (int ladoA = 0; ladoA <= 6; ladoA++) {
            for (int ladoB = ladoA; ladoB <= 6; ladoB++) {
                Peca peca = new Peca(ladoA, ladoB);
                this.pecas.inserir(peca);
            }
        }
    }

    private void distribuirPecas() {
        // Adicionar uma peça aleatória à mesa no início do jogo
        int indiceAleatorioMesa = random.nextInt(pecas.tamanhoDaLista());
        Peca pecaInicial = pecas.removerNaPosicao(indiceAleatorioMesa);
        this.mesa.inserir(pecaInicial);

        distribuirPecasParaJogador(this.jogadorPrincipal);
        distribuirPecasParaJogador(this.jogadorRobo);
    }

    private void distribuirPecasParaJogador(Lista jogador) {
        for (int i = 0; i < 7; i++) {
            int indiceAleatorioHumano = random.nextInt(pecas.tamanhoDaLista());
            Peca pecaSelecionada = pecas.removerNaPosicao(indiceAleatorioHumano);
            jogador.inserir(pecaSelecionada);
        }
    }

    private void jogar() {
            Lista ultimoJogador = jogadorRobo; // Iniciar com o jogador robô
            int contadorPassagem = 0; // Contador de passagens sem jogada válida
            int pecasJogadorPrincipal = jogadorPrincipal.tamanhoDaLista();
            int pecasJogadorRobo = jogadorRobo.tamanhoDaLista();

            while (jogoEmAndamento) {
                Peca ultimaPecaMesa = mesa.obterUltimaPeca();
                Lista jogadorAtual = (ultimoJogador == jogadorPrincipal) ? jogadorRobo : jogadorPrincipal;
                Peca pecaJogavel = jogadorAtual.obterPecaJogavel(ultimaPecaMesa, jogadorAtual);

                if (pecaJogavel != null) {
                    contadorPassagem = 0; // Zera o contador se uma peça jogável foi encontrada
                    if (jogadorAtual == jogadorRobo) {
                        realizarJogadaRobo(jogadorAtual, ultimaPecaMesa, pecaJogavel, pecasJogadorRobo);
                    } else {
                        realizarJogadaHumano(jogadorAtual, ultimaPecaMesa, pecaJogavel,pecasJogadorPrincipal);
                    }
                }else {
                    Output.mensagemSemPecaJogavel(jogadorAtual, jogadorPrincipal);
                    contadorPassagem++;
                    if (contadorPassagem >= 2 && pecasJogadorPrincipal == pecasJogadorRobo) { // Verifica se ambos os jogadores passaram a vez duas vezes consecutivas e têm o mesmo número de peças
                        Output.mensagemEmpate();
                        jogoEmAndamento = false;
                    } else if (contadorPassagem >= 2) { // Verifica se ambos os jogadores passaram a vez duas vezes consecutivas, mas não têm o mesmo número de peças
                        String vencedor = (pecasJogadorPrincipal < pecasJogadorRobo) ? "principal" : "robô";
                        Output.mensagemVencedorPorMenosPecas(vencedor);
                        jogoEmAndamento = false;
                    }
                }
                verificarVencedor(jogadorAtual);
                ultimoJogador = jogadorAtual;
            }
        }
    private void realizarJogadaHumano(Lista jogadorAtual, Peca ultimaPecaMesa, Peca pecaJogavel, int pecasJogadorPrincipal) {
        if (pecaJogavel != null) {
            Output.suaVezDeJogar();
            Output.suasPecas();
            jogadorAtual.imprimirPecas();
            Output.pedirIndiceParaJogar(jogadorAtual.tamanhoDaLista());

            boolean jogadaValida = false;
            while (!jogadaValida) {
                int indiceEscolhido = Input.lerInteiro();
                if (indiceEscolhido >= 1 && indiceEscolhido <= jogadorAtual.tamanhoDaLista()) {
                    Peca pecaEscolhida = jogadorAtual.obterPecaPorIndice(indiceEscolhido - 1);
                    if (pecaEscolhida.podeSerJogadaCom(ultimaPecaMesa)) {
                        Output.mostrarJogadaRealizada(pecaEscolhida);
                        jogadorAtual.removerPeca(pecaEscolhida);
                        mesa.inserir(pecaEscolhida);
                        Output.pecaNaMesaAposJogada();
                        Output.linhasDeSeparacao();
                        mesa.imprimirPecas();
                        Output.linhasDeSeparacao();

                        jogadaValida = true; // Marca a jogada como válida

                        // Atualiza o contador de peças do jogador principal
                        pecasJogadorPrincipal--;
                    } else {
                        Output.pecaEscolhidaNaoJogavel();
                        Output.suasPecas();
                        jogadorAtual.imprimirPecas();
                        Output.pedirIndiceParaJogar(jogadorAtual.tamanhoDaLista());
                    }
                } else {
                    Output.indiceInvalido();
                }
            }
        } else {
            Output.passouAVez();
        }
    }

    private void realizarJogadaRobo(Lista jogadorAtual, Peca ultimaPecaMesa, Peca pecaJogavel, int pecasJogadorRobo) {
        if (pecaJogavel != null) {
            if (pecaJogavel.podeSerJogadaCom(ultimaPecaMesa)) {
                Output.vezDoRoboJogar();
                Output.jogarPecaAleatoria(pecaJogavel);
                jogadorAtual.removerPeca(pecaJogavel);
                mesa.inserir(pecaJogavel);
                Output.pecaNaMesaJogadorRobo();

                Output.linhasDeSeparacao();
                mesa.imprimirPecas();
                Output.linhasDeSeparacao();

                Output.pecasJogadorRobo();
                jogadorAtual.imprimirPecas();

                pecasJogadorRobo--;
                pecasJogadorRobo--;

            }
        } else {
            Output.roboPassouAVez();
        }
    }
    private void verificarVencedor(Lista jogadorAtual) {
        if (jogadorAtual.tamanhoDaLista() == 0) {
            System.out.println("Jogador " + (jogadorAtual == jogadorPrincipal ? "principal" : "robô") + " venceu!");
            jogoEmAndamento = false;
        }
    }
}
