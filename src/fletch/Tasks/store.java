package fletch.Tasks;


import fletch.Task;
import idleChopper.script.AntibanScript;
import idleChopper.script.Util;
import idleChopper.utility.mailme;
import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.MenuCommand;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Npc;

import java.io.IOException;

public class store extends Task {
    int HERB;
    int VIAL;
    public store(ClientContext ctx, int HERB,int VIAL) {
        super(ctx);
        this.HERB=HERB;
        this.VIAL=VIAL;
    }
    static boolean f;
    @Override
    public boolean activate() {
        return (ctx.inventory.select().id(HERB).count() == 0||ctx.inventory.select().id(VIAL).count() == 0) && !f;
    }

    @Override
    public void execute() {
        if (!Util.openBank(ctx,false)) {
            ctx.inventory.select().poll().click();
        }
        if (ctx.bank.opened()) {
            if (ctx.bank.select().id(VIAL).count()==0) {
                f=true;
                System.out.println("hola");
                try {
                    mailme.sendPOST("c acabo","");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("c acabo");
            } else {
                for (Item i : ctx.inventory.select()) {
                    if(i.id()!=HERB) {
                        i.click();
                        Condition.wait(()->!i.valid(),300,10);
                        break;
                    }
                }
                Condition.sleep(Random.nextInt(250, 350));
                if(ctx.inventory.select().id(HERB).count()==0)
                    ctx.bank.select().id(HERB).poll().click();

//                ctx.bank.select().id(HERB).poll().click();
//                Condition.wait(() -> ctx.inventory.select().id(HERB).count() != 0, 300, 3);
//                Condition.sleep(Random.nextInt(200, 350));

                if (ctx.bank.id(VIAL).select().count() == 0) {
                    throw new RuntimeException("c acabo");
                }
                ctx.bank.select().id(VIAL).poll().click();
                Condition.wait(() -> ctx.inventory.select().id(VIAL).count() != 0, 150, 3);
                ctx.bank.close();
            }
        }
    }
    public boolean openBanked(){
        if(!ctx.bank.opened()) {
            Thread t1 = new Thread(() -> ctx.camera.pitch(99));
            Thread t2 = new Thread(() -> ctx.camera.turnTo(ctx.bank.nearest().tile(), 7));
            t1.start();
            t2.start();
            final Filter<MenuCommand> filter = new Filter<MenuCommand>() {
                public boolean accept(MenuCommand command) {
                    String action = command.action;
                    return action.equalsIgnoreCase("Bank") || action.equalsIgnoreCase("Use") || action.equalsIgnoreCase("Open");
                }
            };
            final Npc bank = (Npc)ctx.bank.nearest();
            bank.hover();
            boolean b = bank.interact(filter);
            AntibanScript.moveMouseOffScreen(ctx, 0, () -> ctx.bank.opened() || !ctx.players.local().inMotion());
            if(b && !ctx.bank.opened())
                for(int i=0;i<4;i++)
                    openBankedN();
            return b;
        } else return true;
    }

    public boolean openBankedN(){
        if(!ctx.bank.opened()) {
            boolean b=false;
            for (int i=0; i<4 && !b; i++) {
                Thread t1 = new Thread(() -> ctx.camera.pitch(99));
                Thread t2 = new Thread(() -> ctx.camera.turnTo(ctx.bank.nearest().tile(), 35));
                t1.start();
                t2.start();
                final Filter<MenuCommand> filter = new Filter<MenuCommand>() {
                    public boolean accept(MenuCommand command) {
                        String action = command.action;
                        return action.equalsIgnoreCase("Bank") || action.equalsIgnoreCase("Use") || action.equalsIgnoreCase("Open");
                    }
                };
                final Npc bank = (Npc) ctx.bank.nearest();
                bank.hover();
                b = bank.interact(filter);
                AntibanScript.moveMouseOffScreen(ctx, 1, () -> ctx.bank.opened() || !ctx.players.local().inMotion());
            }
            return b;
        } else return true;
    }
}
