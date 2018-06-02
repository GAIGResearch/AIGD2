# AIGD2 Reading List

## Parametrizing the Game Space

<b>Exploring Game Space Using Survival Analysis</b>
 - Aaron Isaksen, Dan Gopstein, and Andy Nealen. Exploring Game Space Using Survival Analysis. Foundations of Digital Games, 2015. Best Paper in Artificial Intelligence and Game Technology.
 - Link: <a href="http://game.engineering.nyu.edu/wp-content/uploads/2015/04/exploring-game-space-FDG2015.pdf">PDF</a>
 - Comment: Flappy birds variants, obtained by modifying game parameters.
  
<b>Comparing Player Skill, Game Variants, and Learning Rates with Survival Analysis</b>
 - Aaron Isaksen and Andy Nealen, Comparing Player Skill, Game Variants, and Learning Rates with Survival Analysis, Player Modeling Workshop hosted by AAAI AIIDE 2015
 - Link: <a href="http://game.engineering.nyu.edu/wp-content/uploads/2015/08/comparing-player-skill-game-variants-and-learning-rates-using-survival-analysis-FINAL.pdf">PDF</a>
 - Comment: Models player learning in a very simple way as the reduction of the standard deviation of the noise of each action an AI bot makes. This is a very interesting approach that may work for very simple taptiming games, but would be unlikely to work for games where the nature of the learning is more complex than that.

<b>The N-Tuple Bandit Evolutionary Algorithm for Game Improvement</b>
 - Kamolwan Kunanusont, Raluca Gaina, Jialin Liu, Diego Perez-Liebana and Simon Lucas, The N-Tuple Bandit Evolutionary Algorithm for Game Improvement,  Proceedings of the Congress on Evolutionary Computation (2017).
 - Link: <a href="http://www.diego-perez.net/papers/NTupleBanditGameImprovement.pdf">PDF</a>
 - Comment: Evolves the parameters of a game with the N-tuple Bandit EA. This AI-Assisted Game Design paper shows the benefits of using this algorithm for improve the game via its parameters. This paper was a follow up of the work performed during the GD2 module (2016) and it's been accepted for publication in IEEE CEC 2017.

<b>Evolving Game Skill-Depth using General Video Game AI Agents</b>
 - Jialin Liu, Julian Togelius, Diego Perez-Liebana and Simon M. Lucas, Evolving Game Skill-Depth using General Video Game AI Agents, in Proceedings of the Congress on Evolutionary Computation (2017). 
 - Link: <a href="http://www.diego-perez.net/papers/EvolvingGameSkillDepth.pdf">PDF</a>
 - Comment: General Video Game AI agents are used in this paper to evolve parameters of the game space with a Random Mutation Hill-Climber and a Multi-Armed Bandit version of the same algorithm. This paper explores these algorithms as a way to efficiently evaluate variations of the Space Battle game in spite of the high computational cost of the evaluations, with the objective of providing versions of the game that show a good skill depth.

<b>The N-Tuple Bandit Evolutionary Algorithm for Game Agent Optimisation</b>
 - Simon Lucas, Jialin Liu, Diego Perez-Liebana, The N-Tuple Bandit Evolutionary Algorithm for Game Agent Optimisation, in Proceedings of the Congress on Evolutionary Computation (CEC) (2018). 
 - Link: <a href="http://www.diego-perez.net/papers/nTupleGameAgentOptimization.pdf">PDF</a>
 - Comment: Nominated for best paper award. This paper describes the N-Tuple Bandit Evolutionary Algorithm (NTBEA), an optimisation algorithm developed for noisy and expensive discrete (combinatorial) optimisation problems.
 
 

## AI-Assisted Game Design (Evolutionary Game Design, Agent Design, and Automatic Game Evaluation)

<b>An Experiment in Automatic Game Design</b>
 - Julian Togelius and Jurgen Schmidhuber. An Experiment in Automatic Game Design. Proceedings of the IEEE Conference on Computational     - Link: <a href="http://julian.togelius.com/Togelius2008An.pdf">PDF</a>
  - Comment: One of the first papers on evolving game rules. The idea is to use the learnability of a game as a measure of quality. Measuring learnability is hard, the approach here is to estimate it by measuring how well a neural network can be evolved to play it.

