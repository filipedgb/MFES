package MFES;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Track {
    private VDMSeq track = SeqUtil.seq();
    private VDMSet trains = SetUtil.set();

    public Track() {
        cg_init_Track_1();
    }

    public void cg_init_Track_1() {
        return;
    }

    public VDMSeq getTrack() {
        return this.track;
    }

    public void addBlockToTrack(final Block block) {
        block.setIndexInTrack(track.size() + 1L);
        track = SeqUtil.conc(Utils.copy(track), SeqUtil.seq(block));
    }

    public VDMSet getTrains() {
        return this.trains;
    }

    public void addTrainToTrains(final Train t) {
        trains = SetUtil.dunion(SetUtil.set(Utils.copy(trains), SetUtil.set(t)));
    }

    public void changeSemaphoresToRed(final Train t) {
        Block b = t.getCurrentBlock();
        Number initialIndex = b.getIndexInTrack();
        Boolean direction = t.getDirection();
        Number index = initialIndex;

        if (t.getDirection()) {
            Boolean whileCond_1 = true;

            while (whileCond_1) {
                whileCond_1 = Utils.equals(((Block) Utils.get(track, index)).isStation(),
                        false);

                if (!(whileCond_1)) {
                    break;
                }

                ((Block) Utils.get(track, index)).setSemaphore2(MFES.quotes.REDQuote.getInstance());
                index = getNextBlockIndex(index, direction);
            }
        } else {
            Boolean whileCond_2 = true;

            while (whileCond_2) {
                whileCond_2 = Utils.equals(((Block) Utils.get(track, index)).isStation(),
                        false);

                if (!(whileCond_2)) {
                    break;
                }

                ((Block) Utils.get(track, index)).setSemaphore1(MFES.quotes.REDQuote.getInstance());
                index = getNextBlockIndex(index, direction);
            }
        }
    }

    public VDMSet getBlocksBetween(final Train t) {
        VDMSet ret = SetUtil.set();
        Number index = t.getCurrentBlock().getIndexInTrack();
        Boolean whileCond_3 = true;

        while (whileCond_3) {
            whileCond_3 = Utils.equals(((Block) Utils.get(track, index)).isStation(),
                    false);

            if (!(whileCond_3)) {
                break;
            }

            ret = SetUtil.dunion(SetUtil.set(Utils.copy(ret),
                        SetUtil.set(((Block) Utils.get(track, index)))));

            index = getNextBlockIndex(index, t.getDirection());
        }

        return Utils.copy(ret);
    }

    public void changeSemaphoresToGreen(final Train t) {
        Block b = t.getCurrentBlock();
        Number initialIndex = b.getIndexInTrack();
        Boolean direction = t.getDirection();
        Number index = getNextBlockIndex(initialIndex, direction);

        if (t.getDirection()) {
            Boolean whileCond_4 = true;

            while (whileCond_4) {
                whileCond_4 = Utils.equals(((Block) Utils.get(track, index)).isStation(),
                        false);

                if (!(whileCond_4)) {
                    break;
                }

                ((Block) Utils.get(track, index)).setSemaphore1(MFES.quotes.GREENQuote.getInstance());
                index = getNextBlockIndex(index, direction);
            }
        } else {
            Boolean whileCond_5 = true;

            while (whileCond_5) {
                whileCond_5 = Utils.equals(((Block) Utils.get(track, index)).isStation(),
                        false);

                if (!(whileCond_5)) {
                    break;
                }

                ((Block) Utils.get(track, index)).setSemaphore2(MFES.quotes.GREENQuote.getInstance());
                index = getNextBlockIndex(index, direction);
            }
        }
    }

    public Boolean askToLeaveStation(final Train t) {
        Block b = t.getCurrentBlock();
        Number initialIndex = b.getIndexInTrack();
        Boolean direction = t.getDirection();
        Number index = getNextBlockIndex(initialIndex, direction);

        if (t.getDirection()) {
            Boolean whileCond_6 = true;

            while (whileCond_6) {
                whileCond_6 = Utils.equals(((Block) Utils.get(track, index)).isStation(),
                        false);

                if (!(whileCond_6)) {
                    break;
                }

                if (((Block) Utils.get(track, index)).getHasTrain()) {
                    return false;
                }

                index = getNextBlockIndex(index, direction);
            }

            changeSemaphoresToGreen(t);

            return moveTrain(t);
        } else {
            Boolean whileCond_7 = true;

            while (whileCond_7) {
                whileCond_7 = Utils.equals(((Block) Utils.get(track, index)).isStation(),
                        false);

                if (!(whileCond_7)) {
                    break;
                }

                if (((Block) Utils.get(track, index)).getHasTrain()) {
                    return false;
                }

                index = getNextBlockIndex(index, direction);
            }

            changeSemaphoresToGreen(t);

            return moveTrain(t);
        }
    }

    public Boolean moveTrain(final Train t) {
        Block b = t.getCurrentBlock();
        Block nb = getNextBlock(t);
        Block pb = getPreviousBlock(t);

        if (t.getDirection()) {
            if (Utils.equals(nb.getSemaphore1(),
                        MFES.quotes.REDQuote.getInstance())) {
                if (b.isStation()) {
                    return askToLeaveStation(t);
                } else {
                    return false;
                }
            } else {
                t.setCurrentBlock(nb);
                b.setHasTrain(false);

                if (!(b.isStation())) {
                    b.setSemaphore1(MFES.quotes.YELLOWQuote.getInstance());
                } else {
                    b.setSemaphore1(MFES.quotes.REDQuote.getInstance());
                }

                if (!(pb.isStation())) {
                    pb.setSemaphore1(MFES.quotes.GREENQuote.getInstance());
                }

                if (b.isStation()) {
                    changeSemaphoresToRed(t);
                    b.setSemaphore1(MFES.quotes.YELLOWQuote.getInstance());
                }

                return true;
            }
        } else {
            if (Utils.equals(nb.getSemaphore2(),
                        MFES.quotes.REDQuote.getInstance())) {
                if (b.isStation()) {
                    return askToLeaveStation(t);
                } else {
                    return false;
                }
            } else {
                t.setCurrentBlock(nb);
                b.setHasTrain(false);

                if (!(b.isStation())) {
                    b.setSemaphore2(MFES.quotes.YELLOWQuote.getInstance());
                } else {
                    b.setSemaphore2(MFES.quotes.REDQuote.getInstance());
                }

                if (!(pb.isStation())) {
                    pb.setSemaphore2(MFES.quotes.GREENQuote.getInstance());
                }

                if (b.isStation()) {
                    changeSemaphoresToRed(t);
                    b.setSemaphore2(MFES.quotes.YELLOWQuote.getInstance());
                }

                return true;
            }
        }
    }

    public Block getNextBlock(final Train t) {
        Block b = t.getCurrentBlock();

        if (t.getDirection()) {
            Boolean andResult_4 = false;

            if (b.isStation()) {
                if (b.getIndexInTrack().longValue() >= (track.size() - 1L)) {
                    andResult_4 = true;
                }
            }

            if (andResult_4) {
                return ((Block) Utils.get(track, 1L));
            } else {
                if (b.getIndexInTrack().longValue() >= track.size()) {
                    return ((Block) Utils.get(track, 1L));
                } else {
                    if (t.getCurrentBlock().isStation()) {
                        return ((Block) Utils.get(track,
                            b.getIndexInTrack().longValue() + 2L));
                    } else {
                        return ((Block) Utils.get(track,
                            b.getIndexInTrack().longValue() + 1L));
                    }
                }
            }
        } else {
            Boolean andResult_5 = false;

            if (b.isStation()) {
                if (Utils.equals(b.getIndexInTrack(), 2L)) {
                    andResult_5 = true;
                }
            }

            if (andResult_5) {
                return ((Block) Utils.get(track, track.size()));
            } else {
                if (Utils.equals(b.getIndexInTrack(), 1L)) {
                    return ((Block) Utils.get(track, track.size()));
                } else {
                    if (t.getCurrentBlock().isStation()) {
                        return ((Block) Utils.get(track,
                            b.getIndexInTrack().longValue() - 2L));
                    } else {
                        return ((Block) Utils.get(track,
                            b.getIndexInTrack().longValue() - 1L));
                    }
                }
            }
        }
    }

    public Number getNextBlockIndex(final Number currentIndex,
        final Boolean direction) {
        if (direction) {
            Boolean andResult_9 = false;

            if (((Block) Utils.get(track, currentIndex)).isStation()) {
                if (currentIndex.longValue() >= (track.size() - 1L)) {
                    andResult_9 = true;
                }
            }

            if (andResult_9) {
                return 1L;
            } else {
                if (currentIndex.longValue() >= track.size()) {
                    return 1L;
                } else {
                    if (((Block) Utils.get(track, currentIndex)).isStation()) {
                        return currentIndex.longValue() + 2L;
                    } else {
                        return currentIndex.longValue() + 1L;
                    }
                }
            }
        } else {
            Boolean andResult_10 = false;

            if (((Block) Utils.get(track, currentIndex)).isStation()) {
                if (Utils.equals(currentIndex, 2L)) {
                    andResult_10 = true;
                }
            }

            if (andResult_10) {
                return track.size();
            } else {
                if (Utils.equals(currentIndex, 1L)) {
                    return track.size();
                } else {
                    if (((Block) Utils.get(track, currentIndex)).isStation()) {
                        return currentIndex.longValue() - 2L;
                    } else {
                        return currentIndex.longValue() - 1L;
                    }
                }
            }
        }
    }

    public Block getPreviousBlock(final Train t) {
        Block b = t.getCurrentBlock();

        if (t.getDirection()) {
            if (Utils.equals(b.getIndexInTrack(), 1L)) {
                if (((Block) Utils.get(track, track.size())).isStation()) {
                    return ((Block) Utils.get(track, track.size() - 1L));
                } else {
                    return ((Block) Utils.get(track, track.size()));
                }
            } else {
                if (((Block) Utils.get(track,
                            b.getIndexInTrack().longValue() - 1L)).isStation()) {
                    return ((Block) Utils.get(track,
                        b.getIndexInTrack().longValue() - 2L));
                } else {
                    return ((Block) Utils.get(track,
                        b.getIndexInTrack().longValue() - 1L));
                }
            }
        } else {
            Boolean andResult_12 = false;

            if (b.isStation()) {
                if (b.getIndexInTrack().longValue() >= track.size()) {
                    andResult_12 = true;
                }
            }

            if (andResult_12) {
                return ((Block) Utils.get(track, 2L));
            } else {
                if (b.getIndexInTrack().longValue() >= track.size()) {
                    return ((Block) Utils.get(track, 1L));
                } else {
                    if (((Block) Utils.get(track,
                                b.getIndexInTrack().longValue() + 1L)).isStation()) {
                        return ((Block) Utils.get(track,
                            b.getIndexInTrack().longValue() + 2L));
                    } else {
                        return ((Block) Utils.get(track,
                            b.getIndexInTrack().longValue() + 1L));
                    }
                }
            }
        }
    }

    public Number getPreviousBlockIndex(final Number currentIndex,
        final Boolean direction) {
        if (!(direction)) {
            Boolean andResult_16 = false;

            if (((Block) Utils.get(track, currentIndex)).isStation()) {
                if (currentIndex.longValue() >= (track.size() - 1L)) {
                    andResult_16 = true;
                }
            }

            if (andResult_16) {
                return 1L;
            } else {
                if (currentIndex.longValue() >= track.size()) {
                    return 1L;
                } else {
                    if (((Block) Utils.get(track, currentIndex)).isStation()) {
                        return currentIndex.longValue() + 2L;
                    } else {
                        return currentIndex.longValue() + 1L;
                    }
                }
            }
        } else {
            Boolean andResult_17 = false;

            if (((Block) Utils.get(track, currentIndex)).isStation()) {
                if (Utils.equals(currentIndex, 2L)) {
                    andResult_17 = true;
                }
            }

            if (andResult_17) {
                return track.size();
            } else {
                if (Utils.equals(currentIndex, 1L)) {
                    return track.size();
                } else {
                    if (((Block) Utils.get(track, currentIndex)).isStation()) {
                        return currentIndex.longValue() - 2L;
                    } else {
                        return currentIndex.longValue() - 1L;
                    }
                }
            }
        }
    }

    private Boolean twoTrainsInSameBlock(final VDMSet t) {
        Boolean existsExpResult_1 = false;
        VDMSet set_5 = Utils.copy(t);

        for (Iterator iterator_5 = set_5.iterator();
                iterator_5.hasNext() && !(existsExpResult_1);) {
            Train i = ((Train) iterator_5.next());

            for (Iterator iterator_6 = set_5.iterator();
                    iterator_6.hasNext() && !(existsExpResult_1);) {
                Train j = ((Train) iterator_6.next());
                Boolean andResult_18 = false;

                if (!(Utils.equals(i, j))) {
                    if (Utils.equals(i.getCurrentBlock(), j.getCurrentBlock())) {
                        andResult_18 = true;
                    }
                }

                existsExpResult_1 = andResult_18;
            }
        }

        return existsExpResult_1;
    }

    private Boolean hasDuplicatesIndexInTrack(final VDMSeq b) {
        Boolean existsExpResult_2 = false;
        VDMSet set_6 = SeqUtil.inds(b);

        for (Iterator iterator_7 = set_6.iterator();
                iterator_7.hasNext() && !(existsExpResult_2);) {
            Number i = ((Number) iterator_7.next());

            for (Iterator iterator_8 = set_6.iterator();
                    iterator_8.hasNext() && !(existsExpResult_2);) {
                Number j = ((Number) iterator_8.next());
                Boolean andResult_19 = false;

                if (!(Utils.equals(i, j))) {
                    if (Utils.equals(
                                ((Block) Utils.get(b, i)).getIndexInTrack(),
                                ((Block) Utils.get(b, j)).getIndexInTrack())) {
                        andResult_19 = true;
                    }
                }

                existsExpResult_2 = andResult_19;
            }
        }

        return existsExpResult_2;
    }

    private Boolean currentBlockOnTrains(final VDMSet t) {
        Boolean forAllExpResult_5 = true;
        VDMSet set_7 = Utils.copy(t);

        for (Iterator iterator_9 = set_7.iterator();
                iterator_9.hasNext() && forAllExpResult_5;) {
            Train i = ((Train) iterator_9.next());
            forAllExpResult_5 = Utils.equals(i.getCurrentBlock().getHasTrain(),
                    true);
        }

        return forAllExpResult_5;
    }

    private static <T> Boolean hasDuplicates(final VDMSeq s) {
        Boolean existsExpResult_3 = false;
        VDMSet set_8 = SeqUtil.inds(s);

        for (Iterator iterator_10 = set_8.iterator();
                iterator_10.hasNext() && !(existsExpResult_3);) {
            Number i = ((Number) iterator_10.next());

            for (Iterator iterator_11 = set_8.iterator();
                    iterator_11.hasNext() && !(existsExpResult_3);) {
                Number j = ((Number) iterator_11.next());
                Boolean andResult_20 = false;

                if (!(Utils.equals(i, j))) {
                    if (Utils.equals(((T) Utils.get(s, i)),
                                ((T) Utils.get(s, j)))) {
                        andResult_20 = true;
                    }
                }

                existsExpResult_3 = andResult_20;
            }
        }

        return existsExpResult_3;
    }

    private static <T> Boolean hasDuplicatesSet(final VDMSet s) {
        Boolean existsExpResult_4 = false;
        VDMSet set_9 = Utils.copy(s);

        for (Iterator iterator_12 = set_9.iterator();
                iterator_12.hasNext() && !(existsExpResult_4);) {
            T i = ((T) iterator_12.next());

            for (Iterator iterator_13 = set_9.iterator();
                    iterator_13.hasNext() && !(existsExpResult_4);) {
                T j = ((T) iterator_13.next());
                Boolean andResult_21 = false;

                if (!(Utils.equals(i, j))) {
                    if (Utils.equals(i, j)) {
                        andResult_21 = true;
                    }
                }

                existsExpResult_4 = andResult_21;
            }
        }

        return existsExpResult_4;
    }

    public String toString() {
        return "Track{" + "track := " + Utils.toString(track) + ", trains := " +
        Utils.toString(trains) + "}";
    }
}
