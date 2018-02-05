// Управляющий класс

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame{
    JPanel panel1; // Панель, на которой будет изображения
    JPanel panel2; // Панель, на которой будет отображаться загадка
    JPanel panel3; // Панель, на которой будет JComboBox и JButton
    private JComboBox list; // Список всех букв
    private JLabel label; // Загадка
    private Player player; // Игрок
    private String textLabel; // Текст загадки

    public Game(){
        initImage(); // Инициализируем все картинки
        player = new Player();
        initPanel1(); // Инициализируем панели
        initPanel2();
        initPanel3();
        initFrame(); // Инициализируем фрейм
    }

    // Панель на которой бдует изображения
    private void initPanel1() {
        panel1 = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage((Image) player.getPicture().image , 0, 0, this);
            }
        };
    // Установить размеры панели, без pack() не работает хз чё такое
        panel1.setPreferredSize(new Dimension(300, 250)); // height pic == 240
        add(panel1, BorderLayout.NORTH);
    }

     private void initPanel2() {
        initLabel(); // Настраиваем метку
        panel2 = new JPanel();
        // Настройки панели
        panel2.setPreferredSize(new Dimension(300, 55));
        panel2.add(label);
        add(panel2, BorderLayout.CENTER);
     }

    private void initPanel3() {
        // Инициализация компонентов
        list = new JComboBox(Puzzle.abc); // Список
        panel3 = new JPanel();
        // Настройки панели
        panel3.setPreferredSize(new Dimension(300, 70)); // Размер панели
        panel3.add(list); // Добавили список

// Не работает, 2 раза вызывает метод перед тем как завершиться и прорисовать
//        list.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (!(list.getSelectedIndex() == 0))
//                    // Создать метод проверяющий есть ли выбранная буква в загадываемом тексте
//                    label.setText(setTextLabel(list.getSelectedIndex()));
//
//                System.out.println(player.getError());
//                Thread thread = Thread.currentThread();
//                try {
//                    thread.sleep(1000);
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//                repaint();
//            }
//        });

        // Обработчики для кнопки и списка // Не рп

        list.addActionListener(new ActionListener() { // Обработчик событий
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(list.getSelectedIndex() == 0)) // Если не выбрали пустую строку (по умолчанию)
                    // Создать метод проверяющий есть ли выбранная буква в загадываемом тексте
                // Считаем сколько раз ошиблись и сколько букв угадали, чтобы узнать когда мы победим
                    label.setText(setTextLabel(list.getSelectedIndex()));

                weWin(textLabel);
                weLose();

                repaint(); // Перерисовываем
            }
        });

        add(panel3, BorderLayout.SOUTH); // Добавили панель на фрейм
    }

    private void weLose() {
        if(player.getError() == Pics.values().length)
            win(); // Мне уже просто лень, путь просто перезапускается вся игра
    }

    private String setTextLabel(int index){
        String enigma = Puzzle.getPuzzle(player.getTemp());
        String ch = Puzzle.abc[index];
        int count = 0;

        if (enigma.contains(ch)) {
            for (int i = 0; i < enigma.length(); i++, count++) {
                String str = String.valueOf(enigma.charAt(i));

                if (str.equals(ch)) {
                    char[] chars = textLabel.toCharArray(); // Переводим в массив строк
                    chars[count] = ch.charAt(0); // Запихиваем на место отгаданной буквы выбранный символ
                    textLabel = String.valueOf(chars); // Переводим ообратно в строку
                }

                count++;
            }

            return textLabel;
        }
        else {
            player.setError();
            return textLabel;
        }
    }

    // Количество звёзд и пробелов для отображения загадки
    private String setTotalStars() {
        String str = ""; // Срока из звёздочек
        int leng = Puzzle.getPuzzle(player.setPuzzle()).length();

        for (int i = 0; i < leng; i++) {
            str += "*";
            if((i + 1) < leng) str += " "; // Между звёздами ставим пробелы
        }

        return str;
    }

    // Спрашиваем, все ли символы открыты в загадываемом слове?
    private void weWin(String textLabel) {
        for (int i = 0; i < textLabel.length(); i++) {
            String s = String.valueOf(textLabel.charAt(i));

            if (s.equals("*")) return; // Если есть хоть один символ звезды, то не победили
            else if(i == textLabel.length() - 1) win(); // Если Звезд нет и конец сроки, значит всё отгадали
        }
    }

    private void win() {
        player = new Player();
        textLabel = setTotalStars();
        label.setText(textLabel);

        repaint();
    }

    private void initImage() { // Инициализируем все картинки
        for (Pics pics : Pics.values()) {
            String fileName = pics.name().toLowerCase() + ".png"; // Имя картинки
            pics.image = new ImageIcon(getClass().getResource(fileName)).getImage(); // Конвертируем в Image
        }
    }

    // Настройки и инициализация метки
    private void initLabel() {
        textLabel = setTotalStars(); // Сохраняем нашу строку и задаём начальное значение в виде звёзд
        label = new JLabel(textLabel);
        label.setFont(new Font("Arial", Font.BOLD, 25));
    }

    // Инициализация окна
    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Закрыть программу при нажатии на крестик
        setTitle("Gallows"); // Название окна, титул
        setResizable(false); // Нельзя изменить размеры окна
        setVisible(true); // Отобразить рамку окна
        pack(); // Оптимально расположить все элементы
        setLocationRelativeTo(null); // Переместить окно в центр экрана
    }
}
