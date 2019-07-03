package fletch.Tasks;


import fletch.Task;
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
        if(Integer.parseInt(ctx.widgets.widget(320).component(14).component(4).text())<40)
            ctx.input.send("2");
        else
            ctx.input.send("3");
//        c.click();
        miniAntiban();
        Condition.sleep(Random.nextInt(100,150));
    }
    void miniAntiban(){
        AntibanScript.moveMouseOffScreen(ctx, 1,()->ctx.inventory.select().id(VIAL).count()==0 || ctx.players.local().animation()==-1,false);
        Condition.wait(()->ctx.inventory.select().id(VIAL).count()==0  || ctx.players.local().animation()==-1, Random.nextInt(100,120),250);

        if (Random.nextDouble() < 0.01) {
            AntibanScript.moveMouseOffScreen(ctx, -1);
            Condition.sleep(Random.nextInt(3000, 7000));
            moveCamera(ctx,Random.nextInt(-10, 10), Random.nextInt(80, 99));
        }
    }
}
