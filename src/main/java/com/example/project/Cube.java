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
