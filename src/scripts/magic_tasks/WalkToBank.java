package scripts.magic_tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.ItemQuery;
import scripts.SetUp;
import scripts.utility.Task;

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
        logCheck();
        return !setUp.BANK_AREA.contains(ctx.players.local()) && ctx.inventory.isFull() && ctx.bank.nearest().tile().distanceTo(ctx.players.local().tile())>6;
    }

    @Override
    public void execute() throws Exception {
//        System.out.println("Walking to bank");
        Condition.sleep(Random.nextInt(250, 500));
        walkToBank();
    }

    void logCheck(){

//        System.out.println("Begin Dropping: ");
//        Condition.sleep(Random.nextInt(350, 500));
        ItemQuery<Item> d = ctx.inventory.select().name("Logs");
        while(d.count()!=0){
            setUp.STATE = SetUp.State.DROPPING;
            if(!(d.peek().component().visible()))
            {
                System.out.println("Making Inventory Visible");
                ctx.input.move(Random.nextInt(670, 695),Random.nextInt(510, 540));  // click on inventory if not visible
                ctx.input.click(true);
            }
            for(Item i: d){
                if((!(i.name().equals(setUp.AXE_NAME)) && i.component().visible())){ // probably not needed to specify vibility just trying to solve a bug
                    i.interact("Drop");
//                    System.out.println("Dropping");
                }
                Condition.sleep(Random.nextInt(300, 350));
            }
            d = ctx.inventory.select().name("Logs");
        }
    }
}