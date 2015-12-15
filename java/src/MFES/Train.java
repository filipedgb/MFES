package MFES;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Train {
    private Boolean direction;
    private Block currentBlock = null;

    public Train(final Boolean directionIn) {
        cg_init_Train_1(directionIn);
    }

    public Train() {
    }

    public void cg_init_Train_1(final Boolean directionIn) {
        direction = directionIn;

        return;
    }

    public Boolean getDirection() {
        return this.direction;
    }

    public void setDirection(final Boolean d) {
        direction = d;
    }

    public Block getCurrentBlock() {
        return this.currentBlock;
    }

    public void setCurrentBlock(final Block b) {
        b.setHasTrain(true);
        currentBlock = b;
        b.setSemaphore1(MFES.quotes.REDQuote.getInstance());
        b.setSemaphore2(MFES.quotes.REDQuote.getInstance());
    }

    private Boolean currentBlockHasTrain() {
        Boolean orResult_1 = false;

        if (Utils.equals(this.currentBlock, null)) {
            orResult_1 = true;
        } else {
            orResult_1 = this.currentBlock.getHasTrain();
        }

        return orResult_1;
    }

    public String toString() {
        return "Train{" + "direction := " + Utils.toString(direction) +
        ", currentBlock := " + Utils.toString(currentBlock) + "}";
    }
}
