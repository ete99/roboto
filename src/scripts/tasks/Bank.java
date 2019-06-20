package scripts.tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import scripts.SetUp;
import scripts.Task;

import static scripts.quest101.setUp;

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
//        System.out.println("banking");
        ctx.camera.angle(ctx.camera.yaw() + Random.nextInt(-50, 50));
        if(!ctx.bank.inViewport() && ctx.bank.close()){
            ctx.camera.pitch(99-Random.nextInt(0, 3));
        }else {
            setUp.state = SetUp.State.BANKING;
            ctx.bank.open();
            int AXE_ID=ctx.inventory.name(setUp.AXE_NAME).poll().id();
            ctx.bank.depositAllExcept(AXE_ID);
            ctx.bank.close();
        }
    }
}