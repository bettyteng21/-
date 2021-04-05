/** 생일 축하해. 노정훤. 미안해.
 * 1092 nchu algorithm homework local test class
 * @author twjmy@msn.com
 */
public class Test{
	public static void main(final String[] args){
		final Test test = new Test(true,false,true);

		final int[] HillFindingTD = test.generateData_HillFinding(10000,-70000,70000);//test.loadData_HillFinding();
		test.timing_HillFinding(HillFindingTD, new HW03_4108056007_4());
	//	test.timing_HillFinding(HillFindingTD, new HW03_4108056020_2());
	//	test.timing_HillFinding(HillFindingTD, new HW03_4108056020_5());

		// final int[] TSumTD = test.loadData_ThreeSum();
		// test.timing_ThreeSum(TSumTD, new HW02_4108056001_1());
		// test.timing_ThreeSum(TSumTD, new HW02_4108056020_1());
		// test.timing_ThreeSum(TSumTD, new HW02_4108056020_2());
		// test.timing_ThreeSum(TSumTD, new HW02_4108056020_5());
		// test.timing_ThreeSum(TSumTD, new HW02_4108056020_4());
		// test.timing_ThreeSum(TSumTD, new HW02_4108056021_2());
		// test.timing_ThreeSum(TSumTD, new HW02_4108056036_2());
		// test.timing_ThreeSum(TSumTD, new HW02_4108056036_3());
	}

	/** set the times of running for caculating average running time */
	public final int RUN_TIME;
	/** switch of break timming loop when wrong answer */
	public final boolean CHECK_ANS;
	/** switch of show the cost of every running */
	public final boolean SHOW_COUNT;
	/** switch of show the test data you use */
	public final boolean SHOW_TEST_DATA;
	/** the current path of workspace located at */
	public final String PATH;
	// constructors
	public Test(){ this(false); }
	public Test(final int RUN_TIME){ this(RUN_TIME, false); }
	public Test(final boolean ALL){ this(10, false); }
	public Test(final String PATH){ this(10, false, PATH); }
	public Test(final int RUN_TIME, final boolean ALL){
		this(RUN_TIME, ALL, System.getProperty("user.dir")); //default current workspace path.
	}
	public Test(final boolean ALL, final String PATH){
		this(10, ALL, PATH);
	}
	public Test(final int RUN_TIME, final boolean ALL, final String PATH){
		this(RUN_TIME,ALL,ALL,ALL,PATH);
	}
	public Test(final boolean CHECK_ANS, final boolean SHOW_COUNT, final boolean SHOW_TEST_DATA){
		this(10,CHECK_ANS, SHOW_COUNT, SHOW_TEST_DATA, System.getProperty("user.dir"));
	}
	public Test(final int RUN_TIME, final boolean CHECK_ANS, final boolean SHOW_COUNT, final boolean SHOW_TEST_DATA, final String PATH){
		this.RUN_TIME=RUN_TIME; this.PATH=PATH;
		this.CHECK_ANS=CHECK_ANS; this.SHOW_COUNT=SHOW_COUNT; this.SHOW_TEST_DATA=SHOW_TEST_DATA;
		System.out.println(this.getClass()+": Path: " + PATH);
		System.out.println(this.getClass()+": Run times of every function: "+RUN_TIME);
		if(SHOW_COUNT) System.out.println(this.getClass()+": Show count status.");
		if(CHECK_ANS) System.out.println(this.getClass()+": Check answers.");
	}

	int timing_HillFinding(final int[] td, final HillFinding c) {
		System.out.println("Start to timing "+c.getClass()+" function H_Finding()...");
		double averageTime = 0;
		double time = 0;
		int a = -2, ca = -2;
		for(int i = -1; RUN_TIME > ++i && averageTime != -1;){
			if(i==0){
				System.out.println("HillFinding test data answer loading from: "+PATH+"\\HillFinding_test_data_ans.txt");
				try {
					final java.io.BufferedReader br = new java.io.BufferedReader(
							new java.io.InputStreamReader(new java.io.FileInputStream(PATH+"\\HillFinding_test_data_ans.txt")));
					String line;
					while(br.ready() && (line = br.readLine()) != null){
						System.out.println("Correct answer: "+line);
						ca = Integer.valueOf(line);
					}
					br.close();
				} catch (final java.io.FileNotFoundException e) {
					System.out.println("\"HillFinding_test_data.txt\" file not found.");
				} catch (final java.io.IOException e) {
				}
			}
			time = -System.nanoTime();
			a = c.H_Finding(td);
			time = (System.nanoTime()+time)/Math.pow(10,9);
			if(SHOW_COUNT) System.out.printf(
					"\t"+c.getClass()+" running count..."+(i+1)+"\tTime: %.6fs\n",time);
			if(CHECK_ANS) if(ca != -2 && a != ca) averageTime = -1;
			if(averageTime != -1) averageTime += time;
		}
		if(averageTime == -1) System.out.println(
				"\t"+c.getClass()+" function H_Finding() Wrong Answer.");
		else {
			System.out.printf(
					"\t"+c.getClass()+" function H_Finding() "+
							"average running time: %.6fs\n",averageTime/RUN_TIME);
		}
		System.out.println("\tResault: "+c.H_Finding(td));
		System.out.println("End of timing "+c.getClass()+".");
		return a;
	}

