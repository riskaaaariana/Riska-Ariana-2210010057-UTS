/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

// Import library yang diperlukan
import java.sql.SQLException;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;
// import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
// Add these imports
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Kelas utama aplikasi keuangan pribadi
 * 
 * @author Riska
 */
public class AplikasiKeuanganPribadi extends javax.swing.JFrame {
    // Objek untuk akses database
    private TransaksiDAO transaksiDAO;
    // Transaksi yang sedang dipilih
    private Transaksi selectedTransaksi;
    // Format mata uang Rupiah
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    // Add these fields
    private final Gson gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd")
        .setPrettyPrinting()
        .create();

    /**
     * Creates new form AplikasiKeuanganPribadi
     */
    public AplikasiKeuanganPribadi() {
        initComponents();
        transaksiDAO = new TransaksiDAO();
        loadData();
    }

    /**
     * Memuat ulang data tabel
     */
    private void loadData() {
        try {
            List<Transaksi> list = transaksiDAO.getAll();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            for (Transaksi t : list) {
                model.addRow(new Object[] {
                        t.getTanggal(),
                        formatRupiah(t.getJumlah()), // Format as Rupiah
                        t.getKategori(),
                        t.getDeskripsi()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }

    /**
     * Memformat angka ke format Rupiah
     */
    private String formatRupiah(double amount) {
        return currencyFormat.format(amount);
    }

    /**
     * Mengkonversi string Rupiah ke nilai numerik
     */
    private double parseRupiah(String amount) {
        try {
            // Remove currency symbol, dots, and replace comma with dot
            String cleaned = amount.replace("Rp", "")
                    .replace(".", "")
                    .replace(",", ".")
                    .trim();
            return Double.parseDouble(cleaned);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /**
     * Memvalidasi input form
     */
    private boolean validateInput() {
        // Validate date
        if (jDateChooser1.getDate() == null) {
            JOptionPane.showMessageDialog(this,
                    "Tanggal harus diisi!",
                    "Validasi Error",
                    JOptionPane.WARNING_MESSAGE);
            jDateChooser1.requestFocus();
            return false;
        }

        // Validate amount
        String jumlahStr = txtJumlah.getText().trim();
        if (jumlahStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Jumlah uang harus diisi!",
                    "Validasi Error",
                    JOptionPane.WARNING_MESSAGE);
            txtJumlah.requestFocus();
            return false;
        }

        try {
            double jumlah = parseRupiah(jumlahStr);
            if (jumlah <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Jumlah uang harus lebih besar dari Rp 0!",
                        "Validasi Error",
                        JOptionPane.WARNING_MESSAGE);
                txtJumlah.requestFocus();
                return false;
            }
            if (jumlah > 999999999999.99) {
                JOptionPane.showMessageDialog(this,
                        "Jumlah uang terlalu besar!\nMaksimal: Rp 999.999.999.999,99",
                        "Validasi Error",
                        JOptionPane.WARNING_MESSAGE);
                txtJumlah.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Format jumlah uang tidak valid!\nContoh: Rp 10.000 atau 10000",
                    "Validasi Error",
                    JOptionPane.WARNING_MESSAGE);
            txtJumlah.requestFocus();
            return false;
        }

        // Validate description
        String deskripsi = jTextArea1.getText().trim();
        if (deskripsi.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Deskripsi harus diisi!",
                    "Validasi Error",
                    JOptionPane.WARNING_MESSAGE);
            jTextArea1.requestFocus();
            return false;
        }
        if (deskripsi.length() > 255) {
            JOptionPane.showMessageDialog(this,
                    "Deskripsi terlalu panjang!\nMaksimal 255 karakter.",
                    "Validasi Error",
                    JOptionPane.WARNING_MESSAGE);
            jTextArea1.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        cbKategori = new javax.swing.JComboBox<>();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        btnMuat = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aplikasi Keuangan Pribadi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Tanggal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Jumlah");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Kategori");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel2.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel2.add(txtJumlah, gridBagConstraints);

        cbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendapatan", "Pengeluaran" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel2.add(cbKategori, gridBagConstraints);

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel2.add(btnTambah, gridBagConstraints);

        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel2.add(btnUbah, gridBagConstraints);

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel2.add(btnHapus, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel2.add(jDateChooser1, gridBagConstraints);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel2.add(jScrollPane2, gridBagConstraints);

        jLabel1.setText("Deskripsi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel2.add(jLabel1, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("Cari Data Keuangan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 15, 0, 4);
        jPanel3.add(jLabel5, gridBagConstraints);

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 11, 0, 15);
        jPanel3.add(jTextField3, gridBagConstraints);

        btnCari.setText("Search");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 14, 0, 9);
        jPanel3.add(btnCari, gridBagConstraints);

        btnMuat.setText("Muat");
        btnMuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuatActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        jPanel3.add(btnMuat, gridBagConstraints);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        jPanel3.add(btnSimpan, gridBagConstraints);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 300));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tanggal", "Jumlah", "Kategori", "Deskripsi"
            }
        ));
        jTable1.setRowSelectionAllowed(false);
        jTable1.setShowGrid(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 400;
        gridBagConstraints.ipady = 170;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jScrollPane1, gridBagConstraints);

        jButton1.setText("Keluar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel3.add(jButton1, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        if (!validateInput()) {
            return;
        }

        try {
            Transaksi t = new Transaksi(
                    jDateChooser1.getDate(),
                    parseRupiah(txtJumlah.getText().trim()), // Parse from Rupiah format
                    cbKategori.getSelectedItem().toString(),
                    jTextArea1.getText().trim());
            transaksiDAO.insert(t);
            loadData();
            clearForm();
            JOptionPane.showMessageDialog(this,
                    "Data berhasil ditambahkan!",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error menambah data: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_btnTambahActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        if (selectedTransaksi == null) {
            JOptionPane.showMessageDialog(this,
                    "Pilih data yang akan diubah terlebih dahulu!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validateInput()) {
            return;
        }

        try {
            selectedTransaksi.setTanggal(jDateChooser1.getDate());
            selectedTransaksi.setJumlah(parseRupiah(txtJumlah.getText().trim())); // Parse from Rupiah format
            selectedTransaksi.setKategori(cbKategori.getSelectedItem().toString());
            selectedTransaksi.setDeskripsi(jTextArea1.getText().trim());

            transaksiDAO.update(selectedTransaksi);
            loadData();
            clearForm();
            JOptionPane.showMessageDialog(this,
                    "Data berhasil diubah!",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error mengubah data: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (selectedTransaksi == null) {
                JOptionPane.showMessageDialog(this,
                        "Silakan pilih data yang akan dihapus terlebih dahulu!",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Anda yakin ingin menghapus data ini?\n" +
                            "Tanggal: " + selectedTransaksi.getTanggal() + "\n" +
                            "Jumlah: " + selectedTransaksi.getJumlah() + "\n" +
                            "Kategori: " + selectedTransaksi.getKategori() + "\n" +
                            "Deskripsi: " + selectedTransaksi.getDeskripsi(),
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                transaksiDAO.delete(selectedTransaksi.getId());
                loadData();
                clearForm();
                JOptionPane.showMessageDialog(this,
                        "Data berhasil dihapus!",
                        "Informasi",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error menghapus data: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String keyword = jTextField3.getText().trim();
            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Petunjuk Pencarian:\n" +
                                "- Cari berdasarkan deskripsi: masukkan kata kunci (contoh: 'makan')\n" +
                                "- Cari berdasarkan kategori: ketik 'Pendapatan' atau 'Pengeluaran'\n" +
                                "- Pencarian tidak case sensitive\n" +
                                "- Minimal masukkan 1 karakter",
                        "Informasi Pencarian",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            List<Transaksi> list = transaksiDAO.search(keyword);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Data tidak ditemukan untuk kata kunci: " + keyword + "\n" +
                                "Menampilkan semua data.",
                        "Hasil Pencarian",
                        JOptionPane.INFORMATION_MESSAGE);
                loadData(); // Refresh to show all data
                jTextField3.setText(""); // Clear search field
                return;
            }

            // If results found, display them
            for (Transaksi t : list) {
                model.addRow(new Object[] {
                        t.getTanggal(),
                        t.getJumlah(),
                        t.getKategori(),
                        t.getDeskripsi()
                });
            }

            JOptionPane.showMessageDialog(this,
                    "Ditemukan " + list.size() + " data",
                    "Hasil Pencarian",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error searching data: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            loadData(); // Refresh on error too
            jTextField3.setText("");
        }
    }

    private void btnMuatActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnMuatActionPerformed
        importFromJson();
    }// GEN-LAST:event_btnMuatActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSimpanActionPerformed
        exportToJson();
    }// GEN-LAST:event_btnSimpanActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jTable1MouseClicked
        try {
            int row = jTable1.getSelectedRow();
            if (row >= 0) {
                // Get complete transaction object including ID
                selectedTransaksi = transaksiDAO.getByRowIndex(row);
                if (selectedTransaksi != null) {
                    // Update form fields
                    jDateChooser1.setDate(selectedTransaksi.getTanggal());
                    txtJumlah.setText(formatRupiah(selectedTransaksi.getJumlah())); // Format as Rupiah
                    cbKategori.setSelectedItem(selectedTransaksi.getKategori());
                    jTextArea1.setText(selectedTransaksi.getDeskripsi());
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error selecting data: " + e.getMessage());
        }
    }// GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField3ActionPerformed

    /**
     * Membersihkan form input
     */
    private void clearForm() {
        jDateChooser1.setDate(null);
        txtJumlah.setText("");
        cbKategori.setSelectedIndex(0);
        jTextArea1.setText("");
        selectedTransaksi = null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AplikasiKeuanganPribadi.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AplikasiKeuanganPribadi.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AplikasiKeuanganPribadi.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AplikasiKeuanganPribadi.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AplikasiKeuanganPribadi().setVisible(true);
            }
        });
    }

    // Add these methods
    private void exportToJson() {
        try {
            // Get data from database
            List<Transaksi> list = transaksiDAO.getAll();
            
            // Create database directory if it doesn't exist
            File dir = new File("database");
            if (!dir.exists()) dir.mkdir();
            
            // Create filename with timestamp
            String filename = "database/transaksi_" + 
                            new SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date()) + 
                            ".json";
            
            // Write JSON to file
            try (FileWriter writer = new FileWriter(filename)) {
                gson.toJson(list, writer);
            }
            
            JOptionPane.showMessageDialog(this,
                "Data berhasil diekspor ke file JSON:\n" + filename,
                "Ekspor Sukses",
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error mengekspor data: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void importFromJson() {
        try {
            JFileChooser chooser = new JFileChooser("database");
            chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "JSON files", "json"));
            
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                
                // Read JSON file
                try (FileReader reader = new FileReader(selectedFile)) {
                    java.lang.reflect.Type listType = new TypeToken<ArrayList<Transaksi>>(){}.getType();
                    List<Transaksi> importedList = gson.fromJson(reader, listType);
                    
                    if (importedList == null || importedList.isEmpty()) {
                        throw new Exception("File JSON tidak berisi data yang valid");
                    }
                    
                    int successCount = 0;
                    int totalRecords = importedList.size();
                    
                    // Import each transaction
                    for (Transaksi t : importedList) {
                        try {
                            transaksiDAO.insert(t);
                            successCount++;
                        } catch (Exception e) {
                            System.err.println("Error importing record: " + e.getMessage());
                        }
                    }
                    
                    loadData(); // Refresh table
                    
                    JOptionPane.showMessageDialog(this,
                        String.format("Impor selesai!\nBerhasil: %d dari %d data", 
                                    successCount, totalRecords),
                        "Impor Sukses",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error mengimpor data: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnMuat;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cbKategori;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField txtJumlah;
    // End of variables declaration//GEN-END:variables
}
