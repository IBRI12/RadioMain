package com.example.radio.repository;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.radio.model.Playlist;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class PlaylistRepository
{
    MutableLiveData<List<Playlist>> PlaylistListMutableLiveData;
    FirebaseFirestore mFirestore;
    MutableLiveData<Playlist> PlaylistMutableLiveData;

    public PlaylistRepository() {
        this.PlaylistListMutableLiveData = new MutableLiveData<>();
        //define firestore
        mFirestore = FirebaseFirestore.getInstance();
        //define Playlistlist
        PlaylistMutableLiveData = new MutableLiveData<>();

    }


    //get Playlist from firebase firestore
    public MutableLiveData<List<Playlist>> getPlaylistListMutableLiveData() {
        Log.i("TAG", "getPlaylistListMutableLiveData: ");

        mFirestore.collection("playlist").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
            // ...

            List<Playlist> PlaylistList = new ArrayList<>();
            for (QueryDocumentSnapshot doc : value) {
                if (doc != null) {
                    PlaylistList.add(doc.toObject(Playlist.class));
                }
            }
            PlaylistListMutableLiveData.postValue(PlaylistList);
        }});
        return PlaylistListMutableLiveData;
    }

}
