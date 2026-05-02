import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Product> Products = new ArrayList<>();

        System.out.println("        ____  ____   ___  ____  _   _  ____ _____   __  __    _    _   _    _    ____ _____ ____        \n" +
                "  _____|  _ \\|  _ \\ / _ \\|  _ \\| | | |/ ___|_   _| |  \\/  |  / \\  | \\ | |  / \\  / ___| ____|  _ \\ _____ \n" +
                " |_____| |_) | |_) | | | | | | | | | | |     | |   | |\\/| | / _ \\ |  \\| | / _ \\| |  _|  _| | |_) |_____|\n" +
                " |_____|  __/|  _ <| |_| | |_| | |_| | |___  | |   | |  | |/ ___ \\| |\\  |/ ___ \\ |_| | |___|  _ <|_____|\n" +
                "       |_|   |_| \\_\\\\___/|____/ \\___/ \\____| |_|   |_|  |_/_/   \\_\\_| \\_/_/   \\_\\____|_____|_| \\_\\      \n" +
                "                                                                                                        ");

        String Prompt;
        boolean found;

        while (true) {
            System.out.print(":");
            Prompt = scan.nextLine();

            if (Prompt.equals("help")) {
                System.out.println("+----------------------+");
                System.out.println("|      Help  Menu      |");
                System.out.println("+----------------------+");
                System.out.println("| help: get help       |");
                System.out.println("| log: log a listing   |");
                System.out.println("| sold: log a sale     |");
                System.out.println("| add: Add Quantity    |");
                System.out.println("| ls: list products    |");
                System.out.println("+----------------------+");
                System.out.println();
            } else if (Prompt.equals("quit")) {
                break;
            } else if (Prompt.equals("log")) {
                while (true) {
                    System.out.print("Product Name:");
                    String name = scan.nextLine();

                    found = false;
                    for (int i = 0; i < Products.size(); i++) {
                        if (Products.get(i).getName().equals(name)) {
                            System.out.println("Product already exists!");
                            found = true;
                        }
                    }
                    if (found) {
                        found = false;
                        break;
                    }

                    System.out.print("Product Cost:");
                    String temp = scan.nextLine();
                    double cost = 0;
                    try {
                        Double.parseDouble(temp);
                        cost = Double.parseDouble(temp);
                    } catch (NumberFormatException e) {
                        System.out.println("invalid cost value! (double)");
                        break;
                    }

                    System.out.print("Sell Price:");
                    temp = scan.nextLine();
                    double Sell = 0;
                    try {
                        Double.parseDouble(temp);
                        Sell = Double.parseDouble(temp);
                    } catch (NumberFormatException e) {
                        System.out.println("invalid Sell value! (double)");
                        break;
                    }

                    System.out.print("Date Listed:");
                    temp = scan.nextLine();
                    int M = 0;
                    int D = 0;
                    int Y = 0;
                    if (temp.matches("\\d{2}/\\d{2}/\\d{4}")) {
                        M = Integer.parseInt(temp.split("/")[0]);
                        D = Integer.parseInt(temp.split("/")[1]);
                        Y = Integer.parseInt(temp.split("/")[2]);
                    } else {
                        System.out.println("invalid date format! (#/##/####)");
                        break;
                    }

                    System.out.print("quantity:");
                    temp = scan.nextLine();
                    int amount = 0;
                    try {
                        Integer.parseInt(temp);
                        amount = Integer.parseInt(temp);
                    } catch (NumberFormatException e) {
                        System.out.println("invalid quantity value! (int>0)");
                        break;
                    }

                    if (amount < 1) {
                        System.out.println("invalid quantity value! (int>0)");
                        break;
                    }

                    Product p = new Product(name, cost, Sell, M, D, Y);
                    Products.add(p);

                    p.AddStock(amount - 1);

                    System.out.println("Product Successfully Added");
                    System.out.println("-Added " + name + "-");
                    break;
                }
            } else if (Prompt.equals("ls")) {
                for (int i = 0; i < Products.size(); i++) {
                    System.out.println(Products.get(i));
                }
                System.out.println("+----------------------------+");
            } else if (Prompt.equals("sold")) {
                System.out.println("-PRODUCT-LIST-");
                for (Product product : Products) {
                    System.out.println(product);
                }
                System.out.println("+----------------------------+");
                System.out.println();
                while (true) {
                    System.out.print("Product Name:");
                    String name = scan.nextLine();

                    String temp;
                    System.out.print("Date Sold:");
                    temp = scan.nextLine();
                    int M = 0;
                    int D = 0;
                    int Y = 0;
                    if (temp.matches("\\d{2}/\\d{2}/\\d{4}")) {
                        M = Integer.parseInt(temp.split("/")[0]);
                        D = Integer.parseInt(temp.split("/")[1]);
                        Y = Integer.parseInt(temp.split("/")[2]);
                    } else {
                        System.out.println("invalid date format! (#/##/####)");
                        break;
                    }

                    for (int i = 0; i < Products.size(); i++) {
                        if (Products.get(i).getName().equals(name)) {
                            Products.get(i).setDaySold(D);
                            Products.get(i).setMonthSold(M);
                            Products.get(i).setYearSold(Y);

                            Products.get(i).setEfficiency();
                            break;
                        }
                    }
                    System.out.println("-Item Successfully Updated-");
                    break;
                }
            } else if (Prompt.equals("add")) {
                System.out.println("-PRODUCT-LIST-");
                for (Product product : Products) {
                    System.out.println(product);
                }
                System.out.println("+----------------------------+");
                System.out.println();

                while (true) {
                    System.out.print("Product Name:");
                    String name = scan.nextLine();

                    System.out.println("Amount to add:");
                    String amt = scan.nextLine();

                    try {
                        Integer.parseInt(amt);
                        for (int i = 0; i < Products.size(); i++) {
                            if (Products.get(i).getName().equals(name)) {
                                Products.get(i).AddStock(Integer.parseInt(amt));
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("invalid amount value! (int)");
                        break;
                    }


                    System.out.println("-Item Successfully Updated-");
                    break;
                }
            }
            else {
                System.out.println("Invalid command! (Type 'help' for info)");
                System.out.println();
            }
            System.out.println();

        }
    }
}
