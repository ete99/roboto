package scripts.script;

import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.MenuCommand;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.*;
import scripts.Constants;
import scripts.SetUp;
import scripts.quest101;
import scripts.utility.mailme;


import java.awt.*;
import java.io.IOException;
import java.util.concurrent.Callable;

import static scripts.Constants.WC_ANIM;
import static scripts.quest101.setUp;
import static scripts.script.AntibanScript.moveMouseOffScreen;

public class Util {
    public static void Chop(GameObject Tree){
        Condition.sleep(Random.nextInt(350, 500));
        if(!Tree.inViewport())
            dragUntilCamera(Tree);
        if(setUp.ctx.players.local().animation()!=WC_ANIM){
            boolean b=false;
            Tree.hover();
            Condition.sleep(Random.nextInt(150,200)); // must have this sleep after hover
            for (int i=0; i<2;i++)
                if(setUp.ctx.menu.commands()[0].toString().equals("Chop down "+ setUp.TREE_NAME))
                    b= b || Tree.interact("Chop");

            Condition.sleep(150);
            if(setUp.ctx.players.local().animation()!=WC_ANIM)
                    b=b || setUp.ctx.menu.click(menuCommand -> menuCommand.toString().equals("Chop down "+ setUp.TREE_NAME));
//            Tree.click();
            moveMouseOffScreen(setUp.ctx,-1);
            if(b)
                setUp.STATE = SetUp.State.CHOPPING;
            else
                ViewCheck();

            if (setUp.ctx.players.local().animation() != -1 && WC_ANIM == 0)
                Constants.WC_ANIM = setUp.ctx.players.local().animation();
        }
        if(Constants.WC_ANIM != setUp.ctx.players.local().animation() && setUp.STATE == SetUp.State.CHOPPING){
            Condition.sleep(600);
            ViewCheck();
        }
    }
    //@TODO on walk, moveOffScreen to the right, not left
    public static void walkToBank() throws Exception {
        TilePath path = setUp.ctx.movement.newTilePath(setUp.RIDE);
        if(path.valid()) {
            path.randomize(1, 1);
            path.traverse();
        } else {
            LocalPath toBank = setUp.ctx.movement.findPath(setUp.RIDE[setUp.RIDE.length-1]);
            if (!toBank.valid()) {
                throw new Exception("Cant reach bank");
            }
            toBank.traverse();
        }
        if(setUp.ctx.players.local().animation()!=-1 && Constants.RUN_ANIM==0)
            Constants.RUN_ANIM = setUp.ctx.players.local().animation();
        moveCamera(setUp.ctx.camera.yaw() + Random.nextInt(-50, 50), setUp.ctx.camera.pitch() + Random.nextInt(-10, 10));
        moveMouseOffScreen(setUp.ctx,-1);
    }
    public static void walkToTree(){
        TilePath path = setUp.ctx.movement.newTilePath(setUp.RIDE);
        path.reverse();
        path.randomize(1, 1);
        path.traverse();
        if (setUp.ctx.players.local().animation() != -1 && Constants.RUN_ANIM == 0)
            Constants.RUN_ANIM = setUp.ctx.players.local().animation();
        if(Random.nextDouble()>0.5)
            moveCamera(setUp.ctx.camera.yaw() + Random.nextInt(-50, 50), setUp.ctx.camera.pitch() + Random.nextInt(-10, 10));
        moveMouseOffScreen(setUp.ctx,-1);
    }

    public static void moveCamera(final int angle, final int pitch){
        Thread t1 = new Thread(()-> setUp.ctx.camera.pitch(pitch));
        Thread t2 = new Thread(()-> setUp.ctx.camera.angle(angle));
        t1.start();
        t2.start();
    }

