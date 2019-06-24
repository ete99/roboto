package scripts.common_tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.LocalPath;
import org.powerbot.script.rt4.TilePath;
import scripts.SetUp;
import scripts.utility.Task;

import static scripts.Constants.WC_ANIM;
import static scripts.quest101.setUp;
import static scripts.script.Util.*;

public class CheckValid extends Task {

    public CheckValid(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() throws Exception {

        System.out.println("check valid");
        Condition.wait(()-> setUp.ctx.players.local().animation() != -1, 60,10);
        if(ctx.players.local().inMotion())
            setUp.STATE = SetUp.State.WALKING;
        if (setUp.ctx.players.local().animation() == WC_ANIM)
            setUp.STATE = SetUp.State.CHOPPING;
        System.out.println(setUp.STATE);
        if(ctx.game.clientState()!=30){
            sendMail("Paro!");
            System.exit(4);
        }
        if(ctx.inventory.select().name(setUp.AXE_NAME).count()!=1){
            System.out.println("Grabbing axe");
            if(!ctx.bank.inViewport())
                walkToBank();
            else
                pickAxe();
        }
        TilePath path = setUp.ctx.movement.newTilePath(setUp.RIDE);
        if(!path.valid()) {
            walkToBank();
        }
        /*
@TODO fix bug
        if(setUp.STATE == SetUp.State.REALLY_IDLE && (setUp.TREE_AREA.contains(ctx.players.local()))) {
            dragCamera();
            AntibanScript.moveMouseOffScreen(setUp.ctx, 0);
        }
*/
        if(setUp.STATE == SetUp.State.IDLE && (setUp.BANK_AREA.contains(ctx.players.local()))) {
            walkToTree();
        }
        return false;
    }

    @Override
    public void execute() {}
}
