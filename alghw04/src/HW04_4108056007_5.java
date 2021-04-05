public class HW04_4108056007_5 extends One_0k_rock
{
	final Thread[] T = new Thread[4];
	public static void main (String[] args){
		HW04_4108056007_5 test= new HW04_4108056007_5();
		String[] str={"00001111", "000101", "0", "1100", "01", "10"};
		boolean[] ans= test.one0k(str);
		for (boolean an : ans) {
			System.out.println(an);
		}
	}

	public boolean[]  one0k(String[] str){
		boolean[] ans= new boolean[str.length];
		for (int th=-1; 4>++th;){
			final int t = th;
			T[t] = new Thread(()->{
				int j, right, left, flag, len;
				for (int i=t; i< str.length; i+=4){
					if (str[i].length()%2!=0) continue;
					for (len= str[i].length()>>>1, j=0, right= len, left= len-1, flag=0; j<len; ++j){
						if(str[i].charAt(left)>=str[i].charAt(right)) {
							flag=1; break;
						}
						--left; ++right;
					}
					if (flag==0) ans[i]=true;
				}
			});
			T[t].start();
		}
		try{ for(final Thread t:T) t.join(); }
		catch(final InterruptedException e){}
		return ans;
	}
}
