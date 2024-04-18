package Models;

public class No {
    No anterior;
    No proximo;
    Peca peca;

    public No(Peca peca) {
        this.peca = peca;
        this.anterior = null;
        this.proximo = null;
    }
}
