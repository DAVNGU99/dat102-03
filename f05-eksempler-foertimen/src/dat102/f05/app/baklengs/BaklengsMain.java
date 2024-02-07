package dat102.f05.app.baklengs;

import dat102.f05.stabel.StabelADT;
import dat102.f05.stabel.tabellstabel.TabellStabel;

public class BaklengsMain {
	
	public static void main(String[] args) {
		
		String[] tabell = {"Anne", "Per", "Lise", "Knut"};
		
	
		StabelADT<String> stabelen = new TabellStabel<>();
		
		for(String s : tabell) {
			stabelen.push(s);
		}
		
		//for (int i = 0; i < tabell.length; i++) {
		//	stabelen.push(tabell[i]);
		//}
		
		while(!stabelen.isEmpty()) {
			String s = stabelen.pop();
			System.out.println(s);
		}
		
	}
	
}