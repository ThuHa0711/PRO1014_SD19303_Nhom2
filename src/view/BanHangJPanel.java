package view;

import entity.BanHang.BanHang;
import entity.BanHang.HoaDon;
import entity.KhachHang.KhachHang;
import entity.SanPham.SanPham;
import java.text.DecimalFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import service.BanHang.BanHangDAO;
import service.KhachHang.KhachHangDAO;
import service.SanPham.SanPhamDAO;

/**
 *
 * @author ADMIN
 */
public class BanHangJPanel extends javax.swing.JPanel {

    private String maNV;
    private String tenNV;
    private final SanPhamDAO daosp = new SanPhamDAO();
    private final BanHangDAO daobh = new BanHangDAO();
    private final KhachHangDAO daokh = new KhachHangDAO();
    DefaultTableModel molsp = new DefaultTableModel();
    DefaultTableModel molhd = new DefaultTableModel();
    DefaultTableModel molgh = new DefaultTableModel();
    float tongTien = 0;

    public BanHangJPanel(String maNVtruyenvao, String tenTV) {
        initComponents();
        molsp = (DefaultTableModel) tblSanPham.getModel();
        molhd = (DefaultTableModel) tblHoaDon.getModel();
        molgh = (DefaultTableModel) tblGioHang.getModel();
        fillSP();
        fillHD();
        maNV = maNVtruyenvao;
        tenNV = tenTV;
    }

    void fillSP() {
        molsp.setRowCount(0);
        ArrayList<SanPham> lst = daosp.getAll();
        for (SanPham sp : lst) {
            molsp.addRow(new Object[]{
                sp.getMaSp(),
                sp.getTenSp(),
                sp.getSoLuong(),
                sp.getDonGia(),
                sp.getTheLoai(),
                sp.getChatLieu(),
                sp.getMauSac(),
                sp.getKichCo(),
                sp.getTrangthai()});
        }
    }

    void fillHD() {
        int stt = 1;
        molhd.setRowCount(0);
        ArrayList<HoaDon> lsthd = daobh.GetListHD();
        for (HoaDon hd : lsthd) {
            molhd.addRow(new Object[]{
                stt,
                hd.getMaHoaDon(),
                hd.getNgayThanhToan(),
                hd.isTrangThai() ? "Đã Thanh Toán" : "Chưa Thanh Toán"}
            );
            stt++;
        }
    }

    public HoaDon createHD() {
        HoaDon hd = new HoaDon();
        hd.setTaiKhoan(maNV);
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate date = currentDateTime.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String TGnow = date.format(formatter);
        hd.setNgayThanhToan(TGnow);
        hd.setTrangThai(false);
        return hd;
    }

    public void fillTableGH(List<BanHang> listbh) {

        molgh.setRowCount(0);
        for (BanHang bh : listbh) {
            Object[] dtrow = {
                bh.getMaSP(), bh.getTenSP(), bh.getSoLuong(), bh.getDonGia(), bh.getTongTien()
            };
            molgh.addRow(dtrow);
        }
    }

    public void filltoform(HoaDon hd) {
        // txtsdt.setText(hd.getSdt());
        txtMaHD.setText((hd.getMaHoaDon()));
        txtNgaytao.setText(hd.getNgayThanhToan());
        txtMoTa.setText(hd.getMoTa());

    }

    private void thanhToanHoaDon() {
        HoaDon mod = getForm();
        try {
            daobh.updatehd(mod);
            fillHD();

            JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi thanh toán! ");
        }
    }

    public HoaDon getForm() {
        HoaDon hd = new HoaDon();
        hd.setMaKH(lbltenKH.getText());

        // Chuyển đổi chuỗi số tiền sang float
        String tongTienStr = txtTongTien.getText();
        float tongTienFloat = convertStringToFloat(tongTienStr);
        hd.setTongTien(tongTienFloat);

        hd.setMoTa(txtMoTa.getText());
        hd.setMaHoaDon(txtMaHD.getText());
        return hd;
    }

