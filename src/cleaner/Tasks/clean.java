package cleaner.Tasks;


import cleaner.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.ItemQuery;

public class clean extends Task {
    int HERB;
    public clean(ClientContext ctx, int HERB) {
        super(ctx);
        this.HERB=HERB;
    }

    @Override
    public boolean activate()  {
        return ctx.inventory.select().id(HERB).count()>0;
    }

    @Override
    public void execute() {
        ItemQuery<Item> i = ctx.inventory.select().id(HERB);
        for (Item k : i) {
            k.hover();
            Condition.sleep(Random.nextInt(30, 40));
            k.click();
            Condition.sleep(Random.nextInt(100, 150));
        }
    }
}
