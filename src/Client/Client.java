package Client;

import Util.Util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    final int PORT = 3000;
    final String ADDRESS = "localhost";

    Socket socket;

    DataInputStream inputStream;
    DataOutputStream outputStream;

    boolean start = true;

    Client() throws IOException {
        socket = new Socket(ADDRESS,PORT);

        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void sendMessage(String message) throws IOException {
        outputStream.writeUTF(message);
    }

    public String receiveMessage() throws IOException {
        return inputStream.readUTF();
    }

    public void login() throws IOException{
        String result = "NG";
        while (result.equals("NG")){
            System.out.println("Please Login");
            String userName = (String) Util.getFromInput("string","username: ");
            String password = (String) Util.getFromInput("string","password: ");

            sendMessage(userName);
            sendMessage(password);
            String message = receiveMessage();
            result = receiveMessage();

            System.out.println("message: " + message);
            System.out.println("result: " + result);
        }


    }

    public void startTest() throws IOException{
        while (true){
            String question = receiveMessage();
            Util.log_success(question);
            String choice = (String) Util.getFromInput("string","select answer: ");
            sendMessage(choice);

            String message = receiveMessage();
            if(!message.equals("continue")){
                System.out.println(message);
                break;
            }
        }
    }

    public void start() throws IOException{
        login();
        while (start){
            String menu = receiveMessage();
            Util.log_success(menu);
            String choice = (String) Util.getFromInput("string","select: ");
            sendMessage(choice);

            if(choice.equals("1")){
                startTest();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();
    }
}
