package scripts2.tasks;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import scripts2.Constants;
import scripts2.Task;

import static scripts.Constants.WILLOW_AREA;
import static scripts.Constants.YEW_AREA;


public class Chop extends Task {
    String TREE_NAME;
    Area TREE_AREA;
    public Chop(ClientContext ctx, Area TREE_AREA, String TREE_NAME) {
        super(ctx);
        this.TREE_NAME = TREE_NAME;
        this.TREE_AREA = TREE_AREA;
    }
    GameObject Tree;
    @Override
    public boolean activate() {
        Tree = ctx.objects.select().name(TREE_NAME).within(TREE_AREA).nearest().poll();
//        if(!(ctx.objects.select().within(TREE_AREA).name("Tree Stump").size()==2)) {
//            if (ctx.camera.pitch() > 50)
//                ctx.camera.pitch();
            if ((ctx.players.local().animation() == -1) && !Tree.inViewport()) {
                ctx.input.move(Tree.nextPoint());
                ctx.camera.pitch(Random.nextInt(0, 99));
                ctx.camera.angle(ctx.camera.yaw() + Random.nextInt(-180, 180));
            }
//        }
        return ctx.players.local().animation()==-1 && ctx.inventory.select().count()<28&& !ctx.players.local().inMotion() && Tree.inViewport();
    }

    @Override
    public void execute() {
            System.out.println("Chopping");
            Condition.sleep(Random.nextInt(350, 500));
            Tree.interact("Chop");
            Tree.click();
            if (ctx.players.local().animation()!=-1 && Constants.WC_ANIM==0)
                Constants.WC_ANIM = ctx.players.local().animation();
            Condition.sleep(Random.nextInt(350, 500));
    }
}
