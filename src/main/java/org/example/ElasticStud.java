package org.example;

import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ElasticStud {
    RestClient restClient = RestClient.builder(
            new HttpHost("localhost",9200,"http")
    ).build();
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        ElasticStud object = new ElasticStud();
        Connection connection = object.EsConnection();
        object.addData();
        object.getData();
    }

    public Connection EsConnection() throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement", "root", "password");
        return connection;
    }
    public void addData() throws IOException {
        System.out.println("connected to Elastic Search");
        String json = "{\"id\":\"4\",\"name\":\"Sumesh\",\"address\":\"yokoani\"}";
        Request request = new Request("POST", "/student/_doc/1");
        request.setJsonEntity(json);
        Response response = restClient.performRequest(request);

        System.out.println(EntityUtils.toString(response.getEntity()));
    }
    public void getData() throws IOException {
        Request request = new Request("GET", "/student/_doc/1");
        Response response = restClient.performRequest(request);

        System.out.println(EntityUtils.toString(response.getEntity()));
    }

}
