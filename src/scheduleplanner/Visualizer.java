/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleplanner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.Timer;

/**
 *
 * @author Aaron
 */
public class Visualizer extends JApplet{
    
    private Schedule schedule;
    
    public void paint(Graphics g){
        super.paint(g);
        int padding = 14;
        int dayWidth = (getWidth()-2*padding)/5;
        int dayHeight = (getHeight()-2*padding);
        int startTime = 7;
        int endTime = 21;
        int scale = dayHeight/(endTime-startTime);
        g.setColor(Color.BLACK);
        g.drawRect(padding, padding, dayWidth*5, scale * (endTime-startTime));
        for(int i=0;i<endTime-startTime;i++){
            g.drawLine(padding, padding+i*scale, padding+5*dayWidth, padding+i*scale);
            g.drawString(""+((i+startTime-1)%12+1), 0, padding+i*scale+5);
        }
        
        for(int s=0;s<schedule.getNumber();s++){
            for(int d=0;d<5;d++){
                int x = padding+d*dayWidth;
                int y = (int)(padding+(schedule.getSubject(s).getStartTime(d)-startTime)*scale);
                int dy = (int)((schedule.getSubject(s).getEndTime(d)-schedule.getSubject(s).getStartTime(d))*scale);
                g.setColor(Color.GRAY);
                g.fillRect(x, y, dayWidth, dy);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, dayWidth, dy);
                g.setColor(Color.BLACK);
                g.drawString(schedule.getSubject(s).getName(), x, y+10);
            }
        }
    }
    
    public Visualizer(Schedule schedule){
        this.schedule = schedule;
        
        Timer animationTimer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                repaint();
            }
        });
        
        animationTimer.setRepeats(true);
        animationTimer.start();
    }
}
