//OODAss1.java
//aka GameWord.java
//Priyonto Saha
//A class that is designed to be used to play a game of Scrabble.
//Top left of board is coord 1,1
import java.util.*;
class GameWord{
	private String contents;//The important variable
	private String DL="DL";
	private String TL="TL";
	private String DW="DW";
	private String TW="TW";
	private String XX="XX";
	public static final int RIGHT=1;
	public static final int DOWN=2;
	private static int[] sheet={1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};//How much each letter is worth
	private static ArrayList<String>perms=new ArrayList<String>();//Variable to store permutations
	
	private String[][]board={{TW,XX,XX,DL,XX,XX,XX,TW,XX,XX,XX,DL,XX,XX,TW},
						   	 {XX,DW,XX,XX,XX,TL,XX,XX,XX,TL,XX,XX,XX,DW,XX},
						   	 {XX,XX,DW,XX,XX,XX,DL,XX,DL,XX,XX,XX,DW,XX,XX},
						     {DL,XX,XX,DW,XX,XX,XX,DL,XX,XX,XX,DW,XX,XX,DL},
							 {XX,XX,XX,XX,DW,XX,XX,XX,XX,XX,DW,XX,XX,XX,XX},
							 {XX,TL,XX,XX,XX,TL,XX,XX,XX,TL,XX,XX,XX,TL,XX},
							 {XX,XX,DL,XX,XX,XX,DL,XX,DL,XX,XX,XX,DL,XX,XX},
							 {TW,XX,XX,DL,XX,XX,XX,DW,XX,XX,XX,DL,XX,XX,TW},
							 {XX,XX,DL,XX,XX,XX,DL,XX,DL,XX,XX,XX,DL,XX,XX},
							 {XX,TL,XX,XX,XX,TL,XX,XX,XX,TL,XX,XX,XX,TL,XX},
							 {XX,XX,XX,XX,DW,XX,XX,XX,XX,XX,DW,XX,XX,XX,XX},
							 {DL,XX,XX,DW,XX,XX,XX,DL,XX,XX,XX,DW,XX,XX,DL},
							 {XX,XX,DW,XX,XX,XX,DL,XX,DL,XX,XX,XX,DW,XX,XX},
							 {XX,DW,XX,XX,XX,TL,XX,XX,XX,TL,XX,XX,XX,DW,XX},
							 {TW,XX,XX,DL,XX,XX,XX,TW,XX,XX,XX,DL,XX,XX,TW}};
	
	public GameWord(String contents){
		this.contents=contents.toUpperCase();//Makes all characters uppercase so there is no confusion
	}
	
	public String reverse(){
		String newstring="";
		for (int i=contents.length()-1; i>=0; i--) {
        	newstring=newstring+contents.charAt(i);
    	}
    	return newstring;
	}
	
	public boolean anagram(String otherWord){//makes lists, sorts them, checks if they are equal
		otherWord=otherWord.toUpperCase();
		char[]lets=contents.toCharArray();
		char[]otherLets=otherWord.toCharArray();
		Arrays.sort(lets);
		Arrays.sort(otherLets);
		return Arrays.equals(lets,otherLets);
	}
	
	public boolean anagram(GameWord otherWord){//Overload version of anagram that takes a GameWord.
		return anagram(otherWord.toString());	
	}
	
	public ArrayList<String> permutations(){//the front for the real deal, perm.
		perms=new ArrayList<String>();
		perm("",contents);
		return perms;
	}
	
	private static void perm(String St1,String St2){//This does all the hard work, while permutations takes the credit
		if(St2.equals("")){							//Recursive method to find permutations of your GameWord
			perms.add(St1);
		}
		else{
			for (int i=0;i<St2.length();i++){
				char ch=St2.charAt(i);
				String newSt2= St2.substring(0,i)+St2.substring(i+1);
				perm(St1+ch,newSt2);
			}
		}
	}
	
	public int pointValue(int x,int y,int direction){
		boolean valid=true;
		int points=0;
		int multiplier=1;//At end we multiply points by multiplier to give the points you deserve
		if (x<1||x>15||y<1||y>15){//If start is off the board
			valid=false;
		}
		if (valid){
			if (direction==RIGHT){
				if (x+contents.length()>16){//If the end of the word would go off the board
					valid=false;
				}
			}
			if (direction==DOWN){
				if (y+contents.length()>16){//If the end of the word would go off the board
					valid=false;
				}
			}
		}
		x=x-1;//Making coords easier for 2d lists
		y=y-1;
		if (valid){
			if (direction==RIGHT){
				for (int i=0;i<contents.length();i++){
					if ((board[y][x+i]).charAt(1)=='W'){
						if ((board[y][x+i]).charAt(0)=='D'){//If DoubleWord
							multiplier*=2;
						}
						if ((board[y][x+i]).charAt(0)=='T'){//If TripleWord
							multiplier*=3;
						}
						points+=sheet[contents.charAt(i)-65];//Adds the normal points of the letter that landed on a Word multiplier
					}
					else if ((board[y][x+i]).charAt(1)=='L'){
						if ((board[y][x+i]).charAt(0)=='D'){//If DoubleLetter
							points+=(sheet[contents.charAt(i)-65])*2;	
						}
						else if ((board[y][x+i]).charAt(0)=='T'){//If TripleLetter
							points+=(sheet[contents.charAt(i)-65])*3;
						}
					}
					else{
						points+=sheet[contents.charAt(i)-65];//Adds the normal points of the letter if landed on a normal square
					}
				}
			}
			if (direction==DOWN){
				for (int i=0;i<contents.length();i++){
					if ((board[y+i][x]).charAt(1)=='W'){
						if ((board[y+i][x]).charAt(0)=='D'){//If DoubleWord
							multiplier*=2;
						}
						if ((board[y+i][x]).charAt(0)=='T'){//If TripleWord
							multiplier*=3;
						}
						points+=sheet[contents.charAt(i)-65];//Adds the normal points of the letter that landed on a Word multiplier
					}
					else if ((board[y+i][x]).charAt(1)=='L'){
						if ((board[y+i][x]).charAt(0)=='D'){//If DoubleLetter
							points+=(sheet[contents.charAt(i)-65])*2;
						}
						else if ((board[y+i][x]).charAt(0)=='T'){//If TripleLetter
							points+=(sheet[contents.charAt(i)-65])*3;
						}
					}
					else{
						points+=sheet[contents.charAt(i)-65];//Adds the normal points of the letter if landed on a normal square
					}
				}
			}
		}
		if (valid){
			return points*multiplier;
		}
		else{
			return -1;//Returning a -1 means that you lose points for trying to make a move that doesn't work
		}
	}
	
	public String toString(){
		return contents;
	}
}
	