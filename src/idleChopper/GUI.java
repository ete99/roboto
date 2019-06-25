package idleChopper;

import org.powerbot.script.rt4.ClientContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static idleChopper.quest101.setUp;

public class GUI{
    static Font font = new Font(("Arial"), Font.BOLD, 16);
    public static JFrame frame;
    static void frame() {
        initime = System.currentTimeMillis();
        frame = new JFrame();
        frame.setSize(200, 200);
        frame.setVisible(true);
        final JCheckBox EDGE = new JCheckBox("edgville - yews");
        final JCheckBox MAGIC = new JCheckBox("arena -magic tree ");
        final JCheckBox MAGIC_GUILD = new JCheckBox("guild -magic tree ");
        final JCheckBox DEBUG = new JCheckBox("debug");
        final JCheckBox MAIL = new JCheckBox("mail?");
        final Button RUN = new Button("RUN");
//final JCheckBox AntibanCamera= new JCheckBox("Antiban/rotatingCamera");
        JPanel panel = new JPanel();
        final ButtonGroup g = new ButtonGroup();
        g.add(EDGE);
        g.add(MAGIC);
        g.add(MAGIC_GUILD);
        panel.add(EDGE);
        panel.add(MAGIC);
        panel.add(MAGIC_GUILD);
        panel.add(DEBUG);
        panel.add(MAIL);
        panel.add(RUN);
        frame.add(panel);
        RUN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(RUN.isEnabled()){
                    setUp.run = true;
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
        EDGE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (EDGE.isEnabled()) {
                    setUp.set = 1;
                }
            }
        });
        MAGIC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (MAGIC.isEnabled()) {
                    setUp.set = 2;
                }
            }
        });
        MAGIC_GUILD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (MAGIC.isEnabled()) {
                    setUp.set = 3;
                }
            }
        });
        DEBUG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (DEBUG.isEnabled())  setUp.debug = true;
                else{
                    setUp.debug =false;
                    DEBUG.setEnabled(false);
                }
            }
        });
        MAIL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (MAIL.isSelected()) {
                    setUp.mail = true;
                }
            }
        });
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static int WcInitLevel;
    static int WcExpInit;
    static int hours;
    static int seconds;
    static int minutes;
    static int Wclevel;
    static int expGained;
    static double runTime;
    static long initime;
    public static int logsChopped = 0;

    static public void rep(Graphics g1, ClientContext ctx){
        int currentExp = ctx.skills.experience(Constants.WOODCUTTING_LEVEL);
        int currLevel = ctx.skills.level(Constants.WOODCUTTING_LEVEL);
        int logsToNextLevel = (ctx.skills.experienceAt(currLevel + 1) - currentExp) / 175;
        Wclevel = currLevel -WcInitLevel;
        int expGained= currentExp-WcExpInit;
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
        int gameY=ctx.game.dimensions().height;
        g1.fillRect(1,gameY-140,515,140);
        long thickness = 4;
        BasicStroke basic= new BasicStroke(thickness);
        g2.setColor(Color.WHITE);
        g2.setStroke(basic);
        g2.drawRect(1, gameY-140, 515, 140);
        int logH = (int)(logsChopped/runTime);
        g1.setColor(Color.WHITE);
        g1.setFont(font);
        g1.drawString("Levels gained : " + Wclevel, 20, gameY-125);
        g1.drawString("Curr. lvl : " + currLevel, 20, gameY-100);
        g1.drawString("Experience gained : " +expGained,20,gameY-75);
        g1.drawString("Logs to lvl: " + logsToNextLevel + "  t = "+ (logH==0?"inf":(logsToNextLevel/logH))+" hs", 20, gameY-50);
        g1.drawString("Logs chopped :  " + logsChopped, 20, gameY-25);
        int money= (int) ((logsChopped*1050)/runTime);
        g1.drawString("Money/Hour "+money,335,gameY-125);
        String mailOn = "Mail: "+setUp.mail;
        g1.drawString(mailOn, 335,gameY-25);
        String logs= "Log/h: "+logH;
        g1.drawString(logs, 335,gameY-50);
        String xpH= "xp/h: "+(int)(expGained/runTime);
        g1.drawString(xpH, 335,gameY-75);
        g1.drawString("Time passed : " + hours + " : " + minutes + " : " + seconds, 335, gameY-100);

    }

}
