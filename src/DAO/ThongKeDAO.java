package DAO;

import Entity.KetQua;
import Entity.SinhVien;
import Utils.jdbchelper;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import quanlisinhvien.TrangChu;

public class ThongKeDAO extends QuanLySinhVienDAO<KetQua, String> {

    List<KetQua> list = new ArrayList<>();
    List<SinhVien> dssv = new ArrayList<>();

    String selectAll_sql = " select * from tblKET_QUA ";
    String selectAll_sv = " select * from tblSINH_VIEN ";

    @Override
    public void insert(KetQua entity) {

    }

    @Override
    public void update(KetQua entity) {

    }

    @Override
    public void delete(String key) {

    }

    @Override
    public List<KetQua> selectAll() {
        return selectBySql(selectAll_sql);
    }

    public List<SinhVien> selectAllSV() {
        return selectBySV(selectAll_sv);
    }

    @Override
    public KetQua selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<KetQua> selectBySql(String sql, Object... args) {
        try {
            ResultSet rs = jdbchelper.query(sql, args);
            while (rs.next()) {
                KetQua entity = new KetQua();
                entity.setMaSV(rs.getString("masv"));
                entity.setHoTen(rs.getString("hoten"));
                entity.setMaMon(rs.getString("mamon"));
                entity.setDiemTB(rs.getFloat("diemtb"));
                entity.setDiemTongKet(rs.getFloat("diemtongket"));
                entity.setHanhKiem(rs.getString("hanhkiem"));
                entity.setHocKi(rs.getInt("hocki"));
                entity.setGhiChu(rs.getString("ghichu"));
                list.add(entity);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<SinhVien> selectBySV(String sql, Object... args) {
        try {
            ResultSet rs = jdbchelper.query(sql, args);
            while (rs.next()) {
                SinhVien sv = new SinhVien();
                sv.setMaSV(rs.getString(1));
                sv.setHoTen(rs.getString(2));
                sv.setNgaySinh(rs.getString(3));
                sv.setGioitinh(rs.getBoolean(4));
                sv.setDiaChi(rs.getString(5));
                sv.setMaLop(rs.getString(6));
                dssv.add(sv);
            }
        } catch (Exception e) {
        }
        return dssv;
    }

    public void indssv() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("quanlydanhsachsinhvien");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(0);
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Danh sách sinh viên");

            row = sheet.createRow(3);
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Số thứ tự");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Mã sinh viên");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Họ tên");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Ngày sinh");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Giới tính");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Địa chỉ");

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Mã lớp");

            for (int y = 0; y < dssv.size(); y++) {
                row = sheet.createRow(4 + y);
                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(y + 1);

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(dssv.get(y).getMaSV());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(dssv.get(y).getHoTen());

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(dssv.get(y).getNgaySinh());

                cell = row.createCell(6, CellType.STRING);
                if (dssv.get(y).isGioitinh() == false) {
                    cell.setCellValue("Nam");
                } else {
                    cell.setCellValue("Nữ");
                }

                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(dssv.get(y).getDiaChi());

                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue(dssv.get(y).getMaLop());

            }

