package scripts.tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import scripts.Constants;
import scripts.SetUp;
import scripts.Task;
import scripts.script.Util;

import java.util.concurrent.Callable;

import static scripts.quest101.setUp;
import static scripts.script.Util.openDoor;
import static scripts.script.Util.walkToBank;

public class WalkToBank extends Task {
    //static Tile  tiles[] = { new Tile(3172, 3426), new Tile(3177, 3428), new Tile(3182, 3434)};
    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        openDoor();
        return !setUp.BANK_AREA.contains(ctx.players.local()) && ctx.inventory.isFull();
    }

    @Override
    public void execute() {
//        System.out.println("Walking to bank");
        Condition.sleep(Random.nextInt(250, 500));
        walkToBank();

    }
}