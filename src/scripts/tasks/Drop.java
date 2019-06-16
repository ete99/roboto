package scripts.tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.ItemQuery;
import org.powerbot.script.rt4.Widget;
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
        System.out.println("Begin Dropping: ");
        Condition.sleep(Random.nextInt(350, 500));
        ItemQuery<Item> d = ctx.inventory.select().name("Willow Logs");
        while(d.count()!=0){
            if(!(d.peek().component().visible()))
            {
                System.out.println("Making Inverntory Visible");
                ctx.input.move(Random.nextInt(670, 695),Random.nextInt(510, 540));  // click on inventory if not visible
                ctx.input.click(true);
            }
            for(Item i: d){
                if(!(i.name().equals("Adamant Axe") && i.component().visible())){ // probably not needed to specify vibility just trying to solve a bug
                    i.interact("Drop");
                    System.out.println("Dropping");
                }
                Condition.sleep(Random.nextInt(350, 400));
            }
            d = ctx.inventory.select().name("Willow Logs");
        }

    }
}
