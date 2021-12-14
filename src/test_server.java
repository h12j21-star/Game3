
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class test_server extends Thread {
    protected Socket sock;
    private static ArrayList<Socket> clients = new ArrayList<Socket>(10);// ���� ����Ʈ

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
      System.out.println(sock + ": �����");

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
      System.out.println(sock + ": ����(" + ex + ")");
     } finally {
      try {
       if (sock != null) {
        sock.close();
        // ���� �� �������� Ŭ���̾�Ʈ�� ��� ArrayList���� ����
        remove(sock);
       }
       fromClient = null;
       toClient = null;
      } catch (IOException ex) {
      }
     }
    }
    public static void main(String[] args) throws IOException {
        ServerSocket serverSock = new ServerSocket(9999); // 9999�� ��Ʈ�� ��������
        System.out.println(serverSock + ": �������ϻ���");
        while (true) {
         Socket client = serverSock.accept();         
         // ������ Ŭ���̾�Ʈ ���� Socket�� ArrayList�� ����
         clients.add(client);
         
         //---------------------------- Thread Start
         // ������� run �޼ҵ忡�� accept�� ������� Socket�� �̿��Ͽ� 
         // InputStream, OutputStream�� �̿��Ͽ� ���� �аų� ����.
         // -----------------------------------------------
         test_server myServer = new test_server(client);
         myServer.start();
        }
       }
      }
