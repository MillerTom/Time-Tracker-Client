/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/**
 *
 * @author tmiller
 */
public class XMLHandler {

    private String userName;
    private String password;
    private String schema;
    private String driver;
    private String url;
    private String port;
    
    public XMLHandler() {
        try {
            URL fileUrl=this.getClass().getClassLoader().getResource("Config.xml");
            File file = new File(fileUrl.getPath());
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            String usr = document.getElementsByTagName("user-name").item(0).getTextContent();
            this.setUserName(usr);
            String pwd = document.getElementsByTagName("password").item(0).getTextContent();
            this.setPassword(pwd);
            String schema = document.getElementsByTagName("schema").item(0).getTextContent();
            this.setSchema(schema);
            String driver = document.getElementsByTagName("driver").item(0).getTextContent();
            this.setDriver(driver);
            String url = document.getElementsByTagName("url").item(0).getTextContent();
            this.setUrl(url);
            String port = document.getElementsByTagName("port").item(0).getTextContent();
            this.setPort(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
