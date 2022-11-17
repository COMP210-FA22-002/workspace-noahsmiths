package assn07;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();

        // your code below
        String masterInput = "";
        String input = "";

        while (!passwordManager.checkMasterPassword(masterInput)) {
            System.out.println("Enter Master Password");
            masterInput = scanner.nextLine();

            while (!input.equals("Exit") && passwordManager.checkMasterPassword(masterInput)) {
                input = scanner.nextLine();

                String websiteName = "";
                String password = "";
                int passwordLength = 0;

                switch(input) {
                    case "New password":
                        websiteName = scanner.nextLine();
                        password = scanner.nextLine();

                        passwordManager.put(websiteName, password);

                        System.out.println("New password added");
                        break;
                    case "Get password":
                        websiteName = scanner.nextLine();
                        password = passwordManager.get(websiteName);

                        if (password == null) {
                            System.out.println("Account does not exist");
                        } else {
                            System.out.println(password);
                        }

                        break;
                    case "Delete account":
                        websiteName = scanner.nextLine();
                        password = passwordManager.remove(websiteName);

                        if (password == null) {
                            System.out.println("Account does not exist");
                        } else {
                            System.out.println("Account deleted");
                        }

                        break;
                    case "Check duplicate password":
                        password = scanner.nextLine();
                        
                        List<String> duplicateList = passwordManager.checkDuplicate(password);
                        
                        if (duplicateList.size() == 0) {
                            System.out.println("No account uses that password");
                        } else {
                            System.out.println("Websites using that password:");

                            for (String site : duplicateList) {
                                System.out.println(site);
                            }
                        }

                        break;
                    case "Get accounts":
                        Set<String> accounts = passwordManager.keySet();

                        System.out.println("Your accounts:");

                        for (String account : accounts) {
                            System.out.println(account);
                        }
                        break;
                    case "Generate random password":
                        passwordLength = scanner.nextInt();
                        password = passwordManager.generateRandomPassword(passwordLength);

                        System.out.println(password);
                        scanner.nextLine();
                        break;
                    case "Exit":
                        masterInput = "";
                        break;
                    default:
                        System.out.println("Command not found");
                        break;
                }
            }
        }
    }
}
