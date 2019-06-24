package scripts.misc_tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import scripts.utility.Task;
import static scripts.quest101.setUp;


public class Tester extends Task {
    public Tester(ClientContext ctx) {
        super(ctx);
    }
    GameObject Tree;
    @Override
    public boolean activate() {

        System.out.println("pree");
        System.out.println(!(ctx.players.local().animation()==-1));
        Condition.wait( ()->!(ctx.players.local().animation()==-1), 400,20);
        System.out.println("posrt");



        System.out.println("PRE");

        System.out.println(!(ctx.objects.select().id(setUp.TREE_ID).size()>0));
        Condition.wait(() -> !(ctx.objects.select().id(setUp.TREE_ID).size()>0), 400,20);
        System.out.println("POST");

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
//        Area d =  new Area(new Tile(ctx.players.local().tile().x(), ctx.players.local().tile().y()));
//        Area d2 =  new Area(new Tile(3181, 3489), new Tile(3180, 3488));
//        System.out.println(d.contains(ctx.players.local()));
//        System.out.println(d2.contains(ctx.players.local()));
//        System.out.println(ctx.players.local().tile().toString());

    }
}