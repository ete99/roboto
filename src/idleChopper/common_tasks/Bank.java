package idleChopper.common_tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import idleChopper.SetUp;
import idleChopper.utility.Task;

import static idleChopper.quest101.setUp;
import static idleChopper.script.Util.openBank;

public class Bank extends Task {
    public Bank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.isFull();
    }

    @Override
    public void execute() {
        Condition.sleep(Random.nextInt(350, 500));
        System.out.println("banking");
        setUp.STATE = SetUp.State.BANKING;
        ctx.camera.angle(ctx.camera.yaw() + Random.nextInt(-50, 50));
        if(!ctx.bank.inViewport() && !ctx.bank.opened()){
            ctx.camera.pitch(99-Random.nextInt(0, 3));
            ctx.camera.turnTo(ctx.bank.nearest());
        }else {
            if(!ctx.bank.opened())
                openBank();
            int AXE_ID=ctx.inventory.name(setUp.AXE_NAME).poll().id();
            ctx.bank.depositAllExcept(AXE_ID);
            ctx.bank.close();
        }
    }
}