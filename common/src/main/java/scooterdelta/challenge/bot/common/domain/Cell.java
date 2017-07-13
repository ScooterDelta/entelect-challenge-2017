package scooterdelta.challenge.bot.common.domain;

import java.util.Objects;

public class Cell extends AbstractCell {

    private boolean occupied;
    private boolean hit;

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(final boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(final boolean hit) {
        this.hit = hit;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "occupied=" + occupied +
                ", hit=" + hit +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        final Cell cell = (Cell) o;
        return occupied == cell.occupied &&
                hit == cell.hit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), occupied, hit);
    }
}
