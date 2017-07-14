package scooterdelta.challenge.bot.common.state.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class OpponentCell extends AbstractCell {

    @SerializedName("Damaged")
    private boolean damaged;
    @SerializedName("Missed")
    private boolean missed;

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamaged(final boolean damaged) {
        this.damaged = damaged;
    }

    public boolean isMissed() {
        return missed;
    }

    public void setMissed(final boolean missed) {
        this.missed = missed;
    }

    @Override
    public String toString() {
        return "OpponentCell{" +
                "damaged=" + damaged +
                ", missed=" + missed +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        final OpponentCell that = (OpponentCell) o;
        return damaged == that.damaged &&
                missed == that.missed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), damaged, missed);
    }
}