    // @TODO: fix to only hold mouse once until hit the game object
    public static void dragUntilCamera(GameObject obj){
        for(int br=0; br<2; br++) {
            Point pre = new Point(60 + Random.nextInt(-50, 50), 180 + Random.nextInt(-50, 50));
            setUp.ctx.input.move( pre );
            Point post = new Point(300 + Random.nextInt(-50, 50), 400 + Random.nextInt(-50, 50));
            setUp.ctx.input.drag( post , 2);
            if(!obj.inViewport()) {
                post.x += Random.nextInt(-20,20);
                post.y += Random.nextInt(-20,20);
                setUp.ctx.input.move( post );
                pre.x = post.x + 100 + Random.nextInt(-50, 50);
                pre.y = post.y - 200 + Random.nextInt(-50, 50);
                setUp.ctx.input.drag(pre, 2);
            } else break;
        }
    }
    public static void dragCamera(boolean second){
        Point pre = new Point(60 + Random.nextInt(-50, 50), 180 + Random.nextInt(-50, 50));
        setUp.ctx.input.move( pre );
        Point post = new Point(300 + Random.nextInt(-50, 50), 400 + Random.nextInt(-50, 50));
        setUp.ctx.input.drag( post , 2);
        if(second) {
            post.x += Random.nextInt(-20,20);
            post.y += Random.nextInt(-20,20);
            setUp.ctx.input.move( post );
            pre.x = post.x + 100 + Random.nextInt(-50, 50);
            pre.y = post.y - 200 + Random.nextInt(-50, 50);
            setUp.ctx.input.drag(pre, 2);
        }
    }
    public static void dragCamera() {
        dragCamera(true);
    }


    public static void pickAxe(){
        ClientContext ctx = setUp.ctx;
        ctx.camera.angle(ctx.camera.yaw() + Random.nextInt(-50, 50));
        if(!ctx.bank.inViewport() && ctx.bank.close()){
           dragCamera();
        }else {
            setUp.STATE = SetUp.State.BANKING;
            ctx.bank.open();
            ctx.bank.depositInventory();
            ctx.bank.withdraw(setUp.AXE_ID, 1);
            ctx.bank.close();
        }
    }

    private static void ViewCheck(){
        ClientContext ctx = setUp.ctx;
        BasicQuery<GameObject> TREES = ctx.objects.select().name(setUp.TREE_NAME).within(setUp.TREE_AREA).nearest();
        GameObject Tree = TREES.poll();
        int now = ctx.camera.yaw();
        if(!(TREES.size()==0)) {
//            if (ctx.camera.pitch() > 50)
//                ctx.camera.pitch();
            if ((ctx.players.local().animation() == -1) && Tree.inViewport()) {
                ctx.input.move(Tree.nextPoint());

                if(Random.nextDouble()>0.5) {
                    for(int deg = 0; deg<=360 && !Tree.inViewport();deg+=Random.nextInt(20,50)) {
                        moveCamera(now-deg, 99);

                        dragUntilCamera(Tree);
//                        moveCamera(now+deg, 99);
//                        ctx.camera.angle(now + deg);
                    }
                }else{
                    for(int deg = 0; deg<=360 && !Tree.inViewport();deg+=Random.nextInt(20,50)) {
                        moveCamera(now-deg, 99);
                        dragUntilCamera(Tree);
//                        ctx.camera.angle(now - deg);
                    }
                }
            }
        } else if (setUp.STATE != SetUp.State.WAITING){
            System.out.println("waiting");
            // waits for a new yew tree
            if(Random.nextDouble()>0.5) {
                moveCamera(now + 180 + Random.nextInt(-90,90), Random.nextInt(80,99));
            }else{
                moveCamera(now - 180 + Random.nextInt(-90,90), Random.nextInt(80,99));
            }
            Condition.wait(() -> ctx.objects.select().within(setUp.TREE_AREA).id(setUp.TREE_ID).size()!=0,800,20);
        }
    }

    public static void sendMail(){
        if(setUp.mail)
            try {
            mailme.sendPOST("paso algo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openDoor(){
        final GameObject DOOR = setUp.ctx.objects.select().id(1543).nearest().poll();
        if(DOOR.valid()) {
//            System.out.println("ESNTER");
            moveCamera(90 + Random.nextInt(0,20), Random.nextInt(0,10));
//            ctx.camera.angle(90 + Random.nextInt(0,20));
            DOOR.interact("Open");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return DOOR.id()==1544;
                }
            }, 400, 4);
        }
    }

    public static void sendMail(String str){
        if(setUp.mail)
        try {
            mailme.sendPOST(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
