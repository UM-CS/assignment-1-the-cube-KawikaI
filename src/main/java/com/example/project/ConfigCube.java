// this is the central method to cube.java. it manipulates the cube based on the input

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
