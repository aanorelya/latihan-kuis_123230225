import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Frame 1: Pilihan Kendaraan
public class RentalApp extends JFrame implements ActionListener {
    JButton btnMotor, btnMobil;

    public RentalApp() {
        setTitle("Rental Kendaraan");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        btnMotor = new JButton("Motor");
        btnMobil = new JButton("Mobil");

        btnMotor.addActionListener(this);
        btnMobil.addActionListener(this);

        add(btnMotor);
        add(btnMobil);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnMotor) {
            new DetailPenyewaan("Motor");
        } else if (e.getSource() == btnMobil) {
            new DetailPenyewaan("Mobil");
        }
        this.dispose(); // Tutup frame pertama setelah memilih kendaraan
    }

    public static void main(String[] args) {
        new RentalApp();
    }
}

// Frame 2/3: Detail Penyewaan
class DetailPenyewaan extends JFrame implements ActionListener {
    JTextField txtNama, txtTelepon, txtHari;
    JRadioButton rb1, rb2, rb3;
    JButton btnSimpan;
    String jenisKendaraan;

    public DetailPenyewaan(String jenis) {
        this.jenisKendaraan = jenis;
        setTitle("Detail Penyewaan - " + jenis);
        setSize(400, 300);
        setLayout(new FlowLayout());

        add(new JLabel("Nama Penyewa:"));
        txtNama = new JTextField(20);
        add(txtNama);

        add(new JLabel("Nomor Telepon:"));
        txtTelepon = new JTextField(15);
        add(txtTelepon);

        add(new JLabel("Pilih Kendaraan:"));
        rb1 = new JRadioButton(jenis + " A - Rp100.000");
        rb2 = new JRadioButton(jenis + " B - Rp150.000");
        rb3 = new JRadioButton(jenis + " C - Rp200.000");

        ButtonGroup group = new ButtonGroup();
        group.add(rb1);
        group.add(rb2);
        group.add(rb3);

        add(rb1);
        add(rb2);
        add(rb3);

        add(new JLabel("Jumlah Hari:"));
        txtHari = new JTextField(5);
        add(txtHari);

        btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(this);
        add(btnSimpan);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSimpan) {
            new DetailHarga(txtNama.getText(), txtTelepon.getText(), jenisKendaraan,
                    getSelectedPrice(), Integer.parseInt(txtHari.getText()));
            this.dispose();
        }
    }

    private int getSelectedPrice() {
        if (rb1.isSelected()) return 100000;
        if (rb2.isSelected()) return 150000;
        if (rb3.isSelected()) return 200000;
        return 0;
    }
}

// Frame 4: Detail dan Total Harga
class DetailHarga extends JFrame {
    public DetailHarga(String nama, String telepon, String jenis, int harga, int hari) {
        setTitle("Detail Harga");
        setSize(400, 300);
        setLayout(new FlowLayout());

        add(new JLabel("Nama: " + nama));
        add(new JLabel("Telepon: " + telepon));
        add(new JLabel("Jenis Kendaraan: " + jenis));
        add(new JLabel("Harga per Hari: Rp" + harga));
        add(new JLabel("Jumlah Hari: " + hari));
        add(new JLabel("Total Harga: Rp" + (harga * hari)));

        JButton btnSelesai = new JButton("Selesai");
        btnSelesai.addActionListener(e -> System.exit(0));
        add(btnSelesai);

        setVisible(true);
    }
}
