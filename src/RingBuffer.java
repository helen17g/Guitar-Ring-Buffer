import java.util.*;



public class RingBuffer {
	private double[] buffer;
	private int capacity;
	private int size;
	private int first;
	private int last;

	/**
	 * this is the constructor so it sets up the ring buffer
	 * 
	 * @param int capacity is the size of the buffer array so how many values we can
	 *            have at a time
	 */
	public RingBuffer(int capacity) {
		this.capacity = capacity;
		first = 0;
		last = 0;
		size = 0;
		buffer = new double[this.capacity];
	}

	/**
	 * return integer method size returns the size of the buffer
	 * 
	 * @return integer of size of the buffer
	 */
	public int size() {
		return size;
	}

	/**
	 * return boolean method isEmpty will return true when the size of the buffer is
	 * 0 so the buffer is empty and false when the buffer is not empty
	 * 
	 * @return boolean weather or not the buffer is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * return boolean method isFull will return true when the size of the buffer is
	 * equal to the capacity so the buffer is full and false when it is not full
	 * 
	 * @return boolean weather or not the buffer is full
	 */
	public boolean isFull() {
		return size == capacity;
	}

	/**
	 * void method enqueue takes adds a new double to the buffer array first
	 * checking if the array is full and throwing an error if it is. then it will
	 * make the last space in the buffer the inputed double and increase size and
	 * the pointer of last
	 * 
	 * @param double x this is the value to be enqueue to the buffer
	 */
	public void enqueue(double x) {
		if (isFull()) {
			throw new IllegalStateException("Cannot call enqueue on a full RingBuffer.");
		}
		buffer[last] = x;
		last++;
		last %= capacity;
		size++;

	}

	/**
	 * return double method dequeue well first check if the buffer is empty and if
	 * it is throw an error message, then if it is not empty it will take the value
	 * at the first pointer position out of the buffer and return that double then
	 * decrement size and increment the position of first
	 * 
	 * @return double at the first value of buffer which has been removed
	 */
	public double dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Cannot call dequeue on an empty RingBuffer.");
		}
		double temp = buffer[first];
		first++;
		first %= capacity;
		size--;
		return temp;
	}

	/**
	 * return double method peek well first check if the buffer is empty and if it
	 * is throw an error, then if it is not empty it well return the first value in
	 * the buffer
	 * 
	 * @return double at the first value in the buffer
	 */
	public double peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Cannot call peek on an empty RingBuffer.");
		}
		return buffer[first];
	}

	/**
	 * return String method toString will return the string value of the buffer
	 * returning the value at the first pointer position through to the last pointer
	 * position
	 * 
	 * @return the String of the buffer
	 */
	public String toString() {
		String output = "[";
		if (!isEmpty()) {
			if (first < last) {
				// goes from the first position in buffer array to last adding the appropriate
				// double values to the string to be printed out
				for (int i = first; i < last; i++) {
					output += buffer[i] + ", ";
				}
			} else {
				// goes from first to the end of the buffer array adding the appropriate double
				// values to the string to be printed out
				for (int i = first; i < capacity; i++) {
					output += buffer[i] + ", ";
				}
				// goes from the beginning of the buffer array to the last pointer adding the
				// appropriate doubel values to the string to be printed out
				for (int i = 0; i < last; i++) {
					output += buffer[i] + ", ";

				}

			}
			output = output.substring(0, output.length() - 2);
		}
		output += "]";

		return output;
	}

}
