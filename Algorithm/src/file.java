//Mahawaththa Madanayaka      uow=W1790301      iit id=20191040

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class file {
    public static char[][] parser(String fileName) {
        try {
            int height = 0;
            String line;
            int row = 0;
            int column = 0;

            FileReader fileReader = new FileReader("tests/" + fileName + ".txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.mark(1);

            int m = bufferedReader.readLine().length();
            bufferedReader.reset();

            char[][] twoArr = new char[m][m];

            while ((line = bufferedReader.readLine()) != null) {
                height++;
                char[] columns = line.toCharArray();
                twoArr[row] = columns;
                row++;
            }

            for (int i = 0; i < twoArr.length; i++) {

                for (int j = 0; j < twoArr[i].length; j++) {

                    System.out.print(twoArr[i][j]);
                }
                System.out.println();
            }

            return twoArr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new char[0][];
    }


    public static int getFileColumnsNumber(String filename) {
        File file = new File(filename);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        }

        int number = 0;
        if (scanner.hasNextLine()) {
            number = scanner.nextLine().split(",").length;
        }
        scanner.close();
        return number;

    }


    public static void main(String args[]) throws IOException {
        String fileName = "maze10_2";

        char[][] grid = parser(fileName);
        pFinder finder = new pFinder(grid, fileName);
        finder.shortestPSpotter();

        try {

            // make a connection to the file
            Path file = Paths.get("tests/" + fileName + ".txt");

            // read all lines of the file to count height
            long count = Files.lines(file).count();
            System.out.println("Height of the puzzle: " + count);

            String filename = new String();
            //System.out.println(getFileColumnsNumber(filename));

        }
        catch (Exception e) {
            e.getStackTrace();
        }



    }
}









