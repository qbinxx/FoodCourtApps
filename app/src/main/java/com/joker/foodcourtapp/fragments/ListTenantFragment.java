package com.joker.foodcourtapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.joker.foodcourtapp.ListMenuActivity;
import com.joker.foodcourtapp.R;
import com.joker.foodcourtapp.TenantActivity;
import com.joker.foodcourtapp.models.Category;
import com.joker.foodcourtapp.models.Tenant;
import com.joker.foodcourtapp.network.FoodCourtService;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rick on 18/08/16.
 */
public class ListTenantFragment extends Fragment {

    private ArrayList<Tenant> tenants = new ArrayList<>();
    private FoodCourtService service = new FoodCourtService();
    private MainAdapter adapter;
//    private onCardSelected mListener;
    private int categoriesId;
    private boolean MINI_CARD = false;
    private String message;

    static final int TYPE_HEADER = 0;
    static final int TYPE_MINI = 1;
    private Context c;


    public static ListTenantFragment newInstance() {
        return new ListTenantFragment();
    }

    public ListTenantFragment() {

    }


    class MainAdapter extends RecyclerView.Adapter<ViewHolder>{

        public MainAdapter(){

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int viewType){
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_card_big, viewGroup,false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder,final int position){
            final Tenant tenant = tenants.get(position);

            // Without this , some view maybe will be same after scrolling
            viewHolder.vImageView.setImageDrawable(null);
            viewHolder.vName.setText(null);
            //////////////////////////////////////////////////////////////

            viewHolder.setData(tenant.getName(),tenant.getImgUrl());

            viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(getActivity(),TenantActivity.class);
                    intent.putExtra("tenantName",tenant.getName());
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount(){
            return tenants.size();
        }

        @Override
        public int getItemViewType(int position) {
            if(MINI_CARD) return TYPE_MINI;
            else return TYPE_HEADER;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //View
        private ImageView vImageView;
        private TextView vName;

        private ViewHolder(View itemView) {
            super(itemView);

            // Get references to image and name.
            vImageView = (ImageView) itemView.findViewById(R.id.card_thumbnail);
            vName = (TextView) itemView.findViewById(R.id.card_judul);
        }

        private void setData(String name,String url) {
            vImageView.getLayoutParams().height = 320;
//            Picasso.with(itemView.getContext()).load(url).into(vImageView);
            Glide
                    .with(itemView.getContext())
                    .load(url)
                    .centerCrop()
                    .placeholder(R.color.grey)
                    .crossFade()
                    .into(vImageView);
            vName.setText(name);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        addDumpCategoryData(context);
//        adapter.notifyDataSetChanged();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        String url = "http://jsonplaceholder.typicode.com/posts";
        String url = "http://10.10.66.10:3000/getAllTenant";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response){
                        try {
                            for (int i = 0 ; i < response.length(); i++){
                                JSONObject objCategory = response.getJSONObject(i);

                                Tenant category = new Tenant(objCategory);
//                                category.setName(objCategory.getString("title"));
                                tenants.add(category);
                            }
                            adapter.notifyDataSetChanged();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley",error.toString());
            }
        }
        );
        requestQueue.add(jsonArrayRequest);

    }

    public void addDumpCategoryData(Context context){
//        final Resources resources = context.getResources();
//        String[] tenants = resources.getStringArray(R.array.tenantsName);
//        String[] tenantsImgUrl = resources.getStringArray(R.array.tenantsImgUrl);
////        for (int i=0;i< 1;i++){
//        for (int i=0;i< tenants.length;i++){
//            Tenant tenant = new Tenant();
//            tenant.setName(tenants[i]);
//            tenant.setImgUrl(tenantsImgUrl[i]);
//            this.tenants.add(tenant);
//        }

        // Get rage face images.
//        final TypedArray typedArray = resources.obtainTypedArray(R.array.images);
//        final int imageCount = tenants.length;
//        int mImageResIds = typedArray.getResourceId(0, 0);
//        typedArray.recycle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        c = getActivity().getApplicationContext();

        adapter = new MainAdapter();
        final Activity activity = getActivity();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
//        recyclerView.setLayoutManager(new GridLayoutManager(activity,2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        setData();

        return view;
    }

    private void setData(){
//        message = service.getAllTenant();
//        if (!message.contains("error")) {
//            try {
////                JSONObject json = new JSONObject(message);
//                JSONArray tenantListJson = new JSONArray(message);
//                for (int i = 0; i < tenantListJson.length(); i++) {
//                    JSONObject tenantJson = tenantListJson.getJSONObject(i);
//                    Tenant tenant = new Tenant(tenantJson);
//                    tenants.add(tenant);
//                }
//                adapter.notifyDataSetChanged();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }else{
//
//        }
//        Category c1 = new Category();
//        c1.setName("Asian Grill");
//        tenants.add(c1);
//
//        Category c2 = new Category();
//        c2.setName("Asian Food");
//        tenants.add(c1);




    }
}