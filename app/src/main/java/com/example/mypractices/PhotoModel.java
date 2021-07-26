package com.example.mypractices;

import java.util.ArrayList;

public class PhotoModel {

    private String albumName;
    ArrayList<String> arrayList_PhotoPath = new ArrayList<String>();
    private String stringPhotoPath;
    private String firstPhoto;
    private String albumPhotos;
    private int numOfPhotos = 0;


    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String folderName) {
        this.albumName = folderName;
    }

    public ArrayList<String> getArrayList_PhotoPath() {
        return arrayList_PhotoPath;
    }

    public void setArrayList_PhotoPath(ArrayList<String> arrayList_PhotoPath) {
        this.arrayList_PhotoPath = arrayList_PhotoPath;
    }

    public String getStringPhotoPath() {
        return stringPhotoPath;
    }

    public void setStringPhotoPath(String stringPhotoPath) {
        this.stringPhotoPath = stringPhotoPath;
    }

    public String getFirstPhoto() {
        return firstPhoto;
    }

    public void setFirstPhoto(String firstPhoto) {
        this.firstPhoto = firstPhoto;
    }

    public String getAlbumPhotos() {
        return albumPhotos;
    }

    public void setAlbumPhotos(String albumPhotos) {
        this.albumPhotos = albumPhotos;
    }

    public void addPhotos(){
        this.numOfPhotos++;
    }
}
