package Ruby.Tasks;


import Ruby.Task;
import idleChopper.script.AntibanScript;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

import static idleChopper.script.Util.moveCamera;

public class check extends Task {
    int GUAM = 249;
    int VIAL = 249;

    public check(ClientContext ctx,int GUAM,int VIAL) {
        super(ctx);
        this.VIAL = VIAL;
        this.GUAM = GUAM;
    }
    Component c;

    @Override
    public boolean activate() {
        if(Random.nextDouble()<0.01){
            AntibanScript.moveMouseOffScreen(ctx,-1);
            Condition.sleep(Random.nextInt(3000,5000));
        }
        c = ctx.widgets.select().id(270).poll().component(14);
        return c.visible();
    }

    @Override
    public void execute() {
        Condition.sleep(Random.nextInt(150,350));
        ctx.input.send("1");
//        c.click();
        miniAntiban();
        Condition.sleep(Random.nextInt(100,150));
    }
    void miniAntiban(){
        AntibanScript.moveMouseOffScreen(ctx, 1,()->ctx.inventory.select().id(VIAL).count()==0,false);
        Condition.wait(()->ctx.inventory.select().id(VIAL).count()==0, Random.nextInt(100,120),500);

        if (Random.nextDouble() < 0.01) {
            AntibanScript.moveMouseOffScreen(ctx, -1);
            Condition.sleep(Random.nextInt(3000, 7000));
            moveCamera(ctx,Random.nextInt(-10, 10), Random.nextInt(80, 99));
        }
    }
}
