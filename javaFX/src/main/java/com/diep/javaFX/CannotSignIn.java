package com.diep.javaFX;

import com.sun.javafx.webkit.WebConsoleListener;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * https://stackoverflow.com/questions/44905264/cannot-sign-in-to-google-in-javafx-webview
 *
 * Problem: after user enters login username and next, the page does not change
 * We expected next page shows to prompt for password
 *
 * Can use testWebConsoleError() to see what's the error with WebView
 Console: [null:0] Origin https://accounts.google.com is not allowed by Access-Control-Allow-Origin.
 Console: [null:0] XMLHttpRequest cannot load https://ssl.gstatic.com/accounts/static/_/js/k=gaia.gaiafe_glif.en_GB.RK57BDQyRuo.O/am=BjAyDUAEAQAAAAAEAAAAAAAGJDhk-P8W0e0/d=0/rs=ABkqax05yO6rZZi_ZOnHUzXNX1h52zlRZw/m=SF3gsd,wI7Sfc,pB6Zqd,rHjpXd,o02Jie,YTxL4,QLpTOd,lCVo3d,sy6z,uhxrz,oWOlDb,MB66Qc,sy95,sy96,m5Z1Eb,sy5l,sy5m,sy5n,sy9c,sy9d,sy9k,sy9e,em3l,em3u,em3t,em3s,em3r,em3q,em3p,em3o,em3n,em3v,em3m,YmeC5c due to access control checks.
 Console: [null:0] Unrecognized Content-Security-Policy directive 'worker-src'.

 * This is a security feature called same-origin policy.
 * It's designed to stop pages being able to load scripts from potentially malicious third-party websites.
 * Web browser permits scripts contained in a first web page to access data in second web page
 * if both web pages have the same origin (i.e. combination of URI scheme, host name & port number)
 * Same-origin policy protect sites that use authenticated sessions in following case:
 * 1. A user visits banking website and does not logout
 * 2. Then user goes to another site that has some malicious JS code requesting data from banking site
 * Since user is still logging in banking site, malicious code could do anything user could do on banking site
 * like requesting last transactions, creating new transaction
 * This is because the browser can send and receive session cookies to the banking site based on the domain of the banking site
 * Though JS has no direct access to banking to the banking session, it could send and receive requests to the banking site
 * with banking site's session cookie
 *
 */
public class CannotSignIn extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        /**
         * Use this for testing if any error
         * when sun.net.http.allowRestrictedHeaders=false
         */
        // testWebConsoleError();
        WebView browser = new WebView();

        WebEngine webEngine = browser.getEngine();

        webEngine.load("https://calendar.google.com");

        StackPane root = new StackPane();
        root.getChildren().add(browser);

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    private void testWebConsoleError() {
        WebConsoleListener.setDefaultListener((webView, message, lineNumber, sourceId) ->
                System.out.println("Console: [" + sourceId + ":" + lineNumber + "] " + message)
        );
    }

    public static void main(String[] args) {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        launch(args);
    }
}
