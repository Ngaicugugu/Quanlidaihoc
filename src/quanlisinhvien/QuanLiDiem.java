/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package quanlisinhvien;

import DAO.KetQuaDAO;
import DAO.ThongKeDAO;
import DAO.khoaDAO;
import DAO.lopDAO;
import DAO.monDAO;
import Entity.KetQua;
import Entity.Khoa;
import Entity.Lop;
import Entity.Mon;
import Utils.Auth;
import Utils.MsgBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.record.PageBreakRecord;

/**
 *
 * @author akbro
 */
public class QuanLiDiem extends javax.swing.JFrame {

    /**
     * Creates new form QuanLiDiem
     */
    List<Khoa> dsKhoa;
    List<Lop> dsLop;
    List<Mon> dsMon;
    List<KetQua> dsKQ;
    Lop L;
    Mon m;
    // xu ly quyen truy cap
    public void accessRight() {
        if (Auth.isManager().equalsIgnoreCase("Student")) {
            btnAdd.setVisible(false);
            btnUpdate.setVisible(false);
            btnDel.setVisible(false);
        }
        if (Auth.isManager().equalsIgnoreCase("Teacher")) {
            btnDel.setVisible(false);
        }
    }
    /* load combobox */
    // load khoa
    public void fillComboBoxKhoa(){
        cboKhoa.removeAllItems();
        khoaDAO kDAO = new khoaDAO();
        
        dsKhoa= kDAO.selectAll();
        for(Khoa k : dsKhoa){
            cboKhoa.addItem(k.getMaKhoa() + "(" + k.getTenKhoa() + ")");
            L = new Lop();
            m = new Mon();
            L.setMaKhoa(k.getMaKhoa());
            m.setMaKhoa(k.getMaKhoa());
//            System.out.println(L.getMaKhoa());
        }
        this.fillComboBoxLop();
        this.fillComboBoxMon();
    }
    // load lop
    public void fillComboBoxLop(){
       cboLop.removeAllItems();
       lopDAO lDAO = new lopDAO();
       
       int Index = cboKhoa.getSelectedIndex();
       if(Index >=0){
           dsLop = lDAO.selectByKhoa(dsKhoa.get(Index).getMaKhoa());
           for(Lop L: dsLop){
               cboLop.addItem(L.getMaLop() + "(" + L.getTenLop() + ")");       
           }
       }
    }
    // load mon hoc
    public void fillComboBoxMon(){
        cboMon.removeAllItems();
        monDAO mDAO = new monDAO();

        int Index = cboKhoa.getSelectedIndex();
        if(Index >=0){
            dsMon = mDAO.selectByKhoa(dsKhoa.get(Index).getMaKhoa());
            for(Mon m: dsMon){
                cboMon.addItem(m.getMaMon() + "(" + m.getTenMon() + ")");
                System.out.println(m.getMaMon());
            }
            
        }
        loadDataToBangDiemSV();
    }
    //loadData len table
    public void loadDataToBangDiemSV(){
        KetQua kq = new KetQua();
        KetQuaDAO kqDAO = new KetQuaDAO();
        lopDAO lDAO = new lopDAO();
        
        DefaultTableModel model = (DefaultTableModel) tblDiemSinhVien.getModel();
        
        
        int Index = cboMon.getSelectedIndex();
        if(Index >=0){
            model.setRowCount(0);
            m = dsMon.get(Index);
            dsKQ = kqDAO.selectByMaMon(m.getMaMon());
            
            for(int i =0; i<dsKQ.size() ;i++){
                kq = dsKQ.get(i);
                
//                k = kDAO.selectById(m.getMaKhoa());
                dsLop = lDAO.selectByKhoa(m.getMaKhoa());
//                for(Lop L:dsLop){
                      model.addRow(new Object[]{
                        kq.getMaSV(),kq.getHoTen(),L.getMaLop(),kq.getMaMon(),kq.getDiemTB(),kq.getDiemTongKet()
                    });
//                }   
            }
        }
        model.fireTableDataChanged();
        
    }
    //xu ly nut add
    public void addDiem(){
        KetQua kq = getForm();
        KetQuaDAO kqDAO = new KetQuaDAO();
        try {
            kqDAO.insert(kq);
            this.loadDataToBangDiemSV();
            clearForm();
            MsgBox.alert(null, "Thêm Thành Công");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại!");
        }
    }
    //xu ly nut update
    public void updateDiem(){
        KetQua kq = getForm();
        KetQuaDAO kqDAO = new KetQuaDAO();
        try {
            kqDAO.insert(kq);
            MsgBox.alert(null, "Sửa Thành Công");
            clearForm();
            this.loadDataToBangDiemSV();
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại!");
        }
    }
    // xu ly nut del
    public void deleteDiem(){
        String maSV = txtMaSV.getText().trim();
        KetQuaDAO kqDAO = new KetQuaDAO();
        try {
           kqDAO.delete(maSV);
           this.loadDataToBangDiemSV();
           clearForm();
           MsgBox.alert(this, "Xóa thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Xóa thất bại!");
        }
    }
    // xu ly su kien click bang
    public void clickDataToTable(){
        try{
            int Row = tblDiemSinhVien.getSelectedRow();
            if(Row >=0){
                String id = (String) tblDiemSinhVien.getValueAt(Row, 0);
                KetQuaDAO kqDAO = new KetQuaDAO();
                KetQua kq = kqDAO.selectById(id);
                setForm(kq);
            }
        }catch(Exception E){
            E.printStackTrace();
            System.out.println(E.getMessage());
        }
    }
    // clear form
    public void clearForm(){
        txtMaSV.setText("");
        txtHoTen.setText("");
        txtDiemTB.setText("");
        txtDiemTongKet.setText("");
        txtGhiChu.setText("");
        cboHanhKiem.setSelectedItem(null);
    }
    
