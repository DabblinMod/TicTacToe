package tictactoe;

import java.util.*;
import java.util.Random;


public class TicTacToe {
	//keep track of the selected positions
	static ArrayList<Integer> playerPositions=new ArrayList<Integer>();
	static ArrayList<Integer> compPositions=new ArrayList<Integer>();
	
	public static void printGameBoard(char[][] gameBoard) {
		//use a for-each loop
		for(char[] r: gameBoard) {//row
			for(char c: r) {//item
				System.out.print(c);
			}
			System.out.println();//creates a new line between each board print
		}
	}
	public static int pickPosition() {
		int place=0;
		System.out.println("To select a position on the board, enter a number within the range of 1-9.\n");
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a a number (1-9): ");
		place=sc.nextInt();
		while(place <= 0) {
			System.out.println("Entered the wrong number. Enter a a number (1-9): ");
			place=sc.nextInt();
		}
		System.out.println("Chosen number: "+place);
		return place;
	}
	
	public static int compPickPos() {
		int pos=0;
		Random random=new Random();
		pos=random.nextInt(9)+1;//1 ensures that the range goes from 1 to 9 instead of 0 to 9
		System.out.println("Cpu has chosen num: "+pos);
		return pos;
	}
	
	public static void placePiece(char[][] gameBoard, int pos, String user) {
		char mark;
		if(user.equals("player") || user.equals("player".toUpperCase())) {
			mark='X';
			if(playerPositions.contains(pos) || compPositions.contains(pos)) {
				//have the player pick another position
				System.out.println("Wrong, this number has already been used. Select another");
				while(playerPositions.contains(pos) || compPositions.contains(pos)) {
					pos=pickPosition();//to repick the position
				}
			}
			else
				playerPositions.add(pos);
		}
		else if(user.equals("CPU") || user.equals("CPU".toLowerCase()) || user.equals("Computer")) {
			mark= 'O';
			if(compPositions.contains(pos) || playerPositions.contains(pos)) {
				//change the position number
				System.out.println("Number already used! Select another");
				while(playerPositions.contains(pos) || compPositions.contains(pos)) {
					pos=compPickPos();
				}
			}
			else
				compPositions.add(pos);
		}
		else
			mark=' ';
		switch(pos) {
			case 1: 
				gameBoard[0][0]=mark;
				break;
			case 2:
				gameBoard[0][2]=mark;
				break;
			case 3:
				gameBoard[0][4]=mark;
				break;
			case 4:
				gameBoard[2][0]=mark;
				break;
			case 5:
				gameBoard[2][2]=mark;
				break;
			case 6:
				gameBoard[2][4]=mark;
				break;
			case 7:
				gameBoard[4][0]=mark;
				break;
			case 8:
				gameBoard[4][2]=mark;
				break;
			case 9:
				gameBoard[4][4]=mark;
				break;
			default: System.out.println("Exceeded the board's size. You're going outside the box!");
				break;
		}
	}
	
	public static String checkWinner() {
		String winner="";
		//rows
		List topRow= Arrays.asList(1,2,3);
		List midRow= Arrays.asList(4,5,6);
		List lastRow= Arrays.asList(7,8,9);
		
		//columns
		List rCol=Arrays.asList(1,4,7);
		List mCol=Arrays.asList(2,5,8);
		List lCol=Arrays.asList(3,6,9);
		
		//diagonals
		List cross1=Arrays.asList(1,5,9);
		List cross2=Arrays.asList(3,5,7);
		
		List<List> wining= new ArrayList<List>();
		wining.add(topRow);
		wining.add(midRow);
		wining.add(lastRow);
		wining.add(rCol);
		wining.add(mCol);
		wining.add(lCol);
		wining.add(cross1);
		wining.add(cross2);
		
		for(List l: wining) {
			if(playerPositions.containsAll(l)) {
				winner="Congratulations, player! You won!";
				break;
			}
			if(compPositions.containsAll(l)) {
				winner="Congratulations, computer! You won!";
				break;
			}
			else if(playerPositions.size() + compPositions.size() == 9){
				return "CAT! Board is full!";
			}
		}
		return winner;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] board= {{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '}
		};
		
		System.out.printf("Board has a %d x %d\n\n", board.length, board[0].length);
		int tries=4;
		while(tries > 0) {
			System.out.println();
			TicTacToe.printGameBoard(board);
			
			int cpuNum=TicTacToe.compPickPos();
			
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your character's placement from 1 to 9.");
			int given=scan.nextInt();
			System.out.println("Given position: "+given);
			
			TicTacToe.placePiece(board, given, "player");
			TicTacToe.placePiece(board, cpuNum, "cpu");
			
			TicTacToe.printGameBoard(board);
			
			String result=TicTacToe.checkWinner();
			if(!result.equals("")) {
				System.out.println(result);
				System.out.println("Remaining number of tires: "+tries);
				break;
			}
			tries--;
		}
	}

}
