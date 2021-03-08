package tk.iovr.mvctest.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import tk.iovr.mvctest.R;
import tk.iovr.mvctest.model.Board;
import tk.iovr.mvctest.model.Player;

public class MainActivity extends AppCompatActivity {


    private Board model;

    private ViewGroup buttonGrid;
    private View winnerPlayerViewGroup;
    private TextView winnerPlayerLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        winnerPlayerLabel = findViewById(R.id.winnerPlayerLabel);
        winnerPlayerViewGroup = findViewById(R.id.winnerPlayerViewGroup);
        buttonGrid = findViewById(R.id.buttonGrid);

        model = new Board();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tictactoe,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_reset:
                reset();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void reset() {
        winnerPlayerViewGroup.setVisibility(View.INVISIBLE);
        winnerPlayerLabel.setText("");

        model.restart();

        for(int i =0;i<buttonGrid.getChildCount();i++){
            ((Button)buttonGrid.getChildAt(i)).setText("");
        }

    }

    public void onCellClicked(View view){


        Button button = (Button) view;

        String tag = button.getTag().toString();
        int row = Integer.parseInt(tag.substring(0,1));
        int col = Integer.parseInt(tag.substring(1,2));

        Player playerThatMoved = model.mark(row,col);

        if (playerThatMoved !=null){
            button.setText(playerThatMoved.toString());
            if (model.getWinner()!=null){
                winnerPlayerLabel.setText(playerThatMoved.toString());
                winnerPlayerViewGroup.setVisibility(View.VISIBLE);
            }
        }
    }

}