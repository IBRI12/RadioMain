package com.example.radio.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.radio.R;
import com.example.radio.model.ModeratorBewertung;
import com.example.radio.model.Rating;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ModBewertungRepository
{
    MutableLiveData<List<ModeratorBewertung>> RatingListMutableLiveData;
    FirebaseFirestore mFirestore;
    MutableLiveData<Rating> RatingMutableLiveData;

    public ModBewertungRepository() {
        this.RatingListMutableLiveData = new MutableLiveData<>();
        //define firestore
        mFirestore = FirebaseFirestore.getInstance();
        //define Ratinglist
        RatingMutableLiveData = new MutableLiveData<>();

    }



    public void addRating(String mod, String userid, ModeratorBewertung moderatorBewertung, Context context) {
        Log.i("TAG", "addRating: ");
        mFirestore.collection("moderator").document(mod).collection("bewertung").document(userid).set(moderatorBewertung)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, R.string.text_label, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Fehlgeschlagen! Bewertung konnte nicht gespeichert werden", Toast.LENGTH_SHORT).show();

                    }
                });

    }

}
