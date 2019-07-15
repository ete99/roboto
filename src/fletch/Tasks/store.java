package fletch.Tasks;


import combiner.cleaner;
import fletch.Task;
import idleChopper.script.AntibanScript;
import idleChopper.script.Util;
import idleChopper.utility.mailme;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

import java.io.IOException;

import static fletch.cleaner.*;

public class store extends Task {
    static boolean f;

    public store(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
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
        return (ctx.inventory.select().id(KNIFE).count() == 0||ctx.inventory.select().id(LOG_ID).count() == 0) && !f;
    }

    @Override
    public void execute() {
        if (Util.openBank(ctx, Random.nextDouble()>0.9)) {
            fletch.cleaner.until = ctx.bank.select().id(LOG_ID).poll().stackSize();
            if (ctx.bank.select().id(LOG_ID).count()==0) {
                f=true;
//                System.out.println("hola");
//                System.out.println(LOG_ID);
                try {
                    mailme.sendPOST("c acabo","");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("c acabo");
            } else {
                ctx.bank.depositAllExcept(KNIFE);
                Condition.sleep(Random.nextInt(250, 350));
                if(ctx.inventory.select().id(KNIFE).count()==0)
                    ctx.bank.select().id(KNIFE).poll().click();
                if (ctx.bank.id(LOG_ID).select().count() == 0) {
                    throw new RuntimeException("c acabo");
                }
                ctx.bank.withdrawModeQuantity(Bank.Amount.ALL);
                ctx.bank.select().id(LOG_ID).poll().click();
                Condition.sleep(Random.nextInt(0, 30));
                ctx.bank.close();
                AntibanScript.moveMouseOffScreen(ctx, -1);
                Condition.wait(()->!ctx.bank.opened(),30,100);
            }
        }
    }
}
