package yew;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import org.powerbot.script.rt4.ClientAccessor;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements MessageListener {
    static Font font = new Font(("Arial"), Font.BOLD, 16);
    static ClientContext ctx;
    static int choser;
    static void frame(ClientContext ctxs){
        ctx = ctxs;
        initime=System.currentTimeMillis();
        WcInitLevel =ctx.skills.level(Constants.SKILLS_WOODCUTTING);
        WcExpInit= ctx.skills.experience(Constants.SKILLS_WOODCUTTING);
        final JFrame frame = new JFrame();
        frame.setSize(300, 400);
        frame.setVisible(true);
        final JCheckBox draynorV= new JCheckBox("DraynorV");
        final JCheckBox GE= new JCheckBox("Grand Exchange");
        final JCheckBox VEAST= new JCheckBox("Varrock East");
        final JCheckBox LUMB= new JCheckBox("Lumbridge South");
        final JCheckBox FALADORS= new JCheckBox("Faladro South");
        final JCheckBox EDGEVILLE= new JCheckBox("Edgeville");
//final JCheckBox AntibanCamera= new JCheckBox("Antiban/rotatingCamera");
        JPanel panel = new JPanel();
        panel.add(VEAST);
        panel.add(GE);
        panel.add(draynorV);
        // panel.add(LUMB);
        panel.add(FALADORS);
        panel.add(EDGEVILLE);
        frame.add(panel);
        draynorV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(draynorV.isEnabled()){
                    choser =1;
                }
            }
        });
        GE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(GE.isEnabled()){
                    choser =2;
                }
            }
        });
        VEAST.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(VEAST.isEnabled()){
                    choser =3;
                }
            }
        });

        LUMB.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent actionEvent) {
                if(LUMB.isEnabled()){
                    choser =4;
                }
            }
        });
        FALADORS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(FALADORS.isEnabled()){
                    choser =5;
                }
            }
        });
        EDGEVILLE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(EDGEVILLE.isEnabled()){
                    choser =6;
                }
            }
        });

    }
    static int  WcInitLevel;
    static int hours;
    static int seconds;
    static int minutes;
    static int Wclevel;
    static int WcExpInit;
    static double runTime;
    static long initime;
    static int logsChopped=0;
    public void p(){

    }
    static public void rep(Graphics g1){

        int currentExp = ctx.skills.experience(Constants.SKILLS_WOODCUTTING);
        int currLevel = ctx.skills.level(Constants.SKILLS_WOODCUTTING);
        int logsToNextLevel = (ctx.skills.experienceAt(currLevel + 1) - currentExp) / 175;
        int expGained= currentExp-WcExpInit;
        Wclevel = currLevel -WcInitLevel;
        hours = (int) ((System.currentTimeMillis() - initime) / 3600000);
        minutes = (int) ((System.currentTimeMillis() - initime) / 60000 % 60);
        seconds = (int) ((System.currentTimeMillis() - initime) / 1000) % 60;
        runTime = (double) (System.currentTimeMillis() - initime) / 3600000;

        Graphics2D g2= (Graphics2D) g1;
//        int posx= (int) ctx.input.getLocation().getX();
//        int posy= (int) ctx.input.getLocation().getY();
//        g2.setColor(Color.GREEN);
//        g2.drawLine(posx,posy-10,posx,posy+10);
//        g2.drawLine(posx-10,posy,posx+10,posy);
//        g2.setColor(Color.GREEN);
//        g2.drawOval(posx-9,posy-9,18,18);
        g1.setColor(Color.BLACK);
        g1.fillRect(1,340,515,140);
        long thickness = 4;
        BasicStroke basic= new BasicStroke(thickness);
        g2.setColor(Color.WHITE);
        g2.setStroke(basic);
        g2.drawRect(1, 340, 515, 140);

        g1.setColor(Color.WHITE);
        g1.setFont(font);
        g1.drawString("Levels gained : " + Wclevel, 20, 375);
        g1.drawString("Time passed : " + hours + " : " + minutes + " : " + seconds, 20, 400);
        g1.drawString("Experience gained : " +expGained,20,425);
        g1.drawString("Logs to next level: " + logsToNextLevel, 20, 450);
        g1.drawString("Logs chopped :  " + logsChopped, 20, 475);
        int money= (int) ((logsChopped*360)/runTime);
        g1.drawString("Money/Hour "+money,335,375);
        g1.drawString("Spearless", 335,400);
    }

    public void messaged(MessageEvent me) {
        String msg = me.text();
        System.out.println("holas");
        if(msg.contains(" get some yew logs"))
        {
            GUI.logsChopped++;
        }
    }
}
