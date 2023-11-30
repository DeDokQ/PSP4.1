package parts.part1;

import java.io.*;//импорт пакета, содержащего классы для
//ввода/вывода
import java.net.*;//импорт пакета, содержащего классы для работы в
import java.util.Arrays;

//сети Internet
public class server{
    public static void main(String[] arg) {//объявление объекта класса ServerSocket
    ServerSocket serverSocket = null;
    Socket clientAccepted     = null; //объявление объекта класса Socket
    ObjectInputStream  sois   = null; //объявление байтового потока ввода
    ObjectOutputStream soos = null; //объявление байтового потока вывода

    try {
        System.out.println("Запсу сервера....");
        serverSocket = new ServerSocket(2525); //создание сокета сервера для заданного порта

        clientAccepted = serverSocket.accept(); //выполнение метода, который обеспечивает реальное подключение сервера к клиенту
        System.out.println("Подключение клиента к серверу произошло успешно!...."); // создание потока ввода soos = new

        sois = new ObjectInputStream(clientAccepted.getInputStream());
        soos = new ObjectOutputStream(clientAccepted.getOutputStream()); //создание потока вывода

        String clientMessageRecieved = (String)sois.readObject(); //объявление строки и присваивание ей данных потока ввода, представленных в виде строки (передано клиентом)
        double [][] mat = (double[][])sois.readObject();

        while(!clientMessageRecieved.equals("выход")) //выполнение цикла: пока строка не будет равна «quite»
        {
            System.out.println("На сервер получено сообщение: '"+clientMessageRecieved+"'");
            System.out.println("Матрица получена...");

            Determin bb = new Determin(mat);

            soos.writeObject(bb.getValue()); //потоку вывода присваивается значение строковой переменной (передается клиенту)
            clientMessageRecieved = (String)sois.readObject(); //строке присваиваются данные потока ввода, представленные в виде строки (передано клиентом)
        }
    }catch(Exception e)	{

    } finally {
        try {
            sois.close(); //закрытие потока ввода
            soos.close(); //закрытие потока вывода
            clientAccepted.close(); //закрытие сокета, выделенного для клиента
            serverSocket.close(); //закрытие сокета сервера
        } catch(Exception e) {
            e.printStackTrace(); //вызывается метод исключения е
        }
    }
}
}
