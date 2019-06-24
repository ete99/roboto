package scripts.yew_tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.BasicQuery;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import scripts.SetUp;
import scripts.utility.Task;

import static scripts.quest101.setUp;
import static scripts.script.Util.*;


public class Chop extends Task {
    public Chop(ClientContext ctx) {
        super(ctx);
    }
    GameObject Tree;

    @Override
    public boolean activate() {
        BasicQuery<GameObject> trees = ctx.objects.select().name(setUp.TREE_NAME);
        final boolean b = ctx.players.local().animation()==-1 && ctx.inventory.select().count()<28 && !ctx.players.local().inMotion() && trees.size()>0;
        System.out.println(b);
        Tree = trees.nearest().poll();
        return b;
    }

    @Override
    public void execute() {
        System.out.println("Chopping");
        Condition.sleep(Random.nextInt(250, 350));
        ctx.camera.turnTo(Tree.tile(),35);
        Chop(Tree);
    }
}
