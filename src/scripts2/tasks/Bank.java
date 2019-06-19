package scripts2.tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import scripts2.Task;

public class Bank extends Task {
    public Bank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        if(ctx.bank.opened())
            ctx.bank.close();
        return ctx.inventory.isFull();
    }

    @Override
    public void execute() {
        Condition.sleep(Random.nextInt(350, 500));
        System.out.println("banking");
        if(!ctx.bank.inViewport()){
            ctx.camera.pitch(99-Random.nextInt(0, 3));
            ctx.camera.angle(ctx.camera.yaw() + Random.nextInt(-50, 50));
        }else {
            ctx.camera.angle(ctx.camera.yaw() + Random.nextInt(-50, 50));
            ctx.bank.open();
            ctx.bank.depositAllExcept(ctx.inventory.select().poll().id());
            ctx.bank.close();
        }
    }
}