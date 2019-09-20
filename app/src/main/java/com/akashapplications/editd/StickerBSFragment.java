package com.akashapplications.editd;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class StickerBSFragment extends BottomSheetDialogFragment {

    public StickerBSFragment() {
        // Required empty public constructor
    }

    private StickerListener mStickerListener;

    public void setStickerListener(StickerListener stickerListener) {
        mStickerListener = stickerListener;
    }

    public interface StickerListener {
        void onStickerClick(Bitmap bitmap);
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };


    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_bottom_sticker_emoji_dialog, null);
        dialog.setContentView(contentView);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        RecyclerView rvEmoji = contentView.findViewById(R.id.rvEmoji);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        rvEmoji.setLayoutManager(gridLayoutManager);
        StickerAdapter stickerAdapter = new StickerAdapter();
        rvEmoji.setAdapter(stickerAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.ViewHolder> {

        int[] stickerList = new int[]
                {
                R.drawable.sticker1,
                R.drawable.sticker2,
                R.drawable.sticker3,
                R.drawable.sticker4,
                R.drawable.sticker5,
                R.drawable.sticker6,
                R.drawable.sticker7,
                R.drawable.sticker8,
                R.drawable.sticker9,
                R.drawable.sticker10,
                R.drawable.sticker11,
                R.drawable.sticker12,
                R.drawable.sticker13,
                R.drawable.sticker14,
                R.drawable.sticker15,
                R.drawable.sticker16,
                R.drawable.sticker17,
                R.drawable.sticker18,
                R.drawable.sticker19,
                R.drawable.sticker20,
                R.drawable.sticker21,
                R.drawable.sticker22,
                R.drawable.sticker23,
                R.drawable.sticker24,
                R.drawable.sticker25,
                R.drawable.sticker26,
                R.drawable.sticker27,
                R.drawable.sticker28,
                R.drawable.sticker29,
                R.drawable.sticker30,
                R.drawable.sticker31,
                R.drawable.sticker32,
                R.drawable.sticker33,
                R.drawable.sticker34,
                R.drawable.sticker35,
                R.drawable.sticker36,
                R.drawable.sticker37,
                R.drawable.sticker38,
                R.drawable.sticker39,
                R.drawable.sticker40,
                R.drawable.sticker41,
                R.drawable.sticker42,
                R.drawable.sticker43,
                R.drawable.sticker44,
                R.drawable.sticker45,
                R.drawable.sticker46,
                R.drawable.sticker47,
                R.drawable.sticker48,
                R.drawable.sticker49,
                R.drawable.sticker50,
                R.drawable.sticker51,
                R.drawable.sticker52,
                R.drawable.sticker53,
                R.drawable.sticker54,
                R.drawable.sticker55,
                R.drawable.sticker56,
                R.drawable.sticker57,
                R.drawable.sticker58,
                R.drawable.sticker59,
                R.drawable.sticker60,
                R.drawable.sticker61,
                R.drawable.sticker62,
                R.drawable.sticker63,
                R.drawable.sticker64,
                R.drawable.sticker65,
                R.drawable.sticker66,
                R.drawable.sticker67,
                R.drawable.sticker68,
                R.drawable.sticker69,
                R.drawable.sticker70,
                R.drawable.sticker71,
                R.drawable.sticker72,
                R.drawable.sticker73,
                R.drawable.sticker74,
                R.drawable.sticker75,
                R.drawable.sticker76,
                R.drawable.sticker77,
                R.drawable.sticker78,
                R.drawable.sticker79,
                R.drawable.sticker80,
                R.drawable.sticker81,
                R.drawable.sticker82,
                R.drawable.sticker83,
                R.drawable.sticker84,
                R.drawable.sticker85,
                R.drawable.sticker86,
                R.drawable.sticker87,
                R.drawable.sticker88,
                R.drawable.sticker89,
                R.drawable.sticker90,
                R.drawable.sticker91,
                R.drawable.sticker92,
                R.drawable.sticker93,
                R.drawable.sticker94,
                R.drawable.sticker95,
                R.drawable.sticker96,
                R.drawable.sticker97,
                R.drawable.sticker98,
                R.drawable.sticker99,
                R.drawable.sticker100,
                R.drawable.sticker101,
                R.drawable.sticker102,
                R.drawable.sticker103,
                R.drawable.sticker104,
                R.drawable.sticker105,
                R.drawable.sticker106,
                R.drawable.sticker107,
                R.drawable.sticker108,
                R.drawable.sticker109,
                R.drawable.sticker110,
                R.drawable.sticker111,
                R.drawable.sticker112,
                R.drawable.sticker113,
                R.drawable.sticker114,
                R.drawable.sticker115,
                R.drawable.sticker116,
                R.drawable.sticker117,
                R.drawable.sticker118,
                R.drawable.sticker119,
                R.drawable.sticker120,
                R.drawable.sticker121,
                R.drawable.sticker122,
                R.drawable.sticker123,
                R.drawable.sticker124,
                R.drawable.sticker125,
                R.drawable.sticker126,
                R.drawable.sticker127,
                R.drawable.sticker128,
                R.drawable.sticker129,
                R.drawable.sticker130,
                R.drawable.sticker131,
                R.drawable.sticker132,
                R.drawable.sticker133,
                R.drawable.sticker134,
                R.drawable.sticker135,
                R.drawable.sticker136,
                R.drawable.sticker137,
                R.drawable.sticker138,
                R.drawable.sticker139,
                R.drawable.sticker140,
                R.drawable.sticker141,
                R.drawable.sticker142,
                R.drawable.sticker143,
                R.drawable.sticker144,
                R.drawable.sticker145,
                R.drawable.sticker146,
                R.drawable.sticker147,
                R.drawable.sticker148,
                R.drawable.sticker149,
                R.drawable.sticker150,
                R.drawable.sticker151,
                R.drawable.sticker152,
                R.drawable.sticker153,
                R.drawable.sticker154,
                R.drawable.sticker155,
                R.drawable.sticker156,
                R.drawable.sticker157,
                R.drawable.sticker158,
                R.drawable.sticker159,
                R.drawable.sticker160,
                R.drawable.sticker161,
                R.drawable.sticker162,
                R.drawable.sticker163,
                R.drawable.sticker164,
                R.drawable.sticker165,
                R.drawable.sticker166,
                R.drawable.sticker167,
                R.drawable.sticker168,
                R.drawable.sticker169,
                R.drawable.sticker170,
                R.drawable.sticker171,
                R.drawable.sticker172,
                R.drawable.sticker173,
                R.drawable.sticker174,
                R.drawable.sticker175,
                R.drawable.sticker176,
                R.drawable.sticker177,
                R.drawable.sticker178,
                R.drawable.sticker179,
                R.drawable.sticker180,
                R.drawable.sticker181,
                R.drawable.sticker182,
                R.drawable.sticker183,
                R.drawable.sticker184,
                R.drawable.sticker185,
                R.drawable.sticker186,
                R.drawable.sticker187,
                R.drawable.sticker188,
                R.drawable.sticker189,
                R.drawable.sticker190,
                R.drawable.sticker191,
                R.drawable.sticker192,
                R.drawable.sticker193,
                R.drawable.sticker194,
                R.drawable.sticker195,
                R.drawable.sticker196,
                R.drawable.sticker197,
                R.drawable.sticker198,
                R.drawable.sticker199,
                R.drawable.sticker200,
                R.drawable.sticker201,
                R.drawable.sticker202,
                R.drawable.sticker203,
                R.drawable.sticker204,
                R.drawable.sticker205,
                R.drawable.sticker206,
                R.drawable.sticker207,
                R.drawable.sticker208,
                R.drawable.sticker209,
                R.drawable.sticker210,
                R.drawable.sticker211,
                R.drawable.sticker212,
                R.drawable.sticker213,
                R.drawable.sticker214,
                R.drawable.sticker215,
                R.drawable.sticker216,
                R.drawable.sticker217,
                R.drawable.sticker218,
                R.drawable.sticker219,
                R.drawable.sticker220,
                R.drawable.sticker221,
                R.drawable.sticker222,
                R.drawable.sticker223,
                R.drawable.sticker224,
                R.drawable.sticker225,
                R.drawable.sticker226,
                R.drawable.sticker227,
                R.drawable.sticker228,
                R.drawable.sticker229,
                R.drawable.sticker230,
                R.drawable.sticker231,
                R.drawable.sticker232,
                R.drawable.sticker233,
                R.drawable.sticker234,
                R.drawable.sticker235,
                R.drawable.sticker236,
                R.drawable.sticker237,
                R.drawable.sticker238,
                R.drawable.sticker239,
                R.drawable.sticker240,
                R.drawable.sticker241,
                R.drawable.sticker242,
                R.drawable.sticker243,
                R.drawable.sticker244,
                R.drawable.sticker245,
                R.drawable.sticker246,
                R.drawable.sticker247,
                R.drawable.sticker248,
                R.drawable.sticker249,
                R.drawable.sticker250,
                R.drawable.sticker251,
                R.drawable.sticker252,
                R.drawable.sticker253,
                R.drawable.sticker254,
                R.drawable.sticker255,
                R.drawable.sticker256,
                R.drawable.sticker257,
                R.drawable.sticker258,
                R.drawable.sticker259,
                R.drawable.sticker260,
                R.drawable.sticker261,
                R.drawable.sticker262,
                R.drawable.sticker263,
                R.drawable.sticker264,
                R.drawable.sticker265,
                R.drawable.sticker266,
                R.drawable.sticker267,
                R.drawable.sticker268,
                R.drawable.sticker269,
                R.drawable.sticker270,
                R.drawable.sticker271,
                R.drawable.sticker272,
                R.drawable.sticker273,
                R.drawable.sticker274,
                R.drawable.sticker275,
                R.drawable.sticker276,
                R.drawable.sticker277,
                R.drawable.sticker278,
                R.drawable.sticker279,
                R.drawable.sticker280,
                R.drawable.sticker281,
                R.drawable.sticker282,
                R.drawable.sticker283,
                R.drawable.sticker284,
                R.drawable.sticker285,
                R.drawable.sticker286,
                R.drawable.sticker287
        };

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sticker, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.imgSticker.setImageResource(stickerList[position]);
        }

        @Override
        public int getItemCount() {
            return stickerList.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgSticker;

            ViewHolder(View itemView) {
                super(itemView);
                imgSticker = itemView.findViewById(R.id.imgSticker);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mStickerListener != null) {
                            mStickerListener.onStickerClick(
                                    BitmapFactory.decodeResource(getResources(),
                                            stickerList[getLayoutPosition()]));
                        }
                        dismiss();
                    }
                });
            }
        }
    }

    private String convertEmoji(String emoji) {
        String returnedEmoji = "";
        try {
            int convertEmojiToInt = Integer.parseInt(emoji.substring(2), 16);
            returnedEmoji = getEmojiByUnicode(convertEmojiToInt);
        } catch (NumberFormatException e) {
            returnedEmoji = "";
        }
        return returnedEmoji;
    }

    private String getEmojiByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }
}