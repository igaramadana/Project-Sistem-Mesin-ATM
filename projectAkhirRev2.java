import java.util.Scanner;

public class projectAkhirRev2 {
    static Scanner input = new Scanner(System.in);
    static boolean login = false, valid = false, masuk = false;
    static String pilih;
    static String[][] dataNasabah = {
        {"01", "112233", "Annisa", "1122", "Nganjuk", "1000000", "Verified", "085123456"},
        {"02", "334455", "Iga", "3344", "Dampit", "5000000", "Verified", "081123456"},
        {"03", "556677", "Firman", "4455", "Blitar", "3500000", "Verified", "087123456"},
        {"04", "889911", "Bayu", "6677", "Jombang", "3000000", "Verified", "08912345"},
        {"05", "123456", "Novita", "8899", "Watugong", "10000000", "Verified", "088123456"}
    };
    static int[] pulsa = {22500, 27500, 52500, 102500, 502500, 1002500};
    static String[] operator = {"Indosat", "XL", "Telkomsel", "Axis"};
    static int riw = 0, hasil = 0;
    static String[] riwayat = new String[10]    ;
    static int loginAttempts = 0;
    public static void main(String[] args) {
        login();
    }
    private static void login() {
        System.out.println("==================================================================");
        System.out.println("------------------------------------------------------------------");
        System.out.println(" __      _____ _    ___ ___  __  __ ___   _____ ___  \n" + //
                " \\ \\    / / __| |  / __/ _ \\|  \\/  | __| |_   _/ _ \\ \n" + //
                "  \\ \\/\\/ /| _|| |_| (_| (_) | |\\/| | _|    | || (_) |\n" + //
                "   \\_/\\_/ |___|____\\___\\___/|_|  |_|___|   |_| \\___/ \n" + //
                "                                                     ");
        System.out.println("------------------------------------------------------------------");
        System.out.println("    _ _____ __  __   ___   _   _  _ _  __  _  _  ___  \n" + //
                "   /_\\_   _|  \\/  | | _ ) /_\\ | \\| | |/ / | \\| |/ _ \\ \n" + //
                "  / _ \\| | | |\\/| | | _ \\/ _ \\| .` | ' <  | .` | (_) |\n" + //
                " /_/ \\_\\_| |_|  |_| |___/_/ \\_\\_|\\_|_|\\_\\ |_|\\_|\\___/ \n" + //
                "                                                      ");
        System.out.println("------------------------------------------------------------------");
        System.out.println("==================================================================");
        System.out.println();

        // Cek Login
        while (loginAttempts <= 3) {
            System.out.println("    =======================================================");
            System.out.println("    -------------------------------------------------------");
            System.out.print("      [   Masukkan PIN anda : ");
            String inputPin = input.next();
            System.out.println("    -------------------------------------------------------");
            System.out.println("    =======================================================");
            System.out.println();
            

            // Pengecekan verified/diblokir
            if (hasil != -1 && dataNasabah[hasil][6].equals("Disabled")) {
                System.out.println("============================================================================================");
                System.out.println("        [   Status akun anda " + dataNasabah[hasil][6]);
                System.out.println("============================================================================================");
            }

            // Pengecekan kesesuaian PIN login
            for (int i = 0; i < dataNasabah.length; i++) {
                if (inputPin.equals(dataNasabah[i][3]) && dataNasabah[i][6].equals("Verified")) {
                    login = true;
                    hasil = i;
                    menu();
                }
            }
            
            if (login) {
                menu();
            } else {
                    System.out.println("    =======================================================");
                    System.out.println("    |-----------------------------------------------------|");
                    System.out.println("    |       (!) Anda memasukkan PIN yang salah (!)        |");
                    System.out.println("    |              Silahkan Masukkan Kembali.             |");
                    System.out.println("    |-----------------------------------------------------|");
                    System.out.println("    =======================================================");
                    loginAttempts++;

                    if (loginAttempts >= 3) {
                        disabled();
                        break;
                }
            }
    }
}
    private static void menu() {
                do {
                    System.out.println("    =======================================================");
                    System.out.println("    -------------------------------------------------------");
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
                    System.out.print("\tMenu yang dipilih : ");
                    int menu = input.nextInt();

                    switch (menu) {
                        case 1:
                        dataNasabah();
                        case 2:
                        cekSaldo();
                        case 3:
                        tarikTunai();
                        case 4:
                        setorTunai();
                        case 5:
                        transferSaldo();
                        case 6:
                        riwayatTransaksi();
                        case 7:
                        pembayaranLain();
                        case 8:
                        pengaturanAkun();
                        case 0:
                        keluarMenu();
                        default:
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |           MENU YANG ANDA PILIH TIDAK VALID         |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                    } break;     
                }while(login);
                    System.out.println();
}
    private static void disabled() {
        System.out.println("===============================================================================================");
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("           (!) Anda telah gagal lebih dari 3 kali. Akun anda telah di-Disabled. (!)            ");
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("===============================================================================================");
        dataNasabah[hasil][6] = "Disabled";
    }
    private static void dataNasabah() {
        System.out.println("=====================================================================================");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("  ___      _     _____     _       _  _     _     ___     _     ___     _     _  _ \r\n" + //
                " |   \\    /_\\   |_   _|   /_\\     | \\| |   /_\\   / __|   /_\\   | _ )   /_\\   | || |\r\n" + //
                " | |) |  / _ \\    | |    / _ \\    | .` |  / _ \\  \\__ \\  / _ \\  | _ \\  / _ \\  | __ |\r\n" + //
                " |___/  /_/ \\_\\   |_|   /_/ \\_\\   |_|\\_| /_/ \\_\\ |___/ /_/ \\_\\ |___/ /_/ \\_\\ |_||_|\r\n" + //
                "                                                                                   ");
        System.out.println("-------------------------------------------------------------------------------------");        
        System.out.println("=====================================================================================");
        System.out.print("      [   Masukkan PIN anda : ");
        String inPin = input.next();
        
        int index = 0;
        if (inPin.equals(dataNasabah[hasil][3])) {
            for (int i = 0; i < dataNasabah.length; i++) {
                if (dataNasabah[i][3].equals(inPin)) {
                index = 1;
                System.out.println("    =======================================================");
                System.out.println("    [  _________________________________________________\t]");
                System.out.println("    [\t|        \tDATA NASABAH    \t\t|\t]");
                System.out.printf("    [\t|  ID\t\t\t: %s\t\t\t|\t]\n", dataNasabah[i][0]);
                System.out.printf("    [\t|  Nama\t\t\t: %s\t\t\t|\t]\n", dataNasabah[i][2]);
                System.out.printf("    [\t|  Nomor Rekening\t: %s\t\t|\t]\n", dataNasabah[i][1]);
                System.out.printf("    [\t|  Alamat\t\t: %s\t\t|\t]\n", dataNasabah[i][4]);
                System.out.printf("    [\t|  Nomor Telepon\t: %s\t\t|\t]\n", dataNasabah[i][7]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.printf("    [\t|  Status Akun\t\t: %s\t\t|\t]\n", dataNasabah[i][6]);
                System.out.println("    [  -------------------------------------------------\t]");
                System.out.println("    =======================================================");

                System.out.println();
                System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                    pilih = input.next();
                    if (pilih.equals("y")) {
                        menu();
                    }else {
                        tutup();
                    } 
            }
        } 
        } else {
            index = 0;
            System.out.println("    =======================================================");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    |       (!) Anda memasukkan PIN yang salah (!)        |");
            System.out.println("    |              Silahkan Masukkan Kembali.             |");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    =======================================================");
            dataNasabah();
        }
        
        }
    private static void cekSaldo() {
        System.out.println("======================================================================");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("   ___   ___   _  __    ___     _     _      ___     ___  \r\n" +  //   
                "  / __| | __| | |/ /   / __|   /_\\   | |    |   \\   / _ \\ \r\n" + //
                " | (__  | _|  | ' <    \\__ \\  / _ \\  | |__  | |) | | (_) |\r\n" + //
                "  \\___| |___| |_|\\_\\   |___/ /_/ \\_\\ |____| |___/   \\___/ \r\n" + //
                "                                                          ");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("======================================================================");
        System.out.print("      [   Masukkan PIN anda : ");
        String inPin = input.next();

        int index = 0;
        if (inPin.equals(dataNasabah[hasil][3])) {
            for (int i = 0; i < dataNasabah.length; i++) {
                if (dataNasabah[i][3].equals(inPin)) {
                    index = 1;
                    System.out.println();
                    System.out.println("    =======================================================");
                    System.out.println("    [  _________________________________________________\t]");
                    System.out.println("    [\t|        \tCEK SALDO    \t\t|\t]");
                    System.out.printf("    [\t|  Nama\t\t\t: %s\t\t|\t\t]\n", dataNasabah[i][2]);
                    System.out.printf("    [\t|  Sisa Saldo\t\t: Rp. %s\t|\t]\n", dataNasabah[i][5]);
                    System.out.println("    [  -------------------------------------------------\t]");
                    System.out.println("    =======================================================");
                    System.out.println();
                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                    pilih = input.next();
                    if (pilih.equals("y")) {
                        menu();
                    }else {
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                        break;
                    }
                        System.out.println();
                }
            }
        } else {
            index = 0;
            System.out.println("    =======================================================");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    |       (!) Anda memasukkan PIN yang salah (!)        |");
            System.out.println("    |              Silahkan Masukkan Kembali.             |");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    =======================================================");
            cekSaldo();
        }
    }
    private static void tarikTunai() {
        System.out.println("=====================================================================================");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("  _____     _     ___   ___   _  __    _____   _   _   _  _     _     ___ \r\n" + //
                " |_   _|   /_\\   | _ \\ |_ _| | |/ /   |_   _| | | | | | \\| |   /_\\   |_ _|\r\n" + //
                "   | |    / _ \\  |   /  | |  | ' <      | |   | |_| | | .` |  / _ \\   | | \r\n" + //
                "   |_|   /_/ \\_\\ |_|_\\ |___| |_|\\_\\     |_|    \\___/  |_|\\_| /_/ \\_\\ |___|\r\n" + //
                "");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("=====================================================================================");
        System.out.print("      [   Masukkan PIN anda : ");
        String inPin = input.next();

        int index = -1;
        if (inPin.equals(dataNasabah[hasil][3])) {
            for (int i = 0; i < dataNasabah.length; i++) {
                if (dataNasabah[i][3].equals(inPin)) {
                    index = i;
                System.out.print("      [   Masukkan jumlah saldo yang ingin ditarik: Rp.  ");
                double jumlahTarik = input.nextDouble();
                double currentBalance = Double.parseDouble(dataNasabah[index][5]);
                
                if (jumlahTarik > 0 && jumlahTarik <= currentBalance) {
                    currentBalance -= jumlahTarik;
                    dataNasabah[index][5] = String.valueOf(currentBalance);
                    System.out.println();
                    System.out.println("    ======================================================");
                    System.out.println("    ------------------------------------------------------");
                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                    System.out.println("    ------------------------------------------------------");
                    System.out.println("    ======================================================");
                    System.out.println("    ======================================================");
                    System.out.println("    [  _________________________________________________\t]");
                    System.out.println("    [\t|             \tTARIK TUNAI       \t\t|\t]");
                    System.out.printf("    [\t|  Nama\t\t\t: %s\t\t|\t\t]\n", dataNasabah[index][2]);
                    System.out.printf("    [\t|  Sisa Saldo\t\t: Rp. %s\t|\t]\n", dataNasabah[index][5]);
                    System.out.println("    [  -------------------------------------------------\t]");
                    System.out.println("    =======================================================");
                    System.out.println();
                    riwayat[riw] = String.format("Telah melakukan tarik tunai sebesar Rp. %.2f", jumlahTarik);
                        riw++;
                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                    pilih = input.next();
                    if (pilih.equals("y")) {
                        menu();
                    }else {
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                        break;
                    }
                        System.out.println();
                } else {
                    System.out.println("    ======================================================");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
                    System.out.println("    |----------------------------------------------------|");
                    System.out.println("    ======================================================");
                    System.out.println();
                    menu();
                }
                }
            }
        } else {
            index = -1;
            System.out.println("    =======================================================");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    |       (!) Anda memasukkan PIN yang salah (!)        |");
            System.out.println("    |              Silahkan Masukkan Kembali.             |");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    =======================================================");
            tarikTunai();
    }
}
    private static void setorTunai() {
        System.out.println("=====================================================================================");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("  ___   ___   _____    ___    ___     _____   _   _   _  _     _     ___ \r\n" + //
                " / __| | __| |_   _|  / _ \\  | _ \\   |_   _| | | | | | \\| |   /_\\   |_ _|\r\n" + //
                " \\__ \\ | _|    | |   | (_) | |   /     | |   | |_| | | .` |  / _ \\   | | \r\n" + //
                " |___/ |___|   |_|    \\___/  |_|_\\     |_|    \\___/  |_|\\_| /_/ \\_\\ |___|\r\n" + //
                "                                                                         ");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("=====================================================================================");
        System.out.print("      [   Masukkan PIN anda : ");
        String inPin = input.next();

        int index = 0;
        if (inPin.equals(dataNasabah[hasil][3])) {
            for (int i = 0; i < dataNasabah.length; i++) {
                if (dataNasabah[i][3].equals(inPin)) {
                    index = i;
                    System.out.print("Masukkan nominal setor tunai : Rp. ");
                    double nomSetor = input.nextDouble();
                    double saldoUser = Double.parseDouble(dataNasabah[hasil][5]);

                    saldoUser += nomSetor;
                    dataNasabah[i][5] = String.valueOf(saldoUser);
                    System.out.println("    ======================================================");
                    System.out.println("    ------------------------------------------------------");
                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                    System.out.println("    ------------------------------------------------------");
                    System.out.println("    ======================================================");
                    System.out.println("    =======================================================");
                    System.out.println("    [  _________________________________________________\t]");
                    System.out.println("    [\t|             \tSETOR TUNAI       \t\t|\t]");
                    System.out.printf("    [\t|  Nama\t\t\t: %s\t\t|\t\t]\n", dataNasabah[i][2]);
                    System.out.printf("    [\t|  Sisa Saldo\t\t: Rp. %s\t|\t]\n", saldoUser);
                    System.out.println("    [  -------------------------------------------------\t]");
                    System.out.println("    =======================================================");
                    System.out.println();
                    riwayat[riw] = String.format("Telah melakukan setor tunai sebesar Rp. %.2f", nomSetor);
                        riw++;
                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                    pilih = input.next();
                    if (pilih.equals("y")) {
                        menu();
                    }else {
                        keluarMenu();
                        System.out.println("    ======================================================");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                        System.out.println("    |----------------------------------------------------|");
                        System.out.println("    ======================================================");
                        break;
                    }
                }
            }
        } else {
            index = 0;
            System.out.println("    =======================================================");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    |       (!) Anda memasukkan PIN yang salah (!)        |");
            System.out.println("    |              Silahkan Masukkan Kembali.             |");
            System.out.println("    |-----------------------------------------------------|");
            System.out.println("    =======================================================");
            setorTunai();
    }
    }
    private static void transferSaldo() {
        System.out.println("=====================================================================================");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("  _____ ___    _   _  _ ___ ___ ___ ___   ___   _   _    ___   ___  \n" + //
                " |_   _| _ \\  /_\\ | \\| / __| __| __| _ \\ / __| /_\\ | |  |   \\ / _ \\ \n" + //
                "   | | |   / / _ \\| .` \\__ \\ _|| _||   / \\__ \\/ _ \\| |__| |) | (_) |\n" + //
                "   |_| |_|_\\/_/ \\_\\_|\\_|___/_| |___|_|_\\ |___/_/ \\_\\____|___/ \\___/ \n" + //
                "                                                                    ");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("=====================================================================================");
        System.out.print("  [   Masukkan nomor rekening anda : ");
        String inRek = input.next();

        int index = 0;
        if(inRek.equals(dataNasabah[hasil][1])) {
            for (int i = 0; i < dataNasabah.length; i++) {
                if (dataNasabah[i][1].equals(inRek)) {
                    index = i;
                    System.out.print("  [   Masukkan nomor rekening tujuan : ");
                    String rekTujuan = input.next();
                    for (int j = 0; j < dataNasabah.length; j++){
                        if (rekTujuan.equals(dataNasabah[j][1])) {
                            index = 1;
                            System.out.print("  [   Masukkan nominal transfer : Rp. ");
                            double nomTF = input.nextDouble();
                            double saldoUser = Double.parseDouble(dataNasabah[hasil][5]);

                        if (nomTF > 0 && nomTF <= saldoUser){
                            saldoUser -= nomTF;
                            dataNasabah[index][5] = String.valueOf(saldoUser);
                            System.out.println("    =======================================================");
                            System.out.println("    [  _________________________________________________\t]");
                            System.out.println("    [\t|        \tTRANSFER SALDO    \t\t|\t]");
                            System.out.printf("    [\t|  Rekening Tujuan\t\t: %s\t|\t]\n", rekTujuan);
                            System.out.printf("    [\t|  Nominal Transfer\t\t: Rp. %.2f\t|\t]\n", nomTF);
                            System.out.println("    [  -------------------------------------------------\t]");
                            System.out.println("    =======================================================");
                            System.out.println();
                            System.out.print("Konfirmasi transfer SALDO ke rekening " + rekTujuan + " dengan nominal Rp." + nomTF + "(y/t) : ");
                            pilih = input.next();
                                if (pilih.equals("y")) {
                                    System.out.println("    ======================================================");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("     ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println("    ------------------------------------------------------");
                                    System.out.println("    ======================================================");
                                    System.out.println("    ========================================================");
                                    System.out.println("    [  ________________________________________________\t\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Rekening tujuan\t\t: %s\t|\t]\n", rekTujuan);
                                    System.out.printf("    [\t|  Nama\t\t\t\t: %s\t|\t]\n", dataNasabah[j][2]);
                                    System.out.printf("    [\t|  Nominal transfer\t\t: Rp. %.2f\t|\t]\n", nomTF);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %.2f\t|\t]\n", saldoUser);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    riwayat[riw] = String.format("Telah melakukan transfer saldo ke nomor rekening %s sebesar Rp. %.2f", rekTujuan, nomTF);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        menu();
                                    }else {
                                        System.out.println("    ======================================================");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                                        System.out.println("    |----------------------------------------------------|");
                                        System.out.println("    ======================================================");
                                        break;
                                    }
                                }else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |                TRANSAKSI DIBATALKAN                |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    menu();
                                }
                            } else {
                                System.out.println("    ======================================================");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    |   SALDO TIDAK CUKUP UNTUK MELAKUKAN TRANSAKSI INI. |");
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
            System.out.println("    |                REKENING TIDAK VALID                |");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    ======================================================");
            System.out.println();
            menu();
        }
    }
    private static void pembayaranLain() {
        System.out.println("=====================================================================================");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("  ___   ___   __  __   ___     _    __   __    _     ___     _     _  _     _        _     ___   _  _ \r\n" + //
                " | _ \\ | __| |  \\/  | | _ )   /_\\   \\ \\ / /   /_\\   | _ \\   /_\\   | \\| |   | |      /_\\   |_ _| | \\| |\r\n" + //
                " |  _/ | _|  | |\\/| | | _ \\  / _ \\   \\ V /   / _ \\  |   /  / _ \\  | .` |   | |__   / _ \\   | |  | .` |\r\n" + //
                " |_|   |___| |_|  |_| |___/ /_/ \\_\\   |_|   /_/ \\_\\ |_|_\\ /_/ \\_\\ |_|\\_|   |____| /_/ \\_\\ |___| |_|\\_|\r\n" + //
                "                                                                                                      ");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("=====================================================================================");
        System.out.println("""
                [       Silahkan pilih menu dibawah ini :
                [       1. Beli Pulsa
                [       2. Pembayaran Listrik
                [       0. Kembali ke Menu Utama
                """);
        System.out.print("\tMenu yang dipilih : ");
        int menu = input.nextInt();

        switch (menu) {
            case 1 -> beliPulsa();
            case 2 -> bayarListrik();
            case 0 -> menu();
        }

        
    }
    private static void beliPulsa() {
        System.out.println("=====================================================================================");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("  ___ ___ __  __ ___ ___ _    ___   _   _  _   ___ _   _ _    ___   _   \r\n" + //
                " | _ \\ __|  \\/  | _ ) __| |  |_ _| /_\\ | \\| | | _ \\ | | | |  / __| /_\\  \r\n" + //
                " |  _/ _|| |\\/| | _ \\ _|| |__ | | / _ \\| .` | |  _/ |_| | |__\\__ \\/ _ \\ \r\n" + //
                " |_| |___|_|  |_|___/___|____|___/_/ \\_\\_|\\_| |_|  \\___/|____|___/_/ \\_\\\r\n" + //
                "                                                                        ");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("=====================================================================================");
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
    private static void indosat() {
        System.out.println("====================================================");
        System.out.println("----------------------------------------------------");
        System.out.println("  ___ _  _ ___   ___  ___   _ _____ \r\n" + //
                " |_ _| \\| |   \\ / _ \\/ __| /_\\_   _|\r\n" + //
                "  | || .` | |) | (_) \\__ \\/ _ \\| |  \r\n" + //
                " |___|_|\\_|___/ \\___/|___/_/ \\_\\_|  \r\n" + //
                "                                    ");
        System.out.println("----------------------------------------------------");
        System.out.println("====================================================");
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
            System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[0]);
            System.out.println("    [  -------------------------------------------------\t]");
            System.out.println("    ========================================================");
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
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[0]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[0], pulsa[0]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        menu();
                                    }else {
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
            } break;
            case 2:
            System.out.println("    =======================================================");
            System.out.println("    [  _________________________________________________\t]");
            System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
            System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
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
                                    System.out.println("    ======================================================");
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[1]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println("    =======================================================");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[0], pulsa[1]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        menu();
                                    }else {
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
            } break;
            case 3:
            System.out.println("    =======================================================");
            System.out.println("    [  _________________________________________________\t]");
            System.out.println("    [\t|        \tRINCIAN PEMBELIAN    \t\t|\t]");
            System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
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
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[2]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %s\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw] = String.format("Telah melakukan pembelian pulsa %s sebesar Rp. %s", operator[0], pulsa[2]);
                                    riw++;
                                    System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        menu();
                                    }else {
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
            } break;  
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
                                    }else {
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
            } break;
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
                                    }else {
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
            } break;  
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
                                    }else {
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
            } break;    
        }
    }
    private static void xl() {
        System.out.println("=============================");
        System.out.println("-----------------------------");
        System.out.println(" __  ___    \n" + //
                " \\ \\/ / |   \n" + //
                "  >  <| |__ \n" + //
                " /_/\\_\\____|\n" + //
                "            ");
        System.out.println("-----------------------------");
        System.out.println("=============================");
        System.out.print("Masukkan nomor telepon anda : 087-");
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

        switch(nomPul) {
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
                                    }else {
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
                indosat();
            } break;
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
                                    }else {
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
                indosat();
            } break;
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
                                    }else {
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
                indosat();
            } break;
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
                                    }else {
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
                indosat();
            } break;
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
                                    }else {
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
                indosat();
            } break;
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
                                    }else {
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
                indosat();
            } break;
        }
    }
    private static void telkomsel() {
        System.out.println("=================================================================");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("  _____ ___ _    _  _____  __  __ ___ ___ _    \n" + //
                " |_   _| __| |  | |/ / _ \\|  \\/  / __| __| |   \n" + //
                "   | | | _|| |__| ' < (_) | |\\/| \\__ \\ _|| |__ \n" + //
                "   |_| |___|____|_|\\_\\___/|_|  |_|___/___|____|\n" + //
                "                                               ");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("=================================================================");
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
                                    }else {
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
            } break;
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
                                    }else {
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
            } break;
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
                                    }else {
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
            } break;  
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
                                    }else {
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
            } break;
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
                                    }else {
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
            } break;
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
                                    }else {
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
            } break;
        }
    }
    private static void axis() {
        System.out.println("==========================================");
        System.out.println("------------------------------------------");
        System.out.println("    _   __  _____ ___ \n" + //
                "   /_\\  \\ \\/ /_ _/ __|\n" + //
                "  / _ \\  >  < | |\\__ \\\n" + //
                " /_/ \\_\\/_/\\_\\___|___/\n" + //
                "                      ");
        System.out.println("------------------------------------------");
        System.out.println("==========================================");
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
                                    }else {
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
            } break;
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
                                    }else {
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
            } break;
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
                                    }else {
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
            } break;  
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
                                    }else {
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
            } break;
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
                                        login();
                                    }else {
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
            } break;
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
                                    }else {
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
            } break;        
        }      
    }
    private static void bayarListrik() {
        String[][] dataListrik = {
            {"qwerty0987", "100000"},
            {"zxcvbn1234", "150000"},
            {"poiuyt5678", "130000"},
        };
        System.out.println("==========================================");
        System.out.println("------------------------------------------");
        System.out.println("  _    ___ ___ _____ ___ ___ _  __\r\n" + //
                " | |  |_ _/ __|_   _| _ \\_ _| |/ /\r\n" + //
                " | |__ | |\\__ \\ | | |   /| || ' < \r\n" + //
                " |____|___|___/ |_| |_|_\\___|_|\\_\\\r\n" + //
                "                                  ");
        System.out.println("------------------------------------------");
        System.out.println("==========================================");
        System.out.print("      [   Masukkan ID pelanggan PLN : ");
        String inputPln = input.next();

        int index1 = 0;
        if (inputPln.equals(dataListrik[index1][0])) {
            for (int i = 0; i < dataListrik.length; i++) {
                if (dataListrik[i][0].equals(inputPln)) {
                    index1 = 1;
                    System.out.println("    =======================================================");
                    System.out.println("    [  _________________________________________________\t]");
                    System.out.println("    [\t|        \tPembayaran Listrik    \t\t|\t]");
                    System.out.printf("    [\t|  ID Pelanggan\t\t\t: %s\t\t|\t]\n", dataListrik[i][0]);
                    System.out.printf("    [\t|  Total tagihan\t\t: Rp. %s\t|\t]\n", dataListrik[i][1]);
                    System.out.println("    [  -------------------------------------------------\t]");
                    System.out.println("    =======================================================");
                    System.out.println();
                    System.out.printf(" Konfirmasi transaksi tagihan listrik dengan ID pelanggan %s sebesar Rp. %s? y/t: ", dataListrik[i][0], dataListrik[i][1]);
                    pilih = input.next();
                    if (pilih.equals("y")) {
                        System.out.print("      [   Masukkan PIN anda untuk melanjutkan : ");
                        String inPin = input.next();
                        
                        int index = 0;
                        if (inPin.equals(dataNasabah[hasil][3])) {
                            for (int j = 0; j < dataNasabah.length; j++) {
                                if (dataNasabah[j][3].equals(inPin)) {
                                    index = 1;
                                    double sisaSaldo = Double.parseDouble(dataNasabah[hasil][5]);
                                    double nomListrik = Double.parseDouble(dataListrik[i][1]);
                                    if (sisaSaldo >= nomListrik) {
                                        sisaSaldo -= nomListrik;
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
            }
        }
    }
    } else {
        System.out.println("    ======================================================");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    |          ID PELANGGAN PLN INI TIDAK VALID.         |");
        System.out.println("    |----------------------------------------------------|");
        System.out.println("    ======================================================");
        System.out.println();
    }
}
    private static void riwayatTransaksi() {
        System.out.println("=====================================================================================");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("  ___ _____      _____   ___ _____   _____ ___    _   _  _ ___   _   _  _____ ___ \n" + //
                " | _ \\_ _\\ \\    / /_\\ \\ / /_\\_   _| |_   _| _ \\  /_\\ | \\| / __| /_\\ | |/ / __|_ _|\n" + //
                " |   /| | \\ \\/\\/ / _ \\ V / _ \\| |     | | |   / / _ \\| .` \\__ \\/ _ \\| ' <\\__ \\| | \n" + //
                " |_|_\\___| \\_/\\_/_/ \\_\\_/_/ \\_\\_|     |_| |_|_\\/_/ \\_\\_|\\_|___/_/ \\_\\_|\\_\\___/___|\n" + //
                "                                                                                  ");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("=====================================================================================");
        System.out.println("===================================================================================");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("|                         \\RIWAYAT TRANSAKSI TERBARU ANDA/                       |");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("===================================================================================");

        System.out.println("Riwayat Transaksi : ");
        for (int i = 0; i < riw; i++) {
            System.out.println(riwayat[i]);

            System.out.print("\n    [   Ingin melanjutkan transaksi y/t: ");
            pilih = input.next();
            if (pilih.equals("y")) {
                login();
            }else {
                System.out.println("    ======================================================");
                System.out.println("    |----------------------------------------------------|");
                System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
                System.out.println("    |----------------------------------------------------|");
                System.out.println("    ======================================================");
                break;
            }
        }
            System.out.println();
    }
    private static void pengaturanAkun() {
        System.out.println("==================================================================");
        System.out.println("------------------------------------------------------------------");
        System.out.println("  ___ ___ _  _  ___   _ _____ _   _ ___    _   _  _     _   _  ___   _ _  _ \r\n" + //
                " | _ \\ __| \\| |/ __| /_\\_   _| | | | _ \\  /_\\ | \\| |   /_\\ | |/ / | | | \\| |\r\n" + //
                " |  _/ _|| .` | (_ |/ _ \\| | | |_| |   / / _ \\| .` |  / _ \\| ' <| |_| | .` |\r\n" + //
                " |_| |___|_|\\_|\\___/_/ \\_\\_|  \\___/|_|_\\/_/ \\_\\_|\\_| /_/ \\_\\_|\\_\\\\___/|_|\\_|\r\n" + //
                "                                                                            ");
        System.out.println("------------------------------------------------------------------");
        System.out.println("==================================================================");
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
            if (inputPin.equals(dataNasabah[hasil][3])){
                for (int i = 0; i < dataNasabah.length; i++) {
                    if (dataNasabah[i][3].equals(inputPin)) {
                        index = 1;
                        System.out.print("      [   Masukkan nomor telepon anda : ");
                        String notlp = input.next();
                        if (notlp.equals(dataNasabah[i][7])){
                            if (dataNasabah[i][7].equals(notlp)) {
                                System.out.print("      [   Masukkan PIN baru anda : ");
                                String newpin = input.next();
                                dataNasabah[i][3] = newpin;
                                System.out.println("    ======================================================");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ UBAH PIN BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ |");
                                System.out.println("    |----------------------------------------------------|");
                                System.out.println("    ======================================================");
                                menu();
                            }
                        } else {
                            System.out.println("    ======================================================");
                            System.out.println("    |----------------------------------------------------|");
                            System.out.println("    |~ ~ ~ ~ ~ ~ ~ NOMOR TELEPON TIDAK VALID ~ ~ ~ ~ ~ ~ |");
                            System.out.println("    |----------------------------------------------------|");
                            System.out.println("    ======================================================");
                            pengaturanAkun();
                        }
                    }
                }
            } else {
                System.out.println("    ======================================================");
                System.out.println("    |----------------------------------------------------|");
                System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                System.out.println("    |----------------------------------------------------|");
                System.out.println("    ======================================================");
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
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |~ ~ ~ ~ ~ ~ UBAH NOMOR TELEPON BERHASIL ~ ~ ~ ~ ~ ~ |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                } else {
                                    System.out.println("    ======================================================");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    |~ ~ ~ ~ ~ ~ KONFIRMASI TELEPON TIDAK VALID  ~ ~ ~ ~ |");
                                    System.out.println("    |----------------------------------------------------|");
                                    System.out.println("    ======================================================");
                                    pengaturanAkun();
                                }
                            }
                        } else {
                            System.out.println("    ======================================================");
                            System.out.println("    |----------------------------------------------------|");
                            System.out.println("    |~ ~ ~ ~ ~ ~ ~ NOMOR TELEPON TIDAK VALID ~ ~ ~ ~ ~ ~ |");
                            System.out.println("    |----------------------------------------------------|");
                            System.out.println("    ======================================================");
                            pengaturanAkun();
                        }
                    }
                }
            } else {
                System.out.println("    ======================================================");
                System.out.println("    |----------------------------------------------------|");
                System.out.println("    |~ ~ ~ ~ ~ ~ ~ ~ ~ ~ PIN SALAH (!) ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
                System.out.println("    |----------------------------------------------------|");
                System.out.println("    ======================================================");
            } break;
            case 0:
            login();
            break;
            default:
            pengaturanAkun();
            break;
        }
    }
    public static void tutup() {
        if (pilih == "t" || pilih == "T") {
            System.out.println("    ======================================================");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    ======================================================");
        }
    }
    private static void keluarMenu() {
        System.out.print("      [   Apakah anda yakin ingin keluar y/t: ");
        pilih = input.next();
        if (pilih.equals("y")) {
            System.out.println("    ======================================================");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    |       TERIMAKASIH TELAH MENGGUNAKAN ATM INI :).    |");
            System.out.println("    |----------------------------------------------------|");
            System.out.println("    ======================================================");
        }else {
            menu();
        }
    }
}



