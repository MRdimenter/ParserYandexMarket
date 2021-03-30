package API;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApiMethods {


    /**
     * -- Получение JSON по URL и ID товара --
     */

    public String getJsonByApi(String url, String id) {

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);

        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("key", "977823459350b865283fdc130a5c53bf"));
        params.add(new BasicNameValuePair("id", id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Execute and get the response.
        HttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /// HttpEntity entity = response.getEntity();


        Scanner scanner = null;
        try {
            scanner = new Scanner(response.getEntity().getContent(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scanner.nextLine();
    }
}
