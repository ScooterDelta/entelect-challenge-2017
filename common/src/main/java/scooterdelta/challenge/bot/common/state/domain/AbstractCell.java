package scooterdelta.challenge.bot.common.state.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public abstract class AbstractCell {

    @SerializedName("X")
    private int x;
    @SerializedName("Y")
    private int y;

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public Point getPoint() {
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return "AbstractCell{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AbstractCell that = (AbstractCell) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
