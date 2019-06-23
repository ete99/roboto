package scripts.magic_tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import scripts.SetUp;
import scripts.utility.Task;

import static scripts.quest101.setUp;
import static scripts.script.Util.Chop;
import static scripts.script.Util.*;


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

                if(Random.nextDouble()>0.5) {
                    for(int deg = 0; deg<=360 && !Tree.inViewport();deg+=Random.nextInt(20,50)) {
                        moveCamera(now-deg, 99);

                        dragUntilCamera(Tree);
//                        moveCamera(now+deg, 99);
//                        ctx.camera.angle(now + deg);
                    }
                }else{
                    for(int deg = 0; deg<=360 && !Tree.inViewport();deg+=Random.nextInt(20,50)) {
                        moveCamera(now-deg, 99);
                        dragUntilCamera(Tree);
//                        ctx.camera.angle(now - deg);
                    }
                }
            }
        } else if (setUp.STATE != SetUp.State.WAITING){
            // waits for a new yew tree
            if(Random.nextDouble()>0.5) {
                moveCamera(now + 180 + Random.nextInt(-90,90), Random.nextInt(80,99));
            }else{
                moveCamera(now - 180 + Random.nextInt(-90,90), Random.nextInt(80,99));
            }
            Condition.wait(() -> ctx.objects.select().within(setUp.TREE_AREA).name("Tree Stump").size()!=2,400,150);
        }
    }

    @Override
    public boolean activate() {
        Tree = ctx.objects.select().name(setUp.TREE_NAME).within(setUp.TREE_AREA).nearest().poll();
        return ctx.players.local().animation()==-1 && ctx.inventory.select().count()<28 && Tree.inViewport();
    }

    @Override
    public void execute() {
//        System.out.println("Chopping");
        Condition.sleep(Random.nextInt(350, 500));
        Chop(Tree);
//        AntibanScript.moveMouseOffScreen(ctx,0);
        Condition.sleep(Random.nextInt(350, 500));
    }
}
