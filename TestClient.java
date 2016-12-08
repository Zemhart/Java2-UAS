import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

class TestClient {
    private String usr, pwd, sts;
    int mny;
    private boolean flg = false;
    TestClient(String U, String P,String S) throws IOException{
        usr = U;
        pwd = P;
        sts = S;
    }
    TestClient(String U, int M, String s){
        usr = U;
        mny = M;
        sts = s;
    }

     void setFlg(boolean flg) {
        this.flg = flg;
    }
    boolean getFlg(){
        return flg;
    }
    void setMny(int mny){
        this.mny = mny;
    }
    int getMny(){
        return mny;
    }
    void getData() throws IOException {
        try (Socket s = new Socket("192.168.1.142", 9090)) {
            BufferedReader cin = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            s.getInputStream()
                    )
            );

            PrintWriter out = new PrintWriter(
                    s.getOutputStream(),
                    true
            );

            out.println(sts);
            out.println(usr);
            if(sts.equals("login")){
                out.println(pwd);
                boolean f = Boolean.parseBoolean(in.readLine());
                setFlg(f);
                int m = Integer.parseInt(in.readLine());
                setMny(m);
            }
            else if(sts.equals("gch")) out.println(mny);
            else if(sts.equals("daftar")) out.println(pwd);
            else if(sts.equals("chk")) {
                boolean f = Boolean.parseBoolean(in.readLine());
                setFlg(f);
            }

            System.out.println("Connection closed.");
        } catch (IOException e){

        }
        //return f;
    }
}