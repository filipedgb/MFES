package MFES;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class TestRailway extends MyTestCase {
    public TestRailway() {
    }

    public void testCreateBlock() {
        Block p = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        super.assertEqual(MFES.quotes.YELLOWQuote.getInstance(),
            ((Object) p.getSemaphore1()));
        super.assertEqual(MFES.quotes.YELLOWQuote.getInstance(),
            ((Object) p.getSemaphore2()));
    }

    public void testCreateTrack() {
        Block p1 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Block p2 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Block p3 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Block p4 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Block p5 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Block p6 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Track track = new Track();
        track.addBlockToTrack(p1);
        track.addBlockToTrack(p2);
        track.addBlockToTrack(p3);
        track.addBlockToTrack(p4);
        track.addBlockToTrack(p6);
        super.assertEqual(5L, track.getTrack().size());
        track.addBlockToTrack(p5);
        super.assertEqual(6L, track.getTrack().size());
        super.assertEqual(p5,
            ((Block) Utils.get(track.getTrack(), track.getTrack().size())));
        super.assertEqual(6L, p5.getIndexInTrack());
    }

    public void testStation() {
        Block p1 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p2 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        p1.setStation(true);
        super.assertTrue(p1.isStation());
        super.assertTrue(!(p2.isStation()));
    }

    public void testMoveTrain() {
        Block p1 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Block p2 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Block p3 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Block p4 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Block p5 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Block p6 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Track track = new Track();
        Train trainForward = new Train(true);
        Train trainBackward = new Train(false);
        Boolean dummy = false;
        track.addBlockToTrack(p1);
        track.addBlockToTrack(p2);
        track.addBlockToTrack(p3);
        track.addBlockToTrack(p4);
        track.addBlockToTrack(p5);
        track.addBlockToTrack(p6);
        super.assertEqual(6L, track.getTrack().size());
        trainForward.setCurrentBlock(p4);
        trainBackward.setCurrentBlock(p3);
        track.addTrainToTrains(trainForward);
        track.addTrainToTrains(trainBackward);
        super.assertEqual(track.getTrains().size(), 2L);
        super.assertEqual(p4, trainForward.getCurrentBlock());
        super.assertEqual(p3, trainBackward.getCurrentBlock());
        super.assertTrue(p4.getHasTrain());
        super.assertTrue(p3.getHasTrain());
        dummy = track.moveTrain(trainForward);
        dummy = track.moveTrain(trainBackward);
        super.assertEqual(p5, trainForward.getCurrentBlock());
        super.assertEqual(p2, trainBackward.getCurrentBlock());
        super.assertTrue(!(p4.getHasTrain()));
        super.assertTrue(!(p3.getHasTrain()));
        super.assertTrue(p5.getHasTrain());
        super.assertTrue(p2.getHasTrain());
        dummy = track.moveTrain(trainForward);
        dummy = track.moveTrain(trainForward);
        super.assertEqual(p1, trainForward.getCurrentBlock());
        super.assertTrue(p1.getHasTrain());
        trainForward.setDirection(false);
        super.assertTrue(!(trainForward.getDirection()));
        dummy = track.moveTrain(trainForward);
        super.assertTrue(!(dummy));
        super.assertEqual(p1, trainForward.getCurrentBlock());
        super.assertTrue(p1.getHasTrain());
    }

    public void testMoveTrainWithStations() {
        Block p1 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Block p2 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Block p3 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Block p4 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Block p5 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Block p6 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());
        Boolean dummy = false;
        Track track = new Track();
        Train trainForward = new Train(true);
        Train trainBackward = new Train(false);
        p3.setStation(true);
        p4.setStation(true);
        track.addBlockToTrack(p1);
        track.addBlockToTrack(p2);
        track.addBlockToTrack(p3);
        track.addBlockToTrack(p4);
        track.addBlockToTrack(p5);
        track.addBlockToTrack(p6);
        super.assertEqual(6L, track.getTrack().size());
        trainForward.setCurrentBlock(p2);
        trainBackward.setCurrentBlock(p5);
        track.addTrainToTrains(trainForward);
        track.addTrainToTrains(trainBackward);
        dummy = track.moveTrain(trainForward);
        dummy = track.moveTrain(trainBackward);
        super.assertEqual(p3, trainForward.getCurrentBlock());
        super.assertEqual(p4, trainBackward.getCurrentBlock());
        dummy = track.moveTrain(trainForward);
        dummy = track.moveTrain(trainBackward);
        super.assertEqual(p5, trainForward.getCurrentBlock());
        super.assertEqual(p4, trainBackward.getCurrentBlock());
        dummy = track.moveTrain(trainForward);
        dummy = track.moveTrain(trainBackward);
        super.assertEqual(p6, trainForward.getCurrentBlock());
        super.assertEqual(p4, trainBackward.getCurrentBlock());
        dummy = track.moveTrain(trainForward);
        dummy = track.moveTrain(trainBackward);
        dummy = track.moveTrain(trainForward);
        dummy = track.moveTrain(trainBackward);
        dummy = track.moveTrain(trainForward);
        dummy = track.moveTrain(trainBackward);
        super.assertEqual(p3, trainForward.getCurrentBlock());
        super.assertEqual(p2, trainBackward.getCurrentBlock());
    }

    public void testTrainWaiting() {
        Block p1 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p2 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p3 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p4 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p5 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p6 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p7 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p8 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p9 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Boolean dummy = false;
        Track track = new Track();
        Train trainForward = new Train(true);
        Train trainBackward = new Train(false);
        p3.setStation(true);
        p4.setStation(true);
        p8.setStation(true);
        p9.setStation(true);
        track.addBlockToTrack(p1);
        track.addBlockToTrack(p2);
        track.addBlockToTrack(p3);
        track.addBlockToTrack(p4);
        track.addBlockToTrack(p5);
        track.addBlockToTrack(p6);
        track.addBlockToTrack(p7);
        track.addBlockToTrack(p8);
        track.addBlockToTrack(p9);
        trainForward.setCurrentBlock(p2);
        trainBackward.setCurrentBlock(p9);
        track.addTrainToTrains(trainForward);
        track.addTrainToTrains(trainBackward);
        super.assertEqual(MFES.quotes.GREENQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 7L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.GREENQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 6L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.GREENQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 5L)).getSemaphore1()));
        dummy = track.moveTrain(trainForward);
        dummy = track.moveTrain(trainBackward);
        super.assertEqual(p7, trainBackward.getCurrentBlock());
        super.assertEqual(MFES.quotes.REDQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 7L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.REDQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 6L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.REDQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 5L)).getSemaphore1()));
        dummy = track.moveTrain(trainForward);
        super.assertEqual(false, dummy);
        dummy = track.moveTrain(trainBackward);
        dummy = track.moveTrain(trainForward);
        super.assertEqual(false, dummy);
        dummy = track.moveTrain(trainBackward);
        dummy = track.moveTrain(trainForward);
        super.assertEqual(false, dummy);
        dummy = track.moveTrain(trainBackward);
        super.assertEqual(p3, trainForward.getCurrentBlock());
        super.assertEqual(p4, trainBackward.getCurrentBlock());
        dummy = track.moveTrain(trainForward);
        super.assertEqual(p5, trainForward.getCurrentBlock());
        super.assertEqual(MFES.quotes.REDQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 7L)).getSemaphore2()));
        super.assertEqual(MFES.quotes.REDQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 6L)).getSemaphore2()));
        super.assertEqual(MFES.quotes.REDQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 5L)).getSemaphore2()));
    }

    public void testAvoidingCollision() {
        Block p1 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p2 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p3 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p4 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p5 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p6 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p7 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p8 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p9 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p10 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Boolean dummy = false;
        Track track = new Track();
        Train trainForward = new Train(true);
        Train trainBackward = new Train(false);
        Train trainBackward2 = new Train(false);
        p3.setStation(true);
        p4.setStation(true);
        p8.setStation(true);
        p9.setStation(true);
        track.addBlockToTrack(p1);
        track.addBlockToTrack(p2);
        track.addBlockToTrack(p3);
        track.addBlockToTrack(p4);
        track.addBlockToTrack(p5);
        track.addBlockToTrack(p6);
        track.addBlockToTrack(p7);
        track.addBlockToTrack(p8);
        track.addBlockToTrack(p9);
        track.addBlockToTrack(p10);
        trainForward.setCurrentBlock(p2);
        trainBackward.setCurrentBlock(p9);
        trainBackward2.setCurrentBlock(p10);
        track.addTrainToTrains(trainForward);
        track.addTrainToTrains(trainBackward);
        track.addTrainToTrains(trainBackward2);
        super.assertEqual(MFES.quotes.GREENQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 7L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.GREENQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 6L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.GREENQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 5L)).getSemaphore1()));
        dummy = track.moveTrain(trainForward);
        dummy = track.moveTrain(trainBackward);
        dummy = track.moveTrain(trainBackward2);
        super.assertEqual(p9, trainBackward2.getCurrentBlock());
        dummy = track.moveTrain(trainForward);
        dummy = track.moveTrain(trainBackward);
        dummy = track.moveTrain(trainBackward2);
        dummy = track.moveTrain(trainForward);
        dummy = track.moveTrain(trainBackward);
        dummy = track.moveTrain(trainBackward2);
        dummy = track.moveTrain(trainForward);
        dummy = track.moveTrain(trainBackward);
        dummy = track.moveTrain(trainBackward2);
        super.assertEqual(p3, trainForward.getCurrentBlock());
        super.assertEqual(p4, trainBackward.getCurrentBlock());
        super.assertEqual(p5, trainBackward2.getCurrentBlock());
        dummy = track.moveTrain(trainForward);
        dummy = track.moveTrain(trainBackward);
        dummy = track.moveTrain(trainBackward2);
        dummy = track.moveTrain(trainForward);
        super.assertEqual(p5, trainForward.getCurrentBlock());
        super.assertEqual(MFES.quotes.REDQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 7L)).getSemaphore2()));
        super.assertEqual(MFES.quotes.REDQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 6L)).getSemaphore2()));
        super.assertEqual(MFES.quotes.REDQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 5L)).getSemaphore2()));
    }

    public void testSemaphores() {
        Block p1 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p2 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p3 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p4 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Block p5 = new Block(MFES.quotes.GREENQuote.getInstance(),
                MFES.quotes.GREENQuote.getInstance());
        Track track = new Track();
        Boolean dummy = false;
        Train trainForward = new Train(true);
        p1.setStation(true);
        p2.setStation(true);
        track.addBlockToTrack(p1);
        track.addBlockToTrack(p2);
        track.addBlockToTrack(p3);
        track.addBlockToTrack(p4);
        track.addBlockToTrack(p5);
        trainForward.setCurrentBlock(p1);
        track.addTrainToTrains(trainForward);
        dummy = track.moveTrain(trainForward);
        super.assertEqual(MFES.quotes.YELLOWQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 1L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.REDQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 3L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.GREENQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 4L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.GREENQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 5L)).getSemaphore1()));
        dummy = track.moveTrain(trainForward);
        super.assertEqual(MFES.quotes.YELLOWQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 1L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.YELLOWQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 3L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.REDQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 4L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.REDQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 4L)).getSemaphore2()));
        super.assertEqual(MFES.quotes.GREENQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 5L)).getSemaphore1()));
        dummy = track.moveTrain(trainForward);
        super.assertEqual(MFES.quotes.YELLOWQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 1L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.GREENQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 3L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.YELLOWQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 4L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.REDQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 5L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.REDQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 5L)).getSemaphore2()));
        dummy = track.moveTrain(trainForward);
        super.assertEqual(MFES.quotes.REDQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 1L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.GREENQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 4L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.YELLOWQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 5L)).getSemaphore1()));
        super.assertEqual(MFES.quotes.REDQuote.getInstance(),
            ((Object) ((Block) Utils.get(track.getTrack(), 5L)).getSemaphore2()));
    }

    public void runAllTests() {
        testCreateBlock();
        testCreateTrack();
        testMoveTrain();
        testMoveTrainWithStations();
        testStation();
        testTrainWaiting();
        testAvoidingCollision();
        testSemaphores();
    }

    public String toString() {
        return "TestRailway{}";
    }
}
