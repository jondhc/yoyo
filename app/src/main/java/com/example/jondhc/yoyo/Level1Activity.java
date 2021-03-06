package com.example.jondhc.yoyo;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Level1Activity extends AppCompatActivity {

    int basket_counter = 0; //Counter for the fruits
    public int quantity;    //Quantity of fruits to display
    private ImageView character;   //Variable for charater imageView
    private GlobalApplication mApp;
    private Characters selectedC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_level1);
        mApp = ((GlobalApplication) getApplicationContext());
        selectedC = mApp.getGlobalVarValue();

        if (selectedC == Characters.CAT) {
            character = (ImageView) findViewById(R.id.cat_catch);    //Getting cat image
            GlideApp.with(this).load(R.drawable.cat).into(character);
        } else if (selectedC == Characters.DOG) {
            character = (ImageView) findViewById(R.id.dog_catch);    //Getting dog image
            GlideApp.with(this).load(R.drawable.dog).into(character);
        }

        Random randomGenerator = new Random();  //Random generator for fruits number
        int maxFruits = 9;  //Maximum Number of fruits to display
        int minFruits = 1;  //Minimum number of fruits to display
        int range = maxFruits - minFruits + 1;  //Range of fruits to be generated randomly
        quantity = randomGenerator.nextInt(range) + minFruits;  //Setting number between the range
        System.out.println("Cantidad de frutas: " + quantity);  //Printing to console the number of fruits to display

        //Hiding the status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //end of Hiding the status bar

        //Hiding action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
        //End of hiding action bar

        View basket = findViewById(R.id.basket);    //Get basket view
        basket.setOnDragListener(new DragHandlingListener());   //set drag handler to detect when a fruit is dropped inside

        TextView counter = (TextView) findViewById(R.id.count_of_fruits);   //textView to display the counter of collected fruits
        counter.setText(String.valueOf(basket_counter));    //set the text on the counter to the number of collected fruits

        //Adding drag and drop functionality to every berry.
        View berry1 = findViewById(R.id.berry1);
        berry1.setOnTouchListener(new DragTouchListener());
        View berry2 = findViewById(R.id.berry2);
        berry2.setOnTouchListener(new DragTouchListener());
        View berry3 = findViewById(R.id.berry3);
        berry3.setOnTouchListener(new DragTouchListener());
        View berry4 = findViewById(R.id.berry4);
        berry4.setOnTouchListener(new DragTouchListener());
        View berry5 = findViewById(R.id.berry5);
        berry5.setOnTouchListener(new DragTouchListener());
        View berry6 = findViewById(R.id.berry6);
        berry6.setOnTouchListener(new DragTouchListener());
        View berry7 = findViewById(R.id.berry7);
        berry7.setOnTouchListener(new DragTouchListener());
        View berry8 = findViewById(R.id.berry8);
        berry8.setOnTouchListener(new DragTouchListener());
        View berry9 = findViewById(R.id.berry9);
        berry9.setOnTouchListener(new DragTouchListener());
        //end of Adding drag and drop functionality to every berry.

        //Switch-case for the quantity of fruits to be generated
        switch (quantity) {
            case 1:
                berry4.setVisibility(View.VISIBLE);
                break;
            case 2:
                berry6.setVisibility(View.VISIBLE);
                berry2.setVisibility(View.VISIBLE);
                break;
            case 3:
                berry4.setVisibility(View.VISIBLE);
                berry5.setVisibility(View.VISIBLE);
                berry6.setVisibility(View.VISIBLE);
                break;
            case 4:
                berry1.setVisibility(View.VISIBLE);
                berry4.setVisibility(View.VISIBLE);
                berry6.setVisibility(View.VISIBLE);
                berry9.setVisibility(View.VISIBLE);
                break;
            case 5:
                berry1.setVisibility(View.VISIBLE);
                berry3.setVisibility(View.VISIBLE);
                berry5.setVisibility(View.VISIBLE);
                berry6.setVisibility(View.VISIBLE);
                berry9.setVisibility(View.VISIBLE);
                break;
            case 6:
                berry1.setVisibility(View.VISIBLE);
                berry2.setVisibility(View.VISIBLE);
                berry3.setVisibility(View.VISIBLE);
                berry5.setVisibility(View.VISIBLE);
                berry6.setVisibility(View.VISIBLE);
                berry8.setVisibility(View.VISIBLE);
                break;
            case 7:
                berry1.setVisibility(View.VISIBLE);
                berry2.setVisibility(View.VISIBLE);
                berry4.setVisibility(View.VISIBLE);
                berry5.setVisibility(View.VISIBLE);
                berry6.setVisibility(View.VISIBLE);
                berry7.setVisibility(View.VISIBLE);
                berry9.setVisibility(View.VISIBLE);
                break;
            case 8:
                berry1.setVisibility(View.VISIBLE);
                berry2.setVisibility(View.VISIBLE);
                berry4.setVisibility(View.VISIBLE);
                berry5.setVisibility(View.VISIBLE);
                berry6.setVisibility(View.VISIBLE);
                berry7.setVisibility(View.VISIBLE);
                berry8.setVisibility(View.VISIBLE);
                berry9.setVisibility(View.VISIBLE);
                break;
            case 9:
                berry1.setVisibility(View.VISIBLE);
                berry2.setVisibility(View.VISIBLE);
                berry3.setVisibility(View.VISIBLE);
                berry4.setVisibility(View.VISIBLE);
                berry5.setVisibility(View.VISIBLE);
                berry6.setVisibility(View.VISIBLE);
                berry7.setVisibility(View.VISIBLE);
                berry8.setVisibility(View.VISIBLE);
                berry9.setVisibility(View.VISIBLE);
                break;
        }//end Switch
        //end of Switch-case for the quantity of fruits to be generated


        character.setOnClickListener(new View.OnClickListener() { //Setting action when character is touched
            @Override
            public void onClick(View v) {
                MediaPlayer initialInstructions = MediaPlayer.create(getApplicationContext(), R.raw.instructions_g);    //Set audio to instructions audio
                initialInstructions.start();    //Play instructions audio
            }
        });//end setOnClickListener
    }//end onCreate

    @Override
    public boolean onSupportNavigateUp() {  //Method to show action bar, not used
        finish();
        return true;
    }//end onSupportNavigateUp

    class userWon extends TimerTask {   //Runnable for congratulations action to be delayed
        public void run() {
            MediaPlayer winning = MediaPlayer.create(getApplicationContext(), R.raw.hooray);   //set audio to user-won audio
            winning.start();    //Play audio
        }
    }


    private final class DragTouchListener implements View.OnTouchListener { //Method to execute when user drags a fruit
        public boolean onTouch(View view, MotionEvent motionEvent) {    //Using as parameters the view which is interacted with and the motion
            int action = motionEvent.getAction();   //get the action
            switch (action) {
                case MotionEvent.ACTION_DOWN:   //When fruit is selected and dragged
                    System.out.println("Action Down");
                    ClipData data = ClipData.newPlainText("", "");  //Text to keep in clipboard if needed.
                    View.DragShadowBuilder shadowbuilder = new View.DragShadowBuilder(view);    //Shadow to show when dragging
                    view.startDrag(data, shadowbuilder, view, 0);   //start dragging action
                    view.setAlpha(0);   //Set alpha to 0 so the fruit is not shown in original place while dragging
                    break;
                case MotionEvent.ACTION_UP: //Code not accesed, other cases are handled in dragHandlingListener class
                    System.out.println("Action Up");
                    break;
            }//end switch
            return true;
        }//end onTouch
    } //end DragTouchListener

    class DragHandlingListener implements View.OnDragListener { //Handle dragging
        @Override
        public boolean onDrag(View view, DragEvent event) {
            int dragAction = event.getAction(); //Get action when user drags and drops fruit
            switch (dragAction) {
                case DragEvent.ACTION_DRAG_STARTED: //When drag is started
                    break;
                case DragEvent.ACTION_DRAG_ENTERED: //When drag action is entered
                    break;
                case DragEvent.ACTION_DRAG_EXITED:  //When drag action is exited
                    break;
                case DragEvent.ACTION_DROP: //When user drops fruit
                    basket_counter += 1;    //Add one when a fruit is selected
                    TextView counter = (TextView) findViewById(R.id.count_of_fruits);   //Get the counter of collected fruits
                    counter.setText(String.valueOf(basket_counter));    //set the basket counter to the number of collected fruits
                    View basket_berry;
                    //Media player
                    int audio = 0;
                    switch (basket_counter) {   //For every counted fruit, select the according audio
                        case 1:
                            audio = R.raw.uno;
                            basket_berry = findViewById(R.id.berry_basket_1);
                            basket_berry.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            audio = R.raw.dos;
                            basket_berry = findViewById(R.id.berry_basket_2);
                            basket_berry.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            audio = R.raw.tres;
                            basket_berry = findViewById(R.id.berry_basket_3);
                            basket_berry.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            audio = R.raw.cuatro;
                            basket_berry = findViewById(R.id.berry_basket_4);
                            basket_berry.setVisibility(View.VISIBLE);
                            break;
                        case 5:
                            audio = R.raw.cinco;
                            basket_berry = findViewById(R.id.berry_basket_5);
                            basket_berry.setVisibility(View.VISIBLE);
                            break;
                        case 6:
                            audio = R.raw.seis;
                            basket_berry = findViewById(R.id.berry_basket_6);
                            basket_berry.setVisibility(View.VISIBLE);
                            break;
                        case 7:
                            audio = R.raw.siete;
                            basket_berry = findViewById(R.id.berry_basket_7);
                            basket_berry.setVisibility(View.VISIBLE);
                            break;
                        case 8:
                            audio = R.raw.ocho;
                            basket_berry = findViewById(R.id.berry_basket_8);
                            basket_berry.setVisibility(View.VISIBLE);
                            break;
                        case 9:
                            audio = R.raw.nueve;
                            basket_berry = findViewById(R.id.berry_basket_9);
                            basket_berry.setVisibility(View.VISIBLE);
                            break;
                    }//end switch
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), audio);    //set the audio to selected audio
                    mp.start(); //Play the audio of the number of fruits.
                    if (basket_counter == quantity) {   //When all the fruits are collected
                        Timer timer = new Timer();  //Timer for Winning audio to run
                        timer.schedule(new userWon(), 1000);    //Setting delay for audio
                        System.out.println("User won!");
                        //Animation of character
                        RotateAnimation char_anim = new RotateAnimation(0f, 360f, character.getWidth() / 2, character.getHeight() / 2);
                        char_anim.setInterpolator(new LinearInterpolator());
                        char_anim.setRepeatCount(0);
                        char_anim.setDuration(700);
                        character.startAnimation(char_anim);
                        mApp.getStatutLevels().statut_levels.put(Levels.TREE,3);
                        mApp.getStatutLevels().statut_levels.put(Levels.BRIDGE,-1);
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        Intent nextLvScreen = new Intent(Level1Activity.this, Next_Level_Screen.class); //Start next activity, change with correct level
                                        startActivity(nextLvScreen);
                                    }
                                }, 900);

                        //ends Animation of character
                    }//end if
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    if (event.getResult()) {    //When drop action was made in the selected place
                        System.out.println("Was dropped in the correct place");
                        View originView = (View) event.getLocalState(); //Get the element that was dropped
                        originView.setVisibility(View.INVISIBLE);   //If the fruit was dropped correctly, set as invisible
                    } else if (!event.getResult()) {    //When drop action was not succesful (fruit dropped outside the basket)
                        System.out.println("Fruit was dropped outside");
                        View originView = (View) event.getLocalState(); //Get the element that was dropped
                        originView.setAlpha(1); //return to its initial state
                    }//end else if
                    break;
                default:
                    break;
            }//end switch
            return true;
        }//end onDrag
    }//end DragHandlingListener
}//end Level1Activity