package org.mythink.sort.learn;

public class QuickSort {

	/**
	 * 基本思想：选择一个基准元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，将待排序列分成两部分,
	 * 一部分比基准元素小,一部分大于等于基准元素,此时基准元素在其排好序后的正确位置,
	 * 然后再用同样的方法递归地排序划分的两部分。
	 * @param ary
	 */
	public static void quickSort(int[] ary){
		if(ary.length>0){
			_quick(ary,0,ary.length-1);
		}
	}
	
	private static void _quick(int[] ary,int low,int high){
		if(low<high){
			int middle = getMiddle(ary,low,high);
			_quick(ary,low,middle-1);
			_quick(ary,middle+1,high);
		}
		
	}
	
	private static int getMiddle(int[] ary,int low,int high){
		int temp= ary[low];
		while(low<high){
			while(low<high && ary[high]>=temp){
				high--;
			}
			ary[low]=ary[high];
			while(low<high && ary[low]<=temp){
				low++;
			}
			ary[high]=ary[low];
		}
		ary[low]=temp;
		
		return low;
	}
	
	public static void main(String[] args){
		int[] a = {49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
		quickSort(a);
		InserSort.printary(a);
	}
}
