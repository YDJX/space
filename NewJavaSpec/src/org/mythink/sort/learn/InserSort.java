package org.mythink.sort.learn;

public class InserSort {

	/**
	 * 基本思想：在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排好顺序的，
	 * 现在要把第n个数插到前面的有序数中，使得这n个数也是排好顺序的。
	 * 如此反复循环，直到全部排好顺序。
	 * @param ary
	 * @return
	 */
	public static int[] insertSort(int[] ary) {
		if (ary == null || ary.length < 2)
			return ary;
		int temp = 0;
		for (int i = 1; i < ary.length; i++) {
			int j = i - 1;
			temp = ary[i];
			for (; j >= 0 && temp < ary[j]; j--) {
				ary[j + 1] = ary[j];
			}
			ary[j + 1] = temp;
		}
		for (int i = 0; i < ary.length; i++) {
			System.out.printf("%2d  %d\n", i, ary[i]);
		}

		return ary;
	}

	public static void printary(int[] ary) {
		int i = 0;
		for (int a : ary) {
			System.out.println(i++ + " -:- " + a);
		}
	}

	public static void main(String[] args) {
		int[] ary = { 57, 234, 879, 2, 34, 65, 1233, 79, 329, 24, 32, 42, 26, 53 };
		int[] res = insertSort(ary);
		printary(res);

	}
}
