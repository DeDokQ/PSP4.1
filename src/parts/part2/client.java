package parts.part2;

import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;

public class client { //описание класса клиента
    public void runClient() throws IOException { //метод клиента runClient

        DatagramSocket s = null; //создание дейтаграммы

        try {
            byte[] buf = new byte[512]; //буфер для приема/передачи дейтаграммы
            s = new DatagramSocket(); //привязка сокета к реальному объету

            System.out.println("Крутой клиент запущен...");

            byte[] clientZ = { '1', '2', '3' };

            DatagramPacket sendPacket = new DatagramPacket(clientZ, clientZ.length, InetAddress.getByName("127.0.0.1"), 8001); //создание дейтаграммы для отсылки данных
            s.send(sendPacket); //посылка дейтаграммы


            DatagramPacket recvPacket = new DatagramPacket(buf, buf.length); //создание дейтаграммы для получения данных
            s.receive(recvPacket); //получение дейтаграммы


            double doubleValue = ByteBuffer.wrap(buf).getDouble();
            System.out.println("Что-то математическое я получил, спасибо! : " + doubleValue);

            System.out.println("Завершаем это!");
        }
        finally {
            if (s != null) {
                s.close(); //закрытие сокета клиента
            }
        }
    }
    public static void main(String[] args) { //метод main
        try {
            client client = new client(); //создание объекта client
            client.runClient(); //вызов метода объекта client
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}

