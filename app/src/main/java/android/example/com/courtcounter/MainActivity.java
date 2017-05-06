package android.example.com.courtcounter;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    private static final String SCORE_HOLDER_TEAM_A = "scoreTeamA";
    private static final String SCORE_HOLDER_TEAM_B = "scoreTeamB";
    private static final String VIEW_HOLDER_TEAM_A = "viewTeamA";
    private static final String VIEW_HOLDER_TEAM_B = "viewTeamB";

    private ViewHolder viewTeamA;
    private ViewHolder viewTeamB;
    private ScoreStorage scoreTeamA;
    private ScoreStorage scoreTeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar ackshunbar =  getSupportActionBar();

        switch (getResources().getConfiguration().orientation)
        {
            case Configuration.ORIENTATION_PORTRAIT:
                if (ackshunbar != null)
                    ackshunbar.show();
                break;

            case Configuration.ORIENTATION_LANDSCAPE:
                if (ackshunbar != null)
                    ackshunbar.hide();
                break;
        }

        if (savedInstanceState != null)
        {
            scoreTeamA = savedInstanceState.getParcelable(SCORE_HOLDER_TEAM_A);
            scoreTeamB = savedInstanceState.getParcelable(SCORE_HOLDER_TEAM_B);
            viewTeamA = savedInstanceState.getParcelable(VIEW_HOLDER_TEAM_A);
            viewTeamB = savedInstanceState.getParcelable(VIEW_HOLDER_TEAM_B);
        }
        else
        {
            scoreTeamA = new ScoreStorage("a");
            scoreTeamB = new ScoreStorage("b");
            viewTeamA = new ViewHolder();
            viewTeamB = new ViewHolder();
        }

        prepareViewHolder(viewTeamA, scoreTeamA);
        prepareViewHolder(viewTeamB, scoreTeamB);
        displayScoreDetails(viewTeamA, scoreTeamA);
        displayScoreDetails(viewTeamB, scoreTeamB);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SCORE_HOLDER_TEAM_A, scoreTeamA);
        outState.putParcelable(SCORE_HOLDER_TEAM_B, scoreTeamB);
        outState.putParcelable(VIEW_HOLDER_TEAM_A, viewTeamA);
        outState.putParcelable(VIEW_HOLDER_TEAM_B, viewTeamB);
    }

    public void addScore(View view)
    {
        String tag = view.getTag().toString();
        String team = tag.split(",")[0];
        int scoreToAdd = Integer.parseInt(tag.split(",")[1]);

        switch (team)
        {
            case "TeamA":
                scoreTeamA.addScore(scoreToAdd);
                displayScoreDetails(viewTeamA, scoreTeamA);
                break;
            case "TeamB":
                scoreTeamB.addScore(scoreToAdd);
                displayScoreDetails(viewTeamB, scoreTeamB);
                break;
        }
    }

    public void addFoul(View view)
    {

        String team = view.getTag().toString();

        switch (team)
        {
            case "TeamA":
                scoreTeamA.addFoul();
                displayScoreDetails(viewTeamA, scoreTeamA);
                break;
            case "TeamB":
                scoreTeamB.addFoul();
                displayScoreDetails(viewTeamB, scoreTeamB);
                break;
        }
    }

    private void prepareViewHolder(ViewHolder v, ScoreStorage team)
    {
        String teamId = team.getId();
        String packageName = getPackageName();
        v.scoreView = (TextView) findViewById(
                getResources().getIdentifier("team_" + teamId + "_score", "id", packageName));
        v.tripleCountView = (TextView) findViewById(
                getResources().getIdentifier("team_" + teamId + "_triples", "id", packageName));
        v.doubleCountView = (TextView) findViewById(
                getResources().getIdentifier("team_" + teamId + "_doubles", "id", packageName));
        v.singleCountView = (TextView) findViewById(
                getResources().getIdentifier("team_" + teamId + "_singles", "id", packageName));
        v.foulsCountView = (TextView) findViewById(
                getResources().getIdentifier("team_" + teamId + "_fouls", "id", packageName));
    }

    /**
     * Updates data on-screen, reduces redundant code
     *
     * @param team : The Instance of ScoreStorage that is passed to this method
     */
    private void displayScoreDetails(ViewHolder v, ScoreStorage team)
    {

        v.scoreView.setText(String.valueOf(team.getScore()));
        v.tripleCountView.setText(String.valueOf(team.getTriples()));
        v.doubleCountView.setText(String.valueOf(team.getDoubles()));
        v.singleCountView.setText(String.valueOf(team.getSingles()));
        v.foulsCountView.setText(String.valueOf(team.getFouls()));
    }

    public void reset(View view)
    {
        scoreTeamA.reset();
        scoreTeamB.reset();
        displayScoreDetails(viewTeamA, scoreTeamA);
        displayScoreDetails(viewTeamB, scoreTeamB);
    }
}
