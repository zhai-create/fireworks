import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

class Yansheng implements Runnable{
    long num;
    String name;
    Yansheng(long num) {
        this.num=num;
    }
    public void run() {
        try{
            AudioInputStream ais;
            SourceDataLine sdl;
            try{
                ais = AudioSystem.getAudioInputStream(new File("E:\\music.wav"));
                AudioFormat format = ais.getFormat();
                DataLine.Info info = new DataLine.Info(SourceDataLine.class,format);
                sdl=(SourceDataLine) AudioSystem.getLine(info);
                sdl.open(format);
                sdl.start();
                int bytesRead=0;
                byte[] audioDataBuff = new byte[512];
                while (bytesRead != -1) {
                    bytesRead=ais.read(audioDataBuff,0,audioDataBuff.length);
                    if(bytesRead>=0) sdl.write(audioDataBuff,0,bytesRead);
                }
                sdl.drain();
                sdl.close();
                Thread.sleep(num);
                System.out.println("我是线程一");
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


class Bk extends JPanel implements Runnable{
    int r=2;
    //int xx;
    //int yy;
    boolean ishigh;
    int num;
    int[] list=new int[50];
    int[][] listx=new int[50][50];
    int[][] listy=new int[50][50];
    int high=(int)(Math.random()*500);
    int di=5;
    int di2=10;
    boolean ishei;
    int xx=(int)(Math.random()*800+100);
    int yy=(int)(Math.random()*200+800);
    Color[] colors={Color.cyan,Color.blue,Color.green,Color.orange,Color.pink,Color.red};

        //int ceng;
        //final int R=5;
        //int R;
        //int[][] list=new int[10][100];
        Bk(){
            for (int i=1;i<list.length-1;i++) {
                //list[i+1]=2+list[i];
                //list[i]=100*(1+1);
                list[i]=2;
            }
            /*xx=(int)(Math.random()*800+100);
            yy=(int)(Math.random()*800+100);*/


        //System.out.println(xx);
        //System.out.println(yy);
        for (int k=0;k<list.length;k++){
        for(int i=0;i<listx[k].length/2;i++) {
        listx[k][i]=(int)(xx-r+i*r/(listx[k].length/4));
        listy[k][i]=(int)(yy+Math.sqrt(r*r-Math.abs(listx[k][i]-xx)*Math.abs(listx[k][i]-xx)));
        //System.out.println(listy[i]);
        }
        for(int i=listx[k].length/2;i<listx[k].length;i++) {
        listx[k][i]=(int)(listx[k][i-listx[k].length/2]);
        listy[k][i]=(int)(yy-Math.sqrt(r*r-Math.abs(listx[k][i-listx[k].length/2]-xx)*Math.abs(listx[k][i-listx[k].length/2]-xx)));
        }
        } /*for (int i=0;i<listx.length;i++) {
                for (int j=0;j<listx[i].length;j++) {
                    System.out.print(listy[i][j]+" ");
                } System.out.println();
            }*/
             /*for (int k=1;k<listx.length;k++) {
                listx[k]=listx[0];
                listy[k]=listy[0];
            }*/
        setBackground(Color.black);
        setSize(1000,1000);
        setLocation(0,0);
            Timer timer=new Timer(50, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Yansheng yansheng=new Yansheng(50);
                    //new Thread(yansheng).start();
                    if (listy[0][0]>high) {
                        for (int k=0;k<listx.length;k++) {
                            for (int i=0;i<listx[k].length;i++) {
                                listy[k][i]-=40;
                            }
                        }
                    } //repaint();

                    else if (list[0]<25) {
                        //System.out.println(r);
                        for (int k=0;k<list.length;k++) {
                            for(int i=0;i<listx[k].length/2;i++) {
                                listx[k][i]=(int)(xx-list[k]+i*list[k]/(listx[k].length/4));
                                listy[k][i]=(int)(high+Math.sqrt(list[k]*list[k]-Math.abs(listx[k][i]-xx)*Math.abs(listx[k][i]-xx)));
                                //System.out.println(listy[i]);
                            }
                            for(int i=listx[k].length/2;i<listx[k].length;i++) {
                                listx[k][i]=(int)(listx[k][i-listx[k].length/2]);
                                listy[k][i]=(int)(high-Math.sqrt(list[k]*list[k]-Math.abs(listx[k][i-listx[k].length/2]-xx)*Math.abs(listx[k][i-listx[k].length/2]-xx)));
                            }

                        }
                        /*for(int i=0;i<listx.length/2;i++) {
                            listx[i]=(int)(xx-r+i*r/(listx.length/4));
                            listy[i]=(int)(yy+Math.sqrt(r*r-Math.abs(listx[i]-xx)*Math.abs(listx[i]-xx)));
                            //System.out.println(listy[i]);
                        }
                        for(int i=listx.length/2;i<listx.length;i++) {
                            listx[i]=(int)(listx[i-listx.length/2]);
                            listy[i]=(int)(yy-Math.sqrt(r*r-Math.abs(listx[i-listx.length/2]-xx)*Math.abs(listx[i-listx.length/2]-xx)));
                        }
                        r+=5;*/
                        for (int j=0;j<list.length;j++) {
                            list[j]+=j*5;
                        }

                        //repaint();
                    } else {
                        ishei=true;
                    }
                    repaint();
                }
            }); timer.start();

        }

    @Override
    public void run() {
        try{
            Bk bk=new Bk();
            Thread.sleep(1000);

        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    @Override
protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int k=0;k<listx.length;k++) {
        for(int i=0;i<listx[k].length;i++) {
        if (!ishei) {
        g.setColor(colors[(int)(Math.random()*colors.length)]);
        } else {
        g.setColor(Color.black);
        }
        g.fillOval(listx[k][i],listy[k][i],di,di2);
        //di2+=3;
        }
        }
        }

}

class Fireworks_UI extends JFrame{
    Fireworks_UI(){
        Bk[] bks=new Bk[3]; //烟花个数
        for(int i=0;i<bks.length;i++) {
            bks[i]=new Bk();
            new Thread(bks[i]).start();
            add(bks[i]);
        }

        /*Bk bk1=new Bk();
        new Thread(bk1).start();
        Bk bk2=new Bk();
        new Thread(bk2).start();
        add(bk1);
        add(bk2);*/
    }
    public static void main(String[] args) {
        Fireworks_UI fireworks=new Fireworks_UI();
        fireworks.setTitle("烟花");
        fireworks.setSize(1000,1000);
        fireworks.setVisible(true);
        fireworks.setLocationRelativeTo(null);
        fireworks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}