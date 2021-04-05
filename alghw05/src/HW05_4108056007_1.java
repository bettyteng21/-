public class HW05_4108056007_1 extends LLK
{
	public static void main(String[] args){
		HW05_4108056007_1 test= new HW05_4108056007_1();
		int[][] array= {{48471285,46187890},{29017325,54336429},{1111,1111},{2222,2222},{39071816,-13623959},{-68518169,15335968},{5555,5555}};
		System.out.println(test.checkLLK(array));
	}
	public boolean checkLLK(int[][] array){
		int j,k, len=array.length-1;
		for(int i=0; i< array.length; ++i){
			double[] slope= new double[len-i];
			for (j=i+1, k=0; j< array.length; ++j, ++k){
				slope[k]=(double)(array[i][1]-array[j][1])/(double)(array[i][0]-array[j][0]);
			}
			qSort(slope, 0, slope.length-1);
			for(k=0; k< slope.length-1; ++k){
				if (slope[k]==slope[k+1] && slope[k]==slope[k+1]) return true;
			}
		}
		return false;
	}

	static int partition(double[] arr, int low, int high)
	{
		double pivot = arr[high], temp;
		int i = (low - 1);
		for (int j = low; j <= high - 1; ++j) {
			if (arr[j] <= pivot) {
				++i;
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		temp = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = temp;
		return (i+1);
	}

	static void qSort(double[] arr, int low, int high)
	{
		int pi;
		if (low < high) {
			pi = partition(arr, low, high);
			qSort(arr, low, pi - 1);
			qSort(arr, pi + 1, high);
		}
	}
}
