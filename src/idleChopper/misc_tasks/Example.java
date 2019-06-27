package idleChopper.misc_tasks;


import org.powerbot.script.Tile;
import org.powerbot.script.rt4.BasicQuery;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;
import org.powerbot.script.rt4.Widget;
import idleChopper.utility.Task;

public class Example extends Task {

    final static int ROCK_IDS[] = { 7453, 7484 };

    Tile rockLocation = Tile.NIL;

    public Example(ClientContext ctx) {
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
//                for(Component mandamail : w.components())
//                    if(mandamail.actions().length>0)
//                        System.out.println(mandamail.actions()[0]);
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
//        GameObject walker = ctx.menu.bounds().getBounds();
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
