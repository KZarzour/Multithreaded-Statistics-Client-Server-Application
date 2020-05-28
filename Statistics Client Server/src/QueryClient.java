import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class QueryClient {
	public static void main(String[] args) throws Exception {
		try (var socket = new Socket("localhost", 6000)) {
				System.out.println(
						
						"ABS: The absolute value of a number\r\n"+
						"ACOS: The arc cos value of a number\r\n"+
						"ASIN: The arc cos value of a number\r\n"+
						"ATAN: The atan value of a number\r\n"+
						"AVERAGE: The average or arithmetic mean for a group of numbers\r\n"+
						"BIN: The binomial value enter n then k then p\r\n"+
						"COMB: The number of possible combination enter n then k\r\n"+
						"COUNT: The number of cell locations in a range that contain a numeric character\r\n"+
						"COS: The cos value of a number\r\n"+
						"EXP: The exponential value of a number\r\n"+
						"FACT: The factorial value of a number\r\n"+
						"FIB: The fibonacci value of a number\r\n"+
						"MAX: The highest numeric value in a group of numbers\r\n"+
						"MEDIAN: The middle number in a group of numbers (half the numbers in the group are higher than the median and half the numbers in the group are lower than the median)\r\n"+
						"MIN: The lowest numeric value in a group of numbers\r\n"+
						"MODE: The number that appears most frequently in a group of numbers\r\n"+
						"PERM: The number of possible permutation enter n then r\r\n"+
						"PRODUCT: The result of multiplying all the values in a range of cell locations\r\n"+
						"POWSERIES: The sum of numbers from 1 to n\r\n"+
						"SIN: The sin value of a number\r\n"+
						"SQRT: The positive square root of a number\r\n"+
						"STDEV.S: The standard deviation for a group of numbers based on a sample\r\n"+
						"SUM: The total of all numeric values in a group\r\n"+
						"TAN: The tan value of a number\r\n"+
						
						"Input format: AVERAGE\n"+
						"	      1 2 3 4\r\n"
						
						);
				System.out.println("Enter lines of text then Ctrl+D or Ctrl+C to quit");
				
				var scanner = new Scanner(System.in);
				var in = new Scanner(socket.getInputStream());
				var out = new PrintWriter(socket.getOutputStream(),true);
				while (scanner.hasNextLine()) {
					String Bye=scanner.nextLine();
					Bye=Bye+",";
					if(scanner.hasNextLine()) {
						Bye=Bye+scanner.nextLine();
					}
					out.println(Bye);
					System.out.println(in.nextLine());
				}
		}
	}
}