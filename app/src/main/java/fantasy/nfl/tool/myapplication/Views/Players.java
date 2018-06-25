package fantasy.nfl.tool.myapplication.Views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fantasy.nfl.tool.myapplication.API.API_Players;
import fantasy.nfl.tool.myapplication.Models.Player;
import fantasy.nfl.tool.myapplication.R;
import fantasy.nfl.tool.myapplication.Tools.TokenSaver;

//Activity that displays the point guards playing the current day.
public class Players extends AppCompatActivity {


    private int activityIdentity;

    //Calls the API Class to get the point guards playing today.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.players);

        API_Players apiGetPlayers = new API_Players();
        List players = new ArrayList<>();

        //Gets the previously created intent and its extras
        Intent myIntent = getIntent();
        activityIdentity = myIntent.getIntExtra("ActivityIdentity", 0);

        //Execute an async task, apiGetPlayers, from class API_Players and save them in the List players
        try {
            players = apiGetPlayers.execute(TokenSaver.getToken(getApplicationContext())).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //Create linearlayouts and then call functions fillLayout and fillppg in order to fill in the layouts
        LinearLayout rootView = (LinearLayout) findViewById(R.id.AdvancedCatalogContainer);
        fillLayout(rootView, players);

        //FUTURE USE: Add percentage increase for stock holder
//        LinearLayout ppg = (LinearLayout) findViewById(R.id.ppg);
//        fillppg(ppg, players);
    }

    //Takes in a layout and list and fills in the layout with the listed items while also formatting the layout's aesthetically
    public void fillLayout(LinearLayout root, final List<Player> Players) {
        for (int i = 0; i < Players.size(); i++) {
            Button myButton = new Button(this);
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            DecimalFormat formatter = new DecimalFormat("#,###");
            int price = (int) Players.get(i).getPrice();

            if(i == 0 || Players.get(i - 1).getName().indexOf(Players.get(i).getName()) < 0){
                myButton.setText(Players.get(i).getName() + "\n" + formatter.format(price));
            }
            else{
                continue;
            }
            myButton.setBackgroundColor(Color.WHITE);
            params.setMargins(0, 10, 0, 0);
            myButton.setHeight(240);
            myButton.setTextSize(18);
            myButton.setLayoutParams(params);
            myButton.setId(i);
            final int finalI = i;
            Button.OnClickListener myButtonClick = new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Players.this, PlayersStats.class);
                    Bundle b = new Bundle();
                    Intent myIntent = getIntent();
                    b.putString("FirstName", Players.get(finalI).getName());
                    b.putString("Opponent", Players.get(finalI).getOpponent());
                    b.putInt("ActivityIdentity", activityIdentity);
                    b.putInt("Price", (int) Players.get(finalI).getPrice());
                    b.putInt("Position", assignPosition(Players.get(finalI).getPosition(), myIntent.getIntExtra("Position", 0)));
                    intent.putExtras(b);
                    startActivityForResult(intent, 1);
                }
            };
            myButton.setOnClickListener(myButtonClick);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                myButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            root.addView(myButton);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case (0): {
                System.out.println("We made it Morty00!");
                break;
            }
            case (1): {
                if(data != null) {
                    System.out.println("FINISHED HERE");
                    String playerName = data.getStringExtra("Name");
                    String projectedPoints = data.getStringExtra("ProjectedPoints");
                    int price = data.getIntExtra("Price", 1);
                    Intent intent = new Intent(Players.this, Players.class);
                    intent.putExtra("Name", playerName);
                    intent.putExtra("ProjectedPoints", projectedPoints);
                    intent.putExtra("Price", price);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                    break;
                }
            }
        }
    }


    //Fills in each Players's button with their Draftkings fantasy points per game.
    public void fillppg(LinearLayout ppg, List<Player> Players) {
        for (int i = 0; i < Players.size(); i++) {
            TextView myView = new TextView(this);
            double first = Players.get(i).getPpg();
            DecimalFormat formatter = new DecimalFormat("##.#");
            myView.setTextSize(18);
            myView.setTextColor(Color.BLACK);
            myView.setHeight(240);
            myView.setText(formatter.format(first));
            myView.setBackgroundColor(Color.WHITE);
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 10, 0, 0);
            myView.setLayoutParams(params);
            myView.setId(i + 100);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                myView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                myView.setPadding(0, 75, 0, 0);
            }
            ppg.addView(myView);
        }
    }

    //Maps flex player positions to their actual position.
    public int assignPosition(String position, int multiplePosition) {
        if (multiplePosition == 5) {
            if (position.indexOf("QB") > -1) {
                return 0;
            }
            else if (position.indexOf("RB") > -1) {
                return 1;
            }
            else if (position.indexOf("WR") > -1) {
                return 2;
            }
            else if (position.indexOf("TE") > -1) {
                return 3;
            }
            else{
                return 4;
            }
        }
        else{
            return multiplePosition;
        }
    }
}