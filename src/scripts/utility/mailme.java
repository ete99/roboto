package scripts.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static scripts.utility.config.POST_PARAMS;

public class mailme {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String POST_URL = "https://api.elasticemail.com/v2/email/send?";  // poner api en configure java


    public static void main(String[] args) throws IOException {

//        sendGET();
//        System.out.println("GET DONE");
        sendPOST("hola ma");
        System.out.println("POST DONE");
    }

    private static void sendGET(String GET_URL) throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }

    public static void sendPOST(String str) throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("Connection", "close");
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
//        System.out.println(POST_PARAMS);
        String POST_REQ = POST_PARAMS+str;
        os.write(POST_REQ.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        // obtiene OK
        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);


        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Respuesta si se conecto con el API

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result
            System.out.println(response.toString());

        } else {
            System.out.println("POST request not worked");
        }
    }
}