    public float convertStringToFloat(String str) {
        // Loại bỏ ký tự "VND"
        str = str.replace("VND", "").trim();

        // Thay thế dấu chấm phân cách hàng ngàn bằng chuỗi rỗng
        str = str.replace(".", "");

        // Chuyển đổi chuỗi sang float
        float result = 0;
        try {
            result = Float.parseFloat(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }

        return result;
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

    private boolean checkKH() {
//        if (daokh.checkSDT(txtSDT.getText())) {
//            JOptionPane.showMessageDialog(this, "Trùng SDT");
//            return false;

        if (txtTen.getText().trim().isEmpty()) {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JDLthemKH = new javax.swing.JDialog();
        btnThem = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblma3 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        jLabel40 = new javax.swing.JLabel();
        txtSDTKH = new javax.swing.JTextField();
        jdcNgaySinh = new com.toedter.calendar.JDateChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnXoaSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnXoaAll = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNgaytao = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTienKhach = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        btnThanhToan = new javax.swing.JButton();
        jpnkhachhang = new javax.swing.JPanel();
        txtSDT = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        btnCheck = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        lbltenKH = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtFind = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnTaoHD = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        JDLthemKH.setBackground(new java.awt.Color(0, 153, 255));
        JDLthemKH.setMinimumSize(new java.awt.Dimension(820, 400));

        btnThem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText(" Thông Tin Khách Hàng ");

        jPanel8.setBackground(new java.awt.Color(250, 250, 226));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblma3.setText("Mã KH");

        jLabel28.setText("Họ Tên ");

        jLabel29.setText("Ngày Sinh");

        txtMa.setEditable(false);
        txtMa.setBackground(new java.awt.Color(221, 221, 221));

        jLabel30.setText("SDT");

        jLabel31.setText("Giới Tinh");

        rdNam.setBackground(new java.awt.Color(250, 250, 226));
        buttonGroup1.add(rdNam);
        rdNam.setText("Nam");

        rdNu.setBackground(new java.awt.Color(250, 250, 226));
        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");

        jLabel40.setText("Địa Chỉ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel29)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblma3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(25, 25, 25)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTen, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(txtMa, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(rdNam, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdNu, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(99, 99, 99))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(rdNam)
                            .addComponent(rdNu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblma3)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jdcNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout JDLthemKHLayout = new javax.swing.GroupLayout(JDLthemKH.getContentPane());
        JDLthemKH.getContentPane().setLayout(JDLthemKHLayout);
        JDLthemKHLayout.setHorizontalGroup(
            JDLthemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDLthemKHLayout.createSequentialGroup()
                .addGroup(JDLthemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JDLthemKHLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JDLthemKHLayout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JDLthemKHLayout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        JDLthemKHLayout.setVerticalGroup(
            JDLthemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JDLthemKHLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(153, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Giỏ Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành  Tiền"
            }
        ));
        tblGioHang.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tblGioHangComponentAdded(evt);
            }
        });
        jScrollPane5.setViewportView(tblGioHang);

        btnXoaSP.setText("Xóa Sản Phẩm");
        btnXoaSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaSPMouseClicked(evt);
            }
        });
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        btnSuaSP.setText("Sửa Sản Phẩm");
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        btnXoaAll.setText("Xóa Tất Cả");
        btnXoaAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnSuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaAll, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaAll, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jLabel6.setText("Mã HD");

        txtMaHD.setEditable(false);
        txtMaHD.setBackground(new java.awt.Color(224, 224, 224));

        jLabel7.setText("Ngày Tạo");

        txtNgaytao.setEditable(false);
        txtNgaytao.setBackground(new java.awt.Color(224, 224, 224));

        jLabel9.setText("Tổng Tiền");

        txtTongTien.setEditable(false);
        txtTongTien.setBackground(new java.awt.Color(221, 221, 221));

        jLabel10.setText("Tiền Khách Đưa");

