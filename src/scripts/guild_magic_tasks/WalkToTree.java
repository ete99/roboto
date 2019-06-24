package scripts.guild_magic_tasks;


import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Player;
import scripts.SetUp.State;
import scripts.utility.Task;

import static scripts.Constants.*;
import static scripts.quest101.setUp;
import static scripts.script.Util.Chop;
import static scripts.script.Util.walkToTree;

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

        return !ctx.inventory.isFull() && setUp.STATE != State.CHOPPING && ctx.objects.select().name(setUp.TREE_NAME).size()>0 && Tree.tile().distanceTo(player)>6;// && !MAGIC_AREA1.contains(player) && !MAGIC_AREA2.contains(player);
    }


    @Override
    public void execute() {
        ctx.camera.turnTo(Tree.tile(), 35);
//        System.out.println("Walking to Tree");
        if(!Tree.inViewport()) {
            walkToTree();
            if(ctx.players.local().inMotion())
                setUp.STATE = State.WALKING;
        } else {
            Chop(Tree);
        }
    }
}

