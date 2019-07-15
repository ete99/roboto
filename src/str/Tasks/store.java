package str.Tasks;


import idleChopper.script.AntibanScript;
import idleChopper.script.Util;
import idleChopper.utility.mailme;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt4.ClientContext;
import str.Task;
import str.cleaner;

import java.io.IOException;

import static idleChopper.script.Util.moveCamera;

public class store extends Task {
    int HERB;
    public store(ClientContext ctx, int HERB) {
        super(ctx);
        this.HERB=HERB;
    }
    static boolean f;
    int countH;
    @Override
    public boolean activate() {
        countH = ctx.inventory.select().id(HERB).count();
        return (countH == 0) && !f;
    }

    @Override
    public void execute() {
        cleaner.status = "Banking";
        if(ctx.bank.nearest().tile().distanceTo(ctx.players.local().tile())>10)
            ctx.movement.step(new Tile(3209+Random.nextInt(-2,1), 3219+Random.nextInt(-2,1)));
        if(!ctx.bank.inViewport())
            ctx.camera.turnTo(ctx.bank.nearest(),50);
        if (Util.openBank(ctx,false)) {
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
                ctx.bank.withdrawModeQuantity(Bank.Amount.ALL);
                if(Random.nextDouble()>0.8)
                    ctx.bank.depositInventory();
                else
                    ctx.inventory.select().shuffle().poll().click();
                if (ctx.inventory.select().id(HERB).count() == 0)
                    ctx.bank.select().id(HERB).poll().click();
                Condition.wait(() -> ctx.inventory.select().id(HERB).count() != 0, 150, 3);
                ctx.bank.close();
                Condition.wait(()->ctx.inventory.isFull(),30,100);
            }
        }
    }
}
