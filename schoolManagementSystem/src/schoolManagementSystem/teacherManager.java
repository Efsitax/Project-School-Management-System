package schoolManagementSystem;

import java.io.*;
import java.util.*;

public class teacherManager extends baseHuman {

	String branch;
	static String tchNum;
	Scanner scan = new Scanner(System.in);

	@Override
	public boolean enter() {

		boolean corr = false;
		try {

			BufferedReader fread = new BufferedReader(new FileReader(
					"C:\\schoolManagementSystem\\logins\\teacherLogin.txt"));
			String line;

			System.out.print("Please enter Teacher Number: ");
			String gNum = scan.next();
			System.out.print("Please enter Password: ");
			String pass = scan.next();

			while ((line = fread.readLine()) != null) {

				String[] sline = line.split(" ");
				tchNum= sline[0];
				password = sline[1];
				if (gNum.equals(tchNum) && pass.equals(password)) {

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
		
		studentManager stuMgr = new studentManager();
		String[] notes = null,classes = null,stunums = null,stus = null;
		String line;
		int choice=0;
		boolean exist=false;
		System.out.println("\n\n***Listing Notes***");
		try {

			BufferedReader fread = new BufferedReader(new FileReader(
					"C:\\schoolManagementSystem\\classes\\classList.txt"));
			ArrayList<String> arrlst = new ArrayList<String>();
			while ((line = fread.readLine()) != null)
				arrlst.add(line);
			classes = arrlst.toArray(new String[0]);
			fread.close();
		} catch (Exception e) {

			System.out.println("File Not Found!!!");
		}

		while (true) {

			System.out.print("Please enter student class(Example: 3-A): ");
			stuMgr.stuClass = scan.next();
			for (String clss : classes) {

				if (stuMgr.stuClass.equals(clss)) {

					exist = true;
					break;
				} 
				else continue;
			}
			if (exist) {

				try {

					BufferedReader frd = new BufferedReader(new FileReader(
						"C:\\schoolManagementSystem\\classes\\" + stuMgr.stuClass + "\\studentlist.txt"));
					ArrayList<String> stuarr = new ArrayList<String>();
					ArrayList<String> stunumarr = new ArrayList<String>();
					while ((line = frd.readLine()) != null) {

						String[] numsplt = line.split(" ");
						stunumarr.add(numsplt[0]); 
						stuarr.add(line);
					}
					stunums = stunumarr.toArray(new String[0]);
					stus = stuarr.toArray(new String[0]);
					frd.close();
				} catch (IOException e) {

					System.out.println("File not found!");
				}
				int i=1;
				for (String stu : stus) {

					System.out.printf("%d. %s\n",i++,stu);
				}
				while (true) {	
					
					System.out.print("Choose a Student: ");
					choice = scan.nextInt();
					if (choice<=i) {
						
						studentManager.stuNum=stunums[choice-1];
						break;
					}
					else {

						System.out.println("Invalid enterence. Try again.");
						continue;
					}
				}
				break;
			}
			else {

				System.out.println("This class does not exist.");
				System.out.print("This clases are exist: ");
				for (String cls : classes)
					System.out.print(cls + "\t");
				System.out.println();
				continue;
			}
		}
		try {
			
			BufferedReader frd = new BufferedReader(new FileReader(
				"C:\\schoolManagementSystem\\classes\\"+stuMgr.stuClass+"\\"+studentManager.stuNum+".txt"));
			ArrayList<String> notearr = new ArrayList<String>();
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

		System.out.println(stus[choice-1]+"'s notes:");
		for (String note : notes) {
			
			System.out.println(note);
		}
		return;
	}

	void addNotes(){

		
		String[] stuarr = null,clsarr = null,stuarrs = null;
		int cls,stu;
		try {

			BufferedReader frd = new BufferedReader(new FileReader("C:\\schoolManagementSystem\\teachers\\"+tchNum+".txt"));
			String line;
			while ((line = frd.readLine()) != null) {

				String[] spltarr = line.split(" ");
				
				if(spltarr[0].equals("Branch:")) {
					
					branch = spltarr[1];
					break;
				}
			}
			frd.close();
		} catch (IOException e) {

			System.out.println("File Not Found!!!");
		}
		System.out.println("\n\n***Adding Notes***");

		try {
			
			BufferedReader frd = new BufferedReader(new FileReader("C:\\schoolManagementSystem\\classes\\classList.txt"));
			String line;
			ArrayList<String> clslst = new ArrayList<String>();
			int i = 1;
			while ((line = frd.readLine()) != null) {

				clslst.add(line);
				System.out.printf("%d. %s\n",i++,line);
			}
			clsarr = clslst.toArray(new String[0]);
			frd.close();
		} catch (IOException e) {

			System.out.println("File Not Found!!!");
		}

		while (true) {
			
			System.out.print("Choose one of classes: ");
			cls = scan.nextInt();
			if(cls>clsarr.length) {

				System.out.println("Invalid enterence. Try again");
				continue;
			}
			else break;
		}
		try1:
		while (true) {
			
			System.out.print("1. Adding note for single student.\n2. Adding note for all student in class\n3. Exit\nChoose option: ");
			int chs = scan.nextInt();

			try {
						
				BufferedReader frd = new BufferedReader(new FileReader("C:\\schoolManagementSystem\\classes\\"+clsarr[cls-1]+"\\studentlist.txt"));
				String line;
				ArrayList<String> stulst = new ArrayList<String>();
				ArrayList<String> stus = new ArrayList<String>();
				while ((line = frd.readLine()) != null) {

					stus.add(line);
					String[] splt = line.split(" ");
					stulst.add(splt[0]);
				}
				stuarrs = stus.toArray(new String[0]);
				stuarr = stulst.toArray(new String[0]);
				frd.close();
			} catch (Exception e) {

				System.out.println("File Not Found!!!");
			}
			int i = 1;

			switch (chs) {

				case 1:
					
					for (String  student: stuarrs) {
						
						System.out.printf("%d. %s\n",i++,student);
					}
					while (true) {
			
						System.out.print("Choose one of student: ");
						stu = scan.nextInt();
						if(stu>stuarr.length) {
			
							System.out.println("Invalid enterence. Try again");
							continue;
						}
						else break;
					}

					try {
						
						PrintWriter fwrite = new PrintWriter(new BufferedWriter(new FileWriter("C:\\schoolManagementSystem\\classes\\"+clsarr[cls-1]+"\\"+stuarr[stu-1]+".txt",true)));
						int grd1,grd2;

						while (true) {

							System.out.print("Write first grade: ");
							grd1 = scan.nextInt();
							if(grd1>100||grd1<0) System.out.println("Invalid entrence. Try again.");
							else break;
						}
						
						while (true) {

							System.out.print("Write second grade: ");
							grd2 = scan.nextInt();
							if(grd2>100||grd2<0) System.out.println("Invalid entrence. Try again.");
							else break;
							
						}

						fwrite.print(branch+": "+grd1+" "+grd2+"\n");
						fwrite.close();
					} catch (Exception e) {

						System.out.println("File Not Found!!!");
					}
					
					System.out.println("Completed.");
					continue try1;
				case 2:

					for (String student : stuarr) {
						
						System.out.println("For: "+stuarrs[i-1]+"\n");
						i++;
						try {
						
							PrintWriter fwrite = new PrintWriter(new BufferedWriter(new FileWriter("C:\\schoolManagementSystem\\classes\\"+clsarr[cls-1]+"\\"+student+".txt",true)));
							int grd1,grd2;
	
							while (true) {
	
								System.out.print("Write first grade: ");
								grd1 = scan.nextInt();
								if(grd1>100||grd1<0) System.out.println("Invalid entrence. Try again.");
								else break;
							}
							
							while (true) {
	
								System.out.print("Write second grade: ");
								grd2 = scan.nextInt();
								if(grd2>100||grd2<0) System.out.println("Invalid entrence. Try again.");
								else break;
								
							}
	
							fwrite.print(branch+": "+grd1+" "+grd2+"\n");
							fwrite.close();
						} catch (Exception e) {
	
							System.out.println("File Not Found!!!");
						}
						System.out.println();
					}
					System.out.println("Completed.");
					continue try1;
				case 3:
					return;
				default:
					
					System.out.println("Invalid enterence. Try again.");
					continue try1;
			}
		}
	}
}
