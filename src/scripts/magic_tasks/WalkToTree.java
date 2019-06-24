package scripts.magic_tasks;


import org.powerbot.script.rt4.*;
import scripts.SetUp;
import scripts.SetUp.State;
import scripts.utility.Task;


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
        ctx.camera.turnTo(Tree.tile(), 35);
//        System.out.println("Walking to Tree");
//        LocalPath toTree = findPath(Tree);
        //@TODO see if can create a inf loop
//        System.out.println(toTree.valid()+" "+ toTree.traverse());
        if(!Tree.inViewport() || !MAGIC_FULL_AREA.contains(player)) {
            System.out.println("move: "+ctx.movement.step(Tree.tile()));
            if(ctx.players.local().inMotion())
                setUp.STATE = SetUp.State.WALKING;
            antibanned(0);
        } else {
            Chop(Tree);
        }
        //@TODO if just chopped, go to the other less recent area while waiting
    }
}

