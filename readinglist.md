
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"  "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" version="-//W3C//DTD XHTML 1.1//EN" xml:lang="en"> 
  <head> 
	<title>AIGD2 Reading List</title>	
  </head>
<body onload="prettyPrint()">

<div class="accordion" id="acc8">
   <div class="accordion-group">
      <div class="accordion-heading">
         <a class="accordion-toggle" data-toggle="collapse" data-parent="#acc8" href="#collapseIns8">Parametrizing the Game Space</a>
      </div>
      <div id="collapseIns8" class="accordion-body collapse">
         <div class="accordion-inner">
         <ul>
         <li> <b>Exploring Game Space Using Survival Analysis</b>
             <ul>
			 <li> Aaron Isaksen, Dan Gopstein, and Andy Nealen. Exploring Game Space Using Survival Analysis. Foundations of Digital Games, 2015. <i>Best Paper in Artificial Intelligence and Game Technology.</i>
			 <li> Link: <a href="http://game.engineering.nyu.edu/wp-content/uploads/2015/04/exploring-game-space-FDG2015.pdf">PDF</a>
			 <li> Comment: Flappy birds variants, obtained by modifying game parameters.
             </ul>
             
         <li> <b>Comparing Player Skill, Game Variants, and Learning Rates with Survival Analysis</b>
			 <ul> 
				<li> Aaron Isaksen and Andy Nealen, Comparing Player Skill, Game Variants, and Learning Rates with Survival Analysis, Player Modeling Workshop hosted by AAAI AIIDE 2015
				<li> Link: <a href="http://game.engineering.nyu.edu/wp-content/uploads/2015/08/comparing-player-skill-game-variants-and-learning-rates-using-survival-analysis-FINAL.pdf">PDF</a>
				<li> Comment: Models player learning in a very simple way as the reduction of the standard deviation of the noise of each action an AI bot makes. This is a very interesting approach 
				that may work for very simple taptiming games, but would be unlikely to work for games where the nature of the learning is more complex than that.
			 </ul>
			 
         <li> <b>The N-Tuple Bandit Evolutionary Algorithm for Game Improvement</b>
			 <ul> 
				<li> Kamolwan Kunanusont, Raluca Gaina, Jialin Liu, Diego Perez-Liebana and Simon Lucas, The N-Tuple Bandit Evolutionary Algorithm for Game Improvement,  Proceedings of the Congress on Evolutionary Computation (2017).
				<li> Link: <a href="http://www.diego-perez.net/papers/NTupleBanditGameImprovement.pdf">PDF</a>
				<li> Comment: Evolves the parameters of a game with the N-tuple Bandit EA. This AI-Assisted Game Design paper shows the benefits of using this algorithm for improve the game via its parameters. 
				This paper was a follow up of the work performed during the GD2 module (2016) and it's been accepted for publication in IEEE CEC 2017.
			 </ul>
			 
         <li> <b>Evolving Game Skill-Depth using General Video Game AI Agents</b>
			 <ul> 
				<li> Jialin Liu, Julian Togelius, Diego Perez-Liebana and Simon M. Lucas, Evolving Game Skill-Depth using General Video Game AI Agents, in Proceedings of the Congress on Evolutionary Computation (2017). 
				<li> Link: <a href="http://www.diego-perez.net/papers/EvolvingGameSkillDepth.pdf">PDF</a>
				<li> Comment: General Video Game AI agents are used in this paper to evolve parameters of the game space with a Random Mutation Hill-Climber and a Multi-Armed Bandit version of the same algorithm. This paper
				explores these algorithms as a way to efficiently evaluate variations of the Space Battle game in spite of the high computational cost of the evaluations, with the objective of providing versions of the game
				that show a good skill depth.
			 </ul>
			 
         </ul>
         
         
         
      </div>
   </div>
 </div>
 </div>

 
