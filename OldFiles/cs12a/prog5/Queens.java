//Queens.java
//Camille Rogers
//carogers
//pa5
//finds all solutions to the n-Queens problem, for 1<=n<=13.

class Queens {

	static void Usage() {

		System.err.println("Usage: Queens [-v] number" + "\n" + "Option: -v verbose output, print all solutions");
		System.exit(1);

	}

	public static void main(String[] args) {

		int n = 0;
		int num = 0;
		if (args.length == 1) {
			try {
				n = Integer.parseInt(args[0]);
			}

			catch (NumberFormatException e) {
				Usage();
			}

		}

		else if (args.length == 2) {
			if (!args[0].equals("-v")) {
				Usage();
			}

			try {
				n = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				Usage();
			}
			
		} 
		
		else {
			Usage();
		}

		int Q[] = new int[n + 1];
		for (int k = 0; k <= n; k++) {
			Q[k] = k;
		}
		int F = factorial(n);
		for (int i = 0; i < F; i++) {
			nextPermutation(Q);
			if (isSolution(Q)) {
				if (args.length == 2) {
					printArray(Q);
				}
				num++;
			}
		}
		System.out.println(n + "-Queens has " + num + " solutions");
	}

	static void nextPermutation(int[] A) {
		int pivot = 0;
		int successor = 0;
		// boolean checkPivot = false;
		for (int i = A.length - 1; i > 0; i--) {
			int rH = i - 1;

			if (A[i] > A[rH]) {
				pivot = rH;
				break;
			}
		}

		if (pivot == 0) {
			reverse(A, 0);
			return;
		}

		for (int f = A.length - 1; f > pivot; f--) {
			if (A[f] > A[pivot]) {
				successor = f;
				break;
			}
		}

		swap(A, pivot, successor);
		reverse(A, pivot);
		return;
	}

	static void swap(int[] Q, int i, int j) {
		int temp = Q[i];
		Q[i] = Q[j];
		Q[j] = temp;
	}

	static void reverse(int[] T, int k) {
		int j = T.length - 1;
		int h = k + 1;
		while (h < j) {
			swap(T, h, j);
			h++;
			j--;
		}
	}

	static void printArray(int[] P) {
		System.out.print("(");
		for (int i = 1; i < P.length; i++) {
			System.out.print(P[i]);
			if (i != P.length - 1) {
				System.out.print(",");
			}
		}
		System.out.println(")");
	}

	static boolean isSolution(int[] A) {
		for (int i = 1; i <= A.length - 2; i++) {
			for (int k = i + 1; k <= A.length - 1; k++) {
				if (Math.abs(i - k) == Math.abs(A[i] - A[k])) {
					return false;
				}
			}
		}
		return true;
	}

	static int factorial(int A) {
		int product = 1, i = 1;
		while (i <= A) {
			product *= i;
			i++;
		}
		return product;
	}

}
