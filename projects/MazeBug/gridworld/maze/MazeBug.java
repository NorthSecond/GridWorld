package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next; // 下一步要行走的位置
	public Location last; // 上一步的位置
	public boolean isEnd=false; //记录是否到终点
	// 记录树的节点的栈
	public Stack<ArrayList<Location>> crossLocation =new Stack<ArrayList<Location>>();
	public Integer stepCount = 0; //记录从迷宫走到出口的步数
	public boolean hasShown = false; //结束后用来弹出窗口

	public boolean visit[][]; //访问过的位置？
	public ArrayList<Location> branch; // 栈顶节点和已经访问的路径节点
	public int []weight; // 四个方向的权重:东西南北

	/**
	 * Constructs a box bug that traces a square of a given side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);

		visit =new boolean[100][100]; //矩阵大小？
		for(int i=0;i<100;i++){
			for(int j=0;j<100;j++){
				visit[i][j]=false;
			}
		}
		//记录初始位置
		Location loc=getLocation();
		branch =new ArrayList<Location>();
		branch.add(loc);
		//四个方向的权重初始化为1
		weight=new int[4];
		for(int i=0;i<4;i++){
			weight[i]=1;
		}
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		boolean willMove = canMove();// 判断能不能继续走
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			visit[next.getRow()][next.getCol()]=true;
			move();
			//increase step count when move 
			stepCount++;
		}
		else {
			if(branch.isEmpty()){//无路可走
				branch=crossLocation.pop();
				Location loc=branch.get(branch.size()-1);
				int dir=getLocation().getDirectionToward(loc);
				if(dir==Location.WEST){
					weight[0]--;
				}
				else if(dir==Location.NORTH){
					weight[1]--;
				}
				else if(dir==Location.EAST){
					weight[2]--;
				}
				else if(dir==Location.SOUTH){
					weight[3]--;
				}
			}
			next=branch.remove(branch.size()-1);
			move();
			stepCount++;
		}
	}

	/**
	 * Find all positions that can be move to.
	 *
	 * 返回在loc处所有可以移动的位置
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid=new ArrayList<Location>(); // 可到达的位置
		// 枚举一下方向
		int[] direction={Location.NORTH,Location.WEST,Location.SOUTH,Location.EAST};

		for(int d:direction) { //对邻居位置依次判断
			Location neighbor = loc.getAdjacentLocation(getDirection() + d);
			if (gr.isValid(neighbor)) {
				Actor actor = gr.get(neighbor);
				if (actor == null || actor instanceof Flower && !visit[neighbor.getRow()][neighbor.getCol()]) {
					valid.add(neighbor);
				} else if (actor instanceof Rock) {
					if (actor.getColor().equals(Color.RED))
						isEnd = true;
				}
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		ArrayList<Location> locs=getValid(getLocation());
		if(locs.isEmpty()){
			return false;
		}
		else{
			branch.add(getLocation());
			if(locs.size()>1){
				crossLocation.push(branch);
				branch=new ArrayList<Location>();
				next=directionPrediction(locs);
			}
			else{
				next=locs.get(0);
			}
			last=getLocation();
		}
		return true;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		if(gr.isValid(next)){// 要判断一下是不是有效位置
			//移动到下一个位置
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		}
		else {
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}

	/**
	 * 增加方向的概率估计
	 * 向哪边移动，哪个方向的次数就+1
	 */
	public Location directionPrediction(ArrayList<Location> locs){
		int dir=0;
		int east=0,south=0,west=0,north=0;
		for(Location loc:locs){
			dir=getLocation().getDirectionToward(loc);
			if(dir==Location.EAST) {
				east=weight[0];
			}
			else if(dir==Location.SOUTH){
				south=weight[1];
			}
			else if(dir==Location.WEST){
				west=weight[2];
			}
			else{//NORTH
				north=weight[3];
			}
		}
		// 带权重随机算法决定方向
		int random=1+(int)(Math.random() * (east+south+west+north));
		if(random<=east){
			dir=Location.EAST;
			weight[0]++;
		}
		else if(random<=(east+south)){
			dir=Location.SOUTH;
			weight[1]++;
		}
		else if(random<=(east+south+west)){
			dir=Location.WEST;
			weight[2]++;
		}
		else{
			dir=Location.NORTH;
			weight[3]++;
		}

		// 确定下一步的位置
		//Location next=getLocation().getAdjacentLocation(dir);
		Location next=null;
		for(Location loc:locs){
			if(dir==getLocation().getDirectionToward(loc)){
				next=loc;
			}
		}
		return next;
	}
}
