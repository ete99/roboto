package scripts2.tasks.Util;


import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;
import scripts2.Task;
import org.powerbot.script.rt4.Players;

import static scripts2.Constants.WC_ANIM;

public class PosUtil extends Task {
    public PosUtil(ClientContext ctx) {
        super(ctx);
    }
    GameObject Tree;
    @Override
    public boolean activate() {
//        System.out.println(ctx.worlds.open());

//        System.out.println(ctx.inventory.name("Willow Logs").peek().inViewport());
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
//        System.out.println(ctx.players.local().orientation());
        int t = ctx.camera.yaw();
        if(WC_ANIM!=5) {
            ctx.camera.angle(t+180);
            System.out.println("moveds");
        }
        WC_ANIM=5;
//        System.out.println("Pool");
//        System.out.println(ctx.inventory.select().poll().name().split(" ")[1]);
//        ctx.bank.open();
//        ctx.bank.depositAllExcept(ctx.inventory.select().poll().id());

//        Area d =  new Area(new Tile(ctx.players.local().tile().x(), ctx.players.local().tile().y()));
//        Area d2 =  new Area(new Tile(3181, 3489), new Tile(3180, 3488));
//        System.out.println(d.contains(ctx.players.local()));
//        System.out.println(d2.contains(ctx.players.local()));
//        System.out.println(ctx.players.local().tile().toString());

    }
}