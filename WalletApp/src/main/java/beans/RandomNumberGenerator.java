package beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomNumberGenerator {

	private String fileName;
	private File f;
	private Set<Long> used;
	private long randNum;

	public long generateRandom(int length) {

		fileName = "Random.txt";
		f = new File(fileName);
		used = new HashSet<Long>();

		if (f.exists()) {
			try (FileInputStream fin = new FileInputStream(f); ObjectInputStream in = new ObjectInputStream(fin)) {
				Object obj = in.readObject();
				used = (Set<Long>) obj;

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			randNum = generateRandomNumber(length);
			used.add(randNum);
			try (FileOutputStream fout = new FileOutputStream(fileName);
					ObjectOutputStream out = new ObjectOutputStream(fout)) {
				out.writeObject(used);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			used = new HashSet<Long>();
			randNum = generateRandomNumber(length);
			used.add(randNum);
			try (FileOutputStream fout = new FileOutputStream(fileName);
					ObjectOutputStream out = new ObjectOutputStream(fout)) {
				out.writeObject(used);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return randNum;
	}

	public long generateRandomNumber(int length) {

		Random random = new Random();
		long newRandom;
		do {
			char[] digits = new char[length];
			digits[0] = (char) (random.nextInt(9) + '1');
			for (int i = 1; i < length; i++) {
				digits[i] = (char) (random.nextInt(10) + '0');
			}
			newRandom = Long.parseLong(new String(digits));
		} while (used.contains(newRandom));

		used.add(newRandom);
		return newRandom;
	}
}
