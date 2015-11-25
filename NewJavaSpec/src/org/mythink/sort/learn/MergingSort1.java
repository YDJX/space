package org.mythink.sort.learn;

import java.util.Arrays;

public class MergingSort1 {

	public static void mergingSort(int[] ary){
		sort(ary,0,ary.length);
	}
	
	public static void sort(int[] ary,int left,int right){
		if(left+1<right){
			int center = (left+right)/2;
			
			sort(ary,left,center);
			sort(ary,center,right);
			System.out.println("***** left:"+left+",center:"+center+",right:"+right);
			merge(ary,left,center,right);
		}
	}
	
	public static void merge(int[] ary,int left,int mid, int right){
		int temp[] = new int[right-left];
		int start = left;
		int index = 0;
		int lindex = left;
		int rindex = mid;
		System.out.println("left:"+left+",mid:"+mid+",right:"+right);
		while(lindex<mid && rindex<right){
			if(ary[lindex]<=ary[rindex]){
				temp[index++]=ary[lindex++];
				System.out.println("lindx  ===left:"+left+",mid:"+mid+",right:"+right);
			} else {
				temp[index++]=ary[rindex++];
			}
		}
		while(lindex<mid){
			temp[index++]=ary[lindex++];
		}
		while(rindex<right){
			temp[index++]=ary[rindex++];
		}
		int i=0;
		while(start<right){
			ary[start++]=temp[i++];
		}
		System.out.println(Arrays.toString(ary));
	}
	
	public static void main(String[] args){
		int[] a = {35,43,25,40,74,35,93,38,45};
		mergingSort(a);
		InserSort.printary(a);
	}
}
