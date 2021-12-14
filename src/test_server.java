
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class test_server extends Thread {
    protected Socket sock;
    private static ArrayList<Socket> clients = new ArrayList<Socket>(10);// 소켓 리스트

    test_server(Socket sock) {
     this.sock = sock;
    }
    public void remove(Socket socket) {
     for (Socket s : test_server.clients) {
      if (socket == s) {
       test_server.clients.remove(socket);
       break;
      }
     }
    }
    public void run() {
     InputStream fromClient = null;
     OutputStream toClient = null;

     try {
      System.out.println(sock + ": 연결됨");

      fromClient = sock.getInputStream();

      byte[] buf = new byte[1024];
      int count;
      while ((count = fromClient.read(buf)) != -1) {
       for (Socket s : test_server.clients) {
        
         toClient = s.getOutputStream();
         toClient.write(("300\n").getBytes());
          toClient.flush();
       
       }
       System.out.write(buf, 0, count);
       
      }
     } catch (IOException ex) {
      System.out.println(sock + ": 에러(" + ex + ")");
     } finally {
      try {
       if (sock != null) {
        sock.close();
        // 접속 후 나가버린 클라이언트인 경우 ArrayList에서 제거
        remove(sock);
       }
       fromClient = null;
       toClient = null;
      } catch (IOException ex) {
      }
     }
    }
    public static void main(String[] args) throws IOException {
        ServerSocket serverSock = new ServerSocket(9999); // 9999번 포트에 서버생성
        System.out.println(serverSock + ": 서버소켓생성");
        while (true) {
         Socket client = serverSock.accept();         
         // 접속한 클라이언트 상대용 Socket을 ArrayList에 저장
         clients.add(client);
         
         //---------------------------- Thread Start
         // 쓰레드는 run 메소드에서 accept로 만들어진 Socket을 이용하여 
         // InputStream, OutputStream을 이용하여 글을 읽거나 쓴다.
         // -----------------------------------------------
         test_server myServer = new test_server(client);
         myServer.start();
        }
       }
      }
