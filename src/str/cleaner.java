package str;

import idleChopper.common_tasks.Antiban;
import idleChopper.script.AntibanScript;
import idleChopper.script.Util;
import org.powerbot.script.*;
import org.powerbot.script.rt4.*;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Interactive;
import str.Tasks.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static idleChopper.script.Util.moveCamera;


@Script.Manifest(name = "11str", properties = "author=ete; topic=1333332; client=4;", description = "jaja")
public class cleaner extends PollingScript<ClientContext> implements PaintListener  {
        Component unselectedInventory=ctx.widgets.widget(164).component(53);
        Component inventory = ctx.widgets.widget(164).component(60);
    public static List<Task> taskList = new ArrayList<Task>();
    int GUAM = 1779; // diamond
    public void start() {
        initime = System.currentTimeMillis();
        taskList.add(new error(ctx));
        taskList.add(new godown(ctx,GUAM));
        taskList.add(new check(ctx, GUAM));
        taskList.add(new clean(ctx, GUAM));
        taskList.add(new goup(ctx, GUAM));
        taskList.add(new store(ctx, GUAM));
    }
    public void poll() {
        for(Task task : taskList){
            if(ctx.controller.isStopping()){
                break;
            }
            if(task.activate()){
                miniAntiban();
                try {
                    task.execute();
                } catch (RuntimeException e){
                    this.log.info(e.getMessage());
                    throw new RuntimeException("Done");
                }
                break;
            }
        }

    }
    void miniAntiban(){
        cleaner.status = "antiban";
        if (Random.nextDouble() < 0.01) {
            AntibanScript.moveMouseOffScreen(ctx, -1);
            Condition.sleep(Random.nextInt(3000, 7000));
            moveCamera(ctx,Random.nextInt(-10, 10), Random.nextInt(80, 99));
        }
    }

    @Override
    public void stop() {
        System.out.println("stop");
        AntibanScript.moveMouseOffScreen(ctx,-1);
    }

    public static int done;

    @Override
    public void repaint(Graphics graphics) {
        rep(graphics, ctx);
    }

    public static int until;
    public static int WcInitLevel;
    static int WcExpInit;
    static int hours;
    static int seconds;
    static int minutes;
    static int Wclevel;
    public static int expGained;
    public static double runTime;
    static long initime;
    static Font font = new Font(("Arial"), Font.BOLD, 16);
    static final int SKILL = Constants.SKILLS_CRAFTING; // fletch
    public static String status = "-";
    int currentExp;
    static  int uno;
     public void rep(Graphics g1, ClientContext ctx){
        while(WcInitLevel == 0 || WcExpInit == 0){
            WcInitLevel = ctx.skills.level(SKILL);
            WcExpInit = ctx.skills.experience(SKILL);
            Condition.wait(()->!(WcInitLevel==0 && WcExpInit == 0));
        }
        if(ctx.players.local().tile().floor()==2)
            uno=0;
        else {
            int now = ctx.inventory.select().id(GUAM - 2).count();
            if (uno != now) {
                done += now - uno;
                uno = now;
            }
        }
        currentExp = ctx.skills.experience(SKILL);
         int currLevel = ctx.skills.level(SKILL);
        int stringsToLvl = (ctx.skills.experienceAt(currLevel + 1) - currentExp) / 75;
        Wclevel = currLevel -WcInitLevel;
        int expGained= currentExp-WcExpInit;
        hours = (int) ((System.currentTimeMillis() - initime) / 3600000);
        minutes = (int) ((System.currentTimeMillis() - initime) / 60000 % 60);
        seconds = (int) ((System.currentTimeMillis() - initime) / 1000) % 60;
        runTime = (double) (System.currentTimeMillis() - initime) / 3600000;

        Graphics2D g2= (Graphics2D) g1;
        int posx= (int) ctx.input.getLocation().getX();
        int posy= (int) ctx.input.getLocation().getY();
        g2.setColor(Color.GREEN);
//        g2.drawLine(posx,posy-10,posx,posy+10);
//        g2.drawLine(posx-10,posy,posx+10,posy);
//        g2.setColor(Color.GREEN);
        g2.drawOval(posx-9,posy-9,18,18);
        g1.setColor(new Color(0,0,0,40));
        int gameY=ctx.game.dimensions().height;
        g1.fillRect(1,0,515,140);
        long thickness = 4;
        BasicStroke basic= new BasicStroke(thickness);
        g2.setColor(new Color(255,255,255,20));
        g2.setStroke(basic);
        g2.drawRect(1, 0, 515, 140);
        double logH = (done/runTime);
        logH = ((logH*100)-logH%100) /100;
        g1.setColor(Color.WHITE);
        g1.setFont(font);
        g1.drawString("Levels gained : " + Wclevel, 20, 125);
        g1.drawString("Curr. lvl : " + currLevel, 20, 100);
        g1.drawString("Experience gained : " +expGained,20,75);
        g1.drawString("Sletch to lvl: " + stringsToLvl + "  t = "+ (logH==0?"inf":((int)((stringsToLvl/logH)*60)))+" m", 20, 50);
        g1.drawString("Strung :  " + done, 20, 25);
//        int money= (int) ((done*50)/runTime);
//        g1.drawString("Money/Hour "+money,335,125);
        double finish = until/logH;
        String mailOn = "until: "+(int)finish+" : "+(int)(finish*60)%60+" : "+(int)(finish*3600)%60;
        g1.drawString(mailOn, 335,25);
        String logs= "Str/h: "+(int)logH;
        g1.drawString(logs, 335,50);
        String xpH= "xp/h: "+(int)(expGained/runTime);
        g1.drawString(xpH, 335,75);
        g1.drawString("Time passed: " + hours + " : " + minutes + " : " + seconds, 335, 100);
        g1.drawString("Status: " + status, 335, 125);

    }
}
