package RockySand.tasks;

import RockySand.goblin_killer.Constants;
import RockySand.goblin_killer.Task;
import RockySand.utils.sett;
import RockySand.utils.util;
import idleChopper.script.AntibanScript;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Path;
import org.powerbot.script.rt4.TilePath;

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
        TilePath p = ctx.movement.newTilePath(Constants.sandPlace.derive(Random.nextInt(0,2),Random.nextInt(0,2)));
        if(p.valid()) {
            p.randomize(2, 2);
            p.traverse();
        } else ctx.movement.step(Constants.sandPlace);
        AntibanScript.moveMouseOffScreen(ctx,0,()->!ctx.players.local().inMotion(), false);
    }
}
