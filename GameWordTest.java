//GameWordTest.java

public class GameWordTest{
	public static void main(String[]args){
		GameWord man = new GameWord("TRFA");
		GameWord nam = new GameWord("TRAF");
		//System.out.println(man.reverse());
		System.out.println(man.anagram(nam));
		//System.out.println(man.permutations());
		//System.out.println(man.pointValue(1,1,GameWord.DOWN));
	}
}