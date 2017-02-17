package com.libertadorgames.el_libertador;

/**
 * Created by vteo on 10/2/15.
 */
public class Interpolator {

    //2d linear interpolator
    //uses list of destinations to reach with in the format start,end,step
    //the step will be how many step will it take to compete the journey to the destination.
    protected int x[];
    protected int y[];
    protected int step[];
    protected int current_joint;
    protected int current_index;
    protected boolean completed;

    Interpolator(int ax[], int ay[], int step1[]) {
        x= ax;
        y = ay;
        step = step1;
        current_joint = 0;
        current_index = 0;
        completed = false;
    }


    Interpolator(int ax[], int ay[], int step1[], int delay) {
        x= ax;
        y = ay;
        step = step1;
        current_joint = 0;
        current_index = -delay;
        completed = false;
    }

    public boolean isCompleted () {
        return completed;

    }

    protected int[] calc_next() {

        if (current_index <0) {
            int p[] = {x[0],y[0]};
            current_index++;
            return p;

        }

        if (current_joint==x.length-1) {
            int s = x.length;
            int p[] = {x[s-1],y[s-1]};
            completed = true;
            return p;
        }
        float act_x=0;
        float act_y=0;
        float divx =(float) (x[current_joint+1]-x[current_joint])/ (float) step[current_joint];
        float divy = (float) (y[current_joint+1]-y[current_joint])/ (float)step[current_joint];
        if( current_index < step[current_joint]) {
            act_x = (float)x[current_joint]+divx* (float)current_index;
            act_y = (float)y[current_joint]+divy* (float)current_index;
            current_index++;

        } else {
            current_joint++;
            current_index =0;
            act_x = (float)x[current_joint];
            act_y = (float)y[current_joint];
        }
        int r[] = {(int)act_x,(int)act_y};
        return r;


    }

    public int[] next() {
        return calc_next();
    }


}
