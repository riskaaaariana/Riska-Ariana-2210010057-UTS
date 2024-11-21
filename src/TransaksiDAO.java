
// Import library yang diperlukan
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Kelas untuk menangani operasi database transaksi keuangan
 */
public class TransaksiDAO {
    // Koneksi database
    private Connection connection;

    /**
     * Konstruktor - Menginisialisasi koneksi database
     */
    public TransaksiDAO() {
        connection = KoneksiDatabase.getConnection();
        createTableIfNotExists();
    }

    /**
     * Membuat tabel transaksi jika belum ada
     */
    private void createTableIfNotExists() {
        try {
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS transaksi (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "tanggal DATE," +
                    "jumlah DOUBLE," +
                    "kategori TEXT," +
                    "deskripsi TEXT)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Menambah data transaksi baru ke database
     */
    public void insert(Transaksi transaksi) throws SQLException {
        String sql = "INSERT INTO transaksi (tanggal, jumlah, kategori, deskripsi) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(transaksi.getTanggal().getTime()));
            stmt.setDouble(2, transaksi.getJumlah());
            stmt.setString(3, transaksi.getKategori());
            stmt.setString(4, transaksi.getDeskripsi());
            stmt.executeUpdate();
        }
    }

    /**
     * Mengubah data transaksi yang ada di database
     */
    public void update(Transaksi transaksi) throws SQLException {
        String sql = "UPDATE transaksi SET tanggal=?, jumlah=?, kategori=?, deskripsi=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(transaksi.getTanggal().getTime()));
            stmt.setDouble(2, transaksi.getJumlah());
            stmt.setString(3, transaksi.getKategori());
            stmt.setString(4, transaksi.getDeskripsi());
            stmt.setInt(5, transaksi.getId());
            stmt.executeUpdate();
        }
    }

    /**
     * Menghapus data transaksi dari database
     */
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM transaksi WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    /**
     * Mengambil semua data transaksi dari database
     */
    public List<Transaksi> getAll() throws SQLException {
        List<Transaksi> list = new ArrayList<>();
        String sql = "SELECT * FROM transaksi";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Transaksi t = new Transaksi();
                t.setId(rs.getInt("id"));
                t.setTanggal(rs.getDate("tanggal"));
                t.setJumlah(rs.getDouble("jumlah"));
                t.setKategori(rs.getString("kategori"));
                t.setDeskripsi(rs.getString("deskripsi"));
                list.add(t);
            }
        }
        return list;
    }

    /**
     * Mencari data transaksi berdasarkan kata kunci
     */
    public List<Transaksi> search(String keyword) throws SQLException {
        List<Transaksi> list = new ArrayList<>();
        String sql = "SELECT * FROM transaksi WHERE LOWER(deskripsi) LIKE LOWER(?) OR LOWER(kategori) LIKE LOWER(?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transaksi t = new Transaksi();
                    t.setId(rs.getInt("id"));
                    t.setTanggal(rs.getDate("tanggal"));
                    t.setJumlah(rs.getDouble("jumlah"));
                    t.setKategori(rs.getString("kategori"));
                    t.setDeskripsi(rs.getString("deskripsi"));
                    list.add(t);
                }
            }
        }
        return list;
    }

    /**
     * Mengambil data transaksi berdasarkan indeks baris
     */
    public Transaksi getByRowIndex(int rowIndex) throws SQLException {
        String sql = "SELECT * FROM transaksi LIMIT 1 OFFSET ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, rowIndex);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Transaksi t = new Transaksi();
                t.setId(rs.getInt("id"));
                t.setTanggal(rs.getDate("tanggal"));
                t.setJumlah(rs.getDouble("jumlah"));
                t.setKategori(rs.getString("kategori"));
                t.setDeskripsi(rs.getString("deskripsi"));
                return t;
            }
        }
        return null;
    }
}