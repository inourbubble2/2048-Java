package TZFE;

import java.util.ArrayList;

import static java.lang.System.exit;

public class TZFE {
    static int LEN = 4;
    static int[][] ARR;
    static int START_NUM = 2;
    static int count = 0;

    // 숫자 배열 초기화 메소드
    // 모든 배열의 원소를 0으로 초기화 한 뒤,
    // 랜덤한 위치에 2를 삽입한다.
    void init_T() {
        ARR = new int[LEN][LEN];
        int i, j, random_i, random_j;

        for(i = 0; i < LEN; i++)
            for(j = 0; j < LEN; j++)
                ARR[i][j] = 0;

        random_i = (int) (Math.random() * 10) % 4;
        random_j = (int) (Math.random() * 10) % 4;
        ARR[random_i][random_j] = START_NUM;
    }

    // 숫자 배열 출력 메소드
    // 값이 0일 경우, 아무 데이터가 없는 것으로 간주
    void print_T() {
        int i, j;

        System.out.println("============" + " 점수 : " + count);
        for(i = 0; i < LEN; i++) {
            for(j = 0; j < LEN; j++) {
                if(ARR[i][j] == 0)
                    System.out.print(" * ");
                else
                    System.out.print(" " + ARR[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("============");
    }

    // 랜덤한 숫자(2, 4) 추가 메소드
    void add_random_num() {
        int i, j, x, y, num, random_num;
        String stringIJ;
        ArrayList<String> empty_indexes = new ArrayList<String>();

        // 넣을 값 지정
        // 랜덤으로 2나 4 추가
        int random = (int) (Math.random() * 10) % 2;
        if(random == 0)
            num = START_NUM;
        else
            num = START_NUM * 2;

        // 비어있는 인덱스를 empty_indexes 에 넣기
        for(i = 0; i < LEN; i++)
            for(j = 0; j < LEN; j++)
                if(ARR[i][j] == 0)
                    empty_indexes.add(Integer.toString(i) + Integer.toString(j));


        if(empty_indexes.size() == 0) {
            System.out.println("꽉 참");
            return;
        }
        // empty_indexes 의 랜덤한 값의 인덱스에
        // 2나 4 삽입
        random_num = (int) (Math.random() * 10) % empty_indexes.size();
        stringIJ = empty_indexes.get(random_num);
        x = Character.getNumericValue(stringIJ.charAt(0));
        y = Character.getNumericValue(stringIJ.charAt(1));
        ARR[x][y] = num;

        count += num;
    }

    // before 값을 after에 더하기
    private void add_num(int beforeY, int beforeX, int afterY, int afterX) {
        ARR[afterY][afterX] += ARR[beforeY][beforeX];
        ARR[beforeY][beforeX] = 0;
    }

    private boolean is_full() {
        int i, j;

        for(i = 0; i < LEN - 1; i++) {
            for(j = 0; j < LEN - 1; j++) {
                if(ARR[i][j] == 0
                        || ARR[i][j + 1] == 0
                        || ARR[i + 1][j] == 0
                        || ARR[i + 1][j + 1] == 0) {
                    return true;
                }
                if(ARR[i][j] == ARR[i][j + 1]) {
                    return true;
                }
                if(ARR[i][j] == ARR[i + 1][j]) {
                    return true;
                }
                if(ARR[i][j + 1] == ARR[i + 1][j + 1]) {
                    return true;
                }
            }
        }
        System.out.println("=== 더이상 움직일 수 없습니다. ===");
        System.out.println("=== 점수 : " + count + " ===");
        exit(0);
        return false;
    }

    // 움직이기
    void move(byte direction) {
        switch(direction) {
            case 0:     //LEFT
                is_full();
                moveLeft();
                add_random_num();
                print_T();
                break;
            case 1:     //RIGHT
                is_full();
                moveRight();
                add_random_num();
                print_T();
                break;
            case 2:     //UP
                is_full();
                moveUp();
                add_random_num();
                print_T();
                break;
            case 3:     //DOWN
                is_full();
                moveDown();
                add_random_num();
                print_T();
                break;
        }
    }

    // 왼쪽으로 움직이기
    void moveLeft() {
        int i, j, beforeX, beforeY, afterX, afterY;

        //오른쪽부터 탐색
        for(i = 0; i < LEN; i++) {
            for(j = LEN - 1; j > 0; j--) {
                // 현재 원소가 0이 아니고 이동할 자리가 있거나,
                // 왼쪽 원소와 같다면
                if((ARR[i][j] == ARR[i][j - 1]) || (ARR[i][j - 1] == 0 && ARR[i][j] != 0)) {
                    add_num(i, j, i, j - 1);
                }
            }
        }
        for(i = 0; i < LEN; i++) {
            for(j = LEN - 1; j > 0; j--) {
                // 원소가 0이 아닌데
                // 왼쪽 원소가 0인 것들 탐색 후 옮기기
                if(ARR[i][j - 1] == 0 && ARR[i][j] != 0) {
                    add_num(i, j, i, j - 1);
                }
            }
        }
    }

    // 오른쪽으로 움직이기
    void moveRight() {
        int i, j, beforeX, beforeY, afterX, afterY;

        // 왼쪽부터 탐색
        for(i = 0; i < LEN; i++) {
            for(j = 0; j < LEN - 1; j++) {
                // 현재 원소가 0이 아니고 이동할 자리가 있거나,
                // 현재 원소가 오른쪽 원소와 같다면
                if((ARR[i][j] == ARR[i][j + 1]) || (ARR[i][j + 1] == 0 && ARR[i][j] != 0)) {
                    add_num(i, j, i, j + 1);
                }
            }
        }
        for(i = 0; i < LEN; i++) {
            for(j = 0; j < LEN - 1; j++) {
                // 원소가 0이 아니고
                // 오른쪽 원소가 0인 것들 탐색 후 옮기기
                if(ARR[i][j + 1] == 0 && ARR[i][j] != 0) {
                    add_num(i, j, i, j + 1);
                }
            }
        }
    }

    // 아래쪽으로 움직이기
    void moveDown() {
        int i, j, beforeX, beforeY, afterX, afterY;

        // 위쪽부터
        for(i = 0; i < LEN - 1; i++) {
            for(j = 0; j < LEN; j++) {
                // 현재 원소가 0이 아니고 이동할 자리가 있거나,
                // 현재 원소가 밑쪽 원소와 같다면
                if((ARR[i][j] == ARR[i + 1][j]) || (ARR[i + 1][j] == 0 && ARR[i][j] != 0)) {
                    add_num(i, j, i + 1, j);
                }
            }
        }

        // 빈 곳 체크
        for(i = 0; i < LEN - 1; i++) {
            for(j = 0; j < LEN; j++) {
                // 원소가 0이 아닌데
                // 밑쪽 원소가 0인 것들 탐색 후 옮기기
                if(ARR[i + 1][j] == 0 && ARR[i][j] != 0) {
                    add_num(i, j, i + 1, j);
                }
            }
        }
    }

    // 위쪽으로 움직이기
    void moveUp() {
        int i, j;

        // 아래쪽부터
        for(i = LEN - 1; i > 0; i--) {
            for(j = 0; j < LEN; j++) {
                // 현재 원소가 0이 아니고 이동할 자리가 있거나,
                // 현재 원소가 위쪽 원소와 같다면
                if((ARR[i][j] == ARR[i - 1][j]) || (ARR[i - 1][j] == 0 && ARR[i][j] != 0)) {
                    add_num(i, j, i - 1, j);
                }
            }
        }

        // 빈 곳 체크
        for(i = LEN - 1; i > 0; i--) {
            for(j = 0; j < LEN; j++) {
                // 원소가 0이 아닌데
                // 위쪽 원소가 0인 것들 탐색 후 옮기기
                if(ARR[i - 1][j] == 0 && ARR[i][j] != 0) {
                    add_num(i, j, i - 1, j);
                }
            }
        }
    }

}
