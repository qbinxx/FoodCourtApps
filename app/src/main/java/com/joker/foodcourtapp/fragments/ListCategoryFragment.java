package com.joker.foodcourtapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.joker.foodcourtapp.MenuActivity;
import com.joker.foodcourtapp.R;
import com.joker.foodcourtapp.models.Category;
import com.joker.foodcourtapp.models.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rick on 18/08/16.
 */
public class ListCategoryFragment extends Fragment {

    private ArrayList<Category> categories = new ArrayList<>();
    private MainAdapter adapter;
//    private onCardSelected mListener;
    private int categoriesId;
    private boolean MINI_CARD = false;

    static final int TYPE_HEADER = 0;
    static final int TYPE_MINI = 1;
    private Context c;


    public static ListCategoryFragment newInstance() {
        return new ListCategoryFragment();
    }

    public ListCategoryFragment() {

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
            final Category category = categories.get(position);

            // Without this , some view maybe will be same after scrolling
            viewHolder.vImageView.setImageDrawable(null);
            viewHolder.vName.setText(null);
            //////////////////////////////////////////////////////////////

            viewHolder.setData(category.getName());

            viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(getActivity(),MenuActivity.class);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount(){
            return categories.size();
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

        private void setData(String name) {
            vImageView.getLayoutParams().height = 400;
            vImageView.setImageResource(R.drawable.logo_ppni);
            vName.setText(name);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://192.168.43.213:8080/outlet/get-outlets";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response){
                        try {
                            for (int i = 0 ; i < response.length(); i++){
                                JSONObject objCategory = response.getJSONObject(i);
                                Log.d("Outlet", objCategory.getString("name"));

                                Category category = new Category();
                                category.setName(objCategory.getString("name"));
                                categories.add(category);
                            }
                            adapter.notifyDataSetChanged();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley","Error");
            }
        }
        );
        requestQueue.add(jsonArrayRequest);

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
//        Category c1 = new Category();
//        c1.setName("Asian Grill");
//        categories.add(c1);
//
//        Category c2 = new Category();
//        c2.setName("Asian Food");
//        categories.add(c1);




    }
}