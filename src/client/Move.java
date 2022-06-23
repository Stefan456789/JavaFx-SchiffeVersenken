package client;

import java.util.Objects;

public class Move {
    public final int x;
    public final int y;
    public final boolean wasHit;

    public Move(int x, char y, boolean wasHit) {
        this.x = x;
        this.y = y;
        this.wasHit = wasHit;
    }public Move(int x, int y, boolean wasHit) {
        this.x = x;
        this.y = y;
        this.wasHit = wasHit;
    }

    public Move(String x, String y, String wasHit) {

        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
        this.wasHit = Boolean.parseBoolean(wasHit);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean wasLastShotHit() {
        return wasHit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return x == move.x && y == move.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
