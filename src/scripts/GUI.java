package scripts;

import org.powerbot.bot.rt4.client.Client;
import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.rt4.ClientContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static scripts.quest101.setUp;

public class GUI implements MessageListener {
    static Font font = new Font(("Arial"), Font.BOLD, 16);

    static void frame() {
        initime = System.currentTimeMillis();
        WcInitLevel = setUp.ctx.skills.level(Constants.WOODCUTTING_SKILL);
        WcExpInit = setUp.ctx.skills.experience(Constants.WOODCUTTING_SKILL);
        final JFrame frame = new JFrame();
        frame.setSize(200, 200);
        frame.setVisible(true);
        final JCheckBox EDGE = new JCheckBox("edgville - yews");
        final JCheckBox MAGIC = new JCheckBox("arena -magic tree ");
        final JCheckBox DEBUG = new JCheckBox("debug");
        final JCheckBox MAIL = new JCheckBox("mail?");
        final Button RUN = new Button("RUN");
//final JCheckBox AntibanCamera= new JCheckBox("Antiban/rotatingCamera");
        JPanel panel = new JPanel();
        panel.add(EDGE);
        panel.add(MAGIC);
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
        DEBUG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (DEBUG.isEnabled()) {
                    setUp.debug = true;
                }
            }
        });
        MAIL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (MAIL.isEnabled()) {
                    setUp.mail = true;
                }
            }
        });
    }

    static int WcInitLevel;
    static int hours;
    static int seconds;
    static int minutes;
    static int Wclevel;
    static int WcExpInit;
    static double runTime;
    static long initime;
    public static int logsChopped = 0;

    static public void rep(Graphics g1, ClientContext ctx){
        int currentExp = ctx.skills.experience(Constants.WOODCUTTING_SKILL);
        int currLevel = ctx.skills.level(Constants.WOODCUTTING_SKILL);
        int logsToNextLevel = (ctx.skills.experienceAt(currLevel + 1) - currentExp) / 175;
        int expGained= currentExp-WcExpInit;
        Wclevel = currLevel -WcInitLevel;
        hours = (int) ((System.currentTimeMillis() - initime) / 3600000);
        minutes = (int) ((System.currentTimeMillis() - initime) / 60000 % 60);
        seconds = (int) ((System.currentTimeMillis() - initime) / 1000) % 60;
        runTime = (double) (System.currentTimeMillis() - initime) / 3600000;

        Graphics2D g2= (Graphics2D) g1;
        int posx= (int) ctx.input.getLocation().getX();
        int posy= (int) ctx.input.getLocation().getY();
        g2.setColor(Color.GREEN);
        g2.drawLine(posx,posy-10,posx,posy+10);
        g2.drawLine(posx-10,posy,posx+10,posy);
        g2.setColor(Color.GREEN);
        g2.drawOval(posx-9,posy-9,18,18);
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
        int money= (int) ((logsChopped*1050)/runTime);
        g1.drawString("Money/Hour "+money,335,375);
        g1.drawString("Ete", 335,400);
    }

    public void messaged(MessageEvent me) {
        String msg = me.text();

        if(msg.contains(" get some "+ setUp.TREE_MESS_NAME+" logs"))
        {
            GUI.logsChopped++;
        }
    }
}
