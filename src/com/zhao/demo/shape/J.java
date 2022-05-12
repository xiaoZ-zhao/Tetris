package com.zhao.demo.shape;

import com.zhao.demo.App.Tetris;
import com.zhao.demo.block.Cell;
import com.zhao.demo.block.Tetromino;

/**
 * @author xiaoZhao
 * @date 2022/5/11
 * @describe
 */
public class J extends Tetromino {
    public J() {
        cells[0] = new Cell(0,4, Tetris.J);
        cells[1] = new Cell(0,3, Tetris.J);
        cells[2] = new Cell(0,5, Tetris.J);
        cells[3] = new Cell(1,5, Tetris.J);
    }
}
