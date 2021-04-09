public class HW06_4108056007_1 extends Dessert_Desert
{
	public static void main(String[] args){
		HW06_4108056007_1 test= new HW06_4108056007_1();
		int[][] inputArr={{1,1,1,1,1,1,1}, {1,3,5,7,9}, {1,2,3}, {5,4,3,2,1},{2,1,3,2},{6,1,5,8,3,7,9}};
		int[] answer= test.maxBlocks(inputArr);
		for(int i=0; i<inputArr.length;++i){
			System.out.println(answer[i]);
		}
	}

	public class Diff{
		int diff, value, index;
		public Diff(int diff, int value, int index){
			this.diff= diff; this.value= value; this.index=index;
		}
	}

	public int[] maxBlocks(int[][] inputArr){
		int[] ans= new int[inputArr.length];
		int j, k, flag=0;
		for (int i=0; i< inputArr.length; ++i){
			ans[i]=inputArr[i].length;
			Diff arr[]= new Diff[inputArr[i].length];
			for (j=0; j<arr.length; ++j){
				arr[j]= new Diff(j, inputArr[i][j], j);
			}
			sort1(arr, 0, arr.length-1);
			for (j=0; j<arr.length; ++j){
				arr[j].diff=j-arr[j].diff;
			}
			sort2(arr, 0, arr.length-1);
			for (j=0; j<arr.length; ++j){
				if(arr[j].diff<=0){
					for(k=0; k<j; ++k){
						if(((arr[k].index+arr[k].diff)>(arr[j].index+arr[j].diff)) && arr[k].diff>0 && flag==0) {
							flag=1; ans[i]--;
						}
						else if(((arr[k].index+arr[k].diff)>(arr[j].index+arr[j].diff)) && arr[k].diff>0 && flag!=0){
							ans[i]--; break;
						}
					}
				}
				else flag=0;
			}
		}
		return ans;
	}

	void merge1(Diff arr[], int l, int m, int r)
	{
		int n1 = m - l + 1, n2 = r - m;
		Diff L[] = new Diff[n1];
		Diff R[] = new Diff[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		int i = 0, j = 0, k = l;
		while (i < n1 && j < n2) {
			if (L[i].value <= R[j].value) {
				arr[k] = L[i];
				i++;
			}
			else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}
		while (j < n2) {
			arr[k] = R[j]; j++; k++;
		}
	}

	void sort1(Diff arr[], int l, int r)
	{
		if (l < r) {
			int m =l+ (r-l)/2;
			sort1(arr, l, m);
			sort1(arr, m + 1, r);
			merge1(arr, l, m, r);
		}
	}

	void merge2(Diff arr[], int l, int m, int r)
	{
		int n1 = m - l + 1, n2 = r - m;
		Diff L[] = new Diff[n1];
		Diff R[] = new Diff[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		int i = 0, j = 0, k = l;
		while (i < n1 && j < n2) {
			if (L[i].index <= R[j].index) {
				arr[k] = L[i];
				i++;
			}
			else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}
		while (j < n2) {
			arr[k] = R[j]; j++; k++;
		}
	}

	void sort2(Diff arr[], int l, int r)
	{
		if (l < r) {
			int m =l+ (r-l)/2;
			sort2(arr, l, m);
			sort2(arr, m + 1, r);
			merge2(arr, l, m, r);
		}
	}
}