        txtTienKhach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienKhachKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachKeyReleased(evt);
            }
        });

        jLabel11.setText("Tiền Thừa");

        txtTienThua.setEditable(false);
        txtTienThua.setBackground(new java.awt.Color(221, 221, 221));

        jLabel13.setText("Mô Tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jpnkhachhang.setBackground(new java.awt.Color(153, 255, 204));
        jpnkhachhang.setBorder(javax.swing.BorderFactory.createTitledBorder("Khách Hàng"));
        jpnkhachhang.setMaximumSize(new java.awt.Dimension(299, 76));
        jpnkhachhang.setMinimumSize(new java.awt.Dimension(417, 76));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("Số Điện Thoại :");

        btnCheck.setText("Check");
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel37.setText("Tên Khách hàng :");

        lbltenKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbltenKH.setText("null");

        javax.swing.GroupLayout jpnkhachhangLayout = new javax.swing.GroupLayout(jpnkhachhang);
        jpnkhachhang.setLayout(jpnkhachhangLayout);
        jpnkhachhangLayout.setHorizontalGroup(
            jpnkhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnkhachhangLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jpnkhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnkhachhangLayout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbltenKH))
                    .addGroup(jpnkhachhangLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jpnkhachhangLayout.setVerticalGroup(
            jpnkhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnkhachhangLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jpnkhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(btnCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnkhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(lbltenKH))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtTienThua))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtTienKhach))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtTongTien))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtNgaytao))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpnkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(254, 254, 254))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaHD)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNgaytao)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTongTien)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTienKhach)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTienThua)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txtFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindActionPerformed(evt);
            }
        });
        txtFind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindKeyReleased(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thể Loại", "Chất Liệu", "Màu Sắc", "Kích Cỡ", "Trạng Thái"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel1.setText("Tìm Kiếm :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐ", "Ngày Tạo", "Trạng Thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnTaoHD.setText("Tạo Hóa Đơn");
        btnTaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHDActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Hóa Đơn Chờ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Đơn Hàng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(38, 38, 38)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(btnTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 86, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        // TODO add your handling code here:
        try {
            int selectedR = tblGioHang.getSelectedRow();
            if (selectedR > -1) {
                String input = JOptionPane.showInputDialog("Nhập số lượng");

                if (input == null || input.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng hợp lệ!");
                    return;
                }

                int soLuongMoi;
                try {
                    soLuongMoi = Integer.parseInt(input);
                    if (soLuongMoi <= 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                        return;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên hợp lệ");
                    return;
                }

                // Lấy số lượng cũ từ bảng
                int soluong = (Integer) tblGioHang.getValueAt(selectedR, 2);
                float dongia = (Float) tblGioHang.getValueAt(selectedR, 3);
                String masp = (String) tblGioHang.getValueAt(selectedR, 0);

                // Tính chênh lệch số lượng
                int quantityDifference = soLuongMoi - soluong;

                // Cập nhật bảng
                float newTotal = dongia * soLuongMoi;
                molgh.setValueAt(soLuongMoi, selectedR, 2);
                molgh.setValueAt(newTotal, selectedR, 4);

                // Cập nhật tổng giá
                float totalAmount = 0;
                for (int i = 0; i < tblGioHang.getRowCount(); i++) {
                    totalAmount += (Float) tblGioHang.getValueAt(i, 4);
                }

                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                txtTongTien.setText(decimalFormat.format(totalAmount) + " VND");

                // Cập nhật cơ sở dữ liệu với chênh lệch số lượng
                SanPham sp = new SanPham();
                sp.setSoLuong(quantityDifference);
                sp.setMaSp(masp);
                daobh.updatespsausua(sp);

                // Cập nhật thông tin hóa đơn chi tiết
                BanHang bh = new BanHang();
                bh.setMaSP(masp);
                bh.setMaHD(txtMaHD.getText());
                bh.setSoLuong(soLuongMoi);
                bh.setTongTien(newTotal);
                daobh.updatehdct(bh);

                fillSP();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm trong giỏ hàng");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi xử lý dữ liệu");
            e.printStackTrace(); // In lỗi ra để tiện debug
        }

    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        String sdt = txtSDT.getText();
        int kq = daokh.findKhachHangBySDT(sdt);

        if (kq != 0) {
            JOptionPane.showMessageDialog(this, "Khách hàng đã tồn tại, đã lấy được ID khách hàng.");
            String makh = daobh.findMaKHbangsdt(sdt);
            lbltenKH.setText(makh);
        } else {
            int confirmResult = JOptionPane.showConfirmDialog(
                    this,
                    "Khách hàng chưa tồn tại. Bạn có muốn thêm khách hàng mới?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmResult == JOptionPane.YES_OPTION) {
                txtSDTKH.setText(txtSDT.getText());
                JDLthemKH.setVisible(true);
                JDLthemKH.setLocationRelativeTo(null);
            }
        }
        //              int makh =  KHSV.findKHbangsdt(sdt);
        //              lblmaKH.setText(String.valueOf(makh));
        // User clicked "No" or closed the dialog, do nothing or handle accordingly
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCheckActionPerformed

    private void btnTaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHDActionPerformed
        // TODO add your handling code here:
        HoaDon model = createHD();
        try {
            daobh.addhdcho(model);
            fillHD();
            JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi thêm hóa đơn");
        }
    }//GEN-LAST:event_btnTaoHDActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        if (txtMaHD.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn");
        } else {
            try {
                int selectedR = tblSanPham.getSelectedRow();
                if (selectedR > -1) {
                    String masp = (String) tblSanPham.getValueAt(selectedR, 0);
                    String tensp = (String) tblSanPham.getValueAt(selectedR, 1);
                    Float dongia = (Float) tblSanPham.getValueAt(selectedR, 3);
                    int soluongbd = (Integer) tblSanPham.getValueAt(selectedR, 2);

                    String input = JOptionPane.showInputDialog("Nhập số lượng");
                    if (input == null || input.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Vui lòng thử lại số lượng không hợp lệ!");
                        return;
                    }

                    int soluong;
                    try {
                        soluong = Integer.parseInt(input);
                        if (soluong <= 0) {
                            JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên hợp lệ");
                        return;
                    }

                    int soLuongMoi = soluongbd - soluong;
                    if (soLuongMoi < 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng sản phẩm này không đủ");
                        fillSP();
                        return;
                    }

                    float tong = dongia * soluong;

                    // Kiểm tra xem masp đã có trong cột đầu tiên của molGH hay chưa
                    boolean maspExists = false;
                    for (int i = 0; i < molgh.getRowCount(); i++) {
                        String existingMasp = (String) molgh.getValueAt(i, 0);
                        if (masp.equals(existingMasp)) {
                            // Nếu masp đã có, cộng thêm vào số lượng và tính lại tổng
                            int existingQuantity = (int) molgh.getValueAt(i, 2);
                            molgh.setValueAt(existingQuantity + soluong, i, 2);

                            float existingTotal = (float) molgh.getValueAt(i, 4);
                            molgh.setValueAt(existingTotal + tong, i, 4);

                            try {
                                BanHang bh = new BanHang();
                                bh.setMaSP(masp);
                                bh.setMaHD(txtMaHD.getText());
                                bh.setSoLuong(existingQuantity + soluong);
                                bh.setTongTien(existingTotal + tong);

                                // Update hóa đơn chi tiết vào DB
                                int rowsAffected = daobh.updatehdct(bh); // Assume you have an update method in daobh
                                if (rowsAffected > 0) {
                                    SanPham sp = new SanPham();
                                    sp.setSoLuong(soLuongMoi); // Cập nhật số lượng mới
                                    sp.setMaSp(masp);

                                    // Cập nhật sản phẩm trong DB
                                    daobh.updatesp(sp);

                                    // Cập nhật lại bảng sản phẩm
                                    fillSP();

                                    JOptionPane.showMessageDialog(this, "Cập nhật hóa đơn thành công");
                                } else {
                                    JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật hóa đơn chi tiết");
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, "Lỗi cập nhật hóa đơn");
                                e.printStackTrace(); // In ra lỗi để tiện debug
                            }

                            maspExists = true;
                            break;
                        }
                    }
                    if (!maspExists) {
                        // Nếu masp chưa có, thêm một dòng mới vào molGH
                        Object[] rowdata = {masp, tensp, soluong, dongia, tong};
                        molgh.addRow(rowdata);

                        try {
                            BanHang bh = new BanHang();
                            bh.setMaSP(masp);
                            bh.setMaHD(txtMaHD.getText());
                            bh.setSoLuong(soluong);
                            bh.setTongTien(tong);

                            // Thêm hóa đơn chi tiết vào DB
                            int rowsAffected = daobh.addhdct(bh);
                            if (rowsAffected > 0) {
                                SanPham sp = new SanPham();
                                sp.setSoLuong(soLuongMoi); // Cập nhật số lượng mới
                                sp.setMaSp(masp);

                                // Cập nhật sản phẩm trong DB
                                daobh.updatesp(sp);

                                // Cập nhật lại bảng sản phẩm
                                fillSP();

                                JOptionPane.showMessageDialog(this, "Thêm vào giỏ hàng thành công");
                            } else {
                                JOptionPane.showMessageDialog(this, "Lỗi khi thêm vào giỏ hàng");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Lỗi thêm giỏ hàng");
                            e.printStackTrace(); // In ra lỗi để tiện debug
                        }
                    }

                    // Cập nhật tổng tiền
                    int maxrpw = tblGioHang.getRowCount();
                    tongTien = 0;
                    for (int i = 0; i < maxrpw; i++) {
                        float gia = (float) tblGioHang.getValueAt(i, 4);
                        tongTien += gia;
                    }

                    DecimalFormat decimalFormat = new DecimalFormat("#,###");
                    String formattedTong = decimalFormat.format(tongTien);
                    txtTongTien.setText(formattedTong + " VND");

                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi xử lý dữ liệu");
                e.printStackTrace(); // In ra lỗi để tiện debug
            }
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        int slrow = tblHoaDon.getSelectedRow();

        String mahdselect = (String) tblHoaDon.getValueAt(slrow, 1);
        fillTableGH(daobh.GetListGH(mahdselect));
        filltoform(daobh.getHoaDonByMaHD(mahdselect));
        int maxrpw = tblGioHang.getRowCount();
        tongTien = 0;
        for (int i = 0; i < maxrpw; i++) {

            float gia = (float) tblGioHang.getValueAt(i, 4);
            tongTien = tongTien + gia;
        }

// Format the BigDecimal value with commas
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedTong = decimalFormat.format(tongTien);

        txtTongTien.setText(formattedTong + " VND");
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnXoaSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaSPMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaSPMouseClicked

    private void btnXoaAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaAllActionPerformed

        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa tất cả các sản phẩm trong giỏ hàng?", "Xác nhận xóa tất cả", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
            int rowCount = model.getRowCount();

            // Lấy danh sách sản phẩm và số lượng trước khi xóa
            List<String> maspList = new ArrayList<>();
            List<Integer> soluongList = new ArrayList<>();
            for (int i = 0; i < rowCount; i++) {
                maspList.add((String) model.getValueAt(i, 0));
                soluongList.add((Integer) model.getValueAt(i, 2));
            }

            // Xóa tất cả các dòng trong bảng
            model.setRowCount(0);

            // Cập nhật cơ sở dữ liệu
            try {
                String maHD = txtMaHD.getText();
                BanHang bh = new BanHang();
                bh.setMaHD(maHD);

                // Xóa tất cả các hóa đơn chi tiết từ cơ sở dữ liệu
                daobh.deleteAllHdct(bh);

                // Cập nhật lại số lượng sản phẩm trong cơ sở dữ liệu
                for (int i = 0; i < maspList.size(); i++) {
                    SanPham sp = new SanPham();
                    sp.setMaSp(maspList.get(i));
                    sp.setSoLuong(soluongList.get(i));
                    daobh.updatespsauxoa(sp); // Phương thức cập nhật số lượng sản phẩm khi xóa
                }

                // Cập nhật tổng tiền
                tongTien = 0;
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                txtTongTien.setText(decimalFormat.format(tongTien) + " VND");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa dữ liệu từ cơ sở dữ liệu");
                e.printStackTrace(); // In ra lỗi để tiện debug
            }
        }
    }//GEN-LAST:event_btnXoaAllActionPerformed

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblGioHang.getSelectedRow();

        if (selectedRow != -1) {
            // Hỏi người dùng xác nhận việc xóa
            int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();

                // Lấy mã sản phẩm, số lượng và mã hóa đơn từ bảng
                String masp = (String) tblGioHang.getValueAt(selectedRow, 0);
                int soluong = (Integer) tblGioHang.getValueAt(selectedRow, 2);
                String maHD = txtMaHD.getText();

                // Xóa dòng khỏi bảng
                model.removeRow(selectedRow);

                // Cập nhật cơ sở dữ liệu
                try {
                    BanHang bh = new BanHang();
                    bh.setMaSP(masp);
                    bh.setMaHD(maHD);

                    // Giả sử bạn có phương thức deletehdct để xóa hóa đơn chi tiết từ cơ sở dữ liệu
                    daobh.deletehdct(bh);

                    // Cập nhật lại số lượng sản phẩm trong cơ sở dữ liệu
                    SanPham sp = new SanPham();
                    sp.setMaSp(masp);
                    sp.setSoLuong(soluong);
                    daobh.updatespsauxoa(sp); // Phương thức cập nhật số lượng sản phẩm khi xóa

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa dữ liệu từ cơ sở dữ liệu");
                    e.printStackTrace(); // In ra lỗi để tiện debug
                }

                // Cập nhật tổng tiền
                int maxrpw = tblGioHang.getRowCount();
                tongTien = 0;
                for (int i = 0; i < maxrpw; i++) {
                    float gia = ((Number) tblGioHang.getValueAt(i, 4)).floatValue(); // Sử dụng Number để lấy giá trị
                    tongTien += gia;
                }

                // Format tổng tiền với dấu phẩy
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                String formattedTong = decimalFormat.format(tongTien);

                txtTongTien.setText(formattedTong + " VND");
                fillSP();
            }
        } else {
            // Hiển thị thông báo khi không có dòng nào được chọn
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void txtFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindActionPerformed

    private void txtFindKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindKeyReleased
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
        TableRowSorter<DefaultTableModel> abc = new TableRowSorter<>(dtm);
        tblSanPham.setRowSorter(abc);
        abc.setRowFilter(RowFilter.regexFilter(txtFind.getText(), 0, 1));
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindKeyReleased

    private void txtTienKhachKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachKeyPressed

    private void txtTienKhachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachKeyReleased
        // TODO add your handling code here:
        int tienKhachThanhToan = Integer.valueOf(txtTienKhach.getText());
        if (tienKhachThanhToan >= tongTien) {
            float tienTraLai = tienKhachThanhToan - tongTien;
            txtTienThua.setText(tienTraLai + " VND");
        } else {
            txtTienThua.setText("0 VND");
        }
    }//GEN-LAST:event_txtTienKhachKeyReleased

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:

        if (molgh.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Hoá đơn chưa có sản phẩm nào");
            return;
        }
        String thongTinKhachHang = lbltenKH.getText().trim();
        if (thongTinKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin khách hàng");
            return;
        }
        boolean a = entity.BanHang.MsgBox.confirm(this, "Bạn có muốn thanh toán?");
        if (!a) {
            return;
        }
// Lấy giá trị từ ô nhập tiền khách đưa và tổng tiền
        String tienKhachDuaStr = txtTienKhach.getText().trim();
        String tongTienStr = txtTongTien.getText().replace(",", "").replace(" VND", "").trim();

        if (tienKhachDuaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền khách đưa");
            return;
        }

        try {
            // Chuyển đổi giá trị sang số
            double tienKhachDua = Double.parseDouble(tienKhachDuaStr);
            double tongTien = Double.parseDouble(tongTienStr);

            // So sánh tiền khách đưa với tổng tiền
            if (tienKhachDua < tongTien) {
                JOptionPane.showMessageDialog(this, "Số tiền khách đưa nhỏ hơn tổng tiền. Vui lòng kiểm tra lại.");
                return;
            }

            // Tiếp tục thanh toán hóa đơn nếu đủ tiền
            thanhToanHoaDon();
            molgh.setRowCount(0);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số tiền nhập vào không hợp lệ. Vui lòng nhập lại.");
        }

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void tblGioHangComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tblGioHangComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblGioHangComponentAdded

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (checkKH()) {
            daokh.add(getFromData());
            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
            JDLthemKH.setVisible(false);
            String a = txtSDTKH.getText();
            String x = daobh.findMaKHbangsdt(a);
            lbltenKH.setText(x);
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Thất Bại");
        }
    }//GEN-LAST:event_btnThemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog JDLthemKH;
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnTaoHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoaAll;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private com.toedter.calendar.JDateChooser jdcNgaySinh;
    private javax.swing.JPanel jpnkhachhang;
    private javax.swing.JLabel lblma3;
    private javax.swing.JLabel lbltenKH;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtNgaytao;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSDTKH;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTienKhach;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

}
