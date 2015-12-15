package MFES;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Main {
    public Main() {
    }

    public static void Run() {
        new TestRailway().runAllTests();
    }

    public String toString() {
        return "Main{}";
    }

    public static void main(String[] args) {
        Run();
        //IO.println(Utils.toString(Utils.VOID_VALUE));
        CLI();
    }

    public static void CLI() {
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

        track.addBlockToTrack(p1);
        track.addBlockToTrack(p2);
        track.addBlockToTrack(p3);
        track.addBlockToTrack(p4);
        track.addBlockToTrack(p5);
        track.addBlockToTrack(p6);

        trainForward.setCurrentBlock(p4);

        track.addTrainToTrains(trainForward);

        System.out.println(track.getTrack().toString());

    }
}
