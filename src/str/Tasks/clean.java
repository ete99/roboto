package str.Tasks;


import idleChopper.script.AntibanScript;
import idleChopper.script.Util;
import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import str.Task;
import str.cleaner;

public class clean extends Task {
    int GEM;
    public clean(ClientContext ctx, int GEM) {
        super(ctx);
        this.GEM=GEM;
    }

    @Override
    public boolean activate()  {
        return ctx.inventory.select().id(GEM).count()>0 && ctx.players.local().tile().floor()==1 && ctx.players.local().animation()==-1;
    }

    @Override
    public void execute() {
        cleaner.status = "combining";

        if(ctx.camera.pitch()<60)
            Util.moveCamera(ctx,270+ Random.nextInt(-6,5), Random.nextInt(80,99));
        openDoor();
        GameObject WHEEL = ctx.objects.select().id(14889).poll();
        if(!WHEEL.inViewport())
            ctx.movement.step(new Tile(3209+Random.nextInt(-2,1),3214+Random.nextInt(-2,1),1));
        WHEEL.hover();
        if(Random.nextDouble()>0.8)
            WHEEL.interact("Spin");
        else for (int i = 0; i < Random.nextInt(1,4); i++) {
            WHEEL.click();
        }
        AntibanScript.moveMouseOffScreen(ctx,-1);
        Condition.wait(()->ctx.widgets.select().id(270).poll().component(16).visible());
//        if(ctx.players.local().tile().distanceTo(WHEEL)<=1 && !ctx.widgets.select().id(270).poll().component(16).visible())
        if(!ctx.widgets.select().id(270).poll().component(16).visible()){
            ctx.camera.turnTo(WHEEL,50);
            ctx.camera.pitch(Random.nextInt(80,99));
        }
    }
    public void openDoor(){
        GameObject door = ctx.objects.select().name("Door").within(new Area(new Tile(3207,3213,1),new Tile(3209,3215,1))).peek();
        if(door.id() == 1543) {
            Util.moveCamera(ctx,270+ Random.nextInt(-6,5), Random.nextInt(-1,5));
            ctx.input.move(door.centerPoint().x+Random.nextInt(-21,-10),door.centerPoint().y+Random.nextInt(-31,-10));
            door.interact("Open");
        }
    }
}
