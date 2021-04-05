public class HW04_4108056007_1 extends One_0k_rock
{
	public static void main (String[] args){
		long startTime = System.nanoTime();
		HW04_4108056007_1 test= new HW04_4108056007_1();
		String[] str={"00001111", "000101", "1", "11001", "01", "10"};
		boolean[] ans= test.one0k(str);
		for (boolean an : ans) {
			System.out.println(an);
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration);
	}

	public boolean[]  one0k(String[] str)
	{
		boolean[] ans = new boolean[str.length];
		int left, right, flag, len, j;
		for (int i=0; i< str.length; ++i){
			if (str[i].length()%2!=0) continue;
			for (j=0, len= str[i].length()>>>1, left= len-1, right= len, flag=0; j<len; ++j){
				if(str[i].charAt(left)>=str[i].charAt(right)) {
					flag=1; break;
				}
				--left; ++right;
			}
			if (flag==0) ans[i]=true;
		}
		return ans;
	}
}