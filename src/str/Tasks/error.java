package str.Tasks;


import idleChopper.script.AntibanScript;
import idleChopper.script.Util;
import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.GameObject;
import str.Task;
import str.cleaner;

import static idleChopper.script.Util.moveCamera;

public class error extends Task {
    public error(ClientContext ctx) {
        super(ctx);

    }
    @Override
    public boolean activate() {
        Component unselectedInventory=ctx.widgets.widget(164).component(53);
        Component inventory = ctx.widgets.widget(164).component(60);
        if (unselectedInventory.textureId() == -1) {
            inventory.click();
        }
        if(ctx.players.local().tile().floor()==0)
            ctx.objects.select().name("Staircase").nearest().poll().click();
        ctx.widgets.widget(595).component(37).click();
        if(ctx.widgets.select().id(310).poll().component(2).component(11).valid())
            ctx.widgets.select().id(310).poll().component(2).component(11).click();
        return false;
    }

    @Override
    public void execute() {
    }
}
