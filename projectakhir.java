import java.util.Scanner;

public class projectakhir {
    static Scanner input = new Scanner(System.in);

    // Data
    static int[] id = {01, 02, 03, 04, 05};
    static int[] noRek = {112233, 334455, 556677, 889911, 123456};
    static int[] newNoRek = new int [100];
    static String[] nama = {"Annisa", "Iga", "Firman", "Dzaki", "Rama"};
    static String[] newNama = new String[100];
    static int[] pin = {1122, 2233, 3344, 4455, 5566};
    static String[] alamat = {"Nganjuk", "Dampit", "Blitar", "Klojen", "Sukun"};
    static int[] saldo = {1000000, 5000000, 3500000, 2500000, 4000000};
    static int[] newSaldo = new int[100];
    static String pilih;
    static boolean login = false;
    static int countData, countCekPrib, countCek, countPerubahan, countTF, countLupaPIN;

public static void main(String[] args) {
        login();
    }

private static void login() {
        System.out.println("Masukkan PIN anda: ");
        int pinLogin = input.nextInt();

        for (int i = 0; i < pin.length; i++) {
            if (pinLogin == pin[i]) {
                login = true;
                break;
            }
        }

        if (login) {
            do {
                System.out.println("=============================================");
                System.out.println("""
                        Silahkan pilih menu di bawah ini :
                        1. Data Nasabah
                        2. Cek Saldo
                        3. Tarik Tunai
                        0. Keluar""");
                System.out.print("Menu : ");
                int menu = input.nextInt();

                switch (menu) {
                    case 0 -> keluarMenu();
                    case 1 -> dataNasabah();
                    case 2 -> cekSaldo();
                    case 3 -> tarikSaldo();
                    default -> login = true;
                } break;
            } while (login);
            System.out.println("=============================================");
            
        } else {
            System.out.println("Anda Memasukkan PIN yang salah, Silahkan Coba Lagi...");
        }
    }

private static void dataNasabah() {
        System.out.println("=============================================");
        System.out.println("|             Menu Data Nasabah             |");
        System.out.println("=============================================");
        System.out.print("Masukkan PIN Nasabah: ");
        int pinNasabah = input.nextInt();
    
        int index = -1;
        for (int i = 0; i < pin.length; i++) {
            if (pinNasabah == pin[i]) {
                index = i;
                break; 
            }
        }
    
        if (index != -1) {
            System.out.println("Nama\t|No.Rek\t\t|ID Pengguna\t|\tAlamat\t|");
            System.out.println("---------------------------------------------------------");
            System.out.print(nama[index] + "\t|");
            System.out.print("\t" + noRek[index] + "\t|");
            System.out.print("\t" + id[index] + "\t|");
            System.out.print("\t" + alamat[index] + "\t|");
            System.out.print("\n\nIngin melanjutkan transaksi y/t: ");
            pilih = input.next();
            if (pilih.equals("y")) {
                login();
            }else {
                System.out.println("=============================================");
                System.out.println("\nTerima Kasih telah menggunakan ATM :)");
            }
            System.out.println();
        } else {
            System.out.println("PIN Nasabah tidak ditemukan.");
        }
    
    }
private static void cekSaldo() {
    System.out.println("=============================================");
    System.out.println("|              Menu Cek Saldo               |");
    System.out.println("=============================================");
    System.out.print("Masukkan nomor rekening anda : ");
    int no = input.nextInt();

    int test = 0;
        for (int i = 0; i < noRek.length; i++) {
            if (no == noRek[i]) {
                test = 1;
                System.out.println("Nama\t\t: " + nama[i]);
                System.out.println("Saldo anda\t: Rp. " + saldo[i]);
                System.out.println("=============================================");
                System.out.print("\nIngin melanjutkan transaksi y/t: ");
                pilih = input.next();
                if (pilih.equals("y")) {
                    login();
                }else {
                System.out.println("=============================================");
                System.out.println("\nTerima Kasih telah menggunakan ATM :)");
                }
                System.out.println();
            }else if (no == newNoRek[i]) {
                test = 1;
                System.out.println("Nama\t\t: " + newNama[i]);
                System.out.println("Saldo anda\t: Rp. " + newSaldo[i]);
                System.out.println("=============================================");
                System.out.println();
            }
        }

        if (test == 0) {
            System.out.println("Nomor rekening tidak valid");
            System.out.println("=============================================");
            System.out.println();
            cekSaldo();
        }
        countCek++;
}
private static void tarikSaldo() {
    System.out.println("=============================================");
    System.out.println("|              Menu Tarik Tunai             |");
    System.out.println("=============================================");
    System.out.print("Masukkan nomor rekening anda : ");
    int ts = input.nextInt();

    int test = 0;
    for (int i = 0; i < noRek.length; i++) {
        if (ts == noRek[i]) {
            test = 1;
            System.out.print("Masukkan jumlah saldo yang ingin ditarik: ");
            int jumlahTarik = input.nextInt();
            
            if (saldo[i] >= jumlahTarik) {
                saldo[i] -= jumlahTarik; 
                System.out.println("Anda telah berhasil melakukan penarikan.");
                System.out.println("Nama\t\t: " + nama[i]);
                System.out.println("Sisa saldo anda\t: Rp. " + saldo[i]);
                System.out.println("=============================================");
                System.out.print("\nIngin melanjutkan transaksi y/t: ");
                pilih = input.next();
                if (pilih.equals("y")) {
                    login();
                }else {
                System.out.println("=============================================");
                System.out.println("\nTerima Kasih telah menggunakan ATM :)");
                }
                System.out.println();
            } else {
                System.out.println("Saldo tidak mencukupi untuk melakukan penarikan.");
                System.out.println("=============================================");
                System.out.print("\nIngin melanjutkan transaksi y/t: ");
                pilih = input.next();
                if (pilih.equals("y")) {
                    tarikSaldo();
                }else {
                System.out.println("=============================================");
                System.out.println("\nTerima Kasih telah menggunakan ATM :)");
                }
                System.out.println();
            }
            break;
        }
    }

    if (test == 0) {
        System.out.println("Nomor rekening tidak valid");
        System.out.println("=============================================");
        System.out.println();
    }
    countTF++;
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
    
