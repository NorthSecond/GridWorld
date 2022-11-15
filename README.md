


***

****
## 目录

* GridWorld简介
* 正文部分
    * Part 1
      * Exercises 1
    * Part 2
        * Exercises 2
            * CircleBox
            * SpiralBug
            * ZBug
            * DancingBug
            * BugRunner总结
    * Part 3
        * Group Activity
            * 讨论与设计
            * Jumper
            * Test
  
---

# GridWorld简介

**gridworld案例**研究提供了一个图形环境。其中**视觉对象**居住在一个**二维网格**中并相互作用。
该案例允许设计和创建**actor**对象，将它们添加到**grid**中，并确定参与者是否按照它们的规范进行行为。它提供了一个显示grid和actor的**图形用户界面（GUI）**。
此外，GUI还具有一个可以向网格中添加actor和在它们上调用方法的工具。


		Part 1:	Provides experiments to observe the attributes and behavior of the actors.
		
		Part 2: Defines Bug variations.
		
		Part 3: Explores the code that is needed to understand and create actors.
		
		Part 4: Defines classes that extend the Critter class.
		
		Part 5: (CS AB only) Explains grid data structures.

# 正文部分

### part 1


#### Exercises


1.使用setDirection method并完成表格，并给出每个输入代表的方向


| Degrees | Compass Direction |
|---|---|
0 | North
45|NorthEast
90|East
135|SouthEast
180|South
225|SouthWest
270|West
315|NorthWest
360|North

2.使用moveTo方法将Bug移动到不同的位置。你可以向哪个方向移动它？你能把它移多远？如果你将bug移出网格会发生什么？

	>可以使用moveTo方法将bug移动到任何有效位置。

	>当使用moveTo方法移动bug时，bug不会改变它原来的方向。

	>必须使用setDirection方法或turn方法来改变bug的方向

	>尝试将bug移动出grid外时，将会造成IllegalArgumentException


3.用什么方法可以改变bug，花和石头的颜色？


	setColor方法
4.将石头移动到bug上，在将石头移开，会发生什么？

	>当一块石头移动到bug上时，bug将会消失。

	>只剩下石头，再将石头移动到其他位置时，bug就不在那里了

	>在网格的任意地方将一个actor移动到另一个actor的位置时，原位置的actor就会消失

### Part 2
#### Exercises

##### CircleBug

1.编写一个与BoxBug相同的类CircleBug，在act方法中调用一次而不是两次turn方法。它的行为与BoxBug有什么不同？

