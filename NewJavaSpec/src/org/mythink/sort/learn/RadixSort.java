package org.mythink.sort.learn;

import java.util.ArrayList;
import java.util.List;

public class RadixSort {

	/**
	 * 基本思想：将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。
	 * 然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后,
	 * 数列就变成一个有序序列。
	 * @param ary
	 */
	public static void radixSort(int[] ary){
		int max = ary[0];
		for(int i = 1;i<ary.length;i++){
			if(ary[i]>max){
				max=ary[i];
			}
		}
		int time=0;
		while(max>0){
			max/=10;
			time++;
		}
		
		List<ArrayList<Integer>> queue = new ArrayList<>();
		for(int i=0;i<10;i++){
			ArrayList<Integer> queue1 = new ArrayList<>();
			queue.add(queue1);
		}
		
		for(int i=0;i<time;i++){
			for(int j=0;j<ary.length;j++){
				int x = ary[j]%(int)Math.pow(10, i+1)/(int)Math.pow(10,i);
				ArrayList<Integer> queue2 = queue.get(x);
				queue2.add(ary[j]);
				queue.set(x, queue2);
			}
			int count=0;
			for(int k=0;k<10;k++){
				while(queue.get(k).size()>0){
					ArrayList<Integer> queue3 = queue.get(k);
					ary[count]=queue3.get(0);
					queue3.remove(0);
					count++;
				}
			}
		}
	}
	
	public static void main(String[] args){
		int[] a = {49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,101,56,17,18,23,34,15,35,25,53,51};
		radixSort(a);
		InserSort.printary(a);
		
	}
}
