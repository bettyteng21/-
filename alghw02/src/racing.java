/**
 * This class shows how to implement and run two or more thread
 * and end all of them when one of them finished, which generally
 * use to running for more then one algorithm with the same time
 * complexity in java without import or extend any other library.
 * @author twjmy@msn.com
 */
public class racing
{
	/**
	 * Please add this variable to the break condition of
	 * <b>EVERY</b> loop(such as {@code for} or {@code while})
	 * in each implement {@code Runnable} of {@code Thread}
	 * with {@code ||} (<b>OR</b> logic algebra operation) since
	 * we want they check if another {@code Thread} finishied.
	 */
	volatile boolean finished = false;

	/**
	 * Example for return resault.
	 */
	volatile int resault = 0;

	/**
	 * {@code static} modifier should add when
	 * start threads in {@code main} function.
	 * This is an example for running 2 thread.
	 */
	final Thread T[] = {new Thread(() ->
	{ //method 1
		while (!finished) { // or while(condition||!finished)/for(;condition||!finished;)
			System.out.println("T[0] is runing...");
			if (Math.round(Math.random()) == 1) break; // random demo
			//Do stuff
		}
		if (!finished) { //you can put this block to the breakpoint of the loop like *return*
			finished = true;
			resault = 327; //or set any other return variable
		}
		System.out.println("T[0] ended");
	}), new Thread(() ->
	{ //method 2
		while (!finished) {
			System.out.println("T[1] is runing...");
			if (Math.round(Math.random()) == 1) break;
			//Do stuff
		}
		if (!finished) {
			finished = true;
			resault = 420; //or set any other return variable
		}
		System.out.println("T[1] ended");
	})
	};

	/**
	 * Start all threads when constructing.
	 */
	public racing()
	{
		//start all thread initialized in order of Thread array T
		for (final Thread t : T) t.start();
		//wait all thread finish in order of Thread array T
		try {
			for (final Thread t : T) t.join();
		} catch (final Exception e) {
		}
	}

	/**
	 * Start all threads when call this function.
	 * We can modify the function to pass parameter
	 * or return variable.
	 */
	public void func1()
	{
		for (final Thread t : T) t.start();
		try {
			for (final Thread t : T) t.join();
		} catch (final Exception e) {
		}
	}

	/**
	 * Start all threads when call this function,
	 * which returns {@code resault}.
	 */
	public int func2()
	{
		for (final Thread t : T) t.start();
		try {
			for (final Thread t : T) t.join();
		} catch (final Exception e) {
		}
		return resault;
	}

	/**
	 * In main function, if we want to run {@code func1()}
	 * and {@code func2()} we declared above, we must call
	 * them in constructor or pass by any other java class.
	 */
	public static void main(String[] args)
	{
		System.out.println(new racing().resault);
		//the resault is 327 if T[0] finish before T[1] else 420

		//System.out.println(func2()); //error occur
		//main function can only run static functions
	}

}