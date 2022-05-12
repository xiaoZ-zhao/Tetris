package com.zhao.demo.shape;

import com.zhao.demo.App.Tetris;
import com.zhao.demo.block.Cell;
import com.zhao.demo.block.Tetromino;

/**
 * @author xiaoZhao
 * @date 2022/5/11
 * @describe
 */
public class Z extends Tetromino {
    public Z() {
        cells[0] = new Cell(1,4, Tetris.Z);
        cells[1] = new Cell(0,3, Tetris.Z);
        cells[2] = new Cell(0,4, Tetris.Z);
        cells[3] = new Cell(1,5, Tetris.Z);

        //共有两种旋转状态
        states =new State[2];
        //初始化两种状态的相对坐标
        states[0]=new State(0,0,-1,-1,-1,0,0,1);
        states[1]=new State(0,0,-1,1,0,1,1,0);
    }
}
