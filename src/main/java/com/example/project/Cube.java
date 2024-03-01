import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

// all moves work at first
// moves appear slighlty off with more inputs
// optimized solution works for 


public class RubiksCubeSimulator {
    // initlaize map for cube
    private Map<Character, char[][]> faces = new HashMap<>();
    // initialize stack for moves 
    private Stack<String> movesMade = new Stack<>();

    // Constructor to initialize   cube
    public RubiksCubeSimulator() {
        initializeCube();
    }

    // Method to initialize   cube
    private void initializeCube() {
        faces.put('r', new char[][]{{'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}});
        faces.put('b', new char[][]{{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}});
        faces.put('o', new char[][]{{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}});
        faces.put('g', new char[][]{{'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}});
        faces.put('y', new char[][]{{'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}});
        faces.put('w', new char[][]{{'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}});
        // for down face
        faces.put('d', new char[][]{{'d', 'd', 'd'}, {'d', 'd', 'd'}, {'d', 'd', 'd'}});
    }

    

    // method to configure the cube
    public void configCube(String input) {
        if ("r".equals(input)) {
            // Temporarily store the column that will be replaced
            char[] tempUp = new char[3];
            for (int i = 0; i < 3; i++) {
                tempUp[i] = faces.get('w')[i][2]; // Right column of the up w face
            }
    
            // Move  front g column to up w face
            for (int i = 0; i < 3; i++) {
                faces.get('w')[i][2] = faces.get('g')[i][2];
            }
    
            // Move down (y) column to front (g) face
            for (int i = 0; i < 3; i++) {
                faces.get('g')[i][2] = faces.get('y')[i][2];
            }
    
            // Move the back (b) column (inverted) to   down (y) face
            for (int i = 0; i < 3; i++) {
                faces.get('y')[i][2] = faces.get('b')[2-i][0]; // Note: Inverting   back (b) column
            }
    
            // Restore   up (w) column to   back (b) face (inverted)
            for (int i = 0; i < 3; i++) {
                faces.get('b')[2-i][0] = tempUp[i]; // Note: Inverting   column when moving to back
            }
    
            // Rotate   right (r) face itself clockwise
            rotateFaceClockwise('r');
        

        } else if ("l".equals(input)) {
            // Temporarily store   column that will be replaced
            char[] tempUp = new char[3];
            for (int i = 0; i < 3; i++) {
                tempUp[i] = faces.get('w')[i][0]; // Left column of   up (w) face
        }
            // Move   back (b) column to   up (w) face
            for (int i = 0; i < 3; i++) {
                faces.get('w')[i][0] = faces.get('b')[2-i][2]; // Note: Inverting   back (b) column
        }

            // Move   down (y) column to   back (b) face (inverted)
            for (int i = 0; i < 3; i++) {
                faces.get('b')[2-i][2] = faces.get('y')[i][0]; // Note: Inverting when moving to back
        }

            // Move   front (g) column to   down (y) face
            for (int i = 0; i < 3; i++) {
                faces.get('y')[i][0] = faces.get('g')[i][0];
        }

            // Restore   up (w) column to   front (g) face
            for (int i = 0; i < 3; i++) {
                faces.get('g')[i][0] = tempUp[i];
            }

            // Rotate   left (o) face itself clockwise
            rotateFaceClockwise('o');
        
        } else if ("u'".equals(input)) {
            // Temporarily store   top row that will be replaced
            char[] tempFront = new char[3];
            for (int i = 0; i < 3; i++) {
                tempFront[i] = faces.get('g')[0][i]; // Top row of   front (g) face
        }

            // Move   left (o) top row to   front (g) top row
            for (int i = 0; i < 3; i++) {
                faces.get('g')[0][i] = faces.get('o')[0][i];
        }

            // Move   back (b) top row to   left (o) top row
            for (int i = 0; i < 3; i++) {
                faces.get('o')[0][i] = faces.get('b')[0][i];
        }

            // Move   right (r) top row to   back (b) top row
            for (int i = 0; i < 3; i++) {
                faces.get('b')[0][i] = faces.get('r')[0][i];
        }

            // Restore   front (g) top row to   right (r) top row
            for (int i = 0; i < 3; i++) {
                faces.get('r')[0][i] = tempFront[i];
        }

            // Rotate   upper (w) face itself clockwise
            rotateFaceClockwise('w');
    
        } else if ("d".equals(input)) {
            // Temporarily store   bottom row of   front (g) face
            char[] tempFront = new char[3];
            for (int i = 0; i < 3; i++) {
                tempFront[i] = faces.get('g')[2][i]; // Bottom row of   front (g) face
    }
            
            // Move   bottom row of   left (o) face to   bottom row of   front (g) face
            for (int i = 0; i < 3; i++) {
                faces.get('g')[2][i] = faces.get('o')[2][i];
    }
            
            // Move   bottom row of   back (b) face to   bottom row of   left (o) face
            for (int i = 0; i < 3; i++) {
                faces.get('o')[2][i] = faces.get('b')[2][i];
    }
            
            // Move   bottom row of   right (r) face to   bottom row of   back (b) face
            for (int i = 0; i < 3; i++) {
                faces.get('b')[2][i] = faces.get('r')[2][i];
    }
            
            // Restore   bottom row of   front (g) face to   bottom row of   right (r) face
            for (int i = 0; i < 3; i++) {
                faces.get('r')[2][i] = tempFront[i];
    }
            
            if (faces.get('d') == null) {
                throw new IllegalStateException("Down face (y) is not initialized.");
    }
            // Proceed to rotate   down (y) face clockwise
            rotateFaceClockwise('y'); // Fixed: Rotate   'y' face instead of 'd'
            

        
        } else if ("f".equals(input)) {
            // Temporarily store   bottom row of   up (w) face
            char[] tempUp = new char[3];
            for (int i = 0; i < 3; i++) {
                tempUp[i] = faces.get('w')[2][i]; // Bottom row of   up (w) face
            }
    
            // Move   right column of   left (o) face to   bottom row of   up (w) face
            for (int i = 0; i < 3; i++) {
                faces.get('w')[2][i] = faces.get('o')[2 - i][2]; // Inverting   column
            }
    
            // Move   top row of   down (y) face to   right column of   left (o) face (in reverse order)
            for (int i = 0; i < 3; i++) {
                faces.get('o')[i][2] = faces.get('y')[0][2 - i];
            }
    
            // Move   left column of   right (r) face to   top row of   down (y) face (in reverse order)
            for (int i = 0; i < 3; i++) {
                faces.get('y')[0][i] = faces.get('r')[2 - i][0];
            }
    
            // Restore   bottom row of   up (w) face to   left column of   right (r) face
            for (int i = 0; i < 3; i++) {
                faces.get('r')[i][0] = tempUp[i];
            }
    
            // Rotate   front (g) face itself clockwise
            rotateFaceClockwise('g');

        
        } else if ("l'".equals(input)) {
            // Rotate   left (l) face itself counter-clockwise
            rotateFaceCounterClockwise('o'); // Assuming 'o' represents   left face
        
            // Temporarily store   column that will be moved from   front (g) to   up (w) face
            char[] tempFront = new char[3];
            for (int i = 0; i < 3; i++) {
                tempFront[i] = faces.get('g')[i][0]; // Left column of   front (g) face
            }
        
            // Move   down (y) column to   front (g) face
            for (int i = 0; i < 3; i++) {
                faces.get('g')[i][0] = faces.get('y')[i][0];
            }
        
            // Move   back (b) column (inverted) to   down (y) face
            for (int i = 0; i < 3; i++) {
                faces.get('y')[i][0] = faces.get('b')[2 - i][2]; // Inverting   column when moving
            }
        
            // Move   up (w) column to   back (b) face (inverted)
            for (int i = 0; i < 3; i++) {
                faces.get('b')[i][2] = faces.get('w')[2 - i][0]; // Note: Inverting   column when restoring
            }
        
            // Restore   front (g) column to   up (w) face
            for (int i = 0; i < 3; i++) {
                faces.get('w')[i][0] = tempFront[i];
            }
        } else if ("f'".equals(input)) {
            rotateFaceCounterClockwise('g'); // Assuming 'g' represents   front face
        
            char[] tempUp = new char[3];
            for (int i = 0; i < 3; i++) {
                tempUp[i] = faces.get('w')[2][i];
            }
        
            for (int i = 0; i < 3; i++) {
                faces.get('w')[2][i] = faces.get('r')[i][0];
            }
        
            for (int i = 0; i < 3; i++) {
                faces.get('r')[i][0] = faces.get('y')[0][2 - i];
            }
        
            for (int i = 0; i < 3; i++) {
                faces.get('y')[0][i] = faces.get('o')[2 - i][2];
            }
        
            for (int i = 0; i < 3; i++) {
                faces.get('o')[i][2] = tempUp[i];
            }
        
        } else if ("d'".equals(input)) {
            // Rotate the down (d) face itself counter-clockwise
            rotateFaceCounterClockwise('d'); // Assuming 'd' actually refers to the 'y' (yellow) face based on standard Rubik's cube notation.
        
            // Temporarily store the bottom row of the front (g) face to move it later to the right (r)
            char[] tempFront = new char[3];
            for (int i = 0; i < 3; i++) {
                tempFront[i] = faces.get('g')[2][i];
            }
        
            // Move the bottom row of the right (r) face to the bottom row of the front (g) face
            for (int i = 0; i < 3; i++) {
                faces.get('g')[2][i] = faces.get('r')[2][i];
            }
        
            // Move the bottom row of the back (b) face to the bottom row of the right (r) face
            for (int i = 0; i < 3; i++) {
                faces.get('r')[2][i] = faces.get('b')[2][i];
            }
        
            // Move the bottom row of the left (o) face to the bottom row of the back (b) face
            for (int i = 0; i < 3; i++) {
                faces.get('b')[2][i] = faces.get('o')[2][i];
            }
        
            // Restore the temporarily stored bottom row of the front (g) face to the bottom row of the left (o) face
            for (int i = 0; i < 3; i++) {
                faces.get('o')[2][i] = tempFront[i];
            }
        
        } else if ("u".equals(input)) {
            // Rotate   up (u) face itself counter-clockwise
            rotateFaceCounterClockwise('w'); // Assuming 'w' represents   up face
        
            // Temporarily store   top row of   front face to move it later to   left
            char[] tempFront = new char[3];
            for (int i = 0; i < 3; i++) {
                tempFront[i] = faces.get('g')[0][i];
            }
        
            // Move   top row of   right (r) face to   top row of   front (g) face
            for (int i = 0; i < 3; i++) {
                faces.get('g')[0][i] = faces.get('r')[0][i];
            }
        
            // Move   top row of   back (b) face to   top row of   right (r) face
            for (int i = 0; i < 3; i++) {
                faces.get('r')[0][i] = faces.get('b')[0][i];
            }
        
            // Move   top row of   left (o) face to   top row of   back (b) face
            for (int i = 0; i < 3; i++) {
                faces.get('b')[0][i] = faces.get('o')[0][i];
            }
        
            // Restore   top row of   front (g) face to   top row of   left (o) face
            for (int i = 0; i < 3; i++) {
                faces.get('o')[0][i] = tempFront[i];
            }
        } else if ("b".equals(input)) {
            // Temporarily store   top row of   up (w) face
            char[] tempUp = new char[3];
            for (int i = 0; i < 3; i++) {
                tempUp[i] = faces.get('w')[0][i]; // Top row of   up (w) face
            }
        
            // Move   left column of   right (r) face to   top row of   up (w) face
            for (int i = 0; i < 3; i++) {
                faces.get('w')[0][i] = faces.get('r')[2 - i][2];
            }
        
            // Move   bottom row of   down (y) face to   left column of   right (r) face
            for (int i = 0; i < 3; i++) {
                faces.get('r')[i][2] = faces.get('y')[2][i];
            }
        
            // Move   right column of   left (o) face to   bottom row of   down (y) face
            for (int i = 0; i < 3; i++) {
                faces.get('y')[2][i] = faces.get('o')[2 - i][0];
            }
        
            // Restore   top row of   up (w) face to   right column of   left (o) face
            for (int i = 0; i < 3; i++) {
                faces.get('o')[i][0] = tempUp[i];
            }
            // Rotate   back (b) face itself clockwise
            rotateFaceClockwise('b');

        } else if ("r'".equals(input)) {
            // Rotate   right (r) face itself counter-clockwise
            rotateFaceCounterClockwise('r');
        
            // Temporarily store   right column of   white (w) face
            char[] tempWhiteRightColumn = new char[3];
            for (int i = 0; i < 3; i++) {
                tempWhiteRightColumn[i] = faces.get('w')[i][2];
            }
        
            // White's right column is replaced with   left column of   blue (b) face
            for (int i = 0; i < 3; i++) {
                faces.get('w')[i][2] = faces.get('b')[i][0];
            }
        
            //   left column of   blue (b) face is replaced by   right column of   yellow (y) face
            for (int i = 0; i < 3; i++) {
                faces.get('b')[i][0] = faces.get('y')[i][2];
            }
        
            //   right column of   yellow (y) face is replaced by   right column of   green (g) face
            for (int i = 0; i < 3; i++) {
                faces.get('y')[i][2] = faces.get('g')[i][2];
            }
        
            // Finally,   right column of   green (g) face is replaced by   temporarily stored right column of   white (w) face
            for (int i = 0; i < 3; i++) {
                faces.get('g')[i][2] = tempWhiteRightColumn[i];
            }
        
        } else if ("b'".equals(input)) {
            // Rotate the back (b) face itself counter-clockwise
            rotateFaceCounterClockwise('b');
        
            // Temporarily store the right column of the red (r) face
            char[] tempRedRightColumn = new char[3];
            for (int i = 0; i < 3; i++) {
                tempRedRightColumn[i] = faces.get('r')[i][2];
            }
        
            // Move the top row of the white (w) face to the right column of the red (r) face
            for (int i = 0; i < 3; i++) {
                faces.get('r')[i][2] = faces.get('w')[0][i];
            }
        
            // Move the left column of the orange (o) face to the top row of the white (w) face
            for (int i = 0; i < 3; i++) {
                faces.get('w')[0][i] = faces.get('o')[i][0];
            }
        
            // Move the bottom row of the yellow (y) face to the left column of the orange (o) face, in reverse order
            for (int i = 0; i < 3; i++) {
                faces.get('o')[i][0] = faces.get('y')[2][2 - i];
            }
        
            // Restore the temporarily stored right column of the red (r) face to the bottom row of the yellow (y) face, in correct order
            for (int i = 0; i < 3; i++) {
                faces.get('y')[2][i] = tempRedRightColumn[2 - i];
            }
        }   
            
        
}

    // method to manage moves in stack
    private void addMove(String move) {
        // Check if   last three moves are   same as   curr move
        if (movesMade.size() >= 2 && movesMade.peek().equals(move) && movesMade.get(movesMade.size() - 2).equals(move)) {
            // Remove   last two moves, leaving one
            movesMade.pop(); 
            movesMade.pop(); 
            
            // Determine what the single move should be, if it's not an inverse, add its inverse
            String simplifiedMove = move.endsWith("'") ? move.substring(0, move.length() - 1) : move + "'";
            if (!movesMade.isEmpty() && movesMade.peek().equals(simplifiedMove)) {
                // If the simplified move is the same as the top of the stack, remove it instead of adding
                movesMade.pop();
            } else {
                // Otherwise, add the simplified move
                movesMade.push(simplifiedMove);
            }
        } else {
            movesMade.push(move);
        }
    }

    
    // method to get inverse move
    private String getInverseMove(String move) {
        return move.endsWith("'") ? move.substring(0, move.length() - 1) : move + "'";
    }

    // method that prints cube
    public void printCube() {
        // iterate through the faces and print them
        char[] order = {'w', 'r', 'y', 'b', 'g', 'o'};
        for (char faceKey : order) {
            char[][] face = faces.get(faceKey);
            System.out.println("Face: " + faceKey);
            for (char[] row : face) {
                for (char cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    
    // method to print the solution commamds
    public void printSolutionCommands() {
        System.out.println("Solution commands:");
        // iterate through the moves made and print the inverse of the moves
        while (!movesMade.isEmpty()) {
            String move = movesMade.pop();
            System.out.print(getInverseMove(move) + " ");
        }
        System.out.println();
    }

    // method to rotate face clockwise
    private void rotateFaceClockwise(char face) {
        // get original face and create new face
        char[][] originalFace = faces.get(face);
        char[][] rotatedFace = new char[3][3];
        // rotate face
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rotatedFace[j][2 - i] = originalFace[i][j];
            }
        }
        // put face in map
        faces.put(face, rotatedFace);
    }
    // method to rotate face counter clockwise
    private void rotateFaceCounterClockwise(char face) {
        char[][] originalFace = faces.get(face);
        char[][] rotatedFace = new char[3][3];
        // rotate face
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rotatedFace[2 - j][i] = originalFace[i][j];
            }
        }
        // put face in map
        faces.put(face, rotatedFace);
    }


    private String generateRandomScramble(int length) {
        String[] possibleMoves = {"R", "R'", "L", "L'", "U", "U'", "D", "D'", "F", "F'", "B", "B'"};
        StringBuilder scramble = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int moveIndex = (int) (Math.random() * possibleMoves.length);
            scramble.append(possibleMoves[moveIndex]).append(" ");
        }
        return scramble.toString().trim();
    }

    // configure cube and add move to stack
    public void move(String command) {
        System.out.println("Applying move: " + command); // Debugging line
        configCube(command);
        addMove(command);
    }
    
    
    // main
    public static void main(String[] args) {
        RubiksCubeSimulator simulator = new RubiksCubeSimulator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 'random' for a random scramble or 'custom' to enter your own moves:");
        String choice = scanner.nextLine().trim().toLowerCase();

        if ("random".equals(choice)) {
            // Generate a random scramble of a specified length (e.g., 25 moves)
            String randomScramble = simulator.generateRandomScramble(10);
            System.out.println("Random Scramble: " + randomScramble);

            // Apply the random scramble to the cube
            String[] moves = randomScramble.split("\\s+");
            for (String move : moves) {
                simulator.move(move);
            }

            // Display the cube state after applying the random scramble
            System.out.println("Scrambled Cube:");
            simulator.printCube();
        } else if ("custom".equals(choice)) {
            // Allow the user to enter custom moves
            System.out.println("Enter moves (R, R', L, L', U, U', D, D', F, F', B, B') or 'solve' to end:");
            while (scanner.hasNextLine()) {
                String move = scanner.nextLine().trim();
                if ("solve".equalsIgnoreCase(move)) {
                    simulator.printSolutionCommands();
                    break;
                } else if (!move.isEmpty()) {
                    simulator.move(move);
                    simulator.printCube(); // Display the cube after each move
                }
                System.out.println("Enter next move or 'solve' to display the solution:");
            }
        } else {
            System.out.println("Invalid choice. Exiting.");
        }

        scanner.close();
    }
 
    

    }
