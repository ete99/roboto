package saltpetre;

import org.powerbot.script.*;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Item;
import scripts.script.AntibanScript;
import scripts.script.Util;


@Script.Manifest(name = "salt", properties = "author=ete; topic=1333332; client=4;", description = "jaja")
public class saltpetre extends PollingScript<ClientContext> implements MessageListener {

    public void start() {

    }

    boolean stop = false;
    public void poll() {
        if(ctx.inventory.select().name("Saltpetre").count()==0 || ctx.inventory.select().name("Compost").count()==0){
            ctx.bank.open();
            ctx.bank.select().name("Saltpetre").poll().click();
            Condition.wait(()->ctx.inventory.isFull());
            ctx.bank.depositAllExcept("Saltpetre");
            if (ctx.bank.opened() && ctx.bank.name("Saltpetre").select().count()<6) {
                throw new RuntimeException("no hay mas");
            }
//            ctx.bank.withdraw(2114,27);
            ctx.bank.select().name("Compost").poll().click();
            ctx.bank.close();
            stop =false;
//            if(ctx.inventory.select().id(2114).count()==0)
//                throw new RuntimeException("no hay mas");
        }
//        if (!stop && ctx.players.local().animation()==-1) {
//            Condition.sleep(Random.nextInt(30, 50));
//            Component c = ctx.widgets.select().id(270).poll().component(15);
//            if(c.visible()) {
//                c.click();
//                stop=true;
//                miniAntiban();
//                Condition.sleep(Random.nextInt(100, 150));
//            }
//            else
                if(ctx.inventory.select().name("Saltpetre").count()==0)
                    stop=true;
                int inv=ctx.inventory.select().id(13419).count();
                if(ctx.inventory.select().id(13421).poll().interact("Use")){
//                    Condition.sleep(Random.nextInt(100, 150));
                    Item pine = ctx.inventory.select().id(6032).poll();
                    pine.hover();
                    pine.click();
                    Condition.wait(()->ctx.inventory.select().id(13419).count()>inv, Random.nextInt(50,100),10);


//                    if (ctx.menu.commands()[0].toString().contains("Knife"))
//                        ctx.input.click(true);
//            stop=stop && ctx.menu.click(menuCommand -> menuCommand.toString().contains("Knife"));
//                    Condition.sleep(Random.nextInt(250, 350));
                }


        }
    void miniAntiban(){
        AntibanScript.moveMouseOffScreen(ctx, -1,()->ctx.inventory.select().id(2114).count()==0);
        if (Random.nextDouble() < 0.01 && !stop) {
            AntibanScript.moveMouseOffScreen(ctx, -1);
            Condition.sleep(Random.nextInt(3000, 7000));
            moveCamera(Random.nextInt(-10, 10), Random.nextInt(80, 99));
        }
    }
    void moveCamera(int angle, int pitch) {

        Thread t1 = new Thread(() -> ctx.camera.pitch(pitch));
        Thread t2 = new Thread(() -> ctx.camera.angle(angle));
            t1.start();
            t2.start();
    }

    @Override
    public void messaged(MessageEvent me) {
        String msg = me.text();
        if(msg.contains("Your foe's"))
        {
            System.out.println("little wait");
            this.stop =true;
            this.suspend();
            Condition.sleep(Random.nextInt(500, 1000));
            AntibanScript.moveMouseOffScreen(ctx,-1);
            this.resume();
            this.stop = false;
        }
        if(msg.contains("to cast this spell")){
            System.out.println("henloo");
            stop = true;
            this.stop();
            Condition.sleep(Random.nextInt(1000,2000));
            AntibanScript.moveMouseOffScreen(ctx,-1);
            Util.sendMail("paro magic");
        }
    }
}
