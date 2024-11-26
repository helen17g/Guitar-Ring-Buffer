import java.lang.Math;


public class GuitarHero {
	private static final String KEYBOARD = "q2we4r5ty7u8i9/op-[=zxdcfvgbnjmk,.;/' ";
	private static final int ININT_FREQ = 440;
	private static final double INCREMENT = 1.05956;
	private static final int START_POS = 24;

	public static void main(String[] args) {
		GuitarString[] sampleSet = setSample();
		Keyboard keyboard = new Keyboard();
		// runs while true so the entire time the program is running
		while (true) {
			if (keyboard.hasNextKeyPlayed()) {
				char key = keyboard.nextKeyPlayed();
				int pos = KEYBOARD.indexOf(key);
				if (pos != -1) {
					sampleSet[pos].pluck();
				}
			}
			double sample = 0.0;
			// goes from the start of the sampleSet array to the end adds up the value at
			// the first pointer position in the guitar string's ring buffer at every index
			// to the sample
			// variable
			for (int index = 0; index < sampleSet.length; index++) {
				sample += sampleSet[index].sample();
			}
			StdAudio.play(sample);
			// goes from the start of the sampleSet array to the end and tics the guitar
			// string at each index
			for (int index = 0; index < sampleSet.length; index++) {
				sampleSet[index].tic();
			}
		}

	}

	/**
	 * return GuitarString[] array method setSample, creates a new array of guitar
	 * strings the length of the keyboard the user can use. it sets the frequency of
	 * each new guitar string based of the position in the keyboard and the initial
	 * frequency. then returns the new sample set of guitar strings
	 * 
	 * @return GuitarString array full of the frequencies at each position of the
	 *         possible keys on the keyboard
	 */
	public static GuitarString[] setSample() {
		GuitarString[] sampleSet = new GuitarString[KEYBOARD.length()];
		// goes through every index of the sample set array creating a new guitar string
		// with a frequency based of the initial frequency times the increment to the
		// power of the index minus the start position
		for (int index = 0; index < sampleSet.length; index++) {
			sampleSet[index] = new GuitarString(ININT_FREQ * Math.pow(INCREMENT, (index - START_POS)));
		}
		return sampleSet;

	}

}
