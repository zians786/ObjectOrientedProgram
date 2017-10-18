package com.bridgeit.oop.Program;

import java.util.Scanner;

public class DeckOfCard {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		String [] suit={"Clubs", "Diamonds", "Hearts", "Spades"};
		String [] rank={"2"  , "3"  , "4"  , "5"  , "6"  , "7"  , "8"  , "9"  , "10"  , "Jack"  , "Queen"  , "King "  , "Ace"};
		String [] card=new String[suit.length*rank.length];
		String [][] player=new String[4][9];
		
		for(int i=0,k=0;i<suit.length;i++){
			for(int j=0;j<rank.length;j++){
				card[k++]=rank[j]+" of "+suit[i];
			}
		}
		
		int n=0;
		String[] randomCard=new String[card.length];
		while(n<card.length){
			boolean status=true;
		int m=(int)(Math.random()*card.length);
			String random=card[m];
			for(int i=0;i<card.length;i++){
				if(random.equals(randomCard[i])){
					status=false;
				}
			}
			if(status){
				randomCard[n++]=random;
			}
		
		}
		
		for(int i=0,k=0;i<4;i++){
			for(int j=0;j<9;j++){
				player[i][j]=randomCard[k++];
			}
		}
		
		for(int i=0;i<4;i++){
			System.out.println("Cards of player "+(i+1)+" are --> ");
			for(int j=0;j<9;j++){
				System.out.println(player[i][j]);
			}
			System.out.println();
		}
	}

}
