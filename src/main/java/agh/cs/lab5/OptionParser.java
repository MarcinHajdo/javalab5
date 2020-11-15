package agh.cs.lab5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionParser {
    MoveDirection[] parse(String[] a){

        int flag=0;
        MoveDirection[] moves = new MoveDirection[a.length];
        for (int i = 0; i < a.length; i++) {
            switch (a[i]) {
                case "f", "forward":
                    moves[i-flag]=MoveDirection.FORWARD;
                    break;
                case "b", "backward":
                    moves[i-flag]=MoveDirection.BACKWARD;
                    break;
                case "r", "right" :
                    moves[i-flag]=MoveDirection.RIGHT;
                    break;
                case "l", "left" :
                    moves[i-flag]=MoveDirection.LEFT;
                    break;
                default :
                    flag++;
                    throw new IllegalArgumentException(a[i] + " is not legal move specification");
            }

        }
        return Arrays.copyOf(moves,a.length-flag);
    }

    List<MoveDirection> parseList(String args){
        String[] a=args.split(" ");
        List<MoveDirection> moves  = new ArrayList<MoveDirection>();
        for (int i = 0; i < a.length; i++) {
            switch (a[i]) {
                case "f", "forward" -> moves.add(MoveDirection.FORWARD);
                case "b", "backward" -> moves.add(MoveDirection.BACKWARD);
                case "r", "right" -> moves.add(MoveDirection.RIGHT);
                case "l", "left" -> moves.add(MoveDirection.LEFT);
                default ->{}
            }
        }
        return moves;
    }
}
