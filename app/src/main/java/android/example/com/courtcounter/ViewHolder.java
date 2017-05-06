package android.example.com.courtcounter;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

/**
 * Created by ByteTonight on 26.03.2017.
 * This was actually an experiment to see if I can get around
 * running findViewById each time the config changes.
 * The Viewholder instances did hold View resources, but not the ones needed
 */

public class ViewHolder implements Parcelable
{

    public TextView scoreView;
    public TextView tripleCountView;
    public TextView doubleCountView;
    public TextView singleCountView;
    public TextView foulsCountView;

    public ViewHolder(Parcel in)
    {

    }

    public ViewHolder()
    {

    }


    public static final Creator<ViewHolder> CREATOR = new Creator<ViewHolder>()
    {
        @Override
        public ViewHolder createFromParcel(Parcel in)
        {
            return new ViewHolder(in);
        }

        @Override
        public ViewHolder[] newArray(int size)
        {
            return new ViewHolder[size];
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
}
