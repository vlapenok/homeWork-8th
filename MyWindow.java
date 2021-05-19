import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame {
    int random = (int) (Math.random() * 10) + 1;
    int count = 1;
    JTextField field = new JTextField("Игра - угадай число");
    JPanel buttonsPanel = new JPanel(new GridLayout(1,10));
    JPanel restartPanel = new JPanel(new GridLayout(1,2));


    public MyWindow() {
        setTitle("My Game Window");
        setBounds(500, 400, 800, 180);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        add(field, BorderLayout.NORTH); //Добавили текстовый блок на север
        Font font = new Font("Arial", Font.BOLD, 20);
        field.setFont(font);
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setEditable(false);

        //Добавили панель с кнопками
        add(buttonsPanel, BorderLayout.CENTER);
        Font fontButtons = new Font("Arial", Font.BOLD, 16);
        buttonsPanel.setBackground(Color.GRAY);

        //Создаем 10 кнопок
        for (int i = 1; i <= 10; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setFont(fontButtons);
            buttonsPanel.add(button);
            int buttonIndex = i; // Переменная i не должна изменяться для передачи в слушатель
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonAction(buttonIndex);
                }
            });
        }


        //Добавили панель с кнопками новой игры и выхода
        add(restartPanel, BorderLayout.SOUTH);
        JButton restartButton = new JButton("Новая игра");
        JButton exitButton = new JButton("Выход");
        restartButton.setFont(font);
        exitButton.setFont(font);
        restartPanel.add(restartButton);
        restartPanel.add(exitButton);

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                random = (int) (Math.random() * 10) + 1;
                field.setText("Игра началась!" + " У вас 3 попытки.");
                count = 1;
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void buttonAction (int button) {
        if(button == random) {
            field.setText("WIN!!!" + " " + "Вы угадали с " + count + " попытки");
        } else if(button < random) {
            field.setText("Загаданное число больше");
            count = checkCount(count);
        } else {
            field.setText("Загаданное число меньше");
            count = checkCount(count);
        }
    }

    private int checkCount (int x) {
        x++;
        if(x >= 4) {
            field.setText("Попытки закончились, начните заново");
        }
        return x;
    }
}