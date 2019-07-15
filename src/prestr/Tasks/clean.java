package prestr.Tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import prestr.Task;
import prestr.cleaner;

public class clean extends Task {
    int GEM;
    int CHISEL;
    public clean(ClientContext ctx, int GEM, int CHISEL) {
        super(ctx);
        this.GEM=GEM;
        this.CHISEL=CHISEL;
    }

    @Override
    public boolean activate()  {
        return ctx.inventory.select().id(GEM).count()>0 && ctx.inventory.select().id(CHISEL).count()>0 && ctx.players.local().animation()==-1;
    }

    @Override
    public void execute() {
        cleaner.status = "combining";

        if(Random.nextDouble()>0.80) {
            ctx.inventory.select().id(GEM).shuffle().poll().click();
            Condition.sleep(Random.nextInt(30,50));
            if (!ctx.widgets.select().id(270).poll().component(14).visible())
                ctx.inventory.select().id(CHISEL).shuffle().poll().click();
        } else {
            ctx.inventory.select().id(CHISEL).shuffle().poll().click();
            Condition.sleep(Random.nextInt(30,50));
            if (!ctx.widgets.select().id(270).poll().component(14).visible())
                ctx.inventory.select().id(GEM).shuffle().poll().click();
        }
        Condition.wait(()->ctx.widgets.select().id(270).poll().component(14).visible(), 30, 50);
    }
}
