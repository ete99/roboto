package scripts.tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.ItemQuery;
import scripts.Task;

import java.util.Iterator;

public class Drop extends Task {
    int AXE_ID;
    public Drop(ClientContext ctx, int AXE_ID) {
        super(ctx);
        this.AXE_ID = AXE_ID;
    }

    @Override
    public boolean activate() {
        return ctx.inventory.isFull();
    }

    @Override
    public void execute() {
        System.out.println("Dropping");
        Condition.sleep(Random.nextInt(350, 500));
        ItemQuery<Item> d = ctx.inventory.name("Willow Logs");
        for(Item i: d){
            if(!(i.name().equals("Adamant Axe")))
                i.interact("Drop");
        }
    }
}
