package com.web.demo.dtos;

public class ReadProperties {

    private WordPressProperties wordPressProperties;
    private GlobalProperties globalProperties;

    private ReadDatasource readDatasource;

    public WordPressProperties getWordPressProperties() {
        return wordPressProperties;
    }

    public void setWordPressProperties(WordPressProperties wordPressProperties) {
        this.wordPressProperties = wordPressProperties;
    }

    public GlobalProperties getGlobalProperties() {
        return globalProperties;
    }

    public void setGlobalProperties(GlobalProperties globalProperties) {
        this.globalProperties = globalProperties;
    }

    public ReadDatasource getReadDatasource() {
        return readDatasource;
    }

    public void setReadDatasource(ReadDatasource readDatasource) {
        this.readDatasource = readDatasource;
    }
}
