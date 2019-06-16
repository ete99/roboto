package scripts.tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import scripts.Task;

import java.util.concurrent.Callable;

public class Bank extends Task {
    final static  int AXE[] = {1355, 1357, 1359, 1361};
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
        if(!ctx.bank.inViewport() && ctx.bank.close()){
            ctx.camera.pitch(99-Random.nextInt(0, 3));
            ctx.camera.angle(ctx.camera.yaw() + Random.nextInt(-50, 50));
        }else {
            ctx.bank.open();
            ctx.bank.depositAllExcept(AXE[0]);
        }
    }
}