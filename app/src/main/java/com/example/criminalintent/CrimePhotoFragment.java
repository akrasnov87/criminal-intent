package com.example.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class CrimePhotoFragment extends DialogFragment {
    private static final String ARG_UUID = "id";
    private ImageView mImageView;

    public static CrimePhotoFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_UUID, crimeId);
        CrimePhotoFragment fragment = new CrimePhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        UUID uuid = (UUID)getArguments().getSerializable(ARG_UUID);

        Crime mCrime = CrimeLab.get(getActivity()).getCrime(uuid);
        File mPhotoFile = CrimeLab.get(getActivity()).getPhotoFile(mCrime);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_crime_photo, null);

        mImageView = v.findViewById(R.id.crime_big_photo);
        Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
        mImageView.setImageBitmap(bitmap);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.crime_photo)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (getTargetFragment() == null) {
                                    return;
                                }
                                getTargetFragment()
                                        .onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
                            }
                        })
                .create();
    }
}
