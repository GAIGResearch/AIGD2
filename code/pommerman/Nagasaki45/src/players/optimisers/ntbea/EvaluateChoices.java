package players.optimisers.ntbea;

import players.optimisers.evodef.BanditLandscapeModel;
import players.optimisers.evodef.SearchSpaceUtil;
import utils.Picker;
import utils.StatSummary;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by sml on 17/01/2017.
 */
public class EvaluateChoices {

    static Random random = new Random();
    static double tieBreakNoiseLevel = 1e-6;

    BanditLandscapeModel banditLandscapeModel;
    double kExplore;
    StatSummary exploreStats, exploitStats, combined;

    public EvaluateChoices(BanditLandscapeModel banditLandscapeModel, double kExplore) {
        this.banditLandscapeModel = banditLandscapeModel;
        this.kExplore = kExplore;
        exploitStats = new StatSummary("Exploit");
        exploreStats = new StatSummary("Explore");
        combined = new StatSummary("Combined");
    }

    public Picker<int[]> picker = new Picker<int[]>(Picker.MAX_FIRST);
    public Set<Integer> indices = new HashSet<>();

    int nAttempts = 0;
    int nNeighbours = 0;

    public EvaluateChoices setKExplore(double kExplore) {
        this.kExplore = kExplore;
        return this;
    }

    // checking for uniqueness of neighbours can be expensive
    // so only do it if necessary (useful for small search spaces)
    // otherwise set to false
    boolean checkUnique = false;

    // careful: the exploration term is used here
    public void add(int[] p) {
        if (checkUnique) {
            Integer ix = SearchSpaceUtil.indexOf(banditLandscapeModel.getSearchSpace(), p);
            if (indices.contains(ix)) {
                nAttempts++;
                // failed to find a unique point
                return;
            } else {
                indices.add(ix);
            }
        }
        double exploit = banditLandscapeModel.getMeanEstimate(p);
        double explore = banditLandscapeModel.getExplorationEstimate(p);
        exploitStats.add(exploit);
        exploreStats.add(explore);

        // add small random noise to break ties
        double combinedValue = exploit + kExplore * explore +
                random.nextDouble() * tieBreakNoiseLevel;
        combined.add(combinedValue);
//        System.out.format("\t %d\t %d\t %.2f\t %.2f\t %.2f\n", i, j,
//                exploit, explore, combinedValue);
        // System.out.println(exploit + " : " + explore);
        nNeighbours++;
        picker.add(combinedValue, p);
    }
    public void report() {
        System.out.println(exploitStats);
        System.out.println(exploreStats);
        System.out.println(combined);
        System.out.println();
    }

    public int n() {
        return nNeighbours + nAttempts / 4;
    }
}
