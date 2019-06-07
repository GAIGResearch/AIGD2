package nagasaki45_players.rhea.utils;

import java.util.Map;

public class RHEALobsterParams extends RHEAParams {

    public final int LOBSTER_HEURISTIC = 2;

    public RHEALobsterParams() {
        super();
        this.heurisic_type = LOBSTER_HEURISTIC;
    }

    public Map<String, Object[]> getParameterValues() {
        Map<String, Object[]> parameterValues = super.getParameterValues();
        parameterValues.remove("heuristic_type");
        return parameterValues;
    }

    public static void main(String[] args) {
        new RHEALobsterParams().printParameterSearchSpace();
    }
}
