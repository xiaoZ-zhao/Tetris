package com.zhao.demo.App;

import com.zhao.demo.block.Cell;
import com.zhao.demo.block.Tetromino;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author xiaoZhao
 * @date 2022/5/11
 * @describe 俄罗斯方块游戏主类
 */
public class Tetris extends JPanel {

    //正在下落的方块
    private Tetromino currentOne = Tetromino.randomOne();
    //将要下落的方块
    private Tetromino nextOne = Tetromino.randomOne();
    //游戏主区域
    private Cell[][] wall = new Cell[18][9];
    //声明单元格的值
    private static final int CELL_SIZE = 48;

    //游戏分数池
    int[] scores_pool = {0, 1, 2, 5, 10};
    //当前游戏的分数
    private int totalScore = 0;
    //当前消除的行数
    private int totalLine = 0;

    //游戏三种状态 游戏中、暂停、结束
    public static final int PLING = 0;
    public static final int STOP = 1;
    public static final int OVER = 2;
    //当前游戏状态值
    private int game_state;
    //显示游戏状态
    String[] show_state = {"P[pause]", "C[continue]", "S[replay]"};


    //载入方块图片
    public static BufferedImage I;
    public static BufferedImage J;
    public static BufferedImage L;
    public static BufferedImage O;
    public static BufferedImage S;
    public static BufferedImage T;
    public static BufferedImage Z;
    public static BufferedImage background;

    static {
        try {
            I = ImageIO.read(new File("images/I.png"));
            J = ImageIO.read(new File("images/J.png"));
            L = ImageIO.read(new File("images/L.png"));
            O = ImageIO.read(new File("images/O.png"));
            S = ImageIO.read(new File("images/S.png"));
            T = ImageIO.read(new File("images/T.png"));
            Z = ImageIO.read(new File("images/Z.png"));
            background = ImageIO.read(new File("images/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null);
        //平移坐标轴
        g.translate(22, 15);
        //绘制游戏主区域
        paintWall(g);
        //绘制正在下落的四方格
        paintCurrentOne(g);
        //绘制下一个将要下落的四方格
        paintNextOne(g);
        //绘制游戏得分
        paintSource(g);
        //绘制当前游戏状态
        paintState(g);
    }

    private void paintState(Graphics g) {
        if (game_state == PLING) {
            g.drawString(show_state[PLING], 500, 660);
        } else if (game_state == STOP) {
            g.drawString(show_state[STOP], 500, 660);
        } else {
            g.drawString(show_state[OVER], 500, 660);
            g.setColor(Color.RED);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
            g.drawString("GAME OVER!", 30, 400);
        }
    }

    private void paintSource(Graphics g) {
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        g.drawString("分数: " + totalScore, 500, 250);
        g.drawString("行数: " + totalLine, 500, 430);
    }

    private void paintNextOne(Graphics g) {
        Cell[] cells = nextOne.cells;
        for (Cell cell : cells) {
            int x = cell.getCol() * CELL_SIZE + 370;
            int y = cell.getRow() * CELL_SIZE + 25;
            g.drawImage(cell.getImage(), x, y, null);
        }
    }

    private void paintCurrentOne(Graphics g) {
        Cell[] cells = currentOne.cells;
        for (Cell cell : cells) {
            int x = cell.getCol() * CELL_SIZE;
            int y = cell.getRow() * CELL_SIZE;
            g.drawImage(cell.getImage(), x, y, null);
        }
    }

    private void paintWall(Graphics g) {
        for (int i = 0; i < wall.length; i++) {
            for (int j = 0; j < wall[i].length; j++) {
                int x = j * CELL_SIZE;
                int y = i * CELL_SIZE;
                Cell cell = wall[i][j];
                //判断是否有小方块
                if (cell == null) {
                    g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                } else {
                    g.drawImage(cell.getImage(), x, y, null);
                }
            }
        }
    }

    //判断是否出界
    public boolean outOFBounds() {
        Cell[] cells = currentOne.cells;
        for (Cell cell : cells) {
            int col = cell.getCol();
            int row = cell.getRow();
            if (row < 0 || row > wall.length - 1 || col <0 || col >wall[0].length){
                return true;
            }
        }
        return false;
    }

    //按键一次，左移一次
    public void moveleftActive(){
        currentOne.moveLeft();
        //判断是否越界或重合
        if (outOFBounds() || coincide()){
            currentOne.moveRight();
        }
    }

    //按键一次，左移一次
    public void moveRightActive(){
        currentOne.moveRight();
        //判断是否越界或重合
        if (outOFBounds() || coincide()){
            currentOne.moveLeft();
        }
    }

    //判断是否重合
    public boolean coincide(){
        Cell[] cells=currentOne.cells;
        for (Cell cell : cells) {
            int row =cell.getRow();
            int col=cell.getCol();
            if (wall[row][col] !=null){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("俄罗斯方块");
        //创建游戏界面
        Tetris panel = new Tetris();
        jFrame.add(panel);
        //设置可见
        jFrame.setVisible(true);
        //设置窗口大小
        jFrame.setSize(810, 940);
        //设置剧中
        jFrame.setLocationRelativeTo(null);
        //设置窗口关闭时停止
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
