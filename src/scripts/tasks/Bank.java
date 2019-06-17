package scripts.tasks;


import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import scripts.Task;

public class Bank extends Task {
    String AXE_NAME;
    public Bank(ClientContext ctx, String AXE_NAME) {
        super(ctx);
        this.AXE_NAME= AXE_NAME;
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
            int AXE_ID=ctx.inventory.name(AXE_NAME).poll().id();
            ctx.bank.depositAllExcept(AXE_ID);
        }
    }
}