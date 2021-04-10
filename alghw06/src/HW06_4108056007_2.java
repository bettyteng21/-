public class HW06_4108056007_2 extends Dessert_Desert
{
	public static void main(String[] args){
		HW06_4108056007_2 test= new HW06_4108056007_2();
		int[][] inputArr={{2,2,1,1,1,1,1}, {1,1,1,1,1,1,1}, {1,3,5,7,9},{1,2,3}, {5,4,3,2,1},{2,1,3,2},{6,1,5,8,3,7,9},{7,1,2,4,6,5,3},{2,3,1,5,4}};
		int[] answer= test.maxBlocks(inputArr);
		for(int i=0; i<inputArr.length;++i){
			System.out.println(answer[i]);
		}
	}

	public class Data{
		int index, value;
		public Data(int index, int value){
			this.index= index; this.value= value;
		}
	}

	public int[] maxBlocks(int[][] inputArr){
		int[] ans= new int[inputArr.length];
		int currMax=0, currMaxIndex=0, j, k, i;
		for (i=0; i< inputArr.length; ++i, currMax=0, currMaxIndex=0){
			Data[] arr= new Data[inputArr[i].length];
			ans[i]=0;
			for (j=0; j<inputArr[i].length; ++j){
				arr[j]= new Data(j, inputArr[i][j]);
			}
			sort(arr, 0, arr.length-1);
			for (j=0; j<inputArr[i].length; ++j){
				if(currMax<= inputArr[i][j]){
					currMax= inputArr[i][j];
					currMaxIndex= j;
				}
				for (k=0; k<=j; ++k){
					if(arr[k].value==currMax && currMaxIndex==arr[k].index){
						ans[i]++; break;
					}
				}
			}
		}
		return ans;
	}

	void merge(Data[] arr, int l, int m, int r)
	{
		int n1 = m - l + 1, n2 = r - m;
		Data[] L = new Data[n1];
		Data[] R = new Data[n2];

		for (int i = 0; i < n1; ++i) L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j) R[j] = arr[m + 1 + j];

		int i = 0, j = 0, k = l;
		while (i < n1 && j < n2) {
			if (L[i].value <= R[j].value) {
				arr[k] = L[i]; i++;
			}
			else {
				arr[k] = R[j]; j++;
			}
			k++;
		}
		while (i < n1) {
			arr[k] = L[i]; i++; k++;
		}
		while (j < n2) {
			arr[k] = R[j]; j++; k++;
		}
	}

	void sort(Data[] arr, int l, int r)
	{
		if (l < r) {
			int m =l+ (r-l)/2;
			sort(arr, l, m);
			sort(arr, m + 1, r);
			merge(arr, l, m, r);
		}
	}
}
