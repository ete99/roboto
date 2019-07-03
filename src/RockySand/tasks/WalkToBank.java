package RockySand.tasks;

import RockySand.goblin_killer.Constants;
import RockySand.goblin_killer.Task;
import idleChopper.script.AntibanScript;
import org.powerbot.script.Filter;
import org.powerbot.script.MenuCommand;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Path;
import org.powerbot.script.rt4.TilePath;

import static RockySand.utils.sett.*;
import static RockySand.utils.util.*;


public class WalkToBank extends Task {

    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        System.out.println((!Agro) || !hasFood() || needsHeal());
        return (!Agro) || !hasFood() || needsHeal() ;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("bankin");
        TilePath p = ctx.movement.newTilePath(Constants.bank.derive(Random.nextInt(0,2),Random.nextInt(0,2)));
        if(p.valid()) {
            p.randomize(2, 2);
            p.traverse();
        } else ctx.movement.step(Constants.bank);
//        ctx.movement.findPath(Constants.bank).traverse();
        if(ctx.players.local().tile().distanceTo(Constants.bank)<8) {
            if(!hasFood()) {
                openBank();
                if (ctx.bank.opened()) {
                    ctx.bank.depositInventory();
                    ctx.bank.withdraw(333, 28);
                    if (ctx.bank.select(f -> f.id() == 333).poll().stackSize() == 0 && ctx.inventory.isEmpty())
                        throw new Exception("sin comidajaj");
                    ctx.bank.close();
                }
            }
            Agro = true;

        }
    }
    public boolean openBank(){
        if(!ctx.bank.opened()) {
            ctx.camera.turnTo(ctx.bank.nearest().tile(), 7);
            final Filter<MenuCommand> filter = new Filter<MenuCommand>() {
                public boolean accept(MenuCommand command) {
                    String action = command.action;
                    return action.equalsIgnoreCase("Bank") || action.equalsIgnoreCase("Use") || action.equalsIgnoreCase("Open");
                }
            };
            final GameObject bank = ctx.objects.select(ctx.bank.nearest().tile(), 1).nearest().name("Bank chest", "Bank Booth", "Bank").poll();
            bank.hover();
            final boolean b = bank.interact(filter);
            if (b) {
                AntibanScript.moveMouseOffScreen(ctx, 0, () -> ctx.bank.opened());
            }
            return b;
        } else return true;
    }
}
