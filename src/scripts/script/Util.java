package scripts.script;

import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.MenuCommand;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.TilePath;
import scripts.Constants;
import scripts.SetUp;
import scripts.utility.mailme;


import java.awt.*;
import java.io.IOException;
import java.util.concurrent.Callable;

import static scripts.Constants.WC_ANIM;
import static scripts.quest101.setUp;
import static scripts.script.AntibanScript.moveMouseOffScreen;

public class Util {
    public static void Chop(GameObject Tree){
        System.out.println("wola");
        if(setUp.ctx.players.local().animation()!=WC_ANIM){
            boolean b=false;
            Tree.hover();
            Condition.sleep(150); // must have this sleep after hover
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
    //@TODO drag camera only horizontally
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
           dragCamera();
        }else {
            setUp.STATE = SetUp.State.BANKING;
            ctx.bank.open();
            ctx.bank.depositInventory();
            ctx.bank.withdraw(setUp.AXE_ID, 1);
            ctx.bank.close();
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
