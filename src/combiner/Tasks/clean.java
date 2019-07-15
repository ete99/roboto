package combiner.Tasks;


import com.sun.jndi.cosnaming.CNCtx;
import combiner.Task;
import idleChopper.script.AntibanScript;
import org.omg.CORBA.CTX_RESTRICT_SCOPE;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.ItemQuery;

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
        return ctx.inventory.select().id(HERB).count()>0 && ctx.inventory.select().id(VIAL).count()>0;
    }
    static int triedtimes;
    boolean first;
    @Override
    public void execute() {
        triedtimes++;
        if (Random.nextDouble() < 0.80 || !first) {
            first=true;
            ctx.inventory.select().id(HERB).shuffle().poll().click();
            System.out.println(Condition.sleep(Random.nextInt(30, 50)));
            if (!ctx.widgets.select().id(270).poll().component(14).visible())
                ctx.inventory.select().id(VIAL).shuffle().poll().click();
        } else {
            first=false;
            ctx.inventory.select().id(VIAL).shuffle().poll().click();
            System.out.println(Condition.sleep(Random.nextInt(30, 50)));
            if (!ctx.widgets.select().id(270).poll().component(14).visible())
                ctx.inventory.select().id(HERB).shuffle().poll().click();
        }

//        if(!ctx.widgets.select().id(270).poll().component(14).visible())
//            Condition.sleep(Random.nextInt(750,1300));
//        if(ctx.players.local().animation()==-1)
        AntibanScript.moveMouseOffScreen(ctx,-1);
        Condition.wait(()->ctx.widgets.select().id(270).poll().component(14).visible(), 30, 75);
        if(!ctx.widgets.select().id(270).poll().component(14).visible()){
            if (first) {
                ctx.inventory.select().id(HERB).shuffle().poll().click();
            } else {
                ctx.inventory.select().id(VIAL).shuffle().poll().click();
            }
            Condition.wait(()->ctx.widgets.select().id(270).poll().component(14).visible(), 30, 50);
            AntibanScript.moveMouseOffScreen(ctx,-1);
        }

    }
}
