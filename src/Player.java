// Что делает игрок

import java.util.Random;

public class Player {
    private int error;
    private Random rand;
    private int temp; // Для хранения индекса загадки

    public Player() {
        error = 0;
        rand = new Random();
    }

    public Pics getPicture(){
        int num = getError();
        return Pics.values()[num];
    }

    public int getError(){
        return error; // Получаем количество всех ошибок
    }

    public int getTemp() {
        return temp; // Для получаения порядкового номера из массива загадок
    }

    public void setError() {
        error++; // Что делаем, когда ошибаемся
    }

    public int setPuzzle() {
        temp = rand.nextInt(Puzzle.puzzle.length);
        return temp;
    }
}
