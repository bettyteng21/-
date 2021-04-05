public class HW04_4108056007_2 extends  One_0k_rock
{
	public static void main (String[] args){
		HW04_4108056007_2 test= new HW04_4108056007_2();
		String[] str={"00001111", "000101", "0", "1100", "01", "10"};
		boolean[] ans= test.one0k(str);
		for (boolean an : ans) {
			System.out.println(an);
		}
	}

	public boolean[]  one0k(String[] str){
		boolean[] ans = new boolean[str.length];
		multiThread th0= new multiThread(str, ans, 0);
		multiThread th1= new multiThread(str, ans, 1);
		multiThread th2= new multiThread(str, ans, 2);
		th0.start();
		th1.start();
		th2.start();
		try{
			th0.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		try{
			th1.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		try{
			th2.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ans;
	}

	public static class multiThread extends Thread{
		String[] str;
		boolean[] ans;
		int index;
		public multiThread(String[] str, boolean[] ans, int index){
			this.str=str; this.ans= ans; this.index=index;
		}

		public void run(){
			int right, flag, len, j;
			for (int i=index; i< str.length; i+=3){
				if (str[i].length()%2!=0) continue;
				for (j=0, right= str[i].length()-1, flag=0, len= str[i].length()>>>1; j<len; ++j){
					if(str[i].charAt(j)>=str[i].charAt(right)) {
						flag=1; break;
					}
					--right;
				}
				if (flag==0) ans[i]=true;
			}
		}
	}
}
