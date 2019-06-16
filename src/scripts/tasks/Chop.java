package scripts.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import scripts.Constants;
import scripts.Task;


public class Chop extends Task {
    int TREE_ID;
    public Chop(ClientContext ctx, int TREE_ID) {
        super(ctx);
        this.TREE_ID = TREE_ID;
    }
    GameObject Tree;
    @Override
    public boolean activate() {
        Tree = ctx.objects.select().id(TREE_ID).nearest().poll();
        if(ctx.camera.pitch()<50)
            ctx.camera.pitch(99-Random.nextInt(0, 3));
        if(!(ctx.players.local().animation()==-1) && !Tree.inViewport()) {
            ctx.input.move(Tree.nextPoint());
            ctx.camera.pitch(99-Random.nextInt(0, 3));
            ctx.camera.angle(ctx.camera.yaw() + Random.nextInt(-50, 50));
        }
        return ctx.players.local().animation()==-1 && ctx.inventory.select().count()<28&& !ctx.players.local().inMotion() && Tree.inViewport();
    }

    @Override
    public void execute() {
            System.out.println("Chopping");
            Condition.sleep(Random.nextInt(350, 500));
            Tree.interact("Chop");
            Tree.click();
            if (ctx.players.local().animation()!=-1)
                Constants.WC_ANIM = ctx.players.local().animation();
            Condition.sleep(Random.nextInt(350, 500));
    }
}
