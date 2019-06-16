package scripts.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Locatable;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.BasicQuery;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import scripts.Constants;
import scripts.Task;

import java.util.Iterator;

public class Chop extends Task {
    final int OAK_ID = 10820;
    public Chop(ClientContext ctx) {
        super(ctx);
    }
    GameObject Tree;
    @Override
    public boolean activate() {
        Tree = ctx.objects.select().id(OAK_ID).nearest().poll();
        if(ctx.camera.pitch()<50)
            ctx.camera.pitch(80);
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
