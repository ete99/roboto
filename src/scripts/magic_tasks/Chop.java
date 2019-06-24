package scripts.magic_tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.BasicQuery;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import scripts.utility.Task;

import static scripts.quest101.setUp;
import static scripts.script.Util.Chop;


public class Chop extends Task {
    public Chop(ClientContext ctx) {
        super(ctx);
    }
    GameObject Tree;

    @Override
    public boolean activate() {
        BasicQuery<GameObject> trees = ctx.objects.select().name(setUp.TREE_NAME);

        Tree = trees.nearest().poll();
//        System.out.println(Tree.valid());
        return ctx.players.local().animation()==-1 && ctx.inventory.select().count()<28 && trees.size()>0;
    }

    @Override
    public void execute() {
//        System.out.println("Chopping");
        ctx.camera.turnTo(Tree.tile(), 35);
        Chop(Tree);
    }
}
