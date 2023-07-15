package com.sata.izonovel.Model;

public class DetailNovelRequest {
    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    private String collection;
    private String database;
    private String dataSource;
    private String filter;

    public static class Filter{
        public String getIsFavorit() {
            return isFavorit;
        }

        public void setIsFavorit(String isFavorit) {
            this.isFavorit = isFavorit;
        }

        private String isFavorit;
    }
}
