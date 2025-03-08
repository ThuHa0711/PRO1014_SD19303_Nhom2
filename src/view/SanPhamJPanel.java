package view;

import entity.SanPham.ChatLieu;
import entity.SanPham.KichCo;
import entity.SanPham.MauSac;
import entity.SanPham.SanPham;
import entity.SanPham.TheLoai;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.SanPham.ChatLieuDAO;
import service.SanPham.KichCoDAO;
import service.SanPham.MauSacDAO;
import service.SanPham.SanPhamDAO;
import service.SanPham.TheLoaiDAO;

/**
 *
 * @author ADMIN
 */
public class SanPhamJPanel extends javax.swing.JPanel {

    private final TheLoaiDAO daoTheLoai = new TheLoaiDAO();
    private final ChatLieuDAO daoChatLieu = new ChatLieuDAO();
    private final MauSacDAO daoMauSac = new MauSacDAO();
    private final KichCoDAO daoKichCo = new KichCoDAO();
    private final SanPhamDAO dao = new SanPhamDAO();
    DefaultTableModel mol = new DefaultTableModel();
    private String HinhAnh;
    int index = 0;

    public SanPhamJPanel() {
        initComponents();
        mol = (DefaultTableModel) tblSanPham.getModel();

        //load;
        fillCBOTheLoai();
        fillCBOChatLieu();
        fillCBOMauSac();
        fillCBOKichCo();
        //sp
        fillSP();
//        fillTop3();
        //spct
        fillTheLoai();
        fillChatlieu();
        fillMauSac();
        fillKichCo();

    }

    void fillSP() {
        int stt = 1;
        mol.setRowCount(0);
        ArrayList<SanPham> lst = dao.getAll();
        for (SanPham sp : lst) {
            mol.addRow(new Object[]{
                stt,
                sp.getMaSp(),
                sp.getTenSp(),
                sp.getTheLoai(),
                sp.getChatLieu(),
                sp.getMauSac(),
                sp.getKichCo(),
                sp.getSoLuong(),
                sp.getDonGia(),
                sp.getAnh(),
                sp.getTrangthai(),
                sp.getMoTa()
            });
            stt++;
        }
    }

    void fillTheLoai() {
        DefaultTableModel mol1 = (DefaultTableModel) tblTheLoai.getModel();
        mol1.setRowCount(0);
        ArrayList<TheLoai> lstTl = daoTheLoai.getAll();
        for (TheLoai tl : lstTl) {
            mol1.addRow(new Object[]{
                tl.getIDTheLoai(),
                tl.getTenTheloai()
            });
        }
    }

    void fillChatlieu() {
        DefaultTableModel mol2 = (DefaultTableModel) tblChatLieu.getModel();
        mol2.setRowCount(0);
        ArrayList<ChatLieu> lstCl = daoChatLieu.getAll();
        for (ChatLieu cl : lstCl) {
            mol2.addRow(new Object[]{
                cl.getIDChatLieu(),
                cl.getTenChatLieu()
            });
        }
    }

    void fillMauSac() {
        DefaultTableModel mol3 = (DefaultTableModel) tblMauSac.getModel();
        mol3.setRowCount(0);
        ArrayList<MauSac> lstMs = daoMauSac.getAll();
        for (MauSac ms : lstMs) {
            mol3.addRow(new Object[]{
                ms.getIDMauSac(),
                ms.getTenMauSac()
            });
        }
    }

    void fillKichCo() {
        DefaultTableModel mol4 = (DefaultTableModel) tblKichCo.getModel();
        mol4.setRowCount(0);
        ArrayList<KichCo> lstKc = daoKichCo.getAll();
        for (KichCo kc : lstKc) {
            mol4.addRow(new Object[]{
                kc.getIDKichCo(),
                kc.getTenKichCo()
            });
        }
    }

