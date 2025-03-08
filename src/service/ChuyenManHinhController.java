
package service;

import entity.SanPham.DanhMuc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.BanHangJPanel;
import view.VoucherJpanel;
import view.HoaDonJPanel;
import view.KhachHangJPanel;
import view.NhanVienJpanel;
import view.SanPhamJPanel;
import view.ThongKeJPanel;

/**
 *
 * @author TranDyu
 */
public class ChuyenManHinhController {
    private String manv;
     private String tennv;
    private JPanel root;
    private String kindSelected = "";
    private List<DanhMuc> listItem = null;

    public ChuyenManHinhController(JPanel jpnRoot,String customString, String tenNV) {   
       // manv = customString;
        this.root = jpnRoot;
        manv = customString;
        tennv = tenNV;
    }

    public void setview(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "TrangChu";
        jpnItem.setBackground(new Color(96, 100, 191));
        jlbItem.setBackground(new Color(96, 100, 191));

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new BanHangJPanel(manv,tennv));
        root.validate();
        root.repaint();
    }

    public void setEvent(List<DanhMuc> listItem) {
        this.listItem = listItem;
        for (DanhMuc item : this.listItem) {
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node;
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
            System.out.println(this.kind);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(kind);
            switch (kind) {
                case "BanHang":
                    node = new BanHangJPanel(manv,tennv);
                    break;
                case "SanPham":
                    node = new SanPhamJPanel();
                    break;
                case "NhanVien":
                    node = new NhanVienJpanel();
                    break;
                case "KhachHang":
                    node = new KhachHangJPanel();
                    break;
                case "HoaDon":
                    node = new HoaDonJPanel();
                    break;
                case "ThongKe":
                    node = new ThongKeJPanel();
                    break;
                case "Voucher":
                    node = new VoucherJpanel();
                    break;
                default:
                    break;
            }

            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(32,11,10));
                jlbItem.setBackground(new Color(32,11,10));
            }
        }

    }

    private void setChangeBackground(String kind) {
        for (DanhMuc item : listItem) {
            if (item.getKind().equalsIgnoreCase(kind)) {
                item.getJpn().setBackground(new Color(96, 100, 191));
                item.getJlb().setBackground(new Color(96, 100, 191));
            } else {
                item.getJpn().setBackground(new Color(32,11,10));
                item.getJlb().setBackground(new Color(32,11,10));
            }
        }
    }
}
