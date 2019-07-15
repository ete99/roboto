package str.Tasks;


import idleChopper.script.AntibanScript;
import idleChopper.script.Util;
import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import org.powerbot.script.rt4.GameObject;
import str.Task;
import str.cleaner;

import static idleChopper.script.Util.moveCamera;

public class godown extends Task {
    int HERB;
    public godown(ClientContext ctx, int HERB) {
        super(ctx);
        this.HERB=HERB;
    }
    @Override
    public boolean activate() {
        return ctx.players.local().tile().floor()==2 && ctx.inventory.select().id(HERB).count()!=0;
    }

    @Override
    public void execute() {
        cleaner.status = "downning";
        if(ctx.players.local().tile().distanceTo(new Tile(3206,3210,2))>10)
            ctx.movement.step(new Tile(3206+Random.nextInt(-2,1),3210+Random.nextInt(-2,1),2));
        GameObject stair = ctx.objects.select().nearest(new Tile(3206,3210,2)).name("Staircase").peek();
        if(!stair.inViewport()) {
            ctx.camera.turnTo(stair, 50);
            if(ctx.camera.pitch()<60)
                ctx.camera.pitch(Random.nextInt(80,99));
        }
        stair.interact("Climb-down");
        AntibanScript.moveMouseOffScreen(ctx,-1);
        Condition.wait(()->ctx.players.local().tile().floor()==1);
    }

    void miniAntiban(){
        cleaner.status = "antiban";
        if(Random.nextDouble()>0.2)
            Condition.sleep(Random.nextInt(500,750));
        else
            Condition.sleep(Random.nextInt(1500,5050));
        if (Random.nextDouble() < 0.11) {
            AntibanScript.moveMouseOffScreen(ctx, -1);
            Condition.sleep(Random.nextInt(3000, 7000));
            moveCamera(ctx,Random.nextInt(-10, 10), Random.nextInt(80, 99));
        }
    }
}
