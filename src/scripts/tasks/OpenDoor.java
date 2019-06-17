package scripts.tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import scripts.Task;

import java.util.concurrent.Callable;

public class OpenDoor extends Task {

    public OpenDoor(ClientContext ctx) {
        super(ctx);
    }
    GameObject DOOR;
    @Override
    public boolean activate() {
//        DOOR = ctx.objects.select().id(1543).nearest().poll();
        return ctx.objects.select().id(1543).nearest().poll().valid();
    }

    @Override
    public void execute() {
        DOOR.interact("Open");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return DOOR.id()==1544;
            }
        }, 400, 4);
    }
}
