package SistemATM;

import java.text.NumberFormat;
import java.util.Random;
import java.util.Scanner;

public class Bilingual {
    static Scanner input = new Scanner(System.in);
    static String[][] dataNasabah = {
            {"01", "112233", "Annisa", "1122", "Nganjuk", "1000000", "Verified", "085123456"},
            {"02", "334455", "Iga", "3344", "Dampit", "5000000", "Verified", "081123456"},
            {"03", "556677", "Firman", "4455", "Blitar", "3500000", "Verified", "087123456"},
            {"04", "889911", "Bayu", "6677", "Jombang", "3000000", "Verified", "08912345"},
            {"05", "123456", "Novita", "8899", "Watugong", "10000000", "Verified", "088123456"}
    };
    static String[][] datapdam = {
            {"Kota Malang", "MLG1234", "100000"},
            {"Kab. Malang", "MLGK123", "120000"},
            {"Surabaya", "SBY1234", "140000"},
            {"Sidoarjo", "SDJ1234", "125000"},
    };
    static boolean login = false, masuk = false;
    static int[] pulsa = {22500, 27500, 52500, 102500, 502500, 1002500};
    static String[] operator = {"Indosat", "XL", "Telkomsel", "Axis"};
    static int riw = 0, hasil = 0;
    static String[] riwayat = new String[10];
    static int jumlahLogin = 0;
    static String pilih;
    static Random random = new Random();
    static void login() {
        System.out.println("    ==================================================================");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    |                     WELCOME TO ATM BANKNO                      |");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    ==================================================================");
        System.out.println();

