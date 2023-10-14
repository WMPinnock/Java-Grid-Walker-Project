// Wayne Pinnock, CS 212, Spring Semester 2023
import java.util.Scanner;
import java.lang.Math;

class Main {
  public static void main(String[] args) {
// Ask user for how many rows and columns
    Scanner input = new Scanner(System.in);
    int rows, columns;
    System.out.print("How many rows? "); 
    rows = input.nextInt();
    System.out.print("How many columns? ");
    columns = input.nextInt();
    
// Generate grid
    char[][] grid = new char[rows][columns];
    String directions = "LRUD";

// Fill in grid with random direction letters
    for(int i = 0; i < grid.length; i++) { 
      for(int j = 0; j < grid[i].length; j++) {
        grid[i][j] = (((directions.charAt((int)((Math.random() * directions.length() - 1) + 1)))));
      }
    }

// Randomly places one T and displays Grid
    boolean letterTPlaced = false;
    for(int i = 0; i < grid.length; i++) {
      for(int j = 0; j < grid[i].length; j++) {
        if(j == grid[i].length - 1 && i == grid.length - 1 && letterTPlaced == false) {
          grid[i][j] = 'T';
          letterTPlaced = true;
        }
        if((int)(Math.random() * (rows * columns) - 1) + 1 == 1 && letterTPlaced == false) {
          grid[i][j] = 'T';
          letterTPlaced = true;
        }
        System.out.print(grid[i][j] + " ");
      }
      System.out.println();
    }
    
// Ask user for what row and what column they would like to start in
    int startingRow, startingColumn;  
    System.out.print("What is the starting row? "); 
    startingRow = input.nextInt();
    System.out.print("What is the starting column? ");
    startingColumn = input.nextInt();

// Steps algorithm
    int steps = 0;
    boolean isOnT = false, isOffGrid = false, hasIntersectedItself = false;
    for(int i = startingRow; i < grid.length; i++) {
      for(int j = startingColumn; j < grid[i].length; j++) {
          while(isOnT == false && isOffGrid == false && hasIntersectedItself == false) {
            switch(grid[i][j]) {
              case 'L':
                if(j == 0) {
                  grid[i][j] = '*';
                  System.out.println("You left the grid!");
                  isOffGrid = true;
                } else {
                  j--;
                  grid[i][j+1] = '*';
                  steps++;
                }
                break;
              case 'R':
                if(j == grid[i].length - 1) {
                  grid[i][j] = '*';
                  System.out.println("You left the grid!");
                  isOffGrid = true;
                } else {
                  j++;
                  grid[i][j-1] = '*';
                  steps++;
                }
                break;
              case 'U':
                if(i == 0) {
                  grid[i][j] = '*';
                  System.out.println("You left the grid!");
                  isOffGrid = true;
                } else {
                  i--;
                  grid[i+1][j] = '*';
                  steps++;
                }
                break;
              case 'D':
                if(i == grid.length - 1) {
                  grid[i][j] = '*';
                  System.out.println("You left the grid!");
                  isOffGrid = true;
                } else {
                  i++;
                  grid[i-1][j] = '*';
                  steps++;
                }
                break;
              case 'T':
                System.out.println("You have finished in " + steps + " steps.");
                isOnT = true;
                break;
              case '*':
                System.out.println("Your path has intersected itself!");
                hasIntersectedItself = true;
                break;
            }
          }
        if(isOnT == true || isOffGrid == true || hasIntersectedItself == true) {
          break;
        }
      }
      if(isOnT == true || isOffGrid == true || hasIntersectedItself == true) {
          break;
        }
    }

// Display results
    for(int i = 0; i < grid.length; i++) {
      for(int j = 0; j < grid[i].length; j++) {
        System.out.print(grid[i][j] + " ");
      }
      System.out.println();
    }
  }
}
// Beneath are explanations for sections of code to remind myself of what I wrote for them to do, while the other sections of code that are left unexplained are self-explanatory
/* "For the current column at the current row, set its element to be a random character from the directions variable"
for(int i = 0; i < grid.length; i++) {  // Generates Grid with direction characters
      for(int j = 0; j < grid[i].length; j++) {
        grid[i][j] = (((directions.charAt((int)((Math.random() * directions.length() - 1) + 1)))));
      }
    }
*/
/* "The boolean variable letterTPlaced is used in a way that will prevent T from being placed again if it has already been placed. For the current column at the current row, check if it is the last column in the last row and that letterTPLaced is false, if so set the element to be T and then set letterTPlaced to be true. If that first if statement isn't true, check if the random number from 1 to the maximum columns is 1, if so set the element of the current column in the current row to be T and then set letterTPlaced to be true. The T has a lower chance of being placed if there are more rows and columns. If T has not been placed yet, and it is at the last column of the last row, it will forcibly be placed.
boolean letterTPlaced = false;
for(int i = 0; i < grid.length; i++) {  // Randomly places one T and displays Grid
      for(int j = 0; j < grid[i].length; j++) {
        if(j == grid[i].length - 1 && i == grid.length - 1 && letterTPlaced == false) {
          grid[i][j] = 'T';
          letterTPlaced = true;
        }
        if((int)(Math.random() * (rows * columns) - 1) + 1 == 1 && letterTPlaced == false) {
          grid[i][j] = 'T';
          letterTPlaced = true;
        }
        System.out.print(grid[i][j] + " ");
      }
      System.out.println();
    }
*/

/* "Create the steps variable to display how many steps were made in the end. Create boolean variables to keep track of the 3 conditions that will end the game. Start at the specified column in the specified row by the user. Starting from that element, run the while loop while the 3 conditions that will end the game are not met. Each directional letter case in the switch statement has a special case for predetermining whether or not the next element will be out of bounds.
  int steps = 0;
  boolean isOnT = false, isOffGrid = false, hasIntersectedItself = false;
  for(int i = startingRow; i < grid.length; i++) {
    for(int j = startingColumn; j < grid[i].length; j++) {
        while(isOnT == false && isOffGrid == false && hasIntersectedItself == false) {
          switch(grid[i][j]) {
            case 'L':
              if(j == 0) {
                grid[i][j] = '*';
                System.out.println("You left the grid!");
                isOffGrid = true;
              } else {
                j--;
                grid[i][j+1] = '*';
                steps++;
              }
              break;
            case 'R':
              if(j == grid[i].length - 1) {
                grid[i][j] = '*';
                System.out.println("You left the grid!");
                isOffGrid = true;
              } else {
                j++;
                grid[i][j-1] = '*';
                steps++;
              }
              break;
            case 'U':
              if(i == 0) {
                grid[i][j] = '*';
                System.out.println("You left the grid!");
                isOffGrid = true;
              } else {
                i--;
                grid[i+1][j] = '*';
                steps++;
              }
              break;
            case 'D':
              if(i == grid.length - 1) {
                grid[i][j] = '*';
                System.out.println("You left the grid!");
                isOffGrid = true;
              } else {
                i++;
                grid[i-1][j] = '*';
                steps++;
              }
              break;
            case 'T':
              System.out.println("You have finished in " + steps + " steps.");
              isOnT = true;
              break;
            case '*':
              System.out.println("Your path has intersected itself!");
              hasIntersectedItself = true;
              break;
          }
        }
      if(isOnT == true || isOffGrid == true || hasIntersectedItself == true) {
        break;
      }
    }
    if(isOnT == true || isOffGrid == true || hasIntersectedItself == true) {
        break;
      }
  }
*/