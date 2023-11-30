package parts.part2;

import java.net.*;
import java.io.*;
import java.util.Arrays;
import java.nio.ByteBuffer;

public class server {
    public final static int DEFAULT_PORT = 8001;//определение порта сервера
    public final String QUIT_CMD = "QUIT"; //определение команды «выход»

    public void runServer() throws IOException { // метод сервера runServer

        DatagramSocket s = null; // создание объекта DatagramSocket

        try {
            boolean stopFlag = false; // создание флага stopFlag и его инициализация значением false

            byte[] buf = new byte[512]; // буфер для приема/передачи дейтаграммы
            s = new DatagramSocket(DEFAULT_PORT); // привязка сокета к реальному объекту с портом DEFAULT_PORT

            System.out.println("UDPServer: сервер работает на " + s.getLocalAddress() + ":"
                    + s.getLocalPort()); // вывод к консоль сообщения

            Test test = new Test();
            double temp_answer = 1.0;
            // test.getValue();

            while(!stopFlag) { // цикл до тех пор, пока флаг не примет значение true

                DatagramPacket recvPacket = new DatagramPacket(buf, buf.length); // создание объекта дейтаграммы для получения данных

                s.receive(recvPacket); // помещение полученного содержимого в объект дейтаграммы
                String cmd = new String(recvPacket.getData()).trim(); // извлечение команды из пакета
                int n = 0; // количество байт в ответе

                if (cmd.equals(QUIT_CMD)) {
                    stopFlag = true; // остановка сервера
                    continue;
                }
                else{
                    char[] charArray = cmd.toCharArray();
                    test.setX(charArray[0]);
                    test.setY(charArray[1]);
                    test.setZ(charArray[2]);
                    test.setBBBBBB(Integer.parseInt(String.valueOf(charArray[0])), Integer.parseInt(String.valueOf(charArray[1])), Integer.parseInt(String.valueOf(charArray[2])));

                    test.getValue();
                    temp_answer = test.getB();

                    ByteBuffer.wrap(buf).putDouble(temp_answer);

                    // System.out.println(Arrays.toString(buf));
                    // double doubleValue = ByteBuffer.wrap(buf).getDouble();
                    // System.out.println(doubleValue);

                    // System.out.println(temp_answer);

                    stopFlag = true;
                }

                DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, recvPacket.getAddress(), recvPacket.getPort()); //создание дейтаграммы для отсылки данных
                s.send(sendPacket); //посылка дейтаграммы
            }
            System.out.println("UDPServer: выключение сервера.");
        }
        finally {
            if (s != null) {
                s.close(); //закрытие сокета сервера
            }
        }
    }
    public static void main(String[] args) {//метод main
        try {
            server udpSvr = new server(); //создание объекта udpSvr
            udpSvr.runServer(); //вызов метода объекта runServer
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}