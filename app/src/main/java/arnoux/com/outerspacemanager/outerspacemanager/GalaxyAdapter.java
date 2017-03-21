package arnoux.com.outerspacemanager.outerspacemanager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import arnoux.com.outerspacemanager.outerspacemanager.Entity.Building;
import arnoux.com.outerspacemanager.outerspacemanager.Entity.BuildingDB;
import arnoux.com.outerspacemanager.outerspacemanager.Entity.OtherUsers;
import arnoux.com.outerspacemanager.outerspacemanager.retrofit.model.OuterSpaceManagerDAO;
import arnoux.com.outerspacemanager.outerspacemanager.retrofit.model.OuterSpaceManagerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static arnoux.com.outerspacemanager.outerspacemanager.Settings.Setting.KEY_TOKEN;
import static arnoux.com.outerspacemanager.outerspacemanager.Settings.Setting.SHARED_PREFERENCES_FILENAME;
import static java.lang.String.valueOf;

/**
 * Created by White on 14/03/2017.
 */

public class GalaxyAdapter extends RecyclerView.Adapter<GalaxyAdapter.GalaxyViewHolder>{

    private final List<OtherUsers> otherUsers;
    private final Context context;

    public GalaxyAdapter(List<OtherUsers> users, Context context) {
        this.otherUsers = users;
        this.context = context;
    }

    @Override
    public GalaxyAdapter.GalaxyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_galaxy, parent, false);
        GalaxyAdapter.GalaxyViewHolder viewHolder = new GalaxyAdapter.GalaxyViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GalaxyAdapter.GalaxyViewHolder holder, int position) {

        final OtherUsers otherUser = otherUsers.get(position);
        holder.otherUserShowName.setText(otherUser.getUsername());
        holder.otherUserShowPoints.setText(otherUser.getPoints().toString());
    }

    @Override
    public int getItemCount() {
        return otherUsers.size();
    }

    public class GalaxyViewHolder extends RecyclerView.ViewHolder {
        private TextView otherUserShowName;
        private TextView otherUserShowPoints;


        public GalaxyViewHolder(View itemView) {
            super(itemView);
            otherUserShowName = (TextView) itemView.findViewById(R.id.username);
            otherUserShowPoints = (TextView) itemView.findViewById(R.id.points);
        }
    }
}
