package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter full file path:");
		String pathStr = sc.nextLine();
		System.out.print("Enter salary: ");
		double salary = sc.nextDouble();
		
		try (BufferedReader br = new BufferedReader(new FileReader(pathStr))) {
			
			String line = br.readLine();
			
			List<Employee> list = new ArrayList<>();
			
			while (line != null) {
				
				String[] fields = line.split(",");
				
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				
				line = br.readLine();
			}
			
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			System.out.println("Email of people whose salary is more than " + String.format("%.2f", salary));
			List<String> aboveSalary = list.stream().filter(x -> x.getSalary() > salary)
					.map(x -> x.getEmail())
					.sorted(comp)
					.collect(Collectors.toList());
			aboveSalary.forEach(System.out::println);
			
			double startsWithLetter = list.stream().filter(x -> x.getName().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.reduce(0.0, (x, y) -> x+y);
			System.out.print("Sum of salary of people whose name starts with 'M': " + startsWithLetter);
		}catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();
		
	}
	
}
