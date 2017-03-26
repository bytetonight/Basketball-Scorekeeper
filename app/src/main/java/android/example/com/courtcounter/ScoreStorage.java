package android.example.com.courtcounter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A simple Dataholder-Class offering methods to add and retrieve data from Main Activity
 * for the sake of splitting up logic
 */
public class ScoreStorage implements Parcelable{

    private String id = null;
    private int triples = 0;
    private int doubles = 0;
    private int singles = 0;
    private int fouls = 0;

    public ScoreStorage(Parcel in)
    {

    }

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


//region Getters & Setters
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
//endregion

//region parcelable implementation

    public static final Creator<ScoreStorage> CREATOR = new Creator<ScoreStorage>()
    {
        @Override
        public ScoreStorage createFromParcel(Parcel in)
        {
            return new ScoreStorage(in);
        }

        @Override
        public ScoreStorage[] newArray(int size)
        {
            return new ScoreStorage[size];
        }
    };

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents()
    {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {

    }

//endregion

}