**代码传送门：**  [CircleBug.java](https://github.com/NorthSecond/GridWorld/blob/main/projects/boxBug/CircleBug.java)

CircleBugRunner是一个运行容器可以生成有circlebug的grid

[CircleBugRunner.java](https://github.com/NorthSecond/GridWorld/blob/main/projects/boxBug/CircleBug.java)

**运行截图**

![img.png](img.png)

**代码片：**
Class CircleBug

```java
public class CircleBug extends Bug {
    private int steps;
    private final int sideLength;

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public CircleBug(int length) {
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the square.
     * Except that in the <code>act</code> method the <code>turn</code> method
     * is called once instead of twice.
     */
    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else {
            turn();
            steps = 0;
        }
    }
}

```

CicleBug 的路径是一个八边形而不是一个正方形


##### SpiralBug

2.模仿BoxBug写一个SpiralBug使Bug延螺旋形状前行，当Bug转动时调整边长。
**代码传送门：**[SpiralBug.java](https://github.com/NorthSecond/GridWorld/blob/main/projects/boxBug/SpiralBug.java)
SpiralBugRunner可以生成SpiralBug的网格世界
[SpiralBugRunner.java](https://github.com/NorthSecond/GridWorld/blob/main/projects/boxBug/SpiralBugRunner.java)

**运行截图**

![img_1.png](img_1.png)

**代码片：**
Class SpiralBug
```java

public class SpiralBug extends Bug {
    private int steps;
    private int sideLength;

    /**
     * Constructs a spiraled bug
     * @param length the side length
     */
    public SpiralBug(int length) {
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the square.
     * <p>
     * Adjust the side length when the bug turns so that the
     * bug can drop flowers in a spiral pattern
     */
    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else {
            turn();
            turn();
            steps = 0;
            sideLength++;
        }
    }
}

```

##### ZBug


3.编写一个ZBug，让bug延“ **Z** ”字移动，从左上角开始，完成一个Z字型
路径后停止移动，在构造函数中提供**Z**的参数。

**notice**:ZBug运行时，Bug必须面向东（→）


**代码传送门：**

[ZBug.java](https://github.com/NorthSecond/GridWorld/blob/main/projects/boxBug/ZBug.java)

ZBugRunner可以生成ZBug的网格世界

[ZBugRunner.java](https://github.com/NorthSecond/GridWorld/blob/main/projects/boxBug/ZBugRunner.java)


**运行截图**

![img_2.png](img_2.png)

**代码片：**
Class ZBug

```java
public class ZBug extends Bug {
    private int steps;
    private final int sideLength;

    private boolean flag;

    /**
     * Constructs a Z bug that traces a "Z" of a given side length
     * @param length the side length
     */
    public ZBug(int length) {
        steps = 0;
        sideLength = length;
        this.setDirection(90);
        flag = false;
    }

    /**
     * Moves to the next location of the "Z".
     */
    public void act() {

        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else if (steps == sideLength) {
            if (flag) {
                return;
            } else if (this.getDirection() == 90) {
                this.setDirection(225);
            } else if (this.getDirection() == 225) {
                flag = true;
                this.setDirection(90);
            }
            steps = 0;
        }
    }
}


```

##### DancingBug

4.写一个DancingBug类，通过在每次移动前朝不同方向转向实现“**dancing**”

它的构造函数有一个整数数组作为参数，数组中的整数表示Bug在每次移动前turn的次数

每次turn默认为顺时针45 degrees

每次Bug移动前都会按照数组中的条目转动角度，移动后将会继续按照下一个条目转动角度

当执行完最后一次转弯后将会以初始数组值继续移动，使Bug不断重复相同的Dance移动

DancingBugRunner类会创建这一数组，并将其作为一个参数传递给DancingBug的构造函数


**代码传送门：**

[DancingBug.java](https://github.com/NorthSecond/GridWorld/blob/main/projects/boxBug/DancingBug.java)

DanceBugRunner可以生成DancingBug的网格世界

[DancingBugRunner.java](https://github.com/NorthSecond/GridWorld/blob/main/projects/boxBug/DancingBugRunner.java)

**运行截图**

![img_3.png](img_3.png)

**代码片：**
Class DancingBug

```java

public class DancingBug extends Bug {
    // the array of the number of turns when acting
    private final int[] turnArray;
    private int steps;
    private final int sideLength;
    // the times that the bug has acted.
    private int turnIndex;

    /**
     * Constructs a box bug that traces a square of a given side length.
     * @param length the side length
     * @param turns  the array of the number of turns when acting
     */
    public DancingBug(int[] turns, int length) {
        steps = 0;
        sideLength = length;
        turnArray = turns;
        turnIndex = 0;
    }

    /**
     * Constructs a box bug that traces a square of a given side length.
     * <p>
     * In this function, the length = 1.
     * @param turns the array of the number of turns when acting
     */
    public DancingBug(int[] turns) {
        this(turns, 1);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else {
            for (int i = 0; i < turnArray[turnIndex]; i++) {
                turn();
            }
            turnIndex = (turnIndex + 1) % turnArray.length;
            steps = 0;
        }
    }
}

```

##### BugRunner总结

5.学习BugRunner类的代码，总结向网格世界中添加如BoxBug对象的方法。

    >创建一个BoxBug对象

        BoxBug bbug = new BoxBug(2);

    >将新建的BoxBug对象添加到网格世界的指定位置中

        world.add(new Location(5,5) , bbug);


### Part 3
****
####Group Activity

* 小组完成一个名为 **Jumper** 的类 ， 它可以让actor每次移动向前移动两个单元格。
当遇到岩石和花时可以跳过，跳跃时不会留下任何东西

****

* **小组讨论并解决了如下问题**
  
    * 如果Jumper的前一格为空，但是前两格位置有花或者石头。
      
      * 顺时针转 45 度
        
    * 如果Jumper的前两格位置不在Grid中。
      
      * 顺时针转 45 度
      
    * 如果Jumper面对Grid边缘。
      
      * 顺时针转 45 度
      
    * 如果Jumper的前两格位置处有两一个actor。
      
      * 部分实例中会移除原位置的actor
      
    * 如果Jumper在路径上遭遇另一个jumper。
      
      * 部分实例中会移除一个Jumper
      
    * 其他测试
    
---

* **对Jumper类设计决策**
  
    * Jumper应该继承哪个类
      
      * Jumper定义为一种新的Bug，所以可能会继承Bug类
        
    * 是否有和Jumper相似的类
      
      * Bug类与Jumper类相似，有很多相近的methods
      
    * 是否需要构造函数，具体需要哪些参数
      
      * 如果有需要将多个Jumper放入一个Grid，为了便于区分可以在构造函数中添加颜色参数
      
    * 哪些 methods 需要重写
      
      * act 为了让Jumper的行为和Actor不同
      
    * 可能会需要添加哪些新的 methods
      
      * 类似于Bug中的move和canmove函数，Jumper中需要写新的Jump和Jumper函数
      
    * 如何测试该类
      
      * 按照上文中讨论的问题测试 
    
**代码传送**

[Jumper.java](https://github.com/NorthSecond/GridWorld/blob/main/projects/Jumper/Jumper.java)

JumperRunner可以生成Jumper的网格世界

[JumperRunner.java](https://github.com/NorthSecond/GridWorld/blob/main/projects/Jumper/JumperRunner.java)

JumperTest是对Jumper类的测试代码

[JumperTest.java](https://github.com/NorthSecond/GridWorld/blob/main/projects/Jumper/JumperTest.java)


**运行截图**

![img_4.png](img_4.png)

---
**Jumper代码片**

```java
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


```

**测试截图**

![img_5.png](img_5.png)

**测试代码片（JumperTest）**

```java
import static org.junit.Assert.assertEquals;

public class JumperTest {
    private int number = 8;
    private Jumper[] jmps = new Jumper[number];//用来测试的各种Jumper

    @Before
    public void setUp() {
        ActorWorld world = new ActorWorld();
        for (int i = 0; i < number; i++) {
            jmps[i] = new Jumper();
        }
//      基本设置
        world.add(new Location(4, 1), new Flower());
        world.add(new Location(3, 6), new Flower());
        world.add(new Location(4, 2), new Rock());
        world.add(new Location(4, 3), new Bug());

//      放置jumper
        world.add(new Location(1, 0), jmps[0]); // 测试距离边界只有一格的情况
        world.add(new Location(0, 1), jmps[1]); // 测试在边界时的情况
        world.add(new Location(5, 0), jmps[2]); // 前方第二格为空，第一格为空
        world.add(new Location(5, 1), jmps[3]); // 前方第二格为空，第一格为flower
        world.add(new Location(5, 2), jmps[4]); // 前方第二格为空，第一格为rock
        world.add(new Location(5, 3), jmps[5]); // 前方第二格为空，第一格为bug
        world.add(new Location(5, 6), jmps[6]); // 测试前方第二格有flower时的情况
        world.add(new Location(7, 6), jmps[7]); // 测试前方第二格有其他actor时的情况
        world.show();
    }

    //  下面是测试各种jumper能不能移动的情况
    @Test
    public void testJumper0() {
        assertEquals(jmps[0].canMove(), false);
    }

    @Test
    public void testJumper1() {
        assertEquals(jmps[1].canMove(), false);
    }

    @Test
    public void testJumper2() {
        assertEquals(jmps[2].canMove(), true);
    }

    @Test
    public void testJumper3() {
        assertEquals(jmps[3].canMove(), true);
    }

    @Test
    public void testJumper4() {
        assertEquals(jmps[4].canMove(), true);
    }

    @Test
    public void testJumper5() {
        assertEquals(jmps[5].canMove(), false);
    }

    @Test
    public void testJumper6() {
        assertEquals(jmps[6].canMove(), true);
    }

    @Test
    public void testJumper7() {
        assertEquals(jmps[7].canMove(), false);
    }
}


```
