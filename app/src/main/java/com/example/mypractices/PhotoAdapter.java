package com.example.mypractices;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PhotoAdapter extends ArrayAdapter<PhotoModel> {

    Context context;
    ArrayList<PhotoModel> arrayListPhotos;
    int position;

    PhotoViewHolder photoViewHolder;

    private static class PhotoViewHolder{
        ImageView imageViewPhotos;
    }

    public PhotoAdapter(Context context, ArrayList<PhotoModel> arrayListPhoto, int pos) {
        super(context, R.layout.adapter_photo, arrayListPhoto);
        this.context = context;
        this.arrayListPhotos = arrayListPhoto;
        this.position = pos;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (arrayListPhotos.get(position).getArrayList_PhotoPath().size() > 0){
            return arrayListPhotos.get(position).getArrayList_PhotoPath().size();
        }
        else{
            return 1;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            photoViewHolder = new PhotoViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_photo, parent, false);

            photoViewHolder.imageViewPhotos = (ImageView) convertView.findViewById(R.id.imageViewPhotos);

            Bitmap bitmap = BitmapFactory.decodeFile(arrayListPhotos.get(position).getArrayList_PhotoPath().get(0));
            Bitmap bitmapThumbnail = Bitmap.createScaledBitmap(bitmap, 250, 250, false);
            photoViewHolder.imageViewPhotos.setImageBitmap(bitmapThumbnail);

            convertView.setTag(photoViewHolder);
        }
        else{
            photoViewHolder = (PhotoViewHolder) convertView.getTag();
        }

        return convertView;
    }
}
