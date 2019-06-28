package ca.masoudbozorgi.customlist.xml_json;

import android.content.Context;
import android.content.res.AssetManager;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ca.masoudbozorgi.customlist.Flower;
import ca.masoudbozorgi.customlist.R;


/**
 * Created by masoudbozorgi on 2018-03-07.
 */

public class XMLParser {

    private Context context;
    private Flower currentFlower;
    private ArrayList<Flower> flowerArrayList = new ArrayList<>();;
    private String currentTag;

    private InputStream inputStream;

    private InputStreamReader inputStreamReader;

    public XMLParser(Context context) {
        this.context = context;

    }


    public ArrayList<Flower> parseXML(){

        try {
            XmlPullParserFactory xmlParserFactory = XmlPullParserFactory.newInstance();
            //xmlParserFactory.setNamespaceAware(true);
            XmlPullParser xmlParser = xmlParserFactory.newPullParser();

            // Read 'R.raw.flowers_xml' and set the result for 'inputStream' variable
            xmlInputStream();

            // Pass inputStream to XmlPullParser
            xmlParser.setInput(inputStream,null);

            // XmlPullParser:
            // 0 : START_DOCUMENT
            // 1 : END_DOCUMENT

            while(xmlParser.getEventType() != XmlPullParser.END_DOCUMENT){

                switch (xmlParser.getEventType()){

                    case XmlPullParser.START_TAG:
                        handleStartTag(xmlParser.getName());
                        break;
                    case XmlPullParser.TEXT:
                        handleText(xmlParser.getText());
                        break;
                    case XmlPullParser.END_TAG:
                        currentTag = null;
                        break;
                }
                xmlParser.next();
            }
            inputStream.close();

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        return flowerArrayList;
    }


    public void xmlInputStream() {

        // Read XML file by InputStream ------------------------------------------------------------
        inputStream = context.getResources().openRawResource(R.raw.flowers_xml);
        //stringInputStream = convertInputStreamToString(inputStream);
        //System.out.println("------------  InputStream ------------\n" + stringInputStream);
        //------------------------------------------------------------------------------------------
    }


    private void handleStartTag(String tagName) {
        if("product".equals(tagName)){
            currentFlower = new Flower();
            flowerArrayList.add(currentFlower);
        }else{
            currentTag = tagName;
        }
    }


    private void handleText(String text) {

        if(currentFlower == null || currentTag == null) return;

        switch (currentTag){

            case "productId":
                currentFlower.setProductId(Long.valueOf(text));
                break;
            case "category":
                currentFlower.setCategory(text);
                break;
            case "name":
                currentFlower.setName(text);
                break;
            case "instructions":
                currentFlower.setInstructions(text);
                break;
            case "price":
                currentFlower.setPrice(Double.valueOf(text));
                break;
            case "photo":
                currentFlower.setPhoto(text);
                System.out.println("--- XML Object:  " + currentFlower.toString());
                break;
            default: break;
        }
    }
}