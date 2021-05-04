public class HW08_4108056007_2 extends Buy_Phone_v2
{
	int[][] L, R;
	public static void main(String[] args){
		HW08_4108056007_2 test= new HW08_4108056007_2();
		int[][] inputArr= {{8,7,7,4,2,1},{2,4,4,6,2,1},{4,0,5,1,3,2},{5,2,4,3,7,3},{7,5,6,9,8,9},{1,1,1,1,1,100},{1,1,1,1,1,100}, {1,1,1,4,1,3}, {1,1,1,3,1,300}};

		int[][] ans= test.bestPhone(inputArr);

		for (int i=0; i< ans.length; ++i){
			for (int j=0; j<ans[i].length; ++j){
				System.out.print(ans[i][j]+", ");
			}
			System.out.println();
		}
	}

	public int[][] bestPhone(int[][] inputArr){
		int total=inputArr.length, index=0;
		sort(inputArr, 0, inputArr.length-1);
		for (int i=0; i<inputArr.length; ++i){
			if (inputArr[i]==null) {
				--total; continue;
			}
			for (int j= inputArr.length-1; j>i; --j){
				if (inputArr[j]==null) continue;
				if (inputArr[i][0]<=inputArr[j][0] && inputArr[i][1]<=inputArr[j][1] && inputArr[i][2]<=inputArr[j][2] && inputArr[i][3]<=inputArr[j][3] && inputArr[i][4]<=inputArr[j][4] && inputArr[i][5]<=inputArr[j][5]){
						inputArr[i]=null; --total; break;
				}

			}
		}
		int[][] ans= new int[total][];
		for (int[] ints : inputArr) {
			if (ints != null) {
				ans[index] = ints;
				index++;
			}
		}
		return ans;
	}

	void merge(int[][] arr, int l, int m, int r)
	{
		int n1 = m - l + 1,  n2 = r - m;
		L = new int[n1][];
		R = new int[n2][];

		int i, j;
		for (i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		i = 0; j = 0;
		int k = l, x;
		boolean flag;
		while (i < n1 && j < n2) {
			flag=false;
			if (L[i][0] < R[j][0]) {
				arr[k] = L[i++];
			}
			else if (L[i][0] == R[j][0]){
				for (x=1; x<6; ++x){
					if (L[i][x]>R[j][x]){
						arr[k] = R[j++];
						flag=true; break;
					}
					else if(L[i][x]<R[j][x]){
						arr[k] = L[i++];
						flag=true; break;
					}
				}
				if (!flag) arr[k] = L[i++];
			}
			else {
				arr[k] = R[j++];
			}
			++k;
		}

		while (i < n1) {
			arr[k++] = L[i++];
		}

		while (j < n2) {
			arr[k++] = R[j++];
		}
	}

	void sort(int[][] arr, int l, int r)
	{
		if (l < r) {
			int m =l+ (r-l)/2;
			sort(arr, l, m);
			sort(arr, m + 1, r);
			merge(arr, l, m, r);
		}
	}
}
