package ood.blackjack;

/**
 * Created by Xiaotian on 3/20/18.
 */
public class Solution {
    public static void main(String[] args) {
//        System.out.println(Level(3));
//        Suit suit = Suit.getSuitByValue(3);
//        System.out.println(suit.getValue());
        System.out.println(Suit.CLUB);
    }
}

enum Suit{DIAMOND, HEART, SPADE, CLUB}

// enum Suit {
//    Club (0), Diamond (1), Heart (2), Space (3);
//
//    private int value;
//
//    Suit(int v) { this.value = v; }
//
//    public int getValue() { return value; }
//
//    public static Suit getSuitByValue(int v) {
//        switch (v) {
//            case 0: return Suit.Club;
//            case 1: return Suit.Diamond;
//            case 2: return Suit.Heart;
//            case 3: return Suit.Space;
//            default: return null;
//        }
//    }
//
//}
//    public static Suit getSuitFromValue(int value){
//        switch(value){
//            case 0 :
//                return Suit.Club;
//            case 1:
//                return Suit.Diamon;
//            case 2 :
//                return Suit.Heart;
//            case 3:
//                return Suit.Spade;
//            default:
//                return null;
//        }
//    }
//1 public enum Suit {
//    2 Club (0), Diamond (1), Heart (2), Spade (3);
//    3 private int value;
//    4 private Suit(int v) { value = v; }
//    5 public int getValue() { return value; }
//    6 public static Suit getSuitFromValue(int value) { … }
//    7 }
