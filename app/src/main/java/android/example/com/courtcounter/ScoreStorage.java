package android.example.com.courtcounter;

/**
 * A simple Dataholder-Class offering methods to add and retrieve data from Main Activity
 * for the sake of splitting up logic
 */
public class ScoreStorage {

    private String id = null;
    private int triples = 0;
    private int doubles = 0;
    private int singles = 0;
    private int fouls = 0;


    public ScoreStorage(String id)
    {
        this.id = id;
    }

    public void reset()
    {
        triples = 0;
        doubles = 0;
        singles = 0;
        fouls = 0;
    }

    public int getScore() {
        return (triples * 3) + (doubles * 2) + singles;
    }

    public void addScore(int num) {
        if (num > 0 && num < 4) {
            switch (num) {
                case 3:
                    ++triples;
                    break;
                case 2:
                    ++doubles;
                    break;
                case 1:
                    ++singles;
            }
        }
    }

    public String getId()
    {
        return this.id;
    }

    public int getFouls() {
        return fouls;
    }

    public void addFoul() {
        ++fouls;
    }

    public int getTriples() {
        return triples;
    }

    public int getDoubles() {
        return doubles;
    }

    public int getSingles() {
        return singles;
    }


}
