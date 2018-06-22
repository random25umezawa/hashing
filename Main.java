import java.util.*;

class Main {
	static List<Integer> primes = new ArrayList<>();

	//constants
	static final int[] SQUARES = new int[]{
		0x6a09e667,
		0xbb67ae85,
		0x3c6ef372,
		0xa54ff53a,
		0x510e527f,
		0x9b05688c,
		0x1f83d9ab,
		0x5be0cd19
	};
	static final int[] CUBES = new int[]{
		0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
		0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
		0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
		0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
		0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
		0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
		0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
		0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
	};

	public static void main(String[] args) {
		//calc squares,cubes
		//hard coding because of fixed value
		/*
		{
			//calc primes
			{
				int i = 2;
				while(prime.size()<64) {
					boolean flag = false;
					for(int prime : primes) flag |= i%prime==0;
					if(!flag) primes.add(i);
				}
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
		*/

		List<Integer> arr = new ArrayList<Integer>(
			Arrays.asList(
				0x00001111,
				0x22223333,
				0x44445555,
				0x66667777,
				0x88889999,
				0xaaaabbbb,
				0xccccdddd,
				0xeeeeffff,
				0x00112233,
				0x44556677/*,
				0x00000000,
				0x00000000,
				0x00000000,
				0x00000000
				*/
			)
		);

		padding(arr,arr.size()*32);

		System.out.println(arr+" "+arr.size());
	}

	//arr: 32bit arr,	len: bit length
	//padding bit arr to number mod 512 = 0
	//|bitarr+'1'+'0'*k| mod 512 = 448, add more 64bit |bitarr|
	static void padding(List<Integer> arr, long longLen) {
		int len = (int)(longLen&0xffffffffL);
		int bit1Index = len/32;
		int bit1Pos = (32-len%32-1);
		if(arr.size()==bit1Index) arr.add(0);
		arr.set(bit1Index,arr.get(bit1Index)|(1<<bit1Pos));
		int padLength = 448-(len+1);
		padLength = (padLength+512)%512;
		for(int i = 0; i < (padLength-1)/32; i++) {
			arr.add(0);
		}
		arr.add((int)(longLen&0xffffffff00000000L));
		arr.add((int)(longLen&0x00000000ffffffffL));
	}

	//get first 32bit of fractional part
	//convert d to a number not exceeding 2 (for adjustment of the advance in binary number?)
	static int fractional(double d) {
		int n = (int)d;
		d -= (n-1);
		long bits = Double.doubleToLongBits(d);
		return (int)((bits&0x000fffffffffffffL)>>20);
	}

}
