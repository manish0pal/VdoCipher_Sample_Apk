package com.vdochiper;

import android.util.Log;
import android.util.Pair;

import com.vdocipher.aegis.player.VdoPlayer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Utility class
 */

public class Utils {
    private static final String TAG = "Utils";

    static String digitalClockTime(int timeInMilliSeconds) {
        int totalSeconds = timeInMilliSeconds/1000;
        int hours = totalSeconds / (60 * 60);
        int minutes = (totalSeconds - hours * 60 * 60) / 60;
        int seconds = (totalSeconds - hours * 60 * 60 - minutes * 60);

        String timeThumb = "";
        if (hours > 0) {
            if (hours < 10) {
                timeThumb += "0" + hours + ":";
            } else {
                timeThumb += hours + ":";
            }
        }
        if (minutes > 0) {
            if (minutes < 10) {
                timeThumb += "0" + minutes + ":";
            } else {
                timeThumb += minutes + ":";
            }
        } else {
            timeThumb += "00" + ":";
        }
        if (seconds < 10) {
            timeThumb += "0" + seconds;
        } else {
            timeThumb += seconds;
        }
        return timeThumb;
    }

    /**
     * @return index of number in provided array closest to the provided number
     */
    public static int getClosestFloatIndex(float[] refArray, float comp) {
        float distance = Math.abs(refArray[0] - comp);
        int index = 0;
        for (int i = 1; i < refArray.length; i++) {
            float currDistance = Math.abs(refArray[i] - comp);
            if (currDistance < distance) {
                index = i;
                distance = currDistance;
            }
        }
        return index;
    }




    // call on non-ui thread only
    public static Pair<String, String> getSampleOtpAndPlaybackInfo() throws IOException, JSONException {

        //add the link here to get api

        final String SAMPLE_OTP_PLAYBACK_INFO_URL = "https://sociological-lungs.000webhostapp.com/index.php";

        URL url = new URL(SAMPLE_OTP_PLAYBACK_INFO_URL);
        final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == 200) {
            InputStream is = connection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inLine;
            StringBuffer responseBuffer = new StringBuffer();

            while ((inLine = br.readLine()) != null) {
                responseBuffer.append(inLine);
            }
            br.close();

            String response = responseBuffer.toString();
            Log.i(TAG, "response: " + response);

            JSONObject jObj = new JSONObject(response);
            String otp = jObj.getString("otp");
            String playbackInfo = jObj.getString("playbackInfo");
            return Pair.create(otp, playbackInfo);
        } else {
            Log.e(TAG, "error response code = " + responseCode);
            throw new IOException("Network error, code " + responseCode);
        }
    }
}
