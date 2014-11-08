package gtsoffenbach.tourdegts.minigame_math;

import gtsoffenbach.tourdegts.Background;
import gtsoffenbach.tourdegts.gameinterface.Game;

/**
 * Created by Kern on 07.11.2014.
 */
public class MathGameLogic {
    Game game;
    MathGameScreen father;
    float startTimer,gameTimer;

    boolean isRunning,isStarting,isEnded;

    MathGameLogic(Game game,MathGameScreen gameScreen){
        this.game = game;
        startTimer= 100;
        gameTimer= 6000;
        gameScreen=father;
        isRunning= false;
        isStarting=true;
    }
    public void start(){
        isStarting=false;
        isRunning=true;
        isEnded=false;
    }
    public void end(){
        isStarting=false;
        isRunning=false;
        isEnded=true;
    }
    public int[] getJop(){
        int[] job = new int[8];
        int number1 =0,number2 =0;
        int vorzeichen =(int)(Math.random()*2);

        if(vorzeichen>0) {
            boolean ok = false;
            while(!ok) {
                number1 = (int) (Math.random() * 100);
                number2 = (int) (Math.random() * 100);
                if(number1%10==0||number2%10==0){
                    ok=false;
                }else{
                    ok=true;
                }

            }
        }else {
            boolean ok = false;
            while(!ok) {
                number1 = (int) (Math.random() * 100);
                number2 = (int) (Math.random() * 100);
                if(number2>number1){
                    ok=false;
                }else{
                    ok=true;
                }
            }
        }

        int result0=0,result1=0,result2=0,result3=0;



        int rr=(int)(Math.random()*3);
        System.out.println(rr+"");
        boolean ok=false;
        switch (rr){
            case 0:
                if(vorzeichen==0){
                    result0=number1-number2;
                }else {
                    result0=number1+number2;
                }
                while(!ok){
                    result1=result0+(((int)(Math.random()*38))-19);
                    result2=result0+(((int)(Math.random()*38))-19);
                    result3=result0+(((int)(Math.random()*38))-19);

                    if(result1!=result0||result2!=result0||result3!=result0||result1!=result2||result1!=result3||result2!=result3){
                       ok=true;
                    }

                }
                break;
            case 1:
                if(vorzeichen==0){
                    result1=number1-number2;
                }else {
                    result1=number1+number2;
                }
                while(!ok){
                    result0=result1+(((int)(Math.random()*38))-19);
                    result2=result1+(((int)(Math.random()*38))-19);
                    result3=result1+(((int)(Math.random()*38))-19);

                    if(result1!=result0||result2!=result0||result3!=result0||result1!=result2||result1!=result3||result2!=result3){
                        ok=true;
                    }
                }
                break;
            case 2:
                if(vorzeichen==0){
                    result2=number1-number2;
                }else {
                    result2=number1+number2;
                }
                while(!ok){
                    result0=result2+(((int)(Math.random()*38))-19);
                    result1=result2+(((int)(Math.random()*38))-19);
                    result3=result2+(((int)(Math.random()*38))-19);

                    if(result1!=result0||result2!=result0||result3!=result0||result1!=result2||result1!=result3||result2!=result3){
                        ok=true;
                    }
                }
                break;
            case 3:
                if(vorzeichen==0){
                    result3=number1-number2;
                }else {
                    result3=number1+number2;
                }
                while(!ok){
                    result0=result3+(((int)(Math.random()*38))-19);
                    result2=result3+(((int)(Math.random()*38))-19);
                    result1=result3+(((int)(Math.random()*38))-19);

                    if(result1!=result0||result2!=result0||result3!=result0||result1!=result2||result1!=result3||result2!=result3){
                        ok=true;
                    }
                }
                break;
        }

        job[0]=vorzeichen;
        job[1]=number1;
        job[2]=number2;
        job[3]=rr;
        job[4]=result0;
        job[5]=result1;
        job[6]=result2;
        job[7]=result3;
        return job;
    }
    public void subGameTimer(float ms){
        if(gameTimer-ms>0){
            gameTimer-=ms;
        }else{
            end();
        }


    };
    public void subStartTimer(float ms){
        if(startTimer-ms>0){
            startTimer-=ms;
        }else{
            start();
        }
    };
    public float getStartTimer(){
        return startTimer;
    }
    public float getGameTimer(){
        return gameTimer;
    }
}
