public class HW04_4108056007_3 extends One_0k_rock
{
	public static void main (String[] args){
		HW04_4108056007_3 test= new HW04_4108056007_3();
		String[] str={"00001111", "000101", "0", "11001", "01", "10"};
		boolean[] ans= test.one0k(str);
		for (boolean an : ans) {
			System.out.println(an);
		}
	}


	public boolean[] one0k(String[] str) {
		boolean[] ans = new boolean[str.length];
		int right, flag, len, j;
		for (int i=0; i< str.length; ++i){
			if (str[i].length()%2!=0) continue;
			for (j=0, right= str[i].length()-1, flag=0, len= str[i].length()>>>1; j<len; ++j){
				if( str[i].charAt(j)>=str[i].charAt(right)) {
					flag=1; break;
				}
				--right;
			}
			if (flag==0) ans[i]=true;
		}
		return ans;
	}
}
