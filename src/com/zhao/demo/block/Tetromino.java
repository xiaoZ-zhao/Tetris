package com.zhao.demo.block;

import com.zhao.demo.shape.*;

/**
 * @author xiaoZhao
 * @date 2022/5/11
 * @describe 编写四方格父类
 */
public class Tetromino {

    public Cell[] cells = new Cell[4];

    //左移方法
    public void moveLeft() {
        for (Cell cell : cells) {
            cell.left();
        }
    }

    //右移方法
    public void moveRight() {
        for (Cell cell : cells) {
            cell.right();
        }
    }

    //单元格下落
    public void moveDrop() {
        for (Cell cell : cells) {
            cell.down();
        }
    }

    //编写随机生成四方格
    public static Tetromino randomOne() {
        int num = (int) (Math.random() * 7);
        Tetromino tetromino =null;
        switch (num){
            case 0:
                tetromino = new I();
                break;
            case 1:
                tetromino = new J();
                break;
            case 2:
                tetromino = new L();
                break;
            case 3:
                tetromino = new O();
                break;
            case 4:
                tetromino = new S();
                break;
            case 5:
                tetromino = new T();
                break;
            case 6:
                tetromino = new Z();
                break;
        }

        return tetromino;
    }
}
