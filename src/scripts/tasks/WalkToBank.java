package scripts.tasks;


import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.TilePath;
import scripts.SetUp;
import scripts.Task;

import java.util.concurrent.Callable;

import static scripts.quest101.setUp;

public class WalkToBank extends Task {
    //static Tile  tiles[] = { new Tile(3172, 3426), new Tile(3177, 3428), new Tile(3182, 3434)};
    Area BANK_AREA;
    Tile RIDE[];
    public WalkToBank(ClientContext ctx, Area BANK_AREA,Tile RIDE[]) {
        super(ctx);
        this.BANK_AREA = BANK_AREA;
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
        openDoor();
        return !BANK_AREA.contains(ctx.players.local()) && ctx.inventory.isFull();
    }
    @Override
    public void execute() {
        System.out.println("Walking to bank");
        Condition.sleep(Random.nextInt(250, 500));
        TilePath path = ctx.movement.newTilePath(RIDE);
        path.randomize(1, 1);
        path.traverse();
        setUp.state = SetUp.Type.WALKING;

    }
}