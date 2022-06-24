package schoolManagementSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class studentManager extends baseHuman{
	
	String stuClass;
	static String stuNum;
	Scanner scan = new Scanner(System.in);

	@Override
	public boolean enter() {
		
		boolean corr = false;
		try {
			
			BufferedReader fread = new BufferedReader(new FileReader(
				"C:\\schoolManagementSystem\\logins\\studentLogin.txt"));
			String line;
			
			System.out.print("Please enter Student Number: ");
			String gNum = scan.next();
			System.out.print("Please enter Password: ");
			String pass = scan.next();
			
			while((line = fread.readLine())!=null) {
				
				String[] sline = line.split(" ");
				stuNum = sline[0];
				password = sline[1];
				stuClass = sline[2];
				if (gNum.equals(stuNum)&& pass.equals(password)) {
						
					corr = true;
					break;
				}
			}
			fread.close();
		} catch (IOException e) {
			
			System.out.println("File Not Found!!!");
		}
		return corr;
	}

	@Override
	public void listNotes() {
		
		String[] notes = null;
		try {
			
			BufferedReader frd = new BufferedReader(new FileReader(
				"C:\\schoolManagementSystem\\classes\\"+stuClass+"\\"+stuNum+".txt"));
			ArrayList<String> notearr = new ArrayList<String>();
			String line;
			int count=0;
			while((line = frd.readLine())!=null) {
			
				count++;
				if (count>4) notearr.add(line);
				else continue;
			}
			notes = notearr.toArray(new String[0]);
			frd.close();
		} catch (IOException e) {

			System.out.println("File not found!");
		}

		System.out.println("Your notes:");
		for (String note : notes) {
			
			System.out.println(note);
		}
		return;
	}
}
