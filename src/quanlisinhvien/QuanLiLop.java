/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package quanlisinhvien;

import DAO.khoaDAO;
import DAO.lopDAO;
import Entity.Khoa;
import Entity.Lop;
//<<<<<<< HEAD
import Utils.DataValidation;
//=======
import Utils.Auth;
//>>>>>>> 5dd1cc1ef012ca9723a8ec8cbcba062fbff54607
import Utils.MsgBox;
import Utils.jdbchelper;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/** 
 *
 * @author akbro
 */
public class QuanLiLop extends javax.swing.JFrame {

    /**
     * Creates new form QuanLiLop
     */
    // xu ly quyen truy cap
//    public void accessRight() {
//        if (!Auth.isManager().equalsIgnoreCase("Admin")) {
//            btnAdd.setVisible(false);
//            btnUpdate.setVisible(false);
//            btnDel.setVisible(false);
//        }
//    }
    public QuanLiLop() {
        initComponents();
        init();
    }
    int row = -1;

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
        tbllop = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtmalop = new javax.swing.JTextField();
        txttenlop = new javax.swing.JTextField();
        txtmakhoa = new javax.swing.JTextField();
        btnthoat = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 32)); // NOI18N
        jLabel1.setText("QUẢN LÍ LỚP");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tbllop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã khoa", "Tên lớp", "Mã lớp"
            }
        ));
        tbllop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbllopMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbllopMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbllop);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("Mã lớp:");

        jLabel3.setText("Tên lớp:");

        jLabel4.setText("Mã khoa:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txttenlop, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(22, 22, 22)
                        .addComponent(txtmalop, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmakhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(txtmalop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmakhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txttenlop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        btnthoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Exit.png"))); // NOI18N
        btnthoat.setText("Thoát");
        btnthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthoatActionPerformed(evt);
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

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Add.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(271, 271, 271))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnthoat))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addGap(18, 18, 18)
                                .addComponent(btnDel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnDel))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdate)
                            .addComponent(btnthoat))
                        .addGap(61, 61, 61)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbllopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllopMouseClicked
        // TODO add your handling code here:
        current = tbllop.getSelectedRow();
        this.Show();
    }//GEN-LAST:event_tbllopMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        this.insert();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        this.Update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthoatActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnthoatActionPerformed

    private void tbllopMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllopMousePressed
        // TODO add your handling code here:
//                if (evt.getClickCount() == 2) {
//            this.row = tbllop.getSelectedRow();
//            this.edit();
//        }
//       
    }//GEN-LAST:event_tbllopMousePressed

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
            java.util.logging.Logger.getLogger(QuanLiLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLiLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLiLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLiLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLiLop().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnthoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbllop;
    private javax.swing.JTextField txtmakhoa;
    private javax.swing.JTextField txtmalop;
    private javax.swing.JTextField txttenlop;
    // End of variables declaration//GEN-END:variables

    void init() {
        this.setLocationRelativeTo(null);
        this.Load();
//        accessRight();
    }

    lopDAO lopdao = new lopDAO();
    khoaDAO khoadao = new khoaDAO();

    private void Load() {
        DefaultTableModel model = (DefaultTableModel) tbllop.getModel();
        model.setRowCount(0);
        try {
            List<Lop> list = lopdao.selectAll();
            for (Lop lopp : list) {
                Object[] row = {
                    lopp.getMaKhoa(),
                    lopp.getTenLop(),
                    lopp.getMaLop()};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void insert() {
        StringBuilder sb = new StringBuilder();
        DataValidation.validateEmp(txtmalop, sb, "Bạn chưa nhập mã lớp");
        DataValidation.validateEmp(txtmakhoa, sb, "Bạn chưa nhập mã khoa");
        DataValidation.validateEmp(txttenlop, sb, "Bạn chưa nhập Tên lớp");
        if(sb.length() > 0){
            MsgBox.showErrorDialog(null, sb.toString());
            return;
        }
        
        Lop model = getForm();
        try {
            lopdao.insert(model);
            this.Load();

            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại!");
        }
    }

    void Update() {
        StringBuilder sb = new StringBuilder();
        DataValidation.validateEmp(txtmalop, sb, "Bạn chưa nhập mã lớp");
        DataValidation.validateEmp(txtmakhoa, sb, "Bạn chưa nhập mã khoa");
        DataValidation.validateEmp(txttenlop, sb, "Bạn chưa nhập Tên lớp");
        if(sb.length() > 0){
            MsgBox.showErrorDialog(null, sb.toString());
            return;
        }
        Lop model = getForm();
        try {
            lopdao.update(model);
            this.Load();

            MsgBox.alert(this, "Update thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Update thất bại!");
        }

    }

    void delete() {
        StringBuilder sb = new StringBuilder();
        DataValidation.validateEmp(txtmalop, sb, "Vui lòng nhập mã lớp muốn xóa");
        if(sb.length() > 0){
            MsgBox.showErrorDialog(null, sb.toString());
            return;
        }
        if (MsgBox.confirm(this, "Bạn thực sự muốn xóa người học này?")) {
            String makhoa = txtmalop.getText();
            try {
                lopdao.delete(makhoa);
                this.Load();

                MsgBox.alert(this, "Xóa thành công!");
            } catch (HeadlessException e) {
                MsgBox.alert(this, "Xóa thất bại!");
            }
        }
    }
    int current = 0;

    public void Show() {
        txtmakhoa.setText(tbllop.getValueAt(current, 0).toString());
        txttenlop.setText(tbllop.getValueAt(current, 1).toString());
        txtmalop.setText(tbllop.getValueAt(current, 2).toString());
    }

    Lop getForm() {
        Lop kh = new Lop();
        kh.setMaKhoa(txtmakhoa.getText());
        kh.setMaLop(txtmalop.getText());
        kh.setTenLop(txttenlop.getText());
        return kh;
    }


}
