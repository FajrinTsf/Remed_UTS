import java.util.ArrayList;
import java.util.Scanner;

class MataKuliah {
    int no;
    String kode;
    String nama;
    int sks;
    String hurufMutu;
    double nilai;
    double bobot;

    public MataKuliah(int no, String kode, String nama) {
        this.no = no;
        this.kode = kode;
        this.nama = nama;
    }

    public void hitungHurufMutu() {
        if (nilai >= 76) {
            hurufMutu = "A";
        } else if (nilai >= 51) {
            hurufMutu = "B";
        } else if (nilai >= 26) {
            hurufMutu = "C";
        } else {
            hurufMutu = "D";
        }
    }

    public void hitungBobot() {
        switch (hurufMutu) {
            case "A":
                bobot = 4.0;
                break;
            case "B":
                bobot = 3.0;
                break;
            case "C":
                bobot = 2.0;
                break;
            case "D":
                bobot = 1.0;
                break;
            default:
                break;
        }
    }
}

class Mahasiswa {
    String nama;
    int semester;
    ArrayList<MataKuliah> nilaiMataKuliah = new ArrayList<>();

    public Mahasiswa(String nama, int semester) {
        this.nama = nama;
        this.semester = semester;
    }
}

public class UTSkhs {
    // Inisialisasi list mata kuliah dengan data default
    static ArrayList<MataKuliah> mataKuliahDefault = new ArrayList<>();
    static {
        mataKuliahDefault.add(new MataKuliah(1, "PS0101", "Nilai Dasar Shalih Akram"));
        mataKuliahDefault.add(new MataKuliah(2, "PS0102", "Teologi Aswaja"));
        mataKuliahDefault.add(new MataKuliah(3, "PS0104", "Civic Education"));
        mataKuliahDefault.add(new MataKuliah(4, "PS0201", "Ulumul Qur'an"));
        mataKuliahDefault.add(new MataKuliah(5, "PS0203", "Sejarah Pemikiran dan Keuangan Perbankan"));
        mataKuliahDefault.add(new MataKuliah(6, "PS0205", "Bahasa Arab I"));
        mataKuliahDefault.add(new MataKuliah(7, "PS0207", "Bahasa Inggris I"));
        mataKuliahDefault.add(new MataKuliah(8, "PS0209", "Pengantar Ekonomi Mikro"));
        mataKuliahDefault.add(new MataKuliah(9, "PS0218", "Manajemen Syariah"));
        mataKuliahDefault.add(new MataKuliah(10, "PS0227", "Pengantar Filsafat"));
        mataKuliahDefault.add(new MataKuliah(11, "PS0416", "Bahasa Indonesia"));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input data Mahasiswa
        System.out.print("Masukkan Nama Mahasiswa: ");
        String namaMahasiswa = scanner.nextLine();

        System.out.print("Masukkan Semester: ");
        int semesterMahasiswa = scanner.nextInt();
        scanner.nextLine();

        // Inisialisasi objek Mahasiswa
        Mahasiswa mahasiswa = new Mahasiswa(namaMahasiswa, semesterMahasiswa);

        // Memasukkan nilai mata kuliah
        for (MataKuliah mk : mataKuliahDefault) {
            System.out.println("Input Nilai Mata Kuliah:");
            System.out.println("Kode MK   : " + mk.kode);
            System.out.println("Mata Kuliah: " + mk.nama);

            System.out.print("Masukkan SKS: ");
            int sks = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Masukkan Nilai: ");
            double nilai = scanner.nextDouble();
            scanner.nextLine();

            mk.sks = sks;
            mk.nilai = nilai;
            mk.hitungHurufMutu();
            mk.hitungBobot();

            mahasiswa.nilaiMataKuliah.add(mk);
            System.out.println("Nilai berhasil dimasukkan.\n");
        }

        // Menampilkan KHS
        tampilkanKHS(mahasiswa);

        scanner.close();
    }

    private static void tampilkanKHS(Mahasiswa mahasiswa) {
        System.out.println("Kartu Hasil Studi (KHS) Mahasiswa");
        System.out.println("Nama Mahasiswa: " + mahasiswa.nama);
        System.out.println("Semester: " + mahasiswa.semester);
        System.out.println();
        System.out.println(
                "No | Kode MK   | Mata Kuliah                                    | SKS | Huruf Mutu  | Bobot | Nilai");
        System.out.println(
                "------------------------------------------------------------------------------------------------");

        int no = 1;
        for (MataKuliah mk : mahasiswa.nilaiMataKuliah) {
            System.out.printf("%-3d| %-10s | %-45s | %-3d | %-11s | %-5.1f | %-3.0f\n",
                    no, mk.kode, mk.nama, mk.sks, mk.hurufMutu, mk.bobot, mk.nilai);
            no++;
        }

        // Menghitung Jumlah SKS
        int jumlahSKS = mahasiswa.nilaiMataKuliah.stream().mapToInt(mk -> mk.sks).sum();

        // Menghitung IP Semester (Rata Rata Bobot)
        double totalBobot = mahasiswa.nilaiMataKuliah.stream().mapToDouble(mk -> mk.bobot).sum();
        double ipSemester = totalBobot / jumlahSKS;

        // Menghitung SKS Maksimal yang bisa diambil
        int sksMaksimal = 47;
        int sisaSKSMaksimal = sksMaksimal - jumlahSKS;

        System.out.println();
        System.out.println("Jumlah SKS                                                      | " + jumlahSKS);
        System.out.printf("IP Semester (Rata Rata Bobot)                                   | %.2f\n", ipSemester);
        System.out.println("SKS Maksimal                                                    | " + sisaSKSMaksimal);
    }
}
