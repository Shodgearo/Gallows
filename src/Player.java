// ��� ������ �����

import java.util.Random;

public class Player {
    private int error;
    private Random rand;
    private int temp; // ��� �������� ������� �������

    public Player() {
        error = 0;
        rand = new Random();
    }

    public Pics getPicture(){
        int num = getError();
        return Pics.values()[num];
    }

    public int getError(){
        return error; // �������� ���������� ���� ������
    }

    public int getTemp() {
        return temp; // ��� ���������� ����������� ������ �� ������� �������
    }

    public void setError() {
        error++; // ��� ������, ����� ���������
    }

    public int setPuzzle() {
        temp = rand.nextInt(Puzzle.puzzle.length);
        return temp;
    }
}
