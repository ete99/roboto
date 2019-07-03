package idleChopper.misc_tasks;


import idleChopper.script.AntibanScript;
import org.powerbot.script.Client;
import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.MenuCommand;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import idleChopper.utility.Task;
import org.powerbot.script.rt4.Npc;

import java.awt.*;

import static idleChopper.quest101.setUp;
import static idleChopper.script.Util.openBank;
import static idleChopper.script.Util.sendMail;
import static org.powerbot.script.rt4.Magic.Spell.WEAKEN;


public class Tester extends Task {
    public Tester(ClientContext ctx) {
        super(ctx);
    }
    GameObject Tree;

    public boolean openBanked(){
        System.out.println("open");
        if(!ctx.bank.opened()) {
            ctx.camera.turnTo(ctx.bank.nearest().tile(), 7);
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
        System.out.println("opeN");
        if(!ctx.bank.opened()) {
            ctx.camera.turnTo(ctx.bank.nearest().tile(), 7);
            final Filter<MenuCommand> filter = new Filter<MenuCommand>() {
                public boolean accept(MenuCommand command) {
                    String action = command.action;
                    return action.equalsIgnoreCase("Bank") || action.equalsIgnoreCase("Use") || action.equalsIgnoreCase("Open");
                }
            };
            final Npc bank = (Npc)ctx.bank.nearest();
            bank.hover();
            boolean b = bank.interact(filter);
            AntibanScript.moveMouseOffScreen(ctx, 1, () -> ctx.bank.opened() || !ctx.players.local().inMotion());
            return b;
        } else return true;
    }

    @Override
    public boolean activate() {
        for (int i = 0; i < ctx.skills.experiences().length; i++) {

            System.out.println(i+" "+ctx.skills.level(i));

        }

//        ctx.magic.cast(WEAKEN);
//        System.out.println(ctx.players.local().animation());
//        System.out.println(ctx.combat.specialAttack(true));
//        System.out.println(ctx.varpbits.varpbit(300));

//        Thread t1 = new Thread(()-> setUp.ctx.camera.pitch(99));
//        t1.run();
//        System.out.println((Npc)ctx.bank.nearest());
//        Npc bank = (Npc)ctx.bank.nearest();
//        bank.hover();
//        openBanked();
//        for (int level : ctx.skills.levels()) {
//            System.out.println(level);
//        }

//        openBank();
//        System.out.println("hola");
//        System.out.println(ctx.bank.opened());
//
//        AntibanScript.moveMouseOffScreen(ctx, 0, () -> !ctx.bank.opened());
//
//        System.out.println("chau");
//        openBank();
//        System.out.println(ctx.widgets.select().id(320).poll().component(22).component(4).text());
//        System.out.println(ctx.objects.select().name("Door").nearest().poll().id());
//        Tree = ctx.objects.select().name("Oak").nearest().poll();
//        System.out.println(Tree.inViewport());
//        int i = 1;
//        while(!Tree.inViewport()) {
////            ctx.input.drag(new Point(ctx.players.local().tile().x(), ctx.players.local().tile().y()),true);
//            ctx.camera.pitch(ctx.camera.pitch() + i++);
//            ctx.camera.angleTo(ctx.camera.yaw() + i++);
//        }
//        System.out.println(ctx.camera.pitch());
//        System.out.println(ctx.camera.yaw());
//        System.out.println(Tree.inViewport());
//        ctx.camera.pitch(ctx.camera.pitch() + 100);
//        int p = ctx.camera.pitch();
//        while (p==ctx.camera.pitch()){
//            ctx.camera.pitch(ctx.camera.pitch() - 1);
//            if(ctx.camera.pitch() == p)
//                break;
//            p = ctx.camera.pitch();
//        }
//                System.out.println(ctx.widgets.component(164, 51).textureId());
//        System.out.println("hola");
//
//        Condition.wait(new Callable<Boolean>(){
//            @Override
//            public Boolean call() {
//                return ctx.players.local().animation()!=-1;
//            }
//        }, 2000, 100);
//        System.out.println("chau");
        return true;
    }


    @Override
    public void execute() {
//        ctx.menu.commands()
//            System.out.println(i.toString());
//        ctx.input.move(100,100);
//        ctx.input.drag(new Point(ctx.players.local().centerPoint().x+20,ctx.players.local().centerPoint().y),2);
//        ctx.camera.pitch(100);
//        ctx.camera.angle(360);
        /*
        Thread t1 = new Thread(()-> ctx.camera.pitch(100));
        Thread t2 = new Thread(()-> ctx.camera.angle(360));
        t1.start();
        t2.start();
        */
        Condition.sleep(1000);
//        Condition.sleep(60000);
//        System.out.println(YEW_AREA.contains(ctx.players.local()));
//        BasicQuery<GameObject> k = ctx.objects.select().within(YEW_AREA).name("Yew");
//        Area walker =  new Area(new Tile(ctx.players.local().tile().x(), ctx.players.local().tile().y()));
//        Area d2 =  new Area(new Tile(3181, 3489), new Tile(3180, 3488));
//        System.out.println(walker.contains(ctx.players.local()));
//        System.out.println(d2.contains(ctx.players.local()));
//        System.out.println(ctx.players.local().tile().toString());

    }
}