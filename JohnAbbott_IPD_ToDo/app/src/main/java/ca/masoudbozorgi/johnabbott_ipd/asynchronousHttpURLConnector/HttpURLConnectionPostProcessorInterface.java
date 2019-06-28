package ca.masoudbozorgi.johnabbott_ipd.asynchronousHttpURLConnector;

/**
 * Created by masoud on 2017-06-20.
 */

public interface HttpURLConnectionPostProcessorInterface {

    void successHandler(String dataInXML);
    void failureHandler(Exception exception);
}