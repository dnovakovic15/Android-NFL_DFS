package fantasy.nfl.tool.myapplication.Views;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import fantasy.nfl.tool.myapplication.API.API_GetInfo;
import fantasy.nfl.tool.myapplication.API.API_GetStartTime;
import fantasy.nfl.tool.myapplication.API.API_SendLineup;
import fantasy.nfl.tool.myapplication.Models.Player;
import fantasy.nfl.tool.myapplication.R;
import fantasy.nfl.tool.myapplication.Tools.HtmlConverter;
import fantasy.nfl.tool.myapplication.Tools.TokenSaver;

public class User_Account_0 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_account);

        Intent myIntent = getIntent();
        
        TextView salary = (TextView) findViewById(R.id.salary);
        salary.setText((int) myIntent.getSerializableExtra("Salary"));

        if(myIntent.getSerializableExtra("Players") != null){
           ArrayList<Player> players = (ArrayList) myIntent.getSerializableExtra("Players");

           for(int i = 0; i < players.size(); i++){
               setPlayer(players.get(i),i);
           }
        }
    }

    //Accepts a Player as a parameter and updates the UserAccount XML with the player's information based on the slot
    public void setPlayer(Player player, int slot) {
        switch (slot) {
            case 0:
                TextView playerNameSlot0View = (TextView) findViewById(R.id.stock_name0);
                TextView playerPercentSlot0View = (TextView) findViewById(R.id.stock_percent0);
                TextView playerShareSlot0View = (TextView) findViewById(R.id.stock_shares0);
                TextView playerPriceSlot0View = (TextView) findViewById(R.id.stock_price0);

                playerNameSlot0View.setText(player.getName());
                playerShareSlot0View.setText(player.getShares());
                playerPriceSlot0View.setText(player.getPrice());
                playerPercentSlot0View.setText((int) player.getPercent());

                break;
            case 1:
                TextView playerNameSlot1View = (TextView) findViewById(R.id.stock_name1);
                TextView playerPercentSlot1View = (TextView) findViewById(R.id.stock_percent1);
                TextView playerShareSlot1View = (TextView) findViewById(R.id.stock_shares1);
                TextView playerPriceSlot1View = (TextView) findViewById(R.id.stock_price1);

                playerNameSlot1View.setText(player.getName());
                playerShareSlot1View.setText(player.getShares());
                playerPriceSlot1View.setText(player.getPrice());
                playerPercentSlot1View.setText((int) player.getPercent());
                break;
            case 2:
                TextView playerNameSlot2View = (TextView) findViewById(R.id.stock_name2);
                TextView playerPercentSlot2View = (TextView) findViewById(R.id.stock_percent2);
                TextView playerShareSlot2View = (TextView) findViewById(R.id.stock_shares2);
                TextView playerPriceSlot2View = (TextView) findViewById(R.id.stock_price2);

                playerNameSlot2View.setText(player.getName());
                playerShareSlot2View.setText(player.getShares());
                playerPriceSlot2View.setText(player.getPrice());
                playerPercentSlot2View.setText((int) player.getPercent());
                break;
            case 3:
                TextView playerNameSlot3View = (TextView) findViewById(R.id.stock_name3);
                TextView playerPercentSlot3View = (TextView) findViewById(R.id.stock_percent3);
                TextView playerShareSlot3View = (TextView) findViewById(R.id.stock_shares3);
                TextView playerPriceSlot3View = (TextView) findViewById(R.id.stock_price3);

                playerNameSlot3View.setText(player.getName());
                playerShareSlot3View.setText(player.getShares());
                playerPriceSlot3View.setText(player.getPrice());
                playerPercentSlot3View.setText((int) player.getPercent());
                break;
        }
    }

}