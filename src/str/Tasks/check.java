package str.Tasks;


import idleChopper.script.AntibanScript;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import str.Task;
import str.cleaner;

import static idleChopper.script.Util.moveCamera;

public class check extends Task {
    int GUAM;

    public check(ClientContext ctx,int GUAM) {
        super(ctx);
        this.GUAM = GUAM;
    }
    Component c;

    @Override
    public boolean activate() {
        c = ctx.widgets.select().id(270).poll().component(16);
        return c.visible();
    }

    @Override
    public void execute() {
        cleaner.status = "cutting";
        Condition.sleep(Random.nextInt(300,500));
        if(Random.nextDouble()<0.9)
            for (int i = 0; i < Random.nextInt(1,6); i++) {
                ctx.input.send("3");
            }
        else
            c.click();
        miniAntiban();
        Condition.sleep(Random.nextInt(100,150));
    }

    void miniAntiban(){
        AntibanScript.moveMouseOffScreen(ctx, -1);
        Condition.wait(()->ctx.players.local().animation()!=-1);
        for (int i = 0; i < 3; i++) {
            if(ctx.players.local().animation()!=-1) {
                Condition.wait(() -> ctx.inventory.select().id(GUAM).count() == 0 || ctx.widgets.select().id(233).poll().component(3).visible(), Random.nextInt(100, 120), 750);
                break;
            }
            Condition.sleep(300);
        }

        if(Random.nextDouble()<0.9)
            Condition.sleep(Random.nextInt(500,750));
        else
            Condition.sleep(Random.nextInt(5000,7500));
        if(ctx.widgets.select().id(229).poll().component(1).visible()){
            throw new RuntimeException("Need level 10 to string");
        }

    }
}
