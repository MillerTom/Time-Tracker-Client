package model;

import java.text.*;

/**
 *
 * @author tmiller
 */
public class Clock {

    private int Hours;
    private int Minutes;
    private int Seconds;

    /********************
     * public Clock()
     * 
     * Default constructor; sets all the private variables
     * to zero (0).
     ********************/
    public Clock() {
        Hours = 0;
        Minutes = 0;
        Seconds = 0;
    }

    /********************
     * public Clock(in Sec)
     * 
     * This constructor takes an integer representative of seconds.
     * By passing a seconds variable, we can instantiate our clock
     * at any given time. For example, 172800 seconds is equivalent
     * to 48 hours.
     ********************/
    public Clock(int Sec) {
        if (Sec > 60) {
            Hours = 0;
            Minutes = 0;
            while (Sec >= 60) {
                increaseMinutes();
                Sec -= 60;
            }
            Seconds = Sec;
        } else {
            Hours = 0;
            Minutes = 0;
            Seconds = Sec;
        }
    }

    /********************
     * public String displayTime()
     * 
     * This function is used to return the current time our clock has.
     * The return is a string, and is formatted HH:MM:SS.
     ********************/
    public String displayTime() {
        /********************
         * In order to maintain compliance with older versions
         * of Java, we use a combination of the MessageFormat class
         * and the DecimalFormat class to get the desired output.
         *******************/
        MessageFormat mf = new MessageFormat("{0}:{1}:{2}");
        DecimalFormat df = new DecimalFormat("00");
        Object[] objs = {df.format(Hours), df.format(Minutes), df.format(Seconds)};
        return mf.format(objs);
    }

    /********************
     * public void increaseSeconds()
     * 
     * This function is used to increase the Seconds by 1; it
     * is the primary focus of our class, because when it reaches,
     * it increase Minutes; when Minutes reaches 60, it increases
     * Hours, etc.
     ********************/
    public void increaseSeconds() {
        Seconds++;
        if (Seconds >= 60) {
            Seconds = 0;
            increaseMinutes();
        }
    }

    /********************
     * public void increaseMinutes()
     * 
     * This function is used to increase the Minutes by 1; it
     * is called when the Seconds variable is greater than
     * or equal to 60.
     ********************/
    public void increaseMinutes() {
        Minutes++;
        if (Minutes >= 60) {
            Minutes = 0;
            increaseHours();
        }
    }

    /********************
     * public void increaseHours()
     * 
     * This function is used to increase the Hours by 1; it
     * is called when the Minutes variable is greater than
     * or equal to 60.
     ********************/
    public void increaseHours() {
        Hours++;
        /********************
         * In the StopWatch program, we are going to allow for
         * hours greater than 24. Once we reach 24 hours, however,
         * we reset the hours back to 0. This could be any number, however.
         ********************/
        if (Hours >= 24) {
            Hours = 0;
        }
    }

    /********************
     * public void resetClock()
     * 
     * Used to reset the clock innards (the hours, minutes
     * and seconds) back to zero (0).
     ********************/
    public void resetClock() {
        Hours = 0;
        Minutes = 0;
        Seconds = 0;
    }
}