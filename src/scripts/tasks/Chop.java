package scripts.tasks;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import scripts.AntibanScript;
import scripts.Constants;
import scripts.SetUp;
import scripts.Task;
import static scripts.script.Util.Chop;
import scripts.tasks.Antiban.*;

import static scripts.quest101.setUp;


public class Chop extends Task {
    String TREE_NAME;
    Area TREE_AREA;
    public Chop(ClientContext ctx, Area TREE_AREA, String TREE_NAME) {
        super(ctx);
        this.TREE_NAME = TREE_NAME;
        this.TREE_AREA = TREE_AREA;
    }
    GameObject Tree;

    private void EdgeCheck(){
        Tree = ctx.objects.select().name(TREE_NAME).within(TREE_AREA).nearest().poll();
        if(!(ctx.objects.select().within(TREE_AREA).name("Tree Stump").size()==2)) {
//            if (ctx.camera.pitch() > 50)
//                ctx.camera.pitch();
            if ((ctx.players.local().animation() == -1) && !Tree.inViewport()) {
                ctx.input.move(Tree.nextPoint());
                ctx.camera.pitch(Random.nextInt(0, 99));
                int now = ctx.camera.yaw();
                if(Random.nextDouble()>0.5) {
                    for(int deg = 0; deg<=360 && !Tree.inViewport();deg+=Random.nextInt(1,25)) {
                        ctx.camera.angle(now + deg);
                    }
                }else{
                    for(int deg = 0; deg<=360 && !Tree.inViewport();deg+=Random.nextInt(1,25)) {
                        ctx.camera.angle(now - deg);
                    }
                }
            }
        }
    }



    @Override
    public boolean activate() {
        setUp.ctx = ctx;
        EdgeCheck();
        return ctx.players.local().animation()==-1 && ctx.inventory.select().count()<28 && !ctx.players.local().inMotion() && Tree.inViewport();
    }

    @Override
    public void execute() {
        System.out.println("Chopping");
        Condition.sleep(Random.nextInt(350, 500));
        Chop(Tree);
        AntibanScript.moveMouseOffScreen(ctx,0);
        Condition.sleep(Random.nextInt(350, 500));
    }
}
