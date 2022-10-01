/**
 * Author: Lee Ann J
 * Last updated: 2022.10.1
 * for Part3-Group Activity
 * test Jumper class's action under some conditions
 * */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JumperTest {
    private int number=8;
    private  Jumper[] jmps=new Jumper[number];//用来测试的各种Jumper

    @Before
    public void setUp(){
        ActorWorld world =new ActorWorld();
        for(int i=0;i<number;i++){
            jmps[i]=new Jumper();
        }
//      基本设置
        world.add(new Location(4,1),new Flower());
        world.add(new Location(3,6),new Flower());
        world.add(new Location(4,2),new Rock());
        world.add(new Location(4,3),new Bug());

//      放置jumper
        world.add(new Location(1,0),jmps[0]); // 测试距离边界只有一格的情况
        world.add(new Location(0,1),jmps[1]); // 测试在边界时的情况
        world.add(new Location(5,0),jmps[2]); // 前方第二格为空，第一格为空
        world.add(new Location(5,1),jmps[3]); // 前方第二格为空，第一格为flower
        world.add(new Location(5,2),jmps[4]); // 前方第二格为空，第一格为rock
        world.add(new Location(5,3),jmps[5]); // 前方第二格为空，第一格为bug
        world.add(new Location(5,6),jmps[6]); // 测试前方第二格有flower时的情况
        world.add(new Location(7,6),jmps[7]); // 测试前方第二格有其他actor时的情况
        world.show();
    }
//  下面是测试各种jumper能不能移动的情况
    @Test
    public void testJumper0(){
        assertEquals(jmps[0].canMove(),false);
    }
    @Test
    public void testJumper1(){
        assertEquals(jmps[1].canMove(),false);
    }
    @Test
    public void testJumper2(){
        assertEquals(jmps[2].canMove(),true);
    }
    @Test
    public void testJumper3(){
        assertEquals(jmps[3].canMove(),true);
    }
    @Test
    public void testJumper4(){
        assertEquals(jmps[4].canMove(),true);
    }
    @Test
    public void testJumper5(){
        assertEquals(jmps[5].canMove(),false);
    }
    @Test
    public void testJumper6(){
        assertEquals(jmps[6].canMove(),true);
    }
    @Test
    public void testJumper7(){
        assertEquals(jmps[7].canMove(),false);
    }
}
