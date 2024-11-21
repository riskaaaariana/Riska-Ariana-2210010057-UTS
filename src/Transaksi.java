import java.util.Date;
import com.google.gson.annotations.SerializedName;

/**
 * Kelas Transaksi - Merepresentasikan data transaksi keuangan
 * Menerapkan konsep encapsulation dengan private fields dan public methods
 */
public class Transaksi {
    // Properti private untuk encapsulation
    @SerializedName("id")
    private int id;               // ID unik untuk setiap transaksi
    
    @SerializedName("tanggal")
    private Date tanggal;         // Tanggal dilakukannya transaksi
    
    @SerializedName("jumlah") 
    private double jumlah;        // Jumlah uang dalam transaksi
    
    @SerializedName("kategori")
    private String kategori;      // Kategori transaksi (Pendapatan/Pengeluaran)
    
    @SerializedName("deskripsi")
    private String deskripsi;     // Keterangan detail transaksi

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

    // Getter dan Setter dengan validasi dan dokumentasi
    /**
     * Mendapatkan ID transaksi
     * @return ID transaksi
     */
    public int getId() {
        return id;
    }

    /**
     * Mengatur ID transaksi
     * @param id ID baru untuk transaksi
     */
    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("ID tidak boleh negatif");
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