<div class="accordion" id="acc9">
   <div class="accordion-group">
      <div class="accordion-heading">
         <a class="accordion-toggle" data-toggle="collapse" data-parent="#acc9" href="#collapseIns9">AI-Assisted Game Design (Evolutionary Game Design, Agent Design, and Automatic Game Evaluation)</a>
      </div>
      <div id="collapseIns9" class="accordion-body collapse">
         <div class="accordion-inner">
         <ul>
         <li> <b>An Experiment in Automatic Game Design</b>
             <ul>
			 <li> Julian Togelius and Jurgen Schmidhuber. An Experiment in Automatic Game Design. Proceedings of the IEEE Conference on Computational Intelligence and Games, 2008.
			 <li> Link: <a href="http://julian.togelius.com/Togelius2008An.pdf">PDF</a>
			 <li> Comment: One of the first papers on evolving game rules. The idea is to use the learnability of a game as a measure of quality. Measuring learnability is hard, the approach here is to estimate it 
			 by measuring how well a neural network can be evolved to play it.
             </ul>
             
         <li> <b>Evolutionary Game Design</b>
             <ul>
			 <li> C. Browne and F. Maire. Evolutionary Game Design. IEEE Transactions in Computational Intelligence and Artificial Intelligence in Games, 2011.
			 <li> Link: <a href="http://www.cameronius.com/cv/publications/ciaig-browne-maire-19.pdf">PDF</a>
			 <li> Comment: Paper on evolving 2 player board games using it’s own simple game description language. The approach of keeping the game design language (GDL) simple and hiding the complexity in the
             highlevel language interpreter for the GDL inspired the approach for VGDL. This is in contrast to more general and powerful GDLs which tend to lead to more complex descriptions of the games.
             </ul>
             
         <li> <b>Multi-Faceted Evolution of Simple Arcade Games</b>
             <ul>
			 <li> Michael Cook and Simon Colton. Multi-Faceted Evolution of Simple Arcade Games. Proceedings of the IEEE Conference on Computational Intelligence and Games, 2011.
			 <li> Link: <a href="http://wwwhomes.doc.ic.ac.uk/~sgc/papers/cook_cig11.pdf">PDF</a>
			 <li> Comment: This paper extends the work of Togelius and Schmidhuber to evolve mutliple aspects of a game in an orchestrated way, and uses different heuristics to 
			 measure game quality (unlike the learnability measure used by T and S).
             </ul>
             
             
         <li> <b>Automated Map Generation for the Physical Travelling Salesman Problem</b>
             <ul>
			 <li> Diego Perez-Liebana, Julian Togelius, Spyridon Samothrakis, Philipp Rolhfshagen and Simon M. Lucas. Automated Map Generation for the Physical Travelling Salesman Problem. 
			 IEEE Transactions on Evolutionary Computation (2013), DOI: 10.1109/TEVC.2013.2281508
			 <li> Link: <a href="http://www.diego-perez.net/papers/MOPTSPGen_TEVC2013.pdf">PDF</a>
			 <li> Comment: Using Agent-based PCG, explains the creation of PTSP levels by evolution.
             </ul>
             
             
         <li> <b> General Video Game Level Generation</b>
             <ul>
			 <li> Ahmed Khalifa, Diego Perez-Liebana, Simon Lucas and Julian Togelius. General Video Game Level Generation, 
			 Proceedings of the Genetic and Evolutionary Computation Conference (GECCO) (2016).
			 <li> Link: <a href="http://www.diego-perez.net/papers/GVGLG.pdf">PDF</a>
			 <li> Comment: Description of the GVGAI Level Generation track, where AI Agents are used to evaluate the levels generated by sample controllers in a general setting.
             </ul>

         <li><b> Semi-automated level design via auto-playtesting for handheld casual game creation</b>
             <ul>
                <li>Powley, E. J., Colton, S., Gaudl, S., Saunders, R., and Nelson, M. J. (2016, September), In Computational Intelligence and Games (CIG), 2016 IEEE Conference on (pp. 1-8). IEEE.
                <li> Link: <a href="http://ieeexplore.ieee.org/abstract/document/7860438/">PDF</a>
                <li>
         </ul>
         <li><b> Towards automatic personalised content creation for racing games</b>
             <ul>
                 <li>Togelius, Julian, Renzo De Nardi, and Simon M. Lucas, in Computational Intelligence and Games, 2007. CIG 2007. IEEE Symposium on. IEEE, 2007.
                     <li> Link: <a href="http://ieeexplore.ieee.org/abstract/document/4219051/">PDF</a>
                         <li>Comment: Using a multiobjective evolutionary algorithm to evolve the racing tracks in a car racing game.
             </ul>
             <li><b> AI as Evaluator: Search Driven Playtesting of Modern Board Games</b>
                 <ul>
                     <li>Fernando de Mesentier Silva and Scott Lee and Julian Togelius and Andy Nealen.
                         <li> Link: <a href="http://julian.togelius.com/DeMesentier2017AI.pdf">PDF</a>
                             <li>Comment: Ticket to Ride board game.
                 </ul>
                 <li><b>  Modeling player experience for content creation</b>
                     <ul>
                         <li>Christopher Pedersen, Julian Togelius, and Georgios N. Yannakakis, IEEE Transactions on Computational Intelligence and AI in Games 2.1 (2010): 54-67.
                             <li> Link: <a href="http://julian.togelius.com/Pedersen2010Modeling.pdf">PDF</a>
                                 <li>Comment:  Enhance the classic Super Mario Bros game with parameterizable level generation and gameplay metrics collection.
                                     </ul>
                     
                     
                     <li><b>  Combining Gameplay Data with Monte Carlo Tree Search to Emulate Human Play</b>
                     <ul>
                         <li>Sam Devlin, Anastasija Anspoka, Nick Sephton, Peter I. Cowling, Jeff Rollason, Twelfth Artificial Intelligence and Interactive Digital Entertainment Conference (2016).
                             <li> Link: <a href="https://www.aaai.org/ocs/index.php/AIIDE/AIIDE16/paper/view/14003">PDF</a>
                                 <li>Comment: Biasing MCTS using human gameplay data to design strong human-like playing agents for AI Factory Spades.</ul>
                     
                     </ul>
         
         
         
         
      </div>
   </div>
 </div>
 </div>
 
 
