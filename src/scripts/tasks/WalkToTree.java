package scripts.tasks;


import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.TilePath;
import scripts.SetUp.*;
import scripts.Task;
import static scripts.script.Util.Chop;

import java.util.concurrent.Callable;

import static scripts.quest101.setUp;

public class WalkToTree extends Task {
//    static Tile  tiles[] = { new Tile(3172, 3426, 0), new Tile(3167, 3417, 0)};
//    Area TreeArea =  new Area(new Tile(3172, 3424), new Tile(3158, 3400));
//    Area bankArea =  new Area(new Tile(3180, 3446), new Tile(3185, 3434));
    Area TREE_AREA;
    Tile RIDE[];
    public WalkToTree(ClientContext ctx, Area TREE_AREA, Tile RIDE[]) {
        super(ctx);
        this.TREE_AREA = TREE_AREA;
        this.RIDE = RIDE.clone();
    }

    void openDoor(){
        final GameObject DOOR = ctx.objects.select().id(1543).nearest().poll();
        if(DOOR.valid()) {
            DOOR.interact("Open");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return DOOR.id()==1544;
                }
            }, 400, 4);
        }
    }

    @Override
    public boolean activate() {
        return !ctx.inventory.isFull() && !TREE_AREA.contains(ctx.players.local());
    }

    @Override
    public void execute() {
        System.out.println("Walking to Trees");
        Condition.sleep(Random.nextInt(250, 500));
        if(setUp.state != Type.IDLE && ctx.objects.select().name(setUp.TREE_NAME).nearest().poll().inViewport())
        if(!(TREE_AREA.contains(ctx.players.local())))
            openDoor();
        else {
            GameObject Tree = ctx.objects.select().name(setUp.TREE_NAME).nearest().poll();
            if (Tree.inViewport()){
                Chop(Tree);
            }
        }

        TilePath path = ctx.movement.newTilePath(RIDE);
        path.reverse();
        path.randomize(1, 1);
        path.traverse();
        setUp.state = Type.WALKING;
        Condition.sleep(Random.nextInt(250, 500));
        ctx.camera.pitch(ctx.camera.pitch()+Random.nextInt(-10,10));
        ctx.camera.angle(ctx.camera.yaw() + Random.nextInt(-50, 50));
    }
}

