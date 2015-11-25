package org.mythink.sort.learn;

import java.util.Arrays;

public class MergingSort {

	/**
	 * 基本排序：归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有序表，
	 * 即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列。
	 * @param ary
	 */
	public static void mergingSort(int[] ary){
		if(ary.length<2){
			return;
		}
		sort(ary); //ary 不指向a了
	}
	
	public static int[] sort(int[] ary){
		if(ary.length>1){
			int center = ary.length/2;
			int[] leftary=new int[center];//{};
			int[] rightary=new int[ary.length-center];//new int[]{};
			System.arraycopy(ary, 0, leftary, 0, center);
//			System.out.println(Arrays.toString(leftary));
			System.arraycopy(ary, center, rightary, 0, ary.length-center);
			leftary = sort(leftary);
			rightary = sort(rightary);
			ary =merge(leftary,rightary);
		}
		return ary;
	}
	
	public static int[] merge(int[] leftary,int[] rightary){
		int[] tempary = new int[leftary.length+rightary.length];
		int leftIndex=0;
		int rightIndex=0;
		int index = 0;
		System.out.println("left:"+Arrays.toString(leftary)+",right:"+Arrays.toString(rightary));
		while(leftIndex<leftary.length && rightIndex<rightary.length){
			if(leftary[leftIndex]<=rightary[rightIndex]){
				tempary[index++]=leftary[leftIndex++];
			}else {
				tempary[index++]=rightary[rightIndex++];
			}
		}
		while(leftIndex<leftary.length){
			tempary[index++]=leftary[leftIndex++];
		}
		while(rightIndex<rightary.length){
			tempary[index++]=rightary[rightIndex++];
		}
//		while(temp<=right){
//			ary[temp]=tempary[temp++];
//		}
		System.out.println(Arrays.toString(tempary));
		return tempary;
	}
	
	public static void main(String[] args){
		int[] a = {49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
		mergingSort(a);
		InserSort.printary(a);
	}
}
