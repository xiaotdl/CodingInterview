package design_snake_game;

import java.util.*;

/**
 * Created by Xiaotian on 2/23/18.
 */
public class Solution {
}

class SnakeGame {
    // 用deque，因为首尾都发生变化，deque add/remove O(1)
    // 用set，因为要记录所有身体的位置，set add/contains O(1)
    // move时间复杂度O(1)
    // 判断游戏结束也是O(1)
    // 空间复杂度，Deque大小为move的次数（用循环队列可以优化到width*height），Set最大为width*height。
    class Position {
        int x;
        int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    boolean gameOver;
    int width;
    int height;
    int[][] food;
    int foodCnt;
    Deque<Position> snakeQ;
    Set<Integer> snakeSet;

    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        gameOver = false;
        foodCnt = 0;
        snakeQ = new LinkedList<>();
        snakeQ.offerLast(new Position(0, 0));
        snakeSet = new HashSet<>();
        if (food.length > 0 && food[0][0] == 0 && food[0][1] == 0) foodCnt++;
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        if (gameOver) return -1;

        int dx = 0, dy = 0;
        switch(direction) {
            case "U": dx = -1; dy =  0; break;
            case "D": dx =  1; dy =  0; break;
            case "L": dx =  0; dy = -1; break;
            case "R": dx =  0; dy =  1; break;
            default: break;
        }

        Position head = snakeQ.peekLast();
        int x = head.x + dx;
        int y = head.y + dy;
        if (!(0 <= x && x < height && 0 <= y && y < width)) {
            // out of bounds
            gameOver = true;
            return -1;
        }

        Position newHead = new Position(x, y);
        snakeQ.offerLast(newHead);
        if (foodCnt < food.length && x == food[foodCnt][0] && y == food[foodCnt][1]) {
            foodCnt++;
        }
        else { // remove tail if didn't eat food
            Position tail = snakeQ.pollFirst();
            snakeSet.remove(tail.x * width + tail.y);
        }

        // check hitting body after removing tail, as the head might be chasing the tail
        if (snakeSet.contains(x * width + y)) return -1; // hit body
        snakeSet.add(x * width + y);

        return foodCnt;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
