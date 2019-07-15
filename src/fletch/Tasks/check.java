package fletch.Tasks;


import fletch.Task;
import idleChopper.script.AntibanScript;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

import static fletch.cleaner.*;
import static idleChopper.script.Util.moveCamera;

public class check extends Task {
    Component c;
    public check(ClientContext ctx) {
        super(ctx);
    }
    int COMPONENT_ID;
    String COMPONENT_N;

    @Override
    public boolean activate() {
        currLevel = ctx.skills.level(9);
        if (currLevel < 35 && currLevel >= 25) {
            COMPONENT_ID = 16;
            COMPONENT_N = "3";
            LOG_ID = 1521;
        } else if (currLevel < 40 && currLevel >= 35) {
            COMPONENT_ID = 15;
            COMPONENT_N = "2";
            LOG_ID = 1519;
        } else if (currLevel < 50 && currLevel >= 40) {
            COMPONENT_ID = 16;
            COMPONENT_N = "3";
            LOG_ID = 1519;
        } else if (currLevel < 55 && currLevel >= 50) {
            COMPONENT_ID = 15;
            COMPONENT_N = "2";
            LOG_ID = 1517;
        }else if (currLevel < 65 && currLevel >= 55) {
            COMPONENT_ID = 16;
            COMPONENT_N = "3";
            LOG_ID = 1517;
        }else if (currLevel < 70 && currLevel >= 65) {
            COMPONENT_ID = 15;
            COMPONENT_N = "2";
            LOG_ID = 1515;
        }else if (currLevel >= 70) {
            COMPONENT_ID = 16;
            COMPONENT_N = "3";
            LOG_ID = 1515;
        }
        c = ctx.widgets.select().id(270).poll().component(COMPONENT_ID);
        return c.visible();
    }

    @Override
    public void execute() {
        Condition.sleep(Random.nextInt(150,350));
//        System.out.println(COMPONENT_ID);
//        System.out.println(COMPONENT_N);
        if(Random.nextDouble()<0.9)
            for (int i = 0; i < Random.nextInt(1,3); i++) {
                ctx.input.send(COMPONENT_N);
            }
        else
            c.click();
        miniAntiban();
        Condition.sleep(Random.nextInt(100,150));
    }
    void miniAntiban(){
        AntibanScript.moveMouseOffScreen(ctx, 1,()->ctx.inventory.select().id(LOG_ID).count()==0 || ctx.players.local().animation()==-1,false);
        Condition.wait(()->ctx.inventory.select().id(LOG_ID).count()==0  || ctx.players.local().animation()==-1, Random.nextInt(100,120),250);

        if (Random.nextDouble() < 0.01) {
            AntibanScript.moveMouseOffScreen(ctx, -1);
            Condition.sleep(Random.nextInt(3000, 7000));
            moveCamera(ctx,Random.nextInt(-10, 10), Random.nextInt(80, 99));
        }
    }
}
