package scripts.magic_tasks;


import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Player;
import scripts.SetUp.State;
import scripts.utility.Task;

import static scripts.quest101.setUp;
import static scripts.script.Util.walkToTree;

public class WalkToArea extends Task {
//    static Tile  tiles[] = { new Tile(3172, 3426, 0), new Tile(3167, 3417, 0)};
//    Area TreeArea =  new Area(new Tile(3172, 3424), new Tile(3158, 3400));
//    Area bankArea =  new Area(new Tile(3180, 3446), new Tile(3185, 3434));
    public WalkToArea(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
//        System.out.println(ctx.objects.select().id(setUp.TREE_ID).size());
        return false;
//        return !ctx.inventory.isFull() && setUp.STATE != State.CHOPPING;// && ctx.objects.select().id(setUp.TREE_ID).size()<0;// && !MAGIC_AREA1.contains(player) && !MAGIC_AREA2.contains(player);
    }

    @Override
    public void execute() {
//        System.out.println("Walking to Trees");
        GameObject Tree = ctx.objects.select().name(setUp.TREE_NAME).nearest().poll();
            walkToTree();
//            if (Tree.inViewport() && ctx.players.local().animation() != -1) {
//                Chop(Tree);
////            AntibanScript.moveMouseOffScreen(ctx,0);
//            } else {
//                walkToTree();
////            if(!Tree.inViewport())
////                Condition.sleep(Random.nextInt(250, 500));
//            }
    }
}

