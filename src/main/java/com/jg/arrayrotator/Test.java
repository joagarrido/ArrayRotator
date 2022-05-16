package com.jg.arrayrotator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        // keep this function call here
        Scanner s = new Scanner(System.in);
        Integer[][] source = null;
        int x = 0;
        int y = 0;

        String filePath = "D:/temp/input1.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String sCurrentLine;
            int lineCount = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] lineArray = sCurrentLine.split(" ");
                if (++lineCount == 1) {
                    x = Integer.valueOf(lineArray[0]);
                    y = Integer.valueOf(lineArray[1]);
                    source = new Integer[100][100];
                } else {
                    for (int ind = 0; ind < y; ind++) {
                        source[lineCount - 2][ind] = Integer.valueOf(lineArray[ind]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Integer[][] rotated = rotate(source, 0, x, 0, y);
        for (int i = 0; i < x - 1; i++) {
            String line = "";
            for (int j = 0; j < y - 1; j++) {
                line = line.concat(rotated[i][j] != null? rotated[i][j].toString() + " ": "  ");
            }
            System.out.println(line);
        }
    }

    private static Integer[][] rotate(Integer[][] source, int xInit, int xEnd, int yInit, int yEnd) {
        Integer[][] rotated = source.clone();

        for (int i = xInit; i < xEnd - 1; i++) {
            for (int j = yInit; j < yEnd - 1; j++) {
                int rotatedX = 0;
                int rotatedY = 0;
                if (i < xEnd) {
                    if (j < yEnd - 1) {
                        rotatedX = i;
                        rotatedY = j + 1;
                    } else {
                        rotatedX = i + 1;
                        rotatedY = j;
                    }
                } else if (i < xEnd - 1) {
                    if (j == yInit) {
                        rotatedX = i - 1;
                        rotatedY = j;
                    } else if (j == yEnd - 1) {
                        rotatedX = i + 1;
                        rotatedY = j;
                    } else {
                        // Keep interior rings as it is.
                        rotatedX = i;
                        rotatedY = j;
                    }
                } else {
                    if (j > yInit) {
                        rotatedX = i;
                        rotatedY = j - 1;
                    } else {
                        rotatedX = i - 1;
                        rotatedY = j;
                    }
                }
                rotated[rotatedX][rotatedY] = source[i][j];
            }
        }
        return (xEnd - 1 <= xInit || yEnd - 1 <= yInit? rotated: rotate(rotated, xInit + 1, xEnd + 1, yInit + 1, yEnd - 1));
    }

}
