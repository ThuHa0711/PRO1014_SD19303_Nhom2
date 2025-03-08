/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.SanPham;

//import Com.edusys.model.nhanVien;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author VuMinhHa
 */
public class ShareHelper {
   public static final Image APP_ICON;

    static {
        String file = "/icons/8666686_github.png";
        APP_ICON = new ImageIcon(ShareHelper.class.getResource(file)).getImage();
    }

    public static boolean saveLogo(File file) {
        File dir = new File("logos");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    
    public static ImageIcon readLogo(String fileName, int width, int height) {
        File path = new File("logos", fileName);
        ImageIcon originalIcon = new ImageIcon(path.getAbsolutePath());
        Image originalImage = originalIcon.getImage();

        // Thay đổi kích thước của ảnh
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    /**
     * * Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
     */
//    public static nhanVien USER = null;

    /**
     * * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
     */
//    public static void logoff() {
//        ShareHelper1.USER = null;
//    }
//
//    /**
//     * * Kiểm tra xem đăng nhập hay chưa * @return đăng nhập hay chưa
//     */
//    public static boolean authenticated() {
//        return ShareHelper1.USER != null;
//    }
}
