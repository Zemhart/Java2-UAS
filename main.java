import sun.plugin2.os.windows.FLASHWINFO;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

class login extends JFrame {
    login (){
        setTitle("Login");
        setSize(450,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1,0,0));

        Image iMain = new ImageIcon(this.getClass().getResource("Main.png")).getImage().getScaledInstance(getWidth(),getHeight()/2 - 20, Image.SCALE_DEFAULT);
        ImageIcon mainImage = new ImageIcon(iMain);

        JLabel lblImgMain = new JLabel("");
        JLabel lblUsername = new JLabel("Username");
        JLabel lblPassword = new JLabel("Password");
        JTextField tUsername = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JLabel lblCR = new JLabel("Dec 2016");

        //JPanel kosong = new JPanel();

        JButton btnLogin = new JButton("LOGIN");
        JButton btnRegis = new JButton("REGISTRASI");
        lblImgMain.setIcon(mainImage);


        JPanel panelAtas = new JPanel();
        panelAtas.setLayout(new BorderLayout());
        JPanel panelBawah = new JPanel();
        panelBawah.setLayout(new BorderLayout());

        JPanel blank1 = new JPanel(); //PANEL KOSONG ATAS
        FlowLayout fblank1 = (FlowLayout) blank1.getLayout();
        fblank1.setVgap(30);

        JPanel blank2 = new JPanel(); //PANEL KOSONG KIRI
        FlowLayout fblank2 = (FlowLayout) blank2.getLayout();
        fblank2.setHgap(20);

        JPanel blank3 = new JPanel(); //PANEL KOSONG KANAN
        FlowLayout fblank3 = (FlowLayout) blank3.getLayout();
        fblank3.setHgap(20);

        JPanel cr = new JPanel(); //PANEL CR BAWAH

        JPanel info = new JPanel(); //PANEL TENGAH
        info.setLayout(new GridLayout(3,2,30,40)); //hgap = 30, vgap = 50
        cr.setLayout(new FlowLayout(FlowLayout.RIGHT));

        info.add(lblUsername);
        info.add(tUsername);
        info.add(lblPassword);
        info.add(passwordField);
        info.add(btnRegis);
        info.add(btnLogin);

        panelAtas.add(lblImgMain);

        panelBawah.add(blank1,BorderLayout.NORTH);
        panelBawah.add(blank2,BorderLayout.WEST);
        panelBawah.add(blank3,BorderLayout.EAST);
        panelBawah.add(cr,BorderLayout.SOUTH);
        panelBawah.add(info, BorderLayout.CENTER);

        cr.add(lblCR);

        add(panelAtas);
        add(panelBawah);
        btnRegis.addMouseListener((new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                new Registrasi();
            }
        }));
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                String usr = tUsername.getText();
                String pwd = String.valueOf(passwordField.getPassword());
                boolean f = false;
                int mny = 0;
                try{
                    TestClient t = new TestClient(usr, pwd, "login");
                    t.getData();
                    if(t.getFlg()){
                        f = true;
                        mny = t.getMny();
                    }
                }catch (IOException z){
                    z.printStackTrace();
                }
                if (usr.equals("admin") && pwd.equals("1234") || f){
                    JOptionPane.showMessageDialog(null, "Login Success!");
                    hide();
                    try {
                        new main(mny, usr);
                    }
                    catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Username or password wrong, Login failed.");
                }
            }
        });

        setVisible(true);
    }

}

public class main extends JFrame{
    static int roll (){
        Random x = new Random();
        int r = 0;

        //RNG dari system project ini

        r += x.nextInt()%301;
        r += x.nextInt();
        r = Math.abs(r);

        //50 kemungkinan
        r %= 50;
        r += 1;

        //interval 1~50
        // 42 % (1-21)
        if (r>0 && r<=21){
            return 1;
        }
        // 30 % (22-36)
        else if (r>21 && r<=36){
            return 2;
        }
        // 20 % (37-46)
        else if (r>36 && r<=46){
            return 3;
        }
        // 6 % (47-49)
        else if (r>46 && r<=49){
            return 4;
        }
        // 2 % (only 50)
        else if (r>49){
            return 5;
        }

        return 3;
    }

    static String showStatistics(Vector<Integer> s){
        int a = 0,b = 0,c = 0,d = 0,e = 0;
        String str = "";
        for (int i=0;i<s.size();i++){

            if (s.get(i)==1){
                a++;
            }
            else if (s.get(i) ==2){
                b++;
            }
            else if (s.get(i)==3){
                c++;
            }
            else if (s.get(i)==4){
                d++;
            }
            else{
                e++;
            }
        }
        str += ("1* = "+a+" --> "+a*100.0/s.size()+"%"+"\n");
        str += ("2* = "+b+" --> "+b*100.0/s.size()+"%"+"\n");
        str += ("3* = "+c+" --> "+c*100.0/s.size()+"%"+"\n");
        str += ("4* = "+d+" --> "+d*100.0/s.size()+"%"+"\n");
        str += ("5* = "+e+" --> "+e*100.0/s.size()+"%"+"\n");

        return str;
    }

    main (int mny, String usr) throws InterruptedException {
        String[] pok1 = {"1* ZUBAT","1* MAGIKARP","1* SUNKERN", "1* CATERPIE"};
        String[] pok2 = {"2* MARILL","2* UNOWN","2* POOCHEYENA", "2* SANDSHREW"};
        String[] pok3 = {"3* PIKACHU","3* ONIX","3* CHIKORITA", "3* SHUCKLE"};
        String[] pok4 = {"4* MACHOP","4* GENGAR","4* PIPLUP", "4* CHARMANDER"};
        String[] pok5 = {"5* ZAPDOS","5* MEWTWO","5* LUCARIO", "5* ARCEUS"};


        Vector<Integer> s = new Vector<>();
        for (int i=0;i<100000;i++) s.add(roll());

        setTitle("Pokemon Gacha v0.1 BETA");
        setSize(450,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1,0,0));


