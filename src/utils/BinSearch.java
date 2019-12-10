package utils;

public class BinSearch {
	boolean test(long x) {
		return true;
	}

	/**
	 * FFFFFFFFFFTTTTTTTTTTT
	 *           ^
	 *           |
	 * Returns the first test true.
	 */
	long bs(long a, long b) {
		while (a != b) {
			long c = (a + b) / 2;
			if (test(c)) {
				b = c;
			} else {
				a = c + 1;
			}
		}
		return a;
	}
}
