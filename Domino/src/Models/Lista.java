package Models;

public class Lista {
    private No primeiro;
    private No ultimo;

    public Lista() {
        primeiro = null;
        ultimo = null;
    }

    public boolean estaVazia() {
        return primeiro == null;
    }

    public void inserir(Peca novaPeca) {
        No novoNo = new No(novaPeca);
        novoNo.peca = novaPeca;
        if (estaVazia()) {
            primeiro = novoNo;
            ultimo = novoNo;
        } else {
            ultimo.proximo = novoNo;
            novoNo.anterior = ultimo;
            ultimo = novoNo;
        }
    }
    public void imprimirPecas() {
        No atual = primeiro;
        while (atual != null) {
            System.out.print(atual.peca + " | ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    public int tamanhoDaLista() {
        int tamanho = 0;
        No atual = primeiro; // Começa pelo primeiro nó

        // Percorre a lista enquanto houver nós
        while (atual != null) {
            tamanho++; // Incrementa o tamanho a cada nó encontrado
            atual = atual.proximo; // Move para o próximo nó
        }

        return tamanho;
    }

    public void imprimir() {
        No atual = primeiro;
        while (atual != null) {
            System.out.print(atual.peca + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    public Peca removerNaPosicao(int posicao) {
        if (estaVazia() || posicao < 0 || posicao >= tamanhoDaLista()) {
            throw new IndexOutOfBoundsException("Posição inválida.");
        }

        No atual = primeiro;
        No anterior = null;

        // Percorrer até a posição desejada
        for (int i = 0; i < posicao; i++) {
            anterior = atual;
            atual = atual.proximo;
        }

        // Remover o nó atual da lista
        if (anterior == null) {
            // Se for o primeiro nó
            primeiro = atual.proximo;
        } else {
            anterior.proximo = atual.proximo;
        }

        if (atual == ultimo) {
            // Se for o último nó
            ultimo = anterior;
        }

        return atual.peca;
    }
    public Peca buscarNaPosicao(int posicao) {
        if (posicao < 0 || posicao >= tamanhoDaLista()) {
            throw new IndexOutOfBoundsException("Posição inválida.");
        }

        No atual = primeiro;
        for (int i = 0; i < posicao; i++) {
            atual = atual.proximo;
        }
        return atual.peca;
    }
    public Peca obterUltimaPeca() {
        if (estaVazia()) {
            return null;
        }
        return ultimo.peca;
    }
    public Peca obterPecaJogavel(Peca pecaAnterior, Lista jogador) {
        for (int i = 0; i < jogador.tamanhoDaLista(); i++) {
            Peca peca = jogador.buscarNaPosicao(i);
            if (peca.podeSerJogadaCom(pecaAnterior)) {
                return peca;
            }
        }
        return null;
    }

    public Peca removerPeca(Peca peca) {
        No atual = primeiro;
        while (atual != null) {
            if (atual.peca.equals(peca)) {
                No anterior = atual.anterior;
                No proximo = atual.proximo;

                if (anterior != null) {
                    anterior.proximo = proximo;
                } else {
                    primeiro = proximo;
                }

                if (proximo != null) {
                    proximo.anterior = anterior;
                } else {
                    ultimo = anterior;
                }

                return atual.peca;
            }
            atual = atual.proximo;
        }
        return null; // Models.Peca não encontrada na lista
    }
    public Peca obterPecaPorIndice(int indice) {
        if (indice < 0 || indice >= tamanhoDaLista()) {
            throw new IndexOutOfBoundsException("Posição inválida.");
        }

        No atual = primeiro;
        for (int i = 0; i < indice; i++) {
            atual = atual.proximo;
        }
        return atual.peca;
    }
}

