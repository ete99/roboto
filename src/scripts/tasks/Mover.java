package scripts.tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.Locatable;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;
import org.powerbot.script.rt4.Component;
import scripts.Task;

import java.awt.*;
import java.util.List;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.Callable;

public class Mover extends Task {

    final static int ROCK_IDS[] = { 7453, 7484 };

    Tile rockLocation = Tile.NIL;

    public Mover(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        Boolean r = ctx.game.loggedIn();
           BasicQuery<Npc> npc = ctx.npcs.select().nearest();
           Npc g1 = npc.poll();
//           ctx.input.move(g1.nextPoint());
//           g1.click();
           g1.click("Examine");
        System.out.println("noc:"+ctx.input.move(g1.nextPoint()));
        System.out.println(g1.id());
//           g1.click();
//           List np = ctx.npcs.get();

//        System.out.println(npc.select().poll().id());
//        Components comp = ctx.components.widget(558);
//        Component nametxt = ctx.widgets.widget(558).component(11);
        int c = ctx.widgets.first().peek().id();
//        Iterator<Widget> i = ctx.widgets.select().iterator();
//        while(i.hasNext()){
//            Widget w= i.next();
//            if(w.componentCount()>0)
//                for(Component m : w.components())
//                    if(m.actions().length>0)
//                        System.out.println(m.actions()[0]);
//        }
        System.out.println("numbero"+c);
//        Component nametxt = ctx.widgets.widget(263).component(1).component(0);
        Widget nametxt = ctx.widgets.widget(263);
        System.out.println("nombre:" + nametxt.component(10).interact("C"));
//        Rectangle r = nametxt.boundingRect();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void execute() {
//        GameObject d = ctx.menu.bounds().getBounds();
//        GameObject rockToMine = ctx.objects.select().id(ROCK_IDS).nearest().poll();
//        rockLocation = rockToMine.tile();
//        rockToMine.interact("Mine");
//
//        Condition.wait(new Callable<Boolean>(){
//            @Override
//            public Boolean call() throws Exception {
//                return ctx.players.local().animation()!=-1;
//            }
//        }, 200, 10);
    }
}
