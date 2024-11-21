import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Kelas KoneksiDatabase - Mengelola koneksi database
 * Menerapkan konsep Singleton Pattern untuk koneksi database
 * Memastikan hanya ada satu koneksi database yang aktif
 */
public class KoneksiDatabase {
    // Variabel koneksi database
    private static Connection conn;
    private static final String DB_URL = "jdbc:sqlite:database/keuangan.db";
    
    // Konstruktor private untuk Singleton pattern
    private KoneksiDatabase() {}
    
    /**
     * Mendapatkan koneksi ke database
     * @return objek Connection atau null jika gagal
     */
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                // Register JDBC driver
                Class.forName("org.sqlite.JDBC");
                
                // Open connection
                conn = DriverManager.getConnection(DB_URL);
                System.out.println("Database connection established");
            }
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC Driver not found: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Menutup koneksi database dengan aman
     * Memastikan resource database dibebaskan
     */
    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}