package parts.part3;

import java.net.*;
import java.io.*;
import java.util.Locale;

public class server {
    static int countclients = 0;//счетчик подключившихся клиентов

    public static void main(String args[]) throws IOException {
        ServerSocket sock = null;
        InputStream is = null;
        OutputStream os = null;

        try {
            sock = new ServerSocket(1024); //создаем серверный сокет работающий локально по порту 1024
            while (true) { //бесконечный цикл для возможности подключения последовательно нескольних клиентов
                Socket client = sock.accept(); //сработает, когда клиент подключится, для него выделится отдельный сокет client

                countclients++; //количество подключившихся клиентов увеличивается на 1
                System.out.println("=======================================");
                System.out.println("Client " + countclients + " connected");

                is = client.getInputStream(); //получили входной поток для чтения данных
                os = client.getOutputStream();//получили выходной поток для записи данных

                while(true){
                    byte[] bytes = new byte[1024];
                    is.read(bytes); //чтение иформации, посланной клиентом, из вхоного потока в массив bytes[]

                    String str = new String(bytes, "UTF-8"); // переводим тип byte в тип String
                    System.out.println(str);

                    if(str.toLowerCase(Locale.ROOT).equals("quit")){
                        System.out.println("QUIT!");
                        break;
                    }
                    else{
                        StringBuilder myName = new StringBuilder(str);
                        for(int k = 0; k < myName.length() - 1; k++){
                            if(k % 4 == 0 && k != 0){
                                myName.setCharAt(k-1, '%');
                            }
                        }

                        str = myName.toString();

                        bytes = new byte[1024];
                        bytes = str.getBytes();
                        os.write(bytes); // отправляем клиенту информацию
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
        } finally {
            is.close();//закрытие входного потока
            os.close();//закрытие входного потока
            sock.close();//закрытие сокета, выделенного для работы с подключившимся клиентом
            System.out.println("Client " + countclients + " disconnected");
        }
    }
}
