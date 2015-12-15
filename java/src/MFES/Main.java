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

        System.out.println("Hello. This is a railway system model");
        System.out.println("Type the desired size of the track:");

        Scanner sc1 = new Scanner(System.in);
        Integer input1 = sc1.nextInt();

        Track track = new Track();


        Block p0 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());

        Block p1 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());

        Block p2 = new Block(MFES.quotes.YELLOWQuote.getInstance(),
                MFES.quotes.YELLOWQuote.getInstance());

        track.addBlockToTrack(p1);
        track.addBlockToTrack(p2);

        p1.setStation(true);
        p2.setStation(true);

        track = createTrack(track,input1);



        Train trainForward = new Train(true);
        Train trainBackward = new Train(false);
        trainForward.setCurrentBlock(p1);
        trainBackward.setCurrentBlock(p2);

        track.addTrainToTrains(trainForward);
        track.addTrainToTrains(trainBackward);


        while(true) {
            displayTrack(track,trainForward,trainBackward);

            Scanner sc = new Scanner(System.in);
            String input = sc.next();

            switch(input) {
                case "a":
                    if(track.moveTrain(trainForward)) System.out.println("Train forward has moved");
                    else System.out.println("Train forward can't move");
                    break;
                case "b":
                    if(track.moveTrain(trainBackward)) System.out.println("Train backward has moved");
                    else System.out.println("Train backward can't move");
                    break;
            }

            if( trainForward.getCurrentBlock().isStation()) {
                System.out.println("Clockwise train is in station. Index: " + trainForward.getCurrentBlock().getIndexInTrack()) ;
            } else {
                System.out.println("Clockwise train - index: " + trainForward.getCurrentBlock().getIndexInTrack()) ;
            }

            if( trainBackward.getCurrentBlock().isStation()) {
                System.out.println("Counter-clockwise train is in station. Index: " + trainBackward.getCurrentBlock().getIndexInTrack());
            }
             else {
                System.out.println("Counter-clockwise train - index: "+ trainBackward.getCurrentBlock().getIndexInTrack());
            }

        }


    }

    public static void displayTrack(Track track,Train f, Train b) {
        if(f.getCurrentBlock().isStation())  System.out.println("   F");
        else System.out.println("   _");
        System.out.print("_ | |");
        int i = 0;
        for( i = 3; i <= track.getTrack().size(); i++) {
            if(f.getCurrentBlock().getIndexInTrack().intValue() == i) {
                System.out.print(" F");
            } else if(b.getCurrentBlock().getIndexInTrack().intValue() == i){
                System.out.print(" B");
            }
            else System.out.print("  _");
        }

        System.out.println("");
        if(b.getCurrentBlock().isStation()) {
            System.out.println("  | |");
            System.out.println("   B ");
        }
        else {
            System.out.print("  |_|");
        }
        System.out.println("");
        System.out.println("");

    }

    public static Track createTrack(Track track,int tamanho) {
        if(tamanho < 4) {
            System.out.println("tamanho invalido");
        }

        System.out.println("Tamanho do track: " + tamanho);


        for(int i = 1; i <= tamanho-2; i++) {
            track.addBlockToTrack(new Block(MFES.quotes.YELLOWQuote.getInstance(),
                    MFES.quotes.YELLOWQuote.getInstance()));
        }


        return track;

    }
}
