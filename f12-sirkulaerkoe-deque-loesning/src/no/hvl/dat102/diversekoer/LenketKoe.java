package no.hvl.dat102.diversekoer;

import no.hvl.dat102.adter.EmptyQueueException;
import no.hvl.dat102.adter.KoeADT;

/**
 * A class that implements a queue of objects by using a chain of linked nodes
 * that has both head and tail references.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 5.0
 * 
 * Fikset littegrann av Lars-Petter Helland, februar 2024
 */

public final class LenketKoe<Datatype> implements KoeADT<Datatype> {

	/* -------------------------------------------------------------- */

	private class Node {
		private Datatype innhold;
		private Node peker;

		private Node(Datatype data) {
			this.innhold = data;
			this.peker = null;
		}
	}

	/* -------------------------------------------------------------- */

	private Node foran; // References node at front of queue
	private Node bak; // References node at back of queue

	public LenketKoe() {
		foran = null;
		bak = null;
	}

	@Override
	public void enqueue(Datatype newEntry) {

		Node nyNode = new Node(newEntry);

		if (isEmpty()) {
			foran = nyNode;
		} else {
			bak.peker = nyNode;
		}
		bak = nyNode;
	}

	@Override
	public Datatype getFront() {

		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		return foran.innhold;
	}

	@Override
	public Datatype dequeue() {
		Datatype front = getFront(); // Might throw EmptyQueueException
							  // Assertion: firstNode != null
		foran.innhold = null;
		foran = foran.peker;

		if (foran == null) {
			bak = null;
		}
		return front;
	}

	@Override
	public boolean isEmpty() {
		return (foran == null) && (bak == null);
	}

	@Override
	public void clear() {
		foran = null;
		bak = null;
	}
}
