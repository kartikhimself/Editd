package com.akashapplications.editd;

import android.app.Activity;

import com.akashapplications.editd.widget.FilterItem;
import com.akashapplications.editd.widget.TransferItem;
import com.hw.photomovie.render.GLTextureView;

import java.util.List;


/**
 * Created by huangwei on 2018/9/9.
 */
public interface IDemoView {
    GLTextureView getGLView();
    void setFilters(List<FilterItem> filters);
    Activity getActivity();

    void setTransfers(List<TransferItem> items);
}
