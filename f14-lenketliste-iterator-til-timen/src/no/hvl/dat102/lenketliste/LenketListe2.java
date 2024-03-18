package no.hvl.dat102.lenketliste;

/**
 * A linked implemention of the ADT list.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 5.0
 * 
 *          Fikset litt av Lars-Petter Helland, februar 2024
 * 
 *          Ting jeg ALLTID fikser i bokens eksempler: I Java skal
 *          startparentes{ stå på slutten av linje! Alle blokker bør ha
 *          klammeparenteser{}, også hvis kun én linje! Alle metoder definert i
 *          interface eller superklasse skal ha @Override! Bruk kun kommentarer
 *          for ting som IKKE kan uttrykkes i kode! Alle andre kommentarer er
 *          boss.
 * 
 *          Ellers fikser jeg litt på kodestil og forenkler der jeg kan. Jeg har
 *          også tatt et valg om "stil" for feilhåndtering, vet at
 *          feilsituasjoner ryddes av veien (return/throw) FØR man går videre.
 * 
 *          Jeg har også gjort et designvalg om å bruke Node sin data og next
 *          direkte med =, i stedet for å bruke getNext() og setNext().
 * 
 */
public class LenketListe2<T> implements ListeADT<T> {

	/* ------------------------------------------------------------------- */

	private class Node {

		private T data;
		private Node next;

		private Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

	/* ------------------------------------------------------------------- */

	private Node firstNode; // Reference to first node of chain
	private Node lastNode;
	private int numberOfEntries;

	public LenketListe2() {

		initializeDataFields();

	}

	private void initializeDataFields() {

		this.firstNode = null;
		this.numberOfEntries = 0;

	}

	@Override
	public void clear() {

		initializeDataFields();

	}

	@Override
	public void add(T newEntry) {

		Node nyNode = new Node(newEntry);

		if (isEmpty()) {
			firstNode = nyNode;
		} else {
			Node sisteNode = getNodeAt(numberOfEntries);
			sisteNode.next = nyNode;
		}
		numberOfEntries++;

	}

	@Override
	public void add(int givenPosition, T newEntry) {

		if (givenPosition < 1 || givenPosition > numberOfEntries) {
			throw new IndexOutOfBoundsException("Utenfor rekkevidde");
		}

		Node nyNode = new Node(newEntry);

		if (givenPosition == 1) {
			nyNode.next = firstNode;
			firstNode = nyNode;
		} else {

			Node nodeFør = getNodeAt(numberOfEntries - 1);
			Node nodeEtter = getNodeAt(givenPosition + 1);

			nodeFør.next = nyNode;
			nyNode.next = nodeEtter;

		}

		numberOfEntries++;

	}

	@Override
	public T remove(int givenPosition) {
		
		if(givenPosition < 1 || givenPosition > numberOfEntries) {
			throw new IndexOutOfBoundsException("Utenfor rekkevidde");
		}
		
		T slettet = null;
		
	
		
		if(givenPosition == 1) {
			
			slettet = firstNode.data;
			firstNode = firstNode.next;
		}else {
			
		
			Node førSlett = getNodeAt(givenPosition-1);
			
			Node slettDenneNoden = førSlett.next;
			
			førSlett.next = slettDenneNoden.next;
			
			slettet = slettDenneNoden.data;
			
			
		}
		
		
		numberOfEntries--;
		return slettet;
		

	}

	@Override
	public T replace(int givenPosition, T newEntry) {

		if(givenPosition < 1 || givenPosition > numberOfEntries) {
			throw new IndexOutOfBoundsException("Utenfor rekkevidde");
		}
		
		
		Node nyNode = getNodeAt(givenPosition);
		T KlonaNode = nyNode.data;
		
		nyNode.data = newEntry;
		return KlonaNode;
		
		
	}

	@Override
	public T getEntry(int givenPosition) {
		
		if((givenPosition < 1) || (givenPosition > numberOfEntries)) {
			throw new IndexOutOfBoundsException("Utenfor rekkevidde");
		}

		return getNodeAt(givenPosition).data;
		
		
		
	}

	@Override
	public T[] toArray() {

		
		@SuppressWarnings("unchecked")
		T[] tab =(T[]) new Object[numberOfEntries];
		
		int index = 0;
		
		
		Node currentNode = firstNode;
		
		while((index<numberOfEntries) && (currentNode != null)) {
			tab[index] = currentNode.data;
			currentNode = currentNode.next;
			index ++;
		}
		
		return tab;
		
		
		
	}

	@Override
	public boolean contains(T anEntry) {

		Node currentNode = firstNode;
		
		
		while(currentNode != null) {
			if(anEntry.equals(currentNode.data)) {
				return true;
			}
			currentNode = currentNode.next;
		}
		
		return false;
	}

	/*
	 * Denne måtte jeg bare la stå siden den viser mange av tingene jeg fikser.
	 * Sammenlign gjerne med min forenklede/forbedrede versjon under. Ingen, verken
	 * studenter eller lærebokforfattere, bør skrive slik kode !!!
	 */
//	public boolean isEmpty() {
//		boolean result;
//
//		if (numberOfEntries == 0) // Or getLength() == 0
//		{
//			// Assertion: firstNode == null
//			result = true;
//		} else {
//			// Assertion: firstNode != null
//			result = false;
//		} // end if
//
//		return result;
//	} // end isEmpty

	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	/*
	 * Returns a reference to the node at a given position. Precondition: (The chain
	 * is not empty) and (1 <= givenPosition <= numberOfEntries).
	 */
	private Node getNodeAt(int givenPosition) {
		Node currentNode = firstNode;
		for (int counter = 1; counter < givenPosition; counter++) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}
}
