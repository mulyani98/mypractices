package com.example.mypractices;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;
//import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AllPhotoFragment extends Fragment {

    public static ArrayList<PhotoModel> arrayListPhotos = new ArrayList<>();
    int position;

    boolean booleanFolder;
    PhotoAdapter photoAdapter;
    GridView gridViewPhotos;

    public AllPhotoFragment(){
        // Required empty public constructor

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gridViewPhotos = (GridView)  this.getActivity().findViewById(R.id.gridViewPhotos);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_allphoto, container, false);
    }


    public ArrayList<PhotoModel> albumNamePhotoPath(){
        arrayListPhotos.clear();

        int intPosition = 0;
        Uri uri;
        Cursor cursor;
        int columnIndexData, columnIndexAlbumName;


        String absolutePathPhoto = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = getActivity().getApplicationContext().getContentResolver().query(uri, projection, null, null, orderBy + "DESC");
//        cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null, orderBy + "DESC");

        columnIndexData = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        columnIndexAlbumName = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

        while (cursor.moveToNext()){
            absolutePathPhoto = cursor.getString(columnIndexData);
            Log.e("Column", absolutePathPhoto);
            Log.e("Folder", cursor.getString(columnIndexAlbumName));

            for (int i = 0; i < arrayListPhotos.size(); i++){
                if (arrayListPhotos.get(i).getAlbumName().equals(cursor.getString(columnIndexAlbumName))){
                    booleanFolder = true;
                    intPosition = i;
                    break;
                }
                else {
                    booleanFolder = false;
                }
            }

            if (booleanFolder){
                ArrayList<String> arrayListPath = new ArrayList<>();
                arrayListPath.addAll(arrayListPhotos.get(intPosition).getArrayList_PhotoPath());
                arrayListPath.add(absolutePathPhoto);
                arrayListPhotos.get(intPosition).setArrayList_PhotoPath(arrayListPath);
            }
            else{
                ArrayList<String> arraListPath = new ArrayList<>();
                arraListPath.add(absolutePathPhoto);
                PhotoModel photoModel = new PhotoModel();
                photoModel.setAlbumName(cursor.getString(columnIndexAlbumName));
                photoModel.setArrayList_PhotoPath(arraListPath);

                arrayListPhotos.add(photoModel);
            }
        }

        for (int i=0; i < arrayListPhotos.size(); i++){
            Log.e("ALBUM", arrayListPhotos.get(i).getAlbumName());
            for (int j=0; j < arrayListPhotos.get(i).getArrayList_PhotoPath().size(); j++){
                Log.e("PHOTO", arrayListPhotos.get(i).getArrayList_PhotoPath().get(j));
            }
        }
        photoAdapter = new PhotoAdapter(getContext().getApplicationContext(), arrayListPhotos, intPosition);
        gridViewPhotos.setAdapter(photoAdapter);
        return arrayListPhotos;
    }
}
