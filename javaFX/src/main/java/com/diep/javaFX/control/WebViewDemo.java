package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.Iterator;

/**
 * WebView is mini browser, which can show web pages (HTML, CSS, SVG, JavaScript)
 * Handy to show documentation, news, blog posts, other contents downloaded from web at runtime
 * JavaFX WebView uses WebKit open source browser engine internally to render web pages
 *
 * WebEngine is internal component of WebView to load data
 */
public class WebViewDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("WebView Demo");
        VBox box = new VBox(createWebView());
        Scene scene = new Scene(box, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private WebView createWebView() {
        WebView webView = new WebView();
        // Zoom all contents
        webView.setZoom(1.25);

        // Zoom text only, i.e. images remain unscaled
        webView.setFontScale(1.25);

        // Disable WebView context menu
        webView.setContextMenuEnabled(false);

        WebEngine webEngine = webView.getEngine();
        webEngine.load("http://google.com");
        webEngine.reload();

        // Set user-agent HTTP header
        webEngine.setUserAgent("Web View 1.0");

        return webView;
    }

    private void printWebHistory(WebView webView) {
        WebEngine webEngine = webView.getEngine();
        WebHistory history = webEngine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        Iterator<WebHistory.Entry> iterator = entries.iterator();
        while(iterator.hasNext()){
            WebHistory.Entry entry = iterator.next();
            System.out.println(entry.getUrl() + " " + entry.getTitle() + " " + entry.getLastVisitedDate());
        }
    }

    private void navigateWebHistory(WebView webView) {
        WebEngine webEngine = webView.getEngine();
        WebHistory history = webEngine.getHistory();
        System.out.println("Current page index = " + history.getCurrentIndex());
        history.go(-1);// go bac50kward
        System.out.println("Current page index = " + history.getCurrentIndex());
        history.go(1); // go forward
    }

    private WebView createLocalWebView() {
        WebView webView = new WebView();
        webView.getEngine().loadContent("Hello World", "text/html");
        return webView;
    }
}
