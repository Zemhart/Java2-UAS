import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    private static final int PORT = 9090;

    public static void main(String[] args) throws IOException {
        System.out.println(
                "Server berjalan di port: " + PORT
        );
        ServerSocket listener = new ServerSocket(PORT);
        try{
            while(true){
                Socket s = listener.accept();
                Thread t = new Regis(s);
                t.start();
            }
        } finally {
            listener.close();
        }
    }
}

class Regis extends Thread{
    private Socket socket;
    private String client;

    Regis(Socket s){
        socket = s;
        client = s.getRemoteSocketAddress().toString();
        System.out.println("Koneksi dari: " + client);
    }

    public void run(){
        try{
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true
            );

            System.out.println("Selamat datang");
            String sts = in.readLine();
            String usr = in.readLine();
            String pwd;
            int mny;
            DbGacha db = new DbGacha();
            if(sts.equals("login")) {
                pwd = in.readLine();
                boolean f = db.getUsr(usr, pwd);
                System.out.println(f);
                int m = db.getMny(usr);
                System.out.println(m);
                out.println(f);
                out.println(m);
            }else if(sts.equals("gch")){
                mny = Integer.parseInt(in.readLine());
                db.updMny(usr, mny);
            }
            else if(sts.equals("daftar")){
                pwd = in.readLine();
                db.insertUsr(usr, pwd);
            }else if(sts.equals("chk")){
                boolean f = db.chkUsr(usr);
                out.println(f);
            }
            System.out.println("Sukses " + sts );
        }catch (Exception e){
            System.out.println("Error " + client + ": " + e);
        }
        finally {
            try {
                socket.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("Close connection from " + client);
        }
    }
}