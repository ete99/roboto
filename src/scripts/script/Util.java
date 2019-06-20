package scripts.script;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.TilePath;
import scripts.Constants;
import scripts.SetUp;
import scripts.mailme;


import java.awt.*;
import java.io.IOException;
import java.util.concurrent.Callable;

import static scripts.Constants.WC_ANIM;
import static scripts.quest101.setUp;
import static scripts.script.AntibanScript.moveMouseOffScreen;

public class Util {
    public static void Chop(GameObject Tree){
        if(setUp.ctx.players.local().animation()!=WC_ANIM) {
            for (int i=0; i<Random.nextInt(1,3);i++)
                Tree.interact("Chop");
//            Tree.click();
            moveMouseOffScreen(setUp.ctx,-1);

            if (setUp.ctx.players.local().animation() != -1 && WC_ANIM == 0)
                Constants.WC_ANIM = setUp.ctx.players.local().animation();
        }
    }
    //@TODO on walk, moveOffScreen to the right, not left
    public static void walkToBank(){
        TilePath path = setUp.ctx.movement.newTilePath(setUp.RIDE);
        path.randomize(1, 1);
        path.traverse();
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

    public static void dragUntilCamera(GameObject obj){
        for(int br=0; br<2; br++) {
            Point pre = new Point(60 + Random.nextInt(-50, 50), 180 + Random.nextInt(-50, 50));
            setUp.ctx.input.move( pre );
            Point post = new Point(300 + Random.nextInt(-50, 50), 400 + Random.nextInt(-50, 50));
            setUp.ctx.input.drag( post , 2);
            if(!obj.inViewport()) {
                pre.x = post.x + 100 + Random.nextInt(-50, 50);
                pre.y = post.y - 200 + Random.nextInt(-50, 50);
                setUp.ctx.input.drag(pre, 2);
            } else break;
        }
    }

    public static void dragCamera(){
        Point pre = new Point(60 + Random.nextInt(-50, 50), 180 + Random.nextInt(-50, 50));
        setUp.ctx.input.move( pre );
        Point post = new Point(300 + Random.nextInt(-50, 50), 400 + Random.nextInt(-50, 50));
        setUp.ctx.input.drag( post , 2);
        pre.x = post.x + 100 + Random.nextInt(-50, 50);
        pre.y = post.y - 200 + Random.nextInt(-50, 50);
        setUp.ctx.input.drag(pre, 2);
    }



    public static void pickAxe(){
        ClientContext ctx = setUp.ctx;
        ctx.camera.angle(ctx.camera.yaw() + Random.nextInt(-50, 50));
        if(!ctx.bank.inViewport() && ctx.bank.close()){
            ctx.camera.pitch(99-Random.nextInt(0, 3));
        }else {
            setUp.STATE = SetUp.State.BANKING;
            ctx.bank.open();
            ctx.bank.withdraw(setUp.AXE_ID, 1);
            ctx.bank.close();
        }
    }
    public static void sendMail(){
        if(!setUp.debug)
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
        if(!setUp.debug)
        try {
            mailme.sendPOST(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