	int[] generateData_HillFinding(){
		return generateData_HillFinding((int)(Math.random()*(20+1)+1));
	}

	int[] generateData_HillFinding(final int length){
		return generateData_HillFinding(length, Integer.MIN_VALUE/3, Integer.MAX_VALUE/3);
	}

	int[] generateData_HillFinding(final String PATH){
		return generateData_HillFinding((int)(Math.random()*(20+1)+1), Integer.MIN_VALUE/3, Integer.MAX_VALUE/3, PATH);
	}

	int[] generateData_HillFinding(final int minVal, final int maxVal){
		return generateData_HillFinding((int)(Math.random()*(20+1)+1),minVal,maxVal);
	}

	int[] generateData_HillFinding(final int length, final int minVal, final int maxVal){
		return generateData_HillFinding(
				length,
				minVal<Integer.MIN_VALUE/3?minVal:Integer.MIN_VALUE/3,
				maxVal>Integer.MAX_VALUE/3?maxVal:Integer.MAX_VALUE/3,
				PATH
		);
	}

	int[] generateData_HillFinding(final int length, final int minVal, final int maxVal, final String PATH){
		System.out.println("HillFinding test data generating by size " + length + "...");
		java.util.List<Integer> data = new java.util.ArrayList<Integer>(length);
		java.util.List<Integer> resault = null;
		final java.util.Random rand = new java.util.Random();
		for(int i = -1; length > ++i;){
			int pivot = minVal + rand.nextInt((int)(maxVal - minVal + 1));
			int index = java.util.Collections.binarySearch(data, pivot);
			data.add(index > -1 ? index : - index - 1, pivot);
		}
		final int to = (int)(Math.random()*(length+1)); System.out.println(to-1);
		resault = data.subList(to, length);
		resault.addAll(data.subList(0, to));
		try {
			final java.io.File file = new java.io.File(PATH);
			file.createNewFile();
			System.out.println("HillFinding test data saving on: "+PATH+"\\HillFinding_test_data.txt");
			java.io.BufferedWriter bw = new java.io.BufferedWriter(
					new java.io.FileWriter(new java.io.File(
							PATH+"\\HillFinding_test_data.txt"))
			);
			for(int i = -1; length > ++i;){
				bw.write(resault.get(i)+(i==length-1?"":"\r\n"));
				if(SHOW_TEST_DATA) System.out.println(resault.get(i));
			}
			bw.flush(); bw.close();
			System.out.println("HillFinding test data answer saving on: "+PATH+"\\HillFinding_test_data_ans.txt");
			bw = new java.io.BufferedWriter(
					new java.io.FileWriter(new java.io.File(
							PATH+"\\HillFinding_test_data_ans.txt"))
			);
			bw.write(""+(to-1));
			bw.flush(); bw.close();
		} catch (final java.io.IOException e) {
		}
		System.out.println("HillFinding test data generated.");
		return  resault.stream().mapToInt(i -> i).toArray();
	}

	int[] loadData_HillFinding(){
		return loadData_HillFinding(PATH);
	}

	int[] loadData_HillFinding(String data_path) {
		data_path += "\\HillFinding_test_data.txt";
		System.out.println("HillFinding test data loading from: " + data_path);
		java.util.ArrayList<Integer> data = new java.util.ArrayList<>();
		try {
			final java.io.BufferedReader br = new java.io.BufferedReader(
					new java.io.InputStreamReader(new java.io.FileInputStream(data_path)));
			String line;
			while(br.ready() && (line = br.readLine()) != null){
				data.add(Integer.valueOf(line));
				if(SHOW_TEST_DATA) System.out.println(line);
			}
			br.close();
		} catch (final java.io.FileNotFoundException e) {
			System.out.println("\"HillFinding_test_data.txt\" file not found.");
		} catch (final java.io.IOException e) {
		}
		System.out.println("HillFinding test data initialized.");
		return data.stream().mapToInt(Integer::intValue).toArray();
	}

//	int timing_ThreeSum(final int[] td, final ThreeSum c) {
//		System.out.println("Start to timing "+c.getClass()+" function T_Sum()...");
//		double averageTime = 0;
//		double time = 0;
//		int a = -1;
//		for (int i = -1; RUN_TIME > ++i && averageTime != -1;) {
//			time = -System.nanoTime();
//			a = c.T_sum(td);
//			time = (System.nanoTime() + time) / Math.pow(10,9);
//			if(SHOW_COUNT) System.out.printf(
//					"\t"+c.getClass()+" running count..."+(i+1)+"\tTime: %.7fs\n",time);
//			if(CHECK_ANS) if(a != 1338261) averageTime = -1;
//			if(averageTime != -1) averageTime += time;
//		}
//		if(averageTime == -1) System.out.println(
//				"\t"+c.getClass()+" function T_Sum() Wrong Answer.");
//		else {
//			System.out.printf(
//					"\t"+c.getClass()+" function T_Sum() "+
//							"average running time: %.7fs\n",averageTime/RUN_TIME);
//		}
//		System.out.println("\tResault: "+c.T_sum(td));
//		System.out.println("End of timing "+c.getClass()+".");
//		return a;
//	}

