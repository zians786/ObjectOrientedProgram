package com.bridgeit.oop.Utility;

import java.io.*;

public class LinkedListUtility<E> extends Node {

	Node start;
	Node end;
	int size;

	public LinkedListUtility() {
		start = null;
		end = null;
		size = 0;

	}

	public int size() {
		return size;
	}

	public <E> void displayList() {

		if (start == null) {
			System.out.println("List is Empty..");
		} else if (start.getLink() == null) {
			System.out.println(start.getData());
		} else {

			Node temp = start;
			System.out.print(temp.getData());

			while (temp.getLink() != null) {
				temp = temp.getLink();
				System.out.print(" -> " + temp.getData());
			}

		}

	}

	public void printToFile(String location) throws IOException {
		File file = new File(location);
		if (file.exists()) {
			PrintWriter printWriter = new PrintWriter(file);
			Node temp = start;
			printWriter.print(temp.getData());
			while (temp.getLink() != null) {
				temp = temp.getLink();
				printWriter.print("," + temp.getData());

			}
			printWriter.flush();
			printWriter.close();

		} else
			System.out.println("File not found");

	}

	public boolean isEmpty() {
		if (start == null) {
			return true;
		} else
			return false;
	}

	public <E> void insertAtEnd(E data) {
		Node nptr = new Node(null, data);
		size++;
		if (start == null) {

			start = nptr;
			end = start;

		} else {
			end.setLink(nptr);
			end = nptr;
		}
	}

	public <E extends Comparable<E>> int findPosition(E data) {
		boolean status = false;
		int pos = 1;
		/*
		 * if(start.getData().compareTo(data)>0){ return 1; } else
		 * if(end.getData()==data){ return size; } else{
		 */
		Node temp = start;

		while (temp.getLink() != null) {
			if (temp.getData().equals(data)) {
				status = true;
				break;
			}
			temp = temp.getLink();
			pos++;
		}
		if (temp.getData().equals(data)) {
			status = true;
		}

		if (status == true) {
			return pos;
		} else
			return -1;

	}

	public E dataAtPos(int pos) {
		E data = null;
		if (pos == 1) {
			data = (E) start.getData();

			return data;

		}

		else if (pos == size) {
			data = (E) end.getData();
			return data;
		}

		else {

			Node ptr = start.getLink();

			for (int i = 2; i < size; i++) {
				if (i == pos) {

					data = (E) ptr.getData();
					break;

				} else {
					ptr = ptr.getLink();
				}
			}

			return data;
		}
	}

	public E deleteAtPos(int pos) {
		if (pos == 1) {
			E data = (E) start.getData();
			start = start.getLink();
			size--;
			return data;

		}

		else if (pos == size) {
			E data = (E) end.getData();
			Node s = start;
			Node t = start;

			while (s != end)

			{
				t = s;
				s = s.getLink();
			}

			end = t;
			end.setLink(null);

			size--;

			return data;
		}

		else {

			Node ptr = start;
			pos = pos - 1;

			for (int i = 1; i < size - 1; i++) {
				if (i == pos) {

					Node tmp = ptr.getLink();
					E data = (E) tmp.getData();
					tmp = tmp.getLink();
					ptr.setLink(tmp);
					break;

				}

				ptr = ptr.getLink();

			}

			size--;
			return (E) data;
		}
	}

	public <E extends Comparable<E>> void insertInOrder(E value) {
		Node nptr = new Node(null, value);
		size++;
		if (value.compareTo((E) start.getData()) < 0) {
			nptr.setLink(start);
			start = nptr;
		} else if (value.compareTo((E) end.getData()) > 0) {
			end.setLink(nptr);
			end = nptr;
		} else {
			Node temp = start;
			Node pos = temp.getLink();
			while (temp.getLink() != null && ((E) pos.getData()).compareTo(value) < 0) {
				temp = temp.getLink();
				pos = pos.getLink();
			}
			temp.setLink(nptr);
			nptr.setLink(pos);

		}
	}

}
