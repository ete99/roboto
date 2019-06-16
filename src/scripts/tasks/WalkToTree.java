package scripts.tasks;


import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.TilePath;
import scripts.Task;
import yew.AREA;

import java.util.concurrent.Callable;

public class WalkToTree extends Task {
    static Tile  tiles[] = { new Tile(3172, 3426, 0), new Tile(3167, 3417, 0)};
    Area TreeArea =  new Area(new Tile(3172, 3424), new Tile(3158, 3400));
    Area bankArea =  new Area(new Tile(3180, 3446), new Tile(3185, 3434));

    public WalkToTree(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.inventory.isFull() && !TreeArea.contains(ctx.players.local());
    }

    @Override
    public void execute() {
        System.out.println("Walking to Trees");
        Condition.sleep(Random.nextInt(250, 500));
        TilePath path = ctx.movement.newTilePath(tiles);
        path.randomize(2, 2);
        path.traverse();
        Condition.sleep(Random.nextInt(250, 500));
        ctx.camera.pitch(ctx.camera.pitch()+Random.nextInt(-10,10));
        ctx.camera.angle(ctx.camera.yaw() + Random.nextInt(-50, 50));

    }
}

