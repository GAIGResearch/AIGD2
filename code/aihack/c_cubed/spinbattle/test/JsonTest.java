package c_cubed.spinbattle.test;

import c_cubed.spinbattle.core.SpinGameState;
import c_cubed.spinbattle.params.SpinBattleParams;
import com.google.gson.Gson;

public class JsonTest {
    public static void main(String[] args) {
        SpinBattleParams params = new SpinBattleParams();
        params.maxTicks = 100;

        params.useVectorField = false;
        params.useProximityMap = false;
        SpinGameState gameState1 = new SpinGameState().setParams(params).setPlanets();

        SpinGameState gameState = new SpinGameState().setParams(params).setPlanets();

        Gson gson = new Gson();
        System.out.println("Serialising...");
        String json = gson.toJson(gameState);
        System.out.println(json);

        System.out.println();
        System.out.println(json.length());
    }
}
