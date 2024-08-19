package User_DataB;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Ne Etmek Isteyirsiniz? \n" +
                    "1. User Siyahisi \n" +
                    "2. User Silmek \n" +
                    "3. User Elave etmek \n" +
                    "4. User Tapmaq \n" +
                    "5. User Deyisiklik \n" +
                    "6. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    var user = Function.getUsersList();
                    System.out.println(user);
//                    Function.getUsersList().forEach(System.out::println);
                    break;
                case 2:
                    int id = Function.getNum("id daxil edin");
                    Function.deleteUserById(id);
                    break;
                case 3:
                    String name = Function.getStr("ad daxil edin");
                    String surname = Function.getStr("soyad daxil edin");
                    Integer age = Integer.valueOf(Function.getNum("yas daxil edin"));
                    Function.insertUser(name, surname, age);
                    break;
                case 4:
                    id = Function.getNum("id daxil edin");
                    System.out.println(Function.selectUserById(id));
                    break;
                case 5:
                    id = Function.getNum("id daxil edin");
                    System.out.println(Function.selectUserById(id));
                    String ad = Function.getStr("yeni ad daxil edin");
                    String soyad = Function.getStr("yeni soyad daxil edin");
                    Integer yas = Function.getNum("yeni yas daxil edin");
                    Function.updateUserById(id, ad, soyad, yas);
                    break;
                case 6:
                    Function.closeConnection();
                    System.exit(0);
                default:
                    System.out.println("Wrong choice");
            }
        }
    }
}
