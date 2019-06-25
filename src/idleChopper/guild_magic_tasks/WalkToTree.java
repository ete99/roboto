package idleChopper.guild_magic_tasks;


import idleChopper.script.AntibanScript;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Player;
import idleChopper.SetUp.State;
import idleChopper.utility.Task;

import static idleChopper.quest101.setUp;
import static idleChopper.script.Util.*;

public class WalkToTree extends Task {
//    static Tile  tiles[] = { new Tile(3172, 3426, 0), new Tile(3167, 3417, 0)};
//    Area TreeArea =  new Area(new Tile(3172, 3424), new Tile(3158, 3400));
//    Area bankArea =  new Area(new Tile(3180, 3446), new Tile(3185, 3434));
    public WalkToTree(ClientContext ctx) {
        super(ctx);
    }
    GameObject Tree;
    Player player;
    @Override
    public boolean activate() {
        Tree = ctx.objects.select().name(setUp.TREE_NAME).nearest().poll();
        player = ctx.players.local();

        return !ctx.inventory.isFull() && setUp.STATE != State.CHOPPING;// && !MAGIC_AREA1.contains(player) && !MAGIC_AREA2.contains(player);
    }


    @Override
    public void execute() {
//        System.out.println("Walking to Tree");
        if(!Tree.inViewport()) {
            walkToTree();
            if(ctx.players.local().inMotion())
                setUp.STATE = State.WALKING;
        } else {
            Chop(Tree);
            AntibanScript.moveMouseOffScreen(ctx,-1);
        }
    }
}

