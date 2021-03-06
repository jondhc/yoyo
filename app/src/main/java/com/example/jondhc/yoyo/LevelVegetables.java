package com.example.jondhc.yoyo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class LevelVegetables extends Activity {
    private int cuenta_frutas = 0;
    private int contadorR = 0;
    private boolean lechugaYa = false;
    private boolean papaYa = false;
    private boolean tomateYa = false;

    private View lechuga4;
    private View lechuga1;
    private View lechuga2;
    private View lechuga3;
    private View potato4;
    private View potato1;
    private View potato2;
    private View potato3;
    private View tomato4;
    private View tomato2;
    private View tomato1;
    private View tomato3;

    private ImageView character;

    private GlobalApplication mApp;
    private Characters selectedC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_level_vegetables);
        MediaPlayer AudioIni = MediaPlayer.create(getApplicationContext(), R.raw.instructions_g);
        //AudioIni.start(); //Start media player
        mApp = ((GlobalApplication) getApplicationContext());
        selectedC = mApp.getGlobalVarValue();

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN; //Hide the status bar
        decorView.setSystemUiVisibility(uiOptions);

        ActionBar actionBar = getActionBar();
        if (actionBar != null)
            actionBar.hide();   // Hide ActionBar

        if (selectedC == Characters.CAT) {
            character = findViewById(R.id.catV);    //Getting cat image
            GlideApp.with(this).load(R.drawable.cat).into(character);
        } else if (selectedC == Characters.DOG) {
            character = findViewById(R.id.dogV);    //Getting dog image
            GlideApp.with(this).load(R.drawable.dog).into(character);
        }

        TextView count = findViewById(R.id.tecst);
        count.setText(String.valueOf(cuenta_frutas));
        lechuga4 = findViewById(R.id.lechuga4);
        lechuga1 = findViewById(R.id.lechuga1);
        lechuga2 = findViewById(R.id.lechuga2);
        lechuga3 = findViewById(R.id.lechuga3);
        potato4 = findViewById(R.id.potato4);
        potato1 = findViewById(R.id.potato1);
        potato2 = findViewById(R.id.potato2);
        potato3 = findViewById(R.id.potato3);
        tomato4 = findViewById(R.id.tomato4);
        tomato2 = findViewById(R.id.tomato2);
        tomato1 = findViewById(R.id.tomato1);
        tomato3 = findViewById(R.id.tomato3);

        generaFrutas();


    }

    public void generaFrutas() {
        Random randomGenerator = new Random();
        final int maxFrutas = 3;
        int minFrutas = 1;
        int range = maxFrutas - minFrutas + 1;
        int escoge_frutas = randomGenerator.nextInt(range) + 1;
        System.out.println("Frutas para escoger=" + escoge_frutas);

        switch (escoge_frutas) {
            case 1:
                System.out.println("Caso 1");
                if (!lechugaYa) {
                    lechugaYa = true;
                    contadorR = 0;
                    System.out.println("IF ejecutado");
                    lechuga4.setVisibility(View.VISIBLE);
                    lechuga1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.setVisibility(View.INVISIBLE);
                            cuenta_frutas++;
                            contadorR++;
                            playAudio(cuenta_frutas);
                            TextView count = (TextView) findViewById(R.id.tecst);
                            count.setText(String.valueOf(cuenta_frutas));
                            if (contadorR == 3 && contadorR != 9) {
                                generaFrutas();
                                lechuga4.setVisibility(View.INVISIBLE);
                            }
                            else if(cuenta_frutas==9 && contadorR == 3){
                                MediaPlayer AudioF = MediaPlayer.create(getApplicationContext(), R.raw.hooray);
                                AudioF.start();

                                 System.out.println("Felicidades");
                                //Animation of character
                                RotateAnimation char_anim = new RotateAnimation(0f, 360f, character.getWidth() / 2, character.getHeight() / 2);
                                char_anim.setInterpolator(new LinearInterpolator());
                                char_anim.setRepeatCount(0);
                                char_anim.setDuration(700);
                                character.startAnimation(char_anim);
                                mApp.getStatutLevels().statut_levels.put(Levels.VEGETABLES,3);
                                mApp.getStatutLevels().statut_levels.put(Levels.TREE,-1);
                                new java.util.Timer().schedule(
                                        new java.util.TimerTask() {
                                            @Override
                                            public void run() {
                                                Intent nextLvScreen = new Intent(LevelVegetables.this, Next_Level_Screen.class); //Start next activity, change with correct level
                                                startActivity(nextLvScreen);
                                            }
                                        }, 900);
                            }
                        }
                    });
                    lechuga2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.setVisibility(View.INVISIBLE);
                            cuenta_frutas++;
                            contadorR++;
                            playAudio(cuenta_frutas);
                            TextView count = (TextView) findViewById(R.id.tecst);
                            count.setText(String.valueOf(cuenta_frutas));
                            if (contadorR == 3 && contadorR != 9) {
                                generaFrutas();
                                lechuga4.setVisibility(View.INVISIBLE);
                            }
                            else if(cuenta_frutas==9 && contadorR == 3){
                                System.out.println("Felicidades");
                                MediaPlayer AudioF = MediaPlayer.create(getApplicationContext(), R.raw.hooray);
                                AudioF.start();
                                //Animation of character
                                RotateAnimation char_anim = new RotateAnimation(0f, 360f, character.getWidth() / 2, character.getHeight() / 2);
                                char_anim.setInterpolator(new LinearInterpolator());
                                char_anim.setRepeatCount(0);
                                char_anim.setDuration(700);
                                character.startAnimation(char_anim);
                                mApp.getStatutLevels().statut_levels.put(Levels.VEGETABLES,3);
                                mApp.getStatutLevels().statut_levels.put(Levels.TREE,-1);
                                new java.util.Timer().schedule(
                                        new java.util.TimerTask() {
                                            @Override
                                            public void run() {
                                                Intent nextLvScreen = new Intent(LevelVegetables.this, Next_Level_Screen.class); //Start next activity, change with correct level
                                                startActivity(nextLvScreen);
                                            }
                                        }, 900);
                            }
                        }
                    });
                    lechuga3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.setVisibility(View.INVISIBLE);
                            cuenta_frutas++;
                            contadorR++;
                            playAudio(cuenta_frutas);
                            TextView count = (TextView) findViewById(R.id.tecst);
                            count.setText(String.valueOf(cuenta_frutas));
                            if (contadorR == 3 && contadorR != 9) {
                                generaFrutas();
                                lechuga4.setVisibility(View.INVISIBLE);
                            }
                            else if(cuenta_frutas==9 && contadorR == 3){
                                System.out.println("Felicidades");
                                MediaPlayer AudioF = MediaPlayer.create(getApplicationContext(), R.raw.hooray);
                                AudioF.start();
                                //Animation of character
                                RotateAnimation char_anim = new RotateAnimation(0f, 360f, character.getWidth() / 2, character.getHeight() / 2);
                                char_anim.setInterpolator(new LinearInterpolator());
                                char_anim.setRepeatCount(0);
                                char_anim.setDuration(700);
                                character.startAnimation(char_anim);
                                mApp.getStatutLevels().statut_levels.put(Levels.VEGETABLES,3);
                                mApp.getStatutLevels().statut_levels.put(Levels.TREE,-1);
                                new java.util.Timer().schedule(
                                        new java.util.TimerTask() {
                                            @Override
                                            public void run() {
                                                Intent nextLvScreen = new Intent(LevelVegetables.this, Next_Level_Screen.class); //Start next activity, change with correct level
                                                startActivity(nextLvScreen);
                                            }
                                        }, 900);
                            }
                        }
                    });
                } else if (lechugaYa && cuenta_frutas != 9) {
                    System.out.println("ELSE-IF ejecutado");
                    generaFrutas();
                }
                else if (tomateYa && cuenta_frutas == 9){
                    System.out.println("Felicidades");
                    MediaPlayer AudioF = MediaPlayer.create(getApplicationContext(), R.raw.hooray);
                    AudioF.start();
                    //Animation of character
                    RotateAnimation char_anim = new RotateAnimation(0f, 360f, character.getWidth() / 2, character.getHeight() / 2);
                    char_anim.setInterpolator(new LinearInterpolator());
                    char_anim.setRepeatCount(0);
                    char_anim.setDuration(700);
                    character.startAnimation(char_anim);
                    mApp.getStatutLevels().statut_levels.put(Levels.VEGETABLES,3);
                    mApp.getStatutLevels().statut_levels.put(Levels.TREE,-1);
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    Intent nextLvScreen = new Intent(LevelVegetables.this, Next_Level_Screen.class); //Start next activity, change with correct level
                                    startActivity(nextLvScreen);
                                }
                            }, 900);
                }
                break;
            case 2:
                System.out.println("Caso 2");
                if (!papaYa) {
                    papaYa = true;
                    System.out.println("IF ejecutado");
                    contadorR = 0;
                    potato4.setVisibility(View.VISIBLE);
                    potato1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.setVisibility(View.INVISIBLE);
                            cuenta_frutas++;
                            contadorR++;
                            playAudio(cuenta_frutas);
                            TextView count = (TextView) findViewById(R.id.tecst);
                            count.setText(String.valueOf(cuenta_frutas));
                            if (contadorR == 3 && contadorR != 9) {
                                generaFrutas();
                                potato4.setVisibility(View.INVISIBLE);
                            }
                            else if(cuenta_frutas==9 && contadorR == 3){
                                System.out.println("Felicidades");
                                MediaPlayer AudioF = MediaPlayer.create(getApplicationContext(), R.raw.hooray);
                                AudioF.start();
                                //Animation of character
                                RotateAnimation char_anim = new RotateAnimation(0f, 360f, character.getWidth() / 2, character.getHeight() / 2);
                                char_anim.setInterpolator(new LinearInterpolator());
                                char_anim.setRepeatCount(0);
                                char_anim.setDuration(700);
                                character.startAnimation(char_anim);
                                mApp.getStatutLevels().statut_levels.put(Levels.VEGETABLES,3);
                                mApp.getStatutLevels().statut_levels.put(Levels.TREE,-1);
                                new java.util.Timer().schedule(
                                        new java.util.TimerTask() {
                                            @Override
                                            public void run() {
                                                Intent nextLvScreen = new Intent(LevelVegetables.this, Next_Level_Screen.class); //Start next activity, change with correct level
                                                startActivity(nextLvScreen);
                                            }
                                        }, 900);
                            }
                        }
                    });
                    potato2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.setVisibility(View.INVISIBLE);
                            cuenta_frutas++;
                            contadorR++;
                            playAudio(cuenta_frutas);
                            TextView count = (TextView) findViewById(R.id.tecst);
                            count.setText(String.valueOf(cuenta_frutas));
                            if (contadorR == 3 && contadorR != 9) {
                                generaFrutas();
                                potato4.setVisibility(View.INVISIBLE);
                            }
                            else if(cuenta_frutas==9 && contadorR == 3){
                                System.out.println("Felicidades");
                                MediaPlayer AudioF = MediaPlayer.create(getApplicationContext(), R.raw.hooray);
                                AudioF.start();
                                //Animation of character
                                RotateAnimation char_anim = new RotateAnimation(0f, 360f, character.getWidth() / 2, character.getHeight() / 2);
                                char_anim.setInterpolator(new LinearInterpolator());
                                char_anim.setRepeatCount(0);
                                char_anim.setDuration(700);
                                character.startAnimation(char_anim);
                                mApp.getStatutLevels().statut_levels.put(Levels.VEGETABLES,3);
                                mApp.getStatutLevels().statut_levels.put(Levels.TREE,-1);
                                new java.util.Timer().schedule(
                                        new java.util.TimerTask() {
                                            @Override
                                            public void run() {
                                                Intent nextLvScreen = new Intent(LevelVegetables.this, Next_Level_Screen.class); //Start next activity, change with correct level
                                                startActivity(nextLvScreen);
                                            }
                                        }, 900);
                            }
                        }
                    });
                    potato3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.setVisibility(View.INVISIBLE);
                            cuenta_frutas++;
                            contadorR++;
                            playAudio(cuenta_frutas);
                            TextView count = (TextView) findViewById(R.id.tecst);
                            count.setText(String.valueOf(cuenta_frutas));
                            if (contadorR == 3 && contadorR != 9) {
                                generaFrutas();
                                potato4.setVisibility(View.INVISIBLE);
                            }
                            else if(cuenta_frutas==9 && contadorR == 3){
                                System.out.println("Felicidades");
                                MediaPlayer AudioF = MediaPlayer.create(getApplicationContext(), R.raw.hooray);
                                AudioF.start();
                                //Animation of character
                                RotateAnimation char_anim = new RotateAnimation(0f, 360f, character.getWidth() / 2, character.getHeight() / 2);
                                char_anim.setInterpolator(new LinearInterpolator());
                                char_anim.setRepeatCount(0);
                                char_anim.setDuration(700);
                                character.startAnimation(char_anim);
                                mApp.getStatutLevels().statut_levels.put(Levels.VEGETABLES,3);
                                mApp.getStatutLevels().statut_levels.put(Levels.TREE,-1);
                                new java.util.Timer().schedule(
                                        new java.util.TimerTask() {
                                            @Override
                                            public void run() {
                                                Intent nextLvScreen = new Intent(LevelVegetables.this, Next_Level_Screen.class); //Start next activity, change with correct level
                                                startActivity(nextLvScreen);
                                            }
                                        }, 900);
                            }
                        }
                    });

                } else if (papaYa && cuenta_frutas != 9) {
                    System.out.println("ELSE-IF ejecutado");
                    generaFrutas();
                }
                else if (tomateYa && cuenta_frutas == 9){
                    System.out.println("Felicidades");
                    MediaPlayer AudioF = MediaPlayer.create(getApplicationContext(), R.raw.hooray);
                    AudioF.start();
                    //Animation of character
                    RotateAnimation char_anim = new RotateAnimation(0f, 360f, character.getWidth() / 2, character.getHeight() / 2);
                    char_anim.setInterpolator(new LinearInterpolator());
                    char_anim.setRepeatCount(0);
                    char_anim.setDuration(700);
                    character.startAnimation(char_anim);
                    mApp.getStatutLevels().statut_levels.put(Levels.VEGETABLES,3);
                    mApp.getStatutLevels().statut_levels.put(Levels.TREE,-1);
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    Intent nextLvScreen = new Intent(LevelVegetables.this, Next_Level_Screen.class); //Start next activity, change with correct level
                                    startActivity(nextLvScreen);
                                }
                            }, 900);
                }
                break;
            case 3:
                System.out.println("Caso 3");
                if (!tomateYa) {
                    tomateYa = true;
                    System.out.println("IF ejecutado");
                    contadorR = 0;
                    tomato4.setVisibility(View.VISIBLE);
                    tomato1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.setVisibility(View.INVISIBLE);
                            cuenta_frutas++;
                            contadorR++;
                            playAudio(cuenta_frutas);
                            TextView count = (TextView) findViewById(R.id.tecst);
                            count.setText(String.valueOf(cuenta_frutas));
                            if (contadorR == 3 && contadorR != 9) {
                                generaFrutas();
                                tomato4.setVisibility(View.INVISIBLE);
                            }
                            else if(cuenta_frutas==9 && contadorR == 3){
                                System.out.println("Felicidades");
                                MediaPlayer AudioF = MediaPlayer.create(getApplicationContext(), R.raw.hooray);
                                AudioF.start();
                                //Animation of character
                                RotateAnimation char_anim = new RotateAnimation(0f, 360f, character.getWidth() / 2, character.getHeight() / 2);
                                char_anim.setInterpolator(new LinearInterpolator());
                                char_anim.setRepeatCount(0);
                                char_anim.setDuration(700);
                                character.startAnimation(char_anim);
                                mApp.getStatutLevels().statut_levels.put(Levels.VEGETABLES,3);
                                mApp.getStatutLevels().statut_levels.put(Levels.TREE,-1);
                                new java.util.Timer().schedule(
                                        new java.util.TimerTask() {
                                            @Override
                                            public void run() {
                                                Intent nextLvScreen = new Intent(LevelVegetables.this, Next_Level_Screen.class); //Start next activity, change with correct level
                                                startActivity(nextLvScreen);
                                            }
                                        }, 900);
                            }
                        }
                    });
                    tomato2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.setVisibility(View.INVISIBLE);
                            cuenta_frutas++;
                            contadorR++;
                            playAudio(cuenta_frutas);
                            TextView count = (TextView) findViewById(R.id.tecst);
                            count.setText(String.valueOf(cuenta_frutas));
                            if (contadorR == 3 && contadorR != 9) {
                                generaFrutas();
                                tomato4.setVisibility(View.INVISIBLE);
                            }
                            else if(cuenta_frutas==9 && contadorR == 3){
                                System.out.println("Felicidades");
                                MediaPlayer AudioF = MediaPlayer.create(getApplicationContext(), R.raw.hooray);
                                AudioF.start();
                                //Animation of character
                                RotateAnimation char_anim = new RotateAnimation(0f, 360f, character.getWidth() / 2, character.getHeight() / 2);
                                char_anim.setInterpolator(new LinearInterpolator());
                                char_anim.setRepeatCount(0);
                                char_anim.setDuration(700);
                                character.startAnimation(char_anim);
                                mApp.getStatutLevels().statut_levels.put(Levels.VEGETABLES,3);
                                mApp.getStatutLevels().statut_levels.put(Levels.TREE,-1);
                                new java.util.Timer().schedule(
                                        new java.util.TimerTask() {
                                            @Override
                                            public void run() {
                                                Intent nextLvScreen = new Intent(LevelVegetables.this, Next_Level_Screen.class); //Start next activity, change with correct level
                                                startActivity(nextLvScreen);
                                            }
                                        }, 900);
                            }
                        }
                    });
                    tomato3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.setVisibility(View.INVISIBLE);
                            cuenta_frutas++;
                            contadorR++;
                            playAudio(cuenta_frutas);
                            TextView count = (TextView) findViewById(R.id.tecst);
                            count.setText(String.valueOf(cuenta_frutas));
                            if (contadorR == 3 && contadorR != 9) {
                                generaFrutas();
                                tomato4.setVisibility(View.INVISIBLE);
                            }
                            else if(cuenta_frutas==9 && contadorR == 3){
                                System.out.println("Felicidades");
                                MediaPlayer AudioF = MediaPlayer.create(getApplicationContext(), R.raw.hooray);
                                AudioF.start();
                                //Animation of character
                                RotateAnimation char_anim = new RotateAnimation(0f, 360f, character.getWidth() / 2, character.getHeight() / 2);
                                char_anim.setInterpolator(new LinearInterpolator());
                                char_anim.setRepeatCount(0);
                                char_anim.setDuration(700);
                                character.startAnimation(char_anim);
                                mApp.getStatutLevels().statut_levels.put(Levels.VEGETABLES,3);
                                mApp.getStatutLevels().statut_levels.put(Levels.TREE,-1);
                                new java.util.Timer().schedule(
                                        new java.util.TimerTask() {
                                            @Override
                                            public void run() {
                                                Intent nextLvScreen = new Intent(LevelVegetables.this, Next_Level_Screen.class); //Start next activity, change with correct level
                                                startActivity(nextLvScreen);
                                            }
                                        }, 900);
                            }
                        }
                    });
                } else if (tomateYa && cuenta_frutas != 9) {
                    System.out.println("ELSE-IF ejecutado");
                    generaFrutas();
                } else if (tomateYa && cuenta_frutas == 9){
                    System.out.println("Felicidades");
                    MediaPlayer AudioF = MediaPlayer.create(getApplicationContext(), R.raw.hooray);
                    AudioF.start();

                    //Animation of character
                    RotateAnimation char_anim = new RotateAnimation(0f, 360f, character.getWidth() / 2, character.getHeight() / 2);
                    char_anim.setInterpolator(new LinearInterpolator());
                    char_anim.setRepeatCount(0);
                    char_anim.setDuration(700);
                    character.startAnimation(char_anim);
                    mApp.getStatutLevels().statut_levels.put(Levels.VEGETABLES,3);
                    mApp.getStatutLevels().statut_levels.put(Levels.TREE,-1);
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    Intent nextLvScreen = new Intent(LevelVegetables.this, Next_Level_Screen.class); //Start next activity, change with correct level
                                    startActivity(nextLvScreen);
                                }
                            }, 900);

                }
                break;
        }
    }
    public void playAudio(int contador){
        switch (contador){
            case 1:
                MediaPlayer num1 = MediaPlayer.create(getApplicationContext(), R.raw.uno);
                num1.start();
                break;
            case 2:
                MediaPlayer num2 = MediaPlayer.create(getApplicationContext(), R.raw.dos);
                num2.start();
                break;
            case 3:
                MediaPlayer num3 = MediaPlayer.create(getApplicationContext(), R.raw.tres);
                num3.start();
                break;
            case 4:
                MediaPlayer num4 = MediaPlayer.create(getApplicationContext(), R.raw.cuatro);
                num4.start();
                break;
            case 5:
                MediaPlayer num5 = MediaPlayer.create(getApplicationContext(), R.raw.cinco);
                num5.start();
                break;
            case 6:
                MediaPlayer num6 = MediaPlayer.create(getApplicationContext(), R.raw.seis);
                num6.start();
                break;
            case 7:
                MediaPlayer num7 = MediaPlayer.create(getApplicationContext(), R.raw.siete);
                num7.start();
                break;
            case 8:
                MediaPlayer num8 = MediaPlayer.create(getApplicationContext(), R.raw.ocho);
                num8.start();
                break;
            case 9:
                MediaPlayer num9 = MediaPlayer.create(getApplicationContext(), R.raw.nueve);
                num9.start();
        }
    }
}
