import java.util.*;

class Main {
	static List<Integer> primes = new ArrayList<>();

	public static void main(String[] args) {
		for(int i = 2; i < 312; i++) {
			boolean flag = false;
			for(int prime : primes) flag |= i%prime==0;
			if(!flag) primes.add(i);
		}
		System.out.println(primes);
		for(int i = 0; i < 8; i++) {
			System.out.println(Integer.toHexString(fractional(Math.sqrt(primes.get(i)))));
		}
		System.out.println();
		for(int i = 0; i < 64; i++) {
			System.out.println(Integer.toHexString(fractional(Math.cbrt(primes.get(i)))));
		}
	}

	static String charToBinaryString(char _c) {
		int c = (int)_c;
		String ret = "";
		for(int i = 8-1; i >= 0; i--) {
			ret += ((c&(1<<i))!=0)?'1':'0';
		}
		return ret;
	}

	static int fractional(double d) {
		long bits = Double.doubleToLongBits(d);
		return (int)((bits&0x000fffffffffffffL)>>20);
	}
}
