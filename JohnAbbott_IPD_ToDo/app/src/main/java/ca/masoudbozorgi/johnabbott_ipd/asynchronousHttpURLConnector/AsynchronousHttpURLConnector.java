package ca.masoudbozorgi.johnabbott_ipd.asynchronousHttpURLConnector;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by masoud on 2017-06-20.
 */


//AsyncTask<Void, Intent, String>

//Void:     We do not want to send anything to doInBackground method
//Integer:  Give us information about background task progress
//String:   Define return type of doInBackground method


public class AsynchronousHttpURLConnector extends AsyncTask<Void, Integer, String> {

    private String urlString;
    private HttpURLConnectionPostProcessorInterface httpURLConnectionPostProcessorInterface;

    private TextView textViewProgress;

    public AsynchronousHttpURLConnector(HttpURLConnectionPostProcessorInterface httpURLConnectionPostProcessorInterface,
                                        String urlString,
                                        TextView textViewProgress) {

        this.urlString = urlString;

        // This is our Activity which implements the HttpURLConnectionPostProcessorInterface
        this.httpURLConnectionPostProcessorInterface = httpURLConnectionPostProcessorInterface;
        this.textViewProgress = textViewProgress;
    }




    @Override
    protected String doInBackground(Void... params) {

        String urlConnectionResultString = "";
        HttpURLConnection httpURLConnection = null;

        try {
            System.out.println("--------------------> " + urlString);

            httpURLConnection = (HttpURLConnection) new URL(urlString).openConnection();

            InputStream urlConnectionInputStream = httpURLConnection.getInputStream();
            //BufferedInputStream bis = new BufferedInputStream(urlConnectionInputStream);
            System.out.println("-------------------- urlConnectionInputStream: " + urlConnectionInputStream);

            // Convert inputStream to String
            urlConnectionResultString = inputStreamToString(urlConnectionInputStream);

        } catch (Exception exception) {
            // Call failureHandler in Activity
            httpURLConnectionPostProcessorInterface.failureHandler(exception);
        }
        return urlConnectionResultString;
    }






    // Called in doInBackground method to read urlConnectionInputStream
    private String inputStreamToString(InputStream inputStream) {

        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        bufferedReader = new BufferedReader(inputStreamReader);
        String oneLine = null;

        int counter = 0;

        try {
            while ((oneLine = bufferedReader.readLine()) != null) {

                stringBuilder.append(oneLine);

                //---------------------------------- Trigger calling onProgressUpdate() to show background progress
                publishProgress(counter++);
            }
        } catch (Exception e) {
            httpURLConnectionPostProcessorInterface.failureHandler(e);
        }

        System.out.println("-------------------- Result String: " + stringBuilder.toString());
        return stringBuilder.toString();
    }



    //--------------------------------------------  Manually override
    // Output of doInBackground method that we named it "urlConnectionResultString"
    // is going to pass to onPostExecute by Android
    @Override
    protected void onPostExecute(String urlConnectionResultString) {
        super.onPostExecute(urlConnectionResultString);

        System.out.println("-------------------- onPostExecute: " + urlConnectionResultString);

        // Call successHandler method from HttpURLConnectionPostProcessorInterface reference which
        // is our Activity to do UI manipulation
        httpURLConnectionPostProcessorInterface.successHandler(urlConnectionResultString);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        textViewProgress.setText("Number of lines in loaded web page: " + String.valueOf(values[0]));
        textViewProgress.setTextSize(16);
        System.out.println("----------------------- Number of lines in loaded web page: " + String.valueOf(values[0]));
    }
}