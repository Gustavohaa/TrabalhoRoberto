package Models;

public class Peca {
    private int ladoA;
    private int ladoB;

    public Peca(int ladoA, int ladoB) {
        this.ladoA = ladoA;
        this.ladoB = ladoB;
    }

    public int getLadoA() {
        return ladoA;
    }

    public int getLadoB() {
        return ladoB;
    }

    public boolean podeSerJogadaCom(Peca outra) {
        return this.ladoA == outra.ladoA || this.ladoA == outra.ladoB ||
                this.ladoB == outra.ladoA || this.ladoB == outra.ladoB;
    }

    @Override
    public String toString() {
        return "{" + ladoA + ", " + ladoB + '}';
    }
}
