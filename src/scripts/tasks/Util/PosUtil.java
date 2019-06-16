package scripts.tasks.Util;


import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.TilePath;
import scripts.Task;
import yew.AREA;

import java.awt.*;
import java.util.concurrent.Callable;

public class PosUtil extends Task {
    public PosUtil(ClientContext ctx) {
        super(ctx);
    }
    GameObject Tree;
    @Override
    public boolean activate() {
        System.out.println(ctx.inventory.contains(ctx.inventory.name("Willow Logs").peek()));
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
        return false;
    }
    @Override
    public void execute() {
//        Area d =  new Area(new Tile(ctx.players.local().tile().x(), ctx.players.local().tile().y()));
//        Area d2 =  new Area(new Tile(3181, 3489), new Tile(3180, 3488));
//        System.out.println(d.contains(ctx.players.local()));
//        System.out.println(d2.contains(ctx.players.local()));
        System.out.println(ctx.players.local().tile().toString());

    }
}