import java.util.Date;
import com.google.gson.annotations.SerializedName;

/**
 * Kelas model untuk menyimpan data transaksi keuangan
 */
public class Transaksi {
    @SerializedName("id")
    private int id; // ID unik transaksi
    
    @SerializedName("tanggal")
    private Date tanggal; // Tanggal transaksi
    
    @SerializedName("jumlah")
    private double jumlah; // Jumlah uang
    
    @SerializedName("kategori")
    private String kategori; // Kategori (Pendapatan/Pengeluaran)
    
    @SerializedName("deskripsi")
    private String deskripsi; // Keterangan transaksi

    // Konstruktor kosong
    public Transaksi() {
    }

    // Konstruktor dengan parameter
    public Transaksi(Date tanggal, double jumlah, String kategori, String deskripsi) {
        this.tanggal = tanggal;
        this.jumlah = jumlah;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}