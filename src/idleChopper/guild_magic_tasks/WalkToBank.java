package idleChopper.guild_magic_tasks;


import idleChopper.common_tasks.Antiban;
import idleChopper.script.AntibanScript;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import idleChopper.utility.Task;

import static idleChopper.script.Util.walkToBank;

public class WalkToBank extends Task {
    //static Tile  tiles[] = { new Tile(3172, 3426), new Tile(3177, 3428), new Tile(3182, 3434)};
    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.isFull() && ctx.bank.nearest().tile().distanceTo(ctx.players.local().tile())>10 && !ctx.bank.poll().inViewport() && !openBank();
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Walking to bank");
        ctx.camera.turnTo(ctx.bank.nearest().tile(), 13);
//        ctx.bank.open();
        if(!ctx.bank.opened())
            walkToBank();
    }
    boolean openBank(){
        ctx.camera.turnTo(ctx.bank.nearest().tile(), 7);
        boolean b =ctx.bank.poll().click("Use");
        if(b) {
            System.out.println("hola");

            AntibanScript.moveMouseOffScreen(ctx, 0, () -> !ctx.bank.opened());
            System.out.println("chau");

        }
        return b;
    }
}

