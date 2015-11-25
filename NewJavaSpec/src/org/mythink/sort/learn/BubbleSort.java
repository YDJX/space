package org.mythink.sort.learn;

public class BubbleSort {

	public static int[] bubbleSort(int[] ary){
		int num=0;
		for(int i=0;i<ary.length-1;i++){
			boolean flag = false;
			for(int j=1;j<ary.length;j++){
				if(ary[j-1]>ary[j]){
					flag=true;
					int temp = ary[j];
					ary[j]=ary[j-1];
					ary[j-1]=temp;
				}
			}
			if(!flag){
				break;
			}
			num++;
		}
		System.out.println("length:"+ary.length+" sort exec times:"+num);
		return ary;
	}
	
	public static void main(String[] args){
		int a[] ={320,58,41,75,43,57,35,9,93,24,53,56,135,32,475,73,735,95,68,35,23,75,96,87};
		bubbleSort(a);
		InserSort.printary(a);
		int[] b = {345,45,23,13};
		bubbleSort(b);
		InserSort.printary(b);
	}
}
