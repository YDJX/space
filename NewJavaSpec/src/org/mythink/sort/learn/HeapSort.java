package org.mythink.sort.learn;

import java.util.Arrays;

public class HeapSort {

	/**
	 * 基本思想：堆排序是一种树形选择排序，是对直接选择排序的有效改进。
	 * 堆的定义如下：具有n个元素的序列（h1,h2,…,hn),当且仅当满足（hi>=h2i,hi>=2i+1）
	 * 或（hi<=h2i,hi<=2i+1）(i=1,2,…,n/2)时称之为堆。
	 * @param ary
	 * @return
	 */
	public static int[] heapSort(int[] ary){
		System.out.println(" start sort...");
		int arylen = ary.length;
		for(int i=0;i<arylen-1;i++){
			buildMaxHeap(ary,arylen-1-i);
			swap(ary,0,arylen-1-i);
			System.out.println(Arrays.toString(ary));
		}
		
		return null;
	}
	
	public static void buildMaxHeap(int[] ary,int lastIndex){
		for(int i=(lastIndex-1)/2;i>=0;i--){
			int k=i;
			while(k*2+1<=lastIndex){
				int biggerIndex = 2*k+1;
				if(biggerIndex<lastIndex){
					if(ary[biggerIndex]<ary[biggerIndex+1]){
						biggerIndex++;
					}
				}
				if(ary[k]<ary[biggerIndex]){
					swap(ary,k,biggerIndex);
				}else {
					break;
				}
			}
		}
	}
	
	public static void swap(int[] ary,int a,int b){
		int temp = ary[a];
		ary[a]=ary[b];
		ary[b]=temp;
	}
	
	public static void main(String[] args){
		int[] a = {23,546,647,32,452,65,47,78,78,67,85,35,354,68,57,83,53,56,323,5,473,54,2356,25};
		heapSort(a);
	}
}
