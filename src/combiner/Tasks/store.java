package combiner.Tasks;


import combiner.Task;
import combiner.cleaner;
import idleChopper.script.AntibanScript;
import idleChopper.script.Util;
import idleChopper.utility.mailme;
import org.omg.CORBA.CTX_RESTRICT_SCOPE;
import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.MenuCommand;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Npc;

import java.io.IOException;

import static idleChopper.script.Util.moveCamera;

public class store extends Task {
    int HERB;
    int VIAL;
    public store(ClientContext ctx, int HERB,int VIAL) {
        super(ctx);
        this.HERB=HERB;
        this.VIAL=VIAL;
    }
    static boolean f;
    int countH, countV;
    @Override
    public boolean activate() {
        countH = ctx.inventory.select().id(HERB).count();
        countV = ctx.inventory.select().id(VIAL).count();
        return (countH == 0 || countV == 0) && !f;
    }

    @Override
    public void execute() {
        if (Util.openBank(ctx, Random.nextDouble()>0.9)) {
            countH = ctx.bank.select().id(HERB).count();
            countV = ctx.bank.select().id(VIAL).count();
            cleaner.until = Math.min(ctx.bank.select().id(VIAL).peek().stackSize(), ctx.bank.select().id(HERB).peek().stackSize());
            if (countH == 0 || countV == 0) {
                f=true;
                try {
                    mailme.sendPOST("c acabo","");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("bai");
            } else {
                if(Random.nextDouble()>0.8)
                    ctx.bank.depositInventory();
                else
                    ctx.inventory.select().shuffle().poll().click();
                Condition.sleep(Random.nextInt(150, 350));
                ctx.bank.withdrawModeQuantity(Bank.Amount.X);
                if(ctx.bank.withdrawXAmount()!=14)
                    ctx.bank.withdraw(HERB, 14);
                if(Random.nextDouble()>0.8) {
                        ctx.bank.select().id(HERB).poll().click();
                    Condition.sleep(Random.nextInt(0, 30));
                        ctx.bank.select().id(VIAL).poll().click();
                } else {
                        ctx.bank.select().id(VIAL).poll().click();

                    Condition.sleep(Random.nextInt(0, 30));
                        ctx.bank.select().id(HERB).poll().click();

                }
                Condition.sleep(Random.nextInt(0, 30));
                ctx.bank.close();
                AntibanScript.moveMouseOffScreen(ctx, -1);
                Condition.wait(()->!ctx.bank.opened(),30,100);
            }
        }
        miniAntiban();
    }

    void miniAntiban(){
        if (Random.nextDouble() < 0.11) {
            AntibanScript.moveMouseOffScreen(ctx, -1);
            Condition.sleep(Random.nextInt(3000, 7000));
            if(Random.nextDouble()>0.5)
                moveCamera(ctx,Random.nextInt(-10, 10), Random.nextInt(80, 99));
        }
    }
}
