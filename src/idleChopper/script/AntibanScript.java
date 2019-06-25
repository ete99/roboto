package idleChopper.script;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Player;

import java.awt.*;
import java.util.concurrent.Callable;

import static idleChopper.quest101.setUp;

public class AntibanScript {
    final int SKILL_WC = 22;
    ClientContext ctx;
    public AntibanScript(ClientContext ctx) {
        this.ctx = ctx;
    }

//    public void doAntibanAction(int antibanAction){
//        if (antibanAction == 1 || antibanAction == 2){
//        } else if (antibanAction == 3 || antibanAction == 4){
//
//            randomMouseMovement();
//        } else if (antibanAction == 5 || antibanAction == 6){
//            hoverOverRandomPlayer();
//        } else if (antibanAction == 7 || antibanAction == 8){
//            randomCameraTurn();
//        } else if (antibanAction == 9 || antibanAction == 10){
//            moveMouseOffScreen(2);
//        }
//    }

//    public void hoverOverSkill(int skillNumber){
//        if (ctx.widgets.component(164, 51).valid()){
////            ctx.widgets.component(164, 51).hover();
//            Condition.sleep(Random.nextInt(250, 500));
//            if(ctx.widgets.component(164, 51).textureId() == -1)
//                ctx.widgets.component(164, 51).click();
//            if (ctx.widgets.component(320, skillNumber).valid()){
//                if(ctx.widgets.component(320, skillNumber).visible())
//                    ctx.widgets.component(320, skillNumber).hover();
//                Condition.sleep(Random.nextInt(3000, 5000));
//            }
//        }
//    }

    /**
     * Author - Enfilade Moves the mouse a random distance between minDistance
     * and maxDistance from the current position of the mouse by generating
     * random vector and then multiplying it by a random number between
     * minDistance and maxDistance. The maximum distance is cut short if the
     * mouse would go off screen in the direction it chose.
     *
     *
     * The minimum distance the cursor will move
     *
     * The maximum distance the cursor will move (exclusive)
     */
    public static void randomMouseMovement(final ClientContext ctx) {
        int minDistance = Random.nextInt(50, 100);
        int maxDistance = Random.nextInt(250, 300);
        double xvec = Math.random();
        if (Random.nextInt(0, 2) == 1) {
            xvec = -xvec;
        }
        double yvec = Math.sqrt(1 - xvec * xvec);
        if (Random.nextInt(0, 2) == 1) {
            yvec = -yvec;
        }
        double distance = maxDistance;
        Point p = ctx.input.getLocation();
        int maxX = (int) Math.round(xvec * distance + p.x);
        distance -= Math.abs((maxX - Math.max(0,
                Math.min(ctx.game.dimensions().getWidth(), maxX)))
                / xvec);
        int maxY = (int) Math.round(yvec * distance + p.y);
        distance -= Math.abs((maxY - Math.max(0,
                Math.min(ctx.game.dimensions().getHeight(), maxY)))
                / yvec);
        if (distance < minDistance) {
            return;
        }
        distance = Random.nextInt(minDistance, (int) distance);
        ctx.input.move((int) (xvec * distance) + p.x, (int) (yvec * distance) + p.y);
    }

    public void hoverOverRandomPlayer(final ClientContext ctx){
        Player playerToHover = ctx.players.select().nearest().poll();
        if (!playerToHover.inViewport()){
            ctx.camera.turnTo(playerToHover);
        }
        if (playerToHover.valid()){
            playerToHover.hover();
            Condition.sleep(Random.nextInt(2500, 4000));
        }
    }

