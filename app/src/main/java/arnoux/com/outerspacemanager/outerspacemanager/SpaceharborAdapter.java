package arnoux.com.outerspacemanager.outerspacemanager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;

import arnoux.com.outerspacemanager.outerspacemanager.Entity.Building;
import arnoux.com.outerspacemanager.outerspacemanager.Entity.ShipIdAndAmount;
import arnoux.com.outerspacemanager.outerspacemanager.Entity.Spaceship;
import arnoux.com.outerspacemanager.outerspacemanager.retrofit.model.OuterSpaceManagerDAO;
import arnoux.com.outerspacemanager.outerspacemanager.retrofit.model.OuterSpaceManagerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static arnoux.com.outerspacemanager.outerspacemanager.Settings.Setting.KEY_TOKEN;
import static arnoux.com.outerspacemanager.outerspacemanager.Settings.Setting.SHARED_PREFERENCES_FILENAME;

/**
 * Created by White on 27/03/2017.
 */

public class SpaceharborAdapter extends RecyclerView.Adapter<SpaceharborAdapter.SpaceharborViewHolder>{
    private final List<Spaceship> spaceships;
    private final Context context;
    private Handler handler;

    public SpaceharborAdapter(List<Spaceship> spaceships, Context context) {
        this.spaceships = spaceships;
        this.context = context;
        handler = new Handler();
    }

    @Override
    public SpaceharborAdapter.SpaceharborViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_spaceharbor, parent, false);
        SpaceharborAdapter.SpaceharborViewHolder viewHolder = new SpaceharborAdapter.SpaceharborViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SpaceharborAdapter.SpaceharborViewHolder holder, int position) {

        SharedPreferences settings = context.getSharedPreferences(SHARED_PREFERENCES_FILENAME, 0);
        final String currentToken = settings.getString(KEY_TOKEN, null);

        final Spaceship spaceship = spaceships.get(position);
        holder.spaceshipShowName.setText(spaceship.getName());

        holder.spaceshipShowGasCost.setText(Integer.toString(spaceship.getGasCost()));
        holder.spaceshipShowMineralCost.setText(Integer.toString(spaceship.getMineralCost()));
        holder.spaceshipShowSpaceharborLevelRequired.setText(Integer.toString(spaceship.getSpatioportLevelNeeded()));
        holder.spaceshipShowTimeRequiredForConstruction.setText(Integer.toString(spaceship.getTimeToBuild()));
        holder.spaceshipShowLife.setText(Integer.toString(spaceship.getLife()));

        final int attackSum = ((spaceship.getMaxAttack() + spaceship.getMinAttack()) / 2);

        holder.spaceshipShowAttackSum.setText(Integer.toString(attackSum));
        holder.spaceshipShowShield.setText(Integer.toString(spaceship.getShield()));
        holder.spaceshipShowSpeed.setText(Integer.toString(spaceship.getSpeed()));

        holder.spaceshipConfirmBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.spaceshipConfirmBuild) {

                    final int shipId = spaceship.getShipId();
                    final int amount =  Integer.valueOf(holder.spaceshipAskNumberToBuild.getText().toString());

                    final ShipIdAndAmount shipIdAndAmount = new ShipIdAndAmount(shipId, amount);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://outer-space-manager.herokuapp.com")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);

                    Call<Spaceship> call = service.createShip(shipIdAndAmount, currentToken);

                    call.enqueue(new Callback<Spaceship>() {
                        @Override
                        public void onResponse(Call<Spaceship> call, Response<Spaceship> response) {
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(context, MainActivity.class);
                                context.startActivity(intent);
                            } else {

                            }
                        }

                        @Override
                        public void onFailure(Call<Spaceship> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return spaceships.size();
    }

    // Inner class héritant de RecyclerView.ViewHolder
    public class SpaceharborViewHolder extends RecyclerView.ViewHolder {

        private TextView spaceshipShowName;
        private TextView spaceshipShowGasCost;
        private TextView spaceshipShowMineralCost;
        private TextView spaceshipShowSpaceharborLevelRequired;
        private TextView spaceshipShowTimeRequiredForConstruction;
        private TextView spaceshipShowLife;
        private TextView spaceshipShowAttackSum;
        private TextView spaceshipShowShield;
        private TextView spaceshipShowSpeed;
        private EditText spaceshipAskNumberToBuild;
        private Button spaceshipConfirmBuild;

        public SpaceharborViewHolder(View itemView) {
            super(itemView);
            spaceshipShowName = (TextView) itemView.findViewById(R.id.spaceshipShowName);
            spaceshipShowGasCost = (TextView) itemView.findViewById(R.id.spaceshipShowGasCost);
            spaceshipShowMineralCost = (TextView) itemView.findViewById(R.id.spaceshipShowMineralCost);
            spaceshipShowSpaceharborLevelRequired = (TextView) itemView.findViewById(R.id.spaceshipShowSpaceharborLevelRequired);
            spaceshipShowTimeRequiredForConstruction = (TextView) itemView.findViewById(R.id.spaceshipShowTimeRequiredForConstruction);
            spaceshipShowLife = (TextView) itemView.findViewById(R.id.spaceshipShowLife);
            spaceshipShowAttackSum = (TextView) itemView.findViewById(R.id.spaceshipShowAttackSum);
            spaceshipShowShield = (TextView) itemView.findViewById(R.id.spaceshipShowShield);
            spaceshipShowSpeed = (TextView) itemView.findViewById(R.id.spaceshipShowSpeed);
            spaceshipAskNumberToBuild = (EditText) itemView.findViewById(R.id.spaceshipAskNumberToBuild);
            spaceshipConfirmBuild = (Button) itemView.findViewById(R.id.spaceshipConfirmBuild);
        }
    }
}
