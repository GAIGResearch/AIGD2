package near32_players.optimisers.ntbea;

import near32_players.optimisers.ParameterizedPlayer;
import near32_players.optimisers.evodef.EvaluatePommerman;
import near32_players.rhea.RHEAPlayer;
import near32_players.rhea.utils.RHEAParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Run a simple test
 */

public class RunNTBEA {
    public static void main(String[] args) {

        RHEAParams parameterSet = new RHEAParams();
        ParameterizedPlayer player = new RHEAPlayer(0, 0, parameterSet);

//        MCTSParams parameterSet = new MCTSParams();
//        ParameterizedPlayer player = new MCTSPlayer(0, 0, parameterSet);

        // Optimising parameters
        Map<String, Object[]> params = parameterSet.getParameterValues();
        ArrayList<Integer> possibleValues = new ArrayList<>();
        ArrayList<String> paramList = parameterSet.getParameters();

        assert paramList != null;
        assert params != null;

        for (String p : paramList) {
            if (parameterSet.getParameterParent(p) == null) {  // Use only top level parameters
                possibleValues.add(params.get(p).length);
            }
        }

        EvaluatePommerman problem = new EvaluatePommerman(possibleValues, player);
        double kExplore = 2;
        double epsilon = 0.5;
        NTupleBanditEA ntbea = new NTupleBanditEA().setKExplore(kExplore).setEpsilon(epsilon);

        // set a particlar NTuple System as the model
        // if this is not set, then it will use a default model
        NTupleSystem model = new NTupleSystem();

        // set up a non-standard tuple pattern
        model.use1Tuple = true;
        model.use2Tuple = true;
        model.use3Tuple = false;
        model.useNTuple = true;
        ntbea.setModel(model);

        int nEvals = 50;
        int[] solution = ntbea.runTrial(problem, nEvals);

//        System.out.println("Report: ");
//        new NTupleSystemReport().setModel(model).printDetailedReport();
//        new NTupleSystemReport().setModel(model).printSummaryReport();
//
//        System.out.println("Model created: ");
//        System.out.println(model);
//        System.out.println("Model used: ");
//        System.out.println(ntbea.getModel());
//
//        System.out.println();
        System.out.println("Solution returned: " + Arrays.toString(solution));
//        System.out.println("Solution fitness:  " + problem.trueFitness(solution));
        System.out.println("Solution fitness:  " + problem.test(solution));
//        System.out.println("k Explore: " + ntbea.kExplore);
//        System.out.println(timer);

        player.getParameters().translate(solution);
        player.getParameters().printParameters();
    }
}

