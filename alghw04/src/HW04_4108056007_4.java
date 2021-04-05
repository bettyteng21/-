public class HW04_4108056007_4 extends One_0k_rock
{
	public static void main (String[] args){
		HW04_4108056007_4 test= new HW04_4108056007_4();
		String[] str={"00001111", "000101", "1", "11001", "01", "10", "011", "100", "00000000001111111111"};
		boolean[] ans= test.one0k(str);
		for (boolean an : ans) {
			System.out.println(an);
		}
	}

	public boolean compare(String str){
		int len= str.length()-1;
		for (int j= len>>>1; j>=0; --j){
			if(str.charAt(j)>=str.charAt(len-j)) {
				return false;
			}
		}
		return true;
	}

	public boolean[]  one0k(String[] str)
	{
		boolean[] ans = new boolean[str.length];
		for (int i=0; i< str.length; ++i){
			ans[i]=compare(str[i]);
		}
		return ans;
	}
}