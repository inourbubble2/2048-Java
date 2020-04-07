package TZFE;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TZFE t = new TZFE();
        t.init_T();
        t.print_T();

        char ch = 0;
        while(ch != 'q' && ch != 'Q') {
            ch = (char) System.in.read();
            System.in.read();
            if (ch == 10) {
                ch = (char) System.in.read();
            } else {
                switch(ch) {
                    case 'w':               // UP
                    case 'W':
                        t.move((byte) 2);
                        break;
                    case 'a':               // LEFT
                    case 'A':
                        t.move((byte) 0);
                        break;
                    case 's':
                    case 'S':               // DOWN
                        t.move((byte) 3);
                        break;
                    case 'd':
                    case 'D':               // RIGHT
                        t.move((byte) 1);
                        break;
                    default:
                        System.out.println("w - UP, a - LEFT, s - DOWN, d = RIGHT, q = QUIT");
                        break;
                }
            }
        }

    }
}
