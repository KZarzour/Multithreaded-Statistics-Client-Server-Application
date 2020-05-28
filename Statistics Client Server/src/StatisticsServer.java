import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
//
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
//
import java.util.Scanner;
import java.util.concurrent.Executors;

public class StatisticsServer {
	public static void main(String[] args) throws Exception {
		try (var listener = new ServerSocket(6000)) {
			System.out.println("The statistics server is running...");
			var pool = Executors.newFixedThreadPool(20);
			while (true) {
				pool.execute(new StatThread(listener.accept()));
			}
		}
}

public static double fact(double n) {
	int res = 1, i; 
    for (i=2; i<=n; i++) 
        res *= i; 
    return res; 
}

public static long fibArray[]=new long[200];

public static long fibonacci(long n){
	long fibValue=0;
	if(n==0 ){
		return 0;
	}
	else if(n==1){
		return 1;
	}
	else if(fibArray[(int)n]!=0){
		return fibArray[(int)n];
	}
	else{
		fibValue=fibonacci(n-1)+fibonacci(n-2);
		fibArray[(int) n]=fibValue;
		return fibValue;
	}
}

private static class StatThread implements Runnable {
	private Socket socket;
	StatThread (Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		System.out.println("Connected: " + socket);
		try {
			var in = new Scanner(socket.getInputStream());
			var out = new PrintWriter(socket.getOutputStream(), true);
			while (in.hasNextLine()) {
				String[] exp =in.nextLine().toString().split(",");
				String command = exp[0];
				String values = exp[1];
				//
				
				String valuesAsStrings[]=values.split(" ");
				double numbers[]=new double[valuesAsStrings.length];
				for (int i = 0; i < numbers.length; i++)
				{
				  numbers[i] = Double.parseDouble(valuesAsStrings[i]);
				}
				Arrays.sort(numbers);

				
				//
				double result=0;
				boolean problem=false;
				if(command.equals("AVERAGE")){
					double sum=0;
					for (int i = 0; i < numbers.length; i++)
					{
					  sum=sum+numbers[i];
					}
					result=sum/numbers.length;
					out.println(result);

					
					
				}
				
				else if(command.equals("ABS")){
					double absvalue=numbers[0];
					if (absvalue<0){
						result=absvalue*(-1);
					}
					out.println(result);

				}
				
				else if(command.equals("ACOS")){
					double acos=numbers[0];
					
					result=java.lang.Math.acos(acos);
					
					out.println(result);
				}
				else if(command.equals("ASIN")){
					double asin=numbers[0];
					
					result=java.lang.Math.asin(asin);
					
					out.println(result);

				}
				
				else if(command.equals("ATAN")){
					result=java.lang.Math.atan(numbers[0]);
					out.println(result);
				}
				
				else if(command.equals("BIN")){
					double n=numbers[0];
					double k=numbers[1];
					double comb=(fact(n))/(fact(n-k)*fact(k));
					double p=numbers[2];
					result=comb*java.lang.Math.pow(p, k)*java.lang.Math.pow(1-p, n-k);
					
					out.println(result);
				}
				
				else if(command.equals("COMB")){
					result=(fact(numbers[0]))/(fact(numbers[0]-numbers[1])*fact(numbers[1]));
					
					out.println(result);
				}
				
				else if(command.equals("COUNT")){
					HashMap<Double,Integer> map=new HashMap<>();
					int counter=0;
					for(Double i: numbers) {
						if(!(map.containsKey(i))) {
							map.put(i, 1);
						}
						else {
							counter=map.get(i);
							map.remove(i);
							map.put(i, counter+1);
						}
					}
					
					String s="";
					for(Map.Entry<Double, Integer> entry:map.entrySet()) {
						s=s+"The number "+entry.getKey().toString()+" is found "+entry.getValue().toString()+" time(s) ";
					}
					out.println(s);
				}
				
				else if(command.equals("COS")){
					double cos=numbers[0];
					
					result=java.lang.Math.cos(cos);
					
					out.println(result);

				}
				
				else if(command.equals("EXP")){
					result=java.lang.Math.exp(numbers[0]);
					
					out.println(result);
				}
				
				else if(command.equals("FIB")){
					result=fibonacci((long) numbers[0]);
					
					out.println(result);
				}
				
				else if(command.equals("FACT")) {
					result=fact(numbers[0]);
			        
			        
			        out.println(result);
				}
				else if(command.equals("MEDIAN")){
					int size=numbers.length;
					int index=0;
					if (size%2==0) {
						result=numbers[size/2]+numbers[(size/2)-1];
						result=result/2;
					}
					else {
						index=(int) (size/2+0.5);
						result=numbers[index];
					}
					out.println(result);

				}
				
				else if(command.equals("MIN")){
					result=numbers[0];
					out.println(result);
				}
				
				else if(command.equals("MAX")){
					result=numbers[numbers.length-1];
					out.println(result);

				}
				
				else if(command.equals("MODE")){
					double num=numbers[0];
					int count=0;
					int highestCount=0;
					double mode=numbers[0];
					for (int i = 0; i < numbers.length; i++){
						if (numbers[i]==num) {
							count++;
						}
						if (count>highestCount) {
							highestCount=count;
							mode=numbers[i];
						}
						
						else {
							num=numbers[i];
							count=0;
						}
						
							
						
					}
					result=mode;
					out.println(result);

				}
				
				else if(command.equals("PERM")){
					double a=fact(numbers[0]);
					double b=numbers[0]-numbers[1];
				//	b=fact(b);
				//	result=a/b;
					result=a;
					out.println(result);
				}
				
				if(command.equals("AVERAGE")){
					result=(numbers[0])*(numbers[0]-1)/2;
					out.println(result);
	
				}
				else if(command.equals("PRODUCT")){
					double product=1;
					for (int i = 0; i < numbers.length; i++)
					{
					  product=product*numbers[i];
					}
					result=product;
					out.println(result);

				}
				
				else if(command.equals("SIN")){
					double sin=numbers[0];
					
					result=java.lang.Math.sin(sin);
					
					out.println(result);

				}
				
				else if(command.equals("SQRT")){
					result=java.lang.Math.sqrt(numbers[0]);
					out.println(result);
				}
				
				else if(command.equals("STDEV.S")){
					double sum=0;
					double avg=0;
					double A=0;
					double B=0;
					
					for (int i = 0; i < numbers.length; i++){
						sum=sum+numbers[i];
					}
					avg=sum/numbers.length;
					
					for (int i = 0; i < numbers.length; i++){
						B=(numbers[i]-avg);
						B=B*B;
						A=A+B;
					}
					
					A=A/(numbers.length-1);
					result=java.lang.Math.sqrt(A);
					out.println(result);
					
				}
				
				else if(command.equals("SUM")){
					double sum=0;
					for (int i = 0; i < numbers.length; i++)
					{
					  sum=sum+numbers[i];
					}
					result=sum;
					out.println(result);
				}
				
				else if(command.equals("TAN")){
					result=java.lang.Math.tan(numbers[0]);
					out.println(result);
				}
				
				else {
					//problem=true;
					out.println("Invalid request please enter an apropriate command.");
				}
				
				
			//	if (problem==true) {
			//		out.println("Invalid request please enter an apropriate command.");
			//		problem=false;
			//	}
			//	else {
			//		out.println(result); // send the result back
			//	}
				
			}
		} 
		catch (Exception e) {
			System.out.println("Error:" + socket);
		} 
		finally {
			try { 
				socket.close(); 
				} 
			catch (IOException e) {
			}
			System.out.println("Closed: " + socket);
		}
}
}
}