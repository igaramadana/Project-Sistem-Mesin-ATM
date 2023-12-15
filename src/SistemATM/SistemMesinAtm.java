package SistemATM;

import java.text.NumberFormat;
import java.util.Random;
import java.util.Scanner;
import SistemATM.Bilingual;
import java.util.Date;
import java.text.SimpleDateFormat;
public class SistemMesinAtm {
    static Scanner input = new Scanner(System.in);
    static String[][] dataNasabah = {
            {"01", "112233", "Annisa", "1122", "Nganjuk", "1000000", "Verified", "085123456"},
            {"02", "334455", "Iga", "3344", "Dampit", "5000000", "Verified", "081123456"},
            {"03", "556677", "Firman", "4455", "Blitar", "3500000", "Verified", "087123456"},
            {"04", "889911", "Bayu", "6677", "Jombang", "3000000", "Verified", "08912345"},
            {"05", "123456", "Novita", "8899", "Watugong", "10000000", "Verified", "088123456"},
            {"06", "990088", "Naufal", "0099", "Sawojajar", "500000", "Disabled", "0812938219"}
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
    static String red = "\u001b[31m";
    static String green = "\u001b[32m";
    static String reset = "\u001b[0m";
    static void login() {
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |            SELAMAT DATANG DI ATM BANK-NO           |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
        System.out.println();

        //pengecekan login
        while (jumlahLogin <= 3) {
            System.out.println("    ======================================================");
            System.out.println("    ------------------------------------------------------");
            System.out.print("      [   Masukkan PIN anda : ");
            String inputPin = input.next();
            System.out.println("    ------------------------------------------------------");
            System.out.println("    ======================================================");
            System.out.println();

            //pengecekan kesesuaian PIN login
            for (int i = 0; i < dataNasabah.length; i++) {
                if (inputPin.equals(dataNasabah[i][3])) {
                    login = true;
                    hasil = i;
                }
            }
            if (login) {
                //pengecekan verified/diblokir
                if (dataNasabah[hasil][6].equals("Disabled")) {
                    System.out.println(red+"    ======================================================");
                    System.out.println("    ------------------------------------------------------");
                    System.out.print("      [   Status akun anda " + dataNasabah[hasil][6]);
                    System.out.println("\n      [   Silahkan menuju Bank terdekat (!)");
                    System.out.println("    ------------------------------------------------------");
                    System.out.println("    ======================================================"+reset);
                    System.exit(0);
                }
                menu();
            } else {
                System.out.println(red+"    =======================================================");
                System.out.println("    |-----------------------------------------------------|");
                System.out.println("    |       (!) Anda memasukkan PIN yang salah (!)        |");
                System.out.println("    |              Silahkan Masukkan Kembali.             |");
                System.out.println("    |-----------------------------------------------------|");
                System.out.println("    ======================================================="+reset);
                jumlahLogin++;

                if (jumlahLogin >= 3) {
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println(red+"    =======================================================");
                    System.out.println("    |-----------------------------------------------------|");
                    System.out.println("    |     (!) Anda telah gagal lebih dari 3 kali (!)      |");
                    System.out.println("    |              Akun anda telah diblokir.              |");
                    System.out.println("    |-----------------------------------------------------|");
                    System.out.println("    ======================================================="+reset);
                    dataNasabah[hasil][6] = "Disabled";
                    break;
                }
            }
        }
    }
    public static void menu() {
        do {
            System.out.println("    =====================================================");
            System.out.println("    -----------------------------------------------------");
            System.out.println("""
                        [       Silahkan pilih menu dibawah ini :
                        [       1. Data Nasabah
                        [       2. Cek Saldo
                        [       3. Tarik Tunai
                        [       4. Setor Tunai
                        [       5. Transfer Saldo
                        [       6. Riwayat Transaksi
                        [       7. Pembayaran Lain
                        [       8. Pengaturan Akun
                        [       0. Keluar
                    """);
            System.out.print("    [       Menu yang dipilih : ");
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
                    System.out.println(red+"    ======================================================");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    |           MENU YANG ANDA PILIH TIDAK VALID         |");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    ======================================================"+reset);
            }
        } while (login);
        System.out.println(green+"    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================"+reset);
        System.exit(0);
    }
    private static void pengaturanAkun() {
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |                  PENGATURAN AKUN                   |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
        System.out.println();

        System.out.println("""
                [       Silahkan pilih menu dibawah ini :
                [       1. Ubah PIN
                [       2. Ubah nomor telepon
                [       0. Kembali ke Menu Utama
                """);
        System.out.print("\t[   Menu yang dipilih : ");
        int menu = input.nextInt();

        switch (menu) {
            case 1:
                System.out.print("      [   Masukkan PIN lama anda : ");
                String inputPin = input.next();

                int index = 0;
                if (inputPin.equals(dataNasabah[hasil][3])) {
                    for (int i = 0; i < dataNasabah.length; i++) {
                        if (dataNasabah[i][3].equals(inputPin)) {
                            index = 1;
                            System.out.print("      [   Masukkan nomor telepon anda : ");
                            String notlp = input.next();
                            if (notlp.equals(dataNasabah[i][7])) {
                                if (dataNasabah[i][7].equals(notlp)) {
                                    System.out.print("      [   Masukkan PIN baru anda : ");
                                    String newpin = input.next();
                                    dataNasabah[i][3] = newpin;
                                    System.out.println(green+"    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ UBAH PIN BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================"+reset);
                                    menu();
                                }
                            } else {
                                System.out.println(red+"    ======================================================");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    |~ ~ ~ ~ ~ ~ ~ NOMOR TELEPON TIDAK VALID ~ ~ ~ ~ ~ ~ |");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    ======================================================"+reset);
                                pengaturanAkun();
                            }
                        }
                    }
                } else {
                    System.out.println(red+"    ======================================================");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    ======================================================"+reset);
                }
                break;
            case 2:
                System.out.print("      [   Masukkan PIN anda : ");
                String inputPin1 = input.next();

                int index1 = 0;
                if (inputPin1.equals(dataNasabah[hasil][3])) {
                    for (int i = 0; i < dataNasabah.length; i++) {
                        if (dataNasabah[i][3].equals(inputPin1)) {
                            System.out.print("      [   Masukkan nomor telepon lama anda : ");
                            String noTelp = input.next();
                            if (noTelp.equals(dataNasabah[i][7])) {
                                if (dataNasabah[i][7].equals(noTelp)) {
                                    System.out.print("      [   Masukkan nomor telepon baru : ");
                                    String newTelp = input.next();
                                    System.out.print("      [   Konfirmasi nomor telepon baru : ");
                                    String konfirmTelp = input.next();
                                    if (konfirmTelp.equals(newTelp)) {
                                        dataNasabah[i][7] = newTelp;
                                        System.out.println(green+"    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |~ ~ ~ ~ ~ ~ UBAH NOMOR TELEPON BERHASIL ~ ~ ~ ~ ~ ~ |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================"+reset);
                                    } else {
                                        System.out.println(red+"    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |~ ~ ~ ~ ~ ~ KONFIRMASI TELEPON TIDAK VALID  ~ ~ ~ ~ |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================"+reset);
                                        pengaturanAkun();
                                    }
                                }
                            } else {
                                System.out.println(red+"    ======================================================");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    |~ ~ ~ ~ ~ ~ ~ NOMOR TELEPON TIDAK VALID ~ ~ ~ ~ ~ ~ |");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    ======================================================"+reset);
                                pengaturanAkun();
                            }
                        }
                    }
                } else {
                    System.out.println(red+"    ======================================================");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    ======================================================"+reset);
                }
                break;
            case 0:
                menu();
                break;
            default:
                System.out.println(red+"    ======================================================");
                System.out.println("    |----------------------------------------------------|");
                System.out.println("    |           MENU YANG ANDA PILIH TIDAK VALID         |");
                System.out.println("    |----------------------------------------------------|");
                System.out.println("    ======================================================"+reset);
                break;
        }
    }
    static void riwayatTransaksi() {
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |                 RIWAYAT TRANSAKSI                  |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
        System.out.println();
        System.out.println("    [       Riwayat Transaksi : ");

        SimpleDateFormat formatTgl = new SimpleDateFormat(("dd-MM-yyyy HH;mm;ss"));
        String tanggal = formatTgl.format(new Date());

        for (int i = 0; i < riw; i++) {
            System.out.println("    [       "+ tanggal + "- " + riwayat[i]);
        }
        System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
        pilih = input.next();
        if (pilih.equalsIgnoreCase("y")) {
            menu();
        } else {
            System.out.println(green+"    ======================================================");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    ======================================================"+reset);
            System.exit(0);
        }
    }

    static void dataNasabah() {
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |                    DATA NASABAH                    |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
        System.out.println();
        System.out.print("      [   Masukkan PIN anda : ");
        String inPin = input.next();

        int index = 0;
        if (inPin.equals(dataNasabah[hasil][3])) {
            for (int i = 0; i < dataNasabah.length; i++) {
                if (dataNasabah[i][3].equals(inPin)) {
                    index = 1;
                    System.out.println("    =======================================================");
                    System.out.println("    [  ____________________________________________________");
                    System.out.println("    [\t|                \tDATA NASABAH    \t\t\t");
                    System.out.printf("    [\t|  ID\t\t\t\t: %s\n", dataNasabah[i][0]);
                    System.out.printf("    [\t|  Nama\t\t\t\t: %s\n", dataNasabah[i][2]);
                    System.out.printf("    [\t|  Nomor Rekening\t: %s\n", dataNasabah[i][1]);
                    System.out.printf("    [\t|  Alamat\t\t\t: %s\n", dataNasabah[i][4]);
                    System.out.printf("    [\t|  Nomor Telepon\t: %s\n", dataNasabah[i][7]);
                    System.out.println("    [  ----------------------------------------------------");
                    System.out.printf(green+"    [\t|  Status Akun\t\t: %s\n"+reset, dataNasabah[i][6]);
                    System.out.println("    [  ----------------------------------------------------");
                    System.out.println("    =======================================================");

                    System.out.println();
                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                    pilih = input.next();
                    if (pilih.equalsIgnoreCase("y")) {
                        menu();
                    } else {
                        keluarMenu();
                    }
                }
            }
        } else {
            System.out.println(red+"    =======================================================");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    |       (!) Anda memasukkan PIN yang salah (!)        |");
            System.out.println("    |              Silahkan Masukkan Kembali.             |");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    ======================================================="+reset);
            dataNasabah();
        }
    }

    static void cekSaldo() {
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |                     CEK SALDO                      |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
        System.out.println();
        System.out.print("      [   Masukkan PIN anda : ");
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
                    System.out.println("    [\t|        \tCEK SALDO    \t\t\t");
                    System.out.printf("    [\t|  Nama\t\t\t: %s\n", dataNasabah[i][2]);
                    System.out.printf("    [\t|  Sisa Saldo\t\t: %s\n", balance);
                    System.out.println("    [  -------------------------------------------------\t]");
                    System.out.println("    =======================================================");
                    System.out.println();
                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                    pilih = input.next();
                    if (pilih.equalsIgnoreCase("y")) {
                        menu();
                    } else {
                        System.out.println(green+"    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================"+reset);
                        break;
                    }
                }
            }
        } else {
            System.out.println(red+"    =======================================================");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    |       (!) Anda memasukkan PIN yang salah (!)        |");
            System.out.println("    |              Silahkan Masukkan Kembali.             |");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    ======================================================="+reset);
            cekSaldo();
        }
    }

    static void tarikTunai() {
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |                    TARIK TUNAI                     |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
        System.out.println();
        System.out.println("    =======================================================");
        System.out.println("    -------------------------------------------------------");
        System.out.print("      [   Masukkan saldo yang ingin ditarik : IDR  ");
        double nominalTarik = input.nextDouble();
        double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
        System.out.println("    -------------------------------------------------------");
        System.out.println("    =======================================================");

        if (nominalTarik > 0 && nominalTarik <= sisaSaldo) {
            if (nominalTarik >= 50000) {
                sisaSaldo -= nominalTarik;
                NumberFormat format = NumberFormat.getCurrencyInstance();
                String balance = format.format(sisaSaldo);
                String nominal = format.format(nominalTarik);
                dataNasabah[hasil][5] = String.valueOf(sisaSaldo);

                System.out.println("    ======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|             \tTARIK TUNAI       \t\t\t");
                System.out.printf("    [\t|  Nama\t\t\t: %s\n", dataNasabah[hasil][2]);
                System.out.printf("    [\t|  Jumlah Saldo yang ditarik\t: %s\n", nominal);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.printf("\n    [   Konfirmasi penarikan tunai sebesar %s? y/t: ", nominal);
                pilih = input.next();
                if (pilih.equalsIgnoreCase("y")) {
                    System.out.print("      [   Masukkan PIN anda untuk melanjutkan transaksi : ");
                    String inPin = input.next();

                    int index = 0;
                    if (inPin.equals(dataNasabah[hasil][3])) {
                        for (int i = 0; i < dataNasabah.length; i++) {
                            if (dataNasabah[i][3].equals(inPin)) {
                                index = 1;
                                System.out.println();
                                System.out.println(green+"    ======================================================");
                                System.out.println("    ------------------------------------------------------");
                                System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                System.out.println("    ------------------------------------------------------");
                                System.out.println("    ======================================================");
                                System.out.println("    ======================================================");
                                System.out.println("    [  _________________________________________________\t]");
                                System.out.println("    [\t|             \tTARIK TUNAI       \t\t|\t]");
                                System.out.printf("    [\t|  Nama\t\t\t: %s\t\t|\t\t]\n", dataNasabah[hasil][2]);
                                System.out.printf("    [\t|  Sisa Saldo\t\t: %s\t|\t]\n", balance);
                                System.out.println("    [  -------------------------------------------------\t]");
                                System.out.println("    ======================================================="+reset);
                                System.out.println();
                                riwayat[riw] = String.format("Telah melakukan tarik tunai sebesar %s", nominal);
                                riw++;
                                System.out.print("      [   Ingin melanjutkan transaksi y/t: ");
                                pilih = input.next();
                                if (pilih.equalsIgnoreCase("y")) {
                                    menu();
                                } else {
                                    System.out.println(green+"    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================"+reset);
                                    System.exit(0);
                                }
                            }
                        }
                    }
                    } else {
                    System.out.println(red+"    =======================================================");
                    System.out.println("    |-----------------------------------------------------|");
                    System.out.println("    |       (!) Anda memasukkan PIN yang salah (!)        |");
                    System.out.println("    |              Silahkan Masukkan Kembali.             |");
                    System.out.println("    |-----------------------------------------------------|");
                    System.out.println("    ======================================================="+reset);
                    tarikTunai();
                }
            } else {
                System.out.println(red+"    =======================================================");
                System.out.println("    |-----------------------------------------------------|");
                System.out.println("    |      (!) MINIMAL PENARIKAN TUNAI RP. 50.000(!)      |");
                System.out.println("    |              Silahkan Ulangi Kembali.               |");
                System.out.println("    |-----------------------------------------------------|");
                System.out.println("    ======================================================="+reset);
                tarikTunai();
            }
        } else {
            System.out.println(red+"    ======================================================");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    ======================================================"+reset);
            System.out.println();
            tarikTunai();
        }
    }

    static void keluarMenu() {
        System.out.print("      [   Apakah anda yakin ingin keluar y/t: ");
        pilih = input.next();
        if (pilih.equalsIgnoreCase("y")) {
            System.out.println(green+"    ======================================================");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    ======================================================"+reset);

            System.exit(0);
        } else {
            menu();
        }
    }

    static void setorTunai() {
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |                    SETOR TUNAI                     |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
        System.out.println();
        System.out.println("    =======================================================");
        System.out.println("    -------------------------------------------------------");
        System.out.print("      [   Masukkan saldo yang ingin disetor : IDR ");
        double nominalSetor = input.nextDouble();
        double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
        System.out.println("    -------------------------------------------------------");
        System.out.println("    =======================================================");

        if (nominalSetor <= 5000000) {
            sisaSaldo += nominalSetor;
            NumberFormat format = NumberFormat.getCurrencyInstance();
            String balance = format.format(sisaSaldo);
            String nominal = format.format(nominalSetor);
            dataNasabah[hasil][5] = String.valueOf(sisaSaldo);

            System.out.println("    =======================================================");
            System.out.println("    [  _________________________________________________\t]");
            System.out.println("    [\t|             \tSETOR TUNAI       \t\t\t");
            System.out.printf("    [\t|  Nama\t\t\t: %s\n", dataNasabah[hasil][2]);
            System.out.printf("    [\t|  Nominal Setor\t\t: IDR %s\n", nominal);
            System.out.println("    [  -------------------------------------------------\t]");
            System.out.println("    =======================================================");
            System.out.println();
            System.out.printf("\n    [   Konfirmasi setor tunai sebesar %s? y/t: ", nominal);
            pilih = input.next();
            if (pilih.equalsIgnoreCase("y")) {
                System.out.print("      [   Masukkan PIN anda untuk melanjutkan transaksi : ");
                String inPin = input.next();

                int index = 0;
                if (inPin.equals(dataNasabah[hasil][3])) {
                    for (int i = 0; i < dataNasabah.length; i++) {
                        if (dataNasabah[i][3].equals(inPin)) {
                            index = 1;
                            System.out.println(green+"    ======================================================");
                            System.out.println("    ------------------------------------------------------");
                            System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                            System.out.println("    ------------------------------------------------------");
                            System.out.println("    ======================================================");
                            System.out.println("    =======================================================");
                            System.out.println("    [  _________________________________________________\t]");
                            System.out.println("    [\t|             \tSETOR TUNAI       \t\t|\t]");
                            System.out.printf("    [\t|  Nama\t\t\t: %s\t\t|\t\t]\n", dataNasabah[i][2]);
                            System.out.printf("    [\t|  Sisa Saldo\t\t: %s\t|\t]\n", balance);
                            System.out.println("    [  -------------------------------------------------\t]");
                            System.out.println("    ======================================================="+reset);
                            System.out.println();
                            riwayat[riw] = String.format("Telah melakukan setor tunai sebesar %s", nominal);
                            riw++;
                            System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                            pilih = input.next();
                            if (pilih.equalsIgnoreCase("y")) {
                                menu();
                            } else {
                                System.out.println(green+"    ======================================================");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    ======================================================"+reset);
                                System.exit(0);
                            }
                        }
                    }
                } else {
                    System.out.println(red+"    =======================================================");
                    System.out.println("    |-----------------------------------------------------|");
                    System.out.println("    |       (!) Anda memasukkan PIN yang salah (!)        |");
                    System.out.println("    |              Silahkan Masukkan Kembali.             |");
                    System.out.println("    |-----------------------------------------------------|");
                    System.out.println("    ======================================================="+reset);
                    setorTunai();
                }
            }
        } else {
            System.out.println(red+"    =======================================================");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    |   (!) Anda Melebihi Jumlah Nominal Setor Tunai (!)  |");
            System.out.println("    |                Silahkan Coba Kembali.               |");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    ======================================================="+reset);
            setorTunai();
        }
    }

    static void transferSaldo() {
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |                   TRANSFER SALDO                   |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
        System.out.println();
        System.out.print("  [   Masukkan nomor rekening anda : ");
        String inRek = input.next();

        int index = 0;
        if (inRek.equals(dataNasabah[hasil][1])) {
            for (int i = 0; i < dataNasabah.length; i++) {
                if (dataNasabah[i][1].equals(inRek)) {
                    index = i;
                    System.out.print("  [   Masukkan nomor rekening tujuan : ");
                    String rekTujuan = input.next();
                    for (int j = 0; j < dataNasabah.length; j++) {
                        if (rekTujuan.equals(dataNasabah[j][1])) {
                            index = 1;
                            System.out.print("  [   Masukkan nominal transfer : IDR ");
                            double nomTF = input.nextDouble();
                            double saldoUser = Double.parseDouble(dataNasabah[hasil][5]);

                            if (nomTF > 0 && nomTF <= saldoUser) {
                                saldoUser -= nomTF;
                                NumberFormat format = NumberFormat.getCurrencyInstance();
                                String balance = format.format(saldoUser);
                                String nominal = format.format(nomTF);
                                dataNasabah[index][5] = String.valueOf(saldoUser);
                                System.out.println("    =======================================================");
                                System.out.println("    [  _________________________________________________\t]");
                                System.out.println("    [\t|        \tTRANSFER SALDO    \t\t\t");
                                System.out.printf("    [\t|  Rekening Tujuan\t\t: %s\n", rekTujuan);
                                System.out.printf("    [\t|  Nominal Transfer\t\t: %s\n", nominal);
                                System.out.println("    [  -------------------------------------------------\t]");
                                System.out.println("    =======================================================");
                                System.out.println();
                                System.out.print("Konfirmasi transfer SALDO ke rekening " + rekTujuan + " dengan nominal " + nominal + "(y/t) : ");
                                pilih = input.next();
                                if (pilih.equals("y")) {
                                    System.out.println(green+"    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println("    ========================================================");
                                    System.out.println("    [  ________________________________________________\t\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Rekening tujuan\t\t: %s\t|\t]\n", rekTujuan);
                                    System.out.printf("    [\t|  Nama user\t\t\t\t: %s\t|\t]\n", dataNasabah[j][2]);
                                    System.out.printf("    [\t|  Nominal transfer\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    ======================================================="+reset);
                                    System.out.println();
                                    riwayat[riw] = String.format("Telah melakukan transfer saldo ke nomor rekening %s sebesar %s", rekTujuan, nominal);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equalsIgnoreCase("y")) {
                                        menu();
                                    } else {
                                        System.out.println(green+"    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================"+reset);
                                        System.exit(0);
                                    }
                                } else {
                                    System.out.println(red+"    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |                TRANSAKSI DIBATALKAN                |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================"+reset);
                                    menu();
                                }
                            } else {
                                System.out.println(red+"    ======================================================");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    ======================================================"+reset);
                                System.out.println();
                                transferSaldo();
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println(red+"    ======================================================");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    |                REKENING TIDAK VALID                |");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    ======================================================"+reset);
            System.out.println();
            transferSaldo();
        }
    }

    static void pembayaranLain() {
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |                  PEMBAYARAN LAIN                   |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
        System.out.println();
        System.out.println("""
                [       Silahkan pilih menu dibawah ini :
                [       1. Beli Pulsa
                [       2. Pembayaran Listrik
                [       3. Pembayaran PDAM
                [       4. Pembayaran UKT
                [       0. Kembali ke Menu Utama
                """);
        System.out.print("\tMenu yang dipilih : ");
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
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |                   PEMBELIAN PULSA                  |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
        System.out.println();
        System.out.println("""
                [       Pilih operator seluler dibawah ini :
                [       1. Indosat
                [       2. XL
                [       3. Telkomsel
                [       4. Axis 
                [       0. Kembali ke Menu Utama
                """);
        System.out.print("\tOperator yang dipilih : ");
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
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |                      INDOSAT                       |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
        System.out.println();
        System.out.print("Masukkan nomor telepon anda : 085-");
        String nomTelp = input.next();
        System.out.print("\nApakah nomor telepon yang anda masukkan benar? y/t: ");
        pilih = input.next();
        if (pilih.equals("y")) {
            masuk = true;
        } else {
            indosat();
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
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s  \t|\t]\n", pulsa[0]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    ========================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal IDR %s  ? y/t: ", nomTelp, pulsa[0]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[0]);
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println(green+"    ======================================================");
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal );
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    ======================================================="+reset);
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[0], nominal);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        menu();
                                    } else {
                                        System.out.println(green+"    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================"+reset);
                                        System.exit(0);
                                    }
                                } else {
                                    System.out.println(red+"    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================"+reset);
                                    System.out.println();
                                    indosat();

                                }
                            }
                        }
                    } else {
                        index = 0;
                        System.out.println(red+"    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================"+reset);
                        indosat();
                    }
                } else {
                    indosat();
                }
                break;
            case 2:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[1]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[1]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[1]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[0], nominal);
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
            case 3:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[2]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[2]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[2]);
                                    dataNasabah[index][5] = String.valueOf(sisaSaldo);
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[0], nominal);
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
            case 4:
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
                System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[3]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[3]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[3]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[0], nominal);
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[4]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    ======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[4]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[4]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[0], nominal);
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[5]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    ======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[5]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[5]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[0], nominal);
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
                case 0 : menu();
        }
    }

    static void xl() {
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |                         XL                         |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[0]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    ======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[0]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[0]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[1], nominal);
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
                                    xl();
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
                        xl();
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[1]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[1]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[1]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[1], nominal);
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
                                    xl();
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
                        xl();
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[2]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[2]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[2]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[1], nominal);
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
                                    xl();
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
                        xl();
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[3]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[3]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[3]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[1], nominal);
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
                                    xl();
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
                        xl();
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[4]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[4]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[4]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[1], nominal);
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
                                    xl();
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
                        xl();
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[5]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 085-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[5]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[5]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[1], nominal);
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
                                    xl();
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
                        xl();
                    }
                } else {
                    xl();
                }
                break;

                case 0 : menu();
        }
    }

    static void telkomsel() {
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |                     TELKOMSEL                      |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[0]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    ======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[0]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[0]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[2], nominal);
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
                                    telkomsel();
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
                        telkomsel();
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[1]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    ======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[1]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[1]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    ======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[2], nominal);
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
                                    telkomsel();
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
                        telkomsel();
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[2]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[2]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[2]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[2], nominal);
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
                                    telkomsel();
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
                        telkomsel();
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[3]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[3]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[3]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[2], nominal);
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
                                    telkomsel();
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
                        telkomsel();
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[4]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[4]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[4]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[2], nominal);
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
                                    telkomsel();
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
                        telkomsel();
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[5]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[5]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[5]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[2], nominal);
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
                                    telkomsel();
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
                        telkomsel();
                    }
                } else {
                    telkomsel();
                }
                break;

                case 0 : menu();
        }
    }

    static void axis() {
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |                        AXIS                        |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[0]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 083-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[0]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[0]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[3], nominal);
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
                                    axis();
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
                        axis();
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[1]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 083-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[1]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[1]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[3], nominal);
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
                                    axis();
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
                        axis();
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[2]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 083-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[2]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[2]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[3], nominal);
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
                                    axis();
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
                        axis();
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[3]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[3]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[3]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[3], nominal);
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
                                    axis();
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
                        axis();
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[4]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[4]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[4]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[3], nominal);
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
                                    axis();
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
                        axis();
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
                System.out.printf("    [\t|  Total bayar pulsa\t\t: IDR %s\t|\t]\n", pulsa[5]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");
                System.out.println();
                System.out.println("Total bayar diatas sudah termasuk biaya admin IDR 2500");
                System.out.printf(" Konfirmasi transaksi ke nomor telepon 081-%s dengan nominal IDR %s? y/t: ", nomTelp, pulsa[5]);
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
                                    NumberFormat format = NumberFormat.getCurrencyInstance();
                                    String balance = format.format(sisaSaldo);
                                    String nominal = format.format(pulsa[5]);
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
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: %s\t|\t]\n", nominal);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar %s", operator[3], nominal);
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
                                    axis();
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
                        axis();
                    }
                } else {
                    axis();
                }
                break;
                
                case 0 : menu();
        }
    }

    static void bayarListrik() {
        String[][] dataListrik = {
                {"qwerty0987", "100000"},
                {"zxcvbn1234", "125000"},
                {"poiuyt5678", "150000"},
        };
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |                   BAYAR LISTRIK                    |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
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
                        System.out.printf("    [\t|  Total tagihan\t\t: IDR %s\t|\t]\n", dataListrik[i][1]);
                        System.out.println("    [  -------------------------------------------------\t]");
                        System.out.println("    =======================================================");
                        System.out.println();
                        System.out.printf(" Konfirmasi transaksi tagihan PLN dengan ID pelanggan %s sebesar IDR %s? y/t: ", dataListrik[i][0], dataListrik[i][1]);
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
                                            NumberFormat format = NumberFormat.getCurrencyInstance();
                                            String balance = format.format(sisaSaldo);
                                            String nominal = format.format(nomListrik);
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
                                            System.out.printf("    [\t|  Total tagihan\t\t: %s\t|\t]\n", nominal);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.println("    =======================================================");
                                            System.out.println();
                                            riwayat[riw] = String.format("Telah melakukan transaksi tagihan listrik dengan ID pelanggan %s sebesar %s", dataListrik[i][0], nominal);
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
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |                    BAYAR PDAM                      |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
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
                        System.out.printf("    [\t|  Total tagihan\t\t: IDR %s\t|\t]\n", datapdam[i][2]);
                        System.out.println("    [  -------------------------------------------------\t]");
                        System.out.println("    =======================================================");
                        System.out.println();
                        System.out.printf(" Konfirmasi transaksi tagihan PDAM dengan ID pelanggan %s sebesar IDR %s? y/t: ", datapdam[i][1], datapdam[i][2]);
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
                                            NumberFormat format = NumberFormat.getCurrencyInstance();
                                            String balance = format.format(sisaSaldo);
                                            String nominal = format.format(nomPdam);
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
                                            System.out.printf("    [\t|  Total tagihan\t\t: %s\t|\t]\n", nominal);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.printf("    [\t|  Sisa saldo anda\t\t: %s\t|\t]\n", balance);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.println("    =======================================================");
                                            System.out.println();
                                            riwayat[riw] = String.format("Telah melakukan transaksi tagihan PDAM dengan ID pelanggan %s sebesar %s", datapdam[i][1], nominal);
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
                        System.out.printf("    [\t|  Total tagihan\t\t: IDR %s\t|\t]\n", datapdam[i][2]);
                        System.out.println("    [  -------------------------------------------------\t]");
                        System.out.println("    =======================================================");
                        System.out.println();
                        System.out.printf(" Konfirmasi transaksi tagihan PDAM dengan ID pelanggan %s sebesar IDR %s? y/t: ", datapdam[i][1], datapdam[i][2]);
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
                                            NumberFormat format = NumberFormat.getCurrencyInstance();
                                            String balance = format.format(sisaSaldo);
                                            String nominal = format.format(nomPdam);
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
                                            System.out.printf("    [\t|  Total tagihan\t\t: Rp. %s\t|\t]\n", nominal);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", balance);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.println("    =======================================================");
                                            System.out.println();
                                            riwayat[riw] = String.format("Telah melakukan transaksi tagihan PDAM dengan ID pelanggan %s sebesar Rp. %s", datapdam[i][1], nominal);
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
                        System.out.printf("    [\t|  Total tagihan\t\t: IDR %s\t|\t]\n", datapdam[i][2]);
                        System.out.println("    [  -------------------------------------------------\t]");
                        System.out.println("    =======================================================");
                        System.out.println();
                        System.out.printf(" Konfirmasi transaksi tagihan PDAM dengan ID pelanggan %s sebesar IDR %s? y/t: ", datapdam[i][1], datapdam[i][2]);
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
                                            NumberFormat format = NumberFormat.getCurrencyInstance();
                                            String balance = format.format(sisaSaldo);
                                            String nominal = format.format(nomPdam);
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
                                            System.out.printf("    [\t|  Total tagihan\t\t: Rp. %s\t|\t]\n", nominal);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", balance);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.println("    =======================================================");
                                            System.out.println();
                                            riwayat[riw] = String.format("Telah melakukan transaksi tagihan PDAM dengan ID pelanggan %s sebesar Rp. %s", datapdam[i][1], nominal);
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
                        System.out.printf("    [\t|  Total tagihan\t\t: IDR %s\t|\t]\n", datapdam[i][2]);
                        System.out.println("    [  -------------------------------------------------\t]");
                        System.out.println("    =======================================================");
                        System.out.println();
                        System.out.printf(" Konfirmasi transaksi tagihan PDAM dengan ID pelanggan %s sebesar IDR %s? y/t: ", datapdam[i][1], datapdam[i][2]);
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
                                            NumberFormat format = NumberFormat.getCurrencyInstance();
                                            String balance = format.format(sisaSaldo);
                                            String nominal = format.format(nomPdam);
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
                                            System.out.printf("    [\t|  Total tagihan\t\t: Rp. %s\t|\t]\n", nominal);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", balance);
                                            System.out.println("    [  -------------------------------------------------\t]");
                                            System.out.println("    =======================================================");
                                            System.out.println();
                                            riwayat[riw] = String.format("Telah melakukan transaksi tagihan PDAM dengan ID pelanggan %s sebesar Rp. %s", datapdam[i][1], nominal);
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
                {"10074", "Universitas Negeri Malang", "2309876", "Annisa", "Teknik Sipil", "4500000"},
                {"071027", "Universitas Islam Malang", "2212345", "Naufal", "Pendidikan Agama Islam", "3000000"},
                {"071024", "Universitas Muhammadiyah Malang", "23123456", "Bayu", "Teknik Mesin", "5000000"}
        };
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |                     BAYAR UKT                      |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
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
                                    System.out.printf("    [\t|  Nominal dibayarkan\t\t\t: IDR %s\t\t|\t]\n", dataUniv[i][5]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    System.out.printf(" Konfirmasi transaksi pembayaran UKT dengan Nama Perguruan tinggi %s sebesar IDR %s? y/t: ", dataUniv[i][1], dataUniv[i][5]);
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
                                                        NumberFormat format = NumberFormat.getCurrencyInstance();
                                                        String balance = format.format(sisaSaldo);
                                                        String nominal = format.format(nomUkt);
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
                                                        System.out.printf("    [\t|  Nominal dibayarkan\t\t\t: %s\t\t|\t]\n", nominal);
                                                        System.out.println("    [  -------------------------------------------------\t]");
                                                        System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", balance);
                                                        System.out.println("    [  -------------------------------------------------\t]");
                                                        System.out.println("    =======================================================");
                                                        System.out.println();
                                                        riwayat[riw] = String.format("Telah melakukan transaksi Pembayaran UKT dengan Nama Universitas %s sebesar Rp. %s", dataUniv[i][1], nominal);
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
        System.out.println("""
                    [--------------------------
                    [       Pilih Bahasa
                    [--------------------------
                    [       Choose language :
                    [
                    [       1. Bahasa
                    [       2. English
                """);
        System.out.print("    [       Bahasa yang dipilih : ");
        int menu = input.nextInt();
        if (menu == 1) {
            login();
        } if (menu == 2) {
            Bilingual.main(args);
        } else {
            if (menu != 1 && menu != 2) {
                System.out.println("    ======================================================");
                System.out.println("    |----------------------------------------------------|");
                System.out.println("    |     (!) MENU YANG ANDA MASUKKAN TIDAK VALID (!)    |");
                System.out.println("    |----------------------------------------------------|");
                System.out.println("    ======================================================");
                System.exit(0);
            }
    
        }
    }
}