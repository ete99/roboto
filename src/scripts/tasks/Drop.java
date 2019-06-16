package scripts.tasks;


import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
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
        Iterator<Item> inv = ctx.inventory.select().iterator();
        if(!ctx.inventory.peek().valid()){
            ctx.input.move(Random.nextInt(670, 695),Random.nextInt(510, 540));
            ctx.input.click(true);
        }
        Item i;
        do{
            i=inv.next();
            if(!(i.id()==AXE_ID))
                i.interact("Drop");
        }while(inv.hasNext());
    }
}
