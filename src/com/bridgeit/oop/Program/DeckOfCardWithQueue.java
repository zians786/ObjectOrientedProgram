package com.bridgeit.oop.Program;


import java.util.Scanner;

import com.bridgeit.oop.Utility.LinkedQueue;
import com.bridgeit.oop.Utility.Utility;

public class DeckOfCardWithQueue {

	public static void main(String[] args) {
		Utility utility=new Utility();


		LinkedQueue players=new LinkedQueue();
		LinkedQueue cards=new LinkedQueue(); 

		
		Scanner scanner=new Scanner(System.in);
		String [] suit={"Clubs", "Diamonds", "Hearts", "Spades"};
		String [] rank={"2"  , "3"  , "4"  , "5"  , "6"  , "7"  , "8"  , "9"  , "Jack"  , "Queen"  , "King"  , "Ace"};
		String [] card=new String[suit.length*rank.length];
		String [][] player=new String[4][9];
		
		for(int i=0,k=0;i<suit.length;i++){
			for(int j=0;j<rank.length;j++){
				card[k++]=rank[j]+"--"+suit[i];
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
		
	System.out.println("Cards After Sorting According to Ranks... ");
	System.out.println();	
		for(int i=0;i<4;i++){
			String play="";
			for(int j=0;j<9;j++){
				play=play+player[i][j]+" ";
			}
			String[] playerCard=play.split(" ");
			utility.bubbleSort(playerCard);
			for(String s:playerCard){
				cards.insert(s);
			}
			players.insert(i+1);
		}
	
		for(int i=0;i<4;i++){
			System.out.println("Cards of Player "+players.remove()+" are --> ");
			for(int j=0;j<9;j++){
				System.out.println(cards.remove());
			}
			System.out.println();
			
		}
		
		

	}

}
