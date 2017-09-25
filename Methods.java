
public class Methods {

	public static boolean compareABC(int A, int B, int C) {
		return (A < B) && (B < C);

	}

	public static double doubleFactorial(double n) {
	
		if (n < 0)
			throw new IllegalArgumentException("n>0 required");
		double res = 1;
		double cur_el = n;
		if ((n % 2) == 0) {
	
			while (cur_el >= 2) {
				res *= cur_el;
				cur_el -= 2;
			}

		} else {
			
			while (cur_el >= 1) {
				res *= cur_el;
				cur_el -= 2;
			}
		}
		return res;
	}

	public static int max(int number1, int number2) {

		return number1 > number2 ? number1 : number2;

	}

	public static double arythmMeanFromKToL(double array[], int k, int l) {
		double sum = 0;
		int am = 0;

		double arithmMean = 0.;
		for (int i = 0; i < array.length; i++) {

			if (i < k || i > l) // not including those elements with indexes k and l
			{
				sum += array[i];
				am++;
			}
		}
		return  (double) sum / am;
		
	}

	// ========================Fibonacci=====================

	public static int[] fibonacci(int n) {
		int[] result = new int[n];

		if (n == 1) {
			result[0] = 1;
			return result;
		}
		result[0] = 1;
		result[1] = 1;
		int n2;

		for (int i = 2; i < n; i++) {
			n2 = result[i - 2] + result[i - 1];
			result[i] = result[i - 2] + result[i - 1];

		}
		return result;
	}
	// =================end of Fibonacci =======================

	public static int[][] swapColoumnsWithMinAndMax(int array[][], int n, int m) {
		if (n < 1 || m < 1)
			throw new IllegalArgumentException("n>1, m>1 required");
		int minI, minJ, maxI, maxJ, temp;
		int min = array[0][0];
		minI = minJ = maxI = maxJ = 0;
		int max = array[0][0];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (array[i][j] < min) {
					min = array[i][j];
					minI = i;
					minJ = j;
				}
				if (array[i][j] > max) {
					max = array[i][j];
					maxI = i;
					maxJ = j;
				}
			}
		}

		if (minJ == maxJ)
			return array;
		else {
			for (int i = 0; i < n; i++) {
				temp = array[i][maxJ];
				array[i][maxJ] = array[i][minJ];
				array[i][minJ] = temp;
			}
			return array;
		}

	}

	
	public static double toMetres(int lenNum, double len) {
		
		if (lenNum < 1 || lenNum > 5)
			throw new IllegalArgumentException("1<lenNum<5 required");
		double lenMetre;
		lenMetre = len;
		switch (lenNum) {
		case 1:
			lenMetre = len * 0.1;
			break;
		case 2:
			lenMetre = len * 1000;
			break;
		case 3:
			lenMetre = len;
			break;
		case 4:
			lenMetre = len * 0.001;
			break;
		case 5:
			lenMetre = len * 0.01;
			break;
		default:
			lenMetre = -1;
		}
		return lenMetre;
	}

	public Paralelepiped parSquareVolume(int a, int b, int c) {
		int square = 2 * (a * b + b * c + a * c);
		int volume = a * b * c;
		return new Paralelepiped(square, volume);
	}

	public ModDiv splitNumber(int userNumber) {
		int first = (int) userNumber / 10;
		int second = (int) userNumber % 10;
		return new ModDiv(first, second);
	}

	public static void main(String[] args) throws java.lang.Exception {
		System.out.println("ulala:)");
		
		
	}

}