        //pengecekan verified/diblokir
        if (hasil != -1 && dataNasabah[hasil][6].equals("Disabled")) {
            System.out.println("    =======================================================");
            System.out.println("    -------------------------------------------------------");
            System.out.print("      [   Status of your account is  " + dataNasabah[hasil][6]);
            String inputPin = input.next();
            System.out.println("    -------------------------------------------------------");
            System.out.println("    =======================================================");
            System.exit(0);
        }
        //pengecekan login
        while (jumlahLogin <= 3) {
            System.out.println("    =======================================================");
            System.out.println("    -------------------------------------------------------");
            System.out.print("      [   Enter your PIN : ");
            String inputPin = input.next();
            System.out.println("    -------------------------------------------------------");
            System.out.println("    =======================================================");
            System.out.println();

            //pengecekan kesesuaian PIN login
            for (int i = 0; i < dataNasabah.length; i++) {
                if (inputPin.equals(dataNasabah[i][3])) {
                    login = true;
                    hasil = i;
                }
            }
            if (login) {
                menu();
            } else {
                System.out.println("    =======================================================");
                System.out.println("    |-----------------------------------------------------|");
                System.out.println("    |          (!) You entered the wrong PIN (!)          |");
                System.out.println("    |                Please Re-enter Again.               |");
                System.out.println("    |-----------------------------------------------------|");
                System.out.println("    =======================================================");
                jumlahLogin++;

                if (jumlahLogin >= 3) {
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("    =======================================================");
                    System.out.println("    |-----------------------------------------------------|");
                    System.out.println("    |        (!) You failed more than 3 times (!)         |");
                    System.out.println("    |           Your account has been disabled.           |");
                    System.out.println("    |-----------------------------------------------------|");
                    System.out.println("    =======================================================");
                    dataNasabah[hasil][6] = "Disabled";
                    break;
                }
            }
        }
    }

    public static void menu() {
        do {
            System.out.println("    =======================================================");
            System.out.println("    -------------------------------------------------------");
            System.out.println("""
                    [       Choose the menu below :
                    [       1. Customer data
                    [       2. Balance check
                    [       3. Cash Withdraw
                    [       4. Cash Deposit
                    [       5. Balance transfer
                    [       6. Transaction history
                    [       7. Other payments
                    [       8. Account settings
                    [       0. Exit
                    """);
            System.out.print("\tMenu selected : ");
            int menu = input.nextInt();

            switch (menu) {
                case 1:
                    dataNasabah();
                    break;
                case 2:
                    cekSaldo();
                    break;
                case 3:
                    tarikTunai();
                    break;
                case 4:
                    setorTunai();
                    break;
                case 5:
                    transferSaldo();
                    break;
                case 6:
                    riwayatTransaksi();
                    break;
                case 7:
                    pembayaranLain();
                    break;
                case 8:
                    pengaturanAkun();
                    break;
                case 0:
                    keluarMenu();
                    break;
                default:
                    System.out.println("    ======================================================");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    |           THE MENU YOU SELECTED IS INVALID         |");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    ======================================================");
            }
            if (random.nextInt(10) == 0) {
                System.out.println("    ======================================================");
                System.out.println("    |----------------------------------------------------|");
                System.out.println("    |        you have been logged out automatically      |");
                System.out.println("    |                 for security reasons.              |");
                System.out.println("    |----------------------------------------------------|");
                System.out.println("    ======================================================");
                login = false;
            }
        } while (login);
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |            THANKS FOR USING THIS ATM :).           |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
        System.exit(0);
    }

    private static void pengaturanAkun() {
        System.out.println("    ==================================================================");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    |                        ACCOUNT SETTING                         |");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    ==================================================================");
        System.out.println();

        System.out.println("""
                [       Choose the menu below :
                [       1. Change PIN
                [       2. Change phone number
                [       0. Back to Main Menu
                """);
        System.out.print("\t[   Menu selected : ");
        int menu = input.nextInt();

        switch (menu) {
            case 1:
                System.out.print("      [   Enter your old PIN : ");
                String inputPin = input.next();

                int index = 0;
                if (inputPin.equals(dataNasabah[hasil][3])) {
                    for (int i = 0; i < dataNasabah.length; i++) {
                        if (dataNasabah[i][3].equals(inputPin)) {
                            index = 1;
                            System.out.print("      [   Enter your phone number : ");
                            String notlp = input.next();
                            if (notlp.equals(dataNasabah[i][7])) {
                                if (dataNasabah[i][7].equals(notlp)) {
                                    System.out.print("      [   Enter your new PIN : ");
                                    String newpin = input.next();
                                    dataNasabah[i][3] = newpin;
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    | ~ ~ ~ ~ ~ ~ ~ ~ CHANGE PIN SUCCESS ~ ~ ~ ~ ~ ~ ~ ~ |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    menu();
                                }
                            } else {
                                System.out.println("    ========================================================");
                                System.out.println("    |------------------------------------------------------|");
                                System.out.println("    | ~ ~ ~ ~ ~ ~ ~ ~ INVALID PHONE NUMBER ~ ~ ~ ~ ~ ~ ~ ~ |");
                                System.out.println("    |------------------------------------------------------|");
                                System.out.println("    ========================================================");
                                pengaturanAkun();
                            }
                        }
                    }
                } else {
                    System.out.println("    ======================================================");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ WRONG PIN (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    ======================================================");
                }
                break;
            case 2:
                System.out.print("      [   Enter your PIN : ");
                String inputPin1 = input.next();


                int index1 = 0;
                if (inputPin1.equals(dataNasabah[hasil][3])) {
                    for (int i = 0; i < dataNasabah.length; i++) {
                        if (dataNasabah[i][3].equals(inputPin1)) {
                            System.out.print("      [   Enter your old phone number : ");
                            String noTelp = input.next();
                            if (noTelp.equals(dataNasabah[i][7])) {
                                if (dataNasabah[i][7].equals(noTelp)) {
                                    System.out.print("      [   Enter your new phone number : ");
                                    String newTelp = input.next();
                                    System.out.print("      [   Confirm the new phone number : ");
                                    String konfirmTelp = input.next();
                                    if (konfirmTelp.equals(newTelp)) {
                                        dataNasabah[i][7] = newTelp;
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |~ ~ ~ ~ ~ ~ CHANGE PHONE NUMBER SUCCESS ~ ~ ~ ~ ~ ~ |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |~ ~ ~ ~ ~ INVALID PHONE NUMBER CONFIRMATION ~ ~ ~ ~ |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                        pengaturanAkun();
                                    }
                                }
                            } else {
                                System.out.println("    ======================================================");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    | ~ ~ ~ ~ ~ ~ ~ ~ INVALID PHONE NUMBER ~ ~ ~ ~ ~ ~ ~ |");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    ======================================================");
                                pengaturanAkun();
                            }
                        }
                    }
                } else {
                    System.out.println("    ======================================================");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ WRONG PIN (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    ======================================================");
                }
                break;
            case 0:
                menu();
                break;
            default:
                System.out.println("    ======================================================");
                System.out.println("    |----------------------------------------------------|");
                System.out.println("    |           THE MENU YOU SELECTED IS INVALID         |");
                System.out.println("    |----------------------------------------------------|");
                System.out.println("    ======================================================");
                break;
        }
    }

    private static void riwayatTransaksi() {
        System.out.println("    ==================================================================");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    |                      TRANSACTION HISTORY                       |");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    ==================================================================");
        System.out.println();
        System.out.println("    ==================================================================");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    |                  \\YOUR RECENT TRANSACTION//                   |");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    ==================================================================");
        System.out.println();

        System.out.println("Transaction history : ");
        for (int i = 0; i < riw; i++) {
            System.out.println(riwayat[i]);
        }
        System.out.print("\n    [   Want to continue the transaction y/n: ");
        pilih = input.next();
        if (pilih.equalsIgnoreCase("y")) {
            menu();
        } else {
            System.out.println("    ======================================================");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    |             THANKS FOR USING THIS ATM :).          |");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    ======================================================");
            System.exit(0);
        }
    }

    static void dataNasabah() {
        System.out.println("=====================================================================================");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("  ___      _     _____     _       _  _     _     ___     _     ___     _     _  _ \r\n" + //
                " |   \\    /_\\   |_   _|   /_\\     | \\| |   /_\\   / __|   /_\\   | _ )   /_\\   | || |\r\n" + //
                " | |) |  / _ \\    | |    / _ \\    | .` |  / _ \\  \\__ \\  / _ \\  | _ \\  / _ \\  | __ |\r\n" + //
                " |___/  /_/ \\_\\   |_|   /_/ \\_\\   |_|\\_| /_/ \\_\\ |___/ /_/ \\_\\ |___/ /_/ \\_\\ |_||_|\r\n" + //
                "                                                                                   ");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("=====================================================================================");
        System.out.print("      [   Enter your PIN : ");
        String inPin = input.next();

        int index = 0;
        if (inPin.equals(dataNasabah[hasil][3])) {
            for (int i = 0; i < dataNasabah.length; i++) {
                if (dataNasabah[i][3].equals(inPin)) {
                    index = 1;
                    System.out.println("    =======================================================");
                    System.out.println("    [  _________________________________________________\t]");
                    System.out.println("    [\t|        \tCUSTOMER DATA    \t\t|\t]");
                    System.out.printf("    [\t|  ID\t\t\t: %s\t\t\t|\t]\n", dataNasabah[i][0]);
                    System.out.printf("    [\t|  Name\t\t\t: %s\t\t|\t]\n", dataNasabah[i][2]);
                    System.out.printf("    [\t|  Account number\t: %s\t\t|\t]\n", dataNasabah[i][1]);
                    System.out.printf("    [\t|  Address\t\t: %s\t\t|\t]\n", dataNasabah[i][4]);
                    System.out.printf("    [\t|  Phone number\t\t: %s\t\t|\t]\n", dataNasabah[i][7]);
                    System.out.println("    [  -------------------------------------------------\t]");
                    System.out.printf("    [\t|  Account status\t: %s\t\t|\t]\n", dataNasabah[i][6]);
                    System.out.println("    [  -------------------------------------------------\t]");
                    System.out.println("    =======================================================");

                    System.out.println();
                    System.out.print("\n    [   Want to continue the transaction y/n: ");
                    pilih = input.next();
                    if (pilih.equalsIgnoreCase("y")) {
                        menu();
                    } else {
                        keluarMenu();
                    }
                }
            }
        } else {
            System.out.println("    =======================================================");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    |           (!) You entered the wrong PIN (!)         |");
            System.out.println("    |                Please Re-enter Again.               |");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    =======================================================");
            dataNasabah();
        }
    }

    static void cekSaldo() {
        System.out.println("    ==================================================================");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    |                        BALANCE CHECK                           |");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    ==================================================================");
        System.out.println();
        System.out.print("      [   Enter your PIN : ");
        String inPin = input.next();

        int index = 0;
        if (inPin.equals(dataNasabah[hasil][3])) {
            for (int i = 0; i < dataNasabah.length; i++) {
                if (dataNasabah[i][3].equals(inPin)) {
                    index = 1;

                    NumberFormat format = NumberFormat.getCurrencyInstance();
                    String balance = format.format(Double.parseDouble(dataNasabah[i][5]));
                    System.out.println();
                    System.out.println("    =======================================================");
                    System.out.println("    [  _________________________________________________\t]");
                    System.out.println("    [\t|        \tBALANCE CHECK    \t\t|\t]");
                    System.out.printf("    [\t|  Name\t\t\t: %s\t\t|\t\t]\n", dataNasabah[i][2]);
                    System.out.printf("    [\t|  Remaining balance\t\t: %s \t|\t]\n", balance);
                    System.out.println("    [  -------------------------------------------------\t]");
                    System.out.println("    =======================================================");
                    System.out.println();
                    System.out.print("\n    [   Want to continue the transaction y/n: ");
                    pilih = input.next();
                    if (pilih.equalsIgnoreCase("y")) {
                        menu();
                    } else {
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |             THANKS FOR USING THIS ATM :).          |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                        break;
                    }
                }
            }
        } else {
            System.out.println("    =======================================================");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    |          (!) You entered the wrong PIN (!)          |");
            System.out.println("    |              Please Re-enter Again.             |");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    =======================================================");
            cekSaldo();
        }
    }

    static void tarikTunai() {
        System.out.println("    ==================================================================");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    |                         CASH WITHDRAW                          |");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    ==================================================================");
        System.out.println();
        System.out.println("    =======================================================");
        System.out.println("    -------------------------------------------------------");
        System.out.print("      [   Enter the balance you want to withdraw : Rp. ");
        double nominalTarik = input.nextDouble();
        double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
        System.out.println("    -------------------------------------------------------");
        System.out.println("    =======================================================");

        if (nominalTarik > 0 && nominalTarik <= sisaSaldo) {
            sisaSaldo -= nominalTarik;
            dataNasabah[hasil][5] = String.valueOf(sisaSaldo);

            System.out.println("    ======================================================");
            System.out.println("    [  _________________________________________________\t]");
            System.out.println("    [\t|             \tCASH WITHDRAW       \t\t|\t]");
            System.out.printf("    [\t|  Name\t\t\t: %s\t\t|\t\t]\n", dataNasabah[hasil][2]);
            System.out.printf("    [\t|  Amount of balance you want to withdraw : Rp. %.2f\t|\t]\n", nominalTarik);
            System.out.println("    [  -------------------------------------------------\t]");
            System.out.println("    =======================================================");
            System.out.println();
            System.out.printf("\n    [   Confirm cash withdrawal amounting to Rp. %.2f? y/n: ", nominalTarik);
            pilih = input.next();
            if (pilih.equalsIgnoreCase("y")) {
                System.out.print("      [   Enter your PIN to continue the transaction : ");
                String inPin = input.next();

                int index = 0;
                if (inPin.equals(dataNasabah[hasil][3])) {
                    for (int i = 0; i < dataNasabah.length; i++) {
                        if (dataNasabah[i][3].equals(inPin)) {
                            index = 1;
                            System.out.println();
                            System.out.println("    ======================================================");
                            System.out.println("    ------------------------------------------------------");
                            System.out.println("    ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSACTION SUCCESS ~ ~ ~ ~ ~ ~ ~ ~ ");
                            System.out.println("    ------------------------------------------------------");
                            System.out.println("    ======================================================");
                            System.out.println("    ======================================================");
                            System.out.println("    [  _________________________________________________\t]");
                            System.out.println("    [\t|             \tCASH WITHDRAW       \t\t|\t]");
                            System.out.printf("    [\t|  Name\t\t\t: %s\t\t|\t\t]\n", dataNasabah[index][2]);
                            System.out.printf("    [\t|  Remaining balance\t\t: Rp. %s\t|\t]\n", dataNasabah[index][5]);
                            System.out.println("    [  -------------------------------------------------\t]");
                            System.out.println("    =======================================================");
                            System.out.println();
                            riwayat[riw] = String.format("Has made a cash withdrawal amounting to Rp. %.2f", nominalTarik);
                            riw++;
                            System.out.print("      [   Want to continue the transaction y/n: ");
                            pilih = input.next();
                            if (pilih.equalsIgnoreCase("y")) {
                                menu();
                            } else {
                                System.out.println("    ======================================================");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    |            THANKS FOR USING THIS ATM :).           |");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    ======================================================");
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("    =======================================================");
                    System.out.println("    |-----------------------------------------------------|");
                    System.out.println("    |          (!) You entered the wrong PIN (!)          |");
                    System.out.println("    |                Please Re-enter Again.               |");
                    System.out.println("    |-----------------------------------------------------|");
                    System.out.println("    =======================================================");
                    tarikTunai();
                }
            }
        } else {
            System.out.println("    ======================================================");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    |     NOT ENOUGH BALANCE TO MAKE THIS TRANSACTION.   |");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    ======================================================");
            System.out.println();
            tarikTunai();
        }
    }

    static void keluarMenu() {
        System.out.print("      [   Are you sure to exit y/n: ");
        pilih = input.next();
        if (pilih.equalsIgnoreCase("y")) {
            System.out.println("    ======================================================");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    |            THANKS FOR USING THIS ATM :).           |");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    ======================================================");

            System.exit(0);
        } else {
            menu();
        }
    }

    static void setorTunai() {
        System.out.println("    ==================================================================");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    |                          CASH DEPOSIT                          |");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    ==================================================================");
        System.out.println();
        System.out.println("    =======================================================");
        System.out.println("    -------------------------------------------------------");
        System.out.print("      [   Enter the balance you want to deposit : Rp. ");
        double nominalSetor = input.nextDouble();
        double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
        System.out.println("    -------------------------------------------------------");
        System.out.println("    =======================================================");

        if (nominalSetor <= 5000000) {
            sisaSaldo += nominalSetor;
            dataNasabah[hasil][5] = String.valueOf(sisaSaldo);

            System.out.println("    =======================================================");
            System.out.println("    [  _________________________________________________\t]");
            System.out.println("    [\t|             \tCASH DEPOSIT       \t\t|\t]");
            System.out.printf("    [\t|  Name\t\t\t: %s\t\t|\t\t]\n", dataNasabah[hasil][2]);
            System.out.printf("    [\t|  Deposit amount\t\t: Rp. %s\t|\t]\n", nominalSetor);
            System.out.println("    [  -------------------------------------------------\t]");
            System.out.println("    =======================================================");
            System.out.println();
            System.out.printf("\n    [   Confirm cash deposit amounting to Rp. %.2f? y/n: ", nominalSetor);
            pilih = input.next();
            if (pilih.equalsIgnoreCase("y")) {
                System.out.print("      [   Enter your PIN to continue the transaction : ");
                String inPin = input.next();

                int index = 0;
                if (inPin.equals(dataNasabah[hasil][3])) {
                    for (int i = 0; i < dataNasabah.length; i++) {
                        if (dataNasabah[i][3].equals(inPin)) {
                            index = 1;
                            System.out.println("    ======================================================");
                            System.out.println("    ------------------------------------------------------");
                            System.out.println("    ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSACTION SUCCESS ~ ~ ~ ~ ~ ~ ~ ~ ");
                            System.out.println("    ------------------------------------------------------");
                            System.out.println("    ======================================================");
                            System.out.println("    =======================================================");
                            System.out.println("    [  _________________________________________________\t]");
                            System.out.println("    [\t|             \tCASH DEPOSIT       \t\t|\t]");
                            System.out.printf("    [\t|  Name\t\t\t: %s\t\t|\t\t]\n", dataNasabah[i][2]);
                            System.out.printf("    [\t|  Remaining balance\t\t: Rp. %.2f\t|\t]\n", sisaSaldo);
                            System.out.println("    [  -------------------------------------------------\t]");
                            System.out.println("    =======================================================");
                            System.out.println();
                            riwayat[riw] = String.format("Has made cash deposit amounting to Rp. %.2f", nominalSetor);
                            riw++;
                            System.out.print("\n    [   Want to continue the transaction y/n: ");
                            pilih = input.next();
                            if (pilih.equalsIgnoreCase("y")) {
                                menu();
                            } else {
                                System.out.println("    ======================================================");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    |            THANKS FOR USING THIS ATM :).           |");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    ======================================================");
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("    =======================================================");
                    System.out.println("    |-----------------------------------------------------|");
                    System.out.println("    |          (!) You entered the wrong PIN (!)          |");
                    System.out.println("    |                Please Re-enter Again.               |");
                    System.out.println("    |-----------------------------------------------------|");
                    System.out.println("    =======================================================");
                    setorTunai();
                }
            }
        } else {
            System.out.println("    =======================================================");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    |(!) You exceed the nominal amount of cash deposit (!)|");
            System.out.println("    |                   Please try again.                 |");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    =======================================================");
            setorTunai();
        }
    }

    static void transferSaldo() {
        System.out.println("    ==================================================================");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    |                       BALANCE TRANSFER                         |");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    ==================================================================");
        System.out.println();
        System.out.print("  [   Enter your account number : ");
        String inRek = input.next();

        int index = 0;
        if (inRek.equals(dataNasabah[hasil][1])) {
            for (int i = 0; i < dataNasabah.length; i++) {
                if (dataNasabah[i][1].equals(inRek)) {
                    index = i;
                    System.out.print("  [   Enter account number destination : ");
                    String rekTujuan = input.next();
                    for (int j = 0; j < dataNasabah.length; j++) {
                        if (rekTujuan.equals(dataNasabah[j][1])) {
                            index = 1;
                            System.out.print("  [   Enter transfer amount : Rp. ");
                            double nomTF = input.nextDouble();
                            double saldoUser = Double.parseDouble(dataNasabah[hasil][5]);

                            if (nomTF > 0 && nomTF <= saldoUser) {
                                saldoUser -= nomTF;
                                dataNasabah[index][5] = String.valueOf(saldoUser);
                                System.out.println("    =======================================================");
                                System.out.println("    [  _________________________________________________\t]");
                                System.out.println("    [\t|        \tBALANCE TRANSFER    \t\t|\t]");
                                System.out.printf("    [\t|  Account destination\t\t: %s\t|\t]\n", rekTujuan);
                                System.out.printf("    [\t|  Amount transfer\t\t: Rp. %.2f\t|\t]\n", nomTF);
                                System.out.println("    [  -------------------------------------------------\t]");
                                System.out.println("    =======================================================");
                                System.out.println();
                                System.out.print("Confirm balance transfer to account " + rekTujuan + " with amount Rp." + nomTF + "(y/n) : ");
                                pilih = input.next();
                                if (pilih.equals("y")) {
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSACTION SUCCESS ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println("    ========================================================");
                                    System.out.println("    [  ________________________________________________\t\t]");
                                    System.out.println("    [\t|           \tPAYMENT DETAILS \t\t|\t]");
                                    System.out.printf("    [\t|  Account destination\t\t: %s\t|\t]\n", rekTujuan);
                                    System.out.printf("    [\t|  Name\t\t\t\t: %s\t|\t]\n", dataNasabah[j][2]);
                                    System.out.printf("    [\t|  Amount transfer\t\t: Rp. %.2f\t|\t]\n", nomTF);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Your remaining balance\t\t: Rp. %.2f\t|\t]\n", saldoUser);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    riwayat[riw] = String.format("Has made balance transfer to account number %s with amount Rp. %.2f", rekTujuan, nomTF);
                                    riw++;
                                    System.out.print("\n    [   Want to continue the transaction y/n: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        menu();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |            THANKS FOR USING THIS ATM :).           |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                        break;
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |               TRANSACTION CANCELLED                |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    menu();
                                }
                            } else {
                                System.out.println("    ======================================================");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    |     NOT ENOUGH BALANCE TO MAKE THIS TRANSACTION.   |");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    ======================================================");
                                System.out.println();
                                menu();
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("    ======================================================");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    |               INVALID ACCOUNT NUMBER               |");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    ======================================================");
            System.out.println();
            menu();
        }
    }

    static void pembayaranLain() {
        System.out.println("    ==================================================================");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    |                        OTHER PAYMENTS                          |");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    ==================================================================");
        System.out.println();
        System.out.println("""
                [       Choose the menu below :
                [       1. Credit Purchase
                [       2. Electricity Payment
                [       3. PDAM Payment
                [       4. Single tuiton fee payment
                [       0. Back to main menu
                """);
        System.out.print("\tMenu selected : ");
        int menu = input.nextInt();

        switch (menu) {
            case 1 -> beliPulsa();
            case 2 -> bayarListrik();
            case 3 -> bayarAir();
            case 4 -> bayarUkt();
            case 0 -> menu();
        }
    }

    static void beliPulsa() {
        System.out.println("    ==================================================================");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    |                        CREDIT PURCHASE                         |");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    ==================================================================");
        System.out.println();
        System.out.println("""
                [       Choose a mobile operator below :
                [       1. Indosat
                [       2. XL
                [       3. Telkomsel
                [       4. Axis 
                [       0. Back to main menu
                """);
        System.out.print("\tOperator selected : ");
        int operator = input.nextInt();

        switch (operator) {
            case 1 -> indosat();
            case 2 -> xl();
            case 3 -> telkomsel();
            case 4 -> axis();
            case 0 -> menu();
        }
    }

    static void indosat() {
        System.out.println("    ==================================================================");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    |                            INDOSAT                             |");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    ==================================================================");
        System.out.println();
        System.out.print("Enter your phone number : 085-");
        String nomTelp = input.next();
        System.out.print("\nIs the phone number you entered correct? y/n: ");
        pilih = input.next();
        if (pilih.equals("y")) {
            masuk = true;
        } else {
            indosat();
        }
        System.out.println("""
                [       Choose amount below :
                [       1. 20.000
                [       2. 25.000
                [       3. 50.000
                [       4. 100.000
                [       5. 500.000
                [       6. 1.000.000
                [       0. Back to main menu
                """);
        System.out.print("\tAmount selected : ");
        int nomPul = input.nextInt();

        switch (nomPul) {
            case 1:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tPAYMENT DETAILS   \t\t|\t]");
                System.out.printf("    [\t|  Mobile operator\t\t: %s\t|\t]\n", operator[0]);
                System.out.printf("    [\t|  Phone number\t\t: %s\t|\t]\n", "085-" + nomTelp);
                System.out.printf("    [\t|  Total credit payment\t\t: Rp. %d\t|\t]\n", pulsa[0]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    ========================================================");
                System.out.println();
                System.out.println("Total payment above includes admin fee Rp. 2500");
                System.out.printf(" Confirm transaction to phone number 085-%s with amount Rp. %d? y/n: ", nomTelp, pulsa[0]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Enter your PIN to continue the transaction : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[0];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSACTION SUCCESS ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    ======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tPAYMENTS DETAILS \t\t|\t]");
                                    System.out.printf("    [\t|  Mobile operator\t\t: %s\t|\t]\n", operator[0]);
                                    System.out.printf("    [\t|  Phone number\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total credit payment\t\t: Rp. %d\t|\t]\n", pulsa[0]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Your remaining balance\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Has made a credit purchase %s with amount Rp. %s", operator[0], pulsa[0]);
                                    riw++;
                                    System.out.print("\n    [   Want to continue the transaction y/n: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        menu();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |             THANKS FOR USING THIS ATM :).          |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                        break;
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |     NOT ENOUGH BALANCE TO MAKE THIS TRANSACTION.   |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    indosat();

                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ WRONG PIN (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                        indosat();
                    }
                } else {
                    indosat();
                }
                break;
            case 2:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tPAYMENT DETAILS    \t\t|\t]");
                System.out.printf("    [\t|  Mobile operator\t\t: %s\t|\t]\n", operator[0]);
                System.out.printf("    [\t|  Phone number\t\t: %s\t|\t]\n", "085-" + nomTelp);
                System.out.printf("    [\t|  Total credit payment\t\t: Rp. %d\t|\t]\n", pulsa[1]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total payment above includes admin fee Rp. 2500");
                System.out.printf(" Confirm transaction to phone number 085-%s with amount Rp. %d? y/n: ", nomTelp, pulsa[1]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Enter your PIN to continue the transaction : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[1];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSACTION SUCCESS ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    ======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tPAYMENT DETAILS \t\t|\t]");
                                    System.out.printf("    [\t|  Mobile operator\t\t: %s\t|\t]\n", operator[0]);
                                    System.out.printf("    [\t|  Phone number\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total credit payment\t\t: Rp. %d\t|\t]\n", pulsa[1]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Your remaining balance\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Has made a credit purchase %s with amount Rp. %s", operator[0], pulsa[1]);
                                    riw++;
                                    System.out.print("\n    [   Want to continue the transaction y/n: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        menu();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |           THANKS FOR USING THIS ATM :).            |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                        break;
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |     NOT ENOUGH BALANCE TO MAKE THIS TRANSACTION.   |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    indosat();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ WRONG PIN (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                        indosat();
                    }
                } else {
                    indosat();
                }
                break;
            case 3:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tPAYMENT DETAILS    \t\t|\t]");
                System.out.printf("    [\t|  Mobile operator\t\t: %s\t|\t]\n", operator[0]);
                System.out.printf("    [\t|  Phone number\t\t: %s\t|\t]\n", "085-" + nomTelp);
                System.out.printf("    [\t|  Total credit payment\t\t: Rp. %d\t|\t]\n", pulsa[2]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total payment above includes admin fee Rp. 2500");
                System.out.printf(" Confirm transaction to phone number 085-%s with amount Rp. %d? y/n: ", nomTelp, pulsa[2]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Enter your PIN to continue the transaction : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[2];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSACTION SUCCESS ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tPAYMENT DETAILS \t\t|\t]");
                                    System.out.printf("    [\t|  Mobile operator\t\t: %s\t|\t]\n", operator[0]);
                                    System.out.printf("    [\t|  Phone number\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total credit payment\t\t: Rp. %d\t|\t]\n", pulsa[2]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Your remaining balance\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Has made a credit purchase %s with amount Rp. %s", operator[0], pulsa[2]);
                                    riw++;
                                    System.out.print("\n    [  Want to continue the transaction y/n: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        menu();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |             THANKS FOR USING THIS ATM :).          |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                        break;
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |     NOT ENOUGH BALANCE TO MAKE THIS TRANSACTION.   |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    indosat();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ WRONG PIN (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                        indosat();
                    }
                } else {
                    indosat();
                }
                break;
            case 4:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[3]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[3]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[3];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    ======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[3]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[0], pulsa[3]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        menu();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                        break;
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    indosat();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                        indosat();
                    }
                } else {
                    indosat();
                }
                break;
            case 5:
                System.out.println("    ======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[4]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    ======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[4]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[4];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    ======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[4]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[0], pulsa[4]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        menu();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                        break;
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    indosat();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                        indosat();
                    }
                } else {
                    indosat();
                }
                break;
            case 6:
                System.out.println("    ======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[5]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    ======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[5]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[5];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    ======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[5]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[0], pulsa[5]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        menu();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                        break;
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    indosat();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                        indosat();
                    }
                } else {
                    indosat();
                }
                break;
        }
    }

    static void xl() {
        System.out.println("=============================");
        System.out.println("|---------------------------|");
        System.out.println("|            XL             |");
        System.out.println("|---------------------------|");
        System.out.println("=============================");
        System.out.println();
        System.out.print("Masukkan nomor telepon anda : 087-");
        String nomTelp = input.next();
        System.out.print("\nApakah nomor telepon yang anda masukkan benar? y/t: ");
        pilih = input.next();
        if (pilih.equals("y")) {
            masuk = true;
        } else {
            xl();
        }
        System.out.println("""
                [       Silahkan Pilih nominal dibawah ini :
                [       1. 20.000
                [       2. 25.000
                [       3. 50.000
                [       4. 100.000
                [       5. 500.000
                [       6. 1.000.000
                [       0. Kembali ke Menu Utama
                """);
        System.out.print("\tNominal yang dipilih : ");
        int nomPul = input.nextInt();

        switch (nomPul) {
            case 1:
                System.out.println("    ======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[1]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[0]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    ======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[0]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[0];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    ======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[1]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[0]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[1], pulsa[0]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    xl();
                }
                break;
            case 2:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[1]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[1]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[1]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[1];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    =======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[1]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[1]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[1], pulsa[1]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    xl();
                }
                break;
            case 3:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[1]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[2]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[2]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[2];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    =======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[1]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[2]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[1], pulsa[2]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    xl();
                }
                break;
            case 4:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[1]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[3]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[3]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[3];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    =======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[1]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[3]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[1], pulsa[3]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    xl();
                }
                break;
            case 5:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[1]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[4]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[4]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[4];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    =======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[1]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[4]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[1], pulsa[4]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    xl();
                }
                break;
            case 6:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[1]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[5]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[5]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[5];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    =======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[1]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[5]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[1], pulsa[5]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    xl();
                }
                break;
        }
    }

    static void telkomsel() {
        System.out.println("    ==================================================================");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    |                           TELKOMSEL                            |");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    ==================================================================");
        System.out.println();
        System.out.print("Masukkan nomor telepon anda : 081-");
        String nomTelp = input.next();
        System.out.print("\nApakah nomor telepon yang anda masukkan benar? y/t: ");
        String pilih = input.next();
        boolean masuk = false;
        if (pilih.equals("y")) {
            masuk = true;
        } else {
            telkomsel();
        }
        System.out.println("""
                [       Silahkan Pilih nominal dibawah ini :
                [       1. 20.000
                [       2. 25.000
                [       3. 50.000
                [       4. 100.000
                [       5. 500.000
                [       6. 1.000.000
                [       0. Kembali ke Menu Utama
                """);
        System.out.print("\tNominal yang dipilih : ");
        int nomPul = input.nextInt();

        switch (nomPul) {
            case 1:
                System.out.println("    ======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[2]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "081-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[0]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    ======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[0]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[0];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    ======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[2]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "081-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[0]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[2], pulsa[0]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    telkomsel();
                }
                break;
            case 2:
                System.out.println("    ======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[2]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "081-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[1]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    ======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[1]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[1];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    ======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[2]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "081-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[1]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[2], pulsa[1]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    telkomsel();
                }
                break;
            case 3:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[2]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "081-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[2]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[2]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[2];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    =======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[2]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "081-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[2]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[2], pulsa[2]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    telkomsel();
                }
                break;
            case 4:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[2]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "081-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[3]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[3]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[3];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    =======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[2]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "081-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[3]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[2], pulsa[3]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    telkomsel();
                }
                break;
            case 5:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[2]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "081-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[4]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[4]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[3];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    =======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[2]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "081-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[4]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[2], pulsa[4]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    telkomsel();
                }
                break;
            case 6:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[2]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "081-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[5]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[5]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[3];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    =======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[2]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "081-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[5]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[2], pulsa[5]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    telkomsel();
                }
                break;
        }
    }

    static void axis() {
        System.out.println("==========================================");
        System.out.println("|----------------------------------------|");
        System.out.println("|                 AXIS                   |");
        System.out.println("|----------------------------------------|");
        System.out.println("==========================================");
        System.out.println();
        System.out.print("Masukkan nomor telepon anda : 083-");
        String nomTelp = input.next();
        System.out.print("\nApakah nomor telepon yang anda masukkan benar? y/t: ");
        String pilih = input.next();
        boolean masuk = false;
        if (pilih.equals("y")) {
            masuk = true;
        } else {
            axis();
        }
        System.out.println("""
                [       Silahkan Pilih nominal dibawah ini :
                [       1. 20.000
                [       2. 25.000
                [       3. 50.000
                [       4. 100.000
                [       5. 500.000
                [       6. 1.000.000
                [       0. Kembali ke Menu Utama
                """);
        System.out.print("\tNominal yang dipilih : ");
        int nomPul = input.nextInt();

        switch (nomPul) {
            case 1:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBAYARAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t\t|\t]\n", operator[3]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "083-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[0]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 083-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[0]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[0];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    =======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t\t|\t]\n", operator[3]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "083-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[0]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[3], pulsa[0]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    axis();
                }
                break;
            case 2:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBAYARAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t\t|\t]\n", operator[3]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "083-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[1]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 083-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[1]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[1];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t\t|\t]\n", operator[3]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "083-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[1]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[3], pulsa[1]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    axis();
                }
                break;
            case 3:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBAYARAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t\t|\t]\n", operator[3]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "083-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[2]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 083-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[2]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[2];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    =======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t\t|\t]\n", operator[3]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "083-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[2]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[3], pulsa[2]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    axis();
                }
                break;
            case 4:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBAYARAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t\t|\t]\n", operator[3]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "083-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[3]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[3]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[3];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    =======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t\t|\t]\n", operator[3]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "083-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[3]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[3], pulsa[3]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    axis();
                }
                break;
            case 5:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBAYARAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t\t|\t]\n", operator[3]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "083-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[4]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[4]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[3];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    =======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t\t|\t]\n", operator[3]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "083-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[4]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[3], pulsa[4]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        menu();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                        System.exit(0);
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    axis();
                }
                break;
            case 6:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBAYARAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t\t|\t]\n", operator[3]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "083-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[5]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin Rp. 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal Rp. %d? y/t: ", nomTelp, pulsa[5]);
                pilih = input.next();
                if (pilih.equals("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[3];
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    =======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t\t|\t]\n", operator[3]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "083-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[5]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[3], pulsa[5]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    } else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    }
                } else {
                    axis();
                }
                break;
        }
    }

    static void bayarListrik() {
        String[][] dataListrik = {
                {"qwerty0987", "100000"},
                {"zxcvbn1234", "125000"},
                {"poiuyt5678", "150000"},
        };
        System.out.println("    ==================================================================");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    |                      ELECTRICITY PAYMENT                       |");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    ==================================================================");
        System.out.println();
        System.out.print("      [   Masukkan ID pelanggan PLN : ");
        String inputPln = input.next();

        boolean valid = false;
        for (int i = 0; i < dataListrik.length; i++) {
            if (dataListrik[i][0].equals(inputPln)) {
                if (inputPln.equals(dataListrik[i][0])) {
                    valid = true;
                    if (valid) {
                        System.out.println("    =======================================================");
                        System.out.println("    [  _________________________________________________\t]");
                        System.out.println("    [\t|        \tPembayaran Listrik    \t\t|\t]");
                        System.out.printf("    [\t|  ID Pelanggan\t\t\t: %s\t\t|\t]\n", dataListrik[i][0]);
                        System.out.printf("    [\t|  Total tagihan\t\t: Rp. %s\t|\t]\n", dataListrik[i][1]);
                        System.out.println("    [  -------------------------------------------------\t]");
                        System.out.println("    =======================================================");
                        System.out.println();
                        System.out.printf(" Konfirmasi transaksi tagihan PLN dengan ID pelanggan %s sebesar Rp. %s? y/t: ", dataListrik[i][0], dataListrik[i][1]);
                        pilih = input.next();
                        if (pilih.equalsIgnoreCase("y")) {
                            System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                            String inPin = input.next();

                            if (inPin.equals(dataNasabah[hasil][3])) {
                                for (int j = 0; j < dataNasabah.length; j++) {
                                    if (dataNasabah[j][3].equals(inPin)) {
                                        double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                        double nomListrik = Double.parseDouble(dataListrik[i][1]);
                                        if (sisaSaldo >= nomListrik) {
                                            sisaSaldo -= nomListrik;
                                            dataNasabah[j][5] = String.valueOf(sisaSaldo);

                                            System.out.println("    ======================================================");
                                            System.out.println("    ------------------------------------------------------");
                                            System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                            System.out.println("    ------------------------------------------------------");
                                            System.out.println("    ======================================================");
                                            System.out.println();
                                            System.out.println("    =======================================================");
                                            System.out.println("    [  ________________________________________________\t]");
                                            System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                            System.out.printf("    [\t|  ID Pelanggan\t\t: %s\t\t|\t]\n", dataListrik[i][0]);
                                            System.out.printf("    [\t|  Total tagihan\t\t: Rp. %s\t|\t]\n", dataListrik[i][1]);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.println("    =======================================================");
                                            System.out.println();
                                            riwayat[riw] = String.format("Telah melakukan transaksi tagihan listrik dengan ID pelanggan %s sebesar Rp. %s", dataListrik[i][0], dataListrik[i][1]);
                                            riw++;
                                            System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                            pilih = input.next();
                                            if (pilih.equalsIgnoreCase("y")) {
                                                menu();
                                            } else {
                                                System.out.println("    ======================================================");
                                                System.out.println("    |----------------------------------------------------|");
                                                System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                                System.out.println("    |----------------------------------------------------|");
                                                System.out.println("    ======================================================");
                                                System.exit(0);
                                            }
                                        } else {
                                            System.out.println("    ======================================================");
                                            System.out.println("    |----------------------------------------------------|");
                                            System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                            System.out.println("    |----------------------------------------------------|");
                                            System.out.println("    ======================================================");
                                            bayarListrik();
                                        }

                                    }
                                }
                            } else {
                                System.out.println("    ======================================================");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    ======================================================");
                                bayarListrik();
                            }
                        }
                    }
                } else {
                    System.out.println("    ======================================================");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    |     (!) KODE YANG ANDA MASUKKAN TIDAK VALID (!)    |");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    ======================================================");
                    bayarListrik();
                }
            }
        }
    }

    static void bayarAir() {
        System.out.println("    ==================================================================");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    |                          PDAM PAYMENT                          |");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    ==================================================================");
        System.out.println();
        System.out.println("""
                [       Silahkan pilih wilayah dibawah ini :
                [       1. Kota Malang
                [       2. Kab. Malang
                [       3. Surabaya
                [       4. Sidoarjo
                [       0. Kembali ke Menu Utama
                """);
        System.out.print("\tMenu yang dipilih : ");
        int menu = input.nextInt();

        switch (menu) {
            case 1 -> malkot();
            case 2 -> kabmalang();
            case 3 -> surabaya();
            case 4 -> sidoarjo();
            case 0 -> menu();
        }
    }

    static void malkot() {
        System.out.println("-------------------------------------");
        System.out.print("      [   Masukkan ID pelanggan PDAM : ");
        String inputPdam = input.next();

        boolean valid = false;
        for (int i = 0; i < datapdam.length; i++) {
            if (datapdam[i][1].equals(inputPdam)) {
                if (inputPdam.equals(datapdam[i][1])) {
                    valid = true;
                    if (valid) {
                        System.out.println("    =======================================================");
                        System.out.println("    [  _________________________________________________\t]");
                        System.out.println("    [\t|        \tPembayaran PDAM           \t\t|\t]");
                        System.out.printf("    [\t|  ID Pelanggan\t\t\t: %s\t\t|\t]\n", datapdam[i][1]);
                        System.out.printf("    [\t|  Wilayah\t\t\t: %s\t\t|\t]\n", datapdam[0][0]);
                        System.out.printf("    [\t|  Total tagihan\t\t: Rp. %s\t|\t]\n", datapdam[i][2]);
                        System.out.println("    [  -------------------------------------------------\t]");
                        System.out.println("    =======================================================");
                        System.out.println();
                        System.out.printf(" Konfirmasi transaksi tagihan PDAM dengan ID pelanggan %s sebesar Rp. %s? y/t: ", datapdam[i][1], datapdam[i][2]);
                        pilih = input.next();
                        if (pilih.equalsIgnoreCase("y")) {
                            System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                            String inPin = input.next();

                            if (inPin.equals(dataNasabah[hasil][3])) {
                                for (int j = 0; j < dataNasabah.length; j++) {
                                    if (dataNasabah[j][3].equals(inPin)) {
                                        double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                        double nomPdam = Double.parseDouble(datapdam[i][2]);
                                        if (sisaSaldo >= nomPdam) {
                                            sisaSaldo -= nomPdam;
                                            dataNasabah[j][5] = String.valueOf(sisaSaldo);

                                            System.out.println("    ======================================================");
                                            System.out.println("    ------------------------------------------------------");
                                            System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                            System.out.println("    ------------------------------------------------------");
                                            System.out.println("    ======================================================");
                                            System.out.println();
                                            System.out.println("    =======================================================");
                                            System.out.println("    [  ________________________________________________\t]");
                                            System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                            System.out.printf("    [\t|  ID Pelanggan\t\t: %s\t\t|\t]\n", datapdam[i][1]);
                                            System.out.printf("    [\t|  Total tagihan\t\t: Rp. %s\t|\t]\n", datapdam[i][2]);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.println("    =======================================================");
                                            System.out.println();
                                            riwayat[riw] = String.format("Telah melakukan transaksi tagihan PDAM dengan ID pelanggan %s sebesar Rp. %s", datapdam[i][1], datapdam[i][2]);
                                            riw++;
                                            System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                            pilih = input.next();
                                            if (pilih.equalsIgnoreCase("y")) {
                                                menu();
                                            } else {
                                                System.out.println("    ======================================================");
                                                System.out.println("    |----------------------------------------------------|");
                                                System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                                System.out.println("    |----------------------------------------------------|");
                                                System.out.println("    ======================================================");
                                                System.exit(0);
                                            }
                                        } else {
                                            System.out.println("    ======================================================");
                                            System.out.println("    |----------------------------------------------------|");
                                            System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                            System.out.println("    |----------------------------------------------------|");
                                            System.out.println("    ======================================================");
                                            bayarAir();
                                        }
                                    }
                                }
                            } else {
                                System.out.println("    ======================================================");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    ======================================================");
                                bayarAir();
                            }
                        }
                    } else {
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |     (!) KODE YANG ANDA MASUKKAN TIDAK VALID (!)    |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                        bayarAir();
                    }
                } else {
                    System.out.println("    ======================================================");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    |     (!) KODE YANG ANDA MASUKKAN TIDAK VALID (!)    |");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    ======================================================");
                    bayarAir();
                }
            }
        }
    }
    static void kabmalang() {
        System.out.println("-------------------------------------");
        System.out.print("      [   Masukkan ID pelanggan PDAM : ");
        String inputPdam = input.next();

        boolean valid = false;
        for (int i = 0; i < datapdam.length; i++) {
            if (datapdam[i][1].equals(inputPdam)) {
                if (inputPdam.equals(datapdam[i][1])) {
                    valid = true;
                    if (valid) {
                        System.out.println("    =======================================================");
                        System.out.println("    [  _________________________________________________\t]");
                        System.out.println("    [\t|        \tPembayaran PDAM           \t\t|\t]");
                        System.out.printf("    [\t|  ID Pelanggan\t\t\t: %s\t\t|\t]\n", datapdam[i][1]);
                        System.out.printf("    [\t|  Wilayah\t\t\t: %s\t\t|\t]\n", datapdam[1][0]);
                        System.out.printf("    [\t|  Total tagihan\t\t: Rp. %s\t|\t]\n", datapdam[i][2]);
                        System.out.println("    [  -------------------------------------------------\t]");
                        System.out.println("    =======================================================");
                        System.out.println();
                        System.out.printf(" Konfirmasi transaksi tagihan PDAM dengan ID pelanggan %s sebesar Rp. %s? y/t: ", datapdam[i][1], datapdam[i][2]);
                        pilih = input.next();
                        if (pilih.equalsIgnoreCase("y")) {
                            System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                            String inPin = input.next();

                            if (inPin.equals(dataNasabah[hasil][3])) {
                                for (int j = 0; j < dataNasabah.length; j++) {
                                    if (dataNasabah[j][3].equals(inPin)) {
                                        double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                        double nomPdam = Double.parseDouble(datapdam[i][2]);
                                        if (sisaSaldo >= nomPdam) {
                                            sisaSaldo -= nomPdam;
                                            dataNasabah[j][5] = String.valueOf(sisaSaldo);

                                            System.out.println("    ======================================================");
                                            System.out.println("    ------------------------------------------------------");
                                            System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                            System.out.println("    ------------------------------------------------------");
                                            System.out.println("    ======================================================");
                                            System.out.println();
                                            System.out.println("    =======================================================");
                                            System.out.println("    [  ________________________________________________\t]");
                                            System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                            System.out.printf("    [\t|  ID Pelanggan\t\t: %s\t\t|\t]\n", datapdam[i][1]);
                                            System.out.printf("    [\t|  Total tagihan\t\t: Rp. %s\t|\t]\n", datapdam[i][2]);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.println("    =======================================================");
                                            System.out.println();
                                            riwayat[riw] = String.format("Telah melakukan transaksi tagihan PDAM dengan ID pelanggan %s sebesar Rp. %s", datapdam[i][1], datapdam[i][2]);
                                            riw++;
                                            System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                            pilih = input.next();
                                            if (pilih.equalsIgnoreCase("y")) {
                                                menu();
                                            } else {
                                                System.out.println("    ======================================================");
                                                System.out.println("    |----------------------------------------------------|");
                                                System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                                System.out.println("    |----------------------------------------------------|");
                                                System.out.println("    ======================================================");
                                                System.exit(0);
                                            }
                                        } else {
                                            System.out.println("    ======================================================");
                                            System.out.println("    |----------------------------------------------------|");
                                            System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                            System.out.println("    |----------------------------------------------------|");
                                            System.out.println("    ======================================================");
                                            bayarAir();
                                        }
                                    }
                                }
                            } else {
                                System.out.println("    ======================================================");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    ======================================================");
                                bayarAir();
                            }
                        }
                    } else {
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |     (!) KODE YANG ANDA MASUKKAN TIDAK VALID (!)    |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                        bayarAir();
                    }
                } else {
                    System.out.println("    ======================================================");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    |     (!) KODE YANG ANDA MASUKKAN TIDAK VALID (!)    |");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    ======================================================");
                    bayarAir();
                }
            }
        }
    }
    static void surabaya() {
        System.out.println("-------------------------------------");
        System.out.print("      [   Masukkan ID pelanggan PDAM : ");
        String inputPdam = input.next();

        boolean valid = false;
        for (int i = 0; i < datapdam.length; i++) {
            if (datapdam[i][1].equals(inputPdam)) {
                if (inputPdam.equals(datapdam[i][1])) {
                    valid = true;
                    if (valid) {
                        System.out.println("    =======================================================");
                        System.out.println("    [  _________________________________________________\t]");
                        System.out.println("    [\t|        \tPembayaran PDAM           \t\t|\t]");
                        System.out.printf("    [\t|  ID Pelanggan\t\t\t: %s\t\t|\t]\n", datapdam[i][1]);
                        System.out.printf("    [\t|  Wilayah\t\t\t: %s\t\t|\t]\n", datapdam[2][0]);
                        System.out.printf("    [\t|  Total tagihan\t\t: Rp. %s\t|\t]\n", datapdam[i][2]);
                        System.out.println("    [  -------------------------------------------------\t]");
                        System.out.println("    =======================================================");
                        System.out.println();
                        System.out.printf(" Konfirmasi transaksi tagihan PDAM dengan ID pelanggan %s sebesar Rp. %s? y/t: ", datapdam[i][1], datapdam[i][2]);
                        pilih = input.next();
                        if (pilih.equalsIgnoreCase("y")) {
                            System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                            String inPin = input.next();

                            if (inPin.equals(dataNasabah[hasil][3])) {
                                for (int j = 0; j < dataNasabah.length; j++) {
                                    if (dataNasabah[j][3].equals(inPin)) {
                                        double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                        double nomPdam = Double.parseDouble(datapdam[i][2]);
                                        if (sisaSaldo >= nomPdam) {
                                            sisaSaldo -= nomPdam;
                                            dataNasabah[j][5] = String.valueOf(sisaSaldo);

                                            System.out.println("    ======================================================");
                                            System.out.println("    ------------------------------------------------------");
                                            System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                            System.out.println("    ------------------------------------------------------");
                                            System.out.println("    ======================================================");
                                            System.out.println();
                                            System.out.println("    =======================================================");
                                            System.out.println("    [  ________________________________________________\t]");
                                            System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                            System.out.printf("    [\t|  ID Pelanggan\t\t: %s\t\t|\t]\n", datapdam[i][1]);
                                            System.out.printf("    [\t|  Total tagihan\t\t: Rp. %s\t|\t]\n", datapdam[i][2]);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.println("    =======================================================");
                                            System.out.println();
                                            riwayat[riw] = String.format("Telah melakukan transaksi tagihan PDAM dengan ID pelanggan %s sebesar Rp. %s", datapdam[i][1], datapdam[i][2]);
                                            riw++;
                                            System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                            pilih = input.next();
                                            if (pilih.equalsIgnoreCase("y")) {
                                                menu();
                                            } else {
                                                System.out.println("    ======================================================");
                                                System.out.println("    |----------------------------------------------------|");
                                                System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                                System.out.println("    |----------------------------------------------------|");
                                                System.out.println("    ======================================================");
                                                System.exit(0);
                                            }
                                        } else {
                                            System.out.println("    ======================================================");
                                            System.out.println("    |----------------------------------------------------|");
                                            System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                            System.out.println("    |----------------------------------------------------|");
                                            System.out.println("    ======================================================");
                                            bayarAir();
                                        }
                                    }
                                }
                            } else {
                                System.out.println("    ======================================================");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    ======================================================");
                                bayarAir();
                            }
                        }
                    } else {
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |     (!) KODE YANG ANDA MASUKKAN TIDAK VALID (!)    |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                        bayarAir();
                    }
                } else {
                    System.out.println("    ======================================================");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    |     (!) KODE YANG ANDA MASUKKAN TIDAK VALID (!)    |");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    ======================================================");
                    bayarAir();
                }
            }
        }
    }
    static void sidoarjo() {
        System.out.println("-------------------------------------");
        System.out.print("      [   Masukkan ID pelanggan PDAM : ");
        String inputPdam = input.next();

        boolean valid = false;
        for (int i = 0; i < datapdam.length; i++) {
            if (datapdam[i][1].equals(inputPdam)) {
                if (inputPdam.equals(datapdam[i][1])) {
                    valid = true;
                    if (valid) {
                        System.out.println("    =======================================================");
                        System.out.println("    [  _________________________________________________\t]");
                        System.out.println("    [\t|        \tPembayaran PDAM           \t\t|\t]");
                        System.out.printf("    [\t|  ID Pelanggan\t\t\t: %s\t\t|\t]\n", datapdam[i][1]);
                        System.out.printf("    [\t|  Wilayah\t\t\t: %s\t\t|\t]\n", datapdam[3][0]);
                        System.out.printf("    [\t|  Total tagihan\t\t: Rp. %s\t|\t]\n", datapdam[i][2]);
                        System.out.println("    [  -------------------------------------------------\t]");
                        System.out.println("    =======================================================");
                        System.out.println();
                        System.out.printf(" Konfirmasi transaksi tagihan PDAM dengan ID pelanggan %s sebesar Rp. %s? y/t: ", datapdam[i][1], datapdam[i][2]);
                        pilih = input.next();
                        if (pilih.equalsIgnoreCase("y")) {
                            System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                            String inPin = input.next();

                            if (inPin.equals(dataNasabah[hasil][3])) {
                                for (int j = 0; j < dataNasabah.length; j++) {
                                    if (dataNasabah[j][3].equals(inPin)) {
                                        double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                        double nomPdam = Double.parseDouble(datapdam[i][2]);
                                        if (sisaSaldo >= nomPdam) {
                                            sisaSaldo -= nomPdam;
                                            dataNasabah[j][5] = String.valueOf(sisaSaldo);

                                            System.out.println("    ======================================================");
                                            System.out.println("    ------------------------------------------------------");
                                            System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                            System.out.println("    ------------------------------------------------------");
                                            System.out.println("    ======================================================");
                                            System.out.println();
                                            System.out.println("    =======================================================");
                                            System.out.println("    [  ________________________________________________\t]");
                                            System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                            System.out.printf("    [\t|  ID Pelanggan\t\t: %s\t\t|\t]\n", datapdam[i][1]);
                                            System.out.printf("    [\t|  Total tagihan\t\t: Rp. %s\t|\t]\n", datapdam[i][2]);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.println("    =======================================================");
                                            System.out.println();
                                            riwayat[riw] = String.format("Telah melakukan transaksi tagihan PDAM dengan ID pelanggan %s sebesar Rp. %s", datapdam[i][1], datapdam[i][2]);
                                            riw++;
                                            System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                            pilih = input.next();
                                            if (pilih.equalsIgnoreCase("y")) {
                                                menu();
                                            } else {
                                                System.out.println("    ======================================================");
                                                System.out.println("    |----------------------------------------------------|");
                                                System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                                System.out.println("    |----------------------------------------------------|");
                                                System.out.println("    ======================================================");
                                                System.exit(0);
                                            }
                                        } else {
                                            System.out.println("    ======================================================");
                                            System.out.println("    |----------------------------------------------------|");
                                            System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                            System.out.println("    |----------------------------------------------------|");
                                            System.out.println("    ======================================================");
                                            bayarAir();
                                        }
                                    }
                                }
                            } else {
                                System.out.println("    ======================================================");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    ======================================================");
                                bayarAir();
                            }
                        }
                    } else {
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |     (!) KODE YANG ANDA MASUKKAN TIDAK VALID (!)    |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                        bayarAir();
                    }
                } else {
                    System.out.println("    ======================================================");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    |     (!) KODE YANG ANDA MASUKKAN TIDAK VALID (!)    |");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    ======================================================");
                    bayarAir();
                }
            }
        }
    }
    static void bayarUkt() {
        String[][] dataUniv = {
                {"1019", "Universitas Brawijaya", "232101050", "Firman Dzaki", "Ilmu Hukum", "7500000"},
                {"10136", "Politeknik Negeri Malang", "2341760083", "Iga Ramadana", "Sistem Informasi Bisnis", "5000000"},
        };
        System.out.println("    ==================================================================");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    |                   SINGLE TUITON FEE PAYMENT                    |");
        System.out.println("    |----------------------------------------------------------------|");
        System.out.println("    ==================================================================");
        System.out.println();
        System.out.print("      [   Masukkan kode Perguruan Tinggi : ");
        String inCode = input.next();

        boolean valid = false;
        for (int i = 0; i < dataUniv.length; i++) {
            if (dataUniv[i][0].equals(inCode)) {
                if (inCode.equals(dataUniv[i][0])) {
                    valid = true;
                    if (valid) {
                        System.out.println("    =======================================================");
                        System.out.println("    [  _________________________________________________\t]");
                        System.out.println("    [\t|        \tPembayaran UKT           \t\t|\t]");
                        System.out.printf("    [\t|  ID Perguruan Tinggi\t\t\t: %s\t\t|\t]\n", dataUniv[i][0]);
                        System.out.printf("    [\t|  Nama Perguruan TInggi\t\t: %s\t\t|\t]\n", dataUniv[i][1]);
                        System.out.println("    [  -------------------------------------------------\t]");
                        System.out.println("    =======================================================");
                        System.out.println();

                        System.out.print("  [       Masukkan Nomor Induk Mahasiswa : ");
                        String inNim = input.next();
                        for (int j = 0; j < dataUniv.length; j++) {
                            if (dataUniv[i][2].equals(inNim)) {
                                if (inNim.equals(dataUniv[i][2])) {
                                    System.out.println("    =======================================================");
                                    System.out.println("    [  _________________________________________________\t]");
                                    System.out.println("    [\t|        \tPembayaran UKT           \t\t|\t]");
                                    System.out.printf("    [\t|  ID Perguruan Tinggi\t\t\t: %s\t\t|\t]\n", dataUniv[i][0]);
                                    System.out.printf("    [\t|  Nama Perguruan TInggi\t\t: %s\t\t|\t]\n", dataUniv[i][1]);
                                    System.out.printf("    [\t|  Nama Mahasiswa\t\t\t: %s\t\t|\t]\n", dataUniv[i][3]);
                                    System.out.printf("    [\t|  Jurusan\t\t\t\t: %s\t\t|\t]\n", dataUniv[i][4]);
                                    System.out.printf("    [\t|  Nominal dibayarkan\t\t: %s\t\t|\t]\n", dataUniv[i][5]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    System.out.printf(" Konfirmasi transaksi pembayaran UKT dengan Nama Perguruan tinggi %s sebesar Rp. %s? y/t: ", dataUniv[i][1], dataUniv[i][5]);
                                    pilih = input.next();
                                    if (pilih.equalsIgnoreCase("y")) {
                                        System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                                        String inPin = input.next();

                                        if (inPin.equals(dataNasabah[hasil][3])) {
                                            for (int k = 0; k < dataNasabah.length; k++) {
                                                if (dataNasabah[k][3].equals(inPin)) {
                                                    double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                                    double nomUkt = Double.parseDouble(dataUniv[i][5]);

                                                    if (sisaSaldo >= nomUkt) {
                                                        sisaSaldo -= nomUkt;
                                                        dataNasabah[k][5] = String.valueOf(sisaSaldo);

                                                        System.out.println("    ======================================================");
                                                        System.out.println("    ------------------------------------------------------");
                                                        System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                                        System.out.println("    ------------------------------------------------------");
                                                        System.out.println("    ======================================================");
                                                        System.out.println();
                                                        System.out.println("    =======================================================");
                                                        System.out.println("    [  _________________________________________________\t]");
                                                        System.out.println("    [\t|        \tPembayaran UKT           \t\t|\t]");
                                                        System.out.printf("    [\t|  ID Perguruan Tinggi\t\t\t: %s\t\t|\t]\n", dataUniv[i][0]);
                                                        System.out.printf("    [\t|  Nama Perguruan TInggi\t\t: %s\t\t|\t]\n", dataUniv[i][1]);
                                                        System.out.printf("    [\t|  Nama Mahasiswa\t\t\t: %s\t\t|\t]\n", dataUniv[i][3]);
                                                        System.out.printf("    [\t|  Jurusan\t\t\t\t: %s\t\t|\t]\n", dataUniv[i][4]);
                                                        System.out.printf("    [\t|  Nominal dibayarkan\t\t: Rp. %s\t\t|\t]\n", nomUkt);
                                                        System.out.println("    [  -------------------------------------------------\t]");
                                                        System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                                        System.out.println("    [  -------------------------------------------------\t]");
                                                        System.out.println("    =======================================================");
                                                        System.out.println();
                                                        riwayat[riw] = String.format("Telah melakukan transaksi Pembayaran UKT dengan Nama Universitas %s sebesar Rp. %s", dataUniv[i][1], nomUkt);
                                                        riw++;
                                                        System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                                        pilih = input.next();
                                                        if (pilih.equalsIgnoreCase("y")) {
                                                            menu();
                                                        } else {
                                                            System.out.println("    ======================================================");
                                                            System.out.println("    |----------------------------------------------------|");
                                                            System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                                            System.out.println("    |----------------------------------------------------|");
                                                            System.out.println("    ======================================================");
                                                            System.exit(0);
                                                        }
                                                    } else {
                                                        System.out.println("    ======================================================");
                                                        System.out.println("    |----------------------------------------------------|");
                                                        System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                                        System.out.println("    |----------------------------------------------------|");
                                                        System.out.println("    ======================================================");
                                                        bayarUkt();
                                                    }
                                                }
                                            }
                                        } else {
                                            System.out.println("    ======================================================");
                                            System.out.println("    |----------------------------------------------------|");
                                            System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                                            System.out.println("    |----------------------------------------------------|");
                                            System.out.println("    ======================================================");
                                            bayarUkt();
                                        }
                                    }
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   (!) NIM YANG ANDA MASUKKAN TIDAK TERDAFTAR (!)   |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    bayarUkt();
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("    ======================================================");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    |   (!) KODE YANG ANDA MASUKKAN TIDAK TERDAFTAR (!)  |");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    ======================================================");
                    bayarUkt();
                }
            }
        }
    }
    public static void main(String[] args) {
        login();
    }
}
