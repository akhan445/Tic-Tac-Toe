package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.print("Enter cells: ");
        Scanner scanner =  new Scanner(System.in);
        scanner.useDelimiter("");

        String[][] cellsArray = new String[3][3];
        int countX = 0;
        int countO = 0;
        int countBlank = 0;

        for (int i = 0; i < cellsArray.length; i++) {
            for (int j = 0; j < cellsArray.length; j++) {

                cellsArray[i][j] = "";

//                cellsArray[i][j] = scanner.next().replace("_", "").trim();

                if (cellsArray[i][j].equals("X")) {
                    countX++;
                } else if (cellsArray[i][j].equals("O")) {
                    countO++;
                }
                else {
                    countBlank++;
                }
            }
        }
        printField(cellsArray);
        boolean xMove = true;

        boolean[] winner = new boolean[8];
        int countWinner = 0;


//        String result = analyzeField(cellsArray, countX, countO, countBlank, winner, countWinner);

//        System.out.println(result);
//        System.out.println(cellsArray[0][0].isEmpty()); //will print true for blank cells
        String valid = "\\d\\s*\\d";
        boolean flag;

        do {
            // Horizontal Checks for winner
            if (cellsArray[0][0].equals(cellsArray[0][1]) && cellsArray[0][0].equals(cellsArray[0][2]) && !cellsArray[0][0].isBlank()) {
                winner[0] = true;
                countWinner++;
            }
            if (cellsArray[1][0].equals(cellsArray[1][1]) && cellsArray[1][0].equals(cellsArray[1][2]) && !cellsArray[1][0].isBlank()) {
                winner[1] = true;
                countWinner++;
            }
            if (cellsArray[2][0].equals(cellsArray[2][1]) && cellsArray[2][0].equals(cellsArray[2][2]) && !cellsArray[2][0].isBlank()) {
                winner[2] = true;
                countWinner++;
            }

            //Vertical Checks for winner
            if (cellsArray[0][0].equals(cellsArray[1][0]) && cellsArray[0][0].equals(cellsArray[2][0]) && !cellsArray[0][0].isBlank()) {
                winner[3] = true;
                countWinner++;
            }
            if (cellsArray[0][1].equals(cellsArray[1][1]) && cellsArray[0][1].equals(cellsArray[2][1]) && !cellsArray[0][1].isBlank()) {
                winner[4] = true;
                countWinner++;
            }
            if (cellsArray[0][2].equals(cellsArray[1][2]) && cellsArray[0][2].equals(cellsArray[2][2]) && !cellsArray[0][2].isBlank()) {
                winner[5] = true;
                countWinner++;
            }

            //Diagonal Checks for winner
            if (cellsArray[0][0].equals(cellsArray[1][1]) && cellsArray[0][0].equals(cellsArray[2][2]) && !cellsArray[0][0].isBlank()) {
                winner[6] = true;
                countWinner++;
            }
            if (cellsArray[0][2].equals(cellsArray[1][1]) && cellsArray[0][2].equals(cellsArray[2][0]) && !cellsArray[0][2].isBlank()) {
                winner[7] = true;
                countWinner++;
            }

            String result = analyzeField(cellsArray, countX, countO, countBlank, winner, countWinner);

            //Winner or Draw ends game
            if (result.equals("Winner")) {
                String printWinner = "";
                if (xMove) {
                    printWinner = "O";
                } else {
                    printWinner = "X";
                }
                System.out.println(printWinner + " wins");
                break;
            }
            if (result.equals("Draw")) {
                System.out.println(result);
                break;
            }


            System.out.print("Enter the coordinates: ");
            String input = scanner.nextLine().trim();

            flag = input.matches(valid);
            // Check if input matches two single digits
            if (!flag) {
                System.out.println("You should enter numbers!");
            }
            // User has inputted 2 valid single digit integers
            else {
                int num = Character.getNumericValue(input.charAt(0)); // first coordinate
                int num2 = Character.getNumericValue(input.charAt(2)); // second coordinate
                //Check if numbers are between 1 and 3
                if (num < 1 || num > 3 || num2 < 1 || num2 > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
                else {
                    // check is x is 1, 2 or 3
                    if (num == 1) {
                        if (num2 == 1) {
                            num = 2; //set x to 2
                        }
                        else if (num2 == 2) {
                            num = 1; //set x to 1
                        }
                        else {
                            num = 0; //set x to 0
                        }
                        num2 = 0; // set y to 0

                    }
                    else if (num == 2) {
                        if (num2 == 1) {
                            num = 2; //set x to 2
                        }
                        else if (num2 == 2) {
                            num = 1; //set x to 1
                        }
                        else {
                            num = 0; //set x to 0
                        }
                        num2 = 1; // set y to 1

                    }
                    else {
                        if (num2 == 1) {
                            num = 2; //set x to 2
                        }
                        else if (num2 == 2) {
                            num = 1; //set x to 1
                        }
                        else {
                            num = 0; //set x to 0
                        }
                        num2 = 2; // set y to 2
                    }

                    if (cellsArray[num][num2].isEmpty()) {
                        if (xMove) {
                            cellsArray[num][num2] = "X";
                            xMove = false; // change flag to O move
                            countBlank--;
                        }
                        else {
                            cellsArray[num][num2] = "O";
                            xMove = true; // change flag to X move
                            countBlank--;
                        }
                        printField(cellsArray);
                    }
                    else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }

                }
            }
        } while (true);


    }

    public static void printField(String[][] cellsArray) {
        System.out.println("---------");
        for (int i = 0; i < cellsArray.length; i++) {
            System.out.print("| ");

            for (int j = 0; j < cellsArray.length; j++) {
                System.out.print(cellsArray[i][j] + " ");
                if(cellsArray[i][j].isEmpty()) {
                    System.out.print(" ");
                }
            }
            System.out.print("|\n");

        }
        System.out.println("---------");
    }

    public static String analyzeField(String[][] cellsArray, int countX, int countO, int countBlank, boolean[] checkWinner, int countWinner) {
        //check all scenarios that are impossible and game not finished first
        //there are disproportionate X to O ratio
        if (Math.abs(countO - countX) > 1) {
            return "Impossible";
        }
        //more than one winner calculated
        else if (countWinner > 1) {
            return "Impossible";
        }
        // there are empty remaining fields
        else if (countWinner == 0 && countBlank > 0) {
         return "Game not finished";
        }
        // no empty cells and no winner detected
        else if (countWinner == 0 && countBlank == 0){
            return "Draw";
        }
        // only other case is there is a winner
        else {
            return "Winner";
//            int index = 0;
//            for (int i = 0; i < checkWinner.length; i++) {
//                if (checkWinner[i] == true){
//                    index = i;
//                }
//            }
//
//            // winner involves middle field (one of 4 cases)
//            if (index == 1 || index == 4 || index == 6 || index == 7) {
//                return cellsArray[1][1] + " wins";
//            }
//            //winner involves top left corner field
//            else if (index == 0 || index == 3 ) {
//                return cellsArray[0][0] + " wins";
//            }
//            //winner involves bottom right corner field
//            else {
//                return cellsArray[2][2] + " wins";
//            }


        }
    }

}
