public class HW08_4108056007_1 extends Buy_Phone_v2
{
	public static void main(String[] args){
		HW08_4108056007_1 test= new HW08_4108056007_1();
		int[][] inputArr= {{5,6,9,7,6,7},
						   {6,7,10,6,5,4},
						   {9,6,7,8,8,8}};
		int[][] ans= test.bestPhone(inputArr);

		for (int i=0; i< ans.length; ++i){
			for (int j=0; j<ans[i].length; ++j){
				System.out.print(ans[i][j]+", ");
			}
			System.out.println();
		}
	}
	public int[][] bestPhone(int[][] inputArr){
		final int len= inputArr.length, len_minus1= len-1;
		int total=len_minus1;
		boolean flag=false;
		sort(inputArr, 0,len_minus1);

		int[] max_of_y= new int[6];
		System.arraycopy(inputArr[len_minus1], 0, max_of_y, 0, 6);
		int[][] temp= new int[len][6];
		System.arraycopy(inputArr[len_minus1], 0, temp[total--], 0, 6);

		for (int i= len-2; i>=0; --i){
			for (int j=1; j<6; ++j){
				if (inputArr[i][j]>max_of_y[j]) {
					max_of_y[j]=inputArr[i][j]; flag=true;
				}
			}
			if (flag){
				System.arraycopy(inputArr[i], 0, temp[total--], 0, 6);
				flag=false;
			}
		}

		int[][] ans= new int[len_minus1-total][6];
		for (int i=total+1, j=0; i<len; ++i){
			System.arraycopy(temp[i], 0, ans[j++], 0, 6);
		}
		return ans;
	}

	void merge(int[][] arr, int l, int m, int r)
	{
		int n1 = m - l + 1,  n2 = r - m;
		int[][] L = new int[n1][];
		int[][] R = new int[n2][];

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
