package com.diep.javaFX;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class MouseListeningPanel extends JPanel {

    public MouseListeningPanel(String text) {
        add(new JFormattedTextField(text + "1"));
        add(new JFormattedTextField(text + "2"));
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.out.println(me);
            }
        });

    }

    public static boolean hasSum(int[] input, int target) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        for(int i:input){
            System.out.println(i);
            Integer v = mp.get(i);
            if (v == null){
                mp.put(i, 1);
            }else{
                mp.put(i, v+1);
            }
        }
        System.out.println(mp.keySet());
        for(int k : mp.keySet()){
            int reminding = target - k;
            System.out.println(reminding + " " + k);
            if ((reminding == k && mp.get(k) >= 2) || mp.get(reminding) != null){
                System.out.println(mp.get(k));
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        //System.out.println(hasSum(new int[]{5, 5, 2, 1, 0, 3} , 10));
        //System.out.println(hasSum(new int[]{5, 5, 2, 1, 0, 3} , 8));
        System.out.println(hasSum(new int[] {5, 2, 1, 0, 3} , 10));
    }
}