        //warna utama
        Color warna = new Color(253, 255, 132); // kuning
        Color warna2 = new Color(197, 216,222); // biru

        JPanel panelAtas = new JPanel();
        panelAtas.setBackground(warna);

        JPanel panelBawah = new JPanel();
        panelBawah.setLayout(new BorderLayout());

        JPanel cash = new JPanel();
        cash.setLayout(new FlowLayout(FlowLayout.RIGHT));
        FlowLayout fcash = (FlowLayout) cash.getLayout();
        fcash.setVgap(35);
        cash.setBackground(warna2);

        JPanel blank1 = new JPanel();
        FlowLayout fblank1 = (FlowLayout) blank1.getLayout();
        fblank1.setHgap(40);
        blank1.setBackground(warna2);

        JPanel blank2 = new JPanel();
        FlowLayout fblank2 = (FlowLayout) blank2.getLayout();
        fblank2.setHgap(40);
        blank2.setBackground(warna2);

        JPanel result = new JPanel();
        FlowLayout fres = (FlowLayout) result.getLayout();
        fres.setVgap(35);
        result.setBackground(warna2);

        JLabel img = new JLabel("");

        JLabel logoCash = new JLabel("Cash");
        JLabel cashRemain = new JLabel(String.valueOf(mny));

        Image def = new ImageIcon(this.getClass().getResource("-1.png")).getImage().getScaledInstance(220,220, Image.SCALE_DEFAULT);
        Image iCash = new ImageIcon(this.getClass().getResource("cash.jpg")).getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        Image[][] poke = new Image[6][5];

        poke[0][0] = new ImageIcon(this.getClass().getResource("loading.gif")).getImage().getScaledInstance(220,220, Image.SCALE_DEFAULT);
        for (int i=1;i<=5;i++){
            for (int j=1;j<=4;j++){
                String nama_file = i+"."+j+".png";
                poke[i][j] = new ImageIcon(this.getClass().getResource(nama_file)).getImage().getScaledInstance(220,220,Image.SCALE_DEFAULT);
            }
        }

        ImageIcon def1 = new ImageIcon(def);
        ImageIcon gambarCash = new ImageIcon(iCash);

        ImageIcon[][] pokemon = new ImageIcon[6][5];
        pokemon[0][0]  = new ImageIcon(poke[0][0]);
        for (int i=1;i<=5;i++){
            for (int j=1;j<=4;j++){
                pokemon[i][j] = new ImageIcon(poke[i][j]);
            }
        }

        img.setIcon(def1);
        logoCash.setIcon(gambarCash);

        JLabel hasil = new JLabel("");
        hasil.setForeground(Color.red);
        hasil.setFont(new Font("Tahoma", Font.BOLD, 16));

        JButton btnBuy = new JButton("Buy 1\t Cost : \"Cash 300\"");
        btnBuy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int c = Integer.parseInt(cashRemain.getText());
                if (c >= 300){
                    int x =  JOptionPane.showConfirmDialog(null, "Summon will be performed.","CONFIRMATION",JOptionPane.YES_NO_OPTION);
                    //0 yes, 1 no
                    if (x == 0) {
                        c -= 300;
                        /*try{
                            DbGacha db = new DbGacha();
                            db.updMny(pwd, c);
                        }catch (Exception z){
                            z.printStackTrace();
                        }*/
                        try{
                            TestClient t = new TestClient(usr, c, "gch");
                            t.getData();
                        }catch (Exception z){
                            z.printStackTrace();
                        }
                        cashRemain.setText(String.valueOf(c));

                        img.setIcon(pokemon[0][0]); //loading . . .
                        JOptionPane.showMessageDialog(null, "loading");
                        int rn = roll();
                        int rnn = 0;

                        Random rr = new Random();
                        rnn = Math.abs(rr.nextInt());
                        rnn %= 4;
                        rnn += 1;
                        img.setIcon(pokemon[rn][rnn]);
                        if (rn == 1){
                            hasil.setText("You got "+pok1[rnn-1]);
                        }
                        else if (rn == 2){
                            hasil.setText("You got "+pok2[rnn-1]);
                        }
                        else if (rn == 3){
                            hasil.setText("You got "+pok3[rnn-1]);
                        }
                        else if (rn == 4){
                            hasil.setText("You got "+pok4[rnn-1]);
                        }
                        else {
                            hasil.setText("You got "+pok5[rnn-1]);
                        }
                    }

                }
                else {
                    JOptionPane.showMessageDialog(null,"Failed.. Your cash is not enough");
                }
            }
        });

        cash.add(logoCash);
        cash.add(cashRemain);
        result.add(hasil);

        panelAtas.add(img);

        panelBawah.add(cash,BorderLayout.NORTH);
        panelBawah.add(btnBuy,BorderLayout.CENTER);
        panelBawah.add(blank1,BorderLayout.WEST);
        panelBawah.add(blank2,BorderLayout.EAST);
        panelBawah.add(result,BorderLayout.SOUTH);

        add(panelAtas);
        add(panelBawah);


        setVisible(true);
    }


    public static void main (String[] args){
        try {
            new login();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}