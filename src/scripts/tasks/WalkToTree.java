package scripts.tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import scripts.Constants;
import scripts.SetUp.*;
import scripts.Task;

import java.util.concurrent.Callable;

import static scripts.quest101.setUp;
import static scripts.script.Util.*;

public class WalkToTree extends Task {
//    static Tile  tiles[] = { new Tile(3172, 3426, 0), new Tile(3167, 3417, 0)};
//    Area TreeArea =  new Area(new Tile(3172, 3424), new Tile(3158, 3400));
//    Area bankArea =  new Area(new Tile(3180, 3446), new Tile(3185, 3434));
    public WalkToTree(ClientContext ctx) {
        super(ctx);
    }

    void openDoor(){
        final GameObject DOOR = ctx.objects.select().id(1543).nearest().poll();
        DOOR.click();
        if(DOOR.valid()) {
            DOOR.interact("Open");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return DOOR.id()==1544;
                }
            }, 400, 4);
        }
    }

    @Override
    public boolean activate() {
        return !ctx.inventory.isFull() && !setUp.TREE_AREA.contains(ctx.players.local()) && setUp.STATE !=State.CHOPPING;
    }

    @Override
    public void execute() {
//        System.out.println("Walking to Trees");
        Condition.sleep(Random.nextInt(250, 500));
//        if(setUp.STATE != State.IDLE && ctx.objects.select().name(setUp.TREE_NAME).nearest().poll().inViewport())
        if(!(setUp.TREE_AREA.contains(ctx.players.local())))
            openDoor();
        GameObject Tree = ctx.objects.select().name(setUp.TREE_NAME).nearest().poll();
        if (Tree.inViewport() && ctx.objects.select().id(1544).nearest().poll().valid() && ctx.players.local().animation()!=-1){
            Chop(Tree);
//            AntibanScript.moveMouseOffScreen(ctx,0);
        }else {
            walkToTree();
            if (setUp.ctx.players.local().animation() == Constants.RUN_ANIM)
                setUp.STATE = State.WALKING;
            Condition.sleep(Random.nextInt(250, 500));
        }
    }
}

