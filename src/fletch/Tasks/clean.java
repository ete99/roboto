package fletch.Tasks;


import fletch.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

public class clean extends Task {
    int HERB;
    int VIAL;
    public clean(ClientContext ctx, int HERB, int VIAL) {
        super(ctx);
        this.HERB=HERB;
        this.VIAL=VIAL;
    }

    @Override
    public boolean activate()  {
        System.out.println(ctx.inventory.select().id(HERB).count()>0 && ctx.inventory.select().id(VIAL).count()>0);
        return ctx.inventory.select().id(HERB).count()>0 && ctx.inventory.select().id(VIAL).count()>0 && !ctx.widgets.select().id(270).poll().component(14).visible();
    }

    @Override
    public void execute() {
        int inv=ctx.inventory.select().id(HERB).count();
        ctx.inventory.select().id(HERB).peek().hover();
        Condition.sleep(Random.nextInt(30,40));
//                    ctx.inventory.select().id(13421).peek().interact("Use");
        ctx.inventory.select().id(HERB).poll().click();
//                    Condition.sleep(Random.nextInt(100, 150));
        Item pine = ctx.inventory.select().shuffle().id(VIAL).poll();
        pine.hover();
        pine.click();
        Condition.wait(()->ctx.widgets.select().id(270).poll().component(14).visible(),100,20);
//        AntibanScript.moveMouseOffScreen(ctx,-1);
    }
}
