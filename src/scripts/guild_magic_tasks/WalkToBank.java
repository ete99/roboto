package scripts.guild_magic_tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.ItemQuery;
import scripts.SetUp;
import scripts.utility.Task;

import static scripts.quest101.setUp;
import static scripts.script.Util.walkToBank;

public class WalkToBank extends Task {
    //static Tile  tiles[] = { new Tile(3172, 3426), new Tile(3177, 3428), new Tile(3182, 3434)};
    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.isFull() && ctx.bank.nearest().tile().distanceTo(ctx.players.local().tile())>6;
    }

    @Override
    public void execute() throws Exception {
//        System.out.println("Walking to bank");
        Condition.sleep(Random.nextInt(250, 500));
        walkToBank();
    }

}