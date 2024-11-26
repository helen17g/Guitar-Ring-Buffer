import java.lang.Math;
import java.util.Arrays;


public class GuitarString {
	private static final int SAMPLING_RATE = 44100;
	private RingBuffer rb;
	private static final double DECAY_FACTOR = 0.994;
	private int tics = 0;
	private int capacity;
	private static final double RANDOM_RANGE = 0.5;

	/**
	 * the constructor that runs when the class is called with a double constructor
	 * sets the capacity of the ring buffer to be created based of the sampling rate
	 * divided by double frequency and fills the new ring buffer with zeros with the
	 * enqueue method
	 * 
	 * @param double frequency - the value to determine the capacity of the new ring
	 *               buffer
	 */
	public GuitarString(double frequency) {
		capacity = (int) (SAMPLING_RATE / frequency);
		rb = new RingBuffer(capacity);
		// goes from the first index to last of the new ring buffer and fills the whole
		// buffer with zeros enqueue method
		for (int index = 0; index < capacity; index++) {
			rb.enqueue(0.0);
		}

	}

	/**
	 * the constructor for guitar string class that runs when the constructor is
	 * called with constructor double array. this will set the capacity of the ring
	 * buffer to be created to the length of the double array that is inputed then
	 * fill the ring buffer array with zeros with the enqueue method
	 * 
	 * @param double[] init used to get the capacity of the ring buffer to be
	 *                 created
	 */
	public GuitarString(double[] init) {
		capacity = init.length;
		rb = new RingBuffer(capacity);
		// goes from the first index to last of the new ring buffer and fills the whole
		// buffer with zeros with the enqueue method
		for (int index = 0; index < init.length; index++) {
			rb.enqueue(init[index]);
		}

	}

	/**
	 * void method pluck fills the ring buffer with white noise by dequeuing the
	 * current ring buffer and filling it with random values
	 */
	public void pluck() {
		// goes from index 0 to the end of the ring buffer array removing current values
		// and replacing them with random ones
		for (int i = 0; i < capacity; i++) {
			rb.dequeue();
			double randomValue = (Math.random() - RANDOM_RANGE) * 1.0;
			rb.enqueue(randomValue);
		}
	}

	/**
	 * void method tic removes the current value in the first position of the ring
	 * buffer and averages with the new front value multiplying that by the decay
	 * factor and adds this to the last position of the ring buffer. then
	 * increment the amount of times tic-ed
	 */
	public void tic() {
		double frontSample = rb.dequeue();
		double newFront = rb.peek();
		double newSample = ((frontSample + newFront) / 2 * DECAY_FACTOR);
		rb.enqueue(newSample);
		tics++;
	}

	/**
	 * return double method sample returns the value at the first pointer position
	 * of the ring buffer
	 * 
	 * @return double the value at the first pointer position from the ring buffer
	 */
	public double sample() {
		return rb.peek();
	}

	/**
	 * return integer method time returns the amount of time the ring buffer has been ticked 
	 * @return int tics the amount of times the ring buffer has been tic-ed
	 */
	public int time() {
		return tics;
	}

}