            File f = new File("D://thongkedssv.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);
                workbook.write(fis);
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inbangdiemsv() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("quanlydiemsinhvien");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(0);
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Bảng điểm sinh viên");

            row = sheet.createRow(3);
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Số thứ tự");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Mã sinh viên");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Họ tên");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Mã môn");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Điểm trung bình");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Điểm tổng kết");

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Hạnh kiểm");

            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Học kì");

            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("Ghi chú");

            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow(4 + i);
                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(i + 1);

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(list.get(i).getMaSV());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(list.get(i).getHoTen());

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(list.get(i).getMaMon());

                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(list.get(i).getDiemTB());

                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(list.get(i).getDiemTongKet());

                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue(list.get(i).getHanhKiem());

                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue(list.get(i).getHocKi());

                cell = row.createCell(10, CellType.STRING);
                cell.setCellValue(list.get(i).getGhiChu());

            }

            File f = new File("D://quanlydiemsinhvien.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);
                workbook.write(fis);
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void intongketsv() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("quanlydiemtongket");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(0);
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Bảng tổng kết điểm sinh viên");
            row = sheet.createRow(3);
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Số thứ tự");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Mã sinh viên");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Họ tên");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Điểm tổng kết");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Hạnh kiểm");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Học kì");

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Học lực");

            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow(4 + i);
                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(i + 1);

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(list.get(i).getMaSV());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(list.get(i).getHoTen());

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(list.get(i).getDiemTongKet());

                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(list.get(i).getHanhKiem());

                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(list.get(i).getHocKi());

                cell = row.createCell(8, CellType.STRING);
                if (list.get(i).getDiemTongKet() >= 8 && list.get(i).getDiemTongKet() <= 10
                        && list.get(i).getHanhKiem().equalsIgnoreCase("Tốt")) {
                    cell.setCellValue("Giỏi");

                }
                if (list.get(i).getDiemTongKet() >= 8 && list.get(i).getDiemTongKet() <= 10
                        && list.get(i).getHanhKiem().equalsIgnoreCase("Khá")) {
                    cell.setCellValue("Khá");

                }
                if (list.get(i).getDiemTongKet() >= 8 && list.get(i).getDiemTongKet() <= 10
                        && list.get(i).getHanhKiem().equalsIgnoreCase("Trung bình")) {
                    cell.setCellValue("Trung bình");

                }
                if (list.get(i).getDiemTongKet() >= 6.5 && list.get(i).getDiemTongKet() < 8
                        && (list.get(i).getHanhKiem().equalsIgnoreCase("Tốt")
                        || list.get(i).getHanhKiem().equalsIgnoreCase("Khá"))) {
                    cell.setCellValue("Khá");

                }
                if (list.get(i).getDiemTongKet() >= 5 && list.get(i).getDiemTongKet() < 6.5
                        && (list.get(i).getHanhKiem().equalsIgnoreCase("Tốt")
                        || list.get(i).getHanhKiem().equalsIgnoreCase("Khá"))) {
                    cell.setCellValue("Trung bình");

                }
                if (list.get(i).getDiemTongKet() >= 5 && list.get(i).getDiemTongKet() < 6.5
                        && list.get(i).getHanhKiem().equalsIgnoreCase("Trung bình")) {
                    cell.setCellValue("Yếu");

                }
                if (list.get(i).getDiemTongKet() >= 6.5 && list.get(i).getDiemTongKet() < 8
                        && list.get(i).getHanhKiem().equalsIgnoreCase("Trung bình")) {
                    cell.setCellValue("Trung bình");

                }
                if (list.get(i).getDiemTongKet() < 5 
                        && (list.get(i).getHanhKiem().equalsIgnoreCase("Tốt") 
                        || list.get(i).getHanhKiem().equalsIgnoreCase("Khá") 
                        || list.get(i).getHanhKiem().equalsIgnoreCase("Trung bình") 
                        || list.get(i).getHanhKiem().equalsIgnoreCase("Yếu"))) {
                    cell.setCellValue("Yếu");           

                }
//                if (list.get(i).getDiemTongKet() < 5 && list.get(i).getHanhKiem().equalsIgnoreCase("Tốt")) {
//                    cell.setCellValue("Yếu");
//                }
//                if (list.get(i).getDiemTongKet() < 5 && list.get(i).getHanhKiem().equalsIgnoreCase("Khá")) {
//                    cell.setCellValue("Yếu");
//                }
//                if (list.get(i).getDiemTongKet() < 5 && list.get(i).getHanhKiem().equalsIgnoreCase("Trung bình")) {
//                    cell.setCellValue("Yếu");
//                }
//                if (list.get(i).getDiemTongKet() < 5 && list.get(i).getHanhKiem().equalsIgnoreCase("Yếu")) {
//                    cell.setCellValue("Yếu");
//                }
            }

            File f = new File("D://thongketk.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);
                workbook.write(fis);
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
