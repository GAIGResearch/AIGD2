package near32_players.heuristics;

import core.GameState;
import utils.Types;

public class OurHeuristic extends StateHeuristic {

    private int increaseParam = 2;
    private double lambda = 0.99;
    private double lambdaDeltaVSDXY = 0.99;
    private double lambdaEnemiesVSPlayer = 0.99;

    @Override
    public double evaluateState(GameState gs) {
        double value;
        double valueP;
        double valueCloseToB;

        if(gs.winner() == Types.RESULT.WIN)
            value = 1;
        else if (gs.winner() == Types.RESULT.LOSS)
            value = -1;
        else if (gs.winner() == Types.RESULT.TIE)
            value = 0;
        else {
            int pCount = 0;
            for (Types.TILETYPE id : gs.getAliveAgentIDs()) {
                if (id.getKey() != gs.getPlayerId()) {
                    pCount+=increaseParam;
                }
            }
            valueP = 1.0 / pCount;

            Types.TILETYPE[][] board = gs.getBoard();
            int[][] bombBS = gs.getBombBlastStrength();
            double sumDelta = 0;
            int countBomb = 0;
            double sumDXY = 0;
            for (int ip=0; ip<board.length; ip++)
            {
                for (int jp = 0; jp < board.length; jp++)
                {
                    if (board[ip][jp] == Types.TILETYPE.AGENT0 || board[ip][jp] == Types.TILETYPE.AGENT1 || board[ip][jp] == Types.TILETYPE.AGENT2 || board[ip][jp] == Types.TILETYPE.AGENT3)
                    {
                        for (int i = 0; i < gs.getBoard().length; i++)
                        {
                            for (int j = 0; j < gs.getBoard().length; j++)
                            {
                                if (bombBS[i][j] != 0)
                                // if there is a bomb here:
                                {
                                    countBomb += 1;
                                    int dx = Math.abs(i - ip);
                                    int dy = Math.abs(j - jp);
                                    double delta = Math.sqrt(dx * dx + dy * dy);
                                    if (delta < 1) {
                                        delta = 100;
                                    }
                                    if (delta > 3) {
                                        delta = 20;
                                    }

                                    if(board[ip][jp] == Types.TILETYPE.AGENT0)
                                    {
                                        sumDelta += (1-lambdaEnemiesVSPlayer)*delta;
                                        sumDXY += Math.abs(dx - 1) + Math.abs(dy - 1);
                                    }
                                    else
                                    {
                                        sumDelta += lambdaEnemiesVSPlayer*delta;
                                        sumDXY += dx + dy;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if(countBomb == 0){ countBomb=1;}
            //Let us incentivise the agent to have agents (in general) to be close to bombs while being itself on some diagonals rather on the direct line of blast (and have enemies on the direct line of blast):
            valueCloseToB = (lambdaDeltaVSDXY) / (0.001+sumDelta/countBomb) + (1-lambdaDeltaVSDXY) / (0.001+sumDXY/countBomb);



            value = (1.0-lambda)*valueP+lambda*valueCloseToB;
        }

        return value;
    }
}