	int[] loadData_ThreeSum() {
		return loadData_ThreeSum(PATH);
	}

	int[] loadData_ThreeSum(String data_path) {
		data_path += "\\ThreeSum_test_data.txt";
		System.out.println("ThreeSum test data loading from: " + data_path);
		java.util.ArrayList<Integer> data = new java.util.ArrayList<>();
		try {
			final java.io.BufferedReader br = new java.io.BufferedReader(
					new java.io.InputStreamReader(new java.io.FileInputStream(data_path)));
			String line;
			while(br.ready() && (line = br.readLine()) != null){
				data.add(Integer.valueOf(line));
				if(SHOW_TEST_DATA) System.out.println(line);
			}
			br.close();
		} catch (final java.io.FileNotFoundException e) {
			System.out.println("\"ThreeSum_test_data.txt\" file not found.");
		} catch (final java.io.IOException e) {
		}
		System.out.println("ThreeSum test data initialized.");
		return data.stream().mapToInt(Integer::intValue).toArray();
	}

//	void timing_ArrayData(final int[] td, final ArrayData c) {
//		System.out.println("Start to timing "+c.getClass()+" function min()...");
//		double averageTime = 0;
//		double time = 0; int a = 0;
//		for(int i = -1; RUN_TIME > ++i && averageTime != -1;){
//			time = -System.nanoTime();
//			a = c.min();
//			time = (System.nanoTime()+time)/1000000000.0;
//			if(SHOW_COUNT) System.out.printf(
//					"\t"+c.getClass()+" running count..."+(i+1)+"\tTime: %.6fs\n",time);
//			// if(CHECK_ANS) if(a != -1-1-1) averageTime = -1;
//			if(averageTime != -1) averageTime += time;
//		}
//		if(CHECK_ANS && averageTime == -1) System.out.println(
//				"\t"+c.getClass()+" function min() Wrong Answer.");
//		else {
//			System.out.printf(
//					"\t"+c.getClass()+" function min() "+
//							"average running time: %.6fs\n",averageTime/RUN_TIME);
//		}
//		System.out.println("\tResault: "+c.min());
//
//		System.out.println("Start to timing "+c.getClass()+" function max()...");
//		for(int i = -1; RUN_TIME > ++i && averageTime != -1;){
//			time = -System.nanoTime();
//			a = c.max();
//			time = (System.nanoTime()+time)/1000000000.0;
//			if(SHOW_COUNT) System.out.printf(
//					"\t"+c.getClass()+" running count..."+(i+1)+"\tTime: %.6fs\n",time);
//			// if(CHECK_ANS) if(a != -1-1-1) averageTime = -1;
//			if(averageTime != -1) averageTime += time;
//		}
//		if(CHECK_ANS && averageTime == -1) System.out.println(
//				"\t"+c.getClass()+" function max() Wrong Answer.");
//		else {
//			System.out.printf(
//					"\t"+c.getClass()+" function max() "+
//							"average running time: %.6fs\n",averageTime/RUN_TIME);
//		}
//		System.out.println("\tResault: "+c.max());
//
//		System.out.println("End of timing "+c.getClass()+".");
//	}

	int[] generateData_ArrayData(){
		System.out.println("Generating ArrayData test data...");
		int ArrayDataTD[] = new int[(int)(Math.random()*(10+1)+1)];
		if(SHOW_TEST_DATA) System.out.printf("A=[");
		for(int e=-1;ArrayDataTD.length>++e;){
			ArrayDataTD[e] = (int)(Math.random()*(Integer.MAX_VALUE));
			if(Math.round(Math.random())==1) ArrayDataTD[e] = -ArrayDataTD[e]-1;
			if(SHOW_TEST_DATA) System.out.printf(""+ArrayDataTD[e]);
			if(e!=ArrayDataTD.length-1) System.out.printf(", ");
		}
		if(SHOW_TEST_DATA) System.out.println("]\n");
		return ArrayDataTD;
	}
}