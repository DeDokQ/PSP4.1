package parts.part3;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.net.*;
class client extends Frame implements ActionListener, WindowListener {
    TextField tf, tf1, tf2, tf3, tf4, tf5, tf6;
    TextArea ta;
    Label la, la1, la2, la3, la4;
    Socket sock = null;
    InputStream is = null;
    OutputStream os = null;
    public static void main(String args[]) {
        client c = new client();
        c.GUI();
    }
    private void GUI() {
// super("Клиент");
        setTitle("КЛИЕНТ");
        tf = new TextField("127.0.0.1");//ip adress клиента
        tf1 = new TextField("1024");// port клиента
        tf2 = new TextField();

        ta = new TextArea();

        la = new Label("IP");
        la1 = new Label("PORT");
        la2 = new Label("Напишите ниже Вашу строку");
        la3 = new Label("Результат");
        la4 = new Label(" ");

        Button btn = new Button("Подключиться ");
        Button btn1 = new Button("Отправить ");

        tf.setBounds(80, 50, 80, 25);
        tf1.setBounds(260, 50, 80, 25);
        tf2.setBounds(50, 150, 300, 25);

        ta.setBounds(150, 250, 300, 100);

        btn.setBounds(390, 50, 100, 25);
        btn1.setBounds(360, 150, 70, 25);

        la.setBounds(50, 50, 150, 25);
        la1.setBounds(200, 50, 150, 25);
        la2.setBounds(50, 100, 200, 25);
        la3.setBounds(150, 230, 150, 25);
        la4.setBounds(600, 10, 150, 25);

        add(tf);
        add(tf1);
        add(tf2);

        add(btn);
        add(btn1);

        add(ta);

        add(la);
        add(la1);
        add(la2);
        add(la3);
        add(la4);

        setSize(600, 400);
        setVisible(true);
        addWindowListener(this);
        btn.addActionListener(al);
        btn1.addActionListener(this);

        tf2.getText();
    }
    public void windowClosing(WindowEvent we) {
        if (sock != null && !sock.isClosed()) { // если сокет не пустой и сокет открыт
            try {
                sock.close(); // сокет  закрывается
            } catch (IOException e) {
            }
        }
        this.dispose();
    }
    public void windowActivated(WindowEvent we) {}   ;
    public void windowClosed(WindowEvent we) {};
    public void windowDeactivated(WindowEvent we) {};
    public void windowDeiconified(WindowEvent we) {}   ;
    public void windowIconified(WindowEvent we) {};
    public void windowOpened(WindowEvent we) { } ;
    public void actionPerformed(ActionEvent e) {
        if (sock == null) {
            return;
        }
        try {
            is = sock.getInputStream(); // входной поток для чтения данных
            os = sock.getOutputStream();// выходной поток для записи данных

            String numbers = ""; //перменная,в которую записываются введенные числа
            numbers += tf2.getText() + " ";
            os.write(numbers.getBytes()); // отправляем введенные данные. Тип string переводим в byte
            System.out.println(numbers);

            byte[] bytes = new byte[1024];
            is.read(bytes); //получаем назад информацию,которую послал сервер

            String str = new String(bytes, "UTF-8"); // переводим тип byte в String

            ta.append(str); // в text area записываем полученные данные

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                os.close();//закрытие выходного потока
                is.close();//закрытие входного потока
                sock.close();//закрытие сокета, выделенного для работы с сервером
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void actionPerformed2(ActionEvent e) {}
    ActionListener al = new ActionListener() { //событие на нажатие кнопки
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                sock = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                //создается сокет по ip адрессу и порту
            } catch (NumberFormatException e) {

            } catch (UnknownHostException e) {

            } catch (IOException e) {

            }
        }
    };
}
