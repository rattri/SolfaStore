package solfadev.net.solfastore.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import solfadev.net.solfastore.BaseActivity;
import solfadev.net.solfastore.R;
import solfadev.net.solfastore.adapter.PricelistAdapter;
import solfadev.net.solfastore.model.Voucher;

/**
 * Created by Ratri on 11/22/2016.
 */

public class PricelistFragment extends BaseFragment {
    String title;
    RecyclerView recyclerView;
    PricelistAdapter pricelistAdapter;
    Voucher voucher;
    String idKategori, gambar, idVoucher;
    ImageView view_gambar;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseActivity = (BaseActivity) getActivity();
        baseActivity.setBaseFragment(this);/*WAJIB ADA*/
        View v = inflater.inflate(R.layout.pricelist_fragment, container, false);
//        view_gambar = (ImageView) v.findViewById(R.id.gambar);
//        context=baseActivity.getBaseContext();
//        Glide.with(context).load(BaseActivity.BASE_URL+"produk/"+gambar+".png").override(100, 100).into(view_gambar);

        setTitle(title);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv);
        pricelistAdapter = new PricelistAdapter(getBaseActivity(), idVoucher, title);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pricelistAdapter);
        pricelistAdapter.initData();
        return v;
    }

    public static PricelistFragment newInstance(Voucher voucher) {
        PricelistFragment fragment = new PricelistFragment();
        fragment.voucher = voucher;
        fragment.title = voucher.getNama();
        fragment.idKategori = voucher.getId_kategori();
        fragment.gambar=voucher.getGambar();
        fragment.idVoucher=voucher.getId();
        return fragment;
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