    void fillCBOTheLoai() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTheLoai.getModel();
        model.removeAllElements();
        cboTheLoai.addItem("");
        List<TheLoai> list = daoTheLoai.getAll();
        for (TheLoai tl : list) {
            String ten = tl.getTenTheloai();
            if (model.getIndexOf(ten) < 0) {
                model.addElement(ten);
            }
        }
        cboTheLoai.setSelectedIndex(0);
    }

    void fillCBOChatLieu() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboChatLieu.getModel();
        model.removeAllElements();
        cboChatLieu.addItem("");
        List<ChatLieu> list = daoChatLieu.getAll();
        for (ChatLieu cl : list) {
            String ten = cl.getTenChatLieu();
            if (model.getIndexOf(ten) < 0) {
                model.addElement(ten);
            }
        }
        cboChatLieu.setSelectedIndex(0);
    }

    void fillCBOMauSac() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMauSac.getModel();
        model.removeAllElements();
        cboMauSac.addItem("");
        List<MauSac> list = daoMauSac.getAll();
        for (MauSac ms : list) {
            String ten = ms.getTenMauSac();
            if (model.getIndexOf(ten) < 0) {
                model.addElement(ten);
            }
        }
        cboMauSac.setSelectedIndex(0);
    }

    void fillCBOKichCo() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboKichCo.getModel();
        model.removeAllElements();
        cboKichCo.addItem("");
        List<KichCo> list = daoKichCo.getAll();
        for (KichCo kc : list) {
            String ten = kc.getTenKichCo();
            if (model.getIndexOf(ten) < 0) {
                model.addElement(ten);
            }
        }
        cboKichCo.setSelectedIndex(0);
    }

    public SanPham getMode() {
        SanPham sp = new SanPham();
        sp.setTenSp(txtTensp.getText());
        sp.setAnh(lblAnh.getToolTipText());

        sp.setIdTheLoai(cboTheLoai.getSelectedIndex());
        sp.setIdChatLieu(cboChatLieu.getSelectedIndex());
        sp.setIdMauSac(cboMauSac.getSelectedIndex());
        sp.setIdKichCo(cboKichCo.getSelectedIndex());

        String soLuongText = txtSoLuong.getText().trim();
        if (soLuongText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số Lượng không được để trống");
            return null; // Hoặc xử lý theo cách phù hợp
        }
        sp.setSoLuong(Integer.parseInt(soLuongText));
        String donGiaText = txtDonGia.getText().trim();
        if (donGiaText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Đơn Giá không được để trống");
            return null; // Hoặc xử lý theo cách phù hợp
        }
        sp.setDonGia(Float.parseFloat(donGiaText));

        sp.setMoTa(txtMoTa.getText());
        return sp;
    }

    public void addSanPham() {
        if (!check()) {
            return;
        }
        SanPham model = getMode();
        if (model == null) {
            return; // Dừng lại nếu model là null do lỗi nhập liệu
        }
        try {
            dao.add(model);
            this.fillSP();
            this.clear();
            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chuyên đề này đã có!");
        }
    }

    private boolean check() {
        if (txtTensp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên Sản Phẩm không được để trống");
            return false;
        } else if (isTenSPTrung(txtTensp.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Tên Sản Phẩm đã tồn tại");
            return false;
        } else if (txtSoLuong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số Lượng không được để trống");
            return false;
        } else if (!txtSoLuong.getText().trim().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Số Lượng chỉ được nhập số");
            return false;
        } else if (Integer.parseInt(txtSoLuong.getText().trim()) < 0) {
            JOptionPane.showMessageDialog(this, "Số Lượng phải lớn hơn hoặc bằng 0");
            return false;
        } else if (txtDonGia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Đơn Giá không được để trống");
            return false;
        } else if (!txtDonGia.getText().trim().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Đơn Giá chỉ được nhập số");
            return false;
        } else if (Integer.parseInt(txtDonGia.getText().trim()) < 0) {
            JOptionPane.showMessageDialog(this, "Đơn Giá phải lớn hơn hoặc bằng 0");
            return false;
        } else if (txtMoTa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mô tả không được để trống");
            return false;
        } else if (cboTheLoai.getSelectedItem() == null || cboTheLoai.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một mục từ Thể Loại");
            return false;
        } else if (cboChatLieu.getSelectedItem() == null || cboChatLieu.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một mục từ Chất Liệu");
            return false;
        } else if (cboMauSac.getSelectedItem() == null || cboMauSac.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một mục từ Màu Sắc");
            return false;
        } else if (cboKichCo.getSelectedItem() == null || cboKichCo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một mục từ Kích Cỡ");
            return false;
//        } else if (lblAnh.getToolTipText() != null && !lblAnh.getToolTipText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Bạn phải chọn một mục từ Anh");
//            return false;
//            
        } else {
            return true;
        }
    }

    public SanPham getModeU() {
        SanPham sp = new SanPham();
        sp.setMaSp(txtMasp.getText());
        sp.setTenSp(txtTensp.getText());
        sp.setAnh(lblAnh.getToolTipText());

        sp.setIdTheLoai(cboTheLoai.getSelectedIndex());
        sp.setIdChatLieu(cboChatLieu.getSelectedIndex());
        sp.setIdMauSac(cboMauSac.getSelectedIndex());
        sp.setIdKichCo(cboKichCo.getSelectedIndex());

        sp.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        sp.setDonGia(Float.parseFloat(txtDonGia.getText()));
        sp.setMoTa(txtMoTa.getText());
        return sp;
    }

    public TheLoai getTheLoai() {
        TheLoai tl = new TheLoai();
        tl.setTenTheloai(txtTenTL.getText());
        return tl;
    }

    public TheLoai getTheLoaiU() {
        TheLoai tl = new TheLoai();
        tl.setIDTheLoai(Integer.parseInt(txtIdTL.getText()));
        tl.setTenTheloai(txtTenTL.getText());
        return tl;
    }

    public ChatLieu getChatLieu() {
        ChatLieu cl = new ChatLieu();
        cl.setTenChatLieu(txtTenCL.getText());
        return cl;
    }

    public ChatLieu getChatLieuU() {
        ChatLieu cl = new ChatLieu();
        cl.setIDChatLieu(Integer.parseInt(txtIdCL.getText()));
        cl.setTenChatLieu(txtTenCL.getText());
        return cl;
    }

    public MauSac getMauSac() {
        MauSac ms = new MauSac();
        ms.setTenMauSac(txtTenMS.getText());
        return ms;
    }

    public MauSac getMauSacU() {
        MauSac ms = new MauSac();
        ms.setIDMauSac(Integer.parseInt(txtIdMS.getText()));
        ms.setTenMauSac(txtTenMS.getText());
        return ms;
    }

    public KichCo getKichCo() {
        KichCo kc = new KichCo();
        kc.setTenKichCo(txtTenKC.getText());
        return kc;
    }

    public KichCo getKichCoU() {
        KichCo kc = new KichCo();
        kc.setIDKichCo(Integer.parseInt(txtIdKC.getText()));
        kc.setTenKichCo(txtTenKC.getText());
        return kc;
    }

    void setModel(int index) {
        SanPham sp = dao.getAll().get(index);
        txtMasp.setText(sp.getMaSp());
        txtTensp.setText(sp.getTenSp());
        txtSoLuong.setText(String.valueOf(sp.getSoLuong()));
        txtDonGia.setText(String.valueOf(sp.getDonGia()));
        txtMoTa.setText(sp.getMoTa());

        cboTheLoai.setSelectedItem(sp.getTheLoai());
        cboChatLieu.setSelectedItem(sp.getChatLieu());
        cboMauSac.setSelectedItem(sp.getMauSac());
        cboKichCo.setSelectedItem(sp.getKichCo());

        lblAnh.setToolTipText(sp.getAnh());

        this.lblAnh.setPreferredSize(new Dimension(100, 100));
        this.lblAnh.setMinimumSize(new Dimension(100, 100));
        this.lblAnh.setMaximumSize(new Dimension(100, 100));

        ImageIcon defaultIcon = new ImageIcon(new BufferedImage(138, 138, BufferedImage.TYPE_INT_ARGB));

        if (sp.getAnh() != null && !sp.getAnh().trim().isEmpty()) {
            this.lblAnh.setText("");
            ImageIcon imgHinhAnh = new ImageIcon("E:\\PRO1014_SD19303_Nhom2\\src\\img/" + sp.getAnh());
            Image img = imgHinhAnh.getImage();
            this.HinhAnh = sp.getAnh();
            this.lblAnh.setIcon(new ImageIcon(img.getScaledInstance(138, 138, Image.SCALE_SMOOTH)));
        } else {
            // Nếu sp.getAnh() rỗng hoặc không có tên ảnh, đặt biểu tượng mặc định vào lblAnh
            this.lblAnh.setText("");
            this.lblAnh.setIcon(defaultIcon);
        }

        if (sp.getTrangthai().equalsIgnoreCase("Còn hàng")) {
            rdoConHang.setSelected(true);
        } else {
            rdoHetHang.setSelected(true);
        }
    }


    void setModelTheLoai(int index) {
        TheLoai tl = daoTheLoai.getAll().get(index);
        txtTenTL.setText(tl.getTenTheloai());
        txtIdTL.setText(String.valueOf(tl.getIDTheLoai()));
    }

    void setModelChatLieu(int index) {
        ChatLieu cl = daoChatLieu.getAll().get(index);
        txtTenCL.setText(cl.getTenChatLieu());
        txtIdCL.setText(String.valueOf(cl.getIDChatLieu()));
    }

    void setModelMauSac(int index) {
        MauSac ms = daoMauSac.getAll().get(index);
        txtTenMS.setText(ms.getTenMauSac());
        txtIdMS.setText(String.valueOf(ms.getIDMauSac()));
    }

    void setModelKichCo(int index) {
        KichCo kc = daoKichCo.getAll().get(index);
        txtTenKC.setText(kc.getTenKichCo());
        txtIdKC.setText(String.valueOf(kc.getIDKichCo()));
    }

    void selectImage() {
        try {
            JFileChooser jfc = new JFileChooser("E:\\PRO1014_SD19303_Nhom2\\src\\img/");
            jfc.showOpenDialog(null);
            File file = jfc.getSelectedFile();
            this.HinhAnh = file.getName();
            Image img = ImageIO.read(file);
            this.lblAnh.setText("");
            this.lblAnh.setIcon(new ImageIcon(img.getScaledInstance(138, 138, 0)));
            this.lblAnh.setToolTipText(HinhAnh);
        } catch (Exception e) {
            return;
        }

    }

    // THỂ LOẠI
    void insertTL() {
        TheLoai mod1 = getTheLoai();
        try {
            daoTheLoai.add(mod1);
            this.fillTheLoai();
            this.fillCBOTheLoai();
            this.clearTL();
            JOptionPane.showMessageDialog(this, "Thêm Thành Cônng");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chuyên đề này đã có!");
        }
    }

    void deleteTL() {
        String idTL = txtIdTL.getText();
        try {
            daoTheLoai.del(idTL);
            this.fillTheLoai();
            this.fillCBOTheLoai();
            this.clearTL();
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại! ");
        }
    }

    void updateTL() {
        TheLoai mol1 = getTheLoaiU();
        try {
            daoTheLoai.update(mol1);
            this.fillTheLoai();
            this.fillCBOTheLoai();
            this.clearTL();
            JOptionPane.showMessageDialog(this, "Update thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "update thất bại! ");
        }
    }

    void clearTL() {
        txtIdTL.setText("");
        txtTenTL.setText("");
    }

    //CHẤT LIỆU
    void insertCL() {
        ChatLieu mod2 = getChatLieu();
        try {
            daoChatLieu.add(mod2);
            this.fillChatlieu();
            this.fillCBOChatLieu();
            this.clearCL();
            JOptionPane.showMessageDialog(this, "Thêm Thành Cônng");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chuyên đề này đã có!");
        }
    }

    void deleteCL() {
        String idNcc = txtIdCL.getText();
        try {
            daoTheLoai.del(idNcc);
            this.fillChatlieu();
            this.fillCBOChatLieu();
            this.clearCL();
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại! ");
        }
    }

    void updateCL() {
        ChatLieu mod2 = getChatLieuU();
        try {
            daoChatLieu.update(mod2);
            this.fillChatlieu();
            this.fillCBOChatLieu();
            this.clearCL();
            JOptionPane.showMessageDialog(this, "Update thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "update thất bại! ");
        }
    }

    void clearCL() {
        txtIdCL.setText("");
        txtTenCL.setText("");
    }

    //MÀU SẮC
    void insertMS() {
        MauSac mol3 = getMauSac();
        try {
            daoMauSac.add(mol3);
            this.fillMauSac();
            this.fillCBOMauSac();
            this.clearMS();
            JOptionPane.showMessageDialog(this, "Thêm Thành Cônng");
            ;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chuyên đề này đã có!");
        }
    }

    void deleteMS() {
        String ms = txtIdMS.getText();
        try {
            daoMauSac.del(ms);
            this.fillMauSac();
            this.fillCBOMauSac();
            this.clearMS();
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại! ");
        }
    }

    void updateMS() {
        MauSac mod3 = getMauSacU();
        try {
            daoMauSac.update(mod3);
            this.fillMauSac();
            this.fillCBOMauSac();
            this.clearMS();
            JOptionPane.showMessageDialog(this, "Update thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "update thất bại! ");
        }
    }

    void clearMS() {
        txtIdMS.setText("");
        txtTenMS.setText("");
    }

    //KÍCH CỠ
    void insertKC() {
        KichCo mol4 = getKichCo();
        try {
            daoKichCo.add(mol4);
            this.fillKichCo();
            this.fillCBOKichCo();
            this.clearKC();
            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chuyên đề này đã có!");
        }
    }

    void deleteKC() {
        String ms = txtIdMS.getText();
        try {
            daoMauSac.del(ms);
            this.fillKichCo();
            this.fillCBOKichCo();
            this.clearKC();

            JOptionPane.showMessageDialog(this, "Xóa thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại! ");
        }
    }

    void updateKC() {
        KichCo mod4 = getKichCoU();
        try {
            daoKichCo.update(mod4);
            this.fillKichCo();
            this.fillCBOKichCo();
            this.clearKC();

            JOptionPane.showMessageDialog(this, "Update thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Update thất bại! ");
        }
    }

    void clearKC() {
        txtIdKC.setText("");
        txtTenKC.setText("");
    }

    void clear() {
        txtTensp.setText("");
        txtMasp.setText("");
        txtMoTa.setText("");
        txtSoLuong.setText("");
        txtDonGia.setText("");
        lblAnh.setIcon(null);
        cboTheLoai.setSelectedIndex(0);
        cboChatLieu.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);
        cboKichCo.setSelectedIndex(0);
    }

    private boolean isTenSPTrung(String tenSP) {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (tenSP.equals(model.getValueAt(i, 2))) {
                return true;
            }
        }
        return false;
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
        tabs = new javax.swing.JTabbedPane();
        pnlSP = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtFind = new javax.swing.JTextField();
        cboLoc = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txtMasp = new javax.swing.JTextField();
        txtTensp = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        cboTheLoai = new javax.swing.JComboBox<>();
        cboChatLieu = new javax.swing.JComboBox<>();
        cboKichCo = new javax.swing.JComboBox<>();
        cboMauSac = new javax.swing.JComboBox<>();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        rdoConHang = new javax.swing.JRadioButton();
        rdoHetHang = new javax.swing.JRadioButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnAddSP = new javax.swing.JButton();
        btnDeleteSP = new javax.swing.JButton();
        btnUpdateSP = new javax.swing.JButton();
        btnClearSP = new javax.swing.JButton();
        btnXuat = new javax.swing.JButton();
        pnlCTSP = new javax.swing.JPanel();
        plnTL = new javax.swing.JPanel();
        btnAddTL = new javax.swing.JButton();
        btnDeleteTL = new javax.swing.JButton();
        btnUpdateTL = new javax.swing.JButton();
        btnClearTL = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtIdTL = new javax.swing.JTextField();
        txtTenTL = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblTheLoai = new javax.swing.JTable();
        plnCL = new javax.swing.JPanel();
        btnAddCL = new javax.swing.JButton();
        btnDeleteCL = new javax.swing.JButton();
        btnUpdateCL = new javax.swing.JButton();
        btnClearCL = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtTenCL = new javax.swing.JTextField();
        txtIdCL = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblChatLieu = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        pnlMS = new javax.swing.JPanel();
        btnAddMS = new javax.swing.JButton();
        btnDeleteMS = new javax.swing.JButton();
        btnUpdateMS = new javax.swing.JButton();
        btnClearMS = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtTenMS = new javax.swing.JTextField();
        txtIdMS = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblMauSac = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        pnlKC = new javax.swing.JPanel();
        btnAddKC = new javax.swing.JButton();
        btnDeleteKC = new javax.swing.JButton();
        btnUpdateKC = new javax.swing.JButton();
        btnClearKC = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtIdKC = new javax.swing.JTextField();
        txtTenKC = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblKichCo = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Danh sách Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Thể Loại", "Chất Liệu", "Màu sắc", "Kích cỡ", "Số lượng", "Đơn giá", "Ảnh", "Trạng thái", "Mô Tả"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblSanPham);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel10.setText("Tìm kiếm :");

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

        cboLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "còn hàng", "Hết hàng" }));
        cboLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel5.setText("Trạng Thái :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtFind)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboLoc))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("Quản lý Sản Phẩm");

        jPanel11.setBackground(new java.awt.Color(204, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txtMasp.setEditable(false);
        txtMasp.setBackground(new java.awt.Color(224, 224, 224));

        cboTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheLoaiActionPerformed(evt);
            }
        });

        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboKichCo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane11.setViewportView(txtMoTa);

        buttonGroup1.add(rdoConHang);
        rdoConHang.setText("Còn Hàng");
        rdoConHang.setEnabled(false);

        buttonGroup1.add(rdoHetHang);
        rdoHetHang.setText("Hết hàng");
        rdoHetHang.setEnabled(false);

        jLabel45.setText("Trạng thái");

        jLabel46.setText("Mô tả");

        jLabel47.setText("Chất Liệu");

        lblAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/8666659_image.png"))); // NOI18N
        lblAnh.setText("Nhấp chọn ảnh");
        lblAnh.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ảnh"));
        lblAnh.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblAnh.setDisabledIcon(null);
        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblAnhMousePressed(evt);
            }
        });

        jLabel48.setText("Mã Sản Phẩm");

        jLabel49.setText("Tên Sản Phẩm");

        jLabel50.setText("Thể Loại");

        jLabel51.setText("Số lượng");

        jLabel52.setText("Đơn giá");

        jLabel53.setText("Màu Sắc");

        jLabel54.setText("Kích Cỡ");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAddSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Add.png"))); // NOI18N
        btnAddSP.setText("Add");
        btnAddSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSPActionPerformed(evt);
            }
        });

        btnDeleteSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/No.png"))); // NOI18N
        btnDeleteSP.setText("Delete");
        btnDeleteSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSPActionPerformed(evt);
            }
        });

        btnUpdateSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Card file.png"))); // NOI18N
        btnUpdateSP.setText("Update");
        btnUpdateSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateSPActionPerformed(evt);
            }
        });

        btnClearSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Application form.png"))); // NOI18N
        btnClearSP.setText("Clear");
        btnClearSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSPActionPerformed(evt);
            }
        });

        btnXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons-excel.png"))); // NOI18N
        btnXuat.setText("Xuất File");
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdateSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClearSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnAddSP, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDeleteSP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdateSP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClearSP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50)
                            .addComponent(jLabel47)
                            .addComponent(jLabel54)
                            .addComponent(jLabel53))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTheLoai, 0, 173, Short.MAX_VALUE)
                            .addComponent(cboChatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboKichCo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49)
                            .addComponent(jLabel48))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTensp)
                            .addComponent(txtMasp))))
                .addGap(97, 97, 97)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addComponent(jLabel51))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDonGia)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(rdoConHang)
                                .addGap(18, 18, 18)
                                .addComponent(rdoHetHang))
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(53, 53, 53)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel51)
                                        .addGap(24, 24, 24))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel52)
                                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(txtMasp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTensp, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel50))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel47))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel54)
                                    .addComponent(cboKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel45)
                                    .addComponent(rdoHetHang)
                                    .addComponent(rdoConHang))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel46))
                                .addGap(17, 17, 17))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout pnlSPLayout = new javax.swing.GroupLayout(pnlSP);
        pnlSP.setLayout(pnlSPLayout);
        pnlSPLayout.setHorizontalGroup(
            pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(pnlSPLayout.createSequentialGroup()
                .addGap(461, 461, 461)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSPLayout.setVerticalGroup(
            pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Sản phẩm", pnlSP);

        plnTL.setBackground(new java.awt.Color(204, 255, 255));
        plnTL.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Thể loại"));
        plnTL.setPreferredSize(new java.awt.Dimension(810, 170));

        btnAddTL.setText("Add");
        btnAddTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTLActionPerformed(evt);
            }
        });

        btnDeleteTL.setText("Delete");
        btnDeleteTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTLActionPerformed(evt);
            }
        });

        btnUpdateTL.setText("Update");
        btnUpdateTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateTLActionPerformed(evt);
            }
        });

        btnClearTL.setText("Clear");
        btnClearTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearTLActionPerformed(evt);
            }
        });

        jLabel26.setText("Tên Thể Loại");

        jLabel27.setText("ID");

        txtIdTL.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thể Loại");

        tblTheLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Tên loại"
            }
        ));
        tblTheLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTheLoaiMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblTheLoai);

        javax.swing.GroupLayout plnTLLayout = new javax.swing.GroupLayout(plnTL);
        plnTL.setLayout(plnTLLayout);
        plnTLLayout.setHorizontalGroup(
            plnTLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnTLLayout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plnTLLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(plnTLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(plnTLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdTL, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenTL, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plnTLLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddTL, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnDeleteTL, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnUpdateTL, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnClearTL, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        plnTLLayout.setVerticalGroup(
            plnTLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnTLLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(plnTLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plnTLLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(plnTLLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(plnTLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addGap(18, 18, 18)
                        .addGroup(plnTLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))))
                .addGap(29, 29, 29)
                .addGroup(plnTLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddTL)
                    .addComponent(btnDeleteTL)
                    .addComponent(btnUpdateTL)
                    .addComponent(btnClearTL))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        plnCL.setBackground(new java.awt.Color(204, 255, 255));
        plnCL.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Chất liệu"));
        plnCL.setPreferredSize(new java.awt.Dimension(810, 170));

        btnAddCL.setText("Add");
        btnAddCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCLActionPerformed(evt);
            }
        });

        btnDeleteCL.setText("Delete");
        btnDeleteCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCLActionPerformed(evt);
            }
        });

        btnUpdateCL.setText("Update");
        btnUpdateCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCLActionPerformed(evt);
            }
        });

        btnClearCL.setText("Clear");
        btnClearCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearCLActionPerformed(evt);
            }
        });

        jLabel31.setText("Tên Chất Liệu");

        jLabel32.setText("ID");

        txtIdCL.setEditable(false);

        tblChatLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Tên Chất Liệu"
            }
        ));
        tblChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChatLieuMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblChatLieu);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Chất Liệu");

        javax.swing.GroupLayout plnCLLayout = new javax.swing.GroupLayout(plnCL);
        plnCL.setLayout(plnCLLayout);
        plnCLLayout.setHorizontalGroup(
            plnCLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnCLLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(plnCLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(plnCLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIdCL, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(txtTenCL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(plnCLLayout.createSequentialGroup()
                .addGroup(plnCLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plnCLLayout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(plnCLLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btnAddCL, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnDeleteCL, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btnUpdateCL, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btnClearCL, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        plnCLLayout.setVerticalGroup(
            plnCLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnCLLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(plnCLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plnCLLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(plnCLLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(plnCLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32))
                        .addGap(27, 27, 27)
                        .addGroup(plnCLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(plnCLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plnCLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddCL)
                        .addComponent(btnDeleteCL))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plnCLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdateCL)
                        .addComponent(btnClearCL)))
                .addGap(59, 59, 59))
        );

        pnlMS.setBackground(new java.awt.Color(204, 255, 255));
        pnlMS.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Màu sắc"));
        pnlMS.setPreferredSize(new java.awt.Dimension(810, 170));

        btnAddMS.setText("Add");
        btnAddMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMSActionPerformed(evt);
            }
        });

        btnDeleteMS.setText("Delete");
        btnDeleteMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMSActionPerformed(evt);
            }
        });

        btnUpdateMS.setText("Update");
        btnUpdateMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateMSActionPerformed(evt);
            }
        });

        btnClearMS.setText("Clear");
        btnClearMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearMSActionPerformed(evt);
            }
        });

        jLabel33.setText("Tên Màu Sắc");

        jLabel34.setText("ID");

        txtIdMS.setEditable(false);
        txtIdMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdMSActionPerformed(evt);
            }
        });

        tblMauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Tên Màu Sắc"
            }
        ));
        tblMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauSacMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblMauSac);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Màu Sắc");

        javax.swing.GroupLayout pnlMSLayout = new javax.swing.GroupLayout(pnlMS);
        pnlMS.setLayout(pnlMSLayout);
        pnlMSLayout.setHorizontalGroup(
            pnlMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlMSLayout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(26, 26, 26)
                        .addComponent(txtTenMS, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMSLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIdMS, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(pnlMSLayout.createSequentialGroup()
                .addGroup(pnlMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMSLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMSLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(btnAddMS, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnDeleteMS, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btnUpdateMS)
                        .addGap(38, 38, 38)
                        .addComponent(btnClearMS)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMSLayout.setVerticalGroup(
            pnlMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMSLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(txtIdMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(pnlMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(txtTenMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMSLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(pnlMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddMS)
                    .addComponent(btnUpdateMS)
                    .addComponent(btnClearMS)
                    .addComponent(btnDeleteMS))
                .addGap(37, 37, 37))
        );

        pnlKC.setBackground(new java.awt.Color(204, 255, 255));
        pnlKC.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Kích cỡ"));
        pnlKC.setPreferredSize(new java.awt.Dimension(810, 170));

        btnAddKC.setText("Add");
        btnAddKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddKCActionPerformed(evt);
            }
        });

        btnDeleteKC.setText("Delete");
        btnDeleteKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteKCActionPerformed(evt);
            }
        });

        btnUpdateKC.setText("Update");
        btnUpdateKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateKCActionPerformed(evt);
            }
        });

        btnClearKC.setText("Clear");
        btnClearKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearKCActionPerformed(evt);
            }
        });

        jLabel35.setText("Tên Kích Cỡ");

        jLabel36.setText("ID");

        txtIdKC.setEditable(false);

        tblKichCo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Tên Kích Cỡ"
            }
        ));
        tblKichCo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKichCoMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblKichCo);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Kích Cỡ");

        javax.swing.GroupLayout pnlKCLayout = new javax.swing.GroupLayout(pnlKC);
        pnlKC.setLayout(pnlKCLayout);
        pnlKCLayout.setHorizontalGroup(
            pnlKCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKCLayout.createSequentialGroup()
                .addGroup(pnlKCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKCLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btnAddKC)
                        .addGap(28, 28, 28)
                        .addComponent(btnDeleteKC)
                        .addGap(37, 37, 37)
                        .addComponent(btnUpdateKC)
                        .addGap(34, 34, 34)
                        .addComponent(btnClearKC))
                    .addGroup(pnlKCLayout.createSequentialGroup()
                        .addGroup(pnlKCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlKCLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(pnlKCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlKCLayout.createSequentialGroup()
                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35))
                                    .addGroup(pnlKCLayout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addGap(18, 18, 18)))
                                .addGroup(pnlKCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIdKC, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                    .addComponent(txtTenKC)))
                            .addGroup(pnlKCLayout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        pnlKCLayout.setVerticalGroup(
            pnlKCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKCLayout.createSequentialGroup()
                .addGroup(pnlKCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKCLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKCLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlKCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35)
                            .addGroup(pnlKCLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pnlKCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtIdKC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel36))
                                .addGap(29, 29, 29)
                                .addComponent(txtTenKC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(pnlKCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateKC)
                    .addComponent(btnDeleteKC)
                    .addComponent(btnAddKC)
                    .addComponent(btnClearKC))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout pnlCTSPLayout = new javax.swing.GroupLayout(pnlCTSP);
        pnlCTSP.setLayout(pnlCTSPLayout);
        pnlCTSPLayout.setHorizontalGroup(
            pnlCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCTSPLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(plnTL, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                    .addComponent(pnlMS, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
                .addGap(18, 27, Short.MAX_VALUE)
                .addGroup(pnlCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlKC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plnCL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlCTSPLayout.setVerticalGroup(
            pnlCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCTSPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(plnTL, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plnCL, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
                .addGap(39, 39, 39)
                .addGroup(pnlCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlMS, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addComponent(pnlKC, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
                .addGap(129, 129, 129))
        );

        tabs.addTab("Thông tin chi tiết", pnlCTSP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 1193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        index = tblSanPham.getSelectedRow();
        setModel(index);
//        // TODO add your handling code here:
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void txtFindKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindKeyReleased
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
        TableRowSorter<DefaultTableModel> abc = new TableRowSorter<>(dtm);
        tblSanPham.setRowSorter(abc);
        abc.setRowFilter(RowFilter.regexFilter(txtFind.getText(), 0, 1));
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindKeyReleased

    private void btnAddTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTLActionPerformed
        insertTL();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddTLActionPerformed

    private void btnDeleteTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTLActionPerformed
        deleteTL();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteTLActionPerformed

    private void btnUpdateTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTLActionPerformed
        updateTL();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateTLActionPerformed

    private void tblTheLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTheLoaiMouseClicked
        index = tblTheLoai.getSelectedRow();
        setModelTheLoai(index);
        // TODO add your handling code here:
    }//GEN-LAST:event_tblTheLoaiMouseClicked

    private void btnClearTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearTLActionPerformed
        clearTL();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearTLActionPerformed

    private void cboTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheLoaiActionPerformed

    }//GEN-LAST:event_cboTheLoaiActionPerformed

    private void lblAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseClicked
        this.selectImage();
        // TODO add your handling code here:
    }//GEN-LAST:event_lblAnhMouseClicked

    private void lblAnhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMousePressed

        // TODO add your handling code here:
    }//GEN-LAST:event_lblAnhMousePressed

    private void btnAddSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSPActionPerformed
        addSanPham();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddSPActionPerformed

    private void btnDeleteSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSPActionPerformed
        String masp = txtMasp.getText();
        try {
            dao.del(masp);
            this.fillSP();
            //   this.fillTop3();
            this.clear();
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại! ");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteSPActionPerformed

    private void btnUpdateSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSPActionPerformed
        SanPham model = getModeU();
        try {
            dao.update(model);
            this.fillSP();
            // this.fillTop3();
            this.clear();
            JOptionPane.showMessageDialog(this, "Update thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Update thất bại! ");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateSPActionPerformed

    private void btnClearSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSPActionPerformed
        clear();
    }//GEN-LAST:event_btnClearSPActionPerformed

    private void btnAddCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCLActionPerformed
        insertCL();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddCLActionPerformed

    private void btnDeleteCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCLActionPerformed
        deleteCL();
// TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteCLActionPerformed

    private void btnUpdateCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCLActionPerformed
        updateCL();
// TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateCLActionPerformed

    private void tblChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChatLieuMouseClicked
        // TODO add your handling code here:
        index = tblChatLieu.getSelectedRow();
        setModelChatLieu(index);
    }//GEN-LAST:event_tblChatLieuMouseClicked

    private void btnClearCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCLActionPerformed
        clearCL();
// TODO add your handling code here:
    }//GEN-LAST:event_btnClearCLActionPerformed

    private void btnAddMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMSActionPerformed
        // TODO add your handling code here:
        insertMS();
    }//GEN-LAST:event_btnAddMSActionPerformed

    private void btnDeleteMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMSActionPerformed
        // TODO add your handling code here:
        deleteMS();
    }//GEN-LAST:event_btnDeleteMSActionPerformed

    private void btnUpdateMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateMSActionPerformed
        // TODO add your handling code here:
        updateMS();
    }//GEN-LAST:event_btnUpdateMSActionPerformed

    private void tblMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseClicked
        // TODO add your handling code here:
        index = tblMauSac.getSelectedRow();
        setModelMauSac(index);
    }//GEN-LAST:event_tblMauSacMouseClicked

    private void btnClearMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearMSActionPerformed
        // TODO add your handling code here:
        clearMS();
    }//GEN-LAST:event_btnClearMSActionPerformed

    private void btnAddKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddKCActionPerformed
        // TODO add your handling code here:
        insertKC();
    }//GEN-LAST:event_btnAddKCActionPerformed

    private void btnDeleteKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteKCActionPerformed
        // TODO add your handling code here:
        deleteKC();
    }//GEN-LAST:event_btnDeleteKCActionPerformed

    private void btnUpdateKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateKCActionPerformed
        // TODO add your handling code here:
        updateKC();
    }//GEN-LAST:event_btnUpdateKCActionPerformed

    private void tblKichCoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKichCoMouseClicked
        // TODO add your handling code here:
        index = tblKichCo.getSelectedRow();
        setModelKichCo(index);
    }//GEN-LAST:event_tblKichCoMouseClicked

    private void btnClearKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearKCActionPerformed
        // TODO add your handling code here:
        clearKC();
    }//GEN-LAST:event_btnClearKCActionPerformed

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        // TODO add your handling code here:
        try {
//            URL url = SanPhamJPanel.class.getResource("C:\\Users\\ADMIN\\Desktop\\excel\\");
            JFileChooser jfc = new JFileChooser("E:\\PRO1014_SD19303_Nhom2\\lib\\EXCEL FILES");
            jfc.showDialog(this, "Save AS");
            File saveFile = jfc.getSelectedFile();
            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet spreadsheet = workbook.createSheet("Danh Sách Sản Phẩm");
                XSSFRow row = null;
                Cell cell = null;
                row = spreadsheet.createRow((short) 0);
                row.setHeight((short) 500);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("STT");
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Mã SP");
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Tên SP");
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Thể Loại");
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Chất Liệu");
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Màu Sắc");
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue("Kích Cỡ");
                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue("Số Lượng");
                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue("Đơn Giá");
                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue("Ảnh");
                cell = row.createCell(10, CellType.STRING);
                cell.setCellValue("Trạng Thái");
                cell = row.createCell(11, CellType.STRING);
                cell.setCellValue("Mô Tả");
                for (int i = 0; i < tblSanPham.getRowCount(); i++) {
                    row = spreadsheet.createRow((short) 1 + i);
                    row.setHeight((short) 400);
                    row.createCell(0).setCellValue(i + 1);
                    row.createCell(1).setCellValue(tblSanPham.getValueAt(i, 0).toString());
                    row.createCell(2).setCellValue(tblSanPham.getValueAt(i, 1).toString());
                    row.createCell(3).setCellValue(tblSanPham.getValueAt(i, 2).toString());
                    row.createCell(4).setCellValue(tblSanPham.getValueAt(i, 3).toString());
                    row.createCell(5).setCellValue(tblSanPham.getValueAt(i, 4).toString());
                    row.createCell(6).setCellValue(tblSanPham.getValueAt(i, 5).toString());
                    row.createCell(7).setCellValue(tblSanPham.getValueAt(i, 6).toString());
                    row.createCell(8).setCellValue(tblSanPham.getValueAt(i, 7).toString());
                    row.createCell(9).setCellValue(tblSanPham.getValueAt(i, 8).toString());
                    row.createCell(10).setCellValue(tblSanPham.getValueAt(i, 9).toString());
                    row.createCell(11).setCellValue(tblSanPham.getValueAt(i, 10).toString());

                }
                FileOutputStream out = new FileOutputStream(saveFile);
                JOptionPane.showMessageDialog(this, "Lưu thành công");
                workbook.write(out);
                out.close();
                Desktop.getDesktop().open(saveFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_btnXuatActionPerformed

    private void txtIdMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdMSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdMSActionPerformed

    private void txtFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindActionPerformed

    private void cboLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocActionPerformed
        // TODO add your handling code here:
        String selectedStatus = (String) cboLoc.getSelectedItem();
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
        TableRowSorter<DefaultTableModel> abc = new TableRowSorter<>(dtm);
        tblSanPham.setRowSorter(abc);
        if (selectedStatus.equals("Tất Cả")) {
            abc.setRowFilter(null);
        }else if(selectedStatus.equals("còn hàng")) {
            abc.setRowFilter(RowFilter.regexFilter(selectedStatus, 10));
        }else{
            abc.setRowFilter(RowFilter.regexFilter(selectedStatus, 10));
        }
    }//GEN-LAST:event_cboLocActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCL;
    private javax.swing.JButton btnAddKC;
    private javax.swing.JButton btnAddMS;
    private javax.swing.JButton btnAddSP;
    private javax.swing.JButton btnAddTL;
    private javax.swing.JButton btnClearCL;
    private javax.swing.JButton btnClearKC;
    private javax.swing.JButton btnClearMS;
    private javax.swing.JButton btnClearSP;
    private javax.swing.JButton btnClearTL;
    private javax.swing.JButton btnDeleteCL;
    private javax.swing.JButton btnDeleteKC;
    private javax.swing.JButton btnDeleteMS;
    private javax.swing.JButton btnDeleteSP;
    private javax.swing.JButton btnDeleteTL;
    private javax.swing.JButton btnUpdateCL;
    private javax.swing.JButton btnUpdateKC;
    private javax.swing.JButton btnUpdateMS;
    private javax.swing.JButton btnUpdateSP;
    private javax.swing.JButton btnUpdateTL;
    private javax.swing.JButton btnXuat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboKichCo;
    private javax.swing.JComboBox<String> cboLoc;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboTheLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JPanel plnCL;
    private javax.swing.JPanel plnTL;
    private javax.swing.JPanel pnlCTSP;
    private javax.swing.JPanel pnlKC;
    private javax.swing.JPanel pnlMS;
    private javax.swing.JPanel pnlSP;
    private javax.swing.JRadioButton rdoConHang;
    private javax.swing.JRadioButton rdoHetHang;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblChatLieu;
    private javax.swing.JTable tblKichCo;
    private javax.swing.JTable tblMauSac;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblTheLoai;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtIdCL;
    private javax.swing.JTextField txtIdKC;
    private javax.swing.JTextField txtIdMS;
    private javax.swing.JTextField txtIdTL;
    private javax.swing.JTextField txtMasp;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenCL;
    private javax.swing.JTextField txtTenKC;
    private javax.swing.JTextField txtTenMS;
    private javax.swing.JTextField txtTenTL;
    private javax.swing.JTextField txtTensp;
    // End of variables declaration//GEN-END:variables
}
