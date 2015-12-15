package MFES;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Block {
    private VDMMap semaphores = MapUtil.map(new Maplet("cw",
                MFES.quotes.GREENQuote.getInstance()),
            new Maplet("ccw", MFES.quotes.GREENQuote.getInstance()));
    private Boolean hasTrain = false;
    private Boolean isPartOfStation = false;
    private Number indexInTrack = 1L;

    public Block(final Object semaphoreStateIn1, final Object semaphoreStateIn2) {
        cg_init_Block_1(((Object) semaphoreStateIn1),
            ((Object) semaphoreStateIn2));
    }

    public Block() {
    }

    public void cg_init_Block_1(final Object semaphoreStateIn1,
        final Object semaphoreStateIn2) {
        Utils.mapSeqUpdate(semaphores, "cw", semaphoreStateIn1);
        Utils.mapSeqUpdate(semaphores, "ccw", semaphoreStateIn2);

        return;
    }

    public void setSemaphore1(final Object stateIn) {
        Utils.mapSeqUpdate(semaphores, "cw", stateIn);
    }

    public void setSemaphore2(final Object stateIn) {
        Utils.mapSeqUpdate(semaphores, "ccw", stateIn);
    }

    public Object getSemaphore1() {
        return Utils.get(this.semaphores, "cw");
    }

    public Object getSemaphore2() {
        return Utils.get(this.semaphores, "ccw");
    }

    public Number getIndexInTrack() {
        return this.indexInTrack;
    }

    public void setIndexInTrack(final Number i) {
        indexInTrack = i;
    }

    public Boolean getHasTrain() {
        return this.hasTrain;
    }

    public void setHasTrain(final Boolean h) {
        hasTrain = h;
    }

    public void setStation(final Boolean s) {
        isPartOfStation = s;
    }

    public Boolean isStation() {
        return this.isPartOfStation;
    }

    public String toString() {
        return "Block{" + "semaphores := " + Utils.toString(semaphores) +
        ", hasTrain := " + Utils.toString(hasTrain) + ", isPartOfStation := " +
        Utils.toString(isPartOfStation) + ", indexInTrack := " +
        Utils.toString(indexInTrack) + "}";
    }
}