<div class="accordion" id="acc10">
   <div class="accordion-group">
      <div class="accordion-heading">
         <a class="accordion-toggle" data-toggle="collapse" data-parent="#acc10" href="#collapseIns10">Dagstuhl Seminars</a>
      </div>
      <div id="collapseIns10" class="accordion-body collapse">
         <div class="accordion-inner">
         <ul>
             
         <li> <b>Artificial and Computational Intelligence in Games (2012)</b>
             <ul>
			 <li> Simon M. Lucas, Michael Mateas, Mike Preuss, Pieter Spronck and Julian Togelius. Artificial and Computational Intelligence in Games. Report from Dagstuhl Seminar 12191.
			 <li> Link: <a href="http://drops.dagstuhl.de/opus/volltexte/2012/3651/pdf/dagrep_v002_i005_p043_s12191.pdf">PDF</a>
			 <li> Comment: Includes, amonf other things, the first paper on the Video Game Description Language.
             </ul>
             
         <li> <b>Artificial and Computational Intelligence in Games: Integration (2015)</b>
             <ul>
			 <li> Simon M. Lucas, Michael Mateas, Pieter Spronck, Julian Togelius, Mike Preuss. Artificial and Computational Intelligence in Games: Integration. Report from Dagstuhl Seminar 15051.
			 <li> Link: <a href="http://drops.dagstuhl.de/opus/volltexte/2012/3651/pdf/dagrep_v002_i005_p043_s12191.pdf">PDF</a>
			 <li> Comment: See Section 4.1: 4.1 The Dagstuhl Planet Wars Hackathon gives a flavour of the Game AI Hack
             </ul>
             
         </ul>
         
         
      </div>
   </div>
 </div>
 </div>
 
 
<div class="accordion" id="acc11">
   <div class="accordion-group">
      <div class="accordion-heading">
         <a class="accordion-toggle" data-toggle="collapse" data-parent="#acc11" href="#collapseIns11">Monte Carlo Tree Search (MCTS)</a>
      </div>
      <div id="collapseIns11" class="accordion-body collapse">
         <div class="accordion-inner">
         <ul>
         
         <li> <b>A Survey of Monte Carlo Tree Search Methods</b>
             <ul>
			 <li> Cameron Browne, Edward Powley, Daniel Whitehouse, Simon Lucas, Peter Cowling, Philipp Rohlfshagen, Stephen Tavener, Diego Perez-Liebana, Spyridon Samothrakis and Simon Colton. 
			 A Survey of Monte Carlo Tree Search Methods , IEEE Transactions on Computational Intelligence and AI in Games , Vol. 4:1, March, pp. 143, 2012. 
			 <li> Link: <a href="http://www.diego-perez.net/papers/MCTSSurvey.pdf">PDF</a>
			 <li> Comment: Survey about MCTS. Algorithm description, variants and applications.
             </ul>
             
             
         </ul>
         
         
         
      </div>
   </div>
 </div>
 </div>
 
 
