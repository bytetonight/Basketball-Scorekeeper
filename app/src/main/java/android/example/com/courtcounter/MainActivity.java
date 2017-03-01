package android.example.com.courtcounter;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected ScoreStorage scoreTeamA = new ScoreStorage("a");
    protected ScoreStorage scoreTeamB = new ScoreStorage("b");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //setContentView(R.layout.activity_main);
        useLayout();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        useLayout();
        displayScoreDetails(scoreTeamA);
        displayScoreDetails(scoreTeamB);
    }

    /**
     * Apply a layout according to current orientation.
     * Reduces redundant code
     */
    public void useLayout()
    {
        switch (getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                getSupportActionBar().show();
                setContentView(R.layout.activity_main);
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                getSupportActionBar().hide();

                setContentView(R.layout.activity_main_horizontal);
                break;
        }
    }

    public void addScore(View view) {
        String tag = view.getTag().toString();
        String team = tag.split(",")[0];
        int scoreToAdd = Integer.parseInt(tag.split(",")[1]);

        switch (team) {
            case "TeamA":
                scoreTeamA.addScore(scoreToAdd);
                displayScoreDetails(scoreTeamA);
                break;
            case "TeamB":
                scoreTeamB.addScore(scoreToAdd);
                displayScoreDetails(scoreTeamB);
                break;
        }
    }

    public void addFoul(View view) {

        String team = view.getTag().toString();

        switch (team) {
            case "TeamA":
                scoreTeamA.addFoul();
                displayScoreDetails(scoreTeamA);
                break;
            case "TeamB":
                scoreTeamB.addFoul();
                displayScoreDetails(scoreTeamB);
                break;
        }
    }

    /**
     * Updates data on-screen, reduces redundant code
     * @param team : The Instance of ScoreStorage that is passed to this method
     */
    public void displayScoreDetails(ScoreStorage team)
    {
        TextView scoreView = (TextView) findViewById(getResources().getIdentifier("team_"+team.getId()+"_score", "id", getPackageName()));
        TextView tripleCountView = (TextView) findViewById(getResources().getIdentifier("team_"+team.getId()+"_triples", "id", getPackageName()));
        TextView doubleCountView = (TextView) findViewById(getResources().getIdentifier("team_"+team.getId()+"_doubles", "id", getPackageName()));
        TextView singleCountView = (TextView) findViewById(getResources().getIdentifier("team_"+team.getId()+"_singles", "id", getPackageName()));
        TextView foulsCountView = (TextView) findViewById(getResources().getIdentifier("team_"+team.getId()+"_fouls", "id", getPackageName()));
        scoreView.setText(String.valueOf(team.getScore()));
        tripleCountView.setText(String.valueOf(team.getTriples()));
        doubleCountView.setText(String.valueOf(team.getDoubles()));
        singleCountView.setText(String.valueOf(team.getSingles()));
        foulsCountView.setText(String.valueOf(team.getFouls()));
    }

    public void reset(View view) {
        scoreTeamA.reset();
        scoreTeamB.reset();
        displayScoreDetails(scoreTeamA);
        displayScoreDetails(scoreTeamB);

    }
}
