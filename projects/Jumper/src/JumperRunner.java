/**
 * Author: Lee Ann J
 * Last updated: 2022.10.1
 * show Jumper Class for Part3-Group Activity
 * */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

public class JumperRunner {
    public static void main(String[] args){
        ActorWorld world=new ActorWorld();
//      基础设定
        world.add(new Jumper());
        world.add(new Rock());
        world.add(new Flower());

        world.show();
    }
}
