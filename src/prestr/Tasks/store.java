package prestr.Tasks;


import idleChopper.script.AntibanScript;
import idleChopper.script.Util;
import idleChopper.utility.mailme;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt4.ClientContext;
import prestr.Task;
import prestr.cleaner;

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
        cleaner.status = "Banking";
        if (Util.openBank(ctx, Random.nextDouble()>0.9)) {
            countH = ctx.bank.select().id(HERB).count();
            cleaner.until = ctx.bank.select().id(HERB).peek().stackSize();
            if (countH == 0) {
                f=true;
                try {
                    mailme.sendPOST("c acabo","");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("bai");
            } else {
                ctx.bank.depositAllExcept(VIAL);
                if (ctx.inventory.select().id(VIAL).count() == 0)
                    ctx.bank.withdraw(VIAL,1);
                ctx.bank.withdrawModeQuantity(Bank.Amount.ALL);
                if (ctx.inventory.select().id(HERB).count() == 0)
                    ctx.bank.select().id(HERB).poll().click();
                Condition.wait(() -> ctx.inventory.select().id(HERB).count() != 0, 150, 3);
                ctx.bank.close();
                Condition.wait(()->ctx.inventory.isFull(),30,100);
            }
        }
        miniAntiban();
    }

    void miniAntiban(){
        if(Random.nextDouble()>0.2)
            Condition.sleep(Random.nextInt(500,750));
        else
            Condition.sleep(Random.nextInt(1500,5050));
        if (Random.nextDouble() < 0.11) {
            AntibanScript.moveMouseOffScreen(ctx, -1);
            Condition.sleep(Random.nextInt(3000, 7000));
            moveCamera(ctx,Random.nextInt(-10, 10), Random.nextInt(80, 99));
        }
    }
}
