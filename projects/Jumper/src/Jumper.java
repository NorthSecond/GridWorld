/**
 * Author: Lee Ann J
 * Last updated: 2022.10.1
 * Jumper Class for Part3-Group Activity
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

public class Jumper extends Bug {
    public Jumper() {
        setColor(Color.BLUE);
    }

    //   带颜色的声明方式
    public Jumper(Color JumperColor) {
        setColor(JumperColor);
    }

    //    一开始理解错了，其实它只要跳就行，不用管能不能前进一格
    @Override
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
//      获取当前位置
        Location loc = getLocation();
//      移动一次的位置
        Location moveNext = loc.getAdjacentLocation(getDirection());
//      出界判断
        if (!gr.isValid(moveNext)) {
            return false;
        }
//      跳跃一次的位置
        Location jumpNext = moveNext.getAdjacentLocation(getDirection());
        if (!gr.isValid(jumpNext)) {
            return false;
        }
//      判断移动的位置有没有可覆盖的Actor
        Actor moveNeighbor = gr.get(moveNext);
        Actor jumpNeighbor = gr.get(jumpNext);
//      可以跳过花和石头
        boolean jump = moveNeighbor == null || moveNeighbor instanceof Flower || moveNeighbor instanceof Rock;
//      为了可玩性强一点我还是设定可以覆盖花朵吧
        boolean move = jumpNeighbor == null || jumpNeighbor instanceof Flower;
        return move && jump;

    }

    @Override
    public void act() {
        if (canMove()) {
            move();
        } else {
            turn();
        }
    }

    @Override
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }

        Location loc = getLocation();
        Location move = loc.getAdjacentLocation(getDirection());
        Location jump = move.getAdjacentLocation(getDirection());
//      卡一下条件
        if (gr.isValid(move) && gr.isValid(jump)) {
            Actor jumpNeighbor = gr.get(jump);
            if (jumpNeighbor != null) {
                jumpNeighbor.removeSelfFromGrid();
            }
            moveTo(jump);
        }
//        else{
//            removeSelfFromGrid();
//        }
//      留一朵花花，但是好像也没必要
        Flower flw = new Flower(getColor());
        flw.putSelfInGrid(gr, loc);
    }

    //  每次转45度
    @Override
    public void turn() {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

}
