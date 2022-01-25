package golchos.util;

import java.util.Random;

public class AccountNumGenerator {
	public static String generateAccountNum() {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		int num = 0;
		for (int i = 0; i < 2; i++) {			
			num = rand.nextInt(10000);
			while(num < 1000) {
				num = rand.nextInt(10000);
			}
			sb.append(num);
			if(i < 1) sb.append("-");
		}
		
		return sb.toString();
	}
}