    KetQua getForm(){
        KetQua kq = new KetQua();
        try {
            // xu ly chuoi
            String maMon1 = (String) cboMon.getSelectedItem();
            int I = maMon1.indexOf("(");
            String maMon = maMon1.substring(0, I);
            
            kq.setMaSV(txtMaSV.getText().trim());
            kq.setHoTen(txtHoTen.getText().trim());
            kq.setDiemTB(Float.parseFloat(txtDiemTB.getText().trim()));
            kq.setDiemTongKet(Float.parseFloat(txtDiemTongKet.getText().trim()));
            kq.setMaMon(maMon);
            kq.setGhiChu(txtGhiChu.getText().trim());
            kq.setHanhKiem((String) cboHanhKiem.getSelectedItem());
        } catch (Exception e) {
        }
        return kq;
    }
    
    public void setForm(KetQua kq){
        txtMaSV.setText(kq.getMaSV());
        txtHoTen.setText(kq.getHoTen());
        txtDiemTB.setText(kq.getDiemTB() + "");
        txtDiemTongKet.setText(kq.getDiemTongKet() + "");
        txtGhiChu.setText(kq.getGhiChu());
    }
    
    public QuanLiDiem() {
        initComponents();
        accessRight();
        fillComboBoxKhoa();
//        fillComboBoxLop();
//        cboKhoa.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                fillComboBoxKhoa();
//            }
//        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDiemSinhVien = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboKhoa = new javax.swing.JComboBox<>();
        cboMon = new javax.swing.JComboBox<>();
        cboLop = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMaSV = new javax.swing.JTextField();
        txtDiemTB = new javax.swing.JTextField();
        cboHanhKiem = new javax.swing.JComboBox<>();
        txtHoTen = new javax.swing.JTextField();
        txtDiemTongKet = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txtGhiChu = new javax.swing.JTextField();
        btnExit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("QUẢN LÍ ĐIỂM SINH VIÊN");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách"));

        tblDiemSinhVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã sinh viên", "Họ và tên", "Mã lớp", "Mã môn", "Điểm trung bình", "Điểm tổng kết"
            }
        ));
        tblDiemSinhVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDiemSinhVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDiemSinhVien);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lựa chọn"));

        jLabel2.setText("Khoa:");

        jLabel3.setText("Lớp:");

        jLabel4.setText("Môn học:");

        cboKhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKhoaActionPerformed(evt);
            }
        });

        cboMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboMon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboKhoa, 0, 249, Short.MAX_VALUE)
                            .addComponent(cboLop, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết"));

        jLabel6.setText("Mã SV:");

        jLabel7.setText("Điểm TB:");

        jLabel8.setText("Họ tên:");

        jLabel9.setText("Điểm tổng kết:");

        jLabel10.setText("Hạnh kiểm:");

        cboHanhKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tốt", "Khá", "Trung Bình", "Yếu" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaSV)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDiemTB)
                            .addComponent(cboHanhKiem, 0, 152, Short.MAX_VALUE))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiemTongKet, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(46, 46, 46)
                        .addComponent(txtHoTen)))
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(txtDiemTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiemTongKet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboHanhKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(47, 47, 47))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Ghi chú"));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtGhiChu)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtGhiChu)
                .addContainerGap())
        );

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Exit.png"))); // NOI18N
        btnExit.setText("Thoát");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Add.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Edit.png"))); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Delete.png"))); // NOI18N
        btnDel.setText("Xóa");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Card file.png"))); // NOI18N
        btnPrint.setText("In bảng điểm");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(183, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(182, 182, 182))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnDel)
                        .addGap(18, 18, 18)
                        .addComponent(btnExit)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrint))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        ThongKeDAO tkdao = new ThongKeDAO();
        tkdao.selectAll();
        while (true) {
            tkdao.inbangdiemsv();
            break;
            }
        JOptionPane.showMessageDialog(this, "In bảng điểm thành công");
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void cboKhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKhoaActionPerformed
        // TODO add your handling code here:
        fillComboBoxLop();
        fillComboBoxMon();
    }//GEN-LAST:event_cboKhoaActionPerformed

    private void cboMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMonActionPerformed
        // TODO add your handling code here:
        loadDataToBangDiemSV();
    }//GEN-LAST:event_cboMonActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        addDiem();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        updateDiem();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:
        deleteDiem();
    }//GEN-LAST:event_btnDelActionPerformed

    private void tblDiemSinhVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDiemSinhVienMouseClicked
        // TODO add your handling code here:
        clickDataToTable();
    }//GEN-LAST:event_tblDiemSinhVienMouseClicked

    /**
         * @param args the command line arguments
         */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLiDiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLiDiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLiDiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLiDiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLiDiem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboHanhKiem;
    private javax.swing.JComboBox<String> cboKhoa;
    private javax.swing.JComboBox<String> cboLop;
    private javax.swing.JComboBox<String> cboMon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDiemSinhVien;
    private javax.swing.JTextField txtDiemTB;
    private javax.swing.JTextField txtDiemTongKet;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaSV;
    // End of variables declaration//GEN-END:variables
}
