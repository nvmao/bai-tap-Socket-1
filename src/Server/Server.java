package Server;

import Util.Util;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    final int PORT = 3000;
    ServerSocket serverSocket;
    Socket socket;

    DataInputStream inputStream;
    DataOutputStream outputStream;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;


    UserService userService = new UserService();
    User user;
    boolean start = true;

    Server() throws IOException {
        System.out.println("Waiting for connection...");

        serverSocket = new ServerSocket(PORT);
        socket = serverSocket.accept();

        System.out.println("client connected: " + socket);

        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void sendMessage(String message) throws IOException {
        outputStream.writeUTF(message);
    }

    public String receiveMessage() throws IOException {
        return inputStream.readUTF();
    }

    public void sendObject(Object object) throws IOException{
        objectOutputStream.writeObject(object);
    }
    public  Object receiveObject() throws Exception{
        return objectInputStream.readObject();
    }

    public void login() throws IOException{
        String userName = receiveMessage();
        String password = receiveMessage();

        user = userService.Login(userName,password);
        if(user == null){
            sendMessage("login error");
            sendMessage("NG");
            return;
        }
        sendMessage("login success. Hello " + userName + " !");
        sendMessage("OK");
    }
    public void startTest() throws IOException{
        System.out.println("startTest");
        QuestionService questionService = new QuestionService();
        ArrayList<Question> questions = (ArrayList<Question>) questionService.getQuestions();

        if(questions == null || questions.size() == 0){
            return;
        }

        int currentQuestion = 0;
        float point = 0;
        float pointPercent = questions.size() / 10.0f;
        while (currentQuestion < questions.size()){

            String question = String.format("Question %s. : %s \n" +
                            "A. %s \n" +
                            "B. %s \n" +
                            "C. %s \n" +
                            "D. %s \n"
                    ,currentQuestion+1,
                    questions.get(currentQuestion).content,
                    questions.get(currentQuestion).abcd[0],
                    questions.get(currentQuestion).abcd[1],
                    questions.get(currentQuestion).abcd[2],
                    questions.get(currentQuestion).abcd[3]);

            sendMessage(question);
            String answer = receiveMessage();
            if(answer.toLowerCase().equals(questions.get(currentQuestion).answer.toLowerCase())){
                point += pointPercent;
            }
            currentQuestion++;

            if(currentQuestion == questions.size())
            {
                PointService pointService = new PointService();
                boolean result = pointService.savePoint(user.getId(),point);

                if(result){
                    sendMessage("point: " + point);
                }
                else{
                    sendMessage("point: " +point +" . Student already test, point will not save ");
                }
            }
            else{
                sendMessage("continue");
            }
        }

    }

    public void start() throws IOException{
        while (user == null){
            login();
        }
        while (start){
            String menu =   "-----------------------------\n"+
                            "1. Let go challenge !!\n" +
                            "2. Top and Dashboard !!\n" +
                            "3. Exit" +
                             "-----------------------------\n";
            sendMessage(menu);
            String choice = receiveMessage();

            if(choice.equals("1")){
                startTest();
            }

        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }
}
