package schoolManagementSystem;

import java.io.*;
import java.util.Scanner;

public class Main {
	
	
	public static void main(String[] args) {
		
		
		Scanner scan = new Scanner(System.in);
		check();
		int choice, mod;
		System.out.println("*****School Management System*****");
		System.out.print("1. Principal\n2. Teacher\n3. Student\n4. Exit");
		System.out.print("\nPress '1' or '2' or '3' or '4' for continue: ");
		mod = scan.nextInt();

		begin:
		while (true) {
			switch (mod) {

				case 1:
						
					principalManager priMgr = new principalManager();
					
					while (true) {

						if(priMgr.enter()){

							System.out.println("Welcome.");
							while (true){

								System.out.print(
									"1. Principal Register\n2. Teacher Register\n3. Student Register\n4.Create Class\n5. List Notes\n6. Exit");
								System.out.print(
									"\nPress '1' or '2' or '3' or '4' or '5' or '6' for continue: ");
								choice = scan.nextInt();
							
								switch(choice){
									
									case 1:
										
										priMgr.principalRegister();
										break;
									case 2:
										
										priMgr.teacherRegister();
										break;
									case 3:
	
										priMgr.studentRegister();
										break;
									case 4:
										
										priMgr.listNotes();
										break;
									case 5:

										priMgr.createClass();
										break;
									case 6:

										System.out.println("Good Bye!");
										scan.close();
										break begin;
									default:

										System.out.println("Invalid Enterence. Try Again.");
										break;
								}
							}
						}
						else {

							System.out.println("Nickname or password wrong try again.");
							continue;
						}
					}
				case 2:
		
					teacherManager tchMgr = new teacherManager();

					while(true){
					
						if(tchMgr.enter()){

							System.out.println("Welcome.");
							while(true){
								
								System.out.print(
									"1. Add Notes\n2. List Notes\n3. Exit");
								System.out.print(
									"\nPress '1' or '2' or '3' for continue: ");
								choice = scan.nextInt();
								
								switch (choice) {
									
									case 1:

										tchMgr.addNotes();
										break;
									case 2:

										tchMgr.listNotes();
										break;
									case 3:

										System.out.println("Good Bye!");
										scan.close();
										break begin;
									default:
										break;
								}
								
							}
						}
					}
				case 3:
		
					studentManager stuMgr = new studentManager();
					
					while (true) {
						
						if (stuMgr.enter()) {
							
							stuMgr.listNotes();
							System.out.println("Good Bye!");
							scan.close();
						}
						else {

							System.out.println("Nickname or password wrong try again.");
							continue;
						}
					}
					
					
					
				case 4:
	
					System.out.println("Good Bye!");
					scan.close();
					break;
	
				default:
						
					System.out.println("Please write '1' or '2' or '3'!");
					break;
			}
		}
		
		
	}
	static void check() {
		
		principalManager priMgr = new principalManager();
		boolean check=false;
		File f1 = new File("C:\\schoolManagementSystem");
		File f2 = new File("C:\\schoolManagementSystem\\logins");
		File f3 = new File("C:\\schoolManagementSystem\\classes");
		File f4 = new File("C:\\schoolManagementSystem\\teachers");
		File f5 = new File("C:\\schoolManagementSystem\\logins\\studentLogin.txt");
		File f6 = new File("C:\\schoolManagementSystem\\logins\\principalLogin.txt");
		File f7 = new File("C:\\schoolManagementSystem\\logins\\teacherLogin.txt");
		File f8 = new File("C:\\schoolManagementSystem\\classes\\classList.txt");
		
		while (!check) {

			if (f1.canExecute()) {

				if (f2.canExecute()) {

					if (f3.canExecute()) {

						if (f4.canExecute()) {

							if (f5.canExecute()) {

								if (f6.canExecute()) {

									if (f7.canExecute()){

										if (f8.canExecute()){

											System.out.println("Check Complete.");
											check = true;
											return;
										}
										else {
	
											try {
	
												f8.createNewFile();
												System.out.println(
													"File Created. C:\\schoolManagementSystem\\classes\\classList.txt");
												
											} catch (IOException e) {
		
												System.out.println("Error Found! f8");
												break;
											}
											
										}
									}
									else {

										try {

											f7.createNewFile();
											System.out.println(
												"File Created. C:\\schoolManagementSystem\\logins\\teacherLogin.txt");
											System.out.println("For continue please register a teacher:");
											priMgr.teacherRegister();
										} catch (IOException e) {
	
											System.out.println("Error Found! f7");
											break;
										}
										
									}
								}
								else {

									try {

										f6.createNewFile();
										System.out.println(
											"File Created. C:\\schoolManagementSystem\\classes\\principalLogin.txt");
										System.out.println("For continue please register a principal:");
										priMgr.principalRegister();
									} catch (IOException e) {
		
										System.out.println("Error Found! f6");
										break;
									}
								}
							}
							else {

								try {
									f5.createNewFile();
									System.out.println(
										"File Created. C:\\schoolManagementSystem\\logins\\studentLogin.txt");
								} catch (IOException e) {
		
									System.out.println("Error Found! f5");
									break;
								}
							}
						}
						else {

							f4.mkdir();
							System.out.println(
								"Directory Created. C:\\schoolManagementSystem\\teachers");
						}
					}
					else {

						f3.mkdir();
						System.out.println(
							"Directory Created. C:\\schoolManagementSystem\\classes");
					}
				}
				else {

					f2.mkdir();
					System.out.println(
						"Directory Created. C:\\schoolManagementSystem\\logins");
				}
			}
			else {

				f1.mkdir();
				System.out.println(
					"Directory Created. C:\\schoolManagementSystem");
			} 
		}
	}
}
