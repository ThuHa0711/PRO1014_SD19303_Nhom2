/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import entity.KhachHang.KhachHang;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.KhachHang.RepositoryKhachHang;

/**
 *
 * @author ADMIN
 */
public class KhachHangJPanel extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private RepositoryKhachHang repo = new RepositoryKhachHang();
    private ArrayList<KhachHang> listKhachHang = new ArrayList<>();
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Creates new form ViewKhachHang
     */
    public KhachHangJPanel() {
        initComponents();
        dtm = (DefaultTableModel) tblHienThi.getModel();
        listKhachHang = repo.getAll();
        showDataTable(listKhachHang);
    }

    public void showDataTable(List<KhachHang> list) {
        dtm.setRowCount(0);
        SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy");

        for (KhachHang kh : listKhachHang) {
           String ngaySinhFormatted = DATE_FORMATTER.format(kh.getNgaySinh());
            dtm.addRow(new Object[]{
                kh.getMaKhachHang(), kh.getHoTen(), ngaySinhFormatted, kh.getSDT(), kh.isGioiTinh() ? "Nam" : "Nữ", kh.getDiaChi()
            });

        }

    }

    public void fillData(int index) {
        KhachHang kh = listKhachHang.get(index);
        txtMa.setText(kh.getMaKhachHang());
        txtTen.setText(kh.getHoTen());
        jdcNgaySinh.setDate(kh.getNgaySinh());
        txtSDT.setText(kh.getSDT());
        txtDiaChi.setText(kh.getDiaChi());
        boolean gioiTinh = kh.isGioiTinh();
        if (gioiTinh == true) {
            rdNam.setSelected(true);
        } else {
            rdNu.setSelected(true);
        }
        boolean trangThai = kh.isTrangThai();

    }

    private KhachHang getFromData() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang(txtMa.getText());
        kh.setHoTen(txtTen.getText());
        kh.setSDT(txtSDT.getText());
        kh.setNgaySinh(jdcNgaySinh.getDate());
        kh.setGioiTinh(rdNam.isSelected());
        kh.setDiaChi(txtDiaChi.getText());

        return kh;
    }

    private KhachHang getFromData2() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang(txtMa.getText());
        kh.setHoTen(txtTen.getText());
        kh.setSDT(txtSDT.getText());
        kh.setNgaySinh(jdcNgaySinh.getDate());
        kh.setGioiTinh(rdNam.isSelected());
        kh.setDiaChi(txtDiaChi.getText());
        kh.setTrangThai(false);

        return kh;
    }

    private void clearForm() {
        txtMa.setText("");
        txtTen.setText("");

        rdNam.isSelected();
        txtSDT.setText("");
        txtDiaChi.setText("");

    }

    private boolean check() {
        if (repo.checkSDT(txtSDT.getText())) {
            JOptionPane.showMessageDialog(this, "Trùng SDT");
            return false;

        } else if (txtTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ Tên Ko đc trống");
            return false;
        } else if (txtTen.getText().startsWith("  ")) {
            JOptionPane.showMessageDialog(this, " Tên ko được cách đầu dòng");
            return false;
        } else if (!txtTen.getText().trim().matches("^[\\p{L}\\s'-]+$")) {
            JOptionPane.showMessageDialog(this, "Tên không được chứa số hoặc kí tự đặc biệt");
            return false;
        } else if (txtSDT.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "SDT Ko đc trống");
            return false;
        } else if (!txtSDT.getText().trim().matches("^0\\d{9,10}$")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại quá 10 số ");
            return false;
//        } else if (!txtSDT.getText().trim().matches("^[\\p{L}\\s'-]+$")) {
//            JOptionPane.showMessageDialog(this, "SDT ko đc chứa Ký tự đặc biệt ");
//            return false;
        } else if (txtDiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa Chỉ ko đc trống");
            return false;
        } else if (txtDiaChi.getText().startsWith(" ")) {
            JOptionPane.showMessageDialog(this, "Địa chỉ ko được cách đầu dòng");
            return false;
        } else if (!txtDiaChi.getText().trim().matches("^[\\p{L}\\s'-]+$")) {
            JOptionPane.showMessageDialog(this, "Địa Chỉ không được chứa số hoặc kí tự đặc biệt");
            return false;
        } else {
            return true;
        }

    }

    private boolean checkSX() {
        if (txtMa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Ko đc trống");
            return false;

        } else if (txtTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ Tên Ko đc trống");
            return false;
        } else if (txtTen.getText().startsWith("  ")) {
            JOptionPane.showMessageDialog(this, " Tên ko được cách đầu dòng");
            return false;
        } else if (!txtTen.getText().trim().matches("^[\\p{L}\\s'-]+$")) {
            JOptionPane.showMessageDialog(this, "Tên ko đc chứa ý tự đặc biệt");
            return false;
        } else if (txtSDT.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "SDT Ko đc trống");
            return false;
        } else if (!txtSDT.getText().trim().matches("^0\\d{9,10}$")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại quá 10 số và phải bắt đầu bằng 0 ");
            return false;
        } else if (txtDiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa Chỉ ko đc trống");
            return false;
        } else if (txtDiaChi.getText().startsWith("  ")) {
            JOptionPane.showMessageDialog(this, "Địa chỉ ko được cách đầu dòng");
            return false;
        } else if (!txtDiaChi.getText().trim().matches("^[\\p{L}\\s'-]+$")) {
            JOptionPane.showMessageDialog(this, "Địa Chỉ Ko đc chứa kí tự đặc biệt");
            return false;

        } else {
            return true;
        }

    }

    private boolean checkTimKiem() {
        if (txtTimKiem.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Text Tìm kiến ko đc trống");
            return false;

        } else {
            return true;
        }

    }

    public void locNam() {
        dtm.setRowCount(0);
        listKhachHang = repo.locKhachHangNam();
        for (KhachHang kh : listKhachHang) {
            dtm.addRow(new Object[]{
                kh.getMaKhachHang(), kh.getHoTen(), kh.getNgaySinh(), kh.getSDT(), kh.isGioiTinh() ? "Nam" : "Nữ", kh.getDiaChi()
            });

        }
    }

    public void locNu() {
        dtm.setRowCount(0);
        listKhachHang = repo.locKhachHangNu();
        for (KhachHang kh : listKhachHang) {
            dtm.addRow(new Object[]{
                kh.getMaKhachHang(), kh.getHoTen(), kh.getNgaySinh(), kh.getSDT(), kh.isGioiTinh() ? "Nam" : "Nữ", kh.getDiaChi()
            });

        }
    }

    public void tatca() {
        dtm.setRowCount(0);
        listKhachHang = repo.getAll();
        for (KhachHang kh : listKhachHang) {
            dtm.addRow(new Object[]{
                kh.getMaKhachHang(), kh.getHoTen(), kh.getNgaySinh(), kh.getSDT(), kh.isGioiTinh() ? "Nam" : "Nữ", kh.getDiaChi()
            });

        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblma = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jdcNgaySinh = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        BtnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnData = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHienThi = new javax.swing.JTable();
        btnTimKiem = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        cbbGioiTinh = new javax.swing.JComboBox<>();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Khách Hàng");

        jPanel1.setBackground(new java.awt.Color(250, 250, 226));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Khách Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(250, 250, 226));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblma.setText("Mã KH");

        jLabel4.setText("Họ Tên ");

        jLabel5.setText("Ngày Sinh");

        txtMa.setEditable(false);
        txtMa.setBackground(new java.awt.Color(221, 221, 221));

        jLabel6.setText("SDT");

        jLabel7.setText("Giới Tinh");

        rdNam.setBackground(new java.awt.Color(250, 250, 226));
        buttonGroup1.add(rdNam);
        rdNam.setText("Nam");

        rdNu.setBackground(new java.awt.Color(250, 250, 226));
        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");

        jLabel2.setText("Địa Chỉ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblma, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMa)
                    .addComponent(txtTen)
                    .addComponent(jdcNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(rdNam, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdNu, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(138, 138, 138))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblma)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(rdNam)
                    .addComponent(rdNu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel2)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jdcNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        jPanel3.setBackground(new java.awt.Color(244, 244, 240));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        BtnXoa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BtnXoa.setText("Xóa");
        BtnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnData.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnData.setText("LoadData");
        btnData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLamMoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnData)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(250, 250, 226));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Khách Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setToolTipText("");

        tblHienThi.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblHienThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Họ Tên", "Ngày Sinh", "SDT", "Giới Tinh", "Địa Chỉ"
            }
        ));
        tblHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHienThiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHienThi);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        btnTimKiem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(250, 250, 226));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc Khách Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Giới Tính"));

        cbbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Nam", "Nữ" }));
        cbbGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbGioiTinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbbGioiTinh, 0, 169, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(cbbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(289, 289, 289)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(480, 480, 480)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (check()) {
            repo.add(getFromData());
            listKhachHang = repo.getAll();
            showDataTable(listKhachHang);
            JOptionPane.showMessageDialog(this, "Thêm Thành Công");

        } else {
            JOptionPane.showMessageDialog(this, "Thêm Thất Bại");
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int ret;

        if (checkSX()) {
            ret = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn sửa ko", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (ret == JOptionPane.YES_OPTION) {
                int index = tblHienThi.getSelectedRow();

                KhachHang kh = listKhachHang.get(index);
                repo.update(kh.getIdKH(), getFromData());
                listKhachHang = repo.getAll();
                showDataTable(listKhachHang);
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void BtnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnXoaActionPerformed

        int ret;
        ret = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn xóa ko", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (ret == JOptionPane.YES_OPTION) {
            int index = tblHienThi.getSelectedRow();
            KhachHang kh = listKhachHang.get(index);
            repo.delete(kh.getIdKH(), getFromData2());
            listKhachHang = repo.getAll();
            showDataTable(listKhachHang);
        }
    }//GEN-LAST:event_BtnXoaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataActionPerformed
        listKhachHang = repo.getAll();
        showDataTable(listKhachHang);
    }//GEN-LAST:event_btnDataActionPerformed

    private void tblHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHienThiMouseClicked
        int index = tblHienThi.getSelectedRow();
        fillData(index);
    }//GEN-LAST:event_tblHienThiMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        if (checkTimKiem()) {
            String timKiem = txtTimKiem.getText();
            listKhachHang = repo.search(timKiem);
            showDataTable(listKhachHang);
        } else {
            JOptionPane.showMessageDialog(this, "Tìm kiếm bất bại");
        }

    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void cbbGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbGioiTinhActionPerformed
        String gioiTinh = (String) cbbGioiTinh.getSelectedItem();
        if (gioiTinh.equalsIgnoreCase("Nam")) {
            locNam();
        } else if (gioiTinh.equalsIgnoreCase("Nữ")) {
            locNu();
        } else if (gioiTinh.equalsIgnoreCase("Tất cả")) {
            tatca();

        } else {
            showDataTable(listKhachHang);
        }

    }//GEN-LAST:event_cbbGioiTinhActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnXoa;
    private javax.swing.JButton btnData;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbGioiTinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcNgaySinh;
    private javax.swing.JLabel lblma;
    private javax.swing.JLabel lblma1;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNam1;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JRadioButton rdNu1;
    private javax.swing.JTable tblHienThi;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDiaChi1;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMa1;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSDT1;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTen1;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
