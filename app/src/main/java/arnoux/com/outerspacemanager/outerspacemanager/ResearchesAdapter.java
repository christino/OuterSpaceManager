package arnoux.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import arnoux.com.outerspacemanager.outerspacemanager.Entity.Research;
import arnoux.com.outerspacemanager.outerspacemanager.retrofit.model.OuterSpaceManagerService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static arnoux.com.outerspacemanager.outerspacemanager.Settings.Setting.KEY_TOKEN;
import static arnoux.com.outerspacemanager.outerspacemanager.Settings.Setting.SHARED_PREFERENCES_FILENAME;

/**
 * Created by White on 20/03/2017.
 */

public class ResearchesAdapter extends RecyclerView.Adapter<ResearchesAdapter.ResearchViewHolder> {
    private final List<Research> researches;
    private final Context context;
    private boolean inConstruction;

    public ResearchesAdapter(List<Research> researches, Context context) {
        this.researches = researches;
        this.context = context;
    }

    @Override
    public ResearchesAdapter.ResearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_researches, parent, false);
        ResearchesAdapter.ResearchViewHolder viewHolder = new ResearchesAdapter.ResearchViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ResearchesAdapter.ResearchViewHolder holder, int position) {

        SharedPreferences settings = context.getSharedPreferences(SHARED_PREFERENCES_FILENAME, 0);
        final String currentToken = settings.getString(KEY_TOKEN, null);

        final Research research = researches.get(position);
        holder.researchShowName.setText(research.getName());
        holder.researchShowLevel.setText(Integer.toString(research.getLevel()));

        final Integer bonusByLevel = (research.getLevel() * research.getAmountOfEffectByLevel());
        holder.researchShowAmountOfEffectByLevel.setText(bonusByLevel.toString());

        holder.researchShowMineralCostByLevel.setText(Integer.toString(research.getMineralCostByLevel()));

        final Integer timeForUpgrade = ((research.getLevel() * research.getTimeToBuildByLevel()) / 60);
        holder.researchTimeBeforeUpgrade.setText(timeForUpgrade.toString());

        final Integer gasPriceForUpgrade = (research.getLevel() * research.getGasCostByLevel());
        holder.researchShowGasCostByLevel.setText(gasPriceForUpgrade.toString());

        final Integer mineralPriceForUpgrade = (research.getLevel() * research.getMineralCostByLevel());
        holder.researchShowMineralCostByLevel.setText(mineralPriceForUpgrade.toString());

        holder.researchUpgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.researchUpgrade) {
                    final Integer researchId = research.getSearchId();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://outer-space-manager.herokuapp.com")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);

                    Call<Research> call = service.upgradeResearch(researchId, currentToken);

                    call.enqueue(new Callback<Research>() {
                        @Override
                        public void onResponse(Call<Research> call, Response<Research> response) {
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(context, MainActivity.class);
                                context.startActivity(intent);
                            } else {

                            }
                        }

                        @Override
                        public void onFailure(Call<Research> call, Throwable t) {

                        }
                    });
                }
            }
        });

        inConstruction = research.isBuilding();
        if (inConstruction == true){
            holder.researchShowInConstruction.setText("EN COURS DE RECHERCHE");
            holder.researchShowInConstruction.setTextColor(Color.RED);
            holder.researchUpgrade.setVisibility(View.INVISIBLE);
        } else {
            holder.researchShowInConstruction.setText("DISPONIBLE");
            holder.researchShowInConstruction.setTextColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return researches.size();
    }

    // Inner class héritant de RecyclerView.ViewHolder
    public class ResearchViewHolder extends RecyclerView.ViewHolder {
        private TextView researchShowName;
        private TextView researchShowLevel;
        private TextView researchShowAmountOfEffectByLevel;
        private TextView researchShowInConstruction;
        private ImageView researchShowImage;
        private TextView researchShowGasCostByLevel;
        private TextView researchShowMineralCostByLevel;
        private Button researchUpgrade;
        private TextView researchTimeBeforeUpgrade;

        public ResearchViewHolder(View itemView) {
            super(itemView);
            researchShowName = (TextView) itemView.findViewById(R.id.researchShowName);
            researchShowLevel = (TextView) itemView.findViewById(R.id.researchShowLevel);
            researchShowAmountOfEffectByLevel = (TextView) itemView.findViewById(R.id.researchEffect);
            researchShowInConstruction = (TextView) itemView.findViewById(R.id.researchShowInConstruction);
            researchShowGasCostByLevel = (TextView) itemView.findViewById(R.id.researchShowGasCostByLevel);
            researchShowMineralCostByLevel = (TextView) itemView.findViewById(R.id.researchShowMineralCostByLevel);
            researchUpgrade = (Button) itemView.findViewById(R.id.researchUpgrade);
            researchTimeBeforeUpgrade = (TextView) itemView.findViewById(R.id.researchTimeBeforeUpgrade);
        }
    }
}
