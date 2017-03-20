package arnoux.com.outerspacemanager.outerspacemanager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;
import arnoux.com.outerspacemanager.outerspacemanager.Entity.Building;
import arnoux.com.outerspacemanager.outerspacemanager.retrofit.model.OuterSpaceManagerDAO;
import arnoux.com.outerspacemanager.outerspacemanager.retrofit.model.OuterSpaceManagerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static arnoux.com.outerspacemanager.outerspacemanager.Settings.Setting.KEY_TOKEN;
import static arnoux.com.outerspacemanager.outerspacemanager.Settings.Setting.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE;
import static arnoux.com.outerspacemanager.outerspacemanager.Settings.Setting.SHARED_PREFERENCES_FILENAME;

/**
 * Created by White on 14/03/2017.
 */

// Class héritant de recycler view

public class BuildingsAdapter extends RecyclerView.Adapter<BuildingsAdapter.BuildingViewHolder>{

    private final List<Building> buildings;
    private final Context context;
    private boolean inConstruction;

    public BuildingsAdapter(List<Building> buildings, Context context) {
        this.buildings = buildings;
        this.context = context;
    }

    @Override
    public BuildingsAdapter.BuildingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_buildings, parent, false);
        BuildingViewHolder viewHolder = new BuildingViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BuildingsAdapter.BuildingViewHolder holder, int position) {

        SharedPreferences settings = context.getSharedPreferences(SHARED_PREFERENCES_FILENAME, 0);
        final String currentToken = settings.getString(KEY_TOKEN, null);

        final Building building = buildings.get(position);
        holder.buildingShowName.setText(building.getName());
        holder.buildingShowLevel.setText(building.getLevel().toString());

        final Integer bonusByLevel = (building.getLevel() * building.getAmountOfEffectByLevel());
        holder.buildingShowAmountOfEffectByLevel.setText(bonusByLevel.toString());

        holder.buildingShowMineralCostByLevel.setText(building.getMineralCostByLevel().toString());

        final Integer timeForUpgrade = ((building.getLevel() * building.getTimeToBuildByLevel()) / 60);
        holder.buildingTimeBeforeUpgrade.setText(timeForUpgrade.toString());

        final Integer gasPriceForUpgrade = (building.getLevel() * building.getGasCostByLevel());
        holder.buildingShowGasCostByLevel.setText(gasPriceForUpgrade.toString());

        final Integer mineralPriceForUpgrade = (building.getLevel() * building.getMineralCostByLevel());
        holder.buildingShowMineralCostByLevel.setText(mineralPriceForUpgrade.toString());

        holder.buildingUpgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.buildingUpgrade) {
                    final Integer buildingId = building.getBuildingId();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://outer-space-manager.herokuapp.com")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);


                        OuterSpaceManagerDAO buildingDAO = new OuterSpaceManagerDAO(context);
                        Toast.makeText(context, "Test", Toast.LENGTH_LONG).show();
                        buildingDAO.open();
                        buildingDAO.createBuilding(building.getBuildingId(), building.getName(), building.getLevel(), building
                                .getTimeToBuildByLevel(), System.currentTimeMillis());
                        buildingDAO.close();


                    Call<Building> call = service.upgradeBuilding(buildingId, currentToken);

                    call.enqueue(new Callback<Building>() {
                        @Override
                        public void onResponse(Call<Building> call, Response<Building> response) {
                            if (response.isSuccessful()) {

                                Intent intent = new Intent(context, MainActivity.class);
                                context.startActivity(intent);
                            } else {

                            }
                        }

                        @Override
                        public void onFailure(Call<Building> call, Throwable t) {

                        }
                    });
                }
            }
        });

        inConstruction = building.getInConstruction();
        if (inConstruction == true){
            holder.buildingShowInConstruction.setText("EN CONSTRUCTION");
            holder.buildingShowInConstruction.setTextColor(Color.RED);
            holder.buildingUpgrade.setVisibility(View.INVISIBLE);
        } else {
            holder.buildingShowInConstruction.setText("DISPONIBLE");
            holder.buildingShowInConstruction.setTextColor(Color.GREEN);
        }

        Glide
                .with(context)
                .load(building.getImageUrl())
                .centerCrop()
                .crossFade()
                .into(holder.buildingShowImage);
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }

    // Inner class héritant de RecyclerView.ViewHolder
    public class BuildingViewHolder extends RecyclerView.ViewHolder {
        private TextView buildingShowName;
        private TextView buildingShowLevel;
        private TextView buildingShowAmountOfEffectByLevel;
        private TextView buildingShowInConstruction;
        private ImageView buildingShowImage;
        private TextView buildingShowGasCostByLevel;
        private TextView buildingShowMineralCostByLevel;
        private Button buildingUpgrade;
        private TextView buildingTimeBeforeUpgrade;

        public BuildingViewHolder(View itemView) {
            super(itemView);
            buildingShowName = (TextView) itemView.findViewById(R.id.buildingShowName);
            buildingShowLevel = (TextView) itemView.findViewById(R.id.buildingShowLevel);
            buildingShowAmountOfEffectByLevel = (TextView) itemView.findViewById(R.id.buildingShowAmountOfEffectByLevel);
            buildingShowInConstruction = (TextView) itemView.findViewById(R.id.buildingShowInConstruction);
            buildingShowImage = (ImageView) itemView.findViewById(R.id.buildingShowImage);
            buildingShowGasCostByLevel = (TextView) itemView.findViewById(R.id.buildingShowGasCostByLevel);
            buildingShowMineralCostByLevel = (TextView) itemView.findViewById(R.id.buildingShowMineralCostByLevel);
            buildingUpgrade = (Button) itemView.findViewById(R.id.buildingUpgrade);
            buildingTimeBeforeUpgrade = (TextView) itemView.findViewById(R.id.buildingTimeBeforeUpgrade);
        }
    }
}
