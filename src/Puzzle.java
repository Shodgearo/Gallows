// Загадываемы слова

public class Puzzle {
    static String[] abc = {" ", "А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П",
                            "Р", "С", "Т", "У", "Ф", "Х", "Ч", "Ц", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я"};

    static String[] puzzle = {"ЗАГАДКА", "ВИСЕЛИЦА"};

    public static String getChar(int location) {
        return abc[location];
    }

    public static String getPuzzle(int location) {
        return puzzle[location];
    }
}
