package org.mythink.sort.learn;

public class ShellSort {

	/**
	 * 基本思想：算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，
	 * 每组中记录的下标相差d.对每组中全部元素进行直接插入排序，然后再用一个较小的增量（d/2）对它进行分组，
	 * 在每组中再进行直接插入排序。当增量减到1时，进行直接插入排序后，排序完成
	 * @param ary
	 * @return
	 */
	public static int[] shellSort(int[] ary){
		double d1 = ary.length;
		int temp = 0;
		while(true){
			d1 = Math.ceil(d1/2);
			int d = (int)d1;
			for(int x=0;x<d;x++){
				for(int i=x+d;i<ary.length;i+=d){
					int j=i-d;
					temp = ary[i];
					for(;j>=0 && temp<ary[j];j-=d){
						ary[j+d]=ary[j];
					}
					ary[j+d]=temp;
				}
			}
			if(d==1){
				break;
			}
			
		}
		
		for (int i = 0; i < ary.length; i++) {
			System.out.printf("%2d  %d\n", i, ary[i]);
		}

		return ary;
		
	}
	
	public static void main(String[] args){
		int[] a = {345,23,657,56,67,3454,454,234,5667,682,52343,42,633,1234,3,67,23,8,45};
		int[] res = shellSort(a);
		InserSort.printary(a);
	}
}
