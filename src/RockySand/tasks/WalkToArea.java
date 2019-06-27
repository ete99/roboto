package RockySand.tasks;

import RockySand.goblin_killer.Constants;
import RockySand.goblin_killer.Task;
import RockySand.utils.sett;
import RockySand.utils.util;
import idleChopper.script.AntibanScript;
import org.powerbot.script.rt4.ClientContext;

import static RockySand.utils.util.needsHeal;


public class WalkToArea extends Task {

    public WalkToArea(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return sett.Agro && !ctx.players.local().inCombat() && !Constants.ABANDONED_HOUSE.contains(ctx.players.local()) && util.hasFood() && !needsHeal();
    }

    @Override
    public void execute() {
        System.out.println("walkin");
        ctx.movement.step(Constants.sandPlace);
        AntibanScript.moveMouseOffScreen(ctx,0,()->!ctx.players.local().inMotion(), false);
    }
}
