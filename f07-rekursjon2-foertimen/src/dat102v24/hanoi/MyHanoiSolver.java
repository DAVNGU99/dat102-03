package dat102v24.hanoi;

import java.util.function.BiConsumer;

public class MyHanoiSolver implements HanoiSolver {

	@Override
	public void solveHanoi(BiConsumer<Integer, Integer> diskMover, 
			int antallDisker, int from, int dest, int by) {

		
	/**
	 * Vi flytter antallDisker-1 disker from til by
	 * Flytt en disk fra from til dest
	 * Rekursivt flytt n-1 disker fra by til dest
	 */
		
		
		
	
	/**
	 * Kjøretidsanaylse
	 * 
	 * Eksponetiell vekst
	 * 
	 * m(n) = O(2^N)
	 */
		
		if(antallDisker>0) {
			
			solveHanoi(diskMover,antallDisker-1,  from,  by,dest);
			diskMover.accept(from, dest);
			solveHanoi(diskMover,antallDisker-1, by,  dest,from);
		}
		
	}
}