package cleaner;

import cleaner.Tasks.check;
import cleaner.Tasks.clean;
import cleaner.Tasks.store;
import idleChopper.script.AntibanScript;
import org.powerbot.script.*;
import org.powerbot.script.rt4.*;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.List;


@Script.Manifest(name = "clean", properties = "author=ete; topic=1333332; client=4;", description = "jaja")
public class cleaner extends PollingScript<ClientContext>  {
        Component unselectedInventory=ctx.widgets.widget(164).component(53);
        Component inventory = ctx.widgets.widget(164).component(60);
    public static List<Task> taskList = new ArrayList<Task>();
    int GUAM = 199;
    public void start() {
        taskList.add(new check(ctx));
        taskList.add(new clean(ctx, GUAM));
        taskList.add(new store(ctx, GUAM));
//        taskList.add(new idleChopper.guild_magic_tasks.Chop(ctx));
    }

    public void poll() {
        if (unselectedInventory.textureId() == -1) {
            inventory.click();
        }
        for(Task task : taskList){

            if(ctx.controller.isStopping()){
                break;
            }

                if(task.activate()){
                    task.execute();
                    break;
                }
        }

    }

    @Override
    public void stop() {
        System.out.println("stop");
        AntibanScript.moveMouseOffScreen(ctx,-1);
    }

    public void bye(){
            AntibanScript.moveMouseOffScreen(ctx, -1);
            System.exit(1);
            Condition.sleep(300);
            while(true);
        }
    /**?
     * if(!stop) {
     *             if (unselectedInventory.textureId() == -1) {
     *                 inventory.click();
     *             }
     *             if (ctx.inventory.select().id(HERB).count() == 0) {
     *                 if (!openBank()) {
     *                     ctx.movement.step(ctx.players.local().tile());
     *                     openBank();
     *                 }
     *                 if (ctx.bank.opened()) {
     *                     ctx.bank.depositInventory();
     *
     *                     if (ctx.bank.opened() && ctx.bank.id(HERB).select().count() == 0) {
     *                         stop=true;
     *                     }
     *                     ctx.bank.select().id(HERB).poll().click();
     *                     Condition.wait(() -> ctx.inventory.isFull());
     *                     ctx.bank.close();
     *                 }
     *                 stop = false;
     *             }
     *             if (ctx.inventory.select().id(HERB).count() == 0)
     *                 stop = true;
     *             int inv = ctx.inventory.select().id(13419).count();
     *             ItemQuery<Item> i = ctx.inventory.select().id(HERB);
     *             for (Item k : i) {
     *                 k.hover();
     *                 Condition.sleep(Random.nextInt(30, 40));
     *                 k.click();
     *                 Condition.sleep(Random.nextInt(100, 150));
     *             }
     *         }
     */
    void miniAntiban(){
        AntibanScript.moveMouseOffScreen(ctx, -1,()->ctx.inventory.select().id(2114).count()==0);
        if (Random.nextDouble() < 0.01 ) {
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
}
