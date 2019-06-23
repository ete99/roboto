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
//        EdgeCheck();
        BasicQuery<GameObject> trees = ctx.objects.select().name(setUp.TREE_NAME).within(setUp.TREE_AREA).nearest();
        Tree = trees.poll();
        return ctx.players.local().animation()==-1 && ctx.inventory.select().count()<28 && !ctx.players.local().inMotion() && trees.size()>0;
    }

    @Override
    public void execute() {
//        System.out.println("Chopping");
        Chop(Tree);
    }
}