<b>Evolutionary Game Design</b>
  - C. Browne and F. Maire. Evolutionary Game Design. IEEE Transactions in Computational Intelligence and Artificial Intelligence in Games, 2011.
  - Link: <a href="http://www.cameronius.com/cv/publications/ciaig-browne-maire-19.pdf">PDF</a>
  - Comment: Paper on evolving 2 player board games using it’s own simple game description language. The approach of keeping the game design language (GDL) simple and hiding the complexity in the highlevel language interpreter for the GDL inspired the approach for VGDL. This is in contrast to more general and powerful GDLs which tend to lead to more complex descriptions of the games.

<b>Multi-Faceted Evolution of Simple Arcade Games</b>
  - Michael Cook and Simon Colton. Multi-Faceted Evolution of Simple Arcade Games. Proceedings of the IEEE Conference on Computational Intelligence and Games, 2011.
  - Link: <a href="http://wwwhomes.doc.ic.ac.uk/~sgc/papers/cook_cig11.pdf">PDF</a>
  - Comment: This paper extends the work of Togelius and Schmidhuber to evolve mutliple aspects of a game in an orchestrated way, and uses different heuristics to measure game quality (unlike the learnability measure used by T and S).

<b>Automated Map Generation for the Physical Travelling Salesman Problem</b>
  - Diego Perez-Liebana, Julian Togelius, Spyridon Samothrakis, Philipp Rolhfshagen and Simon M. Lucas. Automated Map Generation for the Physical Travelling Salesman Problem. EEE Transactions on Evolutionary Computation (2013), DOI: 10.1109/TEVC.2013.2281508
  - Link: <a href="http://www.diego-perez.net/papers/MOPTSPGen_TEVC2013.pdf">PDF</a>
  - Comment: Using Agent-based PCG, explains the creation of PTSP levels by evolution.
             
<b>General Video Game Level Generation</b>
  - Ahmed Khalifa, Diego Perez-Liebana, Simon Lucas and Julian Togelius. General Video Game Level Generation, Proceedings of the Genetic and Evolutionary Computation Conference (GECCO) (2016).
  - Link: <a href="http://www.diego-perez.net/papers/GVGLG.pdf">PDF</a>
  - Comment: Description of the GVGAI Level Generation track, where AI Agents are used to evaluate the levels generated by sample controllers in a general setting.

<b>Semi-automated level design via auto-playtesting for handheld casual game creation</b>
  - Powley, E. J., Colton, S., Gaudl, S., Saunders, R., and Nelson, M. J. (2016, September), In Computational Intelligence and Games (CIG), 2016 IEEE Conference on (pp. 1-8). IEEE.
  - Link: <a href="http://ieeexplore.ieee.org/abstract/document/7860438/">PDF</a>

<b>Towards automatic personalised content creation for racing games</b>
  - Togelius, Julian, Renzo De Nardi, and Simon M. Lucas, in Computational Intelligence and Games, 2007. CIG 2007. IEEE Symposium on. IEEE, 2007.
  - Link: <a href="http://ieeexplore.ieee.org/abstract/document/4219051/">PDF</a>
  - Comment: Using a multiobjective evolutionary algorithm to evolve the racing tracks in a car racing game.

<b>AI as Evaluator: Search Driven Playtesting of Modern Board Games</b>
  - Fernando de Mesentier Silva and Scott Lee and Julian Togelius and Andy Nealen.
  - Link: <a href="http://julian.togelius.com/DeMesentier2017AI.pdf">PDF</a>
  - Comment: Ticket to Ride board game.

<b>Modeling player experience for content creation</b>
  - Christopher Pedersen, Julian Togelius, and Georgios N. Yannakakis, IEEE Transactions on Computational Intelligence and AI in Games 2.1 (2010): 54-67.
  - Link: <a href="http://julian.togelius.com/Pedersen2010Modeling.pdf">PDF</a>
  - Comment:  Enhance the classic Super Mario Bros game with parameterizable level generation and gameplay metrics collection.

<b>Combining Gameplay Data with Monte Carlo Tree Search to Emulate Human Play</b>
  - Sam Devlin, Anastasija Anspoka, Nick Sephton, Peter I. Cowling, Jeff Rollason, Twelfth Artificial Intelligence and Interactive Digital Entertainment Conference (2016).
  - Link: <a href="https://www.aaai.org/ocs/index.php/AIIDE/AIIDE16/paper/view/14003">PDF</a>
  - Comment: Biasing MCTS using human gameplay data to design strong human-like playing agents for AI Factory Spades.</ul>
               
