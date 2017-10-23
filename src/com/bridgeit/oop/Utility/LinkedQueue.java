package com.bridgeit.oop.Utility;

import java.util.NoSuchElementException;

class Node<E> {
	Node link;
	E data;

	public Node() {
		link = null;

	}

	public Node(Node n, E d) {
		link = n;
		data = d;
	}

	public void setLink(Node n) {
		link = n;
	}

	public void setData(E d) {
		data = d;
	}

	public Node getLink() {
		return link;
	}

	public E getData() {
		return data;
	}

	public boolean isEmpty() {
		return link == null;
	}
}

public class LinkedQueue<E>

{
	protected Node front, rear;

	public int size;

	public LinkedQueue()

	{

		front = null;

		rear = null;

		size = 0;

	}

	public boolean isEmpty()

	{
		return front == null;
	}

	public int getSize() {
		return size;
	}

	public void insert(E data) {
		Node nptr = new Node(null, data);

		if (rear == null) {
			front = nptr;
			rear = nptr;
		} else {
			rear.setLink(nptr);
			rear = rear.getLink();
		}
		size++;
	}

	public E remove() {
		if (isEmpty())

			throw new NoSuchElementException("Underflow Exception");

		Node ptr = front;

		front = ptr.getLink();

		if (front == null)

			rear = null;

		size--;

		return (E) ptr.getData();
	}

	public E peek() {
		if (isEmpty())

			throw new NoSuchElementException("Underflow Exception");

		return (E) front.getData();
	}

	public void display() {
		System.out.print("\nQueue = ");

		if (size == 0)

		{
			System.out.print("Empty\n");
			return;
		}

		Node ptr = front;

		while (ptr != rear.getLink())

		{
			System.out.print(ptr.getData() + " ");

			ptr = ptr.getLink();
		}

		System.out.println();

	}

}
