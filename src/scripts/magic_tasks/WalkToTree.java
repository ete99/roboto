package scripts.magic_tasks;


import org.powerbot.script.Random;
import org.powerbot.script.rt4.*;
import scripts.SetUp.State;
import scripts.utility.Task;

import java.util.Arrays;

import static scripts.Constants.*;
import static scripts.quest101.setUp;
import static scripts.script.AntibanScript.antibanned;
import static scripts.script.Util.Chop;

public class WalkToTree extends Task {
//    static Tile  tiles[] = { new Tile(3172, 3426, 0), new Tile(3167, 3417, 0)};
//    Area TreeArea =  new Area(new Tile(3172, 3424), new Tile(3158, 3400));
//    Area bankArea =  new Area(new Tile(3180, 3446), new Tile(3185, 3434));
    public WalkToTree(ClientContext ctx) {
        super(ctx);
    }
    GameObject Tree;
    Boolean A1, A2;
    Player player;
    @Override
    public boolean activate() {
        Tree = ctx.objects.select().name(setUp.TREE_NAME).nearest().poll();
        player = ctx.players.local();
        A1=MAGIC_AREA1.contains(Tree) && MAGIC_AREA1.contains(player);
        A2=MAGIC_AREA2.contains(Tree) && MAGIC_AREA2.contains(player);

        return !ctx.inventory.isFull() && setUp.STATE != State.CHOPPING &&  !(A1 || A2) && ctx.objects.select().name(setUp.TREE_NAME).size()>0 && Tree.tile().distanceTo(player)>6;// && !MAGIC_AREA1.contains(player) && !MAGIC_AREA2.contains(player);
    }


    @Override
    public void execute() {
//        System.out.println("Walking to Tree");
        LocalPath toTree = findPath(Tree);
        //@TODO see if can create a inf loop
//        System.out.println(toTree.valid()+" "+ toTree.traverse());
        if(!Tree.inViewport() || !MAGIC_FULL_AREA.contains(player)) {
            toTree.traverse();
            antibanned(0);
        } else {
            Chop(Tree);
        }
        //@TODO if just chopped, go to the other less recent area while waiting
    }

    public static LocalPath findPath(GameObject OBJ) {
        boolean[][] m = new boolean[5][5];
        int x, y;
        x = Random.nextInt(-2, 3);
        y = Random.nextInt(-2, 3);
        LocalPath toOBJ = setUp.ctx.movement.findPath(OBJ.tile().derive(x, y));
        int toomuch=0;
        while (!toOBJ.valid() && toomuch<500) {
            toomuch++;
            m[2 + x][2 + y] = true;
            x = Random.nextInt(-2, 3);
            y = Random.nextInt(-2, 3);
            if (!m[2 + x][2 + y])
                toOBJ = setUp.ctx.movement.findPath(OBJ.tile().derive(x, y));
        }
        return toOBJ;
    }
}