    /**
     * Author: Ryukis215
     */
    public static void randomCameraTurn(final ClientContext ctx){
        int randNum = Random.nextInt(1, 2);
        if(randNum == 1){//light camera turn
            ctx.camera.angle(ctx.camera.yaw() + Random.nextInt(-100, 100));
        }
        if(randNum == 2){//aggressive camera turn
            randNum = Random.nextInt(2, 6);
            for(int i = 0; i<randNum; i++){
                ctx.camera.angle(ctx.camera.yaw() + Random.nextInt(-100, 100));
                Condition.sleep(Random.nextInt(250, 500));
            }
        }
    }
    public static void moveMouseOffScreen(final ClientContext ctx, int level){
        moveMouseOffScreen(ctx,level,()->ctx.players.local().animation()==-1);
    }
    public static void moveMouseOffScreen(final ClientContext ctx, int level, Callable<Boolean> cond){
        System.out.println(0);
        int x, y;
        int w,h;
        w=ctx.game.dimensions().width;
        h=ctx.game.dimensions().height;
        if (Random.nextDouble() > 0.50) {
            x = -10;
        } else {
            x = Random.nextInt(0,w/2);

        }
        if(x>0) {
            if (Random.nextDouble() > 0.50) {
                y = -10;
            } else {
                y = h + 10;
            }
        } else{
            y=Random.nextInt(-50,w+50);
        }
        ctx.input.move(x, y);

        //@TODO Remove waits, add if not chopping in check valid
        System.out.println(1);
        ctx.input.defocus();
        if(level>0)
            Condition.sleep(Random.nextInt(3000, 5000));
        int t=Random.nextInt(0, 101);
        if(t>99 && level>=3){
            System.out.println("The long wait");
            Condition.sleep(Random.nextInt(15000, 28000));
        }
        else if(t>70 && level >=2){
            Condition.sleep(Random.nextInt(5000, 7500));
        }
        else if (level>=1) {
            Condition.sleep(Random.nextInt(1000, 3000));
        }
        System.out.println(2);
        if(level>0)
            Condition.wait(cond, 1000, 100);
        else if(level == 0) {
            System.out.println("here");
            Condition.wait(cond, 150, 8);
        }
        else {
            Condition.sleep(400);
        }
        ctx.input.focus();
//        x = Random.nextInt(10, 510);
//        y = Random.nextInt(10, 330);
//        ctx.input.move(x, y);
//        Condition.sleep(Random.nextInt(250, 500));
    }


    public static void antibanned(int level){
//        System.out.println("AntiBanning");
        if (Random.nextDouble() > 0.80){
//            AntibanScript.randomCameraTurn(ctx);
            if (Random.nextDouble() > 0.5){
                Thread t1 = new Thread(() -> Util.moveCamera(Random.nextInt(-90, 90), Random.nextInt(50, 99)));
                Thread t2 = new Thread(()-> AntibanScript.randomMouseMovement(setUp.ctx));
                t1.start();
                t2.start();
            }
            else
                Util.dragCamera();

//            Util.moveCamera(Random.nextInt(-90,90),Random.nextInt(50,99));
//            AntibanScript.randomMouseMovement(ctx);
        }
        else {
            AntibanScript.moveMouseOffScreen(setUp.ctx,level);
        }
    }
    public static void antibanned(int level, Callable<Boolean> cond){
//        System.out.println("AntiBanning");
        if (Random.nextDouble() > 0.80){
//            AntibanScript.randomCameraTurn(ctx);
            if (Random.nextDouble() < 0.3){
                Thread t1 = new Thread(() -> Util.moveCamera(Random.nextInt(-90, 90), Random.nextInt(50, 99)));
                Thread t2 = new Thread(()-> AntibanScript.randomMouseMovement(setUp.ctx));
                t1.start();
                t2.start();
            }
            else if(Random.nextDouble()<0.2)
                Util.dragCamera();
            else {
                Thread t1 = new Thread(()-> setUp.ctx.camera.pitch(Random.nextInt(90,99)));
                Thread t2 = new Thread(()-> setUp.ctx.camera.turnTo(setUp.ctx.objects.select().name(setUp.TREE_NAME).nearest().poll().tile(), 35));
                t1.start();
                t2.start();
            }

//            Util.moveCamera(Random.nextInt(-90,90),Random.nextInt(50,99));
//            AntibanScript.randomMouseMovement(ctx);
        }
        else {
            AntibanScript.moveMouseOffScreen(setUp.ctx,level,cond);
        }

//        moveMouseOffScreen();
//        doAntibanAction(Random.nextInt(1,11));
    }

}
