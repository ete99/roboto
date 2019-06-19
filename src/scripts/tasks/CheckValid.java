package scripts.tasks;


import org.powerbot.script.rt4.ClientContext;
import scripts.Task;

import static scripts.quest101.setUp;
import static scripts.script.Util.*;

public class CheckValid extends Task {

    public CheckValid(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        if(ctx.game.clientState()!=30){
            sendMail("Paro!");
            System.exit(4);
        }

        return ctx.inventory.select().name(setUp.AXE_NAME).count()!=1;
    }

    @Override
    public void execute() {
        walkToBank();
        pickAxe();
    }
}