<div class="accordion" id="acc12">
   <div class="accordion-group">
      <div class="accordion-heading">
         <a class="accordion-toggle" data-toggle="collapse" data-parent="#acc12" href="#collapseIns12">Rolling Horizon Evolutionary Algorithms (RHEA)</a>
      </div>
      <div id="collapseIns12" class="accordion-body collapse">
         <div class="accordion-inner">
         <ul>
         
         <li> <b>Rolling Horizon Evolution versus Tree Search for Navigation in Single Player Real-Time Games</b>
             <ul>
			 <li> Diego Perez-Liebana, Spyridon Samothrakis, Simon M. Lucas and Philipp Rolfshagen. Rolling Horizon Evolution versus Tree Search for Navigation in Single Player Real-Time Games
			 Proceedings of the Genetic and Evolutionary Computation Conference (GECCO) (2013), pages: 351-358.
			 <li> Link: <a href="http://www.diego-perez.net/papers/GECCO_RollingHorizonEvolution.pdf">PDF</a>
			 <li> Comment: One of the first application of Rolling Horizon Evolution to Real-Time Games.
             </ul>
         
         <li> <b>Analysis of Vanilla Rolling Horizon Evolution Parameters in General Video Game Playing</b>
             <ul>
			 <li> Raluca D. Gaina, Jialin Liu, Simon M. Lucas, Diego Perez-Liebana. Analysis of Vanilla Rolling Horizon Evolution Parameters in General Video Game Playing.
			 Proceedings of EvoApplications 2017 (EvoGames) (2017).
			 <li> Link: <a href="http://www.diego-perez.net/papers/VanillaRHEvolution.pdf">PDF</a>
			 <li> Comment: Analysis of the performance of RHEA parameters (population and individual length) in GVGAI games.
             </ul>
             
         
         <li> <b>Population Seeding Techniques for Rolling Horizon Evolution in General Video Game Playing</b>
             <ul>
			 <li> Raluca D. Gaina, Simon M. Lucas, Diego Perez-Liebana. Population Seeding Techniques for Rolling Horizon Evolution in General Video Game Playing.
			  Proceedings of the Congress on Evolutionary Computation (2017).
			 <li> Link: <a href="http://www.diego-perez.net/papers/PopSeedingGVGP.pdf">PDF</a>
			 <li> Comment: Explores and analyzes the performance of RHEA variants where the initial population of individual is seeded with different techniques, in the games from the GVGAI framework.
             </ul>
         
         </ul>
         
         
         
      </div>
   </div>
 </div>
 </div>
 
 
<div class="accordion" id="acc13">
   <div class="accordion-group">
      <div class="accordion-heading">
         <a class="accordion-toggle" data-toggle="collapse" data-parent="#acc13" href="#collapseIns13">General Video Game AI (GVGAI)</a>
      </div>
      <div id="collapseIns13" class="accordion-body collapse">
         <div class="accordion-inner">
         <ul>
         
         <li> <b>Towards a Video Game Description Language</b>
             <ul>
			 <li> M Ebner, J Levine, SM Lucas, T Schaul, T Thompson, J Togelius. Towards a Video Game Description Language, Dagstuhl followup.
			 <li> Link: <a href="http://people.idsia.ch/~tom/publications/dagstuhlvgdl.pdf">PDF</a>
			 <li> Comment: Original first paper in VGDL.
             </ul>
         
         <li> <b>A video game description language for modelbased or interactive learning</b>
             <ul>
			 <li> T Schaul. A video game description language for modelbased or interactive learning, IEEE Conference on Computational Intelligence in Games (CIG), 2013.
			 <li> Link: <a href="http://people.idsia.ch/~schaul/publications/pyvgdl.pdf">PDF</a>
			 <li> Comment: Significantly expanded follow on from the Dagstuhl paper this one includes results for multiple learners on multiple games.
             </ul>
             
         
         <li> <b>The 2014 General Video Game Playing Competition</b>
             <ul>
			 <li> Diego Perez-Liebana, Spyridon Samothrakis, Julian Togelius, Tom Schaul, Simon Lucas, Adrien Couetoux, Jerry Lee, Chong- U Lim, Tommy Thompson. 
			 The 2014 General Video Game Playing Competition. IEEE Transactions on Computational Intelligence and AI in Games , DOI: 10.1109/TCIAIG.2015.2402393 (2015).
			 <li> Link: <a href="http://www.diego-perez.net/papers/GVGAI2014Competition.pdf">PDF</a>
			 <li> Comment: Framework, controllers and results of the GVGAI 2014 competition.
             </ul>
         
         <li> For more GVGAI Papers, see the <a href="http://gvgai.net/papers.php:">papers</a> section of <a href="http://gvgai.net">www.gvgai.net</a>: 
             
         </ul>
         
         
         
      </div>
   </div>
 </div>
 </div>
 
 
</body>

</html>

