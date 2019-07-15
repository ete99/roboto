package fletch.Tasks;


import fletch.Task;
import idleChopper.script.AntibanScript;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

import static fletch.cleaner.*;

public class clean extends Task {
    public clean(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate()  {
        currLevel = ctx.skills.level(9);
        if (currLevel < 35 && currLevel >= 25) {
            LOG_ID = 1521;
        } else if (currLevel < 40 && currLevel >= 35) {
            LOG_ID = 1519;
        } else if (currLevel < 50 && currLevel >= 40) {
            LOG_ID = 1519;
        } else if (currLevel < 55 && currLevel >= 50) {
            LOG_ID = 1517;
        }else if (currLevel < 65 && currLevel >= 55) {
            LOG_ID = 1517;
        }else if (currLevel < 70 && currLevel >= 65) {
            LOG_ID = 1515;
        }else if (currLevel >= 70) {
            LOG_ID = 1515;
        }
        return ctx.inventory.select().id(KNIFE).count()>0 && ctx.inventory.select().id(LOG_ID).count()>0;
    }
    boolean first;
    @Override
    public void execute() {
        if (Random.nextDouble() < 0.80 || !first) {
            first=true;
            ctx.inventory.select().id(KNIFE).poll().click();
            System.out.println(Condition.sleep(Random.nextInt(30, 50)));
            if (!ctx.widgets.select().id(270).poll().component(14).visible())
                ctx.inventory.select().id(LOG_ID).shuffle().poll().click();
        } else {
            first=false;
            ctx.inventory.select().id(LOG_ID).shuffle().poll().click();
            System.out.println(Condition.sleep(Random.nextInt(30, 50)));
            if (!ctx.widgets.select().id(270).poll().component(14).visible())
                ctx.inventory.select().id(KNIFE).shuffle().poll().click();
        }

        AntibanScript.moveMouseOffScreen(ctx,-1);
        Condition.wait(()->ctx.widgets.select().id(270).poll().component(14).visible(), 30, 75);
        if(!ctx.widgets.select().id(270).poll().component(14).visible()){
            if (first) {
                ctx.inventory.select().id(KNIFE).shuffle().poll().click();
            } else {
                ctx.inventory.select().id(LOG_ID).shuffle().poll().click();
            }
            Condition.wait(()->ctx.widgets.select().id(270).poll().component(14).visible(), 30, 50);
            AntibanScript.moveMouseOffScreen(ctx,-1);
        }

    }
}
