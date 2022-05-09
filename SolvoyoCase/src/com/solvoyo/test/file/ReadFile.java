package com.solvoyo.test.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.solvoyo.test.model.Car;

public class ReadFile {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("From which file do you want to load this information? (Please give full path) ");
		String filePath = br.readLine();

		// Open the file
		try {
			FileInputStream fstream = new FileInputStream(filePath.trim());
			br = new BufferedReader(new InputStreamReader(fstream));

			List<Car> cars = new ArrayList<Car>();
			String strLine;
			Car car = new Car();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				if (strLine.contains("Make")) {
					String make = strLine.replace("Make:", "").trim();
					car.setMake(make);
				}
				if (strLine.contains("Model")) {
					String model = strLine.replace("Model:", "").trim();
					car.setModel(model);
				}
				if (strLine.contains("Year")) {
					String year = strLine.replace("Year:", "").trim();
					car.setYear(Integer.valueOf(year));
				}
				if (strLine.contains("License")) {
					String license = strLine.replace("License:", "").trim();
					car.setLicense(license);
					cars.add(car);
					car = new Car();
				}
			}
			// Close the input stream
			fstream.close();

			System.out.println("Data loaded.");
			sortData(cars);
			System.out.println("Data sorted.");
			System.out.println("Arraylist:" + cars);

		} catch (Exception e) {
			System.out.println("File cannot be found.");
			return;
		}

	}

	private static void sortData(List<Car> cars) {
		Collections.sort(cars, new Comparator<Car>() {
			public int compare(Car car1, Car car2) {
				Integer year1 = car1.getYear();
				Integer year2 = car2.getYear();
				return year1.compareTo(year2);
			}
		});
	}

}
