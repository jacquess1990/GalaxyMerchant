package com.galaxyMerchandiser.GalaxyMerchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Scanner;

@SpringBootApplication
public class GalaxyMerchantApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalaxyMerchantApplication.class, args);
		HashMap<String, Integer> conversionValues = new HashMap<String, Integer>();
		conversionValues.put("I", 1);
		conversionValues.put("V", 5);
		conversionValues.put("X", 10);
		conversionValues.put("L", 50);
		conversionValues.put("C", 100);
		conversionValues.put("D", 500);
		conversionValues.put("M", 1000);
		conversionValues.put("IV", 4);
		conversionValues.put("IX", 9);
		conversionValues.put("XL", 40);
		conversionValues.put("XC", 90);
		conversionValues.put("CD", 400);
		conversionValues.put("CM", 900);

		HashMap<String, String> wordsToRomicNumbers = new HashMap<String, String>();
		wordsToRomicNumbers.put("glob", "I");
		wordsToRomicNumbers.put("prok", "V");
		wordsToRomicNumbers.put("pish", "X");
		wordsToRomicNumbers.put("tegj", "L");

		HashMap<String, Integer> combinationToCredits = new HashMap<String, Integer>();
		combinationToCredits.put("glob glob Silver", 34);
		combinationToCredits.put("glob prok Gold", 57800);
		combinationToCredits.put("pish pish Iron", 3910);
		System.out.println("### Test input: \n");
		System.out.println("glob is I");
		System.out.println("prok is V");
		System.out.println("pish is X");
		System.out.println("tegj is L");
		System.out.println("glob glob Silver is 34 Credits");
		System.out.println("glob prok Gold is 57800 Credits");
		System.out.println("pish pish Iron is 3910 Credits");
		System.out.println("how much is pish tegj glob glob?");
		System.out.println("how many Credits is glob prok Silver?");
		System.out.println("how many Credits is glob prok Gold?");
		System.out.println("how many Credits is glob prok Iron?\n");

		String conversionRomanString = wordsToRomicNumbers.get("pish")  + wordsToRomicNumbers.get("tegj") + wordsToRomicNumbers.get("glob")  + wordsToRomicNumbers.get("glob");
		String conversionRomanString1 = wordsToRomicNumbers.get("glob") + wordsToRomicNumbers.get("glob");
		String conversionRomanString2 = wordsToRomicNumbers.get("glob") + wordsToRomicNumbers.get("prok");
		String conversionRomanString3 = wordsToRomicNumbers.get("pish") + wordsToRomicNumbers.get("pish");

		Integer restOfIronCredits = convertRomanGalaxyValues(conversionRomanString3, conversionValues); // getting the value of word derived roman numbers in hashmap and calculating credits
		Integer ironCredits = combinationToCredits.get("pish pish Iron") - restOfIronCredits; // calculating value of Iron

		Integer firstValueCredits = convertRomanGalaxyValues(conversionRomanString, conversionValues);
		Integer restValuesBaseCredits  = convertRomanGalaxyValues(conversionRomanString2, conversionValues); // getting value of word derived roman numbers for pish tegj glob glob

		Integer secondValueCredits = convertRomanGalaxyValues(conversionRomanString1, conversionValues); // getting the value of word derived roman numbers in hashmap and calculating credits
		Integer silverValueCredits = combinationToCredits.get("glob glob Silver") - secondValueCredits; // calculating value of Iron

		Integer goldValueCredits = combinationToCredits.get("glob prok Gold") - restValuesBaseCredits; // calculating value of Iron
		System.out.println("##Test Output: \n");
		System.out.println("pish tegj glob glob is " + firstValueCredits);
		System.out.println("glob prok Silver is " + silverValueCredits);
		System.out.println("glob prok Gold is " + goldValueCredits);
		System.out.println("glob prok Iron is " + ironCredits);





	}

	private static Integer convertRomanGalaxyValues(String romanValues, HashMap<String, Integer> convertedValues) {
		Integer credits = 0;
		try {
			int counter = 0;
			for(int i = 0 ; i < romanValues.length(); i++) { //simple scanner based calculation, where approximately only two roman numbers in a row are considered, could be optimised if its needed
				counter ++;
				String conditionString = "";
				if(counter + 1 < romanValues.length()) {
					conditionString = romanValues.substring(i, i+2); // creating condition string that will be checked in hashmap as value
					if(convertedValues.containsKey(conditionString)) { // if double digit roman number is found
						credits += convertedValues.get(conditionString);
						i=i+1;
					} else {
						credits += convertedValues.get(""+romanValues.charAt(i)); // double digi roman number not found, do the normal calculation
					}
				} else { // in case there we calculate number by number
					credits += convertedValues.get(""+romanValues.charAt(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return credits;
	}


}
