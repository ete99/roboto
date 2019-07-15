package str.Tasks;


import idleChopper.script.AntibanScript;
import idleChopper.script.Util;
import idleChopper.utility.mailme;
import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import org.powerbot.script.rt4.GameObject;
import str.Task;
import str.cleaner;

import java.io.IOException;

import static idleChopper.script.Util.moveCamera;

public class goup extends Task {
    int HERB;
    public goup(ClientContext ctx, int HERB) {
        super(ctx);
        this.HERB=HERB;
    }
    @Override
    public boolean activate() {
        return ctx.players.local().tile().floor()==1 && ctx.inventory.select().id(HERB).count()==0;
    }

    @Override
    public void execute() {
        cleaner.status = "upping";
        openDoor();
        GameObject stair = ctx.objects.select().nearest().name("Staircase").peek();
        if(!stair.inViewport())
            ctx.camera.turnTo(stair,50);
        if(Random.nextDouble()<0.8) {
            stair.click();
            Condition.wait(()->ctx.players.local().inMotion());
            AntibanScript.moveMouseOffScreen(ctx,-1);
            if(ctx.players.local().inMotion()){
                Condition.wait(()->!ctx.players.local().inMotion(),30,100);
            }
            Condition.sleep(Random.nextInt(150,300));
            if(Random.nextDouble()<0.8)
                for (int i = 0; i < Random.nextInt(3,7); i++) {
                    ctx.input.send("1");
                    Condition.sleep(Random.nextInt(10,50));
                }
            else ctx.widgets.select().id(219).poll().component(1).component(1).click();

        } else stair.interact("Climb-up");
        AntibanScript.moveMouseOffScreen(ctx,-1);
        Condition.wait(()->ctx.players.local().tile().floor()==2,300,10);
        if(ctx.players.local().tile().floor()==1 && ctx.players.local().animation()==-1){
            openDoor();
        }
    }
    public void openDoor(){
        GameObject door = ctx.objects.select().name("Door").within(new Area(new Tile(3207,3213,1),new Tile(3209,3215,1))).peek();
        if(door.id() == 1543) {
            Util.moveCamera(ctx,270+ Random.nextInt(-6,5), Random.nextInt(-1,5));
            ctx.input.move(door.centerPoint().x+Random.nextInt(-21,-10),door.centerPoint().y+Random.nextInt(-31,-10));
            ctx.objects.select().name("Door").within(new Area(new Tile(3207,3213,1),new Tile(3209,3215,1))).poll().interact("Open");
            Condition.wait(()->ctx.objects.select().name("Door").within(new Area(new Tile(3207,3213,1),new Tile(3209,3215,1))).poll().id()==1544,60,20);
        }
    }
}
