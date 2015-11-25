package org.mythink.sort.learn;

public class SelectSort {

	/**
	 * 基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
	 * 然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
	 * @param ary
	 * @return
	 */
	public static int[] selectSort(int[] ary){
		for(int x=0;x<ary.length-1;x++){
			int temp = x;
			
			for(int i=x+1;i<ary.length;i++){
				if(ary[temp]>ary[i]){
					temp=i;
				}
			}
			System.out.printf("swap  %2d   -   %2d\n",x,temp);
			int t = ary[x];
			ary[x]=ary[temp];
			ary[temp]=t;
//			swap(ary[temp],ary[x]);
		}
		return ary;
	}
	
	public static void swap(int a,int b){
		int temp = a;
		a=b;
		b=temp;
	}
	
	public static void main(String[] args){
		int[] a = {234,435,457,234,657,3474,214,67467,467235,2,235,546546,56,74};
		selectSort(a);
		InserSort.printary(a);
	}
}