<b>Using a Team of General AI Algorithms to Assist Game Design and Testing</b>
 - Cristina Guerrero-Romero, Simon M Lucas, Diego Perez-Liebana, Using a Team of General AI Algorithms to Assist Game Design and Testing, in Proceedings on the Conference on Computational Intelligence and Games (CIG) (2018).
 - Link: <a href="http://www.diego-perez.net/papers/TeamGeneralAIGameDesign.pdf">PDF</a>
 - Comment: Vision paper. A team of agents with different capabilities to play-test a game (or generated content).
               
 
## Dagstuhl Seminars

<b>Artificial and Computational Intelligence in Games (2012)</b>
  - Simon M. Lucas, Michael Mateas, Mike Preuss, Pieter Spronck and Julian Togelius. Artificial and Computational Intelligence in Games. Report from Dagstuhl Seminar 12191.
  - Link: <a href="http://drops.dagstuhl.de/opus/volltexte/2012/3651/pdf/dagrep_v002_i005_p043_s12191.pdf">PDF</a>
  - Comment: Includes, among other things, the first paper on the Video Game Description Language.

<b>Artificial and Computational Intelligence in Games: Integration (2015)</b>
  - Simon M. Lucas, Michael Mateas, Pieter Spronck, Julian Togelius, Mike Preuss. Artificial and Computational Intelligence in Games: Integration. Report from Dagstuhl Seminar 15051.
  - Link: <a href="http://drops.dagstuhl.de/opus/volltexte/2012/3651/pdf/dagrep_v002_i005_p043_s12191.pdf">PDF</a>
  - Comment: See Section 4.1: 4.1 The Dagstuhl Planet Wars Hackathon gives a flavour of the Game AI Hack
  
<b>Artificial and Computational Intelligence in Games: AI-Driven Game Design (2017)</b>
 - Pieter Spronck and Elisabeth André and Michael Cook and Mike Preuss
 - Link: <a href="http://drops.dagstuhl.de/opus/volltexte/2018/8672/pdf/dagrep_v007_i011_p086_17471.pdf">PDF</a>
 - Comment: A <b>complete</b> seminar about AI-Driven Game Design. Includes topics on game design search spaces, AI-assisted board game play, emergence, a general language for matching tile games, gameplay evaluation measures and explainable AI for Game Designers. 
            
 
## Monte Carlo Tree Search (MCTS)

<b>A Survey of Monte Carlo Tree Search Methods</b>
  - Cameron Browne, Edward Powley, Daniel Whitehouse, Simon Lucas, Peter Cowling, Philipp Rohlfshagen, Stephen Tavener, Diego Perez-Liebana, Spyridon Samothrakis and Simon Colton. A Survey of Monte Carlo Tree Search Methods , IEEE Transactions on Computational Intelligence and AI in Games , Vol. 4:1, March, pp. 143, 2012. 
  - Link: <a href="http://www.diego-perez.net/papers/MCTSSurvey.pdf">PDF</a>
  - Comment: Survey about MCTS. Algorithm description, variants and applications.
  
## Rolling Horizon Evolutionary Algorithms (RHEA)

</b>Rolling Horizon Evolution versus Tree Search for Navigation in Single Player Real-Time Games</b>
  - Diego Perez-Liebana, Spyridon Samothrakis, Simon M. Lucas and Philipp Rolfshagen. Rolling Horizon Evolution versus Tree Search for Navigation in Single Player Real-Time Games Proceedings of the Genetic and Evolutionary Computation Conference (GECCO) (2013), pages: 351-358.
  - Link: <a href="http://www.diego-perez.net/papers/GECCO_RollingHorizonEvolution.pdf">PDF</a>
  - Comment: One of the first application of Rolling Horizon Evolution to Real-Time Games.

<b>Analysis of Vanilla Rolling Horizon Evolution Parameters in General Video Game Playing</b>
  - Raluca D. Gaina, Jialin Liu, Simon M. Lucas, Diego Perez-Liebana. Analysis of Vanilla Rolling Horizon Evolution Parameters in General Video Game Playing. Proceedings of EvoApplications 2017 (EvoGames) (2017).
  - Link: <a href="http://www.diego-perez.net/papers/VanillaRHEvolution.pdf">PDF</a>
  - Comment: Analysis of the performance of RHEA parameters (population and individual length) in GVGAI games.
            
