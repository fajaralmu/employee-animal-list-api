package com.fajar.employeedataapi.util;

import java.math.BigDecimal;
import java.util.Random;
 
public class StringUtil {
	
	static Random rand = new Random();

	public static String generateRandomNumber(int length) {

		String random = "";
		if (length < 1) {
			length = 1;
		}

		for (int i = 0; i < length; i++) {

			Integer n = rand.nextInt(9);
			random += n;
		}
		return random;
	}
	 
	public static int base64StringFileSize  (String base64String)   {
		if (base64String.contains(",")) {
			base64String = base64String.split(",")[1];
		}
		int stringLength = base64String.length();

		double sizeInBytes = 4 * Math.ceil((stringLength / 3))*0.5624896334383812;
		return new BigDecimal(sizeInBytes).intValue();
	}
	
	public static String[] divideStringInto(String string, double partialSize) {
		int stringLength = string.length();
		int arrayCOunt = (int) Math.ceil(stringLength / partialSize);
		String[] strings = new String[ arrayCOunt];
		int partialSizeCounter = 0, order = 0;
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i = 0; i < stringLength; i++) {
			stringBuilder.append(string.charAt(i));
			partialSizeCounter++;
			 
			if (partialSizeCounter == partialSize) {
				 
				partialSizeCounter = 0;
				strings[order] = stringBuilder.toString();
				order++;
				
				stringBuilder = new StringBuilder();
			} else
			if (i == stringLength - 1) {
				 strings[order] = stringBuilder.toString();
			}
//			System.out.println("ORDER: "+order);
		}
		int dividedStringLengthSummary = 0;
		for (int i = 0; i < strings.length; i++) {
			if (strings[i] == null) continue;
			dividedStringLengthSummary += strings[i].length();
		}
		System.out.println("String length: "+ stringLength+", didived string length accumulation: "+ dividedStringLengthSummary);
		return strings ;
	}
}
