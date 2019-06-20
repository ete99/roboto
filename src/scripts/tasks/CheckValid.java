package scripts.tasks;


import org.powerbot.script.rt4.ClientContext;
import scripts.SetUp;
import scripts.Task;
import scripts.script.AntibanScript;

import static scripts.quest101.setUp;
import static scripts.script.Util.*;

public class CheckValid extends Task {

    public CheckValid(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        System.out.println(setUp.state);
        if(ctx.game.clientState()!=30){
            sendMail("Paro!");
            System.exit(4);
        }
        if(ctx.inventory.select().name(setUp.AXE_NAME).count()!=1){
            System.out.println("Grabbing axe");
            walkToBank();
            pickAxe();
        }
        if(setUp.state == SetUp.State.REALLY_IDLE && (setUp.TREE_AREA.contains(ctx.players.local()))) {
            dragCamera();
            AntibanScript.moveMouseOffScreen(setUp.ctx, 0);
        }
        if(setUp.state == SetUp.State.IDLE && (setUp.BANK_AREA.contains(ctx.players.local()))) {
            walkToTree();
        }
        return false;
    }

    @Override
    public void execute() {}
}