<b>Population Seeding Techniques for Rolling Horizon Evolution in General Video Game Playing</b>
  - Raluca D. Gaina, Simon M. Lucas, Diego Perez-Liebana. Population Seeding Techniques for Rolling Horizon Evolution in General Video Game Playing. Proceedings of the Congress on Evolutionary Computation (2017).  
  - Link: <a href="http://www.diego-perez.net/papers/PopSeedingGVGP.pdf">PDF</a>
  - Comment: Explores and analyzes the performance of RHEA variants where the initial population of individual is seeded with different techniques, in the games from the GVGAI framework.

 
## General Video Game AI (GVGAI)

<b>Towards a Video Game Description Language</b>
  - M Ebner, J Levine, SM Lucas, T Schaul, T Thompson, J Togelius. Towards a Video Game Description Language, Dagstuhl followup.
  - Link: <a href="http://people.idsia.ch/~tom/publications/dagstuhlvgdl.pdf">PDF</a>
  - Comment: Original first paper in VGDL.

<b>A video game description language for modelbased or interactive learning</b>
  - T Schaul. A video game description language for modelbased or interactive learning, IEEE Conference on Computational Intelligence in Games (CIG), 2013.
  - Link: <a href="http://people.idsia.ch/~schaul/publications/pyvgdl.pdf">PDF</a>
  - Comment: Significantly expanded follow on from the Dagstuhl paper this one includes results for multiple learners on multiple games.

<b>The 2014 General Video Game Playing Competition</b>
  - Diego Perez-Liebana, Spyridon Samothrakis, Julian Togelius, Tom Schaul, Simon Lucas, Adrien Couetoux, Jerry Lee, Chong- U Lim, Tommy Thompson. The 2014 General Video Game Playing Competition. IEEE Transactions on Computational Intelligence and AI in Games , DOI: 10.1109/TCIAIG.2015.2402393 (2015).
  - Link: <a href="http://www.diego-perez.net/papers/GVGAI2014Competition.pdf">PDF</a>
  - Comment: Framework, controllers and results of the GVGAI 2014 competition.

<b>The 2016 Two-Player GVGAI Competition</b>
 - Raluca D. Gaina, Adrien Couetoux, Dennis J.N.J. Soemers, Mark H.M. Winands, Tom Vodopivec ,Florian Kirchgessner, Jialin Liu, Simon M. Lucas, Diego Perez-Liebana, The 2016 Two-Player GVGAI Competition, in IEEE Transactions on Computational Intelligence and AI in Games (2017). 
  - Link: <a href="http://www.diego-perez.net/papers/GVGAI20162PCompetition.pdf">PDF</a>
  - Comment: This paper showcases the setting and results of the first Two-Player General Video Game AI competition, which ran in 2016 at the IEEE World Congress on Computational Intelligence and the IEEE Conference on Computational Intelligence and Games.

<b>General Video Game AI: a Multi-Track Framework for Evaluating Agents, Games and Content Generation Algorithms</b>
 - Diego Perez-Liebana, Jialin Liu, Ahmed Khalifa, Raluca Gaina, Julian Togelius and Simon Lucas
 - Link: <a href="https://arxiv.org/pdf/1802.10363">PDF (arxiv)</a>
 - Comment: GVGAI Survey, which summarizes and cites all work done in GVGAI until 2017 (included). Under revision at IEEE Transactions on Computational Intelligence and AI in Games.

<b>General Win Prediction from Agent Experience</b>
 - Raluca D. Gaina, Simon M. Lucas, Diego Perez-Liebana, General Win Prediction from Agent Experience, in Proceedings on the Conference on Computational Intelligence and Games (CIG) (2018).
 - Link: <a href="http://www.diego-perez.net/papers/GeneralWinPrediction.pdf">PDF</a>
 - Comment: Based only on agent experience features, this paper presents a victory predictor for GVGAI Games.
 
 

For more GVGAI Papers, see the <a href="http://gvgai.net/papers.php:">papers</a> section of <a href="http://gvgai.net">www.gvgai.net</a>.
             
