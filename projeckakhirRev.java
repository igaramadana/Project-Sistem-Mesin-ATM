import java.util.Scanner;

public class projeckakhirRev {
    static Scanner input = new Scanner(System.in);
    static boolean login = false, valid = false, masuk = false;
    static String pilih;
    static String[][] dataNasabah = {
        {"01", "112233", "Annisa", "1122", "Nganjuk", "1000000", "Verified", "085123456"},
        {"02", "334455", "Iga", "3344", "Dampit", "5000000", "Verified", "081123456"},
        {"03", "556677", "Firman", "4455", "Blitar", "3500000", "Verified", "087123456"},
    };
    static int[] pulsa = {22500, 27500, 52500, 102500, 502500, 1002500};
    static String[] operator = {"Indosat", "XL", "Telkomsel", "Axis"};
    static int riw = 10, count = 10, hasil = 0;
    static String[] riwayat = new String[riw];
    static int loginAttempts = 0;
    static final int maxLoginAttempts = 3;
    public static void main(String[] args) {
        login();
    }
    private static void login() {
        System.out.println("==================================================================================================");
        System.out.println("[ ____  _____ _     ____  _      ____  _____    ____  ____  _____  ____  _      _____   ____  _  ]");
        System.out.println("[/ ___\\/  __// \\   /  _ \\/ \\__/|/  _ \\/__ __\\  /  _ \\/  _ \\/__ __\\/  _ \\/ \\  /|/  __/  /  _ \\/ \\ ]");
        System.out.println("[|    \\|  \\  | |   | / \\|| |\\/||| / \\|  / \\    | | \\|| / \\|  / \\  | / \\|| |\\ ||| |  _  | | \\|| | ]");
        System.out.println("[\\___ ||  /_ | |_/\\| |-||| |  ||| |-||  | |    | |_/|| |-||  | |  | |-||| | \\||| |_//  | |_/|| | ]");
        System.out.println("[\\____/\\____\\\\____/\\_/ \\|\\_/  \\|\\_/ \\|  \\_/    \\____/\\_/ \\|  \\_/  \\_/ \\|\\_/  \\|\\____\\  \\____/\\_/ ]");
        System.out.println("==================================================================================================");
        System.out.println("[\t\t\t ____  _____  _        ____  _  ____        _  _____                  ]");
        System.out.println("[\t\t\t/  _ \\/__ __\\/ \\__/|  / ___\\/ \\/  __\\      / \\/  __/                  ]");
        System.out.println("[\t\t\t| / \\|  / \\  | |\\/||  |    \\| || | //_____ | ||  \\                    ]");
        System.out.println("[\t\t\t| |-||  | |  | |  ||  \\___ || || |_\\\\\\____\\| ||  /_                   ]");
        System.out.println("[\t\t\t\\_/ \\|  \\_/  \\_/  \\|  \\____/\\_/\\____/      \\_/\\____\\                  ]");
        System.out.println("===============================================================================================");
        System.out.println();

        // Cek Login
        while (loginAttempts <= maxLoginAttempts) {
            System.out.println("===============================================================================================");
            System.out.println();
            System.out.print("      [   Masukkan PIN anda : ");
            String inputPin = input.next();
            System.out.println();
            System.out.println("===============================================================================================");
            System.out.println();
            

            // Pengecekan verified/diblokir
            if (hasil != -1 && dataNasabah[hasil][6].equals("Diblokir")) {
                System.out.println("        [   Status akun anda " + dataNasabah[hasil][6]);
                System.out.println("===============================================================================================");
            }

            // Pengecekan kesesuaian PIN login
            for (int i = 0; i < dataNasabah.length; i++) {
                if (inputPin.equals(dataNasabah[i][3]) && dataNasabah[i][6].equals("Verified")) {
                    login = true;
                    hasil = i;
                }
            }
            
            if (login) {
                do {
                    System.out.println("===============================================================================================");
                    System.out.println("""
                            [       Silahkan pilih menu dibawah ini :
                            [       1. Data Nasabah
                            [       2. Cek Saldo
                            [       3. Tarik Tunai
                            [       4. Setor Tunai
                            [       5. Pembayaran Lain.
                            """);
                    System.out.print("\tMenu yang dipilih : ");
                    int menu = input.nextInt();

                    switch (menu) {
                        case 1 -> dataNasabah();
                        case 2 -> cekSaldo();
                        case 3 -> tarikTunai();
                        case 4 -> setorTunai();
                        case 5 -> pembayaranLain();
                        case 0 -> keluarMenu();
                        default -> login = true;
                    } break;     
                }while(login);
                    System.out.println();
                } else {
                    System.out.println("===============================================================================================");
                    System.out.println("_______________________________________________________________________________________________");
                    System.out.println();
                    System.out.println("          [   (!) Anda memasukkan PIN yang salah, Silahkan masukkan kembali. (!)  ]            ");
                    System.out.println("_______________________________________________________________________________________________");
                    System.out.println("===============================================================================================");
                    loginAttempts++;

                    if (loginAttempts >= maxLoginAttempts) {
                        System.out.println("===============================================================================================");
                        System.out.println("_______________________________________________________________________________________________");
                        System.out.println();
                        System.out.println("             [   (!) Anda telah gagal lebih dari 3 kali. Akun anda diblokir. (!)  ]            ");
                        System.out.println("_______________________________________________________________________________________________");
                        System.out.println("===============================================================================================");
                        dataNasabah[hasil][6] = "Disabled";
                    }
                }
                break;
            }
        }
    private static void dataNasabah() {
        System.out.println("=====================================================================================");
        System.out.println();
        System.out.println("  ___      _     _____     _       _  _     _     ___     _     ___     _     _  _ \r\n" + //
                " |   \\    /_\\   |_   _|   /_\\     | \\| |   /_\\   / __|   /_\\   | _ )   /_\\   | || |\r\n" + //
                " | |) |  / _ \\    | |    / _ \\    | .` |  / _ \\  \\__ \\  / _ \\  | _ \\  / _ \\  | __ |\r\n" + //
                " |___/  /_/ \\_\\   |_|   /_/ \\_\\   |_|\\_| /_/ \\_\\ |___/ /_/ \\_\\ |___/ /_/ \\_\\ |_||_|\r\n" + //
                "                                                                                   ");
        System.out.println("=====================================================================================");
        System.out.print("      [   Masukkan PIN anda : ");
        String inPin = input.next();
        
        int index = 0;
        if (inPin.equals(dataNasabah[hasil][3])) {
            for (int i = 0; i < dataNasabah.length; i++) {
                if (dataNasabah[i][3].equals(inPin)) {
                index = 1;
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

                System.out.println();
                System.out.print("\nIngin melanjutkan transaksi y/t: ");
                    pilih = input.next();
                    if (pilih.equals("y")) {
                        login();
                    }else {
                        System.out.println("=============================================");
                        System.out.println("    Terima Kasih telah menggunakan ATM :)    ");
                        System.out.println("            Sampai jumpa lagi                ");
                    }
                        System.out.println();
            }
        } 
        } else {
            index = 0;
            System.out.println(
                    "--------------------------------------------------------------------------------------------");
            System.out
                    .println("                                   [  (!) PIN SALAH! (!)  ]");
            System.out.println(
                    "--------------------------------------------------------------------------------------------");
            System.out.println(
                    "============================================================================================");
        }
        
        }
    private static void cekSaldo() {
        System.out.println("=====================================================================================");
        System.out.println("   ___   ___   _  __    ___     _     _      ___     ___  \r\n" + //
                "  / __| | __| | |/ /   / __|   /_\\   | |    |   \\   / _ \\ \r\n" + //
                " | (__  | _|  | ' <    \\__ \\  / _ \\  | |__  | |) | | (_) |\r\n" + //
                "  \\___| |___| |_|\\_\\   |___/ /_/ \\_\\ |____| |___/   \\___/ \r\n" + //
                "                                                          ");
        System.out.println("=====================================================================================");
        System.out.print("      [   Masukkan PIN anda : ");
        String inPin = input.next();

        int index = 0;
        if (inPin.equals(dataNasabah[hasil][3])) {
            for (int i = 0; i < dataNasabah.length; i++) {
                if (dataNasabah[i][3].equals(inPin)) {
                    index = 1;
                    System.out.println("Nama\t\t: " + dataNasabah[i][2]);
                    System.out.println("Saldo anda\t: Rp. " + dataNasabah[i][5]);
                    System.out.println("=============================================");
                    System.out.println();
                    System.out.print("\nIngin melanjutkan transaksi y/t: ");
                    pilih = input.next();
                    if (pilih.equals("y")) {
                        login();
                    }else {
                        System.out.println("=============================================");
                        System.out.println("    Terima Kasih telah menggunakan ATM :)    ");
                        System.out.println("            Sampai jumpa lagi                ");
                    }
                        System.out.println();
                }
            }
        } else {
            index = 0;
            System.out.println(
                    "--------------------------------------------------------------------------------------------");
            System.out
                    .println("                                   [  (!) PIN SALAH! (!)  ]");
            System.out.println(
                    "--------------------------------------------------------------------------------------------");
            System.out.println(
                    "============================================================================================");
        }
    }
    private static void tarikTunai() {
        System.out.println("=====================================================================================");
        System.out.println("  _____     _     ___   ___   _  __    _____   _   _   _  _     _     ___ \r\n" + //
                " |_   _|   /_\\   | _ \\ |_ _| | |/ /   |_   _| | | | | | \\| |   /_\\   |_ _|\r\n" + //
                "   | |    / _ \\  |   /  | |  | ' <      | |   | |_| | | .` |  / _ \\   | | \r\n" + //
                "   |_|   /_/ \\_\\ |_|_\\ |___| |_|\\_\\     |_|    \\___/  |_|\\_| /_/ \\_\\ |___|\r\n" + //
                "                                                                          ");
        System.out.println("=====================================================================================");
        System.out.print("      [   Masukkan PIN anda : ");
        String inPin = input.next();

        int index = -1;
        if (inPin.equals(dataNasabah[hasil][3])) {
            for (int i = 0; i < dataNasabah.length; i++) {
                if (dataNasabah[i][3].equals(inPin)) {
                    index = i;
                System.out.print("Masukkan jumlah saldo yang ingin ditarik: ");
                double jumlahTarik = input.nextDouble();
                double currentBalance = Double.parseDouble(dataNasabah[index][5]);
                
                if (jumlahTarik > 0 && jumlahTarik <= currentBalance) {
                    currentBalance -= jumlahTarik;
                    dataNasabah[index][5] = String.valueOf(currentBalance);
                    System.out.println("Anda telah berhasil melakukan penarikan.");
                    System.out.println("Nama\t\t: " + dataNasabah[i][2]);
                    System.out.println("Sisa saldo anda\t: Rp. " + dataNasabah[i][5]);
                    System.out.println("=============================================");
                    System.out.println();
                    System.out.print("\nIngin melanjutkan transaksi y/t: ");
                    pilih = input.next();
                    if (pilih.equals("y")) {
                        login();
                    }else {
                        System.out.println("=============================================");
                        System.out.println("    Terima Kasih telah menggunakan ATM :)    ");
                        System.out.println("            Sampai jumpa lagi                ");
                    }
                        System.out.println();
                        riwayat[riw - count] = "Telah melakukan tarik tunai sebesar "
                                + dataNasabah[i][5];
                        count--;
                } else {
                    System.out.println("================================================");
                    System.out.println("Saldo tidak mencukupi untuk melakukan penarikan.");
                    System.out.println("================================================");
                    System.out.println();
                }
                }
            }
        } else {
            index = -1;
            System.out.println(
                    "--------------------------------------------------------------------------------------------");
            System.out
                    .println("                                   [  (!) PIN SALAH! (!)  ]");
            System.out.println(
                    "--------------------------------------------------------------------------------------------");
            System.out.println(
                    "============================================================================================");
    }
}
    private static void setorTunai() {
        System.out.println("=====================================================================================");
        System.out.println("  ___   ___   _____    ___    ___     _____   _   _   _  _     _     ___ \r\n" + //
                " / __| | __| |_   _|  / _ \\  | _ \\   |_   _| | | | | | \\| |   /_\\   |_ _|\r\n" + //
                " \\__ \\ | _|    | |   | (_) | |   /     | |   | |_| | | .` |  / _ \\   | | \r\n" + //
                " |___/ |___|   |_|    \\___/  |_|_\\     |_|    \\___/  |_|\\_| /_/ \\_\\ |___|\r\n" + //
                "                                                                         ");
        System.out.println("=====================================================================================");
        System.out.print("      [   Masukkan PIN anda : ");
        String inPin = input.next();

        int index = 0;
        if (inPin.equals(dataNasabah[hasil][3])) {
            for (int i = 0; i < dataNasabah.length; i++) {
                if (dataNasabah[i][3].equals(inPin)) {
                    index = i;
                    System.out.print("Masukkan nominal setor tunai : Rp. ");
                    int nomSetor = input.nextInt();
                    int saldoUser = Integer.parseInt(dataNasabah[hasil][5]);

                    saldoUser += nomSetor;
                    dataNasabah[i][5] = String.valueOf(saldoUser);
                    System.out.println("Anda telah berhasil melakukan penarikan.");
                    System.out.println("Nama\t\t: " + dataNasabah[i][2]);
                    System.out.println("Sisa saldo anda\t: Rp. " + saldoUser);
                    System.out.println("=============================================");
                    System.out.println();
                    riwayat[riw - count] = "telah melakukan setor tunai sebesar " + nomSetor;
                    count--;
                    System.out.print("\nIngin melanjutkan transaksi y/t: ");
                    pilih = input.next();
                    if (pilih.equals("y")) {
                        login();
                    }else {
                        System.out.println("=============================================");
                        System.out.println("    Terima Kasih telah menggunakan ATM :)    ");
                        System.out.println("            Sampai jumpa lagi                ");
                    }
                }
            }
        } else {
            index = 0;
            System.out.println(
                    "--------------------------------------------------------------------------------------------");
            System.out
                    .println("                                   [  (!) PIN SALAH! (!)  ]");
            System.out.println(
                    "--------------------------------------------------------------------------------------------");
            System.out.println(
                    "============================================================================================");
    }
    }
    private static void pembayaranLain() {
        System.out.println("=====================================================================================");
        System.out.println("  ___   ___   __  __   ___     _    __   __    _     ___     _     _  _     _        _     ___   _  _ \r\n" + //
                " | _ \\ | __| |  \\/  | | _ )   /_\\   \\ \\ / /   /_\\   | _ \\   /_\\   | \\| |   | |      /_\\   |_ _| | \\| |\r\n" + //
                " |  _/ | _|  | |\\/| | | _ \\  / _ \\   \\ V /   / _ \\  |   /  / _ \\  | .` |   | |__   / _ \\   | |  | .` |\r\n" + //
                " |_|   |___| |_|  |_| |___/ /_/ \\_\\   |_|   /_/ \\_\\ |_|_\\ /_/ \\_\\ |_|\\_|   |____| /_/ \\_\\ |___| |_|\\_|\r\n" + //
                "                                                                                                      ");
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
        }

        
    }
    private static void beliPulsa() {
        System.out.println("=====================================================================================");
        System.out.println("  ___ ___ __  __ ___ ___ _    ___   _   _  _   ___ _   _ _    ___   _   \r\n" + //
                " | _ \\ __|  \\/  | _ ) __| |  |_ _| /_\\ | \\| | | _ \\ | | | |  / __| /_\\  \r\n" + //
                " |  _/ _|| |\\/| | _ \\ _|| |__ | | / _ \\| .` | |  _/ |_| | |__\\__ \\/ _ \\ \r\n" + //
                " |_| |___|_|  |_|___/___|____|___/_/ \\_\\_|\\_| |_|  \\___/|____|___/_/ \\_\\\r\n" + //
                "                                                                        ");
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
        }
    }
    private static void indosat() {
        System.out.println("=====================================================================================");
        System.out.println("  ___ _  _ ___   ___  ___   _ _____ \r\n" + //
                " |_ _| \\| |   \\ / _ \\/ __| /_\\_   _|\r\n" + //
                "  | || .` | |) | (_) \\__ \\/ _ \\| |  \r\n" + //
                " |___|_|\\_|___/ \\___/|___/_/ \\_\\_|  \r\n" + //
                "                                    ");
        System.out.println("=====================================================================================");
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
            System.out.println("    [  _________________________________________________\t]");
            System.out.println("    [\t|        \tRINCIAN PEMBAYARAN    \t\t|\t]");
            System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
            System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
            System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[0]);
            System.out.println("    [  -------------------------------------------------\t]");
            System.out.println();
            System.out.println("Total bayar diatas sudah termasuk pajak Rp. 2500");
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
                                int sisaSaldo = Integer.parseInt(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[0];
                                    System.out.println(
                                            "    --------------------------------------------------------------------------------------------");
                                    System.out.println(
                                            "     ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println(
                                            "    --------------------------------------------------------------------------------------------");
                                    System.out.println(
                                            "    ============================================================================================");
                                    System.out.println();
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[0]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %d\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw - count] = "Telah melakukan pembelian pulsa senilai "
                                        + pulsa[0];
                                        count--;
                                    System.out.print("\nIngin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    }else {
                                        System.out.println("=============================================");
                                        System.out.println("    Terima Kasih telah menggunakan ATM :)    ");
                                        System.out.println("            Sampai jumpa lagi                ");
                                    }
                                } else {
                                    System.out.println("================================================");
                                    System.out.println("Saldo tidak mencukupi untuk melakukan pembelian pulsa.");
                                    System.out.println("================================================");
                                    System.out.println();
                            }
                        }
                    }
                } else {
                    index = 0;
                    System.out.println(
                            "--------------------------------------------------------------------------------------------");
                    System.out
                            .println("                                   [  (!) PIN SALAH! (!)  ]");
                    System.out.println(
                            "--------------------------------------------------------------------------------------------");
                    System.out.println(
                            "============================================================================================");
                 }
            } else {
                indosat();
            } break;
            case 2:
            System.out.println("    [  _________________________________________________\t]");
            System.out.println("    [\t|        \tRINCIAN PEMBAYARAN    \t\t|\t]");
            System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
            System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
            System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[1]);
            System.out.println("    [  -------------------------------------------------\t]");
            System.out.println();
            System.out.println("Total bayar diatas sudah termasuk pajak Rp. 2500");
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
                                int sisaSaldo = Integer.parseInt(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[1];
                                    System.out.println(
                                            "    --------------------------------------------------------------------------------------------");
                                    System.out.println(
                                            "     ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println(
                                            "    --------------------------------------------------------------------------------------------");
                                    System.out.println(
                                            "    ============================================================================================");
                                    System.out.println();
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[1]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %d\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw - count] = "Telah melakukan pembelian pulsa senilai "
                                        + pulsa[1];
                                        count--;
                                    System.out.print("\nIngin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    }else {
                                        System.out.println("=============================================");
                                        System.out.println("    Terima Kasih telah menggunakan ATM :)    ");
                                        System.out.println("            Sampai jumpa lagi                ");
                                    }
                                } else {
                                    System.out.println("================================================");
                                    System.out.println("Saldo tidak mencukupi untuk melakukan pembelian pulsa.");
                                    System.out.println("================================================");
                                    System.out.println();
                            }
                        }
                    }
                } else {
                    index = 0;
                    System.out.println(
                            "--------------------------------------------------------------------------------------------");
                    System.out
                            .println("                                   [  (!) PIN SALAH! (!)  ]");
                    System.out.println(
                            "--------------------------------------------------------------------------------------------");
                    System.out.println(
                            "============================================================================================");
                 }
            } else {
                indosat();
            } break;
            case 3:
            System.out.println("    [  _________________________________________________\t]");
            System.out.println("    [\t|        \tRINCIAN PEMBAYARAN    \t\t|\t]");
            System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
            System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
            System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[2]);
            System.out.println("    [  -------------------------------------------------\t]");
            System.out.println();
            System.out.println("Total bayar diatas sudah termasuk pajak Rp. 2500");
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
                                int sisaSaldo = Integer.parseInt(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[2];
                                    System.out.println(
                                            "    --------------------------------------------------------------------------------------------");
                                    System.out.println(
                                            "     ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println(
                                            "    --------------------------------------------------------------------------------------------");
                                    System.out.println(
                                            "    ============================================================================================");
                                    System.out.println();
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[2]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %d\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw - count] = "Telah melakukan pembelian pulsa senilai "
                                        + pulsa[2];
                                        count--;
                                    System.out.print("\nIngin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    }else {
                                        System.out.println("=============================================");
                                        System.out.println("    Terima Kasih telah menggunakan ATM :)    ");
                                        System.out.println("            Sampai jumpa lagi                ");
                                    }
                                } else {
                                    System.out.println("================================================");
                                    System.out.println("Saldo tidak mencukupi untuk melakukan pembelian pulsa.");
                                    System.out.println("================================================");
                                    System.out.println();
                            }
                        }
                    }
                } else {
                    index = 0;
                    System.out.println(
                            "--------------------------------------------------------------------------------------------");
                    System.out
                            .println("                                   [  (!) PIN SALAH! (!)  ]");
                    System.out.println(
                            "--------------------------------------------------------------------------------------------");
                    System.out.println(
                            "============================================================================================");
                 }
            } else {
                indosat();
            } break;  
            case 4:
            System.out.println("    [  _________________________________________________\t]");
            System.out.println("    [\t|        \tRINCIAN PEMBAYARAN    \t\t|\t]");
            System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
            System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
            System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[3]);
            System.out.println("    [  -------------------------------------------------\t]");
            System.out.println();
            System.out.println("Total bayar diatas sudah termasuk pajak Rp. 2500");
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
                                int sisaSaldo = Integer.parseInt(dataNasabah[hasil][5]);
                                if (sisaSaldo >= pulsa[i]) {
                                    sisaSaldo -= pulsa[3];
                                    System.out.println(
                                            "    --------------------------------------------------------------------------------------------");
                                    System.out.println(
                                            "     ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ TRANSAKSI BERHASIL ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");
                                    System.out.println(
                                            "    --------------------------------------------------------------------------------------------");
                                    System.out.println(
                                            "    ============================================================================================");
                                    System.out.println();
                                    System.out.println("    [  ________________________________________________\t]");
                                    System.out.println("    [\t|           \tRINCIAN PEMBAYARAN \t\t|\t]");
                                    System.out.printf("    [\t|  Operator seluler\t\t: %s\t|\t]\n", operator[0]);
                                    System.out.printf("    [\t|  Nomor telepon\t\t: %s\t|\t]\n", "085-" + nomTelp);
                                    System.out.printf("    [\t|  Total bayar pulsa\t\t: Rp. %d\t|\t]\n", pulsa[3]);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.printf("    [\t|  Sisa saldo anda\t\t: Rp. %d\t|\t]\n", sisaSaldo);
                                    System.out.println("    [  -------------------------------------------------\t]");
                                    System.out.println();
                                    // catatan riwayat transaksi
                                    riwayat[riw - count] = "Telah melakukan pembelian pulsa senilai "
                                        + pulsa[3];
                                        count--;
                                    System.out.print("\nIngin melanjutkan transaksi y/t: ");
                                    pilih = input.next();
                                    if (pilih.equals("y")) {
                                        login();
                                    }else {
                                        System.out.println("=============================================");
                                        System.out.println("    Terima Kasih telah menggunakan ATM :)    ");
                                        System.out.println("            Sampai jumpa lagi                ");
                                    }
                                } else {
                                    System.out.println("================================================");
                                    System.out.println("Saldo tidak mencukupi untuk melakukan pembelian pulsa.");
                                    System.out.println("================================================");
                                    System.out.println();
                            }
                        }
                    }
                } else {
                    index = 0;
                    System.out.println(
                            "--------------------------------------------------------------------------------------------");
                    System.out
                            .println("                                   [  (!) PIN SALAH! (!)  ]");
                    System.out.println(
                            "--------------------------------------------------------------------------------------------");
                    System.out.println(
                            "============================================================================================");
                 }
            } else {
                indosat();
            } break;      
        }
    }
    private static void keluarMenu() {
        System.out.print("Apakah anda yakin ingin keluar y/t: ");
        pilih = input.next();
        if (pilih.equals("y")) {
            System.out.println("=============================================");
            System.out.println("    Terima Kasih telah menggunakan ATM :)    ");
            System.out.println("            Sampai jumpa lagi                ");
        }else {
            login();
        }
    }
}



