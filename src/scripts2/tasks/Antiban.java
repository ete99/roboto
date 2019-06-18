package scripts2.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Player;
import scripts2.Constants;
import scripts2.Task;

import java.awt.*;
import java.util.concurrent.Callable;

/**
 * Created by Thijs on 16-6-2017.
 */
public class Antiban extends Task {
    final int SKILL_WC = 22;

    public Antiban(ClientContext ctx) {
        super(ctx);
    }

    public void doAntibanAction(int antibanAction){
        if (antibanAction == 1 || antibanAction == 2){
        } else if (antibanAction == 3 || antibanAction == 4){
            int minDistance = Random.nextInt(50, 100);
            int maxDistance = Random.nextInt(250, 300);
            randomMouseMovement(minDistance, maxDistance);
        } else if (antibanAction == 5 || antibanAction == 6){
            hoverOverRandomPlayer();
        } else if (antibanAction == 7 || antibanAction == 8){
            randomCameraTurn();
        } else if (antibanAction == 9 || antibanAction == 10){
            moveMouseOffScreen(2);
        }
    }

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
     * @param minDistance
     * The minimum distance the cursor will move
     * @param maxDistance
     * The maximum distance the cursor will move (exclusive)
     */
    public void randomMouseMovement(int minDistance, int maxDistance) {
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

    public void hoverOverRandomPlayer(){
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
    public void randomCameraTurn(){
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

    public void moveMouseOffScreen(int level){
        int x, y;
        if (Random.nextDouble() > 0.50){
            y = Random.nextInt(-2000, -1000);
        }
        else {
            y = Random.nextInt(1000, 1500);
        }
        x=-100;
        ctx.input.move(x, y);
        ctx.input.defocus();
        if(level>0)
            Condition.sleep(Random.nextInt(3000, 5000));
        else
            Condition.sleep(Random.nextInt(500, 2500));
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
        if(level>0)
        Condition.wait(new Callable<Boolean>(){
            @Override
            public Boolean call() {
                return ctx.players.local().animation()==-1;
            }
        }, 1000, 100);
        else
            Condition.wait(new Callable<Boolean>(){
                @Override
                public Boolean call() {
                    return ctx.players.local().animation()==-1;
                }
            }, 300, 10);
        ctx.input.focus();
//        x = Random.nextInt(10, 510);
//        y = Random.nextInt(10, 330);
//        ctx.input.move(x, y);
//        Condition.sleep(Random.nextInt(250, 500));
    }

    @Override
    public boolean activate() {
        return ctx.players.local().animation()== Constants.WC_ANIM;
    }

    @Override
    public void execute() {
        System.out.println("AntiBanning");
        if (Random.nextDouble() > 0.90){
            randomCameraTurn();
        }
        else {
            moveMouseOffScreen(2);
        }

//        moveMouseOffScreen();
//        doAntibanAction(Random.nextInt(1,11));
    }
}
