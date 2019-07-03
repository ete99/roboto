package combiner.Tasks;


import combiner.Task;
import idleChopper.script.AntibanScript;
import idleChopper.script.Util;
import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.MenuCommand;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Npc;

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
    static int countH, countV;
    @Override
    public boolean activate() {
        countH = ctx.inventory.select().id(HERB).count();
        countV = ctx.inventory.select().id(VIAL).count();
        return (countH == 0 || countV == 0) && !f;
    }

    @Override
    public void execute() {
        miniAntiban();
        if (!Util.openBank(ctx, Random.nextDouble()>0.9)) {
            ctx.inventory.select().poll().click();
        }
        if (ctx.bank.opened()) {
            if (ctx.bank.select().id(VIAL).count()==0 || ctx.bank.select().id(HERB).count()==0) {
                f=true;
                Util.sendMail("c acabo");
                throw new RuntimeException("bai");
            } else {
//                ctx.bank.depositAllExcept(HERB);
                if(Random.nextDouble()>0.8)
                    ctx.bank.depositInventory();
                else
                    ctx.inventory.select().shuffle().poll().click();
                Condition.sleep(Random.nextInt(150, 350));
                if(Random.nextDouble()>0.8) {
                    if (ctx.inventory.select().id(HERB).count() == 0)
                        ctx.bank.select().id(HERB).poll().click();
                    System.out.println(Random.nextInt(0, 30));
                    if (ctx.inventory.select().id(VIAL).count() == 0)
                        ctx.bank.select().id(VIAL).poll().click();
                } else {
                    if (ctx.inventory.select().id(VIAL).count() == 0)
                        ctx.bank.select().id(VIAL).poll().click();
                    System.out.println(Random.nextInt(0, 30));
                    if (ctx.inventory.select().id(HERB).count() == 0)
                        ctx.bank.select().id(HERB).poll().click();
                }
                Condition.wait(() -> ctx.inventory.select().id(VIAL).count() != 0, 150, 3);
                ctx.bank.close();
                System.out.println(Condition.wait(()->ctx.inventory.isFull(),300,10));
            }
        }
    }

    void miniAntiban(){
//        Condition.sleep(Random.nextInt(14000,15000));
        Condition.sleep(Random.nextInt(500,750));
        if (Random.nextDouble() < 0.11) {
            AntibanScript.moveMouseOffScreen(ctx, -1);
            Condition.sleep(Random.nextInt(3000, 7000));
            moveCamera(ctx,Random.nextInt(-10, 10), Random.nextInt(80, 99));
        }
    }
}
