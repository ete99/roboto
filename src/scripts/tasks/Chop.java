package scripts.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import scripts.script.AntibanScript;
import scripts.SetUp;
import scripts.Task;

import java.util.concurrent.Callable;

import static scripts.script.Util.Chop;

import static scripts.quest101.setUp;


public class Chop extends Task {
    public Chop(ClientContext ctx) {
        super(ctx);
    }
    GameObject Tree;

    private void EdgeCheck(){
        Tree = ctx.objects.select().name(setUp.TREE_NAME).within(setUp.TREE_AREA).nearest().poll();
        int now = ctx.camera.yaw();
        if(!(ctx.objects.select().within(setUp.TREE_AREA).name("Tree Stump").size()==2)) {
//            if (ctx.camera.pitch() > 50)
//                ctx.camera.pitch();
            if ((ctx.players.local().animation() == -1) && !Tree.inViewport()) {
                ctx.input.move(Tree.nextPoint());
                ctx.camera.pitch(Random.nextInt(0, 99));

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
        } else if (setUp.state != SetUp.State.WAITING){
            // waits for a new yew tree
            if(Random.nextDouble()>0.5) {
                    ctx.camera.angle(now + 180 + Random.nextInt(-90,90));
            }else{
                    ctx.camera.angle(now - 180 + Random.nextInt(-90,90));
            }
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.objects.select().within(setUp.TREE_AREA).name("Tree Stump").size()==2;
                }
            },400,150);
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
