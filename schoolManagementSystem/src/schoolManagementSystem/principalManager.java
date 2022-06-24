package schoolManagementSystem;

import java.io.*;
import java.util.*;

public class principalManager extends baseHuman {

	Scanner scan = new Scanner(System.in);
    String priNick;

    @Override
    public boolean enter() {

        boolean corr = false;
		try {

			BufferedReader fread = new BufferedReader(new FileReader(
					"C:\\schoolManagementSystem\\logins\\principalLogin.txt"));
			String line;

			System.out.print("Please enter Principal Nickname: ");
			String gNum = scan.next();
			System.out.print("Please enter Password: ");
			String pass = scan.next();

			while ((line = fread.readLine()) != null) {

				String[] sline = line.split(" ");
				priNick= sline[0];
				password = sline[1];
				if (gNum.equals(priNick) && pass.equals(password)) {

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

    public void createClass() {

		String[] classes = null;
		boolean exist = false;
		try {

			BufferedReader fread = new BufferedReader(new FileReader(
					"C:\\schoolManagementSystem\\classes\\classList.txt"));
			ArrayList<String> arrlst = new ArrayList<String>();
			String line;
			while ((line = fread.readLine()) != null)
				arrlst.add(line);
			classes = arrlst.toArray(new String[0]);
			fread.close();
		} catch (IOException e) {

			System.out.println("File Not Found!!!");
		}

		try {

			PrintWriter fwrite = new PrintWriter(new BufferedWriter(new FileWriter(
					"C:\\schoolManagementSystem\\classes\\classList.txt", true)));
			
			while (true) {
				System.out.print("Please enter class name(Example: 3-A): ");
				String cls = scan.next();
				for (String clss : classes) {

					if (cls.equals(clss)) {

						exist = true;
						break;
					} else
						continue;
				}

				if (!exist) {

					fwrite.println(cls);
					File fl = new File("C:\\schoolManagementSystem\\classes\\" + cls);
					fl.mkdir();
					File f2 = new File("C:\\schoolManagementSystem\\classes\\"+cls+"\\studentlist.txt");
					try {
						f2.createNewFile();
					} catch (IOException e) {
						System.out.println("Error Found. studentlist.txt");
					}
					System.out.println("Class created.");
					fwrite.close();
					
					return;
				} else {

					System.out.println("This class already exists.");
					exist = false;
					continue;
				}
			}

		} catch (IOException e) {

			System.out.println("File Not Found!!!");
		}
	}

	public void studentRegister() {

        studentManager stuMgr = new studentManager();
		
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		String[] logins = null;
		try {

			BufferedReader frd = new BufferedReader(
					new FileReader("C:\\schoolManagementSystem\\logins\\studentLogin.txt"));
			ArrayList<String> loglist = new ArrayList<String>();
			String line;
			while ((line = frd.readLine()) != null) {

				String[] spltarr = line.split(" ");
				loglist.add(spltarr[0]);
			}
			logins = loglist.toArray(new String[0]);
			frd.close();

		} catch (IOException e) {

			System.out.println("File Not Found!!!");
		}
		try {
			PrintWriter fwrite = new PrintWriter(new BufferedWriter(new FileWriter(
					"C:\\schoolManagementSystem\\logins\\studentLogin.txt", true)));
			System.out.println("\n\n***Registering Student***");
			while (true) {
				boolean numexist = false;
				System.out.print("Please enter student number: ");
				studentManager.stuNum = scan.next();
				for (String log : logins) {

					if (studentManager.stuNum.equals(log)) {
						numexist = true;
						break;
					} else
						continue;
				}
				if (!numexist) {

					fwrite.print(studentManager.stuNum + " ");
					break;
				}
				else {
					System.out.println("This number already used.");
					continue;
				}
			}
			System.out.print("Please enter password: ");
			password = scan.next();
			fwrite.print(password + " ");
			fwrite.close();
		} catch (IOException e) {

			System.out.println("File Not Found!!!");
		}
		try {

			String[] classes = null;
			boolean exist = false;
			try {

				BufferedReader fread = new BufferedReader(new FileReader(
						"C:\\schoolManagementSystem\\classes\\classList.txt"));
				ArrayList<String> arrlst = new ArrayList<String>();
				String line;
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
						try {

							PrintWriter fwrite = new PrintWriter(new BufferedWriter(new FileWriter(
								"C:\\schoolManagementSystem\\logins\\studentLogin.txt", true)));
							fwrite.print(stuMgr.stuClass+"\n");
							fwrite.close();
						} catch (Exception e) {

							System.out.println("File Not Found!!!");
						}
						break;
					} else
						continue;
				}
				if (exist) {

					PrintWriter fwrite = new PrintWriter(new BufferedWriter(new FileWriter(
							"C:\\schoolManagementSystem\\classes\\" + stuMgr.stuClass + "\\"
									+ studentManager.stuNum + ".txt",
							true)));
					while (true) {

						System.out.print("Please enter student ID: ");
						id = scan.next();
						if (id.length() == 11) {

							fwrite.print("ID: " + id + "\n");
							break;
						} else {

							System.out.println("ID should has exact 11 digits.");
							continue;
						}
					}
					System.out.print("Please enter student name: ");
					name = bfr.readLine();
					name = name.substring(0, 1).toUpperCase() + name.substring(1);
					fwrite.println("Name: " + name);
					System.out.print("Please enter student surname: ");
					surname = scan.next();
					surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
					fwrite.print("Surname: " + surname + "\n");
					while (true) {

						System.out.print("Please enter gender: ");
						gender = scan.next();
						if (gender.equals("male") || gender.equals("female")) {

							fwrite.print("Gender: " + gender + "\n");
							break;
						} else {

							System.out.println("Value is incorreect. It should be exact same as 'male' or 'female'.");
							continue;
						}
					}
					fwrite.close();
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
		} catch (IOException e) {

			System.out.println("File Not Found!!!");
		}

		try {

			PrintWriter fwrite = new PrintWriter(new BufferedWriter(new FileWriter(
				"C:\\schoolManagementSystem\\classes\\" + stuMgr.stuClass + "\\studentlist.txt",true)));
			fwrite.print(studentManager.stuNum+" "+name+" "+surname+"\n");
			fwrite.close();
		} catch (Exception e) {

			System.out.println("File Not Found!!!");
		}
		System.out.println("***Registration Complete***");
		return;
	}

	void teacherRegister(){

        teacherManager tchMgr = new teacherManager();
		
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		String[] logins = null;
		try {

			BufferedReader frd = new BufferedReader(
					new FileReader("C:\\schoolManagementSystem\\logins\\teacherLogin.txt"));
			ArrayList<String> loglist = new ArrayList<String>();
			String line;
			while ((line = frd.readLine()) != null) {

				String[] spltarr = line.split(" ");
				loglist.add(spltarr[0]);
			}
			logins = loglist.toArray(new String[0]);
			frd.close();

		} catch (IOException e) {

			System.out.println("File Not Found!!!");
		}

		try {
			
            
			PrintWriter fwrite = new PrintWriter(new BufferedWriter(
				new FileWriter("C:\\schoolManagementSystem\\logins\\teacherLogin.txt", true)));
			
			System.out.println("\n\n***Registrating Teacher***");
			while (true) {

				boolean numexist = false;
				System.out.print("Please enter teacher number: ");
				teacherManager.tchNum = scan.next();
				for (String log : logins) {

					if (teacherManager.tchNum.equals(log)) {
						numexist = true;
						break;
					} else
						continue;
				}
				if (!numexist) {

					fwrite.print((teacherManager.tchNum) + " ");
					break;
				}
				else {
					System.out.println("This number already used.");
					continue;
				}
			}
			System.out.print("Please enter password: ");
			password = scan.next();
			fwrite.print(password + "\n");
			fwrite.close();
		} catch (IOException e) {

			System.out.println("File Not Found!!!");
		}

		try {
			
			PrintWriter fwrite = new PrintWriter(new BufferedWriter(
				new FileWriter("C:\\schoolManagementSystem\\teachers\\"+teacherManager.tchNum+".txt", true)));
			
			while (true) {
		
				System.out.print("Please enter teacher ID: ");
				id = scan.next();
				if (id.length() == 11) {
			
					fwrite.print("ID: " + id + "\n");
					break;
				} else {
			
					System.out.println("ID should has exact 11 digits.");
					continue;
				}
			}
			System.out.print("Please enter teacher name: ");
			name = bfr.readLine();
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
			fwrite.println("Name: " + name);
			System.out.print("Please enter teacher surname: ");
			surname = scan.next();
			surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
			fwrite.print("Surname: " + surname + "\n");
			while (true) {
			
				System.out.print("Please enter gender: ");
				gender = scan.next();
				if (gender.equals("male") || gender.equals("female")) {
			
					fwrite.print("Gender: " + gender + "\n");
					break;
				} else {
					System.out.println("Value is incorreect. It should be exact same as 'male' or 'female'.");
					continue;
				}
			}
			System.out.print("Please enter teacher branch: ");
			tchMgr.branch = scan.next();
			tchMgr.branch = tchMgr.branch.substring(0, 1).toUpperCase() + tchMgr.branch.substring(1);
			fwrite.print("Branch: " + tchMgr.branch + "\n");
			fwrite.close();
		} catch (IOException e) {
			
			System.out.println("File Not Found!!!");
		}
		System.out.println("***Registration Complete***");
		return;
	}

    void principalRegister() {

        String[] logins = null;
        
        try {

			BufferedReader frd = new BufferedReader(
					new FileReader("C:\\schoolManagementSystem\\logins\\principalLogin.txt"));
			ArrayList<String> loglist = new ArrayList<String>();
			String line;
			while ((line = frd.readLine()) != null) {

				String[] spltarr = line.split(" ");
				loglist.add(spltarr[0]);
			}
			logins = loglist.toArray(new String[0]);
			frd.close();

		} catch (IOException e) {

			System.out.println("File Not Found!!!");
		}

		try {
			
            
			PrintWriter fwrite = new PrintWriter(new BufferedWriter(
				new FileWriter("C:\\schoolManagementSystem\\logins\\principalLogin.txt", true)));
			
			System.out.println("***Registrating principal***");
			while (true) {

				boolean nickexist = false;
				System.out.print("Please enter Principal nickname: ");
				priNick = scan.next();
				for (String log : logins) {

					if (priNick.equals(log)) {
						nickexist = true;
						break;
					} else
						continue;
				}
				if (!nickexist) {

					fwrite.print(priNick + " ");
					break;
				}
				else {
					System.out.println("This nickname already used.");
					continue;
				}
			}
			System.out.print("Please enter password: ");
			password = scan.next();
			fwrite.print(password + "\n");
			fwrite.close();
		} catch (IOException e) {

			System.out.println("File Not Found!!!");
		}
		System.out.println("***Registration Complete***");
		return;
    }
}
