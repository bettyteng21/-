public class HW07_4108056007_2 extends Buy_Phone
{
	public static void main(String[] args){
		HW07_4108056007_2 test= new HW07_4108056007_2();

		int[][] inputArr= {{1,1},{2,4},{2,10},{5,4},{4,8},{5,5},{8,4},{10,2},{10,1}};
		int[][] ans= test.bestPhone(inputArr);

		for (int[] an : ans) {
			System.out.println(an[0] + "," + an[1]);
		}
	}

	public int[][] bestPhone(int[][] inputArr){
		int len= inputArr.length, len_minus1= inputArr.length-1, i, ans_index=0, curr=inputArr[len_minus1][1];
		sort(inputArr, 0, len_minus1);

		int[][] temp = new int[len][2];
		int[] max_of_y=new int[len];
		max_of_y[len_minus1]= -2147483648;
		for (i=len-2; i>=0; --i){
			max_of_y[i]=curr;
			if (inputArr[i][1]>curr) curr=inputArr[i][1];
		}

		for (i=0; i<len; ++i){
			if (inputArr[i][1]>max_of_y[i]) {
				temp[ans_index][0]=inputArr[i][0]; temp[ans_index++][1]=inputArr[i][1];
			}
		}

		int[][] ans = new int[ans_index][2];
		for (i=0; i<ans_index; ++i){
			ans[i]=temp[i];
		}
		return ans;
	}

	void merge(int[][] arr, int l, int m, int r)
	{
		int n1 = m - l + 1, n2 = r - m;
		int[][] L = new int[n1][2];
		int[][] R = new int[n2][2];

		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		int i = 0, j = 0, k = l;
		while (i < n1 && j < n2) {
			if (L[i][0] < R[j][0]) {
				arr[k] = L[i]; i++;
			}
			else if (L[i][0] == R[j][0]){
				if (L[i][1]>R[j][1]){
					arr[k] = R[j]; j++;
				}
				else{
					arr[k] = L[i]; i++;
				}
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