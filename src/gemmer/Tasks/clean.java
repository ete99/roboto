package gemmer.Tasks;


import gemmer.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

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
        return ctx.inventory.select().id(HERB).count()>0 && ctx.inventory.select().id(VIAL).count()>0;
    }

    @Override
    public void execute() {
        int inv=ctx.inventory.select().id(HERB).count();
        if(Random.nextDouble()>0.80) {
            ctx.inventory.select().id(HERB).shuffle().poll().click();
            System.out.println(Condition.sleep(Random.nextInt(30,50)));
            if (!ctx.widgets.select().id(270).poll().component(14).visible())
                ctx.inventory.select().id(VIAL).shuffle().poll().click();
        } else {
            ctx.inventory.select().id(VIAL).shuffle().poll().click();
            System.out.println(Condition.sleep(Random.nextInt(30,50)));
            if (!ctx.widgets.select().id(270).poll().component(14).visible())
                ctx.inventory.select().id(HERB).shuffle().poll().click();
        }
//        if(!ctx.widgets.select().id(270).poll().component(14).visible())
//            Condition.sleep(Random.nextInt(750,1300));
//        if(ctx.players.local().animation()==-1)
        Condition.wait(()->ctx.widgets.select().id(270).poll().component(14).visible(), 300,10);
//        AntibanScript.moveMouseOffScreen(ctx,-1);
    }
}
