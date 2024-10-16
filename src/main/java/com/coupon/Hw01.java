package com.coupon;


public class Hw01 {
	public static void main(String[] args) {
		// 請設計一隻Java程式,計算12,6這兩個數值的和與積
		System.out.println("第一題===============");
		int num1 = 12, num2 = 6;
		System.out.println(num1 + num2);
		System.out.println(num1 * num2);

		// 請設計一隻Java程式,計算200顆蛋共是幾打幾顆? (一打為12顆)
		System.out.println("第二題===============");
		int num3 = 200, num4 = 12;
		System.out.println(num3 / num4);
		System.out.println(num3 % num4);

		// 請由程式算出256559秒為多少天、多少小時、多少分與多少秒
		System.out.println("第三題===============");
		long num5 = 256559;
		long seconds = num5 % 60;
		long minutes = num5 / 60;
		long hours = minutes / 60;
		long days = hours / 24;
		String s1 = " 天", s2 = "時", s3 = "分", s4 = "秒";
		System.out.println(days + s1 + (hours % 24) + s2 + (minutes % 60) + s3 + seconds + s4);

		// 請定義一個常數為3.1415(圓周率),並計算半徑為5的圓面積與圓周長(可參考09_11 TestOverLoading)
		System.out.println("第四題===============");
		int num6 = 5;
		double pi = 3.1415;
		// DecimalFormat df = new DecimalFormat("#.####");
		System.out.println("圓面積:" + num6 * num6 * pi);
		System.out.println("圓周長:" + num6 * 2 * pi);

		// 某人在銀行存入150萬,銀行利率為2%,如果每年利息都繼續存入銀行,請用程式計算10年後,本金加利息共有多少錢
		// System.out.println("第五題===============");
		// 本利和 = 本金 × (1 + 年利率)^期間
		// int money = 1_500_000;
		// System.out.println(money * (1+0.02) ^ 10);

		// 請寫一隻程式,利用System.out.println()印出以下三個運算式結果:並請用註解各別說明答案的產生原因
		// 5 + 5
		// 5 + ‘5’
		// 5 + “5”
		System.out.println("第六題===============");
		int a = 5, b = 5;
		System.out.print("5 + 5 = ");
		System.out.println(a + b); // 為數字相加

		int c = 5;
		char d = '\u0035';
		System.out.print("5 + ‘5’ = ");
		System.out.println(c + d); // char為字元,會轉成unicode

		int e = 5;
		String f = "5";
		System.out.print("5 + “5” = ");
		System.out.println(e + f); // String為字串,會直接顯示雙引號內的字串
	}
}
