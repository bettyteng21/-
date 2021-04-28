public class HW07_4108056007_1 extends Buy_Phone
{
	int[][] L; int[][] R; int[][] temp; int[][] ans;
	int n1, n2, k, len, len_minus1, ans_index, max_of_y;
	public static void main(String[] args){
		HW07_4108056007_1 test= new HW07_4108056007_1();

		int[][] inputArr= {{1,1},{2,4},{2,10},{5,4},{4,8},{5,5},{8,4},{10,2},{10,1}};
		int[][] ans= test.bestPhone(inputArr);

		for (int[] an : ans) {
			System.out.println(an[0] + "," + an[1]);
		}
	}

	public int[][] bestPhone(int[][] inputArr){
		int j, i;
		len= inputArr.length; len_minus1= inputArr.length-1; ans_index=len_minus1;
		sort(inputArr, 0, len_minus1);

		temp = new int[len][2];
		max_of_y=inputArr[len_minus1][1];

		temp[ans_index--]=inputArr[len_minus1];
		for (i=len-2; i>=0; --i){
			if (inputArr[i][1]>max_of_y) {
				temp[ans_index--]=inputArr[i];
				max_of_y=inputArr[i][1];
			}
		}

		ans = new int[len_minus1-ans_index][];
		for (i=ans_index+1, j=0; i<len; ++i){
			ans[j++]=temp[i];
		}
		return ans;
	}

	void merge(int[][] arr, int l, int m, int r)
	{
		n1 = m - l + 1; n2 = r - m;
		L = new int[n1][2];
		R = new int[n2][2];

		int i, j;
		for (i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		i = 0; j = 0; k = l;
		while (i < n1 && j < n2) {
			if (L[i][0] < R[j][0]) {
				arr[k] = L[i++];
			}
			else if (L[i][0] == R[j][0]){
				if (L[i][1]>R[j][1]){
					arr[k] = R[j++];
				}
				else{
					arr[k] = L[i++];
				}
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
