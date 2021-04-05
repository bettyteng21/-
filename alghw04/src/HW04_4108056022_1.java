public class HW04_4108056022_1 extends One_0k_rock{

    public static void main(String[] args) {
        String[] tests = {
                "0000111",      // false
                "01",    // true
                "011",   // false
                "001",   // false
                "0011",  // true
                "0101",  // false
                "013",   // false
                "0000011111", // true

        };
        boolean[] answer;

        HW04_4108056022_1 test = new HW04_4108056022_1();
        answer = test.one0k(tests);
        for(int i = 0; i <tests.length;i++){
            System.out.println(answer[i]);
        }
    }
    public boolean compare(char ch){
        return ch == '0';
    }
    public boolean checker(String str){
        int length = str.length()-1;
        for (int i = length>>1; i > -1; i--){
            if(!compare(str.charAt(i))||compare(str.charAt(length-i))) return false;
        }
        return true;
    }

    public boolean[]  one0k(String[] str){
        boolean[] ans = new boolean[str.length];
        for(int i = 0; i < str.length; i++){
            ans[i] = checker(str[i]);
        }
        return ans;
    }
}
