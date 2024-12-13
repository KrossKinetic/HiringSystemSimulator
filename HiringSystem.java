/**
* The {@code HiringSystem} class provides a menu-driven interface for managing
* a hiring table of job applicants.
* It allows users to add, remove, retrieve, search, and backup applicant data.
* 
* @author Manas Singh 
*    e-mail: anshumaan.singh@stonybrook.edu
*    Stony Brook ID: 115426564
*    Recitation : R02
**/

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HiringSystem {
    public static void main(String[] args) {
        HiringTable table = new HiringTable();
        HiringTable backup = new HiringTable();

        Scanner input = new Scanner(System.in);
        boolean isDone = false;
        System.out.println("(A) Add Applicant\n(R) Remove Applicant\n(G) Get Applicant\n(P) Print List\n(RS) Refine Search\n(S) Size\n(B) Backup\n(CB) Compare Backup\n(RB) Revert Backup\n(Q) Quit\n");
        while (isDone == false) {
            System.out.print("Please enter a command: "); 
            String choice = input.nextLine();System.out.println();
            if (choice.equalsIgnoreCase("Q")){
                isDone = true;
                System.out.println("Quitting program..."); System.out.println();
            } else if (choice.equalsIgnoreCase("A")){
                System.out.print("Enter Applicant Name: ");
                String applicantName = input.nextLine(); System.out.println();
                System.out.print("Enter Applicant GPA: ");
                double applicantGPA;
                try {
                    applicantGPA = input.nextDouble(); 
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, restarting program... please enter a number and try again.");
                    input.nextLine();
                    continue;
                }
                System.out.println();
                input.nextLine(); 
                System.out.print("Enter Applicant College: ");
                String applicantCollege = input.nextLine(); System.out.println();
                String[] applicantComp = new String[HiringTable.MAX_COMPANIES];
                Arrays.fill(applicantComp, "");
                for (int i=0; i<HiringTable.MAX_COMPANIES; i++){
                    System.out.print("Enter up to " + (3-i) + " Companies: ");
                    String comp = input.nextLine();
                    if (comp.equalsIgnoreCase("")){
                        break;
                    }
                    applicantComp[i] = comp;
                }

                String[] applicantSkills = new String[HiringTable.MAX_SKILLS];
                Arrays.fill(applicantSkills, "");
                for (int i=0; i<HiringTable.MAX_COMPANIES; i++){
                    System.out.print("Enter up to " + (3-i) + " Skills: ");
                    String skill = input.nextLine();
                    if (skill.equalsIgnoreCase("")){
                        break;
                    }
                    applicantSkills[i] = skill;
                }

                Applicant newApplicant = new Applicant(applicantComp, applicantName, applicantGPA, applicantCollege, applicantSkills);
                try {
                    table.addApplicant(newApplicant);
                    System.out.println("Applicant "+ applicantName +" has been successfully added to the hiring system.");
                } catch (FullTableException e) {
                    System.out.println("Table is full");
                }
            } else if (choice.equalsIgnoreCase("P")){
                table.printApplicantTable();
            } else if (choice.equalsIgnoreCase("RS")){
                System.out.print("Enter Company to filter for: ");
                String company = input.nextLine(); System.out.println();
                if (company.equalsIgnoreCase("")){
                    company = null;
                }
                System.out.print("Enter Skill to filter for: ");
                String skill = input.nextLine(); System.out.println();
                if (skill.equalsIgnoreCase("")){
                    skill = null;
                }
                System.out.print("Enter College to filter for: ");
                String college = input.nextLine(); System.out.println();
                if (college.equalsIgnoreCase("")){
                    college = null;
                }
                System.out.print("Enter the minimum GPA to filter for: ");
                String gpa_temp = input.nextLine(); System.out.println();
                double gpa = 0;
                if (!gpa_temp.equalsIgnoreCase("")){
                    gpa = Double.parseDouble(gpa_temp);
                }
                HiringTable.refineSearch(table, company, skill, college, gpa);
            } else if (choice.equalsIgnoreCase("R")){
                System.out.print("Enter Applicant Name: ");
                String name = input.nextLine(); System.out.println();
                try {
                    table.removeApplicant(name);
                    System.out.println("Applicant " + name + " has been successfully removed from the hiring system.\r");
                } catch (ApplicantNotFoundException e) {
                    System.out.println("Applicant not found");
                }
            } else if(choice.equalsIgnoreCase("G")){
                System.out.print("Enter Applicant Name: ");
                String name = input.nextLine(); System.out.println();
                try {
                    Applicant applicant_temp = table.getApplicant(name);
                    System.out.println();
                    System.out.println("Applicant Name: " + applicant_temp.getApplicantName());
                    System.out.println("Applicant Applying From: " + applicant_temp.getCompanyNameAsString());
                    System.out.println("Applicant GPA: " + applicant_temp.getApplicantGPA());
                    System.out.println("Applicant College: " + applicant_temp.getApplicantCollege());
                    System.out.println("Applicant Skills: " + applicant_temp.getApplicantSkillsAsString());

                } catch (ApplicantNotFoundException e) {
                    System.out.println("Applicant not found");
                }
            } else if (choice.equalsIgnoreCase("S")){
                System.out.println("There are " + table.size() + " applicants in the hiring system.");
            } else if (choice.equalsIgnoreCase("B")){
                backup = (HiringTable) table.clone();
                System.out.println("Successfully created backup");
            } else if (choice.equalsIgnoreCase("CB")){
                if (backup.equals(table)){
                    System.out.println("Current list is the same as the backup copy.");
                } else {
                    System.out.println("Current list is not the same as the backup copy.");
                }
            } else if (choice.equalsIgnoreCase("RB")){
                table = backup;
                System.out.println("Successfully reverted to the backup copy.");
            } else {
                System.out.println("Invalid input");
            }
        }
        input.close();
    }
}