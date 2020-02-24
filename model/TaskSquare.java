package model;



import java.util.ArrayList;

public class TaskSquare  {
    private int x;
    private int y;
    private int width;
    private int height;
    private Task t;

    public TaskSquare (boolean isScale, Task task, ArrayList<String> dates) {

        t= task;
        x=0;
        y=0;
        width=0;
        height =0;
        int x1 = 101;
        int w1 = 0;
        if (isScale)
            w1 = 417;
        else
            w1 = 179;
        for (int i = 0; i < dates.size(); i++) {

            int y1 = 30;
            String date = task.getDate();
            String d = dates.get(i);
            if (d.equals(date)) {

                if (isScale)
                    width = 416;
                else
                    width = 178;
                height = height()-1;
                x = x1;
                int hourT = task.getHour1();
                if(task.getMinute1() < 31)
                    y = 26+y1*hourT;
                else
                    y = 41+y1*hourT;
            }
            x1+=w1;
        }
    }
    public int height(){
        int h1= t.getHour1();
        int h2=t.getHour2();
        int m1= t.getMinute1();
        int m2= t.getMinute2();
        int h=0;
        if(((h2-h1)==0)&&(m1<31)&&(m2<31))
            return 15;
        if(((h2-h1)==0)&&(m1>30)&&(m2>30))
            return 15;
        if(((h2-h1)==0)&&(m1<31)&&(m2>30))
            return 30;
        if((h2-h1)!=0){
            if((m1==0) &&(m2==0)){
                h=h2-h1;
                return h*30;
            }
            if((m1==30) &&(m2==30)){
                h=h2-h1;
                return h*30;
            }
            if((m1>30) &&(m2==0)){
                h=h2-h1-1;
                return h*30+15;
            }
            if((m1<31) &&(m2==0)){
                h=h2-h1;
                return h*30;
            }
            if((m1==0) &&(m2>30)){
                h=h2-h1+1;
                return h*30;
            }
            if((m1==0) &&(m2<31)){
                h=h2-h1;
                return h*30+15;
            }
            if(m1==m2){
                h=h2-h1;
                return h*30+15;
            }
            if((m1<31) &&(m2<31)){
                h=h2-h1;
                return h*30+15;
            }
            if((m1<31) &&(m2>30)){
                h=h2-h1+1;
                return h*30;
            }
            if((m1>30) &&(m2<31)){
                h=h2-h1;
                return h*30;
            }
            if((m1>30) &&(m2>30)){
                h=h2-h1;
                return h*30+15;
            }
        }
        return h;
    }
    public int xGet(){return x;}
    public int yGet(){return y;}
    public int widthGet(){return width;}
    public int heightGet(){return height;}
    public Task taskGet(){return t;}

}
