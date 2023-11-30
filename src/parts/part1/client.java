package parts.part1;

import java.io.*; //импорт пакета, содержащего классы для ввода/вывода
import java.net.*; //импорт пакета, содержащего классы для работы в сети

public class client {
    public static void main(String[] arg) {
        try {
            System.out.println("Подключение к серверу....");
            Socket clientSocket = new Socket("127.0.0.1",2525); //установление соединения между локальной машиной и указанным портом узла сети
            System.out.println("Подключение к серверу удалось!");

            BufferedReader stdin =
                    new BufferedReader(new InputStreamReader(System.in)); //создание буферизированного символьного потока ввода

            ObjectOutputStream coos =
                    new ObjectOutputStream(clientSocket.getOutputStream()); //создание потока вывода

            ObjectInputStream  cois =
                    new ObjectInputStream(clientSocket.getInputStream()); //создание потока ввода

            System.out.println("Введите строку: 'выход', чтобы выйти\n");
            String clientMessage = stdin.readLine();
            System.out.println("Вы ввели: "+clientMessage);

            double [][] mat = {
                    {1.0,2.0,3.0},
                    {4.0,5.0,6.0},
                    {7.0,8.0,9.0}
            };

            clientMessage = clientMessage.toLowerCase();

            while(!clientMessage.equals("выход")) { //выполнение цикла, пока строка не будет равна «quite»
                coos.writeObject(clientMessage); //потоку вывода присваивается значение строковой переменной (передается серверу)
                coos.writeObject(mat);

                System.out.println("~сервер~: "+cois.readObject()); //выводится на экран содержимое потока ввода (переданное сервером)
                System.out.println("---------------------------");

                clientMessage = stdin.readLine(); //ввод текста с клавиатуры
                System.out.println("Вы ввели: "+clientMessage); //вывод в консоль строки и значения строковой переменной
                clientMessage = clientMessage.toLowerCase();
            }
            coos.close(); //закрытие потока вывода
            cois.close(); //закрытие потока ввода
            clientSocket.close(); //закрытие сокета
        }catch(Exception e)	{
            e.printStackTrace(); //выполнение метода исключения е
        }
    }
}
