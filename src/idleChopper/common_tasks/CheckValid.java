package idleChopper.common_tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;
import idleChopper.SetUp;
import idleChopper.utility.Task;

import static idleChopper.Constants.WC_ANIM;
import static idleChopper.quest101.setUp;
import static idleChopper.script.Util.*;

public class CheckValid extends Task {

    public CheckValid(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() throws Exception {

        System.out.println("check valid");
        ctx.widgets.widget(595).component(37).click();
        Condition.wait(()-> setUp.ctx.players.local().animation() != -1, 60,10);
        if(ctx.widgets.select().id(310).poll().component(2).component(11).valid())
            ctx.widgets.select().id(310).poll().component(2).component(11).click();
        if(setUp.ctx.widgets.select().id(345).poll().valid()){
            ctx.camera.pitch(99);
            walkToBank();
        }
        if (setUp.ctx.players.local().animation() == WC_ANIM)
            setUp.STATE = SetUp.State.CHOPPING;
        System.out.println(setUp.STATE);
        if(ctx.game.clientState()!=30){
            sendMail("Paro!");
            System.exit(4);
        }
        if(ctx.inventory.select().name(setUp.AXE_NAME).count()!=1 && ctx.equipment.select().name(setUp.AXE_NAME).count()!=1){
            System.out.println("Grabbing axe");
            if(!ctx.bank.inViewport())
                walkToBank();
            else
                pickAxe();
        }
        if(ctx.equipment.select().name(setUp.AXE_NAME).count()==1&& ctx.varpbits.varpbit(300)==1000){
            Condition.wait(()->ctx.players.local().animation()==-1, Random.nextInt(500,600),1);
            ctx.combat.specialAttack(true);
        }
        TilePath path = setUp.ctx.movement.newTilePath(setUp.RIDE);
        if(!path.valid()) {
            walkToBank();
        }
        /*
@TODO fix bug
        if(sett.STATE == SetUp.State.REALLY_IDLE && (sett.TREE_AREA.contains(ctx.players.local()))) {
            dragCamera();
            AntibanScript.moveMouseOffScreen(sett.ctx, 0);
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
