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

public class WalkToBank extends Task {
    static Tile  tiles[] = { new Tile(3172, 3426), new Tile(3177, 3428), new Tile(3182, 3434)};
    Area SGEbankArea =  new Area(new Tile(3180, 3446), new Tile(3185, 3434));

    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !SGEbankArea.contains(ctx.players.local()) && ctx.inventory.isFull();
    }

    @Override
    public void execute() {
        System.out.println("Walking to bank");
        Condition.sleep(Random.nextInt(250, 500));
        TilePath path = ctx.movement.newTilePath(tiles);
        path.randomize(1, 1);
        path.traverse();

    }
}