import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registrasi extends JFrame implements ActionListener {
    private JTextField txtUsr = new JTextField();
    private JPasswordField txtPwd = new JPasswordField();
    private JButton btnReg = new JButton("Submit");
    private JButton btnCnl = new JButton("Cancel");

    Registrasi(){
        JLabel labelUsr = new JLabel("User ID");
        JLabel labelPwd = new JLabel("Password");
        setTitle("Registrasi User Baru");
        setSize(450,350);
        setLocationRelativeTo(null);
        JPanel pnlUtama = new JPanel();
        pnlUtama.setLayout(new GridLayout(3,2,40,80));

        Border empty;
        empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        pnlUtama.setBorder(empty);
        pnlUtama.add(labelUsr);
        pnlUtama.add(txtUsr);
        pnlUtama.add(labelPwd);
        pnlUtama.add(txtPwd);
        pnlUtama.add(btnReg);
        pnlUtama.add(btnCnl);

        add(pnlUtama);
        btnReg.addActionListener(this);
        btnCnl.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnReg){
            String usr = txtUsr.getText();
            String pwd = String.valueOf(txtPwd.getPassword());
            try{
                TestClient t = new TestClient(usr, pwd, "chk");
                t.getData();
                if(!t.getFlg()){
                    t = new TestClient(usr, pwd, "daftar");
                    t.getData();
                    JOptionPane.showMessageDialog(null, "Berhasil registrasi!");
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "ID sudah pernah ada");
                }
            }catch (Exception z){
                System.out.println("Koneksi gagal");
            }
        }else if(e.getSource() == btnCnl){
            dispose();
        }
    }
